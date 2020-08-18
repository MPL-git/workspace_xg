/**
 * 工具js
 */
/**
 * html特殊字符转义
 * 
 * @param str
 * @returns
 */
function htmlEncode(str) {
	if(str == ""||str == null||str == undefined)return "";
	var div = document.createElement("div");
	div.appendChild(document.createTextNode(str));
	return div.innerHTML.replace(/"/g, "&quot;").replace(/'/g, "&#39;");
}

/**
 * 
 * @param str
 * @returns
 */
function htmlDecode(str, isText) {
	if(str == ""||str == null||str == undefined)return "";
	var div = document.createElement("div");
	if(isText){ //兼容纯文本换行
		div = document.createElement("pre");
	}
	div.innerHTML = str;
	return div.innerText || div.textContent;
}

/**
 * 转换特殊字符 
 */
function htmlEntities(text){
	if(!text){
		return "";
	}
	text = text.replace(/&/g, "&amp;");
	text = text.replace(/\"/g,"&quot;");
	text = text.replace(/</g, "&lt;");
	text = text.replace(/>/g, "&gt;");
	return text;
}

/**
 * 删除html标签
 * 
 * @param str
 * @returns
 */
/**
 * 检测是否含有windows非法文件名称
 */
function checkSpecial(str){
	var re=/[*?:|\\\/<>"]/;
    return re.test(str);
}
function delHtmlTag(str) {
	return str.replace(/<[^>]+>/g, "");// 去掉所有的html标记
}

function checkUrl(url) {
	var re = /^((requireOpenId:)?((http|https|ftp):\/\/)?([\w\u4e00-\u9fa5\-]+\.)+[\w\u4e00-\u9fa5\-]+(:\d+)?(\/[\w\u4e00-\u9fa5\-\.\/?\@\%\!\&=\+\~\:\#\;\,]*)?)$/ig;
	return re.test(url);
}

function checkPhone(num){
	return checkMobile(num) || checkTelephone(num);
}

function checkMobile(num){
	var re = /^1[3,4,5,7,8]\d{9}$/;
	return re.test(num);
}

function checkTelephone(num){
	var phoneReg = /(^\+86\.\d{3,5}\d{6,8}$)|(^\d{3}((\d-)|(-\d)|\d|-)\d{3}(\d|-|)\d{3}(\d|)$)/;
	return phoneReg.test(num);
}

function checkPHS(num){
	var re = /^0[0-9]{9,11}$/;
	return re.test(num);
}

function checkEmail(email){
	var re = /^[&~#$*%\u4e00-\u9fa5_0-9a-z\-\.\/\\]+@([\u4e00-\u9fa5-a-z0-9]+\.){1,5}[\u4e00-\u9fa5a-z]+$/i;
	return re.test(email);
}

function checkDomain(domain){
	var re = /^([\u4e00-\u9fa5-a-z0-9]+\.){1,5}[\u4e00-\u9fa5a-z]+$/i;
	return re.test(domain);
}

function checkNumber(number){
	var re = /^[1-9]\d*$/;
	return number == 0 || re.test(number);
}

function checkDate(dateStr){
	var re = /^\d{4}(-)\d{2}(-)\d{2}$/;
	return re.test(dateStr);
}

function checkDateTime(dateTimestr){
	var re = /^\d{4}(-)\d{2}(-)\d{2}( )\d{2}:\d{2}:\d{2}$/;
	return re.test(dateTimestr);
}

function checkMoney(money){
	var re = /^(0|[1-9]\d*)(\.\d{1,2})?$/;
	return re.test(money);
}
//校验只包含数字和字母
function checkOnlyNumAndLetter(strVal){
	var re = /^[0-9a-zA-Z]*$/g;
	return re.test(strVal);
}

/**
 * 浏览器辅助函数
 */
var BrowserHelper = {};
(function(){
	var ua = navigator.userAgent.toLowerCase();
	var check = function(r){
		return r.test(ua);
	};
	(function(browser){
		browser.isOpera = check(/opera/);
		browser.isIE = !browser.isOpera && check(/(msie\s|trident.*rv:)([\w.]+)/);
		browser.isIE6 = browser.isIE && check(/msie 6/);
		browser.isIE7 = browser.isIE && check(/msie 7/);
		browser.isIE8 = browser.isIE && check(/msie 8/);
		browser.isIE9 = browser.isIE && check(/msie 9/);
		browser.isIE11 = browser.isIE && check(/Trident/i);
		browser.isChrome = check(/chrome/);
		browser.isSafari = check(/safari/);
		browser.isUcbrowser = check(/ucbrowser/);
		browser.isIE7Mode = browser.isIE7 || (browser.isIE &&  document.documentMode && document.documentMode == 7);
	})(BrowserHelper);
})();

/***jquery 扩展****/
if (window.jQuery || window.Zepto) {
    (function ($) {
        'use strict';


		/**
		 * 判断是否支持oninput
		 */
		var input = document.createElement("input");
		var supportInput = "oninput" in input;


        /**
		 * tip 绑定提示信息 | 鼠标放上去显示提示信息
		 * @param options:{
		 * 		message : "<strong>商品浏览数：</strong><br />某时间段内……",// 可使用html标签,缺失将读取title
		 * 		timeout : 300, 	 //鼠标离开几毫秒后隐藏
		 *		position : "left", //默认居中，可配置值left 显示左下，right 显示右下
		 *		arrowLeft : 100, //选填 小箭头定位
		 *		offsetTop : -6,  //选填 垂直偏移量 
		 *		offsetLeft : 0   //选填 水平偏移量
		 * }
		 * @author rubekid
		 */
        $.fn.tip = function (options) {
        	options = options || {};
        	var timeout = options.timeout || 300; //鼠标离开几毫秒后隐藏
        	var arrowLeft = options.arrowLeft; //小箭头定位left
        	var offsetTop = options.offsetTop || 0; //垂直偏移量
        	var offsetLeft = options.offsetLeft ||0; //水平偏移量
        	
        	var prompt =  function(){
        		var html = '<ul class="order_pop_1" style="position:absolute;z-index:999;">'+
			                '<li>'+
			                  '<p class="prompt_note_content">'+
			                  '</p>'+
			                '</li>'+
			                '<i class="open_arrow" style="top:-12px;"></i>'+
			              '</ul>';
			              
			    var _prompt = $(html);
			    var _content = _prompt.find("p.prompt_note_content");
			    var _arrow =  _prompt.find("i.open_arrow");
			    _prompt.hide();
			    _prompt.appendTo(document.body);

			    if(options.style){
			    	_prompt.css(options.style);
			    }
			    
			    _prompt.bind({
			     	mouseover:function(){
			     		$(this).trigger("_show");
			     	},
			     	mouseout:function(){
			     		$(this).trigger("_hide");
			     	},
			     	_show:function(){
			     		if(this._timeout){
			     			clearTimeout(this._timeout);
			     			this._timeout = null;
			     		}
			     		$(this).stop();
			     		$(this).css({opacity:1});
			     		$(this).show();
			     	},
			     	_hide:function(){
			     		this._timeout = setTimeout(function(){
			    			_prompt.fadeOut(function(){
			    				_content.empty();
			    			});
			     		}, timeout);
			     	}
			    });
			    return {
			    	show:function(content, target){
			    		if(!arrowLeft){
			    			arrowLeft =  (_prompt.outerWidth(true) - _arrow.outerWidth()) / 2; 
			    		}

			    		var top = target.offset().top + target.outerHeight(true) + _arrow.height() + offsetTop;
			    		var style = {top:top};
			    		var position = options.position;
			    		var diff = (target.outerWidth() - _arrow.outerWidth()) / 2;
			    		
			    		if(position == "left"){//显示左下方
			    			style.left = target.offset().left + target.outerWidth(true) - _prompt.outerWidth(true) + offsetLeft;
			    			arrowLeft =  _prompt.outerWidth() - _arrow.outerWidth()  - target.css("marginRight").replace("px", "") - diff; 
			    		}
			    		else if(position == "right"){//显示右下方
			    			style.left = target.offset().left + offsetLeft;
			    			arrowLeft =  diff + target.css("marginLeft").replace("px", "");
			    			if(arrowLeft < 0){
			    				arrowLeft = 0;
			    			}
			    		}
			    		else{
			    			style.left = target.offset().left - arrowLeft + diff + offsetLeft;
			    		}
			    		
			    		_content.html(content);
			    		_prompt.trigger("_show");
			    		_prompt.css(style);
			    		_arrow.css({
			    			left:arrowLeft+"px"
			    		});
			    	},
			    	hide:function(){
			    		_prompt.trigger("_hide");
			    	}
			    };
        	}();
        	
            return this.each(function(){
            	this._message  = options.message || this.title || $(this).data("tip") || $(this).data("note") || this._message;
            	this.title = "";
            	$(this).unbind("mouseover").unbind("mouseout");
	    		$(this).bind({
	    			mouseover:function(){
	    				var note = this._message;
	        			if(note){
	        				prompt.show(note, $(this));
	        			}
	    			},
	    			mouseout:function(){
	    				prompt.hide();
	    			}
	    		});
            });
        };
        
        /**
         * 恢复默认值
         * @author rubekid
         */
        $.fn.recovery = function(){
        	return this.each(function(){
        		var defaultValue = $(this).attr("data-default")||"";
        		if(defaultValue!=""){
        			$(this).unbind("blur");
	        		$(this).bind({
	        			blur:function(){
	            			if($.trim($(this).val()) == ""){
	            				$(this).val(defaultValue);
	            			}
	            		}
	        		});
        		}
        		if($.trim($(this).val()) == ""){
        			$(this).val(defaultValue);
        		}
        	});
        };
        
        /**
         * 绑定input事件
         */
        $.fn.bindInput = function(func){
        	return this.bind({
        		keyup:func,
        		paste:function(){
        			var _this = this;
        			setTimeout(function(){
        				$(_this).trigger("_inputEvent");
        			},10);
        		},
        		_inputEvent:func
        	})
        };

		$.fn.bindInputEvent = function(){
			var keyEvent = supportInput ? "input":"keyup";
			this.bind("paste", function(e){
				var _this = this;
				setTimeout(function(){
					$(_this).trigger("_input");
				},10);
			});
			if(!supportInput){
				this.bind("keydown", function(e){
					var _this = this;
					setTimeout(function(){
						$(_this).trigger("_input");
					},0);
				});
			}
			this.bind(keyEvent, function(e){
				$(this).trigger("_input");
				
			});
			return this;
		};

		$.fn.numberFormat = function(){
			this.bindInputEvent();
			return this.bind({
				_input:function(){
					var number = $.trim($(this).val());
					if($(this).attr("zero")=="false"||number != '0'){
						number = number.replace(/[^\d]/g, "").replace(/^0+/g, '');
					}
					if($(this).attr("maxnumber")){
						var maxnumber = parseInt($(this).attr("maxnumber"));
						while(number > maxnumber){
							number = Math.floor( number / 10 );
						}
					}
					
					if(number != $(this).val() || number==''){
						$(this).val(number);
					}

				}
			});
		};

		$.fn.moneyFormat = function(){
			this.bindInputEvent();
			return this.bind({
				_input:function(){
					var number = $.trim($(this).val());
					var tmp = number;
					if(number != '0' && number !=''){
						number = number.replace(/[^\d\.]/g, "").replace(/^\./g,'');
						var arr = number.split('.', 2);
						if(arr[0]!=''){
							if(parseInt(arr[0]) == 0)
								arr[0] = 0;
							else
								arr[0] = arr[0].replace(/\b(0+)/gi,"");
						}
						if(arr.length == 2){
							var decimalDigit = $(this).attr("decimal-limit");
							if(decimalDigit == null || decimalDigit.length == 0)
								arr[1] = arr[1].substr(0, 2);
							else{
								arr[1] = arr[1].substr(0, decimalDigit);
							}
						}
						number = arr.join('.');
					}
					if($(this).attr("maxnumber")){
						var maxnumber = $(this).attr("maxnumber") * 1;
						while(number > maxnumber){
							number = Math.floor( number / 10 );
						}
					}

					if(number != tmp || number==''){
						$(this).val(number);
					}
				}
			});
		};
		
		$.fn.integerFormat = function(){
			this.bindInputEvent();
			return this.bind({
				_input:function(){
	    			var number = $.trim($(this).val());
	    			var t = number.charAt(0); 
	    			var tmp = number;
	    			if($(this).attr("zero")=="false"||number != '0'){
	    				number = number.replace(/[^\d]/g, "").replace(/^0/g, '');
	    			}
	    			if($(this).attr("maxnumber")){
	    				var maxnumber = parseInt($(this).attr("maxnumber"));
	    				while(number > maxnumber){
	    					number = Math.floor( number / 10 );
	    				}
	    			}
	    			//如果第一位是负号，则允许添加   如果不允许添加负号 可以把这块注释掉
	    			if (t == '-') {
	    				number = '-' + number;
	    	        }
	    			if(number != tmp){
	    				$(this).val(number);
	    			}
	    		}
			});
		};
		
		$.fn.negativeMoneyFormat = function(){
			this.bindInputEvent();
			return this.bind({
				_input:function(){
	    			var number = $.trim($(this).val());
					var tmp = number;
					if(number != '0' && number !=''){
						var inputValue = number.charAt(0);
						number = number.replace(/[^\d\.]/g, "").replace(/^\./g,'');
						if(inputValue == "-"){
							number = "-" + number;
						}
						var arr = number.split('.', 2);
						if(arr[0]!=''){
							if(parseInt(arr[0]) == 0) {
								arr[0] = 0;
							} else {
								arr[0] = arr[0].replace(/\b(0+)/gi,"");
							}
						}
						if(arr.length == 2){
							var decimalDigit = $(this).attr("decimal-limit");
							if(decimalDigit == null || decimalDigit.length == 0)
								arr[1] = arr[1].substr(0, 2);
							else{
								arr[1] = arr[1].substr(0, decimalDigit);
							}
						}
						number = arr.join('.');
					}
					if($(this).attr("maxnumber")){
						var maxnumber = $(this).attr("maxnumber") * 1;
						while(number > maxnumber){
							number = Math.floor( number / 10 );
						}
					}

					if(number != tmp || number==''){
						$(this).val(number);
					}
	    		}
			});
		};
		
		$.fn.maxLength = function(){
			return this.bind({
	    		_input:function(){
	    			if($(this).attr("maxlength") > 0 && $(this).val().length > $(this).attr("maxlength")){
	    				$(this).val($(this).val().substr(0,  $(this).attr("maxlength")));
	    			}
	    		}
	    	});
		};

        
    })(window.jQuery || window.Zepto);
    
    
   
    $(function(){
    	
    	/**
    	 * 初始化拦截器 
    	 */
    	if(typeof window.initInputEventInterceptor == "function"){
    		var result = window.initInputEventInterceptor();
    		if(typeof result != "undefined" && result === false ){
    			return false;
    		}
    	}
    	
    	$("textarea[maxlength],input[data-type='number'],input[data-type='money'],input[data-type='limit'],input[data-type='negativeNumber'],input[data-type='negativeMoney']").bindInputEvent();
    	
    	/**
         * 可恢复输入框 为空失去焦点恢复默认值
         */
    	$("input[data-default]").recovery();
    	
    	/**
         * 对textarea maxlength的兼容
         */
    	$("textarea[maxlength]").maxLength();
    	
    	/**
         * 限制input只能输入数值
         */
    	$("input[data-type='number']").numberFormat();

    	/**
         * 限制input只能输入正负整数值
         */
    	$("input[data-type='negativeNumber']").integerFormat();
    	
    	/**
    	 * 限制input只能输入正负金额
    	 */
    	$("input[data-type='negativeMoney']").negativeMoneyFormat();
    	
    	/**
         * 限制input只能输入金额
         */
    	$("input[data-type='money']").moneyFormat();
    	
    	/**
         * 限制input只能输入大于0的整数 默认值为-1 表示无限
         */
    	$("input[data-type='limit']").each(function(){
    		var defaultValue = $(this).attr("data-default") || "-1";
    		$(this).attr("data-default", defaultValue);
    		$(this).bind({
    			_input:function(){
	    			var number = $.trim($(this).val());
	    			var tmp = number;
	    			if(defaultValue.indexOf(number) == -1){
	    				if(number.indexOf(defaultValue) == 0){
	    					number = defaultValue;
	    				}
	    				else{
	    					if($(this).attr("zero")=="true"&&number == '0'){
	    	    				number = number.replace(/[^\d]/g, "");
	    	    			}else{
	    	    				number = number.replace(/[^\d]/g, "").replace(/^0/g, '');
	    	    			}
	    				}
	    			}
	    			if(number != tmp){
	    				$(this).val(number);
	    			}
	    		}
    		});
    		$(this).recovery();
    	});
    	
    });
}

/**
 * 多行缩略
 * @author rubekid
 * {
 *    maxLine:2, //最多显示行默认为1
 *    ellipsisChar:"..." //省略占位符，建议不设置，与原生一致 
 * }
 */
(function ($) {
	
	var supportLineClamp = function(){
		var div = document.createElement('div');
		var style = computeStyle(div);
		return style!=null && "-webkit-line-clamp" in style;
	}();
	

	function computeStyle(el){
		if(window.getComputedStyle){
		
			return window.getComputedStyle(el,null); 
		}
		return el.currentStyle;
	}

	/**
	 * 设置样式
	 * @param Element el
	 * @param JSON css
	 */
	function setStyle(el, css){
		for(var i in css){
			el.style[i] = css[i];
		}
	}

    function ellipsis($elem, options) {
    	var maxLine = options.maxLine ||1;
    	var lineHeight = parseFloat($elem.css("line-height"));
        var maxHeight = maxLine * lineHeight;
        var content = $elem.attr("data-content") || $elem.text();
        var $temp_elem = $elem.clone(false)
            .css({"visibility": "hidden","overflow":"visible", "height":"auto"})
            .appendTo($elem.parent());
        $temp_elem.text(content);
        var height = $temp_elem.height();
		if(height > maxHeight){
			if(supportLineClamp){
				setStyle($elem.get(0), {
					"text-overflow":"ellipsis",
				    "display":"-webkit-box",
				    "-webkit-line-clamp":maxLine,
				    "-webkit-box-orient":"vertical",
				    "overflow":"hidden"
				});

			}
			else{
			
				var _width = $elem.width();
				var ellipsis_char = options.ellipsisChar;
				var lineCount = 0;
		        var texts = content.split("\n", maxLine);
		        var newTexts = [];
		        for(var i=0; i<texts.length; i++){
		        	var text = texts[i];
		        	$temp_elem.text(text);
		        	height = $temp_elem.height();
		        	var _lineCount = height / lineHeight;
		        	if(lineCount + _lineCount >= maxLine){
		        		text = text + ellipsis_char;
		        		$temp_elem.text(text);
		        		text = text.replace(' ','　'); //for fix white-space bug　
		        		$temp_elem.css({"whiteSpace": "nowrap","width":"auto" });
		        		var max = (maxLine - lineCount) * _width;
			        	var width = $temp_elem.get(0).scrollWidth;
			        	if(width > max){
			    			var stop =  Math.floor(text.length * max / width); // for performance while content exceeding the limit substantially
			    			var temp_str = text.substring(0,stop) + ellipsis_char;
			    			width = $temp_elem.text(temp_str).get(0).scrollWidth;
			    			if(width > max){
			    				while (width > max && stop > 1) {
			    					stop--;
			    					temp_str = text.substring(0, stop) + ellipsis_char;
			    					width = $temp_elem.text(temp_str).width();
			    				}					
			    			}
			    			else if(width < max){
			    				while (width < max && stop < text.length) {
			    					stop++;
			    					temp_str = text.substring(0, stop) + ellipsis_char;
			    					width = $temp_elem.text(temp_str).get(0).scrollWidth;
			    				}
			    				if(width > max){
			    					temp_str = text.substring(0,stop -1)+ellipsis_char;
			    				}
			    			}
			    			
			    			newTexts.push(temp_str.replace('　',' '));
			    		}
			        	else{
			        		newTexts.push(text);
			        	}
			        	break;
		        	}
		        	newTexts.push(text);
		        	lineCount +=_lineCount;
		        	
		        }
		        text = newTexts.join("\n");
		        $temp_elem.text(text).css(
		        	{"whiteSpace": "","width": $elem.width() }
		        );
		        var lastIndex = text.length;
		        var _temp_str = text;
		        height = $temp_elem.height();
	        	while(height > maxHeight){
	        		_temp_str = text.substring(0, --lastIndex) + ellipsis_char;
	        		$temp_elem.text(_temp_str);
	        		height = $temp_elem.height();
	        	}
		        
		        $elem.text(_temp_str);
			}
		}
		$temp_elem.remove();
    }


    var defaults = {
        maxLine: 1,
		ellipsisChar:'...'
    };

    $.fn.ellipsis = function (options) {
        return this.each(function () {
            var $elem = $(this);
            var opts = $.extend({}, defaults, options);
            ellipsis($elem, opts);
        });
    };

    $.fn.ellipsis.options = defaults;
})(jQuery);

function formatMoney(s, n)
{
   n = n > 0 && n <= 20 ? n : 2;
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
   var l = s.split(".")[0].split("").reverse(),
   r = s.split(".")[1];
   t = "";
   for(i = 0; i < l.length; i ++ )
   {
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "" : "");
   }
   return t.split("").reverse().join("") + "." + r;
}



/**
 * 字符串比较  a>b 返回正数 ; a=b 返回0 ; a<b 返回负数
 * 
 * 
 */
function strCompare(a, b) {
	var reg = /[0-9]+/g;
	var lista = a.match(reg);
	var listb = b.match(reg);
	if (!lista || !listb) {
		return a.localeCompare(b);
	}
	for (var i = 0, minLen = Math.min(lista.length, listb.length); i < minLen; i++) {
		//数字所在位置序号
		var indexa = a.indexOf(lista[i]);
		var indexb = b.indexOf(listb[i]);
		//数字前面的前缀
		var prefixa = a.substring(0, indexa);
		var prefixb = a.substring(0, indexb);
		//数字的string
		var stra = lista[i];
		var strb = listb[i];
		//数字的值
		var numa = parseInt(stra);
		var numb = parseInt(strb);
		//如果数字的序号不等或前缀不等，属于前缀不同的情况，直接比较
		if (indexa != indexb || prefixa != prefixb) {
			return a.localeCompare(b);
		} else {
			//数字的string全等
			if (stra === strb) {
				//如果是最后一个数字，比较数字的后缀
				if (i == minLen - 1) {
					return a.substring(indexa).localeCompare(
							b.substring(indexb));
				}
				//如果不是最后一个数字，则循环跳转到下一个数字，并去掉前面相同的部分
				else {
					a = a.substring(indexa + stra.length);
					b = b.substring(indexa + stra.length);
				}
			}
			//如果数字的string不全等，但值相等
			else if (numa == numb) {
				//直接比较数字前缀0的个数，多的更小
				return strb.lastIndexOf(numb + '')
						- stra.lastIndexOf(numa + '');
			} else {
				//如果数字不等，直接比较数字大小
				return numa - numb;
			}
		}
	}
}

/**
 * ajax post 请求
 *
 * @param url
 * @param data
 * @param successCallBack
 * @param errorCallBack
 */
function ajaxPost(url, data, successCallBack, errorCallBack) {
	$.ajax({
		url: url,
		contentType: "application/json",
		type: "post",
		data: JSON.stringify(data),
		success: function (result) {
			if (successCallBack && typeof successCallBack === "function") {
				successCallBack(result);
			}
		},
		beforeSend: function () {
		    show_waitMe();
		},
		complete: function () {
		    hide_waitMe();
		},
		error: function (jqXHR, textStatus, errorThrown) {
			if (errorCallBack && typeof  errorCallBack === "function") {
				errorCallBack(jqXHR);
			} else if (hasText(jqXHR.responseJSON) && hasText(jqXHR.responseJSON.returnMsg)) {
				swalError(jqXHR.responseJSON.returnMsg);
			} else {
				swalError("网络异常,请稍后再试！");
			}
		}
	});

}

//判空方法
function hasText(value) {
	return !(undefined === value || null === value || '' === value);
}