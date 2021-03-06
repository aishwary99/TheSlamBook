<%@ taglib uri='/WEB-INF/tlds/CustomTags.tld' prefix='sb'%>
<sb:SecurityGuard/>
<jsp:useBean id='memberBean' scope='session' class='com.slam.book.beans.MemberBean'/>
<%@page import="java.util.*" %>
<%@page import="com.slam.book.dao.*" %>
<%@page import="com.slam.book.beans.*"%>
<!DOCTYPE html >
<html dir="ltr" lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="assets/images/favicon.png">
    <title>Write Slam</title>
    <!-- This page CSS -->
    <link href="assets/libs/jquery-steps/jquery.steps.css" rel="stylesheet">
    <link href="assets/libs/jquery-steps/steps.css" rel="stylesheet">
    <!-- Custom ---->
    <link href="dist/css/style.min.css" rel="stylesheet">
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
     <!-- Custom CSS -->
     <link href="dist/css/style.min.css" rel="stylesheet">
     <script src="customJs/style.js"></script>
     <link rel="stylesheet" href="customCss/style.css">

     <link rel="stylesheet" href="customCss/jqueryUi.css">
     <script type="text/javascript" src="customJs/jQuery1.12.4.js"></script>
     <script type="text/javascript" src="customJs/jQueryUI.js"></script>
     
     <!-------Custom JS-->
     <link rel="stylesheet" href="customCss/jquery.badge.css">
     <link rel="stylesheet" href="font-awesome/css/font-awesome.css">
     <script src="customJs/jquery.badge.js"></script>
     <script src="customJs/friendUtility.js"></script>
    <script src="customJs/slam.js"></script>
    <style>
        .cover
        {
            width: 300px;
            height: 300px;
            margin: 20px auto;
            
        }
        .image-preview
        {
            margin-bottom:10px;
            width: 100%;
            height: 56%;
            overflow: hidden;
            border-radius: 10px;
            border: 1px dashed black;
        }
        .image-preview img
        {
             width: 100%;
             height: auto;
        }
    </style>
</head>

