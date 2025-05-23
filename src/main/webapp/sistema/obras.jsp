<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listagem de Obras - ArtVision</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/obras.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<div class="dashboard-container">
    <main class="main-content">
        <section class="content-area">
            <div class="content-header">
                <h1>Obras</h1>
                <a href="${pageContext.request.contextPath}/sistema/obra?action=novo" class="btn btn-primary">
                    <span class="material-icons">add</span> Adicionar Nova Obra
                </a>
            </div>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Nome da Obra</th>
                            <th>Autor</th>
                            <th>Data de Entrada</th>
                            <th>Valor Estimado</th>
                            <th>Localização</th>
                            <th>Área do Museu</th>
                            <th>Última Manutenção</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="obra" items="${obras}">
                            <tr>
                                <td>${obra.nome}</td>
                                <td>${obra.nomeAutor}</td>
                                <td><fmt:formatDate value="${obra.dataEntradaMuseu}" pattern="dd/MM/yyyy"/></td>
                                <td>R$ <fmt:formatNumber value="${obra.valorEstimado}" type="number" minFractionDigits="2" maxFractionDigits="2"/></td>
                                <td>${obra.localizacao}</td>
                                <td>${obra.areaMuseu}</td>
                                <td><fmt:formatDate value="${obra.ultimaManutencao}" pattern="dd/MM/yyyy"/></td>
                                <td class="actions">
                                    <a href="${pageContext.request.contextPath}/sistema/obra?action=editar&id=${obra.id}" class="btn-icon" title="Editar">
                                        <span class="material-icons">edit</span>
                                    </a>
                                    <a href="${pageContext.request.contextPath}/sistema/obra?action=excluir&id=${obra.id}" 
                                       class="btn-icon" 
                                       title="Excluir"
                                       onclick="return confirm('Tem certeza que deseja excluir esta obra?')">
                                        <span class="material-icons">delete</span>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
    </main>
</div>
</body>
</html>
