function showDetail(tar) {
    console.log("click");
    var link=tar.getAttribute('data-detailTarget');
    console.log(link);
    $("#detailModal .modal-body").load(link,function () {
        $("#detailModal").modal("show");
    })
}

function showInfo(tar) {
    console.log("click Info");
    var link=tar.getAttribute('data-detailTarget');
    console.log(link);
    $("#infoModal .modal-body").load(link,function () {
        $("#infoModal").modal("show");
    })
}

function deleteData(tar) {
    console.log("click Delete");
    var link=tar.getAttribute('data-detailTarget');
    console.log(link);
    var s={};
    s["id"]=link;
    console.log(s);
    var data=JSON.stringify(s);
    console.log(data);
    $.ajax({
        url:link,
        type:"POST",
        success:function (result) {
            console.log(result);
            window.location.reload();
        },
        error:function (result) {
            console.log(result);
            console.log("fail-------------------");
        }
    })
}

function confirm(tar) {
    // alert("confirm");
    console.log("click Info");
    var link = tar.getAttribute('data-detailTarget');
    console.log(link);
	  $("#infoModal .modal-body").load(link,function () {
		  $("#infoModal").modal("show");
	  })
//    var person_id=tar.getAttribute('data-person_id');
//    var card_id=tar.getAttribute('data-card_id');
//    var s={};
//    s["person_id"]=person_id;
//    s["card_id"]=card_id;
//    var data=JSON.stringify(s);
//    console.log(data);
//    $.ajax({
//        url:'/user/fosterage/confirm/'+card_id+'/{applier}',
//        type:"POST",
//        success:function (result) {
//            // 确认后返回一个路由加载确认后的页面
//            console.log(result);
//            $("#infoModal .modal-body").load(result,function () {
//                $("#infoModal").modal("show");
//            })
//        },
//        error:function () {
//            console.log("error");
//        }
//    });
}
