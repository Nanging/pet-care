function showDetail(tar) {
    console.log("click");
    var link=tar.getAttribute('data-detailTarget');
    console.log(link);
    var s={};
    s["id"]=link;
    console.log(s);
    var data=JSON.stringify(s);
    console.log(data);
    $("#detailModal").modal("show");
    $.ajax({
        url:link,
        type:"POST",
        contentType: "application/json;charset=utf-8",
        dataType:'json',
        data: data,
        success:function () {
            console.log(success);
        },
        error:function () {
            console.log("fail");
        }
    })

    // $("#detailModal .modal-body").load(link,function () {
    //     $("#detailModal").modal("show");
    // });
}

function showInfo(tar) {
    console.log("click Info");
    var link=tar.getAttribute('data-detailTarget');
    console.log(link);
    var s={};
    s["id"]=link;
    console.log(s);
    var data=JSON.stringify(s);
    console.log(data)
    var content=$("#infoTable");
    content.text("");
    content.append("<table class=\"table table-striped\">\n" +
        "                              <thead>\n" +
        "                              <tr>\n" +
        "                                  <td>Name</td>\n" +
        "                                  <td>Phone Number</td>\n" +
        "                                  <td>Confirm</td>\n" +
        "                              </tr>\n" +
        "                              </thead>\n" +
        "                              <tbody>\n" +
        "                              <tr>\n" +
        "                                  <td>帖子相关人的名字</td>\n" +
        "                                  <td>贴子相关的人的手机号码</td>\n" +
        "                                  <td th:attr=\"data-infomationTarget=@{'/id'}\"><a class=\"glyphicon glyphicon-ok-circle\" onclick=\"confirm()\"></a></td>\n" +
        "                              </tr>\n" +
        "                              </tbody>\n" +
        "                          </table>");
    $.ajax({
        url:link,
        type:"POST",
        contentType: "application/json;charset=utf-8",
        dataType:'json',
        data: data,
        success:function (result) {
            var content=$("#infoTable");
            content.text("");
            // 返回一个string在content里添加
            content.append("");
        },
        error:function () {
            console.log("fail");
        }
    })
    $("#infoModal").modal("show");
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
        contentType: "application/json;charset=utf-8",
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