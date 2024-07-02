<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, model.VO.ProductoVO, model.VO.CategoriaVO, java.util.HashMap" %>
<!DOCTYPE html>
<html lang="es">
<head>
	<jsp:include page="../fragmentos/head.jsp" />
</head>
<body>
    <jsp:include page="../fragmentos/header.jsp" />
    
    <main role="main" class="p-3 m-0 border-0 bd-example m-0 border-0 mb-1">
    	
    	<div class="container mt-3">
			
			<div class="row mt-2">
				<div class="col-2">&nbsp;</div>
            	<div class="col-8 mb-4">
					<h3>Solicitud de compra</h3>
                	<div class="card">
                    	<div class="card-body">
                    		<div>
                    			El producto ha sido solicitado a su vendedor para su compra. <br>
                    			Una vez se apruebe la compra, se completará el pedido del producto.
                    		</div>
                    		<br>
                    		
                    		<div class="accordion w-75" id="accordionPanelsStayOpen">
							    <div class="accordion-item">
							        <div class="accordion-item">
							            <h2 class="accordion-header">
							                <button class="accordion-button" type="button"
							                    data-bs-toggle="collapse"
							                    data-bs-target="#panelsStayOpen-collapseTwo"
							                    aria-expanded="true" aria-controls="panelsStayOpen-collapseTwo">
							                    Resumen del producto
							                </button>
							            </h2>
							            <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse show">
							                <div class="accordion-body">
							                    <div class="container mt-0">
							                        <%
							                            ProductoVO producto = (ProductoVO) request.getAttribute("producto");
							                            if (producto != null) {
							                        %>
							                        <table class="table w-75">
							                            <thead>
							                                <tr>
							                                    <th>Imagen</th>
							                                    <th>Nombre</th>
							                                    <th>Precio</th>
							                                    <th></th>
							                                </tr>
							                            </thead>
							                            <tbody>
							                                <tr>
							                                    <td>
							                                        <img src="<%= request.getContextPath() + producto.getUrl_imagen() %>" class="img-fluid" 
							                                            style="max-width:50%; max-height: 50px">
							                                    </td>
							                                    <td><%=producto.getNombre() %></td>
							                                    <td><h5> <%= String.format("%.2f", producto.getPrecio()) %> €</h5></td>
							                                    <td></td>
							                                </tr>
							                        </table>
							                        <% 
							                            } else {
							                        %>
							                        <div>No hay producto.</div>
							                        <% 
							                            }
							                        %>
							                    </div>
							                </div>
							            </div>
							        </div>
							    </div>
							</div>
                    		
							<br>
							
							<div>
								El vendedor ha sido notificado automáticamente a través de un mensaje de chat.<br>
								Puedes comunicarse con él por este medio dirigiéndote a la bandeja de chats o siguiendo el siguiente enlace:
							</div>
							<br>
							<form method="post" action="chat" class="d-flex justify-content-start align-items-center">
				                <input type="hidden" id="id" name="id" value="<%= producto.getId() %>">
				                <input type="hidden" id="idUser" name="idUser" value="<%= producto.getUsuario_id() %>">
				                <button type="submit" class="btn btn-primary">Mensaje</button>
				            </form>
							
                    	</div>
                    </div>
                </div>
                <div class="col-2">&nbsp;</div>
            </div>
        </div>
    	
    </main>

    <jsp:include page="../fragmentos/footer.jsp" />