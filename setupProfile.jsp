<useBean id="memberBean" class="com.slam.book.beans.MemberBean"/>
<!DOCTYPE html>
<html><head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta content="IE=edge" http-equiv="X-UA-Compatible">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="A simple jQuery image cropping plugin.">
        <meta name="keywords" content="HTML, CSS, JS, JavaScript, jQuery plugin, image cropping, front-end, frontend, web development">
        <meta name="author" content="Fengyuan Chen">
        <title>Setup Profile</title>
        <link href="http://fengyuanchen.github.io/cropper/apple-touch-icon-precomposed.png" rel="apple-touch-icon-precomposed">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <link href="cropper/css/cropper.css" rel="stylesheet">
        <link href="cropper/css/style.css" rel="stylesheet">
        <link rel="stylesheet" href="font-awesome/css/font-awesome.css">
        <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
		<link href="https://fonts.googleapis.com/css?family=Quicksand&display=swap" rel="stylesheet"> 
        <script type="text/javascript">
        function redirectToHome()
        {        
            $(location).attr('href','home.jsp');
        }
        function CropperService()
        {
            this.uploadData=function(imageData,onSuccess,onFailure,onError){
            $.ajax({
                "url":"/slamBook/upload",
                "type":"POST",
                "dataType":"json",
                "data":imageData,
                "contentType":"application/json",
                "success":function(responseJson)
                {  
                    if(responseJson.success=="true") onSuccess();
                    else onFailure();
                },
                "error":function()
                {
                    onError();
                }
            });
            };
        }
        function sendData()
        {
            var img=$("#dataURL").val();
            var cropperService=new CropperService();
            cropperService.uploadData(img,function(responseJson){
            //on Success
            var successBox=$(".successDiv");
            successBox.empty();
            var hyperLink=$("<p>");
            hyperLink.attr("class","alert alert-success");
            hyperLink.text("Profile Picture Uploaded Successfully !");
            successBox.append(hyperLink);
            successBox.removeClass("hide");
            setTimeout(redirectToHome,3000);
            },function(exception){
            //on Exception
            alert(exception);
            },function(){
                alert("Error");
            });
        }
            var cFlag = false;

            function setCrop()
            {
                $("#err").text("");
                cFlag = true;
            }

            function validate()
            {
                if (cFlag)
                {
                    return cFlag;
                }
                else
                {
                    $("#err").text("Please crop before upload");
                    $("#err").css("color", "red");
                    return cFlag;
                }

            }
        </script>
    </head>
    <body>
    <div class="container-fluid main-box">
    <div class="container-fluid" style="height:20px;"></div>
    <div class="col-lg-2"></div>
     <div class="container col-lg-8 inner-box">
   	   <div class="row">
            <img src="img/logo-black.png" class="img-responsive center-block" width="180">
            <h2 class="text-center" style="margin-top:-3px; font-family: 'Quicksand', sans-serif;"><small><b>Hello  ,</b></small> <strong style="color:darkcyan">${memberBean.name}!</strong></h2>                     
<h4 class="text-center" style="padding-top:10px; font-family: 'Quicksand', sans-serif;"><i class="fa fa-address-book-o" aria-hidden="true"></i>
			&nbsp;<b>Setup Profile</b></h4>
        </div>
        <div class="successDiv hide"></div>
            <div class="row"> 
                <div class="container-fluid eg-container" id="basic-example">
                    <div class="row eg-main">
                    	<div class="col-lg-1"></div>
                        <div class="col-lg-5 col-md-5 col-xs-5">
                            <div class="eg-wrapper">
                                &nbsp;&nbsp;&nbsp;<img class="cropper" alt="Select Picture">
                            </div>
                        </div>
                        <div class="col-lg-2"></div>
                        <div class="col-lg-5 eg-output">
                        <div id="showDataURL"></div>
                        <div class="col-lg-1"></div>
                        </div>
                    </div>

                    <div class="clearfix">

                        <div class="col-xs-12">
                            <div class="eg-button">
                                <button class="btn btn-warning" id="reset" type="button"><i class="fa fa-window-restore" aria-hidden="true"></i>
Reset</button>
                                <button class="btn btn-info" id="zoomIn" type="button"><i class="fa fa-search-plus" aria-hidden="true"></i>
Zoom In</button>
                                <button class="btn btn-info" id="zoomOut" type="button"><i class="fa fa-search-minus" aria-hidden="true"></i>
Zoom Out</button>
                                <label class="btn btn-primary" for="inputImage" title="Upload image file">
                                    <input class="hide" id="inputImage" name="file" accept="image/*" type="file">
                                    <span data-original-title="Import image with FileReader" class="docs-tooltip" data-toggle="tooltip" title="">
                                        <span class="glyphicon glyphicon-upload"></span> Select Image to Crop
                                    </span>
                                </label>
                                <button class="btn btn-primary" id="getDataURL" onclick="setCrop();" type="button"><i class="fa fa-crop" aria-hidden="true"></i>
Crop Image to Preview</button>
                            </div>

                            <div class="row eg-output">
                                <div class="col-md-12">
                                    
                                    <div id="err"></div>
                                </div>
                                <div class="col-md-6">
                        
                                        <textarea name="image_file" class="form-control hide" id="dataURL" rows="10"></textarea>
                                        <button type="button" onclick="sendData()" class="btn btn-success"><i class="fa fa-floppy-o" aria-hidden="true"></i>
Save</button>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="hidden-print docs-sidebar" role="complementary">
                                </div>
                            </div>
                        </div>                
                </div>           
            </div>
        </div>
        	<div class="container-fluid">
            	<a href="/slamBook/skipProfileSetter" class="btn btn-default pull-right"><i class="fa fa-share" aria-hidden="true"></i>
