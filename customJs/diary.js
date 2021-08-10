var baseURL="/slamBook/";
function plotSlamData(slamData,memberCode,friendCode,img,imgFormat)
{
    console.log(img);
    var data=JSON.parse(slamData);
    var name=data.name;
    var nickName=data.nickName;
    var dob=data.dob;
    var requiredAnswers=data.required;
    //------------Getting section references....
    var friendDetails=$("#friendDetails");
    var personality=$("#personality");
    var nameAndRelation=$("#nameAndRelation");
    var songDivison=$("#songDivison");
    var jailDivison=$("#jailDivison");
    var likeUnlike=$("#likeUnlike");
    var thinking=$("#thinking");
    var bestMoment=$("#bestMoment");
    var favourites=$("#favourites");
    var dreamAndConfession=$("#dreamAndConfession");
    var customQuestion=$("#customQuestion");
    var moments=$("#moments");
    var nextButton=$("nextButton");
    //--------------Plotting data in sections--------
    $(".formRow").css("display","block");
    $("#friendShowCase").css("display","block");
    if($(window).width()<=600)
    {
        friendDetails.css("display","block");
    }
    else
    {
        friendDetails.css("display","flex");
    }
    $("#name").html("");
    $("#nickname").html("");
    $("#birthday").html("");
    $("#name").html("&nbsp;&nbsp;"+name);
    $("#nickname").html("&nbsp;&nbsp;"+nickName);
    $("#birthday").html("&nbsp;&nbsp;"+dob);
    //---------Section 1---------------
    if($(window).width()<=600)
    {
        personality.css("display","block");
    }
    else
    {
        personality.css("display","flex");
    }
    var drink=requiredAnswers[1];
    var personAtmosphere=requiredAnswers[3];
    var relationshipStatus=requiredAnswers[5];
    var personType=requiredAnswers[7];
    if(drink=="tea")
    {
        $("input:radio[id=tea]").prop("checked",true);
        $("input:radio[id=coffee]").prop("disabled",true);
    }
    else
    {
        $("input:radio[id=tea]").prop("disabled",true);
        $("input:radio[id=coffee]").prop("checked",true);
    }
    if(personType=="introvert")
    {
        $("input:radio[id=introvert]").prop("checked",true);
        $("input:radio[id=extrovert]").prop("disabled",true);
    }
    else
    {
        $("input:radio[id=introvert]").prop("disabled",true);
        $("input:radio[id=extrovert]").prop("checked",true);
    }
    if(personAtmosphere=="morning")
    {
        $("input:radio[id=morning]").prop("checked",true);
        $("input:radio[id=night]").prop("disabled",true);
    }
    else
    {
        $("input:radio[id=morning]").prop("disabled",true);
        $("input:radio[id=night]").prop("checked",true);
    }
    if(relationshipStatus=="single")
    {
        $("input:radio[id=single]").prop("checked",true);
        $("input:radio[id=mingle]").prop("disabled",true);
    }
    else
    {
        $("input:radio[id=single]").prop("disabled",true);
        $("input:radio[id=mingle]").prop("checked",true);
    }
    //--Section 3---------
    if($(window).width()<=600)
    {
        nameAndRelation.css("display","block");
    }
    else
    {
        nameAndRelation.css("display","flex");
    }
    var nameInPhone=requiredAnswers[9];
    var relationship=requiredAnswers[11];
    $("#nameInPhone").html("&nbsp;&nbsp;"+nameInPhone);
    $("#relationship").html("&nbsp;&nbsp;"+relationship);
    //----Section 4---------
    if($(window).width()<=600)
    {
        songDivison.css("display","block");
    }
    else
    {
        songDivison.css("display","flex");
    }
    var song=requiredAnswers[13];
    $("#song").html(""+song);
    if($(window).width()<=600)
    {
        jailDivison.css("display","block");
    }
    else
    {
        jailDivison.css("display","flex");
    }
    
    var jail=requiredAnswers[15];
    $("#jail").html(""+jail);
    //-------Section 5--------
    if($(window).width()<=600)
    {
        likeUnlike.css("display","block");
    }
    else
    {
        likeUnlike.css("display","flex");

    }
    var like=requiredAnswers[17];
    var unlike=relationship[19];
    $("#like").html("&nbsp;&nbsp;"+like);
    $("#unlike").html("&nbsp;&nbsp;"+unlike);
    //----------Section 6----------
    if($(window).width()<=600)
    {
        thinking.css("display","block");
    }
    else
    {
        thinking.css("display","flex");
    }
    var childishThing=requiredAnswers[21];
    var untrue=requiredAnswers[23];
    $("#childishThing").html("&nbsp;&nbsp;"+childishThing);
    $("#untrue").html("&nbsp;&nbsp;"+untrue);
    //-------Section 7-----------
    if($(window).width()<=600)
    {
        bestMoment.css("display","block");    
    }
    else
    {
        bestMoment.css("display","flex");
    }
    var moment=requiredAnswers[25];
    var memory=requiredAnswers[27];
    $("#moment").html("&nbsp;&nbsp;"+moment);
    $("#memory").html("&nbsp;&nbsp;"+memory);
    //----------Section 8
    if($(window).width()<=600)
    {
        favourites.css("display","block");
    }
    else
    {
        favourites.css("display","flex");
    }
    var movie=requiredAnswers[29];
    var show=requiredAnswers[31];
    var singer=requiredAnswers[33];
    var food=requiredAnswers[35];
    var hobbies=requiredAnswers[37];
    var roleModel=requiredAnswers[39];
    var activities=requiredAnswers[41];
    var app=requiredAnswers[43];
    $("#movie").html(movie);
    $("#show").html(show);
    $("#singer").html(singer);
    $("#food").html(food);
    $("#hobbies").html(hobbies);
    $("#roleModel").html(roleModel);
    $("#activites").html(activities);
    $("#app").html(app);
    //----Section 9----------
    if($(window).width()<=600)
    {
        dreamAndConfession.css("display","block");
    }
    else
    {
        dreamAndConfession.css("display","flex");
    }
    var dream=requiredAnswers[45];
    var confession=requiredAnswers[47];
    if(dream=='' || dream==null)
    {
        $("#dream").html("Not Answered");
    }
    else
    {
        $("#dream").html(dream);
    }
    if(confession=='' || confession==null)
    {
        $("#confession").html("Not Answered");
    }
    else
    {
        $("#confession").html(confession);
    }
    //------Section 10---------
    if($(window).width()<=600)
    {
        customQuestion.css("display","block");
    }
    else
    {
        customQuestion.css("display","flex");
    }
    var question=requiredAnswers[49];
    if(question=='' || question==null)
    {
        $("#question").html("Not Answered");
    }
    else
    {
        $("#question").html(question);
    }
    //--------Section 11------------
    if($(window).width()<=600)
    {
        moments.css("display","block");
    }
    else
    {
        moments.css("display","flex");
    }
    var imgString="";
    if(imgFormat=="jpg")
    {
        imgString="data:image/jpg;base64,"+img;
    }
    else
    {
        imgString="data:image/png;base64,"+img;
    }
    var momentsImage=$("<img>");
    momentsImage.attr("src",imgString);
    momentsImage.attr("width","30%");
    momentsImage.attr("id","momentsPic");
    $("#pictureTogether").append(momentsImage);
    $("#nextButton").css("display","block");

    //--------Section 12----------

}
function drawFriendSelector(friends)
{
    var friendSelector=$("#slamFriends");
    friendSelector.html("");
    var selectedOption=$("<option selected>");
    selectedOption.html("Choose...");
    friendSelector.append(selectedOption);
    var data = $.parseJSON(friends);
    $(data).each(function(i,val)
    {
        var option=$("<option>");
        option.attr("value",val.code);
        option.html(val.name);
        friendSelector.append(option);
    });
}
function showForm()
{
    //Friend Code--->
    var friendCode=$( "#slamFriends option:selected" ).attr("value");
    var friendName=$( "#slamFriends option:selected" ).html();
    $("#friendName").html("&nbsp;&nbsp;"+friendName);
    var json={
        "friendCode":friendCode
    };
    $.ajax({
        "url":baseURL+"getSlamData",
        "type":"POST",
        "dataType":"json",
        "data":JSON.stringify(json),
        "contentType":"application/json",
        "success":function(responseJson)
         {
            if(responseJson.success=="true")
            {
                //on Success
                plotSlamData(responseJson.slamData,responseJson.memberCode,responseJson.friendCode,responseJson.img,responseJson.imgFormat);
            }
            else
            {
                console.log("Failure while extracting data...");  
            }
         },
        "error":function()
         {
            console.log("Error");
         }
    });
}
function showDiary()
{
    var diarySection=$("#diaryOpenerSection");
    diarySection.css('display','none');
    $("#friendSelector").css("display","block");
    $("#slamForm").css("display","block");
}
$(document).ready(function()
{
    $("input:radio[id=tea]").prop("checked",false);
    $("input:radio[id=coffee]").prop("checked",false);
    $("input:radio[id=introvert]").prop("checked",false);
    $("input:radio[id=extrovert]").prop("checked",false);
    $("input:radio[id=morning]").prop("checked",false);
    $("input:radio[id=night]").prop("checked",false);
    $("input:radio[id=single]").prop("checked",false);
    $("input:radio[id=mingle]").prop("checked",false);
    $.ajax({
        "url":baseURL+"getSlamFriends",
        "type":"POST",
        "dataType":"json",
        "data":"",
        "contentType":"application/json",
        "success":function(responseJson)
         {
            if(responseJson.success=="true")
            {
                drawFriendSelector(responseJson.friends);
            }
            else
            {
                console.log("Failure while extracting data...");  
            }
         },
        "error":function()
         {
            console.log("Error");
         }
    });
});