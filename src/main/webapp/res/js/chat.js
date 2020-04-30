let chatUnit = {
    init() {
        this.startBox = document.querySelector(".start");
        this.chatBox = document.querySelector(".chatBox");

        this.startBtn = this.startBox.querySelector("button");
        this.nameInput = this.startBox.querySelector("input");

        this.msgTextArea = document.querySelector(".msg");
        this.chatMessageContainer = this.chatBox.querySelector(".messages");
        this.btn = document.querySelector(".inputMsg button");
        this.bindEvents();
    },

    bindEvents() {
        this.startBtn.addEventListener("click", ev => this.openSocket());
        this.msgTextArea.addEventListener("keyup", e=>{
            if(this.msgTextArea.value != "" && e.keyCode == 13 ) {
                e.preventDefault();
                this.send(this.msgTextArea.value);
            }
        });
        this.btn.addEventListener("click", evt => {
            if(this.msgTextArea.value != "") {
                evt.preventDefault();
                this.send(this.msgTextArea.value);
            }
        })
    },

    send() {
        this.sendMessage({
            name: this.name,
            text: this.msgTextArea.value,
        });
    },

    onOpenSock() {

    },

    onMessage(parseMsg) {
        let msgBlock = document.createElement("div");
        msgBlock.className = "msg";

        if(parseMsg.name == "I'm") {
            msgBlock.classList.add("me");
        } else {
            msgBlock.classList.add("anotherUser");
        }

        let fromBlock = document.createElement("span");
        fromBlock.className = "from";
        fromBlock.innerText = parseMsg.name;

        let textBlock = document.createElement("span");
        textBlock.className = "text";
        textBlock.innerText = parseMsg.text;

        let date = new Date();
        let timeBlock = document.createElement("span");
        timeBlock.className = "timeBlock";
        timeBlock.innerText = date.getFullYear() + "/" + date.getMonth() + "/" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();

        msgBlock.appendChild(fromBlock);
        msgBlock.appendChild(textBlock);
        msgBlock.appendChild(timeBlock);

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
        this.msgTextArea.style.display = "inline-block";
        this.btn.style.display = "inline-block";
    }

};

window.addEventListener("load", ev => chatUnit.init());