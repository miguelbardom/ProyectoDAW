<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, model.VO.ProductoVO, model.VO.CategoriaVO, model.VO.UsuarioVO, model.VO.MensajeVO, 
    java.util.HashMap, java.util.ArrayList" %>
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
				<div class="col-3">&nbsp;</div>
            	<div class="col-6 mb-4 row">
				  <h3>Bandeja de entrada</h3>
				  <div class="card pt-2" style="min-height: 450px;">
					<% UsuarioVO usuario = (UsuarioVO) session.getAttribute("usuario");
					 ArrayList<MensajeVO> chats = (ArrayList<MensajeVO>) request.getAttribute("chats"); 
					 ArrayList<UsuarioVO> usuarios = (ArrayList<UsuarioVO>) request.getAttribute("usuarios"); %>
					 
					<% if (chats != null && !chats.isEmpty()) { %>
			        <% for (MensajeVO chat : chats) { %>
			            <% 
			                UsuarioVO usuarioChat = null;
			                if (chat.getReceptor_id() == usuario.getId()) {
			                    for (UsuarioVO usuar : usuarios) {
			                        if (chat.getEmisor_id() == usuar.getId()) {
			                            usuarioChat = usuar;
			                            break;
			                        }
			                    }
			                } else {
			                    for (UsuarioVO usuar : usuarios) {
			                        if (chat.getReceptor_id() == usuar.getId()) {
			                            usuarioChat = usuar;
			                            break;
			                        }
			                    }
			                }
			            %>
		            <div class="card mb-2 list-group-item list-group-item-primary">
		           	  <form method="post" action="chat" class="">
		 				<button type="submit" class="nav-link btn btn-light h-100 w-100" href="<%=request.getContextPath()%>/chat">
		                    <div class="card-body d-flex justify-content-between">
		                        <p class="card-text text-start mb-0">
                            	<%= usuarioChat != null ? usuarioChat.getNombre() : "Usuario no encontrado" %>
                            	</p>
                            	<p class="card-text text-end mb-0">
                            	<%= new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(chat.getFecha_envio()) %>
                            	</p>
                            </div>
						</button>
						<input type="hidden" id="idUser" name="idUser" value="<%= usuarioChat.getId() %>">
						<input type="hidden" id="id" name="id" value="-1">
					  </form>
					</div>
					<%
						}
					} else {
					%>
						<h5>No tienes conversaciones.</h5>
					<%
					}
					%>
				  </div>
    			</div>
    			<div class="col-3">&nbsp;</div>
    		</div>
    		
    	</div>
    	
    </main>
    
	<jsp:include page="../fragmentos/footer.jsp" />