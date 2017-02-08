
function validateRegexBlue(elename,regex){
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

function isEmptyBlue(elename){
    var obj = document.getElementById(elename);
    var msg = document.getElementById(elename+"Msg");
    if(obj.value!=""){
        return true;
    }else{
        msg.innerHTML = "内容输入不能为空";
        return false;
    }
}
function isEmptyFocus(elename){
    var obj = document.getElementById(elename);
    var msg = document.getElementById(elename+"Msg");
    if(obj.value!=""){
        msg.innerHTML = "";
        return true;
    }else{
        msg.innerHTML = "";
        return false;
    }
}

window.onload = function(){

    document.getElementById("user.name").addEventListener("blur",validateNameBlue,false);
    document.getElementById("user.name").addEventListener("focus",validateNameFocus,false);
    document.getElementById("user.loginName").addEventListener("blur",validateLoginNameBlue,false);
    document.getElementById("user.loginName").addEventListener("focus",validateLoginNameFocus,false);
    document.getElementById("user.password").addEventListener("blur",validatePasswordBlue,false);
    document.getElementById("user.password").addEventListener("focus",validatePasswordFocus,false);
    document.getElementById("user.tel").addEventListener("blur",validateTelBlue,false);
    document.getElementById("user.tel").addEventListener("focus",validateTelFocus,false);

};


function validateNameBlue(){
    return isEmptyBlue("user.name");
}
function validateNameFocus(){
    return isEmptyFocus("user.name");
}
function validateLoginNameBlue(){
    return isEmptyBlue("user.loginName");
}
function validateLoginNameFocus(){
    return isEmptyFocus("user.loginName");
}
function validatePasswordBlue(){
    return isEmptyBlue("user.password");
}
function validatePasswordFocus(){
    return isEmptyFocus("user.password");
}
function validateTelBlue(){
    return validateRegexBlue("user.tel",/(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/);
}
function validateTelFocus(){
    return validateRegexFocus("user.tel",/(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/);
}


function validateAdmin(){
    return  validateNameBlue()&&
        validateLoginNameBlue()&&
    validatePasswordBlue()&&
    validateTelBlue();

}









/*function validateGoodprice(){
    return validateRegex("gooprice",/^\d+(\.\d{1,2})?$/);
}*/
