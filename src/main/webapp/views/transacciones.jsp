<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, model.VO.UsuarioVO, model.VO.ProductoVO, model.VO.TransaccionVO, java.util.HashMap" %>
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
		        <h2 class="col">Compras</h2>
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
                    <th>Vendedor</th>
                    <th>Fecha</th>
                    <th>Estado</th>
                    <th></th>
                </tr>
            </thead>
            	<%
                List<TransaccionVO> transacciones = (List<TransaccionVO>) request.getAttribute("transacciones");
                List<ProductoVO> productos = (List<ProductoVO>) request.getAttribute("productos");
                List<UsuarioVO> usuarios = (List<UsuarioVO>) request.getAttribute("usuarios");
                
            	if (!transacciones.isEmpty()) {
            	%>
            <tbody>
            	<%
	                for (TransaccionVO transaccion : transacciones) {
	                	
	                	if (transaccion.getComprador_id() == usuario.getId()) {
	            %>
	            <tr>
	            <%
	                		for (ProductoVO producto : productos) {
	                			
	                			if (transaccion.getProducto_id() == producto.getId()) {
	            %>
	            	<td><img src="<%= request.getContextPath()+producto.getUrl_imagen() %>" class="img-fluid" 
                			style="max-width:50%; max-height: 50px">
                	</td>
                    <td><%= producto.getNombre() %></td>
                    <td><%= String.format("%.2f",producto.getPrecio()) %> €</td>
	            <%
	                			}
	                		}
				            for (UsuarioVO u : usuarios) {
			        			
			        			if (transaccion.getVendedor_id() == u.getId()) {
			    %>
			    	<td><%= u.getNombre() %></td>
			    <%
			        			}
				            }
	            %>
	                <td><%= transaccion.getFecha() %></td>
                    <td><%= transaccion.getEstado() %></td>
                    <td>
                    <%
                    	if (transaccion.getEstado().equals("Pendiente de Envío")) {
                    %>
                    	<a class="btn btn-outline-danger mb-3" href="<%=request.getContextPath()%>/cancelarCompra?id=<%= transaccion.getId()%>">Cancelar</a>
                    <%
                    	}
                    %>
                    </td>
	            <%
	                	}
	                	
	                }
            	%>
            	</tr>
            </tbody>
            <tfoot>
                <% 
                } else {
                %>
                <tr>
                    <td colspan="5">No tienes compras</td>
                </tr>
                <% 
                }
                %>
            </tfoot>
        	</table>
    
    	</div>
    	
    	<div class="container mt-3">
			<div class="row align-items-center">
		        <h2 class="col">Ventas</h2>
			</div>
			<table class="table">
            <thead>
                <tr>
                	<th>Imagen</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Comprador</th>
                    <th>Fecha</th>
                    <th>Estado</th>
                    <th></th>
                </tr>
            </thead>
            	<%
				if (!transacciones.isEmpty()) {
				%>
            <tbody>
            	<%
	                for (TransaccionVO transaccion : transacciones) {
	                	
	                	if (transaccion.getVendedor_id() == usuario.getId()) {
	            %>
	            <tr>
	            <%
	                		for (ProductoVO producto : productos) {
	                			
	                			if (transaccion.getProducto_id() == producto.getId()) {
	            %>
	            	<td><img src="<%= request.getContextPath()+producto.getUrl_imagen() %>" class="img-fluid" 
                			style="max-width:50%; max-height: 50px">
                	</td>
                    <td><%= producto.getNombre() %></td>
                    <td><%= String.format("%.2f",producto.getPrecio()) %> €</td>
	            <%
	                			}
	                		}
				            for (UsuarioVO u : usuarios) {
			        			
			        			if (transaccion.getComprador_id() == u.getId()) {
			    %>
			    	<td><%= u.getNombre() %></td>
			    <%
			        			}
				            }
	            %>
	                <td><%= transaccion.getFecha() %></td>
                    <td><%= transaccion.getEstado() %></td>
                    <td>
                    <%
                    	if (transaccion.getEstado().equals("Pendiente de Envío")) {
                    %>
                    	<a class="btn btn-outline-success mb-3" href="<%=request.getContextPath()%>/aprobarVenta?id=<%= transaccion.getId()%>">Confirmar</a>
                    	<a class="btn btn-outline-danger mb-3" href="<%=request.getContextPath()%>/cancelarVenta?id=<%= transaccion.getId()%>">Cancelar</a>
                    <%
                    	}
                    %>
                    </td>
	            <%
	                	}
	                	
	                }
            	%>
	            </tr>
            </tbody>
            <tfoot>
                <% 
                } else {
                %>
                <tr>
                    <td colspan="5">No tienes ventas</td>
                </tr>
                <% 
                }
                %>
            </tfoot>
        </table>
    
    </main>
    
    <jsp:include page="../fragmentos/footer.jsp" />