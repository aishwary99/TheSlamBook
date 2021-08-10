var baseURL="/slamBook/";
function showMsgModal(msg)
{
    var launcher=$("#modalLauncher");
    launcher.click();
    if(msg=="")
    {
        //success....
        $(".modal-body").html('<h4 style="color:green;">Slam submitted successfully....</h4>');
    }
    else
    {
        //exception....
        $(".modal-body").html('<h4 style="color:red;">'+msg+'</h4>');
    }
}
function createForm()
{
    var friendName=$( "#inputGroupSelect01 option:selected").text();
    var friendCode=$( "#inputGroupSelect01 option:selected" ).attr("value");
    $("#friendCode").attr("value",friendCode);
    $("#friendsOption").css("display","none");
    $("#loader").css("display","block");
    setTimeout(function(){
        $("#loader").css("display","none");
        $("#slamForm").css("display","block");         
    },4000);
}
function drawFriendsSelector(listOfFriends)
{
    var listOfFriendsSection=$("#inputGroupSelect01");
    listOfFriendsSection.html("");
    var selectedOption=$("<option selected>");
    selectedOption.html("Choose...");
    listOfFriendsSection.append(selectedOption);
    var data = $.parseJSON(listOfFriends);
    $(data).each(function(i,val)
    {
        var option=$("<option>");
        option.attr("value",val.code);
        option.html(val.name);
        listOfFriendsSection.append(option);
    });
}
$(document).ready(function(){
        $.ajax({
            "url":baseURL+"getArrayOfFriends",
            "type":"POST",
            "dataType":"json",
            "data":"",
            "contentType":"application/json",
            "success":function(responseJson)
             {
                if(responseJson.success=="true")
                {
                    drawFriendsSelector(responseJson.listOfFriends);
                }
             },
            "error":function()
             {
                 alert("Error");
             }                
            });
});
function submitForm()
{
    var slam="";    //json
    var memberCode=$("#memberCode").attr("value");
    var friendCode=$("#friendCode").attr("value");
    var answers="";
    var required=new Array();
    var optional=new Array();
    //--------json properties------------
    var name=$("#name").val();
    var nickName=$("#nickname").val();
    var dob=$("#dob").val();
    //------Page 1 questions-------
    var drink= $('input[name="drink"]:checked').val();
    var personType= $('input[name="personType"]:checked').val();
    var relationshipStatus= $('input[name="relationshipStatus"]:checked').val();
    var personBehaviour= $('input[name="personBehaviour"]:checked').val();
    var nameInPhone= $("#nameInPhone").val();
    var relationshipWithAMovieName= $("#relationshipWithAMovieName").val();
    var dedicatedSong= $("#dedicatedSong").val();
    var crime= $("#crime").val();
    var like= $("#like").val();
    var dontlike= $("#dontLike").val();
    //------Page 2 questions-------
    var childishThing= $("#childishThing").val();
    var friendsThought= $("#friendsThought").val();
    var bestMomentInLife= $("#bestMomentInLife").val();
    var memorableMoment= $("#memorableMoment").val();
    var movie= $("#movie").val();
    var show= $("#show").val();
    var singer= $("#singer").val();
    var food= $("#food").val();
    var hobbies= $("#hobbies").val();
    var roleModel= $("#roleModel").val();
    var activities= $("#activities").val();
    var app= $("#app").val();
    //------Page 3 questions-------
    var dream= $("#dream").val();
    var confession= $("#confession").val();             //not required
    var customQuestion= $("#customQuestion").val();      //not required
    var ps= $("#ps").val();                             //not required
    var imgContents=$("#imgUploaded").html();              //not required
    //------Page 4 questions-------

    required.push(0,drink);
    required.push(1,personType);
    required.push(2,relationshipStatus);
    required.push(3,personBehaviour);
    required.push(4,nameInPhone);
    required.push(5,relationshipWithAMovieName);
    required.push(6,dedicatedSong);
    required.push(7,crime);
    required.push(8,like);
    required.push(9,dontlike);
    required.push(10,childishThing);
    required.push(11,friendsThought);
    required.push(12,bestMomentInLife);
    required.push(13,memorableMoment);
    required.push(14,movie);
    required.push(15,show);
    required.push(16,singer);
    required.push(17,food);
    required.push(18,hobbies);
    required.push(19,roleModel);
    required.push(20,activities);
    required.push(21,app);
    required.push(22,dream);
    console.log(required);
    optional.push(0,confession);
    optional.push(1,customQuestion);
    optional.push(2,ps);

    slam={
        "memberCode":memberCode,
        "friendCode":friendCode,
        "name":name,
        "nickName":nickName,
        "dob":dob,
        "answers":{
            "required":required,
            "optional":optional,
        },
        "img":imgContents
    };
        
    $.ajax({
            "url":baseURL+"uploadSlam",
            "type":"POST",
            "dataType":"json",
            "data":JSON.stringify(slam),
            "contentType":"application/json",
            "success":function(responseJson)
             {
                if(responseJson.success=="true")
                {
                    $("#name").val("");
                    $("#nickname").val("");
                    $("#dob").val("");
                    $("#drink").val("");
                    $("#personType").val("");
                    $("#relationshipStatus").val("");
                    $("#personBehaviour").val("");
                    $("#nameInPhone").val("");
                    $("#relationshipWithAMovieName").val("");
                    $("#dedicatedSong").val("");
                    $("#crime").val("");
                    $("#like").val("");
                    $("#dontlike").val("");
                    $("#childishThing").val("");
                    $("#friendsThought").val("");
                    $("#bestMomentInLife").val("");
                    $("#memorableMoment").val("");
                    $("#movie").val("");
                    $("#show").val("");
                    $("#singer").val("");
                    $("#food").val("");
                    $("#hobbies").val("");
                    $("#roleModel").val("");
                    $("#activities").val("");
                    $("#app").val("");
                    $("#dream").val("");
                    $("#confession").val("");
                    $("#customQuestion").val("");
                    $("#ps").val("");
                    $("#imgUploaded").css("display","none");
                    showMsgModal("");
                }
                else
                {
                    showMsgModal(responseJson.exception);
                }
             },
            "error":function()
             {
                    $("#imgUploaded").css("display","none");
                    showMsgModal("Oops ! There was some error. Please try again later...");
             }
    });
}