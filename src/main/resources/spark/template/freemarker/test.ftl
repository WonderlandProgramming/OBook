<#import "/lib/template.ftl" as template> 
<@template.page>		
				<form method="post">
						   <dl>
						     <dt>BookCategory:
						     <input type="hidden" name="search" value="true">
						     <dd><input name="fach" size="30" maxlength="50"">
						   </dl>
						   <input type="submit" value="Search">
					  </form>
					
					  <#if error??>
					    	<div class="error">
					    		<strong>Error:</strong> ${error}
					    	</div>
					  </#if>
					
					  <#if books??>
					  	<table>
					    	<colgroup>
					    		<col width="300">
					    		<col width="30">
					    		<col width="100">
					    		<col width="400">
					    		<col width="100">
					  		</colgroup>
					  	<#list books as book>
					    	
					  			
					  			<tr>
					    			<td>${book.name}</td>
					    			<td>${book.id}</td>
					    			<td>${book.subject}</td>
					    			<td>${book.category}</td>
					    			<td>
					    				<form method="post">
					    					 <input type="hidden" name="removed" value=${book.id}>
					    					 <input type="submit" class="btn btn-primary btn-grad btn-rect" value="Remove Book">
					               		</form>
					               	</td>
					 		 	</tr>
						</#list>
					  </table>
					  <#else>
					  		No Books in List
					  </#if>
</@template.page> 