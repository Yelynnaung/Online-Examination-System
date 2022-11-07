<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<head>
<title>Home</title>
</head>
<body>
<div align="center">
	<u><h1>Online Examination System</h1></u>
	<h3><a href="/user/userList">User Management</a> | <a href="/exam/examList">Exam Management</a></h3><br/><br/>
	<a href="/logout" onclick="return confirm('Are your sure to logout?')">Logout</a>
</div>

</body>
</html>