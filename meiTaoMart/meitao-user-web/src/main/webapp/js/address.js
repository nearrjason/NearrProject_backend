$(function () {
    $(".default-address").click(changeDefaultAddress);
    $('.default-address.selected').off("click");
});

function changeDefaultAddress() {
    $(this).off("click");
    $('.default-address.selected').html("设为默认");
    $('.default-address.selected').on('click', changeDefaultAddress);
    $('.default-address.selected').removeClass("selected");
    $(this).html("已设默认");
    $(this).addClass("selected");
}