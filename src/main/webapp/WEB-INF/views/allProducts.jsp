<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<body>
<h3>Products Page</h3>

<table cellpadding="2" cellspacing="2" border="1">
	<tr>
		<th>Code</th>
		<th>Name</th>
		<th>Price</th>
		<th>CreateDate</th>
		<th>Buy</th>
	</tr>

<c:forEach items="${product}" var="product">
<tr>
	<td>${product.code }</td>
	<td>${product.name }</td>
	<td>${product.price }</td>
	<td>${product.createDate}</td>
	<td align="center">
	<a href="${pageContext.request.contextPath }/buy/${product.code}">Buy Now</a>
	</td>
</tr>
   <%-- <li>${product.code}</li>
   <li>${product.name}</li>
   <li>${product.price}</li>
   <li>${product.createDate}</li> --%>
</c:forEach>
</table>
<a href="/addProducts">Add new product ! </a>
</body>