window.onload = function() {

    // Create a new WebSocket.
    var sessionId = document.getElementById('sessionId').value;
    var path = document.getElementById('path').value;
    var socket = new WebSocket('ws://'+path + sessionId);


    // Handle any errors that occur.
    socket.onerror = function(error) {
        console.log('--ws-e--' + error);
    };

    // Show a connected message when the WebSocket is opened.
    socket.onopen = function(event) {
        console.log('--ws-o--');
    };


    // Handle messages sent by the server.
    socket.onmessage = function(event) {

        var message = event.data;

        if(message !== undefined)
        {
            var messagesList = document.getElementById('socketmessages');

            var obj = JSON.parse(message);
            var messageText = obj.message;
            var userName = obj.userName;
            var userId = obj.userId;
            var senderId = obj.senderId;
            console.log(messageText);
            var mess = document.getElementById("demo")
            mess.innerHTML = "<div id='rem' class='notification'>"+"<p class='new-notification'>"+ userName +"</p>"+"<p class='new-msg'>"+messageText+"</p>"+"<button id='dismiss-btn'>Dismiss</button> "+"</div>";
            document.getElementById("dismiss-btn").onclick = function() {

                var words = window.location.href;

                var n = words.split("/");
                if ('messages'===n[n.length - 1]){
                    document.getElementById("userId1").value=senderId;
                    document.getElementById("senderId1").value = userId;
                    document.getElementById("socketForm").submit();
                }else {
                    var elem = document.getElementById('demo');
                    elem.parentNode.removeChild(elem);
                }
            }

        }
    };

    var objDiv = document.getElementById("scrollDown");
    objDiv.scrollTop = objDiv.scrollHeight;

};
