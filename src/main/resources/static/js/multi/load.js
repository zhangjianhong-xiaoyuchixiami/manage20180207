

function loadJs(jsurl){
    var myScript= document.createElement("script");
    myScript.type = "text/javascript";
    myScript.src=jsurl;
    document.body.appendChild(myScript);
}