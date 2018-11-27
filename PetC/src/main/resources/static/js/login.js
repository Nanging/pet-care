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
    var signupData=$('#signUpform').serializeArray()
    var s = {};
    $.each(signupData,function(index,field){
        s[field.name] = field.value;
    })
    console.log(s);
    $.ajax({
        url:"/signup",  //tartget url
        type:"POST",
        contentType: "application/json;charset=utf-8",
        dataType:'json',
        data: JSON.stringify(s),
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
    var s={
    		
    };
    $.each(loginData,function(index,field){
        s[field.name] = field.value;
    })
    console.log(s);
    $.ajax({
        url: "/login",  //tartget url
        type: "POST",
        contentType: "application/json;charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(s),
        success: function (result) {
            if (result) {
                alert("sucess");
                window.location.href="userInfo.html";
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
            return;
            alert("failed");
            //failed like 404 & 400
        }
    })
}