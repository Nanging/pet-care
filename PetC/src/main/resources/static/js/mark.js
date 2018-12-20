function isMark(tar) {
    var ismark=tar.getAttribute("data-isMark");
    // 未打分
    if(ismark==0){
        alert("unmarked");
    }
    // 已打分
    else {
        alert("marked");
        var id=tar.getAttribute("data-id");
        var s={};
        s["id"]=id;
        var data=JSON.stringify(s);
        console.log(data);
        btnUnabled();

        $.ajax({
            url:mark,
            type:'POST',
            dataType:'json',
            data: data,
            success:function (result) {
                // 返回评分数据
                switchGrade(result);
            },
            error:function () {
                alert("fail");
            }
        })

    }
}

function switchGrade(grade1) {
    $("#Grade").text(grade1);
    if(grade1==1){
        $('#markOne').attr("class","glyphicon glyphicon-star");
        $("#markTwo").attr("class","glyphicon glyphicon-star-empty");
        $("#markThree").attr("class","glyphicon glyphicon-star-empty");
        $("#markFour").attr("class","glyphicon glyphicon-star-empty");
        $("#markFive").attr("class","glyphicon glyphicon-star-empty");
    }
    else if(grade1==2){
        $('#markOne').attr("class","glyphicon glyphicon-star");
        $("#markTwo").attr("class","glyphicon glyphicon-star");
        $("#markThree").attr("class","glyphicon glyphicon-star-empty");
        $("#markFour").attr("class","glyphicon glyphicon-star-empty");
        $("#markFive").attr("class","glyphicon glyphicon-star-empty");
    }
    else if(grade1==3){
        $('#markOne').attr("class","glyphicon glyphicon-star");
        $("#markTwo").attr("class","glyphicon glyphicon-star");
        $("#markThree").attr("class","glyphicon glyphicon-star");
        $("#markFour").attr("class","glyphicon glyphicon-star-empty");
        $("#markFive").attr("class","glyphicon glyphicon-star-empty");
    }
    else if(grade1==4){
        $('#markOne').attr("class","glyphicon glyphicon-star");
        $("#markTwo").attr("class","glyphicon glyphicon-star");
        $("#markThree").attr("class","glyphicon glyphicon-star");
        $("#markFour").attr("class","glyphicon glyphicon-star");
        $("#markFive").attr("class","glyphicon glyphicon-star-empty");
    }
    else if(grade1==5){
        $('#markOne').attr("class","glyphicon glyphicon-star");
        $("#markTwo").attr("class","glyphicon glyphicon-star");
        $("#markThree").attr("class","glyphicon glyphicon-star");
        $("#markFour").attr("class","glyphicon glyphicon-star");
        $("#markFive").attr("class","glyphicon glyphicon-star");
    }
}

function getMark(tar) {
    var grade=tar.getAttribute("value");
    alert(grade);
    switchGrade(grade);
}

function clearGrade() {
    $("#Grade").text(0);
    $('#markOne').attr("class","glyphicon glyphicon-star-empty");
    $("#markTwo").attr("class","glyphicon glyphicon-star-empty");
    $("#markThree").attr("class","glyphicon glyphicon-star-empty");
    $("#markFour").attr("class","glyphicon glyphicon-star-empty");
    $("#markFive").attr("class","glyphicon glyphicon-star-empty");
}

function submitGrade(tar) {
    var grade=$("#Grade").text();
    alert(grade);
    var id=tar.getAttribute("data-id");
    alert(id);
   var s={};
   s["grade"]=grade;
   s["id"]=id;
   console.log(s);
   var data=JSON.stringify(s);
   console.log(data);
   $.ajax({
       url:"mark",
       type:"POST",
       dataType:'json',
       data:data,
       success:function () {
          btnUnabled();
       },
       error:function () {
           console.log("fail")
       }

   });



}

function btnUnabled() {
    $("#submitBtn").attr("disabled",true);
    $("#clearBtn").attr("disabled",true);
    $("#prompt").text("Already Marked");
    $(".starBtn").attr("disabled",true);

}