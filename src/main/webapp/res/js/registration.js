window.addEventListener("load", start());

function start() {

    let username = document.querySelector("#name");
    let password1 = document.querySelector("#password");
    let password2 = document.querySelector("#password2");

    let btn_submit = document.querySelector("#btn_submit");

    btn_submit.addEventListener("click", evt => {

        let emptyField = "Не все поля заполнены!";
        let inCorrectLogin = "Логин не может быть менее 3-х символов и не более 20!";
        let littlePass = "Пороль не может быть менее 6 символов!";
        let inCorrectPass = "Пороли не совпадают";
        let successful = "Аккаунт успешно создан!";

        let alert = document.querySelector(".alert p");
        let p = document.createElement("p");

        if ( username.value == "" || password1.value == "" || password2.value == "") {
            evt.preventDefault();
            alert.innerText = " ";
            alert.innerText = emptyField;
        } else if( password1.value != password2.value) {
            evt.preventDefault();
            alert.innerText = " ";
            alert.innerText = inCorrectPass;
        } else if (username.value.toString().length < 3 || username.value.toString().length > 20) {
            evt.preventDefault();
            alert.innerText = " ";
            alert.innerText = inCorrectLogin;
        } else if (password1.value.toString().length < 6) {
            evt.preventDefault();
            alert.innerText = " ";
            alert.innerText = littlePass;
        } else {
            let span = document.createElement("span");
            alert.innerText = " ";
            span.className = "successful";
            span.innerText = successful;
            alert.appendChild(span);
        }
    });

    username.addEventListener("click", evt => {
        console.log();
    })
}