function showShadow(){
	var h = $(document).height();
	$('#screen').css({ 'height': h });	
	$('#screen').show();
	$('.indexshadow').center();
	$('.indexshadow').fadeIn();
}	
var COMSTATIC = {};
//邮箱正则校验
COMSTATIC.mail_preg = function(mail) {
    var mail_preg =  /^\w+([-+.\']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    var string = $.trim(mail);
    if (mail_preg.test(string)) {
        return true;
    }
    return false;
}
$(document).bind("mouseover", "div.item",function(){
    $(this).addClass("select");
});
$(document).bind("mouseleave", "div.item",function(){
	$(this).removeClass("select");
});


(function($){
	$.fn.lazyload=function(options){
		var settings = {
			threshold:0,
			failurelimit:0,
			event:"scroll",
			effect:"show",
			container:window
		};
		if(options){
			$.extend(settings,options);
		}
	var elements=this;
	
	if("scroll"==settings.event){
		$(settings.container).on("scroll", function(event){
			var counter=0;
			elements.each(function(){
				if($.abovethetop(this,settings)||$.leftofbegin(this,settings))
				{}else if(!$.belowthefold(this,settings)&&!$.rightoffold(this,settings)){
					$(this).trigger("appear");
				}else{
					if(counter++ > settings.failurelimit){
						return false;
					}
				}
			});
			var temp = $.grep(elements,function(element){
				return !element.loaded;
			});
			elements = $(temp);
		});
	}

	this.each(function(){
	
		var self=this;
		
		if(undefined==$(self).attr("data")){
			$(self).attr("data", $(self).attr("src"));
		}if("scroll" != settings.event||undefined == $(self).attr("src")||"" == $(self).attr("src")||settings.placeholder == $(self).attr("src")||($.abovethetop(self,settings)||$.leftofbegin(self,settings)||$.belowthefold(self,settings)||$.rightoffold(self,settings))){
			if(settings.placeholder){
				$(self).attr("src", settings.placeholder);}else{//$(self).removeAttr("src");
			}
			self.loaded=false;
		}else{
			self.loaded=true;
		}
		
		$(self).one("appear", function(){
			if(!this.loaded || 1 == 1){
				$("<img />").on("load", function(){
					$(self).hide().attr("src", $(self).attr("data"))[settings.effect](settings.effectspeed);
					self.loaded=true;
				}).attr("src", $(self).attr("data"));
			};
		});
		
		if("scroll"!=settings.event){
			$(self).on(settings.event, function(event){
			if(!self.loaded){
				$(self).trigger("appear");
			}
		}
		
		);}
	});

		$(settings.container).trigger(settings.event);
		return this;
	};
	
	$.belowthefold=function(element,settings){
		if(settings.container === undefined || settings.container === window){
			var fold = $(window).height() + $(window).scrollTop();
		}else{
			var fold = $(settings.container).offset().top + $(settings.container).height();
		}
		return fold <= $(element).offset().top - settings.threshold;
	};

	$.rightoffold=function(element,settings){
		if(settings.container === undefined || settings.container === window){
			var fold = $(window).width() + $(window).scrollLeft();
		}else{
			var fold = $(settings.container).offset().left + $(settings.container).width();
		}
		return fold <= $(element).offset().left - settings.threshold;
	};

	$.abovethetop=function(element,settings){
		if(settings.container === undefined || settings.container === window){
			var fold = $(window).scrollTop();
		}else{
			var fold = $(settings.container).offset().top;
		}
		return fold >= $(element).offset().top + settings.threshold  + $(element).height();
	};

	$.leftofbegin=function(element,settings){
		if(settings.container === undefined || settings.container === window){
			var fold = $(window).scrollLeft();
		}else{
			var fold = $(settings.container).offset().left;
		}
		return fold >= $(element).offset().left + settings.threshold + $(element).width();
	};

	$.extend(
		$.expr[':'],
		{
			"below-the-fold" : "$.belowthefold(a, {threshold : 0, container: window})",
			"above-the-fold" : "!$.belowthefold(a, {threshold : 0, container: window})",
			"right-of-fold"  : "$.rightoffold(a, {threshold : 0, container: window})",
			"left-of-fold"   : "!$.rightoffold(a, {threshold : 0, container: window})"
		}
	);
	
})(jQuery);


function getCookie (name) {
 var arg = name + "=";
 var alen = arg.length;
 var clen = document.cookie.length;
 var i = 0;
 while (i < clen) {
  var j = i + alen;
  if (document.cookie.substring(i, j) == arg) return getCookieVal (j);
  i = document.cookie.indexOf(" ", i) + 1;
  if (i == 0) break;
 }
 return null;
}
function setCookie(name, value, expires, path, domain, secure)
{
  var today = new Date();
  var expiry = new Date(today.getTime() + 100000 * 24 * 60 * 60 * 1000);
  if(expires==''||expires==null)
  {
 	expires=expiry;
  }
  var curCookie = name + "=" + escape(value) +
      ((expires) ? "; expires=" + expires.toGMTString() : "") +
      ((path) ? "; path=" + path : "") +
      ((domain) ? "; domain=" + domain : "") +
      ((secure) ? "; secure" : "");
  document.cookie = curCookie;
}

(function($){
    $.fn.sfHover = function(options){
        var defaults = {
            hoverDuring: 200,
            outDuring: 200,
            hoverEvent: function(){},
            outEvent: function(){}
        };
        var s = $.extend(defaults,options || {});
        var h, o, that = this;
        return $(this).each(function(){
            $(this).hover(function(){
                clearTimeout(o);
                h = setTimeout(function(){s.hoverEvent.apply(that)}, s.hoverDuring);
            },function(){
                clearTimeout(h);
                o = setTimeout(function(){s.outEvent.apply(that)}, s.outDuring);
            });
        });
    };
	
	function sf_a(obj,f,c,u) {
		$(obj).addClass("curr").siblings().removeClass("curr");
		var i=$(f).index($(f+".curr"));
		var w = u.find(".slideArror").width();
		var $left = i * w + "px";
		c.eq(i).show().siblings().hide();
		u.find(".slideArror").animate({"left":$left},300);	
		
	}
	var fp=".panel .subTab li";
	$(fp).each(function(){
		$(this).sfHover({
			hoverDuring: 300,
			hoverEvent: function(){
				var This =$(this),
					c=$(".panel .mList .sfList"),
					t=$(".panel");
					sf_a(This,fp,c,t);
				i = $(this).index();
				if(i == 4 && !c.eq(i).attr('isGet')){
					c.eq(i).html("loading....");
					$.post("/product/like",{},function(str){
						c.eq(i).html(str);
						c.eq(i).attr('isGet',1)
					});
				}else{
					c.eq(i).find("img.lazy_load").trigger("sporty");
				}
			}
		});	
	});
	var fc=".countDown .subTab li";
	$(fc).each(function(){
		$(this).sfHover({
			hoverDuring: 300,
			hoverEvent: function(){
				var This =$(this),
					c=$(".countDown .mList .sfList"),
					t=$(".countDown");
					sf_a(This,fc,c,t);
					
			}    
		});	
	});
	var ff=".fresh .floorTab li";
	$(ff).each(function(){
		$(this).sfHover({
			hoverDuring: 300,
			hoverEvent: function(){
				var This =$(this),
					c=$(".fresh .subCont > div"),
					t=$(".fresh");
					sf_a(This,ff,c,t);
				i = $(this).index();
				if(i>0){
					c.eq(i).find("img.lazy_load").trigger("sporty");
				}
			}    
		});	
	});
	var fdr=".drinks .floorTab li";
	$(fdr).each(function(){
		$(this).sfHover({
			hoverDuring: 300,
			hoverEvent: function(){
				var This =$(this),
					c=$(".drinks .subCont > div"),
					t=$(".drinks");
					sf_a(This,fdr,c,t);
				i = $(this).index();
				if(i>0){
					c.eq(i).find("img.lazy_load").trigger("sporty");
				}
			}
		});	
	});
	var fd=".food .floorTab li";
	$(fd).each(function(){
		$(this).sfHover({
			hoverDuring: 300,
			hoverEvent: function(){
				var This =$(this),
					c=$(".food .subCont > div"),
					t=$(".food");
					sf_a(This,fd,c,t);
				i = $(this).index();
				if(i>0){
					c.eq(i).find("img.lazy_load").trigger("sporty");
				}
			}    
		});	
	});
	var fo=".oil .floorTab li";
	$(fo).each(function(){
		hoverDuring: 300,
		$(this).sfHover({
			hoverEvent: function(){
				var This =$(this),
					c=$(".oil .subCont > div"),
					t=$(".oil");
					sf_a(This,fo,c,t);
				i = $(this).index();
				if(i>0){
					c.eq(i).find("img.lazy_load").trigger("sporty");
				}
			}    
		});	
	});
	var ft=".tea .floorTab li";
	$(ft).each(function(){
		$(this).sfHover({
			hoverDuring: 300,
			hoverEvent: function(){
				var This =$(this),
					c=$(".tea .subCont > div"),
					t=$(".tea");
					sf_a(This,ft,c,t);
				i = $(this).index();
				if(i>0){
					c.eq(i).find("img.lazy_load").trigger("sporty");
				}	
			}    
		});	
	});
	var fb=".baby .floorTab li";
	$(fb).each(function(){
		$(this).sfHover({
			hoverDuring: 300,
			hoverEvent: function(){
				var This =$(this),
					c=$(".baby .subCont > div"),
					t=$(".baby");
					sf_a(This,fb,c,t);
				i = $(this).index();
				if(i>0){
					c.eq(i).find("img.lazy_load").trigger("sporty");
				}
			}    
		});	
	});
	var fh=".health .floorTab li";
	$(fh).each(function(){
		$(this).sfHover({
			hoverDuring: 300,
			hoverEvent: function(){
				var This =$(this),
					c=$(".health .subCont > div"),
					t=$(".health");
					sf_a(This,fh,c,t);
				i = $(this).index();
				if(i>0){
					c.eq(i).find("img.lazy_load").trigger("sporty");
				}	
			}    
		});	
	});
	var fts=".tools .floorTab li";
	$(fts).each(function(){
		$(this).sfHover({
			hoverDuring: 300,
			hoverEvent: function(){
				var This =$(this),
					c=$(".tools .subCont > div"),
					t=$(".tools");
					sf_a(This,fts,c,t);
				i = $(this).index();
				if(i>0){
					c.eq(i).find("img.lazy_load").trigger("sporty");
				}	
			}    
		});	
	});
})(jQuery);
$(function(){
	
	$(".pList li").each(function(){
		$(this).hover(function(){
			$(this).find(".gBtn").animate({top:"136px"},300);
		},
		function(){
			$(this).find(".gBtn").animate({top:"160px"},300);
		});	
	});

	$(".sfList li").each(function(){
		$(this).sfHover({
			hoverEvent: function(){
				if ($(this).find(".gWindow").length > 0){
					$(this).find(".gBtn").hide();
				}else{
					$(this).find(".gBtn").show();
					$(this).find(".gBtn").animate({top:"150px"},200);
				}	
			},
			outEvent: function(){
			    $(this).find(".gBtn").animate({top:"184px"},300);
			}   
		});	
	});
	
	$(document).on("mouseenter", ".rHot dd",function(){
    	if ($(this).find(".gWindow").length > 0){
			$(this).find(".hBtn").hide();
		}else{
			$(this).find(".hBtn").show();
		}
		$(this).find("a.pname").addClass("ddHover");
	});
	$(document).on("mouseleave", ".rHot dd",function(){
		$(this).find(".hBtn").hide();
		$(this).find("a.pname").removeClass("ddHover");
	});
	
	SFbest.Slide.init();
	$(document).on("mouseenter", ".ajaxdata_index li",function(){
    	if ($(this).find(".gWindow").length > 0){
			$(this).find(".gBtn").hide();
		}else{
			$(this).find(".gBtn").show();
			$(this).find(".gBtn").animate({top:"150px"},200);
		}	
	});
	$(document).on("mouseleave", ".ajaxdata_index li",function(){
		$(this).find(".gBtn").animate({top:"184px"},300);
	});
})

var SFbest = {};
(function (d) {
	SFbest.Slide = new function () {
		this.init = function () {
			Q();
			S();
			T();
		};
		function Q() {		
			var f = $("#lunbo_1");
			var li = $("ul>li", "#slide_show");
			if (f.length > 0 && li.length > 1) {
				setTimeout(function () {
					$("#lunboNum").show();
					m("#slide_show");	
					var liwidth=0;	
					$("#lunboNum").find("li").each(function(){
						liwidth+=$(this).outerWidth(true);	
					})
					$("#lunboNum").css({"margin-left":-liwidth/2});		
				}, 1000)
			}
			
			e();
			function e() {
				var p = p || {};
				p.u_hover = function (r) {
					var q = $(r);
					q.hover(function () {
						$(this).removeClass("hovers").siblings().addClass("hovers")
					}, function () {
						$(this).siblings().removeClass("hovers")
					})
				};
				p.initFun = function () {
					p.u_hover("#index_slide .mini_pic a")
				};
				p.initFun()
			}
			function m(q) {
				p();
				function p() {
					var t = $("ol", "#slide_show").width(),
					k = $(window).width(),
					B = $("#slide_show>ul>li"),
					A = $("#index_slide>ol"),
					D = $("#index_slide>ol>li"),
					F = $("#indexbg>dl"),//
					x = D.length,
					z;
					console.log(t);
					//alert(k);
					var r = D.first();
					D.last().clone().prependTo(A);
					A.width(t * (x + 2) + 100).css("left", "-" + t + "px");
					$("#slide_show").hover(function () {
						$(this).children("a").show();
						clearInterval(z)
					}, function () {
						$(this).children("a").hide();
						clearInterval(z);
						z = setInterval(function () {
								s(y())
							}, 5000)
					}).trigger("mouseout");
					B.hover(function () {
						var E = B.index(this);
						$(this).addClass("cur").siblings().removeClass("cur");
						$("ol", "#index_slide").stop(true).animate({
							left : "-" + (E + 1) * t + "px"
						}, 360);
						$("dl", "#indexbg").stop(true).animate({
							left : "-" + E * k + "px"
						}, 0);//New
					});
					$(".show_next,.show_pre", "#slide_show").click(function () {
						var E = y();
						if ($("ol", "#index_slide").is(":animated")) {
							return
						}
						if ($(this).hasClass("show_pre")) {
							//alert(x);
							$("dl", "#indexbg").animate({
							left : "-=" +E*k + "px"
							}, 360,function () {
									if (E-1 == 0) {
										$("dl", "#indexbg").css("left", "-" + 0 + "px");
									}
									else if (E-1 > 0) {
										$("dl", "#indexbg").css("left", "-" + (E-1) * k + "px");
									}else{
										$("dl", "#indexbg").css("left", "-" + k * (x-1) + "px");
									}
							}
							)//new
							$("ol", "#index_slide").animate({
								left : "+=" + t + "px"
							}, 360, function () {
								if (E > 0) {
									B.eq(E - 1).addClass("cur").siblings().removeClass("cur");
								} else {
									if (E == 0) {
										$("ol", "#index_slide").css("left", "-" + t * (x) + "px");
										B.eq(-1).addClass("cur").siblings().removeClass("cur");
									}
								}
							})
						} else {
							s(E)
						}
						return false
					});
					function s(E) {
						if (E == x - 1) {
							r.addClass("cur").css("left", t * x)
						}
						$("dl", "#indexbg").stop(true, true).animate({
							left : "-=" + k + "px"
						}, 360)
						$("ol", "#index_slide").stop(true, true).animate({
							left : "-=" + t + "px"
						}, 360, function () {
							if (E < x - 1) {
								B.eq(E + 1).addClass("cur").siblings().removeClass("cur");
							} else {
								if (E == x - 1) {
									r.removeClass("cur").css("left", -t);
									$("dl", "#indexbg").css("left", "-" + 0 + "px");//New
									$("ol", "#index_slide").css("left", "-" + t + "px");
									B.eq(0).addClass("cur").siblings().removeClass("cur")
								}
							}
						})
					}
					function y() {
						return $("ul>li", "#slide_show").index($("ul>li.cur", "#slide_show"))
					}

				}
			}
		}
		function S() {
            $(".slide").each(function() {
                var f = $(this);
                var w = f.width();
                var l = f.find("ul li").length;
                var i = 0;
                var b = "<div class='slideControls'>";
                if (l > 1) {
                    var b = "<div class='slideControls'>";
                    for (var i = 0; i < l; i++) {
                        b += "<span>" + (i + 1) + "</span>";
                    }
                    b += "</div>";
                    f.append(b);
					f.hover(function(){f.children("a").show();},function(){f.children("a").hide();});
                }
                f.find(".slideControls span").removeClass("cur").eq(0).addClass("cur");
                f.find(".slideControls span").mouseenter(function() {
                    i = f.find(".slideControls span").index(this);
                    if (i == l) {g();i = 0;} else {h();}
                });
                f.find("ul").css("width", w * (l + 1));
				f.find(".btn_next").click(function(){
					i = f.find(".slideControls span.cur").index();
					i++;
					if (i == l) {g();} else {h();}
				});
				f.find(".btn_prev").click(function(){
					i = f.find(".slideControls span.cur").index();
					if (i == 0) {
                        f.find("ul").prepend(f.find("ul li:last").clone());
						f.find("ul").css("left",-w);
                        f.find("ul").stop(true, false).animate({"left": 0}, 360, function() {
                            f.find("ul").css("left", -(l-1)*w);
							f.find("ul li:first").remove();
                        });
                        f.find(".slideControls span").removeClass("cur").eq(l-1).addClass("cur");
                    } else {i--;h();}
				});
				function g(){
				    f.find("ul").append(f.find("ul li:first").clone());
					var nowLeft = -l * w;
					f.find("ul").stop(true, false).animate({"left": nowLeft}, 360, function() {
						f.find("ul").css("left", "0");
						f.find("ul li:last").remove();
					});
					f.find(".slideControls span").removeClass("cur").eq(0).addClass("cur");
				}
				function h(){
				    n();
                    f.find(".slideControls span").removeClass("cur").eq(i).addClass("cur"); 
				}
				function n(){
				    var nowLeft = -i * w;
					f.find("ul").stop(true, false).animate({"left": nowLeft}, 360);
				}
            })
        }
		function T(){
			var i=0;
			var li = $(".lpscroll li");
			var n=li.length-1;
			var speed = 300;
			if(n > 0){
			  li.not(":first").css({left:"218px"});
			  li.eq(n).css({left:"-218px"});
			  $(".lpleftbtn").show();
			  $(".lprightbtn").show();
			}
			$(".lpleftbtn").click(function(){
				if (!li.is(":animated")) {
					if (i>=n){
						i=0;li.eq(n).animate({left:"-218px"},speed);
						li.eq(i).animate({left:"0px"},speed);
					}else{
						i++;
						li.eq(i-1).animate({left:"-218px"},speed);
						li.eq(i).animate({left:"0px"},speed);
					}
					li.not("eq(i)").css({left:"218px"});
					$("i").text(i+1);
				}
			});
			$(".lprightbtn").click(function(){
				if (!li.is(":animated")) {
					if (i<=0){
						i=n;
						li.eq(0).animate({left:"218px"},speed);
						li.eq(n).animate({left:"0px"},speed);
					}else{
						i--;
						li.eq(i+1).animate({left:"218px"},speed);
						li.eq(i).animate({left:"0px"},speed);
					}
					li.not("eq(i)").css({left:"-218px"});
					$("i").text(i+1);
				}
			});	
		}
	};
})(jQuery);