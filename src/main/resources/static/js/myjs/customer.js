

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

    document.getElementById("name").addEventListener("blur",validateName,false);
    document.getElementById("name").addEventListener("focus",validateNameFocus,false);
    document.getElementById("authIdAdd").addEventListener("blur",validateAuthIdAdd,false);
    document.getElementById("authIdAdd").addEventListener("focus",validateAuthIdAddFocus,false);
}

function validateName(){
    return isEmpty("name");
}
function validateNameFocus(){
    return isEmptyFocus("name");
}

function validateAuthIdAdd(){
    return isEmpty("authIdAdd");
}
function validateAuthIdAddFocus(){
    return isEmptyFocus("authIdAdd");
}

function validateCustomer(){
    return  validateName()&&
        validateAuthIdAdd();
}



/*function validateGoodprice(){
    return validateRegex("gooprice",/^\d+(\.\d{1,2})?$/);
}*/
