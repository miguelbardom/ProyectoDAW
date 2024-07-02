<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Tienda Serbatic</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Agregar Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
    body {
        background-color: #f8f9fa;
    }
    footer {
        text-align: center;
        padding: 20px;
        background-color: #343a40;
        color: #ffffff;
    }
</style>
</head>
<body>
    <header class="navbar-dark bg-dark">
    	<span class="navbar-brand mb-0 h1">Tienda Serbatic</span>
    	<form method="get" action="login">
	    	<button class="btn btn-light" href="#" name="Home_Login">Inicia Sesión</button>
	    	|
	    	<button class="btn btn-success" href="#" name="Home_Registro">Regístrate</button>
    	</form>
    </header>
    
    <main>
    <% 
        if (session.getAttribute("vista") == null) {
            // Si la sesión 'vista' no está establecida, incluir 'home.jsp'
    %>
            <jsp:include page="layout.jsp" />
    <%
        } else {
            // Si la sesión 'vista' está establecida, incluir la vista almacenada en 'vista'
    %>
            <jsp:include page="<%= session.getAttribute("vista") %>" />
    <%
        }
    %>
</main>
    
    
    <footer>
        <div class="container">
            <p>&copy; 2024 Mi Tienda. Todos los derechos reservados.</p>
        </div>
    </footer>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
    