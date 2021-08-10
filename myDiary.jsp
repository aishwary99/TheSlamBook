<%@ taglib uri='/WEB-INF/tlds/CustomTags.tld' prefix='sb'%>
<sb:SecurityGuard/>
<jsp:useBean id='memberBean' scope='session' class='com.slam.book.beans.MemberBean'/>
<%@page import="java.util.*" %>
<%@page import="com.slam.book.dao.*" %>
<%@page import="com.slam.book.beans.*"%>
<!DOCTYPE html>
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
    <title>My Diary</title>
    <!-- Custom CSS -->
    <link href="dist/css/style.min.css" rel="stylesheet">
    <script src="customJs/style.js"></script>
    <script src="customJs/jquery.autocomplete.min.js"></script>
    <link rel="stylesheet" href="customCss/style.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
    <link rel="stylesheet" href="customCss/jqueryUi.css">
    <script type="text/javascript" src="customJs/jQuery1.12.4.js"></script>
    <script type="text/javascript" src="customJs/jQueryUI.js"></script>
    
    <!-------Custom JS-->
    <link rel="stylesheet" href="customCss/jquery.badge.css">
    <script src="customJs/jquery.badge.js"></script>
    <script src="customJs/friendUtility.js"></script>
    <script src="customJs/diary.js"></script>
    <link rel="stylesheet" href="customCss/diary.css">
    <link rel="stylesheet" href="font-awesome/css/font-awesome.css">
    <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
    <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
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
                                <div class="user-content-menu m-t-10">
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
                                <span class=-menu">Write Slam</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="friendRecommendation.jsp" aria-expanded="false">
                                <i class="fa fa-users"></i>
                                <span class=-menu">Recommendations</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="myDiary.jsp" aria-expanded="false">
                                <i class="fa fa-paste"></i>
                                <span class=-menu">My Diary</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="members.jsp" aria-expanded="false">
                                <i class="ti-user"></i>
                                <span class=-menu">Add Friends</span>
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
                            <div id="toast">
                                    <div id="img"><p class="ti-comments-smiley" style="font-size: 24px;"></p></div>
                                    <div id="desc">Welcome ${memberBean.name} !</div>
                                </div>
                    </div>
                    <div class="col-7 align-self-center">
                        <div class="d-flex no-block justify-content-end align-items-center">
                            
                        </div>
                    </div>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- End Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Container fluid  -->
            <!-- ============================================================== -->
            <div class="container-fluid">
                <!-- ============================================================== -->
                <!-- Start Page Content -->
                <!-- ============================================================== -->
                <div class="row">
                    <div class="col-12">
                        
                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="card-title"><i class="fa fa-slack" aria-hidden="true"></i>
                                            Slam</h4>
                                        <!-- Column -->

                                        <div class="container" id="friendSelector">
                                            <div class="row">
                                                <div class="col-lg-12">
                                                    <center>
                                                        <div class="input-group mb-3 col-lg-6" style="margin: auto;">
                                                            <div class="input-group-prepend">
                                                              <label class="input-group-text" for="slamFriends">Select Friend</label>
                                                            </div>
                                                            <select class="custom-select" id="slamFriends">
                                                                <!--------Friends List -------->
                                                            </select>
                                                            <input type="hidden" id="friendCode">
                                                            <input type="hidden" id="memberCode" value="${memberBean.code}">
                                                            &nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-default" onclick="showForm()">Show</button>
                                                    </center>
                                                </div>
                                            </div>
                                        </div>
                                            
                                        <div class="container-fluid formContainer">
                                            <div class="row formRow" style="display: none;">
                                                <div class="col-lg-12 flex" id="friendShowCase" style="display: none;">
                                                    <div class="col-lg-6 flex">
                                                        <p><i class="fa fa-pencil-square" aria-hidden="true"></i>
                                                            Author : </p>
                                                            <h4 id="friendName">
                                                            </h4>
                                                    </div>                                             
                                                </div>
                                                <div class="col-lg-12 flex" id="friendDetails" style="display: none;">
                                                    <div class="col-lg-4 flex">
                                                        <p><i class="fa fa-sort-numeric-desc" aria-hidden="true"></i>
                                                            Name : </p>
                                                        <p id="name"></p>
                                                    </div>
                                                    <div class="col-lg-4 flex">
                                                        <p><i class="fa fa-user-secret" aria-hidden="true"></i>
                                                            Nickname : </p>
                                                        <p id="nickname"></p>
                                                    </div>
                                                    <div class="col-lg-4 flex">
                                                        <p><i class="fa fa-calendar" aria-hidden="true"></i>
                                                            DOB : </p>
                                                        <p id="birthday"></p>
                                                    </div>
                                                </div>
                                                <!--------Personal details section ends here-->
                                                <div class="col-lg-12 flex" id="personality" style="display: none;">
                                                    <div class="col-lg-3 col-xs-12">
                                                        <p><i class="fa fa-male" aria-hidden="true"></i>
                                                            What describes your personality : </p>
                                                    </div>
                                                    <div class="col-lg-9 col-xs-12">
                                                        <table align="Left" border="0">
                                                            <tr>
                                                                <td style="width: 150px;"><input type="radio" id="coffee">&nbsp;&nbsp;Cold Coffee</td>
                                                                <td style="width: 150px;"><input type="radio" id="tea">&nbsp;&nbsp;Tapri ki Chai</td>
                                                            </tr>
                                                            <tr>
                                                                <td style="width: 150px;"><input type="radio" id="morning">&nbsp;&nbsp;Morning Person</td>
                                                                <td style="width: 150px;"><input type="radio" id="night">&nbsp;&nbsp;Night Owl</td>
                                                            </tr>
                                                            <tr>
                                                                <td style="width: 150px;"><input type="radio" id="single">&nbsp;&nbsp;Single</td>
                                                                <td style="width: 150px;"><input type="radio" id="mingle">&nbsp;&nbsp;Mingle</td>
                                                            </tr>
                                                            <tr>
                                                                <td style="width: 150px;"><input type="radio" id="introvert">&nbsp;&nbsp;Introvert</td>
                                                                <td style="width: 150px;"><input type="radio" id="extrovert">&nbsp;&nbsp;Extrovert</td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </div>
                                                

                                                <div class="col-lg-12 flex" id="nameAndRelation" style="display: none;">
                                                    <div class="col-lg-5 flex">
                                                        <p><i class="fa fa-mobile" aria-hidden="true"></i>
                                                            My Name in your Phone :</p>
                                                        <p id="nameInPhone"></p>
                                                    </div>
                                                    <div class="col-lg-7 flex">
                                                        <p><i class="fa fa-retweet" aria-hidden="true"></i>
                                                            Describe our relationship with a movie name :</p>
                                                        <p id="relationship"></p>
                                                    </div>
                                                </div>

                                                <div class="col-lg-12" id="songDivison" style="display: none;">
                                                    <div class="col-lg-12 flex" id="songQ">
                                                        <div class="col-lg-4 col-xs-12">
                                                            <p><i class="fa fa-music" aria-hidden="true"></i>
                                                                A song you want to dedicate me : </p>
                                                        </div>
                                                        <div class="col-lg-8 col-xs-12">
                                                            <p id="song"></p>    
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12 flex" id="jailDivison" style="display: none;">
                                                        <div class="col-lg-5">
                                                         <p><i class="fa fa-lock" aria-hidden="true"></i>
                                                             If i am in jail , what crime i would have done you think (?)  </p>
                                                        </div>
                                                        <div class="col-lg-7 textJustify">
                                                         <p id="jail"></p>    
                                                        </div>
                                                </div>

                                                <div class="col-lg-12 flex aos-item" data-aos-delay="50"
                                                data-aos-duration="950"
                                                data-aos-easing="ease-in-out" data-aos="fade-up" id="likeUnlike" style="display: none;">
                                                    <div class="col-lg-6 textJustify">
                                                        <p><i class="fa fa-thumbs-up" aria-hidden="true"></i>
                                                            Something you like in me :  </p>
                                                        <p id="like"></p>
                                                    </div>
                                                    <div class="col-lg-6 textJustify">
                                                        <p><i class="fa fa-thumbs-down" aria-hidden="true"></i>
                                                            Something you don't like in me :</p>    
                                                        <p id="unlike"></p>
                                                    </div>
                                                </div>
                                                
                                                <div class="col-lg-12 flex" data-aos-delay="50"
                                                data-aos-duration="950"
                                                data-aos-easing="ease-in-out" data-aos="fade-up" id="thinking" style="display: none;">
                                                    <div class="col-lg-6 textJustify">
                                                        <p><i class="fa fa-child" aria-hidden="true"></i>
                                                            Most Childish thing you still do : </p>
                                                        <p id="childishThing"></p>
                                                    </div>
                                                    <div class="col-lg-6 textJustify">
                                                        <p><i class="fa fa-users" aria-hidden="true"></i>
                                                            What do most of your friends think about you i.e totally untrue :  </p>
                                                        <p id="untrue"></p>
                                                    </div>
                                                </div>

                                                <div class="col-lg-12 flex" data-aos-delay="50"
                                                data-aos-duration="950"
                                                data-aos-easing="ease-in-out" data-aos="fade-up" id="bestMoment" style="display: none;">
                                                    <div class="col-lg-6 textJustify">
                                                        <p><i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                                            What is the best moment in your life so far : </p>
                                                        <p id="moment"></p>
                                                    </div>
                                                    <div class="col-lg-6 textJustify">
                                                        <p><i class="fa fa-share-alt-square" aria-hidden="true"></i>
                                                            What according to you is the best memorable moment we shared together(?) :  </p>
                                                        <p id="memory"></p>
                                                    </div>
                                                </div>

                                                <div class="col-lg-12 flex" data-aos-delay="50"
                                                data-aos-duration="950"
                                                data-aos-easing="ease-in-out" data-aos="fade-up" id="favourites" style="display: none;">
                                                    <div class="col-lg-3 col-xs-12">
                                                        <p><i class="fa fa-star" aria-hidden="true"></i>
                                                            Favourites :</p>
                                                    </div>
                                                    <div class="col-lg-9 col-xs-12">
                                                        <table align="Left" border="0">
                                                            <tr>
                                                                <td style="width: 150px;">&nbsp;&nbsp;<i class="fa fa-video-camera" aria-hidden="true"></i>
                                                                    Movie : </td>
                                                                <td style="width: 150px;" id="movie">&nbsp;&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td style="width: 150px;">&nbsp;&nbsp;<i class="fa fa-television" aria-hidden="true"></i>
                                                                    TV/WebSeries : </td>
                                                                <td style="width: 150px;" id="show">&nbsp;&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td style="width: 150px;">&nbsp;&nbsp;<i class="fa fa-wpexplorer" aria-hidden="true"></i>
                                                                    Singer/Band : </td>
                                                                <td style="width: 150px;" id="singer"&nbsp;&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td style="width: 150px;">&nbsp;&nbsp;<i class="fa fa-cutlery" aria-hidden="true"></i>
                                                                    Food : </td>
                                                                <td style="width: 150px;" id="food">&nbsp;&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td style="width: 150px;">&nbsp;&nbsp;<i class="fa fa-bed" aria-hidden="true"></i>
                                                                    Hobbies : </td>
                                                                <td style="width: 150px;" id="hobbies">&nbsp;&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td style="width: 150px;">&nbsp;&nbsp;<i class="fa fa-rocket" aria-hidden="true"></i>
                                                                    Role Model : </td>
                                                                <td style="width: 150px;" id="roleModel">&nbsp;&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td style="width: 150px;">&nbsp;&nbsp;<i class="fa fa-free-code-camp" aria-hidden="true"></i>
                                                                    Activities : </td>
                                                                <td style="width: 150px;" id="activites">&nbsp;&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td style="width: 150px;">&nbsp;&nbsp;<i class="fa fa-bars" aria-hidden="true"></i>
                                                                    App : </td>
                                                                <td style="width: 150px;" id="app">&nbsp;&nbsp;</td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </div>

                                                <div class="col-lg-12 flex" data-aos-delay="50"
                                                data-aos-duration="950"
                                                data-aos-easing="ease-in-out" data-aos="fade-up" id="dreamAndConfession" style="display: none;">
                                                    <div class="col-lg-6 textJustify">
                                                        <p><i class="fa fa-bar-chart" aria-hidden="true"></i>
                                                            Your Dream : </p>
                                                        <p id="dream"></p>
                                                    </div>                                                    
                                                    <div class="col-lg-6 textJustify">
                                                        <p><i class="fa fa-comment-o" aria-hidden="true"></i>
                                                            Any Confession or Message for me(?) :  </p>
                                                        <p id="confession"></p>
                                                    </div>                                                    
                                                </div>
                                                
                                                <div class="col-lg-12 flex textJustify" data-aos-delay="50"
                                                data-aos-duration="950"
                                                data-aos-easing="ease-in-out" data-aos="fade-up" id="customQuestion" style="display: none;">
                                                    <div class="col-lg-2">
                                                        <p><i class="fa fa-question-circle" aria-hidden="true"></i>
                                                            Custom Question : </p>
                                                    </div>
                                                    <div class="col-lg-10">
                                                        <p id="question"></p>
                                                    </div>
                                                    
                                                </div>

                                                <div class="col-lg-12 flex" data-aos-delay="50"
                                                data-aos-duration="950"
                                                data-aos-easing="ease-in-out" data-aos="fade-up" id="moments" style="display: none;">
                                                    <div class="col-lg-2">
                                                        <p><i class="fa fa-picture-o" aria-hidden="true"></i>
                                                            Moments Together : </p>
                                                    </div>
                                                    <div class="col-lg-10" id="pictureTogether">
                                                        
                                                    </div>                                                    
                                                </div>

                                                <div class="col-lg-12" style="display: none;" id="nextButton">
                                                    <button type="button" id="next" class="btn btn-danger btn-rounded">Next</button>
                                                </div>

                                            </div> 
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
            <!-- footer -->
            <!-- ============================================================== -->
         
            <!-- ============================================================== -->
            <!-- End footer -->
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
    <!-- customizer Panel -->
    <!-- ============================================================== -->
    
    
    <div class="chat-windows"></div>
    <!-- ============================================================== -->
    <!-- All Jquery -->
    <!-- ============================================================== -->
    <script>
        AOS.init();
    </script>
    <script src="assets/libs/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="assets/libs/popper.js/dist/umd/popper.min.js"></script>
    <script src="assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- apps -->
    <script src="dist/js/app.min.js"></script>
    <script src="dist/js/app.init.light-sidebar.js"></script>
    <script src="dist/js/app-style-switcher.js"></script>
    <!-- slimscrollbar scrollbar JavaScript -->
    <script src="assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
    <script src="assets/extra-libs/sparkline/sparkline.js"></script>
    <!--Wave Effects -->
    <script src="dist/js/waves.js"></script>
    <!--Menu sidebar -->
    <script src="dist/js/sidebarmenu.js"></script>
    <!--Custom JavaScript -->
    <script src="dist/js/custom.min.js"></script>
</body>
</html>