<body>
    <!-- ============================================================== -->
    <!-- Preloader - style you can find in spinners.css -->
    <!-- ============================================================== -->
    <div class="preloader">
        <div class="lds-ripple">
            <div class="lds-pos"></div>
            <div class="lds-pos"></div>
        </div>
    </div>
    <!-- sample modal content -->
    <div id="verticalcenter" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="vcenter" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="vcenter">Submitted</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">??</button>
                </div>
                <div class="modal-body">
                    
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-info waves-effect" data-dismiss="modal">Close</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
    <button type="button" id="modalLauncher" data-toggle="modal" data-target="#verticalcenter" style="display:none;"></button>
    <!-- ============================================================== -->
    <!-- Main wrapper - style you can find in pages.scss -->
    <!-- ============================================================== -->
    <div id="main-wrapper">
        <!-- ============================================================== -->
        <!-- Topbar header - style you can find in pages.scss -->
        <!-- ============================================================== -->
        

        <header class="topbar">
            <nav class="navbar top-navbar navbar-expand-md navbar-dark">
                <div class="navbar-header">
                    <!-- This is for the sidebar toggle which is visible on mobile only -->
                    <a class="nav-toggler waves-effect waves-light d-block d-md-none" href="javascript:void(0)">
                        <i class="ti-menu ti-close"></i>
                    </a>
                    <!-- ============================================================== -->
                    <!-- Logo -->
                    <!-- ============================================================== -->
                    <a class="navbar-brand" href="home.jsp">
                                                <!-- Logo text -->
                        <span class="logo-text" style="margin-left:10px">
                            <!-- dark Logo text -->
	           <img src="img/logo-white.png" alt="homepage" width="200" class="light-logo" /> 
                            <img src="img/logo-black.png" alt="homepage" width="200" class="dark-logo" />                      
                        </span>
                    </a>
                    <!-- ============================================================== -->
                    <!-- End Logo -->
                    <!-- ============================================================== -->
                    <!-- ============================================================== -->
                    <!-- Toggle which is visible on mobile only -->
                    <!-- ============================================================== -->
                    <a class="topbartoggler d-block d-md-none waves-effect waves-light" href="javascript:void(0)" data-toggle="collapse" data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <i class="ti-more"></i>
                    </a>
                </div>
                <!-- ============================================================== -->
                <!-- End Logo -->
                <!-- ============================================================== -->
                <div class="navbar-collapse collapse" id="navbarSupportedContent">
                    <!-- ============================================================== -->
                    <!-- toggle and nav items -->
                    <!-- ============================================================== -->
                    <ul class="navbar-nav float-left mr-auto">
                        <li class="nav-item d-none d-md-block">
                            <a class="nav-link sidebartoggler waves-effect waves-light" href="javascript:void(0)" data-sidebartype="mini-sidebar">
                                <i class="sl-icon-menu font-20"></i>
                            </a>
                        </li>
                        <!-- ============================================================== -->
                        <!-- mega menu -->
                        <!-- ============================================================== -->
                        <li class="nav-item dropdown mega-dropdown">
                            <a class="nav-link dropdown-toggle waves-effect waves-dark" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-cog" aria-hidden="true" style="font-size:20px;"></i>
                            </a>
                            <div class="dropdown-menu animated bounceInDown">
                                <div class="mega-dropdown-menu row">
                                    <div class="col-lg-3 m-b-30">
                                        
                                    </div>
                                    <div class="col-lg-3  m-b-30">
                                        <h5 class="m-b-20">Facing Bug ? Contact Us</h5>
                                        <!-- Contact -->
                                        <form>
                                            <div class="form-group">
                                                <input type="text" class="form-control" id="exampleInputname1" placeholder="Enter Name"> </div>
                                            <div class="form-group">
                                                <input type="email" class="form-control" placeholder="Enter email"> </div>
                                            <div class="form-group">
                                                <textarea class="form-control" id="exampleTextarea" rows="3" placeholder="Message"></textarea>
                                            </div>
                                            <button type="submit" class="btn btn-info">Submit</button>
                                        </form>
                                    </div>
                                    
                                </div>
                            </div>
                        </li>
                        <!-- ============================================================== -->
                        <!-- End mega menu -->
                        <!-- ============================================================== -->
                        <!-- ============================================================== -->
                        <!-- Comment -->
                        <!-- ============================================================== -->
                        <li class="nav-item dropdown">
                               
                            <a class="nav-link dropdown-toggle waves-effect waves-dark" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fa fa-bell-o fa-1x" style="font-size:20px;"></i>
                            </a>
                            <div class="dropdown-menu mailbox animated bounceInDown">
                                <span class="with-arrow">
                                    <span class="bg-primary"></span>
                                </span>


                                <!-----------Notifications Panel-->
                                <ul class="list-style-none">
                                    <li>
                                        <div class="drop-title bg-primary text-white">
                                            <h4 class="m-b-0 m-t-5">Notifications</h4>
                                            
                                        </div>
                                    </li>
                                    <li>
                                        <div class="message-center notifications">
                                            <!-- Message -->
                                            <a href="javascript:void(0)" class="message-item">
                                                <span class="btn btn-danger btn-circle">
                                                    <i class="fa fa-link"></i>
                                                </span>
                                                <div class="mail-contnet">
                                                    <h5 class="message-title">Saksham Solanki <small style="color:green;">accepted your Friend Request</small></h5>
                                                    <span class="mail-desc"></span>
                                                    <span class="time">9:30 AM</span>
                                                </div>
                                            </a>

                                            <a href="javascript:void(0)" class="message-item">
                                                <span class="btn btn-default btn-circle">
                                                    <i class="fa fa-book"></i>
                                                </span>
                                                <div class="mail-contnet">
                                                    <h5 class="message-title">Saksham Solanki <small style="color:blue;"><br>has Written a Slam for you</small></h5>
                                                    <span class="mail-desc"></span>
                                                    <span class="time">9:30 AM</span>
                                                </div>
                                            </a>
                                            

                                        </div>
                                    </li>

                                    
                                </ul>
                            </div>
                        </li>


                        <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle waves-effect waves-dark" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="fa fa-user-plus fa-1x" style="font-size:22px;"></i> 
                                        
                                        
                                        <span class="badge" id="requestListBellBadge"></span>
                                        
                                </a>
                                
                                <div class="dropdown-menu mailbox animated bounceInDown">
                                    <span class="with-arrow">
                                        <span class="bg-primary"></span>
                                    </span>
                                    <ul class="list-style-none">
                                        <li>
                                            <div class="drop-title bg-primary text-white">
                                                <h4 class="m-b-0 m-t-5">Friend</h4>
                                                <span class="font-light">Requests</span>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="message-center notifications" id="friendRequestList">
                                                <!-- Message -->
    
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                        <!-- ============================================================== -->
                        <!-- End Comment -->
                        <!-- ============================================================== -->
                        

                    </ul>
                    <!-- ============================================================== -->
                    <!-- Right side toggle and nav items -->
                    <!-- ============================================================== -->
                    <ul class="navbar-nav float-right">
                        <!-- ============================================================== -->
                        <!-- Search -->
                        <!-- ============================================================== -->
                        <li class="nav-item search-box">
                            <a class="nav-link waves-effect waves-dark" href="javascript:void(0)">
                                <i class="ti-search font-16"></i>
                            </a>
                            <form action="javascript:SearchMember()" class="app-search position-absolute">
                                <input type="text" class="form-control autocomplete" id="autocomplete" placeholder="Search &amp; enter">
                                <a class="srh-btn">
                                    <i class="ti-close"></i>
                                </a>
                            </form>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link waves-effect waves-dark" href="javascript:void(0)">
                                    <i class="ti-target font-16">&nbsp;<strong style="font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif">ID : ${memberBean.registrationId}</strong></i>
                            </a>
                        </li>
                        <!-- ============================================================== -->
                        <!-- create new -->
                        <!-- ============================================================== -->
                        
                        <!-- ============================================================== -->
                        <!-- User profile and search -->
                        <!-- ============================================================== -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle text-muted waves-effect waves-dark pro-pic" href="#" data-toggle="dropdown" aria-haspopup="true"
                                aria-expanded="false">
                                <img src="${memberBean.imagePath}" alt="user" class="rounded-circle" width="31">
                            </a>
                            <div class="dropdown-menu dropdown-menu-right user-dd animated flipInY">
                                <span class="with-arrow">
                                    <span class="bg-primary"></span>
                                </span>
                                <div class="d-flex no-block align-items-center p-15 bg-primary text-white m-b-10">
                                    <div class="">
                                        <img src="${memberBean.imagePath}" alt="user" class="img-circle" width="60">
                                    </div>
                                    <div class="m-l-10">
                                        <h4 class="m-b-0">${memberBean.name}</h4>
                                        <p class=" m-b-0">${memberBean.email}</p>
                                    </div>
                                </div>
                                <a class="dropdown-item" href="profile.jsp">
                                    <i class="ti-user m-r-5 m-l-5"></i> My Profile</a>
                                <div class="dropdown-divider"></div>
                                <a href="/slamBook/logout" class="dropdown-item">
                                    <i class="fa fa-power-off m-r-5 m-l-5"></i> Logout</a>
                                <div class="dropdown-divider"></div>
                                <div class="p-l-30 p-10">
                                    <a href="profile.jsp" class="btn btn-sm btn-success btn-rounded">View Profile</a>
                                </div>
                            </div>
                        </li>
                        <!-- ============================================================== -->
                        <!-- User profile and search -->
                        <!-- ============================================================== -->
                    </ul>
                </div>
            </nav>
        </header>


        <!-- ============================================================== -->
        <!-- End Topbar header -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->
        
        <aside class="left-sidebar">
            <!-- Sidebar scroll-->
            <div class="scroll-sidebar">
                <!-- Sidebar navigation-->
                <nav class="sidebar-nav">
                    <ul id="sidebarnav">
                        <!-- User Profile-->
                        <li>
                            <!-- User Profile-->
                            <div class="user-profile dropdown m-t-20">
                                <div class="user-pic" style="margin-top:-20px;">
                                    <img src="${memberBean.imagePath}" alt="users" class="rounded-circle img-fluid" style="-webkit-box-shadow: -4px 10px 47px -32px rgba(0,0,0,0.75);
                                    -moz-box-shadow: -4px 10px 47px -32px rgba(0,0,0,0.75);
                                    box-shadow: -4px 10px 47px -32px rgba(0,0,0,0.75);" />
                                </div>
                                <div class="user-content hide-menu m-t-10">
                                    <h5 class="m-b-10 user-name font-medium">${memberBean.name}</h5>
                                    <a href="profile.jsp" class="btn btn-circle btn-sm m-r-5" id="Userdd" role="button" aria-haspopup="true"
                                        aria-expanded="false" >
                                        <i class="ti-settings"></i>
                                    </a>
                                    <a href="/slamBook/logout" title="Logout" class="btn btn-circle btn-sm">
                                        <i class="ti-power-off"></i>
                                    </a>
                                    <div class="dropdown-menu animated flipInY" aria-labelledby="Userdd">
                                        <a class="dropdown-item" href="javascript:void(0)">
                                            <i class="ti-settings m-r-5 m-l-5"></i> Account Setting</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="/slamBook/logout">
                                            <i class="fa fa-power-off m-r-5 m-l-5"></i> Logout</a>
                                    </div>
                                </div>
                            </div>
                            <!-- End User Profile-->
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="writeSlam.jsp" aria-expanded="false">
                                <i class="fa fa-book"></i>
                                <span class="hide-menu">Write Slam</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="friendRecommendation.jsp" aria-expanded="false">
                                <i class="fa fa-users"></i>
                                <span class="hide-menu">Recommendations</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="index.html" aria-expanded="false">
                                <i class="fa fa-paste"></i>
                                <span class="hide-menu">Post</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="index.html" aria-expanded="false">
                                <i class="fa fa-align-justify"></i>
                                <span class="hide-menu">Draft</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="members.jsp" aria-expanded="false">
                                <i class="ti-user"></i>
                                <span class="hide-menu">Add Friends</span>
                            </a>
                        </li>
                    </ul>
                </nav>
                <!-- End Sidebar navigation -->
            </div>
            <!-- End Sidebar scroll-->
        </aside>



        <!-- ============================================================== -->
        <!-- End Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Page wrapper  -->
        <!-- ============================================================== -->
        <div class="page-wrapper">
            <!-- ============================================================== -->
            <!-- Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <div class="page-breadcrumb">
                <div class="row">
                    <div class="col-5 align-self-center">
                        <h4 class="page-title">Write Slam</h4>
                        <div class="d-flex align-items-center">

                        </div>
                    </div>
                </div>
            </div>


            <div class="container" style="margin-top:100px;" id="friendsOption">
                <div class="input-group mb-3 col-lg-6">
                    <div class="input-group-prepend">
                      <label class="input-group-text" for="inputGroupSelect01">Select Friend</label>
                    </div>
                    <select class="custom-select" id="inputGroupSelect01">
                                              
                    </select>
                    <input type="hidden" id="friendCode">
                    <input type="hidden" id="memberCode" value="${memberBean.code}">
                    &nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-default" onclick="createForm()">Continue</button>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- End Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Container fluid  -->
            <!-- ============================================================== -->
            <div class="container" style="display:none" id="loader">
                <center><i class="fa fa-spinner fa-pulse fa-3x fa-fw"></i><h3>Creating Form....</h3></center>
            </div>
            <div class="container-fluid" style="display:none;" id="slamForm">
                <!-- ============================================================== -->
                <!-- Start Page Content -->
                <!-- ============================================================== -->
                <div class="row">
                    <!-- ============================================================== -->
                    
                    <!-- Example -->
                    <!-- ============================================================== -->
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body wizard-content">
                                <form action="#" class="validation-wizard wizard-circle m-t-40">
                                    <!-- Step 1 -->
                                    <h6>About Me</h6>
                                    <section>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="wfirstName2">Name : <span class="danger">*</span> </label>
                                                    <input type="text" class="form-control" id="name" name="name" maxlength="20" required> </div>
                                                </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="wlastName2"> Nickname : <span class="danger">*</span> </label>
                                                    <input type="text" class="form-control" id="nickname" name="nickname" maxlength="10" required> </div>
                                            </div>
                                        </div>
                                        
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="wdate2">Date of Birth :</label>
                                                    <input type="date" class="form-control" id="dob" name="dob" required> </div>
                                            </div>
                                        </div>
                                    </section>
                                    <!-- Step 2 -->
                                    <h6>Questions</h6>
                                    <section>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <label>What best describes your personality :</label>
                                                    <div class="c-inputs-stacked">
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" id="tea" name="drink" value="tea" class="custom-control-input" required>
                                                            <label class="custom-control-label" for="tea">Tea</label>
                                                        </div>
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" id="coffee" name="drink" value="coffee" class="custom-control-input" required>
                                                            <label class="custom-control-label" for="coffee">Coffee</label>
                                                        </div>
                                                    </div>
                                                    <div class="c-inputs-stacked">
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" id="morning" name="personType" value="morning" class="custom-control-input" required>
                                                            <label class="custom-control-label" for="morning">Morning Person</label>
                                                        </div>
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" id="night" name="personType" value="night" class="custom-control-input" required>
                                                            <label class="custom-control-label" for="night">Night Owl</label>
                                                        </div>
                                                    </div>
                                                    <div class="c-inputs-stacked">
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" id="single" name="relationshipStatus" value="single" class="custom-control-input" required>
                                                            <label class="custom-control-label" for="single">Single</label>
                                                        </div>
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" id="mingle" name="relationshipStatus" value="mingle" class="custom-control-input" required>
                                                            <label class="custom-control-label" for="mingle">Mingle</label>
                                                        </div>
                                                    </div>
                                                    <div class="c-inputs-stacked">
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" id="introvert" name="personBehaviour" value="introvert" class="custom-control-input" required>
                                                            <label class="custom-control-label" for="introvert">Introvert</label>
                                                        </div>
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" id="extrovert" name="personBehaviour" value="extrovert" class="custom-control-input" required>
                                                            <label class="custom-control-label" for="extrovert">Extrovert</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="webUrl3">What's my name in your phone :</label>
                                                    <input type="text" id="nameInPhone" name="nameInPhone" class="form-control" maxlength="20" required> 
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="webUrl3">Describe our relationship with a movie name :</label>
                                                    <input type="text" id="relationshipWithAMovieName" name="relationshipWithAMovieName" maxlength="25" class="form-control" required>
                                                 </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="webUrl3">A song you want to dedicate me :</label>
                                                    <input type="text" id="dedicatedSong" name="dedicatedSong" class="form-control" maxlength="25" required>
                                                 </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="shortDescription3">If i am in jail, what crime i would have done you think? :</label>
                                                    <textarea name="shortDescription" id="crime" name="crime" rows="4" class="form-control" maxlength="200" required></textarea>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="webUrl3">Something you like in me :</label>
                                                    <input type="text" id="like" name="like" class="form-control" maxlength="50" required>
                                                 </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="webUrl3">Something you don't like in me :</label>
                                                    <input type="text" id="dontLike" name="dontLike" class="form-control" maxlength="50" required>
                                                 </div>
                                            </div>
                                        </div>
                                    </section>
                                    <!-- Step 3 -->
                                    <h6>Favourites</h6>
                                    <section>
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="wint1">Most childish thing you still do :</label>
                                                    <input type="text" id="childishThing" name="childishThing" maxlength="50" class="form-control" required> 
                                                </div>
                                                <div class="form-group">
                                                    <label for="wintType1">What do most of your friends think about you that is totally untrue :</label>
                                                    <input type="text" id="friendsThought" name="friendsThought" maxlength="50" class="form-control" required> 
                                                </div>
                                                <div class="form-group">
                                                    <label for="wintType1">What is the best moment in your life so far :</label>
                                                    <input type="text" id="bestMomentInLife" name="bestMomentInLife" maxlength="50" class="form-control" required> 
                                                </div>
                                                <div class="form-group">
                                                    <label for="wintType1">What according to you is the most memorable moment we shared together :</label>
                                                    <input type="text" id="memorableMoment" name="memorableMoment" maxlength="50" class="form-control"> 
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <h4>Favourites -</h4>
                                                <div class="form-group">
                                                    <label for="wjobTitle2">Movie :</label>
                                                    <input type="text" id="movie" name="movie" class="form-control" maxlength="15" required> 
                                                </div>
                                                <div class="form-group">
                                                    <label for="wjobTitle2">TV shows/ Web series :</label>
                                                    <input type="text" id="show" name="show" class="form-control" maxlength="25" required>  
                                                </div>
                                                <div class="form-group">
                                                    <label for="wjobTitle2">Singer/Band :</label>
                                                    <input type="text" id="singer" name="singer" class="form-control" maxlength="25" required> 
                                                </div>
                                                <div class="form-group">
                                                    <label for="wjobTitle2">Food :</label>
                                                    <input type="text" id="food" name="food" class="form-control" maxlength="20" required> 
                                                </div>
                                                <div class="form-group">
                                                    <label for="wjobTitle2">Hobbies :</label>
                                                    <input type="text" id="hobbies" name="hobbies" class="form-control" maxlength="20" required> 
                                                </div>
                                                <div class="form-group">
                                                    <label for="wjobTitle2">Role Model :</label>
                                                    <input type="text" id="roleModel" name="roleModel" class="form-control" maxlength="20" required> 
                                                </div>
                                                <div class="form-group">
                                                    <label for="wjobTitle2">Weekend Activities :</label>
                                                    <input type="text" id="activities" name="activities" class="form-control" maxlength="20" required> 
                                                </div>
                                                <div class="form-group">
                                                    <label for="wjobTitle2">App :</label>
                                                    <input type="text" id="app" name="app" class="form-control" maxlength="20" required> 
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                    <!-- Step 4 -->
                                    <h6>Moments Together</h6>
                                    <section>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="wjobTitle2">Your Dream :</label>
                                                    <textarea name="dream" id="dream" rows="4" class="form-control" maxlength="200" required></textarea>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="wjobTitle2">Any Confession or Message for me ? </label>
                                                    <textarea name="confession" id="confession" rows="4" maxlength="200" class="form-control"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="wjobTitle2">Create a custom question(?) :  </label>
                                                    <textarea name="customQuestion" id="customQuestion" rows="2" maxlength="100" class="form-control"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label for="wjobTitle2">PS : </label>
                                                        <input type="text" name="ps" id="ps" maxlength="80" class="form-control">
                                                    </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="wjobTitle2">Upload a memorable pic :</label>
                                                    <div class="cover">
                                                        <div class="image-preview text-center" id="image-preview">
                                                            <img src="" alt="Preview" id="image-preview-image">
                                                        </div>
                                                        <div id="imgUploaded" style="display:none;">
                                                        </div>
                                                        <div class="upload-image">
                                                            <input type="file" name="" id="selectFile" accept="image/x-png,image/jpeg,image/jpg,image/png" style="display:none;">
                                                            <label for="selectFile">
                                                                <!-- SVG content in label -->
                                                                <svg width="56" height="56">
                                                                  <!-- Background -->
                                                                  <circle cx="28" cy="28" r="28" fill="red" id="uploadCircle"/>
                                                              
                                                                  <!-- Icon -->
                                                                  <!-- From Material Design -->
                                                                  <path 
                                                                    class="icon" 
                                                                    d="M9 16h6v-6h4l-7-7-7 7h4zm-4 2h14v2H5z" 
                                                                    fill="white" 
                                                                    transform="translate( 15, 15 )"/>
                                                              
                                                                  <!-- Animated completion indicator -->
                                                                  <!-- Pie slice -->
                                                                  <path 
                                                                    class="pie" 
                                                                    d="M 28 28 L 28 0 A 28 28 1 0 1 28 0 z" 
                                                                    fill="red" 
                                                                    opacity="0"/>
                                                              
                                                                  <!-- Numeric completion indicator -->
                                                                  <text 
                                                                    x="28" 
                                                                    y="28" 
                                                                    text-anchor="middle" 
                                                                    fill="white" 
                                                                    font-size="14" 
                                                                    font-weight="700" 
                                                                    dominant-baseline="central" 
                                                                    opacity="0">0%</text>
                                                                </svg>
                                                              </label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <script type="text/javascript">
                                                var selectFile=document.getElementById("selectFile");
                                                var imageBox=document.getElementById("image-preview");
                                                imageBox.style.display="none";
                                                selectFile.addEventListener("change",function(){
                                                     var file= this.files[0];
                                                     if(file)
                                                     {
                                                         var reader=new FileReader();
                                                         reader.addEventListener("load",function(){
                                                              imageBox.style.display="block";
                                                              var imageView= document.getElementById("image-preview-image");
                                                              imageView.setAttribute("src",this.result);
                                                              $("#imgUploaded").html(this.result);
                                                         });
                                                         reader.readAsDataURL(file);
                                                     }
                                                     else
                                                     {
                                                        imageView.setAttribute("src","" );
                                                     }
                                                });
                                            </script>
                                            <div class="col-md-6">
                                                
                                            </div>
                                        </div>
                                    </section>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- End PAge Content -->
                <!-- ============================================================== -->
                <!-- ============================================================== -->
                <!-- Right sidebar -->
                <!-- ============================================================== -->
                <!-- .right-sidebar -->
                <!-- ============================================================== -->
                <!-- End Right sidebar -->
                <!-- ============================================================== -->
            </div>
            <!-- ============================================================== -->
            <!-- End Container fluid  -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
        </div>
        <!-- ============================================================== -->
        <!-- End Page wrapper  -->
        <!-- ============================================================== -->
    </div>
    <!-- ============================================================== -->
    <!-- End Wrapper -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    
    <!-- ============================================================== -->
    <!-- All Jquery -->
    <!-- ============================================================== -->
    <script src="assets/libs/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="assets/libs/popper.js/dist/umd/popper.min.js"></script>
    <script src="assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- apps --><script src="dist/js/app.min.js"></script>
    <script src="dist/js/app.init.light-sidebar.js"></script>
    <script src="dist/js/app-style-switcher.js"></script>
    <!-- slimscroscrollbar JavaScript -->
    <script src="assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
    <script src="assets/extra-libs/sparkline/sparkline.js"></script>
    <script src="dist/js/waves.js"></script>
    <script src="dist/js/sidebarmenu.js"></script>
    <script src="dist/js/custom.js"></script>
    <script src="assets/libs/jquery-steps/build/jquery.steps.min.js"></script>
    <script src="assets/libs/jquery-validation/dist/jquery.validate.min.js"></script>
        
    <script>
    //Vertical Steps

    $("#example-vertical").steps({
        headerTag: "h3",
        bodyTag: "section",
        transitionEffect: "slideLeft",
        stepsOrientation: "vertical"
    });

    //Custom design form example
    $(".tab-wizard").steps({
        headerTag: "h6",
        bodyTag: "section",
        transitionEffect: "fade",
        titleTemplate: '<span class="step">#index#</span> #title#',
        labels: {
            finish: "Submit"
        },
        onFinished: function(event, currentIndex) {
            swal("Form Submitted!", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed lorem erat eleifend ex semper, lobortis purus sed.");

        }
    });


    var form = $(".validation-wizard").show();

    $(".validation-wizard").steps({
        headerTag: "h6",
        bodyTag: "section",
        transitionEffect: "fade",
        titleTemplate: '<span class="step">#index#</span> #title#',
        labels: {
            finish: "Submit Slam",
        },
        onStepChanging: function(event, currentIndex, newIndex) {
            return currentIndex > newIndex || !(3 === newIndex && Number($("#age-2").val()) < 18) && (currentIndex < newIndex && (form.find(".body:eq(" + newIndex + ") label.error").remove(), form.find(".body:eq(" + newIndex + ") .error").removeClass("error")), form.validate().settings.ignore = ":disabled,:hidden", form.valid())
        },
        onFinishing: function(event, currentIndex) {
            return form.validate().settings.ignore = ":disabled", form.valid()
        },
        onFinished: function(event, currentIndex) {
            submitForm();
        }   
    }), $(".validation-wizard").validate({
        ignore: "input[type=hidden]",
        errorClass: "text-danger",
        successClass: "text-success",
        highlight: function(element, errorClass) {
            $(element).removeClass(errorClass)
        },
        unhighlight: function(element, errorClass) {
            $(element).removeClass(errorClass)
        },
        errorPlacement: function(error, element) {
            error.insertAfter(element)
        },
        rules: {
            email: {
                email: !0
            }
        }
    })
    </script>
</body>
</html>