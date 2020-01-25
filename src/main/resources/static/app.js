var stompClient = null;
var greetingMessage;

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
    console.log($('#fromUser').val());

    stompClient.connect({'user' : $('#fromUser').val() }, function (frame) {
        $('#fromUser').prop('disabled', true);
        setConnected(true);
        console.log(frame);
        stompClient.send("/app/register", {}, JSON.stringify({'fromUser': $('#fromUser').val()}));
        greetingMessage = 'Welcome ' + $('#fromUser').val();
        notify(JSON.stringify({'fromUser': 'System', 'content' : greetingMessage}));

        stompClient.subscribe("/user/queue/errors", function(message) {
            alert("Error " + message.body);
        });

        stompClient.subscribe("/topic/public", function(message) {
            alert("Message Public: " + message);
        notify(message);
        });

        stompClient.subscribe("/user/queue/reply", function(message) {
            alert("Message user: " + message);
            notify(message.body);
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
    $('#fromUser').prop('disabled', false);
    console.log("Disconnected");
}


function sendMessage() {
    var message;

    if ($('#toUser').text() === 'public' || $('#toUser').text() === null) {
        message = {
            'fromUser' : $('#fromUser').val(),
            'content': $('#content').val()
        }
    } else {
        message = {
            'fromUser' : $('#fromUser').val(),
            'content' : $('#content').val(),
            'toUser' : $('#toUser').val()
        }
    }

    /*var sender = document.getElementById('sender').value;
    var content = document.getElementById('content').value;
    stompClient.send("/chatroom/send.message", {}, JSON.stringify({'sender': sender, 'content': content}));*/
    console.log(message);

    stompClient.send("/app/send", {}, JSON.stringify(message));
    $('#content').html("").focus();
}

function notify(message) {
    console.log(message);
    $("#conversation").append("<tr><td>" + message.fromUser + "</td>" + "<td>" + message.content + "</td></tr>");
}

function greeting() {

}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(); });
});