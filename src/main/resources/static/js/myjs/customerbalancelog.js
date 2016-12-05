
function isEmpty(elename){
    var obj = document.getElementById(elename);
    var msg = document.getElementById(elename+"Msg");
    if(obj.value!=""){
        return true;
    }else{
        msg.innerHTML = "内容输入不能为空";
        return false;
    }
}
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

function isEmptyFocus(elename){
    var obj = document.getElementById(elename);
    var msg = document.getElementById(elename+"Msg");
    if(obj.value!=""){
        return true;
    }else{
        msg.innerHTML = "";
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

    document.getElementById("authId").addEventListener("blur",validateAuthId,false);
    document.getElementById("amount").addEventListener("blur",validateAmount,false);
    document.getElementById("authId").addEventListener("focus",validateAuthIdFocus,false);
    document.getElementById("amount").addEventListener("focus",validateAmountFocus,false);
    document.getElementById("reasonId").addEventListener("blur",validateReasonId,false);
}

function validateAuthId(){
    return isEmpty("authId");
}
function validateAuthIdFocus(){
    return isEmptyFocus("authId");
}
function validateAmount(){
    return validateRegex("amount",/[1-9][0-9]*$/);
}
function validateAmountFocus(){
    return validateRegexFocus("amount",/^[1-9][0-9]*$/);
}

function validateReasonId(){
    return isEmpty("reasonId");
}

function validateCustomerBalanceLog(){
    return  validateAuthId()&&
        validateAmount()&&
        validateReasonId();
}



/*function validateGoodprice(){
    return validateRegex("gooprice",/^\d+(\.\d{1,2})?$/);
}*/
