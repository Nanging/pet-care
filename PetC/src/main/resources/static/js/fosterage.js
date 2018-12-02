$(document).ready(function () {
    $('.grid').masonry({
        // options
        itemSelector: '.grid-item',
        columnWidth: 200,
        gutter: 16,
    });
    $('.grid').masonry("layout")
})


function filterSubmit(){
    $("#searchForm").submit();
}