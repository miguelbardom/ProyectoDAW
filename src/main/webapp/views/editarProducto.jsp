<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List, model.VO.ProductoVO, model.VO.CategoriaVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	

<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="../fragmentos/head.jsp" />

</head>

<body>
	<jsp:include page="../fragmentos/header.jsp" />
	
	<div class="container mt-5">
        <%
		Object usuarioObj = session.getAttribute("usuario");
        model.VO.UsuarioVO usuario = null;
		if (usuarioObj != null) {
			usuario = (model.VO.UsuarioVO) usuarioObj;
		%>
		<% } %>
		
		<div class="row gx-8">
			<div class="col-6 offset-3 mb-4">
				<div class="card shadow">
					<div class="card-header border-0">
						<div class="row mt-3 ms-1">
							<div class="">
								<h3 class="black justify-content text-center">Editar Producto</h3>
							</div>
						</div>
					</div>
					<div class="card-body text-center">
					
						<form method="post" action="editar" enctype="multipart/form-data">
							<%
			                ProductoVO producto = (ProductoVO)request.getAttribute("producto");
			            	if (producto != null) {
			            	%>
							
							<input type="hidden" name="id" id="id" value="<%= producto.getId() %>">
							
							<div class="form-floating mb-3">
								<input type="text" name="nombre" id="nombre" class="form-control"
									placeholder="Nombre" value="<%= producto.getNombre() %>" required>
								<label for="nombre">Nombre</label>
							</div>
							<p class="error"></p>
							
							<div class="form-floating mb-3">
								<textarea class="form-control" id="descripcion" name="descripcion" style="height: 150px" required><%= producto.getDescripcion() %></textarea>
								<label for="descripcion">Descripción</label>
							</div>
							<p class="error"></p>
							
							<div class="form-floating mb-3">
	                            <select class="form-control" id="categoria_id" name="categoria_id" required>
								<%
	                            List<CategoriaVO> categorias = (List<CategoriaVO>)request.getAttribute("categorias");
	                                                                                    
	                            for (CategoriaVO categoria : categorias){
	                            	
	                            	if (categoria.getId() == producto.getCategoria_id()) {
	                            		
	                            %>
	                            	<option value="<%= categoria.getId() %>" selected><%= categoria.getNombre() %></option>
								<%
	                            	} else {
	                            %>
	                            	<option value="<%= categoria.getId() %>"><%= categoria.getNombre() %></option>
	                            <%
	                            	}
	                            }
								%>
	                            </select>
								<label for="categoria_id">Categoría</label>
							</div>
							<p class="error"></p>
							
							<div class="form-floating mb-3">
								<input type="number" name="precio" id="precio" class="form-control"
									placeholder="Precio" step="0.01" value="<%= producto.getPrecio() %>" required>
								<label for="precio">Precio</label>
							</div>
							<p class="error"></p>
							
							<div class="form-floating mb-3">
								<input class="form-control" type="file" id="img" name="img" required>
								<label for="img">Imagen</label>
							</div>
							<p class="error"></p>
							
							<div class="form-floating mb-3">
								<select class="form-control" id="baja" name="baja" required>
							        <c:choose>
							            <c:when test="${producto.isBaja()}">
							                <option value="true" selected>Sí</option>
							                <option value="false">No</option>
							            </c:when>
							            <c:otherwise>
							                <option value="false" selected>No</option>
							                <option value="true">Sí</option>
							            </c:otherwise>
							        </c:choose>
                    			</select>
								<label for="baja">Dar de baja:</label>
							</div>
							<p class="error"></p>
							
							<p class="correcto"></p>

							<br>
							
							<input type="submit" value="Editar Producto" name="EditarProducto"
								class="btn btn-primary">
							
							<% } %>

						</form>

					</div>
				</div>
			</div>
		</div>
		
		
    </div>
	
	<jsp:include page="../fragmentos/footer.jsp" />