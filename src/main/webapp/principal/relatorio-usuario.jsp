<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

  <body>
  <!-- Pre-loader start -->
  <jsp:include page="theme-loader.jsp"></jsp:include>
  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
          
          <jsp:include page="navbar.jsp"></jsp:include>

          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
              
                  <jsp:include page="navbarmainmenu.jsp"></jsp:include>
                  
                  <div class="pcoded-content">
                      <!-- Page-header start -->
                      <jsp:include page="page-header.jsp"></jsp:include>
                      <!-- Page-header end -->
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                        <div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-block">
														<h4 class="sub-title">Relatório de Usuário</h4>

														<form class="form-material" action="<%= request.getContextPath() %>/ServletRelatorioController?acao=imprimirRelatorioUsuario" method="post" id="formUser">
															<div class="row">
																<div class="col-6 col-md-4">
																	<label>Data Inicial</label>
																	<input type="date" class="form-control" id="dataInicial" name="dataInicial" placeholder="Data Inicial" value="${dataInicial}">
																</div>
																<div class="col-6 col-md-4">
																	<label>Data Final</label>
																	<input type="date" class="form-control" id="dataFinal" name="dataFinal" placeholder="Data Final" value="${dataFinal}">
																</div>
																<div class="col align-self-center">
																	<button type="submit" class="btn btn-primary">Pesquisar</button>
																</div>
															</div>
														</form>
														<!-- Tabela com o resultado da pesquisa -->
														<div style="height: 400px; overflow: scroll;">
														<table class="table" id="tabelaresultadosview">
															<thead>
																<tr>
																	<th scope="col">Id</th>
																	<th scope="col">Nome</th>
																	<th scope="col">Telefone</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${listaUsuarios}" var="lista">
																	<tr>
																		<td><c:out value="${lista.id}"></c:out></td>
																		<td><c:out value="${lista.nome}"></c:out></td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>
												</div>
											</div>
                                    <!-- Page-body end -->
                                	</div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Required Jquery -->
    <jsp:include page="javascriptfile.jsp"></jsp:include>
</body>

</html>
