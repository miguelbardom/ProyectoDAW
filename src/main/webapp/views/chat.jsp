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
            	<div class="col-6 mb-4">
					<h3 class="ms-2">Chat</h3>
                	<div class="card">
                		<% UsuarioVO vendedor = (UsuarioVO) request.getAttribute("vendedor"); %>
                		<% UsuarioVO usuario = (UsuarioVO) session.getAttribute("usuario"); %>
                		<div class="card-header d-flex justify-content-between">
                			<h4><%= vendedor.getNombre() %></h4>
                			<h4><%= usuario.getNombre() %></h4>
                		</div>
                    	<% ArrayList<MensajeVO> lista = (ArrayList<MensajeVO>) request.getAttribute("lista"); %>
                    	<div class="card-body">
                    	<%
                    	if (!lista.isEmpty()) {
                    		for (MensajeVO mensaje : lista) {
                    			if (mensaje.getEmisor_id() == usuario.getId()) {
                    	%>
                    		<div class="d-flex justify-content-end">
                    		<div class="alert alert-light w-50 text-start" style="white-space: pre-line;" role="alert"><%= mensaje.getTexto() %></div>
                    		</div>
                    	<%
                    			}  
                    				if (mensaje.getEmisor_id() == vendedor.getId()) {
                    	%>
                    		<div class="d-flex justify-content-start">
                    		<div class="alert alert-primary w-50 text-start" style="white-space: pre-line;" role="alert"><%= mensaje.getTexto() %></div>
                    		</div>
                    	<%
                    				}
                    		}
                    	}
                    	%>
                    	</div>
                    	<div class="card-footer">
                    		<form method="post" action="mensaje" class="d-flex justify-content-start align-items-center">
                    			<input type="text" name="mensaje" id="mensaje" class="form-control">
                    			<input type="hidden" id="id" name="id" value="-1">
                    			<input type="hidden" name="idUser" id="idUser" value="<%= vendedor.getId() %>">
                    			<button type="submit" class="btn btn-primary container" style="width: 19%;">
                    				Enviar&nbsp;&nbsp;<i class="fa fa-paper-plane" style="color: white;"></i>
<!-- 	                    			<i class="fa fa-send" style="color: #0091ff;"></i> -->
                    			</button>
                    		</form>
                		</div>
                    </div>
                    <form method="post" action="chat" class="d-flex justify-content-start align-items-center mt-2">
                    	<input type="hidden" id="id" name="id" value="-1">
                    	<input type="hidden" name="idUser" id="idUser" value="<%= vendedor.getId() %>">
                    	<button type="submit" class="btn btn-outline-warning container" style="width: 8%;">
                   			<i class="fa fa-refresh"></i>
               			</button>
               		</form>
                </div>
                <div class="col-3">&nbsp;</div>
            </div>
            
        </div>
        
    </main>
    
    <jsp:include page="../fragmentos/footer.jsp" />