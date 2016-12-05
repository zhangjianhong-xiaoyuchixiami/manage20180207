
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

    document.getElementById("price").addEventListener("blur",validatePrice,false);
    document.getElementById("price").addEventListener("focus",validatePriceFocus,false);

}


function validatePrice(){
    return validateRegex("price",/[1-9][0-9]*$/);
}
function validatePriceFocus(){
    return validateRegexFocus("price",/^[1-9][0-9]*$/);
}

function validateCustomerApi(){
    return  validatePrice();
}



/*function validateGoodprice(){
    return validateRegex("gooprice",/^\d+(\.\d{1,2})?$/);
}*/
