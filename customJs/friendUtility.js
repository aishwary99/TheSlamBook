var baseURL="/slamBook/";
function SearchMemberService()
{
    this.searchMember=function(name,onSuccess,onFailure,onError)
    {
        var nameJson={
        "name":name
        };
        $.ajax({
            "url":baseURL+"searchMember",
            "type":"POST",
            "dataType":"json",
            "data":JSON.stringify(nameJson),
            "contentType":"application/json",
            "success":function(responseJson)
             {
                if(responseJson.success=="true") onSuccess();
                    else onFailure();
             },
            "error":function()
             {
                onFailure();
             }
            });
    };
}
function SearchMember()
{
    var name=$("#autocomplete").val();
    var searchMemberService=new SearchMemberService();
    searchMemberService.searchMember(name,function(responseJson){
    //on Success
    $(location).attr('href','searchResults.jsp');
    },function(){
    //on Failure
    alert("Failure");
    },function(){
    //on Error
    });
}
function drawFriendRequestList(listOfFriendRequestArrived)
{
    var friendRequestList=$("#friendRequestList");
    friendRequestList.html("");
    var data = $.parseJSON(listOfFriendRequestArrived);
    if(data.length!=0) $("#requestListBellBadge").html(data.length);
    $(data).each(function(i,val)
    {
        var link=$("<a>");
        link.attr("href","javascript:void(0)");
        link.attr("class","message-item");
        var userImg=$("<img>");
        userImg.attr("src",val.imagePath);
        userImg.attr("style","border-radius:50%");
        userImg.attr("width","23%");
        link.append(userImg);
        var listSection=$("<div>");
        listSection.attr("class","mail-contnet");
        var inputElement=$("<input>");
        inputElement.attr("type","hidden");
        inputElement.attr("id","memberCode");
        inputElement.attr("name","memberCode");
        inputElement.attr("value",val.code);
        listSection.append(inputElement);       
        inputElement=$("<input>");
        inputElement.attr("type","hidden");
        inputElement.attr("id","memberName");
        inputElement.attr("name","memberName");
        inputElement.attr("value",val.name);
        listSection.append(inputElement);
        var messageTitle=$("<h5>");
        messageTitle.attr("class","message-title");
        messageTitle.html(val.name);
        listSection.append(messageTitle);
        var acceptButton=$("<span>");
        acceptButton.attr("type","button");
        acceptButton.attr("class","btn btn-primary");
        acceptButton.attr("style","margin-top:5px");
        acceptButton.attr("onclick","acceptFriendRequest("+val.code+")");
        acceptButton.html("Accept");
        listSection.append(acceptButton);
        var rejectButton=$('<span>');
        rejectButton.attr("type","button");
        rejectButton.attr("class","btn btn-danger");
        rejectButton.attr("style","margin-top:5px; margin-left:5px;");
        rejectButton.attr("onclick","rejectFriendRequest()");
        rejectButton.html("Reject");
        listSection.append(rejectButton);
        link.append(listSection);
        friendRequestList.append(link);
    });
}
$(document).ready(function(){ 
    setInterval(function()
    {
        $.ajax({
            "url":baseURL+"updater",
            "type":"POST",
            "dataType":"json",
            "data":"",
            "contentType":"application/json",
            "success":function(responseJson)
             {
                if(responseJson.success=="true")
                {
                    drawFriendRequestList(responseJson.listOfFriendRequestArrived);
                }
                else
                {
                    location.href="index.html";
                    location.replace="index.html";
                }
             },
            "error":function()
             {
             }                
            });
    }, 2000);
});
function MemberUpdater()
{
    this.name="";
    this.email="";
    this.currentPassword="";
    this.newPassword="";
}
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
function RequestAccepter()
{
    this.requestSenderCode=0;
    this.requestSenderName="";
}
function Canceller()
{
    this.requestSenderCode=0;
    this.requestSenderName="";
}
function SearchMemberService()
{
    this.searchMember=function(name,onSuccess,onFailure,onError)
    {
        var nameJson={
        "name":name
        };
        $.ajax({
            "url":baseURL+"searchMember",
            "type":"POST",
            "dataType":"json",
            "data":JSON.stringify(nameJson),
            "contentType":"application/json",
            "success":function(responseJson)
             {
                if(responseJson.success=="true") onSuccess();
                    else onFailure();
             },
            "error":function()
             {
                onFailure();
             }
            });
    };
}
function SearchMember()
{
    var name=$("#autocomplete").val();
    var searchMemberService=new SearchMemberService();
    searchMemberService.searchMember(name,function(responseJson){
    //on Success
    $(location).attr('href','searchResults.jsp');
    },function(){
    //on Failure
    alert("Failure");
    },function(){
    //on Error
    });
}
function updateProfile()
{
    var nameField=$("#name");
    var emailField=$("#email");
    var currentPasswordField=$("#currentPassword");
    var newPasswordField=$("#newPassword");
    if(emailField.val().length==0)
    {
            emailField.css("border","1px solid red");
            return;
    }
    if(currentPasswordField.val().length==0)
    {
            currentPasswordField.css("border","1px solid red");
            return;
    }
    if(newPasswordField.val().length==0)
    {
            newPasswordField.css("border","1px solid red");
            return;
    }
    var memberUpdater=new MemberUpdater();
    memberUpdater.name=nameField.attr("placeholder");
    memberUpdater.email=emailField.val();
    memberUpdater.currentPassword=currentPasswordField.val();
    memberUpdater.newPassword=newPasswordField.val();
    var ups=new UpdateProfileService();
    ups.updateProfile(memberUpdater,function(){
            //onSuccess
    var alertSection=$(".alertMessage");
    var alertMsg=$("<p>");
    alertMsg.attr("class","alert alert-success");
    alertMsg.text("Profile updated successfully...");
    alertSection.append(alertMsg);
    alertSection.show();
    reset();
    setTimeout(function(){
		alertSection.hide();
		},2000);
    },function(error){
    //onFailure
    var alertSection=$(".alertMessage");
    var alertMsg=$("<p>");
    alertMsg.attr("class","alert alert-danger");
    alertMsg.text(error);
    alertSection.append(alertMsg);
    alertSection.show();
    });
}
function UpdateProfileService()
{
    this.updateProfile=function(memberUpdater,onSuccess,onFailure)
    {
                $.ajax({
                "url":baseURL+"updateProfile",
                "type":"POST",
                "dataType":"json",
                "data":JSON.stringify(memberUpdater),
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
function reset()
{
    var nameField=$("#name");
    var emailField=$("#email");
    var currentPasswordField=$("#currentPassword");
    var newPasswordField=$("#newPassword");
    nameField.val("");
    emailField.val("");
    currentPasswordField.val("");
    newPasswordField.val("");
}
function AddFriendService()
{
    this.add=function(requestSender,onSuccess,onFailure,onError)
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
    },function(exception){
        //onException
        alert(exception);
    },function(){
        
    });    
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
    this.cancel=function(canceller,onSuccess,onFailure,onError)
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
    var requestSenderName=$("#memberName").val();
    var cancelFriendRequestService=new CancelFriendRequestService();
    var canceller=new Canceller();
    canceller.requestSenderCode=code;
    canceller.requestSenderName=requestSenderName;
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
    }
    );
}
function acceptFriendRequest(code)
{
var rn="";
var rc=code;
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
var rn=$("#memberName").val();
var rc=$("#memberCode").val();
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
},
function(){
    //onError
    alert("Error");
});
}
function RejectFriendRequestService()
{
    this.reject=function(requestRejecter,onSuccess,onFailure,onError)
    {
        $.ajax({
                "url":baseURL+"rejectFriendRequest",
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