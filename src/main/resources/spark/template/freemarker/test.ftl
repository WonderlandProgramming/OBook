<html>
<head>
  <title>${title} | OBook</title>
</head>
<body>
  <h1>Welcome ${user}!</h1>
  <p>Our latest product:
  <a href="${latestProduct.url}">${latestProduct.name}</a>!
	
  <form method="post">
	   <dl>
	     <dt>BookCategory:
	     <input type="hidden" name="search" value="true">
	     <dd><input name="fach" size="30" maxlength="50"">
	   </dl>
	   <input type="submit" value="submit">
  </form>

  <#if error??>
    	<div class="error">
    		<strong>Error:</strong> ${error}
    	</div>
  </#if>

  <#if books??>
  	<#list books as book>
    	<table>
    		<colgroup>
    			<col width="300">
    			<col width="30">
    			<col width="100">
    			<col width="400">
    			<col width="100">
  			</colgroup>
  			
  			<tr>
    			<td>${book.name}</td>
    			<td>${book.id}</td>
    			<td>${book.subject}</td>
    			<td>${book.category}</td>
    			<td>
    				<form method="post">
    					 <input type="hidden" name="removed" value=${book.id}>
    					 <button type="submit">Remove Book</button>
               		</form>
               	</td>
 		 	</tr>
		</table>
	</#list>
  <#else>
  		No Books in List
  </#if>
</body>
</html>