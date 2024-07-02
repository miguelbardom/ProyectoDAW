<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, model.VO.ProductoVO, model.VO.CategoriaVO, java.util.HashMap" %>
<!DOCTYPE html>
<html lang="es">
<head>
	<jsp:include page="../fragmentos/head.jsp" />
	<style>
		/* Establecer el ancho de los input number */
		input[type="number"] {
		    width: 100px; /* Define el ancho deseado */
		}
	</style>
</head>
<body>
    <jsp:include page="../fragmentos/header.jsp" />
    
<%--     <jsp:include page="layout.jsp" /> --%>

	<h4>
		<%
		Object usuarioObj = session.getAttribute("usuario");
		if (usuarioObj != null) {
			model.VO.UsuarioVO usuario = (model.VO.UsuarioVO) usuarioObj;
		%>
<!-- 			Bienvenido,	 -->
<%-- 			<%= usuario.getNombre() %> --%>
		<% } %>
	</h4>

    <main role="main" class="d-flex flex-row container mt-3 mb-3">
        
        <div class="col-md-3 me-5 mt-2" style="margin-left: -4%">
            <aside class="bg-light p-1">
                <h4 style="color: #dd6002">Filtrar Productos</h4>
                
                <!-- Filtro por Precio -->
                <div class="mb-3">
	                <form action="precio" method="GET" class="form-control">
	    				<label class="form-label" for="precio_min">Precio Mínimo (€):</label>
	    				<input class="form-control" type="number" id="precio_min" name="precio_min" min="0" step="0.01" value="0.00">
						<p></p>
	    				<label class="form-label" for="precio_max">Precio Máximo (€):</label>
	    				<input class="form-control" type="number" id="precio_max" name="precio_max" min="0" step="0.01" value="0.00">
						<p></p>
	    				<button class="btn btn-outline-primary btn-sm" type="submit">Filtrar por Precio</button>
					</form>
                </div>
                
            </aside>
        </div>

        <div class="row">
        <%
        	List<ProductoVO> productosFiltro = (List<ProductoVO>) request.getAttribute("productosFiltro");
        	Double precioMin = (Double)request.getAttribute("precioMin");
        	Double precioMax = (Double)request.getAttribute("precioMax");
        	if (productosFiltro == null) {
        		
        %>
        	<div class="card-body">
        		<h3 style="color: #dd6002">Últimos Productos</h3>
        	</div>
        	<%
                List<ProductoVO> ultimosProd = (List<ProductoVO>) request.getAttribute("ultimosProd");
            	if (ultimosProd != null) {
            		
	                for (ProductoVO producto : ultimosProd) {
	                	
            %>
            
            <div class="col-md-4 mb-4">
                <div class="card h-100">
                    <div class="card-body d-flex flex-column">
                    	<div class="flex-grow-1 d-flex flex-column">
		                	<a href="<%= request.getContextPath() %>/producto?id=<%= producto.getId() %>" class="nav-link">
		                        <h5 class="card-title"><%= producto.getNombre() %></h5>
<%-- 						        <img src="<%= request.getContextPath()+producto.getUrl_imagen() %>" class="img-fluid" style=" max-height: 200px"> --%>
						        <div class="d-flex justify-content" style="height: 200px;">
			                        <img src="<%= request.getContextPath() + producto.getUrl_imagen() %>" class="img-fluid" style="max-height: 100%; max-width: 100%; object-fit: cover;">
			                    </div>
		                    </a>
                    	</div>
	                        <br>
	                        <h6 class="card-text"><%= producto.getPrecio() %> €</h6>
	                        <form method="get" action="comprar" class="d-flex justify-content-between align-items-center">
	                            <input type="hidden" id="id" name="id" value="<%= producto.getId() %>">
	                            <button type="submit" class="btn btn-success">Comprar</button>
	                        </form>
	                        <br>
							<% 
	    					HashMap<Integer, String> alert = (HashMap<Integer, String>) request.getAttribute("alert");
	    					if (alert != null) { 
						        Integer productId = producto.getId(); // Obtener el ID del producto actual
						        String message = alert.get(productId); // Obtener el mensaje correspondiente al ID del producto
						        
							    if (message != null) { // Verificar si hay un mensaje para este producto
							%>
			                    <p class="alert alert-danger">
						            <%= message %>
								</p>
						    <% 
						        }
						    } 
							%>
	                </div>
                </div>
            </div>
            <%
	                }
                }
            %>
        	
        	<span class="mb-2"> </span>
        	<div class="card-body">
        		<h3 style="color: #dd6002">Todos los Productos</h3>
        	</div>
            <%
                List<ProductoVO> catalogo = (List<ProductoVO>) request.getAttribute("catalogo");
            	if (catalogo != null) {
            		
	                for (ProductoVO producto : catalogo) {
	                	
            %>
            
            <div class="col-md-4 mb-4">
                <div class="card h-100">
                    <div class="card-body d-flex flex-column">
                    	<div class="flex-grow-1 d-flex flex-column">
		                	<a href="<%= request.getContextPath() %>/producto?id=<%= producto.getId() %>" class="nav-link">
		                        <h5 class="card-title"><%= producto.getNombre() %></h5>
<%-- 						        <img src="<%= request.getContextPath()+producto.getUrl_imagen() %>" class="img-fluid" style=" max-height: 200px"> --%>
						        <div class="d-flex justify-content" style="height: 200px;">
			                        <img src="<%= request.getContextPath() + producto.getUrl_imagen() %>" class="img-fluid" style="max-height: 100%; max-width: 100%; object-fit: cover;">
			                    </div>
		                    </a>
                    	</div>
	                        <br>
	                        <h6 class="card-text"><%= producto.getPrecio() %> €</h6>
	                        <form method="get" action="comprar" class="d-flex justify-content-between align-items-center">
	                            <input type="hidden" id="id" name="id" value="<%= producto.getId() %>">
	                            <button type="submit" class="btn btn-success">Comprar</button>
	                        </form>
	                        <br>
							<% 
	    					HashMap<Integer, String> alert = (HashMap<Integer, String>) request.getAttribute("alert");
	    					if (alert != null) { 
						        Integer productId = producto.getId(); // Obtener el ID del producto actual
						        String message = alert.get(productId); // Obtener el mensaje correspondiente al ID del producto
						        
							    if (message != null) { // Verificar si hay un mensaje para este producto
							%>
			                    <p class="alert alert-danger">
						            <%= message %>
								</p>
						    <% 
						        }
						    } 
							%>
	                </div>
                </div>
            </div>
            <%
	                }
                }
            %>
            <%
            } else {
            	
            	if (productosFiltro != null) {
            %>
            <div class="card-body">
        		<h3 style="color: #dd6002">Productos entre <%= precioMin %>€ y <%= precioMax %>€</h3>
        	</div>
        	<%
	                for (ProductoVO producto : productosFiltro) {
	                	
            %>
            
            <div class="col-md-4 mb-4">
                <div class="card h-100">
                    <div class="card-body d-flex flex-column">
                    	<div class="flex-grow-1 d-flex flex-column">
		                	<a href="<%= request.getContextPath() %>/producto?id=<%= producto.getId() %>" class="nav-link">
		                        <h5 class="card-title"><%= producto.getNombre() %></h5>
<%-- 						        <img src="<%= request.getContextPath()+producto.getUrl_imagen() %>" class="img-fluid" style=" max-height: 200px"> --%>
						        <div class="d-flex justify-content" style="height: 200px;">
			                        <img src="<%= request.getContextPath() + producto.getUrl_imagen() %>" class="img-fluid" style="max-height: 100%; max-width: 100%; object-fit: cover;">
			                    </div>
		                    </a>
                    	</div>
	                        <br>
	                        <h6 class="card-text"><%= producto.getPrecio() %> €</h6>
	                        <form method="get" action="comprar" class="d-flex justify-content-between align-items-center">
	                            <input type="hidden" id="id" name="id" value="<%= producto.getId() %>">
	                            <button type="submit" class="btn btn-success">Comprar</button>
	                        </form>
	                        <br>
							<% 
	    					HashMap<Integer, String> alert = (HashMap<Integer, String>) request.getAttribute("alert");
	    					if (alert != null) { 
						        Integer productId = producto.getId(); // Obtener el ID del producto actual
						        String message = alert.get(productId); // Obtener el mensaje correspondiente al ID del producto
						        
							    if (message != null) { // Verificar si hay un mensaje para este producto
							%>
			                    <p class="alert alert-danger">
						            <%= message %>
								</p>
						    <% 
						        }
						    } 
							%>
	                </div>
                </div>
            </div>
            <%
	                }
            	}
            }
            %>
        </div>
        

    </main>

    <jsp:include page="../fragmentos/footer.jsp" />