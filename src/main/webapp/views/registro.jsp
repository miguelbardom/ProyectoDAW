<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Map, java.util.HashMap, service.UsuarioService"%>
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
								<h3 class="black justify-content text-center">Registro</h3>
							</div>
						</div>
					</div>
					<div class="card-body text-center">
					
<%-- 						<% Map<String, String> erroresMap = new HashMap<>(); %> --%>

						<form method="post" action="registro">

							<input type="text" name="nombre" id="nombre" class="form-control"
								placeholder="Nombre" value="<%
								if (request.getAttribute("recuerda") != null) {
									
									HashMap<String, String> recuerdaMap = (HashMap<String, String>)request.getAttribute("recuerda");

								    String recuerdaNom = recuerdaMap.get("nombre");
									
									if (recuerdaNom != null && !recuerdaNom.isEmpty()) {
								%><%=recuerdaNom%><%
									}
								}
								%>">
							<p class="error">
								<%
								if (request.getAttribute("errores") != null) {
									
									HashMap<String, String> erroresMap = (HashMap<String, String>)request.getAttribute("errores");

								    String nombreError = erroresMap.get("nombre");
									
									if (nombreError != null && !nombreError.isEmpty()) {
								%>
									<%=nombreError%>
								<%
									}
								}

								%>
							</p>

							<input type="text" name="apellidos" id="apellidos"
								class="form-control" placeholder="Apellidos" value="<%
								if (request.getAttribute("recuerda") != null) {
									
									HashMap<String, String> recuerdaMap = (HashMap<String, String>)request.getAttribute("recuerda");

								    String recuerdaApe = recuerdaMap.get("apellidos");
									
									if (recuerdaApe != null && !recuerdaApe.isEmpty()) {
								%><%=recuerdaApe%><%
									}
								}
								%>">
							<p class="error">
								<%
								if (request.getAttribute("errores") != null) {
									
									HashMap<String, String> erroresMap = (HashMap<String, String>)request.getAttribute("errores");

								    String apellidosError = erroresMap.get("apellidos");

								if (apellidosError != null && !apellidosError.isEmpty()) {
								%>
									<%=apellidosError%>
								<%
									}
								}

								%>								
							</p>
							
							<input type="date" name="fecha_nacimiento" id="fecha_nacimiento"
								class="form-control" value="<%
								if (request.getAttribute("recuerda") != null) {
									
									HashMap<String, String> recuerdaMap = (HashMap<String, String>)request.getAttribute("recuerda");

								    String recuerdaFecha = recuerdaMap.get("fecha");
									
									if (recuerdaFecha != null && !recuerdaFecha.isEmpty()) {
								%><%=recuerdaFecha%><%
									}
								}
								%>">
							<p class="error">
								<%
								if (request.getAttribute("errores") != null) {
									
									HashMap<String, String> erroresMap = (HashMap<String, String>)request.getAttribute("errores");

								    String fechaError = erroresMap.get("fecha");

								if (fechaError != null && !fechaError.isEmpty()) {
								%>
									<%=fechaError%>
								<%
									}
								}

								%>
							</p>

							<input type="email" name="email" id="email" class="form-control"
								placeholder="Email" value="<%
								if (request.getAttribute("recuerda") != null) {
									
									HashMap<String, String> recuerdaMap = (HashMap<String, String>)request.getAttribute("recuerda");

								    String recuerdaEmail = recuerdaMap.get("email");
									
									if (recuerdaEmail != null && !recuerdaEmail.isEmpty()) {
								%><%=recuerdaEmail%><%
									}
								}
								%>">
							<p class="error">
								<%
								if (request.getAttribute("errores") != null) {
									
									HashMap<String, String> erroresMap = (HashMap<String, String>)request.getAttribute("errores");

								    String emailError = erroresMap.get("email");

								if (emailError != null && !emailError.isEmpty()) {
								%>
									<%=emailError%>
								<%
									}
								}

								%>
							</p>
							
							<input type="password" name="clave" id="clave" class="form-control"
								placeholder="Contraseña">
							<p class="error">
								<%
								if (request.getAttribute("errores") != null) {
									
									HashMap<String, String> erroresMap = (HashMap<String, String>)request.getAttribute("errores");

								    String claveError = erroresMap.get("clave");

								if (claveError != null && !claveError.isEmpty()) {
								%>
									<%=claveError%>
								<%
									}
								}

								%>
							</p>

							<input type="password" name="claveRep" id="claveRep"
								class="form-control" placeholder="Repetir contraseña">
							<p class="error">
								<%
								if (request.getAttribute("errores") != null) {
									
									HashMap<String, String> erroresMap = (HashMap<String, String>)request.getAttribute("errores");

								    String claveRepError = erroresMap.get("claveRep");

								if (claveRepError != null && !claveRepError.isEmpty()) {
								%>
									<%=claveRepError%>
								<%
									}
								}

								%>
							</p>
							
							<p class="correcto">
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
							<p class="error">
								<%
								if (request.getAttribute("validarMal") != null) {
									String validarMal = (String) request.getAttribute("validarMal");

									if (validarMal != null && !validarMal.isEmpty()) {
								%>
									<%=validarMal%>
								<%
									}
								}

								%>
							</p>

							<input type="submit" value="Registrar" name="Registro_Registrar"
								class="btn btn-success">

						</form>

					</div>
				</div>
			</div>
		</div>
	</div>



	<jsp:include page="../fragmentos/footer.jsp" />