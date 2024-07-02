<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="../../fragmentos/head.jsp" />
</head>

<body>
	<jsp:include page="../../fragmentos/headerAdmin.jsp" />

	<div class="login-page d-flex align-items-center bg-gray-100 mt-5 mb-4">
		<div class="container mb-3">
			<div class="row">
				<div class="col-md-6 mx-auto">
					<div class="card">
						<div class="card-header border-0 text-center mb-1">
							<div class="row mt-3 ms-1">
								<div class="">
									<h1 class="text-xxl text-gray-400 text-uppercase justify-content text-center">
										<strong class="text-primary">Dashboard:</strong><br>Trade2<i>Shop</i>
									</h1>
								</div>
							</div>
						</div>
						<div class="card-body p-5">
							<form method="post" action="acceso" class="text-center">
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
								<br>
								<input type="submit" value="Login" name="Login_IniciarSesion" class="btn btn-primary">
<!-- 								<br>  -->
<!-- 								<span class="text-xs mb-0 text-gray-500">Do	not have an account?</span>  -->
<!-- 								<a class="text-xs text-paleBlue ms-1" href="register.html"> Signup</a> -->
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div
			class="text-center position-absolute bottom-0 start-0 w-100 z-index-20">
			<!--         <p class="text-gray-500">Design by <a class="external" href="https://bootstrapious.com/p/admin-template">Bootstrapious</a> -->
			<!-- Please do not remove the backlink to us unless you support further theme's development at https://bootstrapious.com/donate. It is part of the license conditions. Thank you for understanding :)                      -->
			<!--         </p> -->
		</div>
	</div>

	<jsp:include page="../../fragmentos/footerAdmin.jsp" />