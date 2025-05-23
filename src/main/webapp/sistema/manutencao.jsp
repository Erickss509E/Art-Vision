<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agenda de Serviços</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            padding-bottom: 20px;
            border-bottom: 1px solid #eee;
        }
        h2 {
            margin: 0;
            color: #333;
            font-size: 24px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #fff;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #eee;
        }
        th {
            background-color: #f8f9fa;
            color: #333;
            font-weight: 600;
        }
        tr:hover {
            background-color: #f8f9fa;
        }
        .btn {
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            display: inline-flex;
            align-items: center;
            gap: 8px;
            text-decoration: none;
            transition: background-color 0.3s;
        }
        .btn-primary {
            background-color: #007bff;
            color: white;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        .actions {
            display: flex;
            gap: 8px;
        }
        .edit-btn {
            background-color: #28a745;
            color: white;
        }
        .edit-btn:hover {
            background-color: #218838;
        }
        .delete-btn {
            background-color: #dc3545;
            color: white;
        }
        .delete-btn:hover {
            background-color: #c82333;
        }
        .status-badge {
            padding: 4px 8px;
            border-radius: 4px;
            font-size: 12px;
            font-weight: 500;
            text-transform: uppercase;
        }
        .status-agendada {
            background-color: #e3f2fd;
            color: #1976d2;
        }
        .status-pendente {
            background-color: #fff3e0;
            color: #f57c00;
        }
        .status-concluida {
            background-color: #e8f5e9;
            color: #388e3c;
        }
        .status-cancelada {
            background-color: #ffebee;
            color: #d32f2f;
        }
        .empty-state {
            text-align: center;
            padding: 40px;
            color: #666;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h2>Agenda de Serviços</h2>
            <button class="btn btn-primary" onclick="abrirModal()">+ Agendar Novo</button>
        </div>

        <table>
            <thead>
                <tr>
                    <th>Obra</th>
                    <th>Tipo</th>
                    <th>Data</th>
                    <th>Responsável</th>
                    <th>Status</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty manutencoes}">
                        <tr>
                            <td colspan="6" class="empty-state">Nenhum serviço encontrado</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="manutencao" items="${manutencoes}">
                            <tr>
                                <td>${manutencao.nomeObra}</td>
                                <td>${manutencao.nomeManutencao}</td>
                                <td><fmt:formatDate value="${manutencao.dataManutencao}" pattern="dd/MM/yyyy"/></td>
                                <td>${manutencao.nomeFuncionario}</td>
                                <td>
                                    <c:set var="statusClass" value="${fn:toLowerCase(manutencao.status)}"/>
                                    <span class="status-badge status-${statusClass}">${manutencao.status}</span>
                                </td>
                                <td class="actions">
                                    <button class="btn edit-btn" onclick="editarServico(${manutencao.idManutencao})">Editar</button>
                                    <button class="btn delete-btn" onclick="excluirServico(${manutencao.idManutencao})">Excluir</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>

    <script>
        function abrirModal() {
            window.location.href = '${pageContext.request.contextPath}/sistema/manutencao/novo';
        }

        function editarServico(id) {
            window.location.href = '${pageContext.request.contextPath}/sistema/manutencao/editar?id=' + id;
        }

        function excluirServico(id) {
            if (confirm('Tem certeza que deseja excluir este serviço?')) {
                window.location.href = '${pageContext.request.contextPath}/sistema/manutencao/excluir?id=' + id;
            }
        }
    </script>
</body>
</html>
