<#import "/lib/template.ftl" as template> 
<#import "/lib/smallHelpers.ftl" as helpers>
<#import "create_1.ftl" as page1>
<#import "create_2.ftl" as page2>
<#import "create_3.ftl" as page3>

<@template.page>
	<@helpers.subPageTitle pageTitle = title />
	<form method="post" style="display:inline;" id="orderForm"></form>
	
	<table style="min-height:100%; min-width:100%;">
		<tr>
			<td height="90">
				<!--Image comes here-->
			</td>
			<td style="position: absolute; right: 25px; min-width: 250px; padding: 10px" height="90">
					<div class="panel panel-info">
	                	<div class="panel-heading" align="center" style="font-size: 18px;">
	                	   		Schritt ${currentID}
						</div>
						<#if currentID &gt; 1>
							<div class="panel-body">
								<div align="center">
										<#if currentID &gt; 1>
							    			<input form="orderForm" type="submit" class="btn btn-default" name="zurueck" value="Zurück" style="width:100px; margin-right:5px">
										</#if>
										<#if currentID < 3>
							    			<input form="orderForm" name="weiter" type="submit" class="btn btn-info" value="Weiter" style="width:150px;">
										</#if>
										<#if currentID == 3>
							    			<input form="orderForm" type="submit" name="fertigstellen" class="btn btn-info" value="Fertigstellen" style="width:150px;">
										</#if>
								<div>
		                   	</div>
	                   	</#if>
					</div>
			</td>
		</tr>
		
		<#if currentID &gt; 1>
			<tr height="500">
		<#else>
			<tr height="375">
		</#if>
			<td>
				<#switch currentID>
				
				  <#case 1>
				  
				    <@page1.part/>
				    
				    <#break>
				  
				  <#case 2>
				   
					 <@page2.part/>
					
				    <#break>
				      
				  <#case 3>
				   
					 <@page3.part/>
					
				    <#break>
				    
				</#switch>
			</td>
		</tr>
	</table>
</@template.page> 