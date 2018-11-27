$(function() {
    $('.banner').unslider({
        speed: 500,
        delay: 3500,
        keys: false,
        fluid: true,
    });
//    var height=$('body').height;

//    $('.banner').unslider().data('unslider').start();

//    $('.banner').height(height);

});
function checkSignupEmpty(){
    if($("#newPassword").val()==""||$("#newUsername").val()==""||$("#newPhone").val()==""){
        $("#accept").attr("disabled",true)
    }
    else
        $("#accept").attr("disabled",false)
}





function checkEmpty(){
    if($("#passWord").val()==""||$("#userName").val()==""){
        $("#btnSubmit").attr("disabled",true)
    }
    else
        $("#btnSubmit").attr("disabled",false)
}

function signup() {
    //signuphere
    var signupData=$('#signUpform').serializeArray();
    console.log(signupData);
    $.ajax({
        url:"/signup",  //tartget url
        type:"POST",
        dataType:'json',
        data: signupData,
        success: function(result){
            if(result){
                alert("sucess");
                //signup&login success
                //create session and redirect
            }
            else{
                //failed
                //add specific function according to the result from backend later
                alert("failed");


            }

        },
        error:function () {
            alert("failed");
            //failed like 404 & 400
        }
    })
}


function login() {

    var loginData = $('#loginForm').serializeArray();
    console.log(loginData);
    $.ajax({
        url: "/login",  //tartget url
        type: "POST",
        dataType: 'json',
        data: loginData,
        success: function (result) {
            if (result) {
                alert("sucess");
                //login success
                //create session and redirect
            }
            else {
                //failed
                //add specific function according to the result from backend later
                alert("failed");


            }

        },
        error: function () {
            alert("failed");
            //failed like 404 & 400
        }
    })
}