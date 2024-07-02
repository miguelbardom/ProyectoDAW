<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="../../fragmentos/head.jsp" />
<style>
@media (min-width: 991.98px) {
  main {
    padding-left: 240px;
  }
}

/* Sidebar */
.sidebar {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  padding: 58px 0 0; /* Height of navbar */
  box-shadow: 0 2px 5px 0 rgb(0 0 0 / 5%), 0 2px 10px 0 rgb(0 0 0 / 5%);
  width: 240px;
  z-index: 600;
}

@media (max-width: 991.98px) {
  .sidebar {
    width: 100%;
  }
}
.sidebar .active {
  border-radius: 5px;
  box-shadow: 0 2px 5px 0 rgb(0 0 0 / 16%), 0 2px 10px 0 rgb(0 0 0 / 12%);
}

.sidebar-sticky {
  position: relative;
  top: 0;
  height: calc(100vh - 48px);
  padding-top: 0.5rem;
  overflow-x: hidden;
  overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
}
	
</style>
</head>

<body>
	<jsp:include page="../../fragmentos/headerAdmin.jsp" />
	
	<div class="d-flex flex-column flex-shrink-0 p-3 mb-5" style="width: 280px; height: 500px; background-color: #eef5ff;">
    <a href="<%=request.getContextPath()%>/acceso" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
      <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>
      <span class="fs-4">Administración</span>
    </a>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
      <li class="nav-item">
        <a href="#" class="nav-link text-light active btn btn-primary" aria-current="page">
          <svg class="bi me-2" width="16" height="16"><use xlink:href="#home"></use></svg>
          Home
        </a>
      </li>
      <li>
        <a class="nav-link text-dark btn btn-light" href="<%=request.getContextPath()%>/productos">
        	Gestión de productos
    	</a>
      </li>
      <li>
        <a class= "nav-link text-dark btn btn-light" href="<%=request.getContextPath()%>/pedidos">
       		Gestión de pedidos
    	</a>
      </li>
<!--       <li> -->
<%--         <a class= "nav-link text-dark" href="<%=request.getContextPath()%>/categorias"> --%>
<!--        		Gestión de categorías -->
<!--     	</a> -->
<!--       </li> -->
<!--       <li> -->
<%--         <a class="nav-link text-dark" href="<%=request.getContextPath()%>/clientes"> --%>
<!--         	Gestión de clientes -->
<!--     	</a> -->
<!--       </li> -->
    </ul>
    <hr>
    <%
	Object usuarioObj = session.getAttribute("usuarioA");
    model.VO.UsuarioVO usuario = null;
	if (usuarioObj != null) {
		usuario = (model.VO.UsuarioVO) usuarioObj;
	%>
	<% } %>
    <div class="dropdown text-center">
      <a href="#" class="d-flex align-items-center link-dark text-decoration-none dropdown-toggle ms-3" id="dropdownUser2" data-bs-toggle="dropdown" aria-expanded="false">
        <strong><%= usuario.getNombre() %></strong>
      </a>
      <ul class="dropdown-menu text-small shadow" aria-labelledby="dropdownUser2">
<!--         <li><a class="dropdown-item" href="#">New project...</a></li> -->
<!--         <li><a class="dropdown-item" href="#">Settings</a></li> -->
<!--         <li><a class="dropdown-item" href="#">Profile</a></li> -->
<!--         <li><hr class="dropdown-divider"></li> -->
        <li><a class="dropdown-item" href="<%=request.getContextPath()%>/logoutA">Sign out</a></li>
      </ul>
    </div>
  </div>
<!--   <div class="mb-5"><br></div> -->
<!--   <div class="mb-5"><br></div> -->
<!--   <div class="mb-5"><br></div> -->
  
<!--   <div class="container-fluid"> -->
<!--   	<a class="nav-link p-4 text-dark fs-5" href=" /productos"> -->
<!--     	Gestión de productos -->
<!--     </a> -->
<!--     <a class="nav-link p-4 text-dark fs-5" href=" /clientes"> -->
<!--         Gestión de clientes -->
<!--     </a> -->
<!--     <a class= "nav-link p-4 text-dark fs-5" href=" /empleados"> -->
<!--        	Gestión de empleados -->
<!--     </a> -->
<!--     <a class= "nav-link p-4 text-dark fs-5" href=" /categorias"> -->
<!--        	Gestión de categorías -->
<!--     </a> -->
<!--     <a class= "nav-link p-4 text-dark fs-5" href=" /pedidos"> -->
<!--        	Gestión de pedidos -->
<!--     </a> -->
<!--   </div> -->
	
	
	
	<jsp:include page="../../fragmentos/footerAdmin.jsp" />