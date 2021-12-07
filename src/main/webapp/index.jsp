<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Sistema JSP</title>
</head>
<body>

	<div class="title">
		<h4>Bem vindo ao Sistema JSP</h4>
	</div>
	<form action="<%= request.getContextPath() %>/ServletLogin" method="post" class="row g-3 needs-validation" novalidate>
		<input type="hidden" value="<%=request.getParameter("url")%>" name="url">
		<div class="mb-3">
			<label class="form-label" for="login">Login</label>
			<input	class="form-control" id="login" name="login" type="text" required>
			<div class="invalid-feedback">Obrigatório</div>
			<div class="valid-feedback">ok</div>
		</div>

		<div class="mb-3">
			<label class="form-label" for="senha">Senha</label> 
			<input class="form-control" id="senha" name="senha" type="password" required>
			<div class="invalid-feedback">Obrigatório</div>
			<div class="valid-feedback">ok</div>
		</div>
		<input type="submit" value="Acessar" class="btn btn-primary">
	</form>
	<h5 class="msg">${msg}</h5>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous">
		
	</script>
	<script type="text/javascript" src="js/scripts.js"></script>
</body>
</html>