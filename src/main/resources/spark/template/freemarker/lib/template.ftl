<#import "mainwrapper.ftl" as wrapper> 

<#macro page>
	<@wrapper.wrapper pageTitle = title>
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
	                                     	<#if topBarElement.noElementsMessage??>
	                                              <center>${topBarElement.noElementsMessage}.</center>
											</#if>
	                                      
	                                     
	                                    
	                                        <li class="divider"></li>
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
											<#if topBarElement??>
	                                       		 <a class="text-center" href="${topBarElement.link}">
	                                            	<strong>${topBarElement.bottomText}</strong>
	                                           	 	<i class="icon-angle-right"></i>
	                                        	 </a>
											</#if>
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
																		<span class="pull-right" style="margin-right: 20px;">
	                           												<i class="icon-angle-right"></i>
	                       												</span>
	                       												
	                       												
																	<#if subMenue.name??>
																		<#if subMenue.iconPath??>
																			<!-- vielleicht icon laden incl. -->
	                            										</#if>
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
	               	<!--PAGE CONTENT add page here!-->
	               		<#-- This processes the enclosed content:  -->
	    					<#nested>
	                <!--END PAGE CONTENT -->
	            </div>
	        </div>
	    </div>
	</@wrapper.wrapper> 
	    
</#macro>