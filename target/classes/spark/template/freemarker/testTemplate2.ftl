<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD-->
<head>
   
     <meta charset="UTF-8" />
    <title>${title} | OBook</title>
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
                    
                    <#if topBarElements??>
                        <#list topBarElements as topBarElement>
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                    <#if topBarElement.shownNumber??>
                                            <span class="label label-success">${topBarElement.shownNumber}</span>    <i class=${topBarElement.symbol}></i>&nbsp; <i class="icon-chevron-down"></i>
                                    </#if>
                                </a>
                                
                                <ul class="dropdown-menu dropdown-messages">
                                     <#if topBarElement.topBarElements??>
                                        <center>Ungelesene Nachrichten.</center>
                                     
                                    
                                        <li class="divider"></li>
                                        <!-- Infos oben rechts erster kasten!
                                             Hier wird etwas per loop angezeigt -> todo loop implementieren und was soll angezeigt werden?
                                        -->
                                        <#list topBarElement.topBarElements as element>
                                            <li>
                                                <a href="#">
                                                    <div>
                                                        <strong>${element.topMessage}</strong>
                                                        <span class="pull-right text-muted">
                                                            <em>vor ${element.time} Minute[n]</em>
                                                        </span>
                                                    </div>
                                                    <div>${element.message}
                                                    </div>
                                                </a>
                                            </li>
                                            <li class="divider"></li>
                                        </#list>
                                    </#if>
                                    <li>
                                        <a class="text-center" href="#">
                                            <strong>Alle Nachrichten lesen</strong>
                                            <i class="icon-angle-right"></i>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                        </#list>
                    </#if>       
                
                    <!--END MESSAGES SECTION -->

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
                            <li><a href="/logout"><i class="icon-signout"></i> Ausloggen </a>
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
                </div>
                
               	<!--PAGE CONTENT add page here!-->
               		       <div id="content">

         <div class="panel panel-default">
                        <div class="panel-heading">
                            Horizontal Wizard
                        </div>
                        <div class="panel-body">
                            <div id="wizard" >
                <h2> Personal </h2>
                <section>
                           <form role="form">
                                        <div class="form-group" name="firstName">
                                            <label>First Name</label>
                                            <input class="form-control" />
                                            <p class="help-block">Example block-level help text here.</p>
                                        </div>
                               
                                        <div class="form-group" name="firstName">
                                            <label>First Name</label>
                                            <input class="form-control" />
                                            <p class="help-block">Example block-level help text here.</p>
                                        </div>
                                       
                                       <div class="form-group">
                                            <label>Last Name</label>
                                            <input class="form-control" />
                                            <p class="help-block">Example block-level help text here.</p>
                                        </div>
                                        <div class="form-group">
                                            <label>Email</label>
                                            <input class="form-control" />
                                            <p class="help-block">Example block-level help text here.</p>
                                        </div>
                                        <div class="form-group">
                                            <label>Retype Email</label>
                                            <input class="form-control" />
                                            <p class="help-block">Example block-level help text here.</p>
                                        </div>                                                            
                                    </form>
                    
                </section>

                <h2> Login </h2>
                <section>
                   <form role="form">
                                        <div class="form-group">
                                            <label>Username</label>
                                            <input class="form-control" />
                                            <p class="help-block">Example block-level help text here.</p>
                                        </div>
                                        <div class="form-group">
                                            <label>Password</label>
                                            <input class="form-control" />
                                            <p class="help-block">Example block-level help text here.</p>
                                        </div>
                                       <div class="form-group">
                                            <label>Retype Password</label>
                                            <input class="form-control" />
                                            <p class="help-block">Example block-level help text here.</p>
                                        </div>
                                        <div class="form-group">
                                            <label> Security Code </label>
                                            <input class="form-control" />
                                            <p class="help-block">Example block-level help text here.</p>
                                        </div>
                                       
                                    </form>
                </section>

                <h2>Extras </h2>
                <section>
                      <form role="form">
                                        <div class="form-group">
                                            <label> Occupation </label>
                                            <input class="form-control" />
                                            <p class="help-block">Example block-level help text here.</p>
                                        </div>
                                        <div class="form-group">
                                            <label> Qualification </label>
                                            <input class="form-control" />
                                            <p class="help-block">Example block-level help text here.</p>
                                        </div>
                                       <div class="form-group">
                                            <label> Age </label>
                                            <input class="form-control" />
                                            <p class="help-block">Example block-level help text here.</p>
                                        </div>
                                        <div class="form-group">
                                            <label> Notes </label>
                                            <input class="form-control" />
                                            <p class="help-block">Example block-level help text here.</p>
                                        </div>
                                       
                                    </form>
                </section>

                <h2>Agreement</h2>
                <section>
                    <p style="text-align:justify;color:gray;">
                        <b> Agreement & Declaration</b> <br /><br />
                        Morbi ornare tellus at elit ultrices id dignissim lorem elementum. Sed eget nisl at justo condimentum dapibus. Fusce eros justo, 
                        pellentesque non euismod ac, rutrum sed quam. Ut non mi tortor. Vestibulum eleifend varius ullamcorper. Aliquam erat volutpat.
                        <br />  <br /> 
                        Donec diam massa, porta vel dictum sit amet, iaculis ac massa. Sed elementum dui commodo lectus sollicitudin in auctor mauris 
                        venenatis. Quisque at sem turpis, id sagittis diam. Suspendisse malesuada eros posuere mauris vehicula vulputate. Aliquam sed sem tortor. 
                        Quisque sed felis ut mauris feugiat iaculis nec ac lectus. Sed consequat vestibulum purus, imperdiet varius est pellentesque vitae. 
                        Suspendisse consequat cursus eros, vitae tempus enim euismod non. Nullam ut commodo tortor.
                        <br />
                    </p>
                </section>


        </div>
                <!--END PAGE CONTENT -->
            </div>
        </div>
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
