function offerClick(){
    $("#btnContainer").children("small").remove();
    var s={};
    s["targetID"]=$("#identity").text();
    $.ajax({
        url: "/offer",  //tartget url
        type: "POST",
        contentType: "application/json;charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(s),
        success:function(result){
            //to be done
            console.log(result);

            if(result.code==0){
                $("#offerBtn").attr("disabled",true);
                $("#btnContainer").append("<small style='color:green'><span class='glyphicon glyphicon-check'></span>You've shown your will to help</small>");
            }
            else{
                $("#btnContainer").append("<small style='color:orangered'><span class='glyphicon glyphicon-remove'></span>Offer refused by server</small>");
            }
        },
        error:function(result){
            //to be done
            console.log(result);
            $("#btnContainer").append("<small style='color:orangered'><span class='glyphicon glyphicon-remove'></span>404 Error</small>");
        }

    });
}
