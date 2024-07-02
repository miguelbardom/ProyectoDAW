<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, model.VO.ProductoVO, model.VO.UsuarioVO, java.util.HashMap, java.util.ArrayList, 
    model.VO.CategoriaVO, model.DAO.CategoriaDAO" %>
<!DOCTYPE html>
<html lang="es">
<head>
	<jsp:include page="../fragmentos/head.jsp" />
</head>
<body>
    <jsp:include page="../fragmentos/header.jsp" />
    
    <main class="p-3 m-0 border-0 bd-example m-0 border-0">
      
      <div class="row">
		<div class="col-2">&nbsp;</div>
		<div class="col-7 container mt-3 mb-3">
<!-- 			<h3></h3> -->
			
			<div class="row mt-2">
            	<div class="col mb-4">
                	<div class="card">
                    	<div class="card-body">
                    		
                    	<%
                    	ProductoVO producto = (ProductoVO)request.getAttribute("producto");
                   		if (producto != null) {
                   			
                   		%>
                   		  <div class="row">
                   		  	<div class="col-5 ms-2 me-2">
						        <img src="<%= request.getContextPath()+producto.getUrl_imagen() %>" class="img-fluid" style="min-width:25%; max-height:450px">
		                        <br><br>

                   		  	</div>
                   		  	<div class="col-6">
	                    		<h3 class="card-title justify-content"><%= producto.getNombre() %></h3>
		                        <p class="card-text">
		                        <%
		                            List<CategoriaVO> results = new ArrayList<>();
		                            results = CategoriaDAO.getAll();
		                                                                                    
		                            for (CategoriaVO categoria : results){
		                            	if (categoria.getId() == producto.getCategoria_id()) {
		                            %>
		                                Categoría: <%= categoria.getNombre() %>
		                            <% 
		                            }}
		                           %>
		                        </p>
		                        <hr>
		                        <p>Descripción:</p>
		                        <p class="card-text justify-content" style="white-space: pre-line;"><%= producto.getDescripcion() %></p>
		                        <hr>
		                    	<div class="d-flex justify-content-between">
			                    	<% UsuarioVO usuarioPro = (UsuarioVO)request.getAttribute("usuarioPro"); %>
			                        <div>
				                        <p class="card-text">Vendedor: <%= usuarioPro.getNombre() %></p>
				                        <form method="post" action="chat" class="d-flex justify-content-start align-items-center">
				                            <input type="hidden" id="id" name="id" value="<%= producto.getId() %>">
				                            <input type="hidden" id="idUser" name="idUser" value="<%= producto.getUsuario_id() %>">
				                            <button type="submit" class="btn btn-primary">Mensaje</button>
				                        </form>
			                        </div>
			                        <div class="text-end">
				                        <p class="card-text">Precio: <%= producto.getPrecio() %> €</p>
				                        <form method="get" action="comprar" class="d-flex justify-content-end align-items-center">
				                            <input type="hidden" id="id" name="id" value="<%= producto.getId() %>">
				                            <input type="hidden" id="vista" name="vista" value="producto">
				                            <button type="submit" class="btn btn-success">Comprar</button>
				                        </form>
			                        </div>
		                        </div>
                   		  	</div>
                   		  </div>
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
	    				} else {
	    					
						%>
						<p class="card-text">No existe el producto seleccionado</p>
						<%
	    				}
						%>
						</div>
					</div>
				</div>
			</div>
    	</div>
    	<div class="col-2">&nbsp;</div>
      </div>
    </main>
    
    <jsp:include page="../fragmentos/footer.jsp" />