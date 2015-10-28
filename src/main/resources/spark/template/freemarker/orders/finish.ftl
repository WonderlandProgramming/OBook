<#import "/lib/template.ftl" as template> 
<#import "/lib/smallHelpers.ftl" as helpers>

<@template.page>
	<@helpers.subPageTitle pageTitle = title />
		

		<form method="post" style="display:inline;" id="finishForm"></form>
			<#if orderID == -1>
				<div class="col-lg-30" style="margin-left: auto; margin-right: auto; min-width: 250px; max-width: 400px">
			<#else>
				<div class="col-lg-30" style="margin-left: auto; margin-right: auto; min-width: 250px; max-width: 600px">
			</#if>
				<#if error??>
					<p class="text-muted text-center btn-block btn btn-danger btn-rect">    
						${error}
					</p><p>
					<p style="margin-top:15px;"></p>
				</#if>

				<div class="panel panel-default">
					
				<#if orderID == -1>
					<div class="panel-heading">
						Auftrag fertigstellen:
					</div>
					                        
					<div class="panel-body">
						<p> 
								<label>Nummer eingeben:</label>
				                <input form="finishForm" name="input_nummer" class="form-control" style="max-width:100%;">
							               						
								<p style="margin-top:10px;"></p>
								<input form="finishForm" type="submit" class="btn btn-info" value="Suchen" name="button_AuftragSuchen">
						  	</p>
						 </div>
					</div>
			        </div>
		    	<#else>
					<div class="panel-heading">
						Auftrag ${orderID}:
					</div>
					                        
					<div class="panel-body">							
						
							<label>Auftrag details:</label>
						    
						    <#if order.books??>
								  	<table>
								    	<colgroup>
								    		<col width="25">
								    		<col width="200">
								    		<col width="100">
								    		<col width="100">
								  		</colgroup>
								  		<tr>
								    			<td>5</td>
								    			<td>Mathe für Anfänger</td>
								    			<td>Mathematik</td>
								    			<td>Naturwissenschaften</td>
								 		 </tr>
								  	<#list order.books as book>
								  			<tr>
								    			<td>${book.amount}</td>
								    			<td>${book.name}</td>
								    			<td>${book.subject}</td>
								    			<td>${book.category}</td>
								 		 	</tr>
									</#list>
								  </table>
							  </#if>
						    
							<p style="margin-top:10px;"></p>
							<input form="finishForm" type="submit" class="btn btn-default" value="Abbrechen" name="button_Abbrechen">
							<input form="finishForm" type="submit" class="btn btn-info" value="Fertigstellen" name="button_AuftragFertigstellen">
					  	</p>
					 </div>
				</div>
	        </div>
        </#if>
        
        	
        			
</@template.page> 