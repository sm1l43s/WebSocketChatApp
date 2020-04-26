<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>Веб приложение "Чат"</title>
    <link rel="stylesheet" href="res/css/style.css">
    <style>
        .chatBox {
            display: none;
            width: 600px;
            height: 300px;
            overflow: scroll;
        }

        .messages {
            width: 500px;
            padding: 20px;
            margin: 10px;
        }

        .messages .from {
            background-color: #0e991b;
            text-align: center;
            line-height: 30px;
            color: white;
        }

        .messages .msg {
            background-color: aliceblue;
            border-radius: 10px;
            margin-bottom: 5px;
            overflow: hidden;
        }

        .messages .msg .text {
            padding: 10px;
        }

        textarea.msg {
            width: 540px;
            padding: 10px;
            resize: none;
            border: none;
            box-shadow: 2px 2px 5px 0 inset;
        }

    </style>

</head>
<body>
    <h1>Chat box</h1>

    <div class="start">
        <input type="text" class="username" value="${user.name}" placeholder="введите имя">
        <button id="start">start</button>
    </div>

    <div class="chatBox">
        <textarea class="msg"></textarea>
        <div class="messages">

        </div>
    </div>

</body>

<script>
    let chaUnit = {
        init() {
            this.startBox = document.querySelector(".start");
            this.chatBox = document.querySelector(".chatBox");

            this.startBtn = this.startBox.querySelector("button");
            this.nameInput = this.startBox.querySelector("input");

            this.msgTextArea = this.chatBox.querySelector("textarea");
            this.chatMessageContainer = this.chatBox.querySelector(".messages");

            this.bindEvents();
        },

        bindEvents() {
            this.startBtn.addEventListener("click", ev => this.openSocket());
            this.msgTextArea.addEventListener("keyup", e=>{
                if(e.ctrlKey && e.keyCode == 13 && this.msgTextArea.value != "") {
                    e.preventDefault();
                    this.send(this.msgTextArea.value);
                }
            })
        },

        send() {
            this.sendMessage({
                name: this.name,
                text: this.msgTextArea.value
            });
        },

        onOpenSock() {

        },

        onMessage(parseMsg) {
            let msgBlock = document.createElement("div");
            msgBlock.className = "msg";

            let fromBlock = document.createElement("div");
            fromBlock.className = "from";
            fromBlock.innerText = parseMsg.name;

            let textBlock = document.createElement("div");
            textBlock.className = "text";
            textBlock.innerText = parseMsg.text;

            msgBlock.appendChild(fromBlock);
            msgBlock.appendChild(textBlock);

            this.chatMessageContainer.prepend(msgBlock);


        },

        onClose() {

        },

        sendMessage(msg) {
            this.ws.send(JSON.stringify(msg));
            this.onMessage({name: "I'm", text:msg.text});
            this.msgTextArea.value = "";
        },

        openSocket() {
            this.name = this.nameInput.value;
            this.ws = new WebSocket("ws://localhost:8080/chat/" + this.name);
            this.ws.onopen = () => this.onOpenSock();
            this.ws.onmessage = (e) => this.onMessage(JSON.parse(e.data));
            this.ws.onclose = () => this.onClose();


            this.name = this.nameInput.value;
            this.startBox.style.display = "none";
            this.chatBox.style.display = "block";
        }

    };

    window.addEventListener("load", ev => chaUnit.init());

</script>

</html>
