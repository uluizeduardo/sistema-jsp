<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sistema JSP</title>
</head>
<body>

	<h1>Sistema com jSP</h1>
	<form action="ServletLogin" method="post">
		<table>
			<tr>
				<td><label>Login</label></td>
				<td><input name="login" type="text"></input></td>
			</tr>
			<tr>
				<td><label>Login</label></td>
				<td><input name="senha" type="password"></input></td>
			</tr>
			
			<tr>
				<td></td>
				<td><input type="submit" value="Enviar"></input></input></td>
			</tr>
		</table>
	</form>
	<h3>${msg}</h3>
</body>
</html>