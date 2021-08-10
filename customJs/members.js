function drawMemberShowCase(members)
{
    var memberShowCase=$("#memberShowCase");
    memberShowCase.html("");
    var data = $.parseJSON(members);
    $(data).each(function(i,val){
        var section=$("<div>");
        section.attr("class","col-lg-3 col-md-6");

        var card=$("<div>");
        card.attr("class","card");

        var cardItem=$("<div>");
        cardItem.attr("class","el-card-item");
        
        var cardAvatar=$("<div>");
        cardAvatar.attr("class","el-card-avatar el-overlay-1");
        
        var userImg=$("<img>");
        userImg.attr("src",val.imagePath);
        userImg.attr("alt","user");

        var elOverlay=$("<div>");
        elOverlay.attr("class","el-overlay");
        var ul=$("<ul>");
        ul.attr("class","list-style-none el-info");
        var li=$("<li>");
        li.attr("class","el-item");
        var a=$("<a>");
        a.attr("class","btn default btn-outline image-popup-vertical-fit el-link");
        a.attr("href",val.imagePath);

        var liMagnifier=$("<i>");
        liMagnifier.attr("class","sl-icon-magnifier");
        a.append(liMagnifier);
        li.append(a);
        ul.append(li);
        elOverlay.append(ul);
        cardAvatar.append(userImg);
        cardAvatar.append(elOverlay);


        var cardContent=$("<div>");
        cardContent.attr("class","el-card-content");
        var userTitle=$("<h4>");
        userTitle.attr("class","m-b-0");
        userTitle.attr("style","margin-bottom: 10px;");
        userTitle.html(val.name);
        cardContent.append(userTitle);

        cardItem.append(cardAvatar);
        cardItem.append(cardContent);
        card.append(cardItem);
        section.append(card);
        memberShowCase.append(section);


        var token=val.token;
        if(token==1)
        {
            var acceptButton=$("<span>");
            acceptButton.attr("type","button");
            acceptButton.attr("class","btn btn-primary");
            acceptButton.attr("onclick","acceptFriendRequest("+val.code+")");
            acceptButton.attr("id","acceptFriendRequest"+val.code);
            acceptButton.attr("name","acceptFriendRequest"+val.code);
            acceptButton.html("Accept");
            cardContent.append(acceptButton);
            
            var cancelButton=$("<span>");
            cancelButton.attr("type","button");
            cancelButton.attr("class","btn btn-danger");
            cancelButton.attr("style","margin-left:4px");
            cancelButton.attr("onclick","cancelFriendRequest("+val.code+")");
            cancelButton.attr("id","cancelRequestButton"+val.code);
            cancelButton.attr("name","cancelRequestButton"+val.code);
            var closeButton=$("<span>");
            closeButton.attr("class","ti-close");
            cancelButton.append(closeButton);
            cardContent.append(cancelButton);
    
            var nameInput=$("<input>");
            nameInput.attr("type","hidden");
            nameInput.attr("id","memberName");
            nameInput.attr("name","memberName");
            nameInput.attr("value",val.name);
            cardContent.append(nameInput);
    
            var codeInput=$("<input>");
            codeInput.attr("type","hidden");
            codeInput.attr("id","memberCode");
            codeInput.attr("name","memberCode");
            codeInput.attr("value",val.code);
            cardContent.append(codeInput);

        }
        else if(token==2)
        {
            var sentButton=$("<p>");
            sentButton.attr("class","btn btn-primary");
            sentButton.attr("id","acceptFriendRequest"+val.code);
            sentButton.attr("name","acceptFriendRequest"+val.code);
            sentButton.html("Friend Request Sent");
            cardContent.append(sentButton);

            var cancelButton=$("<span>");
            cancelButton.attr("type","button");
            cancelButton.attr("class","btn btn-danger");
            cancelButton.attr("style","margin-left:4px; margin-top:-15px;");
            cancelButton.attr("onclick","cancelFriendRequest("+val.code+")");
            cancelButton.attr("id","cancelRequestButton"+val.code);
            cancelButton.attr("name","cancelRequestButton"+val.code);
            var closeButton=$("<span>");
            closeButton.attr("class","ti-close");
            cancelButton.append(closeButton);
            cardContent.append(cancelButton);

            var nameInput=$("<input>");
            nameInput.attr("type","hidden");
            nameInput.attr("id","memberName");
            nameInput.attr("name","memberName");
            nameInput.attr("value",val.name);
            cardContent.append(nameInput);
    
            var codeInput=$("<input>");
            codeInput.attr("type","hidden");
            codeInput.attr("id","memberCode");
            codeInput.attr("name","memberCode");
            codeInput.attr("value",val.code);
            cardContent.append(codeInput);
        }
        else
        {
            var addButton=$("<span>");
            addButton.attr("type","button");
            addButton.attr("class","btn btn-primary");
            addButton.attr("onclick","addFriendRequest("+val.code+")");
            addButton.attr("id","addFriendRequest"+val.code);
            addButton.attr("name","addFriendRequest"+val.code);
            addButton.html("Add Friend");
            cardContent.append(addButton);
            
            var cancelButton=$("<span>");
            cancelButton.attr("type","button");
            cancelButton.attr("class","btn btn-danger hide");
            cancelButton.attr("style","margin-left:4px");
            cancelButton.attr("onclick","cancelFriendRequest("+val.code+")");
            cancelButton.attr("id","cancelRequestButton"+val.code);
            cancelButton.attr("name","cancelRequestButton"+val.code);
            var closeButton=$("<span>");
            closeButton.attr("class","ti-close");
            cancelButton.append(closeButton);
            cardContent.append(cancelButton);
    
            var nameInput=$("<input>");
            nameInput.attr("type","hidden");
            nameInput.attr("id","memberName");
            nameInput.attr("name","memberName");
            nameInput.attr("value",val.name);
            cardContent.append(nameInput);
    
            var codeInput=$("<input>");
            codeInput.attr("type","hidden");
            codeInput.attr("id","memberCode");
            codeInput.attr("name","memberCode");
            codeInput.attr("value",val.code);
            cardContent.append(codeInput);
        }

    });
}