Skip</a>
            </div>
            <div class="container-fluid" style="height:20px;"></div>
         </div>
                 <div class="container-fluid" style="height:50px;"></div>
        </div>

        <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script src="cropper/js/cropper.js"></script>
        <script src="cropper/js/docs.js"></script>
        <script>
                                        $(function () {
                                            var $image = $(".cropper"),
                                                    $dataX = $("#dataX"),
                                                    $dataY = $("#dataY"),
                                                    $dataHeight = $("#dataHeight"),
                                                    $dataWidth = $("#dataWidth"),
                                                    console = window.console || {log: $.noop},
                                            cropper;

                                            $image.cropper({
                                                aspectRatio: 16 / 9,
                                                // autoCropArea: 1,
                                                data: {
                                                    x: 420,
                                                    y: 50,
                                                    width: 640,
                                                    height: 360
                                                },
                                                preview: ".preview",
                                                // multiple: FALSE,
                                                // autoCrop: TRUE,
                                                // dragCrop: TRUE,
                                                // dashed: TRUE,
                                                // modal: TRUE,
                                                // movable: TRUE,
                                                // resizable: TRUE,
                                                // zoomable: TRUE,
                                                // rotatable: TRUE,

                                                // maxWidth: 480,
                                                // maxHeight: 270,
                                                // minWidth: 160,
                                                // minHeight: 90,

                                                done: function (data) {
                                                    $dataX.val(data.x);
                                                    $dataY.val(data.y);
                                                    $dataHeight.val(data.height);
                                                    $dataWidth.val(data.width);
                                                },
                                                build: function (e) {
                                                    console.log(e.type);
                                                },
                                                built: function (e) {
                                                    console.log(e.type);
                                                },
                                                dragstart: function (e) {
                                                    console.log(e.type);
                                                },
                                                dragmove: function (e) {
                                                    console.log(e.type);
                                                },
                                                dragend: function (e) {
                                                    console.log(e.type);
                                                }
                                            });

                                            cropper = $image.data("cropper");

                                            $image.on({
                                                "build.cropper": function (e) {
                                                    console.log(e.type);
                                                    // e.preventDefault();
                                                },
                                                "built.cropper": function (e) {
                                                    console.log(e.type);
                                                    // e.preventDefault();
                                                },
                                                "dragstart.cropper": function (e) {
                                                    console.log(e.type);
                                                    // e.preventDefault();
                                                },
                                                "dragmove.cropper": function (e) {
                                                    console.log(e.type);
                                                    // e.preventDefault();
                                                },
                                                "dragend.cropper": function (e) {
                                                    console.log(e.type);
                                                    // e.preventDefault();
                                                }
                                            });

                                            $("#reset").click(function () {
                                                $image.cropper("reset");
                                            });

                                            $("#reset2").click(function () {
                                                $image.cropper("reset", true);
                                            });

                                            $("#clear").click(function () {
                                                $image.cropper("clear");
                                            });

                                            $("#destroy").click(function () {
                                                $image.cropper("destroy");
                                            });

                                            $("#enable").click(function () {
                                                $image.cropper("enable");
                                            });

                                            $("#disable").click(function () {
                                                $image.cropper("disable");
                                            });

                                            $("#zoom").click(function () {
                                                $image.cropper("zoom", $("#zoomWith").val());
                                            });

                                            $("#zoomIn").click(function () {
                                                $image.cropper("zoom", 0.1);
                                            });

                                            $("#zoomOut").click(function () {
                                                $image.cropper("zoom", -0.1);
                                            });

                                            $("#rotate").click(function () {
                                                $image.cropper("rotate", $("#rotateWith").val());
                                            });

                                            $("#rotateLeft").click(function () {
                                                $image.cropper("rotate", -90);
                                            });

                                            $("#rotateRight").click(function () {
                                                $image.cropper("rotate", 90);
                                            });

                                            $("#setAspectRatio").click(function () {
                                                $image.cropper("setAspectRatio", $("#aspectRatio").val());
                                            });

                                            $("#replace").click(function () {
                                                $image.cropper("replace", $("#replaceWith").val());
                                            });

                                            $("#getImageData").click(function () {
                                                $("#showImageData").val(JSON.stringify($image.cropper("getImageData")));
                                            });

                                            $("#setData").click(function () {
                                                $image.cropper("setData", {
                                                    x: $dataX.val(),
                                                    y: $dataY.val(),
                                                    width: $dataWidth.val(),
                                                    height: $dataHeight.val()
                                                });
                                            });

                                            $("#getData").click(function () {
                                                $("#showData").val(JSON.stringify($image.cropper("getData")));
                                            });

                                            $("#getDataURL").click(function () {
                                                var dataURL = $image.cropper("getDataURL");										
                                                $("#dataURL").text(dataURL);
                                                $("#showDataURL").html('<img src="' + dataURL + '" class="img-circle" style="-webkit-box-shadow: -1px 2px 46px -25px rgba(0,0,0,0.75); -moz-box-shadow: -1px 2px 46px -25px rgba(0,0,0,0.75); box-shadow: -1px 2px 46px -25px rgba(0,0,0,0.75);">');
                                            });

                                            $("#getDataURL2").click(function () {
                                                var dataURL = $image.cropper("getDataURL", "image/jpeg");

                                                $("#dataURL").text(dataURL);
                                                $("#showDataURL").html('<img src="' + dataURL + '">');
                                            });
                                        });
        </script>
    </body>
</html>
