$(document).ready(function () {
    $('.grid').masonry({
        // options
        itemSelector: '.grid-item',
        columnWidth: 160,
        gutter: 10,
    });
})

$(".grid").imagesLoaded().progress(function () {
    $(".grid").masonry("layout");
});
