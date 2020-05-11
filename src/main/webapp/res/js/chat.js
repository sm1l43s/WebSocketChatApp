let chatUnit = {
    init() {
        this.startBox = document.querySelector(".start");
        this.chatBox = document.querySelector(".chatBox");

        this.startBtn = this.startBox.querySelector("button");
        this.nameInput = this.startBox.querySelector("input");

        this.roleUser = document.querySelector("#role_user").value;
        this.userId = document.querySelector(".user_id").value;

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
            id: this.userId,
            name: this.name,
            text: this.msgTextArea.value,
        });
    },

    onOpenSock() {

    },

    onMessage(parseMsg) {
        let message = document.createElement("div");
        message.className = "message";

        if(parseMsg.name == "I'm") {
            message.classList.add("me");
        } else {
            message.classList.add("another_user");
        }

        let from = document.createElement("div");
        from.className = "from";
        from.innerText = parseMsg.name.trim().substring(0, 1);

        let context = document.createElement("div");
        context.className = "context";

        let text_message = document.createElement("span");
        text_message.className = "text_message";
        text_message.innerText = parseMsg.text;

        let date = new Date();

        let str_for_time = "";

        if (this.roleUser == "ADMIN" || this.roleUser == "MODERATOR") {
            str_for_time = "Отправлено: "
                + "<a href='/chatApp/edit_moderator/" + parseMsg.id + "' class='user_chat'>"
                + parseMsg.name
                + "   " + "<i class=\"fas fa-link\"></i>" + "   "
                + "</a>"
                + " в "
                + "<i class=\"fas fa-clock\"></i>"
                + " " + date.getHours()
                + ":" + date.getMinutes();
        } else {
            str_for_time = "Отправлено: "
                + parseMsg.name
                + " в "
                + "<i class=\"fas fa-clock\"></i>"
                + " " + date.getHours()
                + ":" + date.getMinutes();
        }

        let time = document.createElement("time");
        time.innerHTML = str_for_time;

        context.appendChild(text_message);
        context.appendChild(time);

        message.appendChild(from);
        message.appendChild(context);

        this.chatMessageContainer.prepend(message);
    },

    onClose() {

    },

    sendMessage(msg) {
        this.ws.send(JSON.stringify(msg));
        this.onMessage({id: this.userId, name: "I'm", text:msg.text});
        this.msgTextArea.value = "";
    },

    openSocket() {
        this.name = this.nameInput.value;
        this.ws = new WebSocket("ws://localhost:8080/chatApp/chat/" + this.name + "_" + this.userId);
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