var pageNum; // 分页数量(一共有几页)
var currentPage; // 当前分页
var showPageNum; // 分页展示数量
var keyword;

function pageClick() {
	currentPage = $(this).html();
	$('#pag').empty();
	searchKeyAndPage(keyword, currentPage);
	//UpdatePages();
}

function previousPage() {
	currentPage -= 1;
	$('#pag').empty();
	searchKeyAndPage(keyword, currentPage);
	//UpdatePages();
}

function nextPage() {
	currentPage = parseInt(currentPage) + 1;
	$('#pag').empty();
	searchKeyAndPage(keyword, currentPage);
	//UpdatePages();
}

function firstPage() {
	currentPage = 1;
	$('#pag').empty();
	searchKeyAndPage(keyword, currentPage);
	//UpdatePages();
}

function lastPage() {
	currentPage = pageNum;
	$('#pag').empty();
	searchKeyAndPage(keyword, currentPage);
	//UpdatePages();
}

function LinkButton() {
	$('#previous').show();
	$('#next').show();
	$('#first').show();
	$('#last').show();
	$('#previous').off('click').on('click', previousPage);
	$('#next').off('click').on('click', nextPage);
	$('#first').off('click').on('click', firstPage);
	$('#last').off('click').on('click', lastPage);
	if (pageNum == 1) {
		$('#previous').hide();
		// $('#first').hide();
		$('#next').hide();
		// $('#last').hide();
	} else if (currentPage == 1) {
		$('#previous').hide();
		// $('#first').hide();
	} else if (currentPage == pageNum) {
		$('#next').hide();
		// $('#last').hide();
	}
}

function UpdatePages() {
	if ((currentPage - 4) <= 0) {
		for (var i = 1; i <= showPageNum; i++) {
			var p = $('<a></a>');
			p.html(i);
			if (i == currentPage) {
				p.addClass('active');
			}
			p.on('click', pageClick);
			$('#pag').append(p)
		}
	} else if ((pageNum - currentPage - 3) <= 0) {
		for (var i = pageNum - showPageNum + 1; i <= pageNum; i++) {
			var p = $('<a></a>');
			p.html(i);
			if (i == currentPage) {
				p.addClass('active');
			}
			p.on('click', pageClick);
			$('#pag').append(p)
		}
	} else {
		for (var i = currentPage - 3; i <= parseInt(currentPage) + 3; i++) {
			var p = $('<a></a>');
			p.html(i);
			if (i == currentPage) {
				p.addClass('active');
			}
			p.on('click', pageClick);
			$('#pag').append(p)
		}
	}
	LinkButton();
}


$(function() {
	var totalPageNumber = $("#totalPageNumber").val();
	console.log(totalPageNumber);
	keyword = $("#currentKeyword").val();
	currentPage = $("#currentPage").val();
	console.log(currentPage);
	pageNum = totalPageNumber;
	showPageNum = Math.min(totalPageNumber, 7);
	UpdatePages();
	$(".total-pages span").html(pageNum);
});