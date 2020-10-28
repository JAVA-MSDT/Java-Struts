<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logging Page</title>
</head>
<body>

	<h1>Logging Page</h1>
	<br>
	<s:form action="helloworld/login">
		<s:textfield name="username" label="User Name"></s:textfield>
		<s:textfield name="password" label="Password" type="password"></s:textfield>
		<s:submit value="Login"></s:submit>
	</s:form>
</body>
</html>