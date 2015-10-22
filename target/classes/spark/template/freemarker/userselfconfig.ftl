<#import "/lib/template.ftl" as template> 
<#import "/lib/smallHelpers.ftl" as helpers>

<@template.page>
	<@helpers.subPageTitle pageTitle = title />
	 				<p class="text-muted text-center btn-block btn btn-primary btn-rect" style="margin: 0 auto; min-width: 150px; max-width: 450px; object-fit: cover;">
	                    Bitte neue Daten angeben:
	                </p>
		            <form id="changeform" method ="post" style="margin: 0 auto; min-width: 150px; max-width: 450px; object-fit: cover;">
		                <p style="margin-top:5px;"><p>
		                <input name="oldPassword" type="password" placeholder="Altes Passwort" class="form-control" />
		                <p style="margin-top:45px;"><p>
		                <input name="password" type="password" placeholder="Neues Passwort" class="form-control" />
		                <p style="margin-top:2px;"><p>
		                <input name="comparePassword" type="password" placeholder="Neues Passwort wiederholen" class="form-control" />
	                	
						<#if error??>
							<p style="margin-top:9px;"><p>
		                	<p class="text-muted text-center btn-block btn btn-danger btn-rect" style="width: 450px;margin: 0 auto;">
		                    	${error}
		               	 	</p><p>
	                	</#if>
	                	<p style="margin-top:2px;"><p>
	                	<button class="btn text-muted text-center btn-danger" type="submit">Daten ändern</button>
	                </form>
	                
</@template.page> 