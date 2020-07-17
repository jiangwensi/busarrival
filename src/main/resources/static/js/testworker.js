onmessage = event => {
    let request = new XMLHttpRequest();
    request.open("GET", event.data[0]);
    request.send();
    request.onreadystatechange = function(){
        if(request.readyState==XMLHttpRequest.DONE){
            let status = request.status;
            if (status ===0 || status >=200 && status<400) {
                let result = request.responseText;
                postMessage(result);
            }
        }
        close();
    }
}