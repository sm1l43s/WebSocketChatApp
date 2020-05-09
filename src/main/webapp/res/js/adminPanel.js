function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

window.addEventListener("load", evt => {

    let a_btn = document.querySelector("#searchBtn");
    let input_search = document.querySelector(".inp_search");

    a_btn.onclick = function () {
        let href = a_btn.getAttribute("href");
        href += input_search.value;
        a_btn.setAttribute("href", href);
        console.log(a_btn);
    }
});