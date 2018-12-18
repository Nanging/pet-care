$(document).ready(function () {




});





function endorse(){
    $("#btnContainer").children("small").remove();
    var s={};
    s["targetID"]=$("#identity").text();
    $.ajax({
        url: "/endorse",  //tartget url
        type: "POST",
        contentType: "application/json;charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(s),
        success:function(result){
            //to be done
            console.log(result);

            if(result.code==0){
                $("#endorseBtn").disable();
                $("#btnContainer").append("<small style='color:lawngreen'><span class='glyphicon glyphicon-check'></span>Endorsed</small>");
            }
            else{
                $("#btnContainer").append("<small style='color:orangered'><span class='glyphicon glyphicon-remove'></span>Endorse refused</small>");
            }
        },
        error:function(result){
            //to be done
            console.log(result);
            $("#btnContainer").append("<small style='color:orangered'><span class='glyphicon glyphicon-remove'></span>404 Error</small>");
        }

    });
}
function comment(){
    $("#clps").collapse("toggle");
}
function commentSubmit(){
    $("#submitGroup").children("small").remove();
    if ($("#commentBox").val() == '') {
        $("#submitGroup").append("<small style='color:orangered'><span class='glyphicon glyphicon-remove'></span>Comment cannot be empty</small>");
        return;
    };
    var s={};
    s["newComment"]=$("#commentBox").val();
    s["targetID"]=$("#identity").text();
    console.log(s);
    $.ajax({
        url: "/publishComment",  //tartget url
        type: "POST",
        contentType: "application/json;charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(s),
        success:function(result){
            //to be done
            console.log(result);
            if(result.code==0){
                $("#commentBox").val("");
                $("#submitGroup").append("<small style='color:lawngreen'><span class='glyphicon glyphicon-check'></span>Comment submitted</small>");
            }
            else{
                $("#submitGroup").append("<small style='color:orangered'><span class='glyphicon glyphicon-remove'></span>Comment submit refused</small>");
            }
        },
        error:function(result){
            //to be done
            $("#submitGroup").append("<small style='color:orangered'><span class='glyphicon glyphicon-remove'></span>404 Error</small>");
            console.log(result);
        }

    });
}
function wordCount(){
    $("#commentCounter").text(""+$("#commentBox").val().length+"/400");
}
