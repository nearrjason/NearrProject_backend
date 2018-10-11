$(function() {
	$('#sale-amount').on('click', sortBySale);
	$('#pricing').on('click', sortByPrice);
	$('#on-market').on('click', sortByMarket);
});

function sortByOverall() {
	$(this).off("click");
	$('.nav-bar li.selected').removeClass("selected");
	$('.nav-bar li').removeClass("ascend");
	$('.nav-bar li').removeClass("descend");
	$('#sale-amount').off('click').on('click', sortBySale);
	$('#on-market').off("click").on('click', sortByMarket);
	$('.p-img').attr('src', '/images_meitao/icons/sort.svg');
	$('#overall').addClass('selected');
}

function sortBySale() {
	$(this).off("click");
	$('.nav-bar li.selected').removeClass("selected");
	$('.nav-bar li').removeClass("ascend");
	$('.nav-bar li').removeClass("descend");
	$('#on-market').off('click').on('click', sortByMarket);
	$('#overall').off("click").on('click', sortByOverall);
	$('.p-img').attr('src', '/images_meitao/icons/sort.svg');
	$('#sale-amount').addClass('selected');
}

function sortByPrice() {
	$('.nav-bar li.selected').removeClass("selected");
	$('#pricing').addClass('selected');
	if ($('#pricing').hasClass('ascend')) {
		$('#pricing').addClass('descend');
		$('#pricing').removeClass('ascend');
		$('.p-img').attr('src', '/images_meitao/icons/descend.svg');
	} else if ($('#pricing').hasClass('descend')) {
		$('#pricing').addClass('ascend');
		$('#pricing').removeClass('descend');
		$('.p-img').attr('src', '/images_meitao/icons/ascend.svg');
	} else {
		$('#pricing').addClass('ascend');
		$('.p-img').attr('src', '/images_meitao/icons/ascend.svg');
		$('#sale-amount').off('click').on('click', sortBySale);
		$('#on-market').off("click").on('click', sortByMarket);
		$('#overall').off("click").on('click', sortByOverall);
	}
}

function sortByMarket() {
	$(this).off("click");
	$('.nav-bar li.selected').removeClass("selected");
	$('.nav-bar li').removeClass("ascend");
	$('.nav-bar li').removeClass("descend");
	$('#sale-amount').off('click').on('click', sortBySale);
	$('#overall').off("click").on('click', sortByOverall);
	$('.p-img').attr('src', '/images_meitao/icons/sort.svg');
	$('#on-market').addClass('selected');
}

function categoryDrop(el) {
	if ($('.thirdlevel:visible').length == 0) {
		$(el).parent().parent().find('ul').slideDown(120);
		$(el).children().css('transform', 'rotate(180deg)');
	} else {
		$(el).parent().parent().find('ul').slideUp(120);
		$(el).children().css('transform', 'rotate(0deg)');
	}
}

$('.add-to-cart').mouseover(function() {
	$(this).animate({
		deg : 180
	}, {
		step : function(now, fx) {
			$(this).css({
				transform : "rotate(" + now + "deg)"
			});
		}
	});
});

$('.add-to-cart').mouseleave(function() {
	$(this).animate({
		deg : 0
	}, {
		step : function(now, fx) {
			$(this).css({
				transform : "rotate(" + now + "deg)"
			});
		}
	});
});