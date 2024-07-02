<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.VO.ProductoVO, java.util.HashMap, java.util.ArrayList, java.util.List,
	model.VO.CategoriaVO,model.DAO.CategoriaDAO"%>

<!-- <header class="d-flex flex-row bg-dark text-white"> -->
<!--         <div class="logo col-3 mx-auto d-flex align-items-center h-50"> -->
<%--             <a class="nav-link text-white" href="<%=request.getContextPath()%>"> --%>
<%-- 	            <img src="<%= request.getContextPath()%>/img/libreria-miguel-ok2.png" class="img-fluid w-75"> --%>
<!--             </a> -->
<!--         </div> -->
        
<header class="d-flex flex-row bg-white text-white align-items-center">
    <div class="col-2 mx-auto text-center">
        <a class="nav-link fs-3 d-inline-block align-middle bg-white" style="color: #192666;" href="<%=request.getContextPath()%>/">
            <img src="<%=request.getContextPath()%>/img/trade2shop.png" class="img-fluid" style="max-height: 40px;">
            <b class="align-middle">Trade2<i>Shop</i></b>
        </a>
    </div>
        
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
                        <li class="nav-item btn-primary">
                            <a class="nav-link p-4 text-dark fs-5" href="<%=request.getContextPath()%>/">
                            	Productos
                            </a>
                        </li>
                        <li class="nav-item dropdown btn-primary">
                            <a class="nav-link dropdown-toggle p-4 text-dark fs-5" href="#" id="navbarDropdownMenuLink"
                                role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Categorías
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <%
//                          	CategoriaDAO categoriaDAO = new CategoriaDAO();
                            List<CategoriaVO> results = new ArrayList<>();
                            results = CategoriaDAO.getAll();
                                                                                    
                            for (CategoriaVO categoria : results){
                            %>
                                <li><a class="dropdown-item" href="<%= request.getContextPath()%>/categoria?id=<%= categoria.getId()%>">
                                	<%= categoria.getNombre() %>
                                	</a>
                                </li>
                            <% 
                            }
                            %>
                            </ul>
                        </li>
                        <li class="nav-item dropdown btn-primary">
                            <a class= "nav-link p-4 text-dark fs-5" href="<%=request.getContextPath()%>/contacto">
                            	Contacto
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        
        <div class="col-3 navbar text-end ps-5">
        	<div class="container-fluid mt-3 mb-3 text-end">
        		<div>
		            <%
		            if (session.getAttribute("usuario") == null) {
		            %>
		            <a class="btn btn-secondary me-2" href="<%=request.getContextPath()%>/login">
		            	Inicia Sesión
		            </a>
		            <a class="btn btn-success" href="<%=request.getContextPath()%>/registro">
		            	Regístrate
		            </a>
		            <%
		            } else {
		            %>
		            <a href="<%=request.getContextPath()%>/bandeja" class="btn btn-light">
		            	<i class="fa fa-envelope"></i>
		            	<span class="badge bg-warning rounded-pill"></span>
		            </a>
		            <a class="btn btn-light" href="<%=request.getContextPath()%>/perfil">
		            	<i class="fa fa-user-circle-o"></i>&nbsp;
		            	Mi Cuenta
		            </a>
		            <a class="btn btn-dark" href="<%=request.getContextPath()%>/logout">
		            	Cerrar Sesión
		            </a>
		            <%
		            }
		            %>
	            </div>
        	</div>
        </div>
    </div>
</header>
