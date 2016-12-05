

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

    document.getElementById("loginName").addEventListener("blur",validateLoginNameBlue,false);
    document.getElementById("loginName").addEventListener("focus",validateLoginNameFocus,false);
    document.getElementById("password").addEventListener("blur",validatePasswordBlue,false);
    document.getElementById("password").addEventListener("focus",validatePasswordFocus,false);
    document.getElementById("newPassword").addEventListener("blur",validatenewPasswordBlue,false);
    document.getElementById("newPassword").addEventListener("focus",validatenewPasswordFocus,false);
    document.getElementById("rppassword").addEventListener("blur",validateRpPasswordBlue,false);
    document.getElementById("rppassword").addEventListener("focus",validateRpPasswordFocus,false);

}


function validateLoginNameBlue(){
    return isEmptyBlue("loginName");
}
function validateLoginNameFocus(){
    return isEmptyFocus("loginName");
}
function validatePasswordBlue(){
    return isEmptyBlue("password");
}
function validatePasswordFocus(){
    return isEmptyFocus("password");
}
function validatenewPasswordBlue(){
    return isEmptyBlue("newPassword");
}
function validatenewPasswordFocus(){
    return isEmptyFocus("newPassword");
}
function validateRpPasswordBlue(){
    return isEmptyBlue("rppassword");
}
function validateRpPasswordFocus(){
    return isEmptyFocus("rppassword");
}

function validateUpdatePassword(){
    return  validateLoginNameBlue()&&
        validatePasswordBlue()&&
        validatenewPasswordBlue()&&
        validateRpPasswordBlue();

}









/*function validateGoodprice(){
    return validateRegex("gooprice",/^\d+(\.\d{1,2})?$/);
}*/
