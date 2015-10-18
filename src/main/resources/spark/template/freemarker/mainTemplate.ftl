<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD-->
<head>
   
     <meta charset="UTF-8" />
    <title>OBook | ${title}</title>
     <meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
     <!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
    <!-- GLOBAL STYLES -->
    <!-- GLOBAL STYLES -->
    <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" href="assets/css/main.css" />
    <link rel="stylesheet" href="assets/css/theme.css" />
    <link rel="stylesheet" href="assets/css/MoneAdmin.css" />
    <link rel="stylesheet" href="assets/plugins/Font-Awesome/css/font-awesome.css" />
    <!--END GLOBAL STYLES -->

    <!-- PAGE LEVEL STYLES -->
    <!-- END PAGE LEVEL  STYLES -->
       <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
    <!-- END  HEAD-->
    <!-- BEGIN BODY-->
<body class="padTop53 " >

     <!-- MAIN WRAPPER -->
    <div id="wrap">
         <!-- HEADER SECTION -->
        <div id="top">
            <nav class="navbar navbar-inverse navbar-fixed-top " style="padding-top: 10px;">
                <a data-original-title="Show/Hide Menu" data-placement="bottom" data-tooltip="tooltip" class="accordion-toggle btn btn-primary btn-sm visible-xs" data-toggle="collapse" href="#menu" id="menu-toggle">
                    <i class="icon-align-justify"></i>
                </a>
                </header>
                <ul class="nav navbar-top-links navbar-right">

                    <!-- MESSAGES SECTION -->
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                          	<#if pendingMessages??>
                          		<span class="label label-success">${pendingMessages}</span>    <i class="icon-envelope-alt"></i>&nbsp; <i class="icon-chevron-down"></i>
                       		<#else>
                       			<span class="label label-success">0</span>    <i class="icon-envelope-alt"></i>&nbsp; <i class="icon-chevron-down"></i>
                       		</#if>
                        </a>

                        <ul class="dropdown-menu dropdown-messages">
                            <center>Ungelesene Nachrichten.</center>
                            <li class="divider"></li>
                            <!-- Infos oben rechts erster kasten!
                                 Hier wird etwas per loop angezeigt -> todo loop implementieren und was soll angezeigt werden?
                            -->
                            <li>
                                <a href="#">
                                    <div>
                                        <strong>Didi Frog</strong>
                                        <span class="pull-right text-muted">
                                            <em>vor 2 minuten</em>
                                        </span>
                                    </div>
                                    <div>Das System klappt klasse!
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            
                            <li>
                                <a class="text-center" href="#">
                                    <strong>Alle Nachrichten lesen</strong>
                                    <i class="icon-angle-right"></i>
                                </a>
                            </li>
                        </ul>

                    </li>
                    <!--END MESSAGES SECTION -->

                    <!--TASK SECTION -->
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <span class="label label-danger">1</span>   <i class="icon-tasks"></i>&nbsp; <i class="icon-chevron-down"></i>
                        </a>

                        <ul class="dropdown-menu dropdown-tasks">
                            <li>
                                <a href="#">
                                    <div>
                                        <p>
                                            <strong> Aufträge </strong>
                                            <span class="pull-right text-muted">40% Aufträge fertig</span>
                                        </p>
                                        <div class="progress progress-striped active">
                                            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                                <span class="sr-only">40% Aufträge fertig (success)</span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                                
                            <li>
                                <a class="text-center" href="#">
                                    <strong>Alle Aufgaben ansehen</strong>
                                    <i class="icon-angle-right"></i>
                                </a>
                            </li>
                        </ul>

                    </li>
                    <!--END TASK SECTION -->

                    <!--ALERTS SECTION -->
                    <li class="chat-panel dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <span class="label label-info">1</span>   <i class="icon-comments"></i>&nbsp; <i class="icon-chevron-down"></i>
                        </a>

                        <ul class="dropdown-menu dropdown-alerts">

                            <li>
                                <a href="#">
                                    <div>
                                        <i class="icon-comment" ></i> Neue Aufträge [5]
                                    <span class="pull-right text-muted small"> vor 1 Minute/n</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            
                            <li>
                                <a class="text-center" href="#">
                                    <strong>Alle Meldungen ansehen</strong>
                                    <i class="icon-angle-right"></i>
                                </a>
                            </li>
                        </ul>

                    </li>
                    <!-- END ALERTS SECTION -->

                    <!--ADMIN SETTINGS SECTIONS -->

                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="icon-user "></i>&nbsp; <i class="icon-chevron-down "></i>
                        </a>

                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="#"><i class="icon-user"></i> Profil ansehen </a>
                            </li>
                            <li><a href="#"><i class="icon-gear"></i> Einstellungen </a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="login.html"><i class="icon-signout"></i> Ausloggen </a>
                            </li>
                        </ul>

                    </li>
                    <!--END ADMIN SETTINGS -->
                </ul>

            </nav>

        </div>
        <!-- END HEADER SECTION -->



        <!-- MENU SECTION -->
       <div id="left">
            <div class="media user-media well-small">
                <br />
                <div class="media-body">
                    <h5 class="media-heading"> ${user.username} </h5>
                    <ul class="list-unstyled user-info">
                        <li>
                             <a class="btn btn-success btn-xs btn-circle" style="width: 10px;height: 12px;"></a>${user.loginlevel}
                        </li>
                       
                    </ul>
                </div>
                <br />
            </div>

            <ul id="menu" class="collapse">

				<#if sideBarElements??>
  					<li class="panel">

						<#list sideBarElements as sideBarElement>
							<#if sideBarElement.linkPath??>
								<a href=${sideBarElement.linkPath} >
                        			<i class=${sideBarElement.iconPath}></i> ${sideBarElement.name}
                    			</a> 
							<#else>
								 <a href="#" data-parent="#menu" data-toggle="collapse" class="accordion-toggle" data-target="#${sideBarElement.name}">
                       			 	<i class=${sideBarElement.iconPath}> </i> ${sideBarElement.name}
										<span class="pull-right">
                          					<i class="icon-angle-left"></i>
                        				</span>
                        		</a>
                       					<ul class="collapse" id=${sideBarElement.name}>
	                       					<#if sideBarElement.subMenues??>
	                       						<#list sideBarElement.subMenues as subMenue>
	                       							<#if subMenue.linkPath??>
														<li class="">
															<a href=${subMenue.linkPath}>
																<i class="icon-angle-right"></i> 
																<#if subMenue.name??>
																	${subMenue.name}
																</#if>
															</a>
														</li>
													</#if>
	                       						</#list>
											</#if>
										</ul>			
							</#if>
  						</#list>

					 </li>
  				</#if>
        </div>
        <!--END MENU SECTION -->


        <!--PAGE CONTENT -->
        <div id="content">

            <div class="inner" style="min-height:1200px;">
                <div class="row">
                    <div class="col-lg-12">


                        <h2>Here comes the implemented page!</h2>


                    </div>
                </div>

                <hr/>
            </div>




        </div>
       <!--END PAGE CONTENT -->


    </div>

     <!--END MAIN WRAPPER -->

   <!-- FOOTER -->
    <div id="footer">
        <p>&copy;  &nbsp;2015 L. Kannenberg &amp; L.Peer &nbsp;</p>
    </div>
    <!--END FOOTER -->
     <!-- GLOBAL SCRIPTS -->
    <script src="assets/plugins/jquery-2.0.3.min.js"></script>
     <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/plugins/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    <!-- END GLOBAL SCRIPTS -->
</body>
    <!-- END BODY-->
    
</html>
