<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.VO.ProductoVO, java.util.HashMap, java.util.ArrayList, java.util.List,
	model.VO.CategoriaVO, model.DAO.CategoriaDAO"%>

<head>
    <script>
//     document.addEventListener("DOMContentLoaded", function() {
//         // Obtener referencia al elemento select
//         let selectIdioma = document.getElementById("idioma");

//         // Adjuntar un controlador de eventos para el evento change
//         selectIdioma.addEventListener("change", function() {
//             // Obtener el formulario
//             let form = document.getElementById("form_lang");

//             // Enviar el formulario
//             form.submit();
//         });
//     });
	</script>
</head>
        
<header class="d-flex flex-row text-dark align-items-center" style="background-color: #5b9aff; height: 90px;">
    <div class="col-2 mx-auto text-center">
        <a class="nav-link fs-3 d-inline-block align-middle" href="<%=request.getContextPath()%>/acceso">
        	<img src="<%=request.getContextPath()%>/img/trade2shop.png" class="img-fluid" style="max-height: 40px;">
            <b class="align-middle">Trade2<i>Shop</i></b>
        </a>
    </div>
        
        
<!--         <nav class="col-4 navbar navbar-expand-xl text-light mx-auto d-flex align-items-center"> -->
<!-- 		  <div class="container-fluid"> -->
<!-- 		    <a class="navbar-brand" href="#">Navbar</a> -->
<!-- 		    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarBasic" aria-controls="navbarBasic" aria-expanded="false" aria-label="Toggle navigation"> -->
<!-- 		      <span class="navbar-toggler-icon"></span> -->
<!-- 		    </button> -->
<!-- 		    <div class="collapse navbar-collapse show" id="navbarBasic"> -->
<!-- 		      <ul class="navbar-nav me-auto mb-2 mb-xl-0"> -->
<!-- 		        <li class="nav-item"> -->
<!-- 		          <a class="nav-link active" aria-current="page" href="#">Home</a> -->
<!-- 		        </li> -->
<!-- 		        <li class="nav-item"> -->
<!-- 		          <a class="nav-link" href="#">Link</a> -->
<!-- 		        </li> -->
<!-- 		      </ul> -->
<!-- 		    </div> -->
<!-- 		  </div> -->
<!-- 		</nav> -->
		
		<nav class="menuPrin col-4 navbar navbar-expand-lg mx-auto d-flex align-items-center text-dark"
            aria-label="Fourth navbar example">
            <div class="container-fluid text-dark">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarWithDropdown" aria-controls="navbarWithDropdown" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarWithDropdown">
                    <ul class="navbar-nav align-items-center">
<!--                         <li class="nav-item btn-primary"> -->
<%--                             <a class="nav-link p-4 text-dark fs-5" href="<%=request.getContextPath()%>/productos"> --%>
<!--                             	Gestión de productos -->
<!--                             </a> -->
<!--                         </li> -->
<!--                         <li class="nav-item dropdown btn-primary"> -->
<%--                             <a class="nav-link p-4 text-dark fs-5" href="<%=request.getContextPath()%>/clientes"> --%>
<!--                                 Gestión de clientes -->
<!--                             </a> -->
<!--                         </li> -->
<!--                         <li class="nav-item dropdown btn-primary"> -->
<%--                             <a class= "nav-link p-4 text-dark fs-5" href="<%=request.getContextPath()%>/empleados"> --%>
<!--                             	Gestión de empleados -->
<!--                             </a> -->
<!--                         </li> -->
<!--                         <li class="nav-item dropdown btn-primary"> -->
<%--                             <a class= "nav-link p-4 text-dark fs-5" href="<%=request.getContextPath()%>/categorias"> --%>
<!--                             	Gestión de categorías -->
<!--                             </a> -->
<!--                         </li> -->
                        <li class="nav-item dropdown btn-primary">
<%--                             <a class= "nav-link p-4 text-dark fs-5" href="<%=request.getContextPath()%>/pedidos"> --%>
<!--                             	Gestión de pedidos -->
<!--                             </a> -->
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        
        <div class="col-3 navbar text-end ps-5">
        	<div class="container-fluid m-3 text-end">
	            <%
	            if (session.getAttribute("usuarioA") == null) {
	            %>
<%-- 	            <a class="btn btn-secondary" href="<%=request.getContextPath()%>/login"> --%>
<%-- 	            	<% if (session.getAttribute("login") != null) { %> --%>
<%--                     	<%= session.getAttribute("login") %> --%>
<%--                     <% } %> --%>
<!-- <!-- 	            	Inicia Sesión -->
<!-- 	            </a> -->
<%-- 	            <a class="btn btn-success" href="<%=request.getContextPath()%>/registro"> --%>
<%-- 	            	<% if (session.getAttribute("registro") != null) { %> --%>
<%--                     	<%= session.getAttribute("registro") %> --%>
<%--                     <% } %> --%>
<!-- <!-- 	            	Regístrate -->
<!-- 	            </a> -->
	            <%
	            } else {
	            %>
<%-- 	            <a class="btn btn-light" href="<%=request.getContextPath()%>/perfil"> --%>
<!-- 	            	<i class="fa fa-user-circle-o"></i>&nbsp; -->
<%-- 	            	<% if (session.getAttribute("cuenta") != null) { %> --%>
<%--                     	<%= session.getAttribute("cuenta") %> --%>
<%--                     <% } %> --%>
<!-- <!-- 	            	Mi Cuenta -->
<!-- 	            </a> -->
	            <a class="btn btn-dark" href="<%=request.getContextPath()%>/logoutA">
	            	Cerrar Sesión
	            </a>
	            <%
	            }
	            %>
        	</div>
        </div>
        
</header>
