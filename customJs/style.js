function updateBells(friendRequestArrivedCount)
{
    var requestListBellBadge=document.getElementById('requestListBellBadge');
    if(typeof(friendRequestArrivedCount)=="undefined") requestListBellBadge.style.display="none"; 
    var requestCount=requestListBellBadge.innerHTML;
    if(requestCount==0) requestListBellBadge.style.display="none"; 
    requestListBellBadge.innerHTML=friendRequestArrivedCount;
    if(friendRequestArrivedCount==0) requestListBellBadge.style.display="none";      
}
function updater()
{
    $.ajax({
        "url":"/slamBook/updater",
        "type":"POST",
        "dataType":"json",
        "data":"",
        "contentType":"application/json",
        "success":function(responseJson)
        {
            if(responseJson.success=="true")
            {
                updateBells(responseJson.friendRequestArrivedCount);
            }
            else
            {
                alert("responseJson.exception");
            }
        },
        "error":function()
        {
            onError();
        }
        });
}
function initializePage()
{
    var requestListBellBadge=document.getElementById('requestListBellBadge');
    var requestCount=requestListBellBadge.textContent;
    if(requestCount==0)
    {
        requestListBellBadge.style.display="none";
        return;
    }
    requestListBellBadge.style.display="block";   
}
function launch_toast() {
    var x = document.getElementById("toast")
    x.className = "show";
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 5000);
    initializePage();
}
