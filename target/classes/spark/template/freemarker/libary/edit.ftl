<#import "/lib/template.ftl" as template> 
<#import "/lib/smallHelpers.ftl" as helpers>

<@template.page>
	<div class="row">
                    <div class="col-lg-12">
                        <h2> Alle Bücher: </h2>
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
                                            <th>Buch ID</th>
                                            <th>Name</th>
                                            <th>Fach</th>
                                            <th>Fachbereich</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<#if books??>
                                    		<#list books as book>
                                    			<tr>
					    							<td>${book.id}</td>
		                                           	<td>${book.name}</td>
									    			<td>${book.subject}</td>
									    			<td>${book.category}</td>
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