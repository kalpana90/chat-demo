# Chat Application

This is a simple chat application.
And it contains the below functionalities.
```
- Login to the Chat Application
- List the other users
- Chat history 
- Send Chat
```

## Getting Started

Clone or download the project from the github repository.
Unzip the project if you downloaded instead of clone.

### Prerequisites
```
- Java installation
- Apache tomcat server Installation
- Mysql server installation
- Maven installation
```

### Installing

Make sure tomcat server and MySQL server is up and running.

```
- Go to src/main/chat-demo-script.sql inside the "chat-demo-master" directory
- Run "chat-demo-script.sql" script (To create the initial chat-demo database)
- Go to /src/main/resources/application.properties and update the database configurations according to your local setup
- Open a terminal
- Go inside the "chat-demo-master" directory 
- Run command "mvn clean compile install"
- Go to messaging/target and copy "messaging.war" file into your tomcat/webapps folder
- You can check the tomcat/logs/catalin.out log file to see the status of the war file deployment
- Then go to browser and go for this url "http://localhost:8080/messaging"
- Login Page will be displayed
```

## Running the tests
Credentials for Users

```
1)UserName - 032156756 and Password - priit (You will be login as Priit)
2)UserName - 0321543422 and Password - maksim (You will be login as Maksim)
```
Guidlines for Testing
```
- Open two different browsers (make sure not to use two tabs/two windows in same browser)
- Login to the application using the given credentials
- Display all the participants in the application.
- Once click on one participant, you can see your chat history with him/her.
- And you can send messages to specific participants and will recieve messages from them.
- Once you will get a new message a notification will appear and click on the dismiss button.
- Do not use browser refresh button and back button :)
```

## Authors

**Kalpana Anjali**