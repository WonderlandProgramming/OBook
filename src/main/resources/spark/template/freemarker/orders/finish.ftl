<#import "/lib/template.ftl" as template> 
<#import "/lib/smallHelpers.ftl" as helpers>

<@template.page>
	<@helpers.subPageTitle pageTitle = title />
		<form method="post" style="display:inline;" id="finishForm"></form>

		<div class="col-lg-25" style="position: absolute; left: 50%; top: 15%; min-width: 350px; padding: 10px; margin-top: -100px;margin-left: -200px;">
			<div class="panel panel-default">
				<div class="panel-heading">
					Auftrag fertigstellen:
				</div>
				                        
				<div class="panel-body">
					<p> 
						<label>Nummer eingeben:</label>
		                <input form="orderForm" name="input_nummer" class="form-control" style="max-width:100%;">
					               						
						<p style="margin-top:10px;"><p
				  	</p>
				 </div>
			</div>
        </div>						
</@template.page> 