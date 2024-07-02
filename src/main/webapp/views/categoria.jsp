<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, model.VO.ProductoVO, model.VO.CategoriaVO, java.util.HashMap" %>
<!DOCTYPE html>
<html lang="es">
<head>
	<jsp:include page="../fragmentos/head.jsp" />
</head>
<body>
    <jsp:include page="../fragmentos/header.jsp" />
    
<%--     <jsp:include page="layout.jsp" /> --%>


    <main role="main" class="d-flex flex-row container mt-3 mb-3">

		

        <div class="row ms-5">
        	<%
			CategoriaVO categoria = (CategoriaVO)request.getAttribute("categoria");
			if (categoria != null) {
			%>
			<h3 style="color: #dd6002">
				<%= categoria.getNombre() %>
			</h3>
			<% } %>
            <%
                List<ProductoVO> productosCateg = (List<ProductoVO>) request.getAttribute("catalogoCateg");
            	if (productosCateg != null && !productosCateg.isEmpty()) {
            		
	                for (ProductoVO producto : productosCateg) {
	                	
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
<%-- 	                        <p class="card-text"><%= producto.getDescripcion() %></p> --%>
<!-- 	                        <p class="card-text"> -->
<%-- 	                        <% --%>
<!--  	                        List<CategoriaVO> categorias = (List<CategoriaVO>)request.getAttribute("categorias"); -->
<!--  	                        for (CategoriaVO categoria : categorias) { -->
<!--  	                        	if (categoria.getId() == producto.getCategoria_id()) { -->
<%-- 	                        %> --%>
<%-- 	                        Categoría: <%= categoria.getNombre() %> --%>
<%-- 	                        <% --%>
<!--  	                        	} -->
<!--  	                        } -->
<%-- 	                        %> --%>
<!-- 	                        </p> -->
	                        <br>
	                        <h6 class="card-text"><%= producto.getPrecio() %> €</h6>
	                        <form method="get" action="comprar" class="d-flex justify-content-between align-items-center">
	                            <input type="hidden" id="id" name="id" value="<%= producto.getId() %>">
	                            <input type="hidden" id="vista" name="vista" value="categoria">
	                            <input type="hidden" id="categoria_id" name="categoria_id" value="<%= producto.getCategoria_id() %>">
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
        </div>
        

    </main>

    <jsp:include page="../fragmentos/footer.jsp" />