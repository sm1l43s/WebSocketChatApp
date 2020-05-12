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
            let arrUsers = getArrayObjectUser(users);
            let currentUserId = document.querySelector(".user_id").value;
            setCountOnlineUsers(arrUsers.length);
            setListUsers(arrUsers);

            if( checkUser(arrUsers, currentUserId)) window.location = "http://localhost:8080/chatApp";
        },
        error : function() {
            console.log("Error ajax");
        }
    });
}

function checkUser(users, curentUserId) {
    let user = {};
    let flag = false;

    for (let i = 0; i < users.length; i++) {
        if (curentUserId == users[i].id) {
            user = users[i];
            flag = false;
            break;
        } else {
            flag = true;
        }
    }

    if (user.isBlocked == "true") {
        flag = true;
    }

    return flag;
}

function getArrayObjectUser(users) {
    let arrUsers = [];
    for (let i = 0; i < users.length; i++) {
       let user = {
            id: getFieldObject(users[i], "id=", 4),
            name: getFieldObject(users[i], "name='", 6),
            isBlocked: getFieldObject(users[i], "isBlocked=", 10)
        };
        arrUsers.push(user);
    }
    return arrUsers;
}

function getFieldObject(userStr, findField, posShift) {
    let startPos = userStr.indexOf(findField);
    let finishPos = userStr.indexOf(", ", startPos);

    let shift = 0;

    if (findField == "name='" || findField == "id=") shift = 1;

    return  userStr.substring(startPos + posShift, finishPos - shift);
}

function setListUsers(users) {

    let block = document.querySelector(".listUsers");
    block.innerHTML = "";

    for(let i = 0; i < users.length; i++) {
        let divUser = document.createElement("div");
        divUser.classList.add("user");

        let span = document.createElement("span");
        span.className = "username";
        span.innerText = users[i].name;

        divUser.appendChild(span);
        block.appendChild(divUser);
    }
}

function setCountOnlineUsers(countUsers) {
    let block = document.querySelector("#countOnlineUsers");
    block.innerText = "";
    block.innerText = countUsers + " онлайн";
}