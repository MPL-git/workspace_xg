/**********************************************************
 *          表单验证脚本
 *                2014-05-22
 *************************************************************/

/**
 * 电子邮件地址验证
 */
function validEmail(str){
	var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if(reg.test(str)){
		return true;
	}else{
		return false;
	}
}

/**
 * 电话号码和手机号码验证
 * @param tel
 * @returns {Boolean}
 */
function checkTel(tel){
	var mobile = /^1[3-8]+\d{9}/,
	    phone = /^0\d{2,3}-?\d{7,8}$/; 
	return mobile.test(tel) || phone.test(tel); 
}

/**
 * 身份证号码验证
 * @param num
 * @returns {Boolean}
 */
function validCardNo(num) 
{   
        num = num.toUpperCase();  
        //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。   
        if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num)))   
        { 
            //alert('输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X。'); 
            return false; 
        } 
        //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。 
        //下面分别分析出生日期和校验位 
        var len, re; 
        len = num.length; 
        if (len == 15) 
        { 
            re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/); 
            var arrSplit = num.match(re); 

            //检查生日日期是否正确 
            var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]); 
            var bGoodDay; 
            bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4])); 
            if (!bGoodDay) 
            { 
                //alert('输入的身份证号里出生日期不对！');   
                return false; 
            } 
            else 
            { 
                    //将15位身份证转成18位 
                    //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。 
                    var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); 
                    var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); 
                    var nTemp = 0, i;   
                    num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6); 
                    for(i = 0; i < 17; i ++) 
                    { 
                        nTemp += num.substr(i, 1) * arrInt[i]; 
                    } 
                    num += arrCh[nTemp % 11];   
                    return true;   
            }   
        } 
        if (len == 18) 
        { 
            re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/); 
            var arrSplit = num.match(re); 

            //检查生日日期是否正确 
            var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]); 
            var bGoodDay; 
            bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4])); 
            if (!bGoodDay) 
            { 
                //alert(dtmBirth.getYear()); 
                //alert(arrSplit[2]); 
                //alert('输入的身份证号里出生日期不对！'); 
                return false; 
            } 
        else 
        { 
            //检验18位身份证的校验码是否正确。 
            //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。 
            var valnum; 
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); 
            var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); 
            var nTemp = 0, i; 
            for(i = 0; i < 17; i ++) 
            { 
                nTemp += num.substr(i, 1) * arrInt[i]; 
            } 
            valnum = arrCh[nTemp % 11]; 
            if (valnum != num.substr(17, 1)) 
            { 
                //alert('18位身份证的校验码不正确！应该为：' + valnum); 
                return false; 
            } 
            return true; 
        } 
        } 
        return false; 
} 
/**
 * 获取字符串长度
 * 中文长度2，英文为1
 * @param str
 * @returns {Number}
 */
function getStrLength(str) {
    ///<summary>获得字符串实际长度，中文2，英文1</summary>
    ///<param name="str">要获得长度的字符串</param>
    var realLength = 0, len = str.length, charCode = -1;
    for (var i = 0; i < len; i++) {
        charCode = str.charCodeAt(i);
        if (charCode >= 0 && charCode <= 128) realLength += 1;
        else realLength += 2;
    }
    return realLength;
};

/**
 * 获取包含英文字符串的长度
 * @param str
 * @returns {Number}
 */
function getEnStrLength(str) {
    var realLength = 0, 
        len = str.length, 
        charCode = -1;
    for (var i = 0; i < len; i++) {
        charCode = str.charCodeAt(i);
        if (charCode >= 0 && charCode <= 128) realLength += 1;
        else realLength += 0;
    }
    return realLength;
};

