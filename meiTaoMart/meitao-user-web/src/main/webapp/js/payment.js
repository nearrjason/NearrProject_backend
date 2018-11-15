/*$(function () {
    $(".default-card").click(changeDefaultCard);
    $('.default-card.selected').off("click");
});

function changeDefaultCard() {
    $(this).off("click");
    $('.default-card.selected').html("设为默认");
    $('.default-card.selected').on('click', changeDefaultCard);
    $('.default-card.selected').removeClass("selected");
    $(this).html("已设默认");
    $(this).addClass("selected");
}*/

$(function () {
    $(".default-card").click(changeDefaultPayment);
    $('.default-card.selected').off("click");
    $('.credit-detail').on('mouseover', showPaymentEdits);
    $('.credit-detail').on('mouseleave', hidePaymentEdits);
    showDefaultPayment();
});

function changeDefaultPayment() {
    $(this).off("click");
    $('.default-card.selected').html("设为默认");
    $('.default-card.selected').on('click', changeDefaultPayment);
    $('.default-card.selected').removeClass("selected");
    $(this).html("默认支付");
    $(this).addClass("selected");
    showDefaultPayment();
}

function showDefaultPayment() {
    $('.credit').find('.default-card').hide();
    $('.credit').find('.selected').show();
}

function showPaymentEdits() {
    $(this).find('.default-card').show();
    $(this).find('.edits').show();
}

function hidePaymentEdits() {
    showDefaultPayment();
    $(this).find('.edits').hide();
}