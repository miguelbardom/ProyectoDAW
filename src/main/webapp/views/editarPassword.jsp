<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
								<h3 class="black justify-content text-center">Cambiar contraseña</h3>
							</div>
						</div>
					</div>
					<div class="card-body text-center">

						<form method="post" action="editarPassword" class="text-center">
							
							<div class="form-floating mb-3">
								<input type="password" name="clave" id="clave"
									class="form-control" placeholder="Contraseña">
								<label for="clave">Contraseña actual</label>
							</div>
							<p class="error">
								<%
								if (request.getAttribute("claveVacio") != null) {
									String claveVacio = (String) request.getAttribute("claveVacio");

									if (claveVacio != null && !claveVacio.isEmpty()) {
								%>
									<%=claveVacio%>
								<%
									}
								}
								%>							
							</p>
							
							<div class="form-floating mb-3">
								<input type="password" name="nuevaClave" id="nuevaClave"
									class="form-control" placeholder="Contraseña">
								<label for="nuevaClave">Nueva contraseña</label>
							</div>
							<p class="error">
								<%
								if (request.getAttribute("nuevaClaveVacio") != null) {
									String claveVacio = (String) request.getAttribute("nuevaClaveVacio");

									if (claveVacio != null && !claveVacio.isEmpty()) {
								%>
									<%=claveVacio%>
								<%
									}
								}
								%>							
							</p>
							
							<div class="form-floating mb-3">
								<input type="password" name="repNuevaClave" id="repNuevaClave"
									class="form-control" placeholder="Contraseña">
								<label for="repNuevaClave">Repetir nueva contraseña</label>
							</div>
							<p class="error">
								<%
								if (request.getAttribute("repNuevaClaveVacio") != null) {
									String claveVacio = (String) request.getAttribute("repNuevaClaveVacio");

									if (claveVacio != null && !claveVacio.isEmpty()) {
								%>
									<%=claveVacio%>
								<%
									}
								}
								%>							
							</p>
							
							<p class="error">
								<%
								if (request.getAttribute("validar") != null) {
									String validar = (String) request.getAttribute("validar");

									if (validar != null && !validar.isEmpty()) {
								%>
									<%=validar%>
								<%
									}
								}
								%>
							</p>

							<br>
							<input type="submit" value="Cambiar Contraseña" name="Login_CambiarContraseña" class="btn btn-primary">

						</form>

					</div>
				</div>
			</div>
		</div>
	</div>



	<jsp:include page="../fragmentos/footer.jsp" />