<#import "/lib/template.ftl" as template> 
<#import "/lib/smallHelpers.ftl" as helpers>

<@template.page>
				<div class="row">
                    <div class="col-lg-12">
                        <h2> Alle Nutzer: </h2>
                    </div>
                </div>
                <hr />

                <div class="row">
                <div class="col-lg-12" style="margin-left: auto; margin-right: auto; max-width: 100%;min-width: 500px;">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>Nutzername</th>
                                            <th>Zugriffsrechte</th>
                                            <th>NutzerID</th>
                                            <th>Bearbeiten</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<#if users??>
                                    		<#list users as user>
                                    			<tr>
		                                            <td>${user.username}</td>
		                                            <td>${user.loginlevel}</td>
		                                            <td>${user.userID}</td>
		                                            <td class="center">Bearbeiten</td>
                                        		</tr>
                                    		</#list>
                                    	</#if>
                                </table>
                            </div>
                           
                        </div>
                    </div>
                </div>
            </div>           
</@template.page> 