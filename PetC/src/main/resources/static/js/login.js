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
            console.log(result);
            if(result.code==0){

                window.location.href="./main";

                //signup&login success
                //create session and redirect
            }
            else if(result.code==-1){//user already exists
                //add specific function according to the result from backend later
                $("#newUsername").parent().children("small").remove();
                $("#newUsername").parent().append("<small style='color:orangered'><span class='glyphicon glyphicon-remove'>  "+result.msg+"</small>")



            }
            else{//other error
                $("#newPhone").parent().children("small").remove();
                $("#newPhone").parent().append("<small style='color:orangered'><span class='glyphicon glyphicon-remove'>  "+result.msg+"</small>")

            }

        },
        error:function () {
            $("#newPhone").parent().children("small").remove();
            $("#newPhone").parent().append("<small style='color:orangered'><span class='glyphicon glyphicon-remove'>  "+"404 ERROR"+"</small>")
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
            console.log(result);
            if (result.code==0) {//success
                //alert("sucess");
                window.location.href="./main";
                //redirect to main page
                //login success
                //create session and redirect
            }
            else {//failed
                //failed
                //add specific function according to the result from backend later
                $("#userName").parent().children("small").remove();
                $("#userName").parent().append("<small style='color:orangered'><span class='glyphicon glyphicon-remove'>  "+result.msg+"</small>")



            }

        },
        error: function () {
            $("#userName").parent().children("small").remove();
            $("#userName").parent().append("<small style='color:orangered'><span class='glyphicon glyphicon-remove'>  "+"404 ERROR"+"</small>")
            //window.location.href="./main";

            //failed like 404 & 400
        }
    })
}