window.addEventListener("load", evt => start(evt));

function start(evt) {
    let div = document.querySelector(".side-panel");
    let btn = document.querySelector(".sidePanel");

    div.addEventListener("click", () => {
        div.classList.remove("open");
    });

    btn.addEventListener("click", e => {
        e.preventDefault();
        div.classList.toggle("open");
    })
}