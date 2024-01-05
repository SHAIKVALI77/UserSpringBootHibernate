<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
    <h1>Add new products</h1>
        
    <form:form  method="POST" action="added" modelAttribute="product">
        <form:errors path="" element="div" />
        <div>
            <%-- <form:label path="name">Product Name</form:label>
            <form:input path="name" />
            <form:errors path="name" /> --%>
            
            <table style="with: 50%">
				<tr>
					<td>Product Code</td>
					<td><input type="text" name="code" /></td>
				</tr>
				<tr>
					<td>Product Name</td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>Product Price</td>
					<td><input type="text" name="price" /></td>
				</tr>
			</table>
        </div>
        <div>
            <input type="submit" />
        </div>
    </form:form>
</body>
</html>
	