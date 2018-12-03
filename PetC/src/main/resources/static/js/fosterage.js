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


    console.log( $("#titleCounter").text())
})

function counterTitle() {

    $("#titleCounter").text(""+$("#newTitle").val().length+"/140");
}
function counterDetail() {
    $("#detailCounter").text(""+$("#newContent").val().length+"/800");
}

$(".grid").imagesLoaded().progress(function () {
    $(".grid").masonry("layout");
});

function filterSubmit(){
    $("#searchForm").submit();
}