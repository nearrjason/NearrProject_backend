$(function () {
    $(".default-address").click(changeDefaultAddress);
    $('.default-address.selected').off("click");
    $('.address-detail').on('mouseover', showAddressEdits);
    $('.address-detail').on('mouseleave', hideAddressEdits);
    showDefaultAddress();
});

function changeDefaultAddress() {
    $(this).off("click");
    $('.default-address.selected').html("设为默认");
    $('.default-address.selected').on('click', changeDefaultAddress);
    $('.default-address.selected').removeClass("selected");
    $(this).html("默认地址");
    $(this).addClass("selected");
    showDefaultAddress();
}

function showDefaultAddress() {
    $('.address').find('.default-address').hide();
    $('.address').find('.selected').show();
}

function showAddressEdits() {
    $(this).find('.default-address').show();
    $(this).find('.edits').show();
}

function hideAddressEdits() {
    showDefaultAddress();
    $(this).find('.edits').hide();
}