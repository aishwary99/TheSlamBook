var baseURL="/slamBook/";
function RequestSender()
{
    this.requestSenderCode=0;
    this.requestSenderName="";
}
function RequestRejecter()
{
    this.requestSenderCode=0;
    this.requestSenderName="";
}
function canceller()
{
    this.requestAccepterCode=0;
    this.requestAccepterName="";
}
function AddFriendService()
{
    this.add=function(requestSender,onSuccess,onFailure)
    {
        showSpinner(requestSender.requestSenderCode);
        //alert(JSON.stringify(requestSender));
        $.ajax({
                "url":baseURL+"addFriendRequest",
                "type":"POST",
                "dataType":"json",
                "data":JSON.stringify(requestSender),
                "contentType":"application/json",
                "success":function(responseJson)
                {
                    if(responseJson.success=="true") onSuccess();
                    else onFailure(responseJson.exception);
                },
                "error":function()
                {
                    onError();
                }
                });
    };

}
function addFriendRequest(code)
{
    var name=$("#memberName").val();
    var addFriendService=new AddFriendService();
    var requestSender=new RequestSender();
    requestSender.requestSenderCode=code;
    requestSender.requestSenderName=name;
    addFriendService.add(requestSender,function(){
        //onSuccess
        //alert("Success");
    },function(exception){
                //onFailure
                //alert("Failure");
    },
    function()
    {
    }
    );    
}
function resetRequester(code)
{
    var addFriendBox=$("#addFriendRequest"+code);
    addFriendBox.text("Add Friend");
    addFriendBox.show();
    var cancelRequestBox=$("#cancelRequestButton"+code);
    cancelRequestBox.addClass("hide");
}
function CancelFriendRequestService()
{
    this.cancel=function(canceller,onSuccess,onFailure)
    {
        resetRequester(canceller.requestAccepterCode);
        //alert(JSON.stringify(requestSender));
        $.ajax({
                "url":baseURL+"cancelFriendRequest",
                "type":"POST",
                "dataType":"json",
                "data":JSON.stringify(canceller),
                "contentType":"application/json",
                "success":function(responseJson)
                {
                    if(responseJson.success=="true") onSuccess();
                    else onFailure(responseJson.exception);
                },
                "error":function()
                {
                    onError();
                }
            });
    };

}
function cancelFriendRequest(code)
{
    var requestAccepterName=$("#memberName").val();
    var cancelFriendRequestService=new CancelFriendRequestService();
    var canceller=new Canceller();
    canceller.requestAccepterCode=code;
    canceller.requestAccepterName=requestAccepterName;
    cancelFriendRequestService.cancel(canceller,function(){
    //onSuccess
    //alert("Success");
    },function(exception){
    //onFailure
    //alert("Failure");
    },
    function()
    {
    //onError
    //alert("Error");
    }
    );
}
function acceptFriendRequest()
{
var rn=$("#requestSenderName").val();
var rc=$("#requestSenderCode").val();
var acceptFriendRequestService=new AcceptFriendRequestService();
var requestSender=new RequestSender();
requestSender.requestSenderCode=rc;
requestSender.requestSenderName=rn;
acceptFriendRequestService.accept(requestSender,function(){
    //onSuccess
    updater();
    initializePage();
},
function(){
    //onError
});
}
function AcceptFriendRequestService()
{
            this.accept=function(requestSender,onSuccess,onFailure)
            {
                $.ajax({
                "url":baseURL+"acceptFriendRequest",
                "type":"POST",
                "dataType":"json",
                "data":JSON.stringify(requestSender),
                "contentType":"application/json",
                "success":function(responseJson)
                {
                    if(responseJson.success=="true") onSuccess();
                    else onFailure(responseJson.exception);
                },
                "error":function()
                {
                    onError();
                }
                });
            };

}
function rejectFriendRequest()
{
var rn=$("#requestSenderName").val();
var rc=$("#requestSenderCode").val();
var rejectFriendRequestService=new RejectFriendRequestService();
var requestRejecter=new RequestRejecter();
requestRejecter.requestSenderCode=rc;
requestRejecter.requestSenderName=rn;
rejectFriendRequestService.reject(requestRejecter,function(){
    //onSuccess
    updater();
    initializePage();
},
function(){
    //onError
});
}
function RejectFriendRequestService()
{
    this.reject=function(requestRejecter,onSuccess,onFailure)
    {
        $.ajax({
                url":baseURL+"rejectFriendRequest",
                "type":"POST",
                "dataType":"json",
                "data":JSON.stringify(requestRejecter),
                "contentType":"application/json",
                "success":function(responseJson)
                {
                    if(responseJson.success=="true") onSuccess();
                    else onFailure(responseJson.exception);
                },
                "error":function()
                {
                    onError();
                }
                });
            };

}
function showSpinner(code)
{
    var addFriendBox=$("#addFriendRequest"+code);
    var hyperLink=$("<li>");
    hyperLink.attr("class","fa fa-spinner fa-pulse fa-1x fa-fw");
    addFriendBox.html(hyperLink);
    addFriendBox.show();
    setTimeout(function() {
    hideSpinner(code);
    }, 1000)
}
function hideSpinner(code)
{
    var addFriendBox=$("#addFriendRequest"+code);
    addFriendBox.text("Friend Request Sent");
    addFriendBox.show();
    var cancelRequestBox=$("#cancelRequestButton"+code);
    cancelRequestBox.removeClass("hide");
}