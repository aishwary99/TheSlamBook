    function reset()
    {
        $("#male"). prop("checked", false);
        $("#female"). prop("checked", false);
        $("#name").val("");
        $("#email").val("");
    }
    function successStatus()
    {
        var successBox=$(".successDiv");
        successBox.empty();
        var hyperLink=$("<p>");
        hyperLink.attr("class","alert alert-success");
        hyperLink.text("Logged in successfully.");
        successBox.append(hyperLink);
        successBox.show();    
    }
    function authenticate()
    {
        var email=$.trim($("#email").val());
        var password=$.trim($("#password").val());
        var valid=true;
        if(email.length==0 && password.length==0)
        {
            $("div input:text").addClass("has-error");
            $("div input:password").addClass("has-error");
            valid=false;
            return;
        }
        if(email.length==0)
        {
            $("#email").addClass("has-error");
            valid=false;
            return;
        }
        if(password.length==0)
        {
            $("#password").addClass("has-error");
            valid=false;
            return;
        }
        if(!valid) return;
        var slamBookService=new SlamBookService();
        var member=new Member();
        member.email=email;
        member.password=password;
        slamBookService.authenticate(member,function(imageStatus){
            //on Success
            successStatus();
            if(imageStatus=="true")
            {
            	$(location).attr('href','home.jsp');
            }
            else
            {
	            $(location).attr('href','setupProfile.jsp');
             }
        },function(exception){
            //on Exception
            var errorBox=$(".errorDiv");
            errorBox.empty();
            var hyperLink=$("<p>");
            hyperLink.attr("class","alert alert-danger");
            hyperLink.text(exception);
            errorBox.append(hyperLink);
            errorBox.show();
        },function(){
            //on Error
            //switchToErrorPage();
        });

    }
    function register()
    {
        var name=$.trim($("#name").val());
        var email=$.trim($("#email").val());
        var password=$.trim($("#password").val());
        var confirmPassword=$.trim($("#confirmPassword").val());
        var gender="";
        if(document.getElementById("male").checked)
        {
            gender="male";
        }
        else if(document.getElementById("female").checked)
        {
            gender="female";
        }
        else
        {
            gender="";
        }
        var valid=true;
        if(name.length==0 && email.length==0 && password.length==0 && confirmPassword.length==0 && gender.length==0)
        {
            $("div input:text").addClass("has-error");
            $("div input:password").addClass("has-error");
            valid=false;
            return;
        }
        if(name.length==0)
        {
            $("#name").addClass("has-error");
            valid=false;
            return;
        }
        if(email.length==0)
        {
            $("#email").addClass("has-error");
            valid=false;
            return;
        }
        if(password.length==0)
        {
            $("#password").addClass("has-error");
            valid=false;
            return;
        }
        if(confirmPassword.length==0)
        {
            $("#confirmPassword").addClass("has-error");
            valid=false;
            return;
        }
        if(password!=confirmPassword)
        {
            $("#password").addClass("has-error");
            valid=false;
            return;
        }
        if(gender.length==0)
        {
            valid=false;
            return;
        }
        if(!valid) return;
        var slamBookService=new SlamBookService();
        var member=new Member();
        member.name=name;
        member.email=email;
        member.gender=gender;
        member.password=password;
        slamBookService.register(member,function(){
            //on Success
            var successBox=$(".successDiv");
            successBox.empty();
            var hyperLink=$("<a>");
            hyperLink.attr("href",baseURL+"/index.html");
            hyperLink.attr("class","alert alert-success btn btn-success btn-block btn-flat");
            hyperLink.text("Registration Done , Proceed to Login ->");
            successBox.append(hyperLink);
            successBox.show();
        },function(exception){
            //on Exception
            var errorBox=$(".errorDiv");
            errorBox.empty();
            var hyperLink=$("<p>");
            hyperLink.attr("class","alert alert-danger");
            hyperLink.text(exception);
            errorBox.append(hyperLink);
            errorBox.show();
        },function(){
            //on Error
            alert("error");
        });
    }