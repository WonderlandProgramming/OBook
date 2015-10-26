<#macro part>
	<table style="min-width:100%;">
				    	<tr>
							<td style="min-width:55%;">
								<div class="col-lg-25" style="max-width=95%">
									<div class="panel panel-default">
										<div class="panel-heading">
				                            Neu Erstellen:
				                        </div>
				                        
				                        <div class="panel-body">
				                            <p> 
						                        		<label>Optionen:</label>
		                                            	<div class="radio">
			                                                <label>
			                                                    <input form="orderForm" type="radio" name="radio_vorauswahl" value="mitVorauswahlt" checked="checked" />Mit Vorauswahlt
			                                        		</label>
		                                           	 	</div>
			                                            <div class="radio">
			                                                <label>
			                                                    <input form="orderForm" type="radio" name="radio_vorauswahl" value="ohneVorauswahlt" value="option2" />Ohne Vorauswahlt
	                                                		</label>
					                            		</div>
				                            	
				                            			<p style="margin-top:20px;"><p>
				                            	
				                            			<label>Neu Erstellen:</label>
				                            			
				                            			<p style="margin-top:20px;"><p>
				                            			
				                            			<label class="control-label col-lg-2">Nummer:</label>
					               							<input form="orderForm" name="input_nummer" class="form-control" style="max-width:70%;">
					               						
					               							<p style="margin-top:10px;"><p>
					               						
					               						<label class="control-label col-lg-2">Klasse:</label>
															<select name="select_Class" form="orderForm" data-placeholder="Klasse:" class="form-control chzn-select" tabindex="0" style="max-width:70%;">
					                            				<optgroup label="5 Klasse">
													                <option>5a</option>
													                <option>5b</option>
													                <option>5c</option>
													                <option>5d</option>
													            </optgroup>
													            <optgroup label="6 Klasse">
													                <option>6a</option>
													                <option>6b</option>
													                <option>6c</option>
													                <option>6d</option>
													                <option>6e</option>
													            </optgroup>
													            <optgroup label="7 Klasse">
													                <option>7a</option>
													                <option>7c</option>
													                <option>7d</option>
													            </optgroup>
					                            			 </select>
				                            			
				                            			
				                            			<p style="margin-top:10px;"><p>
				                            			
				                            			<label class="control-label col-lg-4">Zusatzinformationen:</label>
				                            			<textarea form="orderForm" name="textarea_zusatz" class="form-control" rows="3" style="max-width:86.5%;"></textarea>
				                            			
				                            			<p style="margin-top:15px;"><p>
					               						<input form="orderForm" type="submit" class="btn btn-info" value="Bestätigen" name="button_neuErstellen">
				                           			 </div>
				                            </p>
				                        </div>
                    				</div>
                				</div>
							</td>
							
							<td style="width:40%; padding-left:10px;" valign="top">
								<div class="col-lg-15" style="min-width=100%">
									<div class="panel panel-default">
										<div class="panel-heading">
				                            ID Bestätigen
				                        </div>
				                        
				                        <div class="panel-body">
				                            <p> 
				                            	<label>WebCode </label>
                                            	<input form="orderForm" name="input_webCode" class="form-control">
                                            	<p style="margin-top:10px;"><p>
					               				<input form="orderForm" type="submit" class="btn btn-info" value="Bestätigen" name="button_webCode">
				                            </p>
				                        </div>
                    				</div>
                				</div>
							</td>
				    </table>
</#macro> 