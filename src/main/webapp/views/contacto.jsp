<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Map, java.util.HashMap, service.UsuarioService, model.VO.UsuarioVO"%>
<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="../fragmentos/head.jsp" />
</head>
<body>
	<jsp:include page="../fragmentos/header.jsp" />


	<div class="container mt-5 mb-4">
		<div class="row gx-8">
			<div class="col-6 offset-3 mb-4">
				<div class="card shadow">
					<div class="card-header border-0">
						<div class="row mt-3 ms-1">
							<div class="">
								<h3 class="black justify-content text-center">Formulario de Contacto</h3>
							</div>
						</div>
					</div>
					<div class="card-body text-center">
					
						<form method="post" action="contacto">
						
							<div class="form-floating mb-3">
								<input type="text" name="nombre" id="nombre" class="form-control"
									placeholder="Nombre">
								<label for="nombre">Nombre</label>
							</div>
							<p class="error"></p>
							
							<div class="form-floating mb-3">
								<input type="text" name="apellidos" id="apellidos"
									class="form-control" placeholder="Apellidos">
								<label for="apellidos">Apellidos</label>
							</div>
							<p class="error"></p>
							
							<div class="form-floating mb-3">
								<input type="email" name="email" id="email" class="form-control"
									placeholder="Email">
								<label for="email">Email</label>
							</div>
							<p class="error"></p>
							
							<div class="form-floating mb-3">
								<textarea class="form-control" placeholder="Mensaje" id="mensaje" name="mensaje" style="height: 100px"></textarea>
  								<label for="mensaje">Mensaje</label>
							</div>
							<p class="error"></p>
							
							<p class="correcto"></p>

							<br>
							<input type="submit" value="Enviar" name="Contacto_Enviar"
								class="btn btn-success">

						</form>
						<br>
							<% 
	    					String msj = (String) request.getAttribute("msj");
	    					if (msj != null) { 
						        
							%>
			                    <p class="alert alert-success">
						            <%= msj %>
								</p>
						    <% 
						    } 
							%>

					</div>
				</div>
			</div>
		</div>
	</div>



	<jsp:include page="../fragmentos/footer.jsp" />