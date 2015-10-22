<#import "/lib/mainwrapper.ftl" as wrapper> 

<@wrapper.wrapper pageTitle = title>
   <!-- PAGE CONTENT --> 
    <div class="container">
	    <div class="text-center">
			 <div class="row">
	              <div class="col-lg-12">
	                  <h1> Login für die Bücherverwaltung </h1>
	              </div>
	         </div>
	    </div>
	    <p style="margin-top:12px;"><p>
	    
	    <style>
			form {
				margin-left: auto;
   				margin-right: auto;
				max-width: 600px;
				min-width: 150px;
			}
			</style>
			
	    <div class="tab-content" style="max-width: 600px; margin-left: auto; margin-right: auto; min-width: 150px;">
	        <div id="login" class="tab-pane active">
	            <form action="/login" class="form-signin" method ="post">
	                <p class="text-muted text-center btn-block btn btn-primary btn-rect">
	                    Enter your username and password
	                </p>
	                <p style="margin-top:6px;"><p>
	                <input name="username" type="username" placeholder="Nutzername" class="form-control" />
	                <p style="margin-top:2px;"><p>
	                <input name="password" type="password" placeholder="Password" class="form-control" />
	                
	                <#if error??>
	                	<p class="text-muted text-center btn-block btn btn-danger btn-rect">
	                    	${error}
	               	 	</p><p>
	                </#if>
	
					<p>
	                <button class="btn text-muted text-center btn-danger" type="submit">Sign in</button>
	            </form>
	        </div>
	    </div>
	<p style="margin-top:200px;"><p>
	</div>
	<div>
	</div>
</@wrapper.wrapper> 