function ajaxRequest() {
    let user_id = document.querySelector(".user_id").value;
    let data = { id : user_id};

    $.ajax({
        type : 'POST',
        data : data,
        url : 'chat/closed',//this is url mapping for controller
        success : function(response) {
            console.log("yes");
        },
        error : function() {
            console.log("Error ajax");
        }
    });
}