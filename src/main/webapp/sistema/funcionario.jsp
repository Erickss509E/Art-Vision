<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="br.com.artvision.dto.FuncionarioDTO" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="funcionarioService" class="br.com.artvision.services.FuncionarioService" />
<%
    List<FuncionarioDTO> funcionarios = funcionarioService.listarFuncionarios();
    request.setAttribute("funcionarios", funcionarios);
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Listagem de Funcionários - ArtVision</title>
    <link rel="stylesheet" href="../css/Funcionario.css?99999" />
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
</head>
<body>
    <div class="dashboard-container">
        <section class="content-area">
            <div class="content-header">
                <h1>Funcionários</h1>
                <a href="${pageContext.request.contextPath}/sistema/add_funcionario.jsp" class="btn btn-primary">
                    <span class="material-icons">add</span> Adicionar Novo Funcionário
                </a>
            </div>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Foto</th>
                            <th>Matrícula</th>
                            <th>Nome Completo</th>
                            <th>CPF</th>
                            <th>E-mail</th>
                            <th>Cargo</th>
                            <th>Setor</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="func" items="${funcionarios}">
                            <tr>
                                <td>
                                    <img src="${pageContext.request.contextPath}/assets/img/user-01.png"
                                         alt="Foto do Funcionário"
                                         class="table-avatar" />
                                </td>
                                <td>${func.matriculaFunc}</td>
                                <td>${func.nomeFunc}</td>
                                <td>${func.cpfFunc}</td>
                                <td>${func.emailFunc}</td>
                                <td>${func.nomeCargo}</td>
                                <td>${func.nomeSetor}</td>
                                <td class="actions">
                                    <a href="${pageContext.request.contextPath}/sistema/funcionario?action=editar&id_func=${func.idFunc}"
                                       class="btn-icon" title="Editar">
                                        <span class="material-icons">edit</span>
                                    </a>
                                    <a href="${pageContext.request.contextPath}/sistema/funcionario?action=excluir&id_func=${func.idFunc}"
                                       class="btn-icon btn-icon-delete" title="Excluir"
                                       onclick="return confirm('Confirma exclusão?');">
                                        <span class="material-icons">delete</span>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
    </div>
</body>
</html>
