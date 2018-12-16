function showDetail(tar) {
    console.log("click");
    var link=tar.getAttribute('data-detailTarget');
    console.log(link);
    $("#detailModal .modal-body").load(link,function () {
        $("#detailModal").show();
    })
}

function showInfo(tar) {
    console.log("click Info");
    var link=tar.getAttribute('data-detailTarget');
    console.log(link);
    $("#infoModal .modal-body").load(link,function () {
        $("#infoModal").show();
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
        dataType:'json',
        data: data,
        success:function () {
            console.log(success);
        },
        error:function () {
            console.log("fail");
        }
    })
}

function confirm(tar) {
    console.log("click Info");
    var link=tar.getAttribute('data-detailTarget');
    console.log(link);
    $("#infoModal .modal-body").load(link,function () {
        $("#infoModal").show();
    })
}
