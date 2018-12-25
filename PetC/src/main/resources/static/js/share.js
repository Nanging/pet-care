$(document).ready(function () {
    $('.grid').masonry({
        // options
        itemSelector: '.grid-item',
        columnWidth: 160,
        gutter: 10,
    });


    $("#imgInput").fileinput({
        uploadUrl:"/uploadImageShare",   //differ from foster
        msgFilesTooLess:"You should choose at least 1 image",
        msgFilesTooMany:"You should choose no more than 3 images",
        allowedFileExtensions:["jpg","jpeg","png"],
        msgInvalidFileExtension:"You can only upload jpg, jpeg, png files",
        minFileCount:1,
        maxFileCount:3,
        maxFileSize:3000,
        uploadAsync: false,
        msgSizeTooLarge:"The image should be no more than 3MB",
        fileActionSettings:{
            showUpload:false,
            showRemove:true,
            showZoom:false,
        },
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
            url: "/publishShare",  //tartget url
            type: "POST",
            contentType: "application/json;charset=utf-8",
            dataType: 'json',
            data: JSON.stringify(s),
            success:function(result){
                console.log(result);
                //to be done
                if(result.code==0) { //should use response code here
                    $("#publishModal").modal("hide");
                    $("#msgTitle").text("Success");
                    $("#msgContent").text("Your publish has been submitted successfully!");
                    $("#msgModal").modal("show");
                    $("#imgInput").fileinput("clear");
                }
                else{
                    $("#errorText").children("small").remove();
                    $("#errorText").append("<small style='color:orangered'><span class='glyphicon glyphicon-remove'></span>"+result.msg+"</small>")
                }
            },
            error:function (result) {
                console.log(result);
                //to be done
                $("#errorText").children("small").remove();
                $("#errorText").append("<small style='color:orangered'><span class='glyphicon glyphicon-remove'></span>404 Error!</small>");

            }

        })
    })

    $("#imgInput").on("filebatchuploaderror",function(event,data,msg){ //upload failed
        console.log(msg);                                          //http://plugins.krajee.com/file-input/plugin-events#filebatchuploaderror FOR HELP
        $("#imgInput").fileinput('unlock');
        $("#errorText").children("small").remove();
        $("#errorText").append("<small style='color:orangered'><span class='glyphicon glyphicon-remove'></span>"+msg+"</small>");
        //in design

    });

    $("#detailModal").on("hidden.bs.modal",function(e){
        $("#detailModal .modal-body").html("");
    })
    //after the detailbox close, reset it
    $(".grid-item").imagesLoaded().progress(function () {
        $(".grid").masonry("layout");
    });



    var isLateralNavAnimating = false;

    //open/close lateral navigation
    $('.navi-trigger').on('click', function(event){
        event.preventDefault();
        //stop if nav animation is running
        if( !isLateralNavAnimating ) {
            if($(this).parents('.csstransitions').length > 0 ) isLateralNavAnimating = true;

            $('body').toggleClass('navigation-is-open');
            $('.navigation-wrapper').one('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function(){
                //animation is over
                isLateralNavAnimating = false;
            });
        }
    });
})

function counterTitle() {

    $("#titleCounter").text(""+$("#newTitle").val().length+"/140");
}
function counterDetail() {
    $("#detailCounter").text(""+$("#newContent").val().length+"/2000");
}



function filterSubmit(){
    $("#searchForm").submit();
}
function inputCheck() {
    if ($("#newTitle").val() == '') {
        $("#errorText").append("<small style='color:orangered'><span class='glyphicon glyphicon-remove'></span>Title cannot be empty</small>");
        return false;
    }
    if ($("#newContent").val() == '') {
        $("#errorText").append("<small style='color:orangered'><span class='glyphicon glyphicon-remove'></span>Content cannot be empty</small>");
        return false;
    }
    return true;
}
function showDetail(tar){
    console.log("click");
    var link=tar.getAttribute('data-detailTarget');
    $("#detailModal .modal-body").load(link,function () {
        $("#detailModal").modal("show");
    });

}


function publishSubmit(){
    $("#errorText").children("small").remove();
    if(!inputCheck()) return;
    console.log("start");
    $("#imgInput").fileinput('upload').fileinput('lock');//upload images first
}

function refresh(){
    window.location.reload();
}