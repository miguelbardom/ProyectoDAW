<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, model.VO.UsuarioVO, model.VO.ProductoVO, java.util.HashMap" %>
<!DOCTYPE html>
<html lang="es">
<head>
	<jsp:include page="../fragmentos/head.jsp" />
</head>
<body>
    <jsp:include page="../fragmentos/header.jsp" />
    
    <main class="p-3 m-0 border-0 bd-example m-0 border-0">
	    
	    <div class="container mt-3">
			<div class="row align-items-center">
		        <h2 class="col">Productos</h2>
		        <div class="col-auto">
		        	<a class="btn btn-primary mb-3" href="<%=request.getContextPath()%>/crear">Crear Producto</a>
		        </div>
			</div>
	        <%
			Object usuarioObj = session.getAttribute("usuario");
	        UsuarioVO usuario = null;
			if (usuarioObj != null) {
				usuario = (UsuarioVO) usuarioObj;
			%>
			<% } %>
			<table class="table">
            <thead>
                <tr>
                	<th>Imagen</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                List<ProductoVO> productos = (List<ProductoVO>) request.getAttribute("productos");
            	if (!productos.isEmpty()) {
            		
	                for (ProductoVO producto : productos) {
	                	
            	%>
                <tr>
                	<td><img src="<%= request.getContextPath()+producto.getUrl_imagen() %>" class="img-fluid" 
                			style="max-width:50%; max-height: 50px">
                	</td>
                    <td><%= producto.getNombre() %></td>
                    <td><%= String.format("%.2f",producto.getPrecio()) %> €</td>
                    <td>
                    	<a class="btn btn-outline-primary mb-3" href="<%=request.getContextPath()%>/editar?id=<%= producto.getId()%>">Editar</a>
                    </td>
                    <td>
                    <%
                    	if (producto.isBaja() == false) {
                    %>
                    	<a class="btn btn-outline-danger mb-3" href="<%=request.getContextPath()%>/eliminar?id=<%= producto.getId()%>">Baja</a>
                    <%
                    	} else {
                    %>
                    	<a class="btn btn-outline-success mb-3" href="<%=request.getContextPath()%>/activar?id=<%= producto.getId()%>">Alta</a>
                    <%
                    	}
                    %>
                    </td>
                </tr>
                <% 
                    }
                %>
            </tbody>
            <tfoot>
                <% 
                } else {
                %>
                <tr>
                    <td colspan="5">No tienes productos</td>
                </tr>
                <% 
                }
                %>
            </tfoot>
        </table>
    
    
    	</div>
    
    </main>
    
    <jsp:include page="../fragmentos/footer.jsp" />