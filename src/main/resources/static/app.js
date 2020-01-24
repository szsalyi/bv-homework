var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#conversation").html("");
}

function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log(frame);
        stompClient.subscribe("/user/queue/errors", function(message) {
            alert("Error " + message.body);
        });

        stompClient.subscribe("/topic/public.messages", function(message) {
            alert("Message Public: " + message);

        });

        stompClient.subscribe("/user/queue/reply", function(message) {
            alert("Message " + message);
            notify(message);
        });
    }, function(error) {
        alert("STOMP error " + error);
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}


function sendMessage() {
    var message;

    if ($('#toUser').text() == 'public') {
        message = {
            'sender' : $('#sender').val(),
            'content': $('#content').val()
        }
    } else {
        message = {
            'sender' : $('#sender').val(),
            'content' : $('#content').val(),
            'toUser' : $('#toUser').val()
        }
    }

    /*var sender = document.getElementById('sender').value;
    var content = document.getElementById('content').value;
    stompClient.send("/chatroom/send.message", {}, JSON.stringify({'sender': sender, 'content': content}));*/

    stompClient.send("/app/send.message", {}, JSON.stringify(message));
    $('#content').html("").focus();
}

function notify(message) {
    console.log(message.body);
    $("#conversation").append("<tr><td>" + message.body.sender + "</td>" + "<td>" + message.body.content + "</td></tr>");
}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(); });
});