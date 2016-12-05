
function validateRegex(elename,regex){
    var obj = document.getElementById(elename);
    var msg = document.getElementById(elename+"Msg");
    if(regex.test(obj.value)){
        return true;
    }else{
        msg.innerHTML = "内容输入格式不正确";
        return false;
    }
}
function validateRegexFocus(elename,regex){
    var obj = document.getElementById(elename);
    var msg = document.getElementById(elename+"Msg");
    if(regex.test(obj.value)){
        return true;
    }else{
        msg.innerHTML = "";
        return false;
    }
}

window.onload = function(){

    document.getElementById("beginIp").addEventListener("blur",validateBeginIp,false);
    document.getElementById("beginIp").addEventListener("focus",validateBeginIpFocus,false);
    document.getElementById("endIp").addEventListener("blur",validateEndIp,false);
    document.getElementById("endIp").addEventListener("focus",validateEndIpFocus,false);
}

function validateBeginIp(){
    return validateRegex("beginIp",/^\d+\.\d+\.\d+\.\d+$/);
}
function validateBeginIpFocus(){
    return validateRegexFocus("beginIp",/^\d+\.\d+\.\d+\.\d+$/);
}

function validateEndIp(){
    return validateRegex("endIp",/^\d+\.\d+\.\d+\.\d+$/);
}
function validateEndIpFocus(){
    return validateRegexFocus("endIp",/^\d+\.\d+\.\d+\.\d+$/);
}

function validateCustomerIp(){
    return  validateBeginIp()&&
        validateEndIp();
}



/*function validateGoodprice(){
    return validateRegex("gooprice",/^\d+(\.\d{1,2})?$/);
}*/
