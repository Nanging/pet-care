$(document).ready(function () {




});





function endorse(){
    var s={};
    //in design!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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
        },
        error:function(result){
            //to be done
            console.log(result);
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
        },
        error:function(result){
            //to be done
            console.log(result);
        }

    });
}
function wordCount(){
    $("#commentCounter").text(""+$("#commentBox").val().length+"/400");
}
