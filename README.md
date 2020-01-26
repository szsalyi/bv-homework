# Simple Chat Appication
The following project is a simple chat application

### Requirements
1. Java - 1.8 or higher
2. Maven - 3.x.x 

## Steps to Setup

## Chat 

Open localhost:8080 and you'll face the landing page.

1. Connect to the server
> Type a name with the user will use the app. 
>(Username input should be filled and should be unique)
2. A greeting message will show to users that are logged in
3. Send a message to a specific user
> Grab a connected user and type username in and the content (message).
4. Disconnect when we want to leave the app

## Status and Info endpoints
Spring boot actuator is integrated, so on the following endpoint the status can be checked
http://localhost:8080/actuator/health
Info
http://localhost:8080/actuator/info
