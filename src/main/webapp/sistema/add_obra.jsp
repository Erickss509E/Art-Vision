<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="br.com.artvision.dto.SetorDTO" %>
<%@page import="br.com.artvision.dto.FuncionarioDTO" %>
<%@page import="br.com.artvision.services.SetorService" %>
<%@page import="br.com.artvision.services.FuncionarioService" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="setorService" class="br.com.artvision.services.SetorService" />
<jsp:useBean id="funcionarioService" class="br.com.artvision.services.FuncionarioService" />
<%
    List<SetorDTO> setores = setorService.listarSetor();
    request.setAttribute("setores", setores);
    List<FuncionarioDTO> funcionarios = funcionarioService.listarFuncionarios();
    request.setAttribute("funcionarios", funcionarios);
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulário de Obra - ArtVision</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/add_obra.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<div class="dashboard-container">
    <main class="main-content">
        <section class="content-area">
            <div class="content-header">
                <h1>${obra != null ? 'Editar' : 'Adicionar Nova'} Obra</h1>
                <a href="${pageContext.request.contextPath}/sistema/obra?action=listar" class="btn btn-secondary">
                    <span class="material-icons">arrow_back</span> Voltar para Listagem
                </a>
            </div>
            <div class="form-container">
                <form action="${pageContext.request.contextPath}/sistema/obra" method="post">
                    <input type="hidden" name="action" value="${obra != null ? 'atualizar' : 'cadastrar'}">
                    <c:if test="${obra != null}">
                        <input type="hidden" name="id" value="${obra.id}">
                    </c:if>

                    <div class="form-section">
                        <h3>Detalhes da Obra</h3>
                        <div class="form-row">
                            <div class="form-group input-half">
                                <label for="nome_obra">Nome da Obra</label>
                                <input type="text" id="nome_obra" name="nome_obra" value="${obra.nomeObra}" required>
                            </div>
                            <div class="form-group input-half">
                                <label for="nome_autor">Nome do Autor</label>
                                <input type="text" id="nome_autor" name="nome_autor" value="${obra.nomeAutor}" required>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group input-half">
                                <label for="data_entrada">Data de Entrada</label>
                                <input type="date" id="data_entrada" name="data_entrada" 
                                       value="<fmt:formatDate value="${obra.dataEntrada}" pattern="yyyy-MM-dd"/>" required>
                            </div>
                            <div class="form-group input-half">
                                <label for="data_saida">Data de Saída</label>
                                <input type="date" id="data_saida" name="data_saida" 
                                       value="<fmt:formatDate value="${obra.dataSaida}" pattern="yyyy-MM-dd"/>">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group input-half">
                                <label for="valor_estimado">Valor Estimado (R$)</label>
                                <input type="number" id="valor_estimado" name="valor_estimado" 
                                       value="${obra.valorEstimado}" step="0.01" min="0" required>
                            </div>
                            <div class="form-group input-half">
                                <label for="id_setor">Setor</label>
                                <select id="id_setor" name="id_setor" required>
                                    <option value="">Selecione o Setor</option>
                                    <c:forEach var="setor" items="${setores}">
                                        <option value="${setor.id}" ${setor.id == obra.idSetor ? 'selected' : ''}>
                                            ${setor.nome}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group input-half">
                                <label for="id_func">Funcionário Responsável</label>
                                <select id="id_func" name="id_func" required>
                                    <option value="">Selecione o Funcionário</option>
                                    <c:forEach var="funcionario" items="${funcionarios}">
                                        <option value="${funcionario.idFunc}" ${funcionario.idFunc == obra.idFunc ? 'selected' : ''}>
                                            ${funcionario.nomeFunc}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group input-half">
                                <label for="id_usuario">Usuário</label>
                                <input type="hidden" id="id_usuario" name="id_usuario" value="${sessionScope.usuario.id}">
                                <input type="text" value="${sessionScope.usuario.nome}" disabled>
                            </div>
                        </div>
                    </div>

                    <div class="form-actions">
                        <button type="reset" class="btn btn-cancel">Cancelar</button>
                        <button type="submit" class="btn btn-primary">Salvar Obra</button>
                    </div>
                </form>
            </div>
        </section>
    </main>
</div>
</body>
</html>