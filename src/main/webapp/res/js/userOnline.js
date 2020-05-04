window.addEventListener("load", init(), ajaxRequest());

function init() {
    setInterval(ajaxRequest, 10000);
}

function ajaxRequest() {
    $.ajax({
        type : 'POST',
        url : 'chat/ajax',//this is url mapping for controller
        success : function(response) {
            let users = JSON.parse(response);
            setCountOnlineUsers(users.length);
            setListUsers(users);
            //this response is list of object commming from server
        },
        error : function() {
            console.log("Error ajax");
        }
    });
}

function setListUsers(users) {

    let block = document.querySelector(".listUsers");
    block.innerHTML = "";

    for(let i = 0; i < users.length; i++) {
        let divUser = document.createElement("div");
        divUser.classList.add("user");

        let span = document.createElement("span");
        span.innerText = getName(users[i]);

        divUser.appendChild(span);
        block.appendChild(divUser);
    }
}

function getName(str) {
    let startPos = str.indexOf("name='");

    let finishPos = str.indexOf("',", startPos);

    str = str.substring(startPos + 6, finishPos);

    return str;
}

function setCountOnlineUsers(countUsers) {
    let block = document.querySelector("#countOnlineUsers");
    block.innerText = "";
    block.innerText = countUsers + " онлайн";
}







