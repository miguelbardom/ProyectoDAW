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
								<h3 class="black justify-content text-center">Iniciar
									Sesión</h3>
							</div>
						</div>
					</div>
					<div class="card-body text-center">

						<form method="post" action="login" class="text-center">
							<div class="form-floating mb-3">
								<input type="email" name="email" id="email" class="form-control"
									placeholder="Email">
								<label for="email">Email</label>
							</div>
							<p class="error">
								<%
								if (request.getAttribute("emailVacio") != null) {
									String emailVacio = (String) request.getAttribute("emailVacio");

									if (emailVacio != null && !emailVacio.isEmpty()) {
								%>
									<%=emailVacio%>
								<%
									}
								}
								%>
							</p>
							
							<div class="form-floating mb-3">
								<input type="password" name="clave" id="clave"
									class="form-control" placeholder="Contraseña">
								<label for="clave">Contraseña</label>
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

							<!-- <div class="row mt-3 mx-2">
                            <div class="col-6">
                                <input class="form-check-input" type="checkbox" name="" id="recordar">
                                <label class="form-check-label" for="recordar">Recordar contraseña</label>
                            </div>
                            <div class="col-6">
                                <div class="link-primary no-decoration text-end">He olvidado mi contraseña</div>
                            </div>
                        </div> -->

							<br>
							<input type="submit" value="Login" name="Login_IniciarSesion" class="btn btn-primary">

						</form>

					</div>
				</div>
			</div>
		</div>
	</div>



	<jsp:include page="../fragmentos/footer.jsp" />