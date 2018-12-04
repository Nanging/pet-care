$(document).ready(function () {
    $('.grid').masonry({
        // options
        itemSelector: '.grid-item',
        columnWidth: 160,
        gutter: 10,
    });
    var now = new Date();

    $('#newDate').datepicker({

        startDate: now,

    });

    $("#imgInput").fileinput({
        uploadUrl:"/uploadImage",
        msgFilesTooLess:"You should choose at least 1 image",
        msgFilesTooMany:"You should choose no more than 3 images",
        allowedFileExtensions:["jpg","jpeg","png"],
        msgInvalidFileExtension:"You can only upload jpg, jpeg, png files",
        minFileCount:1,
        maxFileCount:3,
        maxFileSize:3000,
        uploadAsync: false,
        msgSizeTooLarge:"The image should be no more than 3MB",
        showClose:false,
        showCancel:false,
        showUpload: false,                              //http://plugins.krajee.com/file-input#ajax-sync  FOR HELP
    }).on("filebatchuploadsuccess",function (event,data) {  //if images uploaded successfully, then post the publish request with the url of images
        var imageList=data.response.imagelist;           // http://plugins.krajee.com/file-input/plugin-events#filebatchuploadsuccess   FOR HELP
        var publishData=$("#publishForm").serializeArray();
        var s={"images":[]};
        $.each(publishData,function (index,field) {      //transfer form into json string
            s[field.name] = field.value;
        });
        $.each(imageList,function (index,field) {         //add the image url into the publish
            s["images"][index]=field;
        })
        console.log(s);
        $.ajax({
            url: "/publishFoster",  //tartget url
            type: "POST",
            contentType: "application/json;charset=utf-8",
            dataType: 'json',
            data: JSON.stringify(s),
            success:function(result){
                console.log(result);
                //to be done
            },
            error:function (result) {
                console.log(result);
                //to be done
            }

        })
    })

    $("#imgInput").on("filebatchuploaderror",function(event,data,msg){ //upload failed
        console.log(msg);                                          //http://plugins.krajee.com/file-input/plugin-events#filebatchuploaderror FOR HELP
        //in design

    });

})

function counterTitle() {

    $("#titleCounter").text(""+$("#newTitle").val().length+"/140");
}
function counterDetail() {
    $("#detailCounter").text(""+$("#newContent").val().length+"/800");
}

$(".grid-item").imagesLoaded().progress(function () {
    $(".grid").masonry("layout");
});

function filterSubmit(){
    $("#searchForm").submit();
}
function inputCheck(){
    if($("#newTitle").val()==''){
        $("#errorText").append("<small style='color:orangered'><span class='glyphicon glyphicon-remove'></span>Title cannot be empty</small>");
        return false;
    }
    if($("#newContent").val()==''){
        $("#errorText").append("<small style='color:orangered'><span class='glyphicon glyphicon-remove'></span>Details cannot be empty</small>");
        return false;
    }
}


function publishSubmit(){
    $("#errorText").children("small").remove();
    if(!inputCheck()) return;
    $("#imgInput").lock();
    $("#imgInput").upload(); //upload images first
    $("#imgInput").unlock();
}