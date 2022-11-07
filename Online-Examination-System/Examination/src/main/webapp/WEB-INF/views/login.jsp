<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<head>
<title>Login</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="js/custom.js"></script>
<link href="css/custom.css" rel="stylesheet">

<body>
<div align="center" style="padding-top: 100px;">
<h1>Online Examination System</h1>
<h3>User Login</h3>
<form:form action="/login" method="POST" modelAttribute="user">	
	<table>
		<tr>
			<td align="left" style="padding: 5px 70px 5px 5px;">User Name</td>
			<td><form:input path="username" class="form-control"/></td>
		</tr>
		<tr>
			<td style="padding: 5px 70px 5px 5px;">Password</td>
			<td><form:input path="password" class="form-control"/></td>
		</tr>
		<tr style="padding-top: 15px;">
			<td></td>
			<td><input type="submit" value="Login" class="btn btn-primary"></td>
		</tr>
	</table>
	<span style="color: red;">${message }</span>
</form:form>
</div>
</body>
</html>