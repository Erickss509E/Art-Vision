<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manutenções - ArtVision</title>
    <link rel="stylesheet" type="text/css" href="../styles/style.css">
    <link rel="stylesheet" type="text/css" href="../styles/navigation.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <style>
        .status {
            padding: 5px 10px;
            border-radius: 4px;
            font-size: 14px;
            font-weight: 500;
        }
        
        .status-agendada {
            background-color: #ffd700;
            color: #000;
        }
        
        .status-concluida {
            background-color: #4CAF50;
            color: white;
        }
        
        .status-pendente {
            background-color: #ff9800;
            color: white;
        }
        
        .actions {
            display: flex;
            gap: 8px;
        }
        
        .btn-icon {
            background: none;
            border: none;
            cursor: pointer;
            padding: 4px;
            border-radius: 4px;
            transition: background-color 0.3s;
            color: inherit;
            text-decoration: none;
        }
        
        .btn-icon:hover {
            background-color: #f0f0f0;
        }
        
        .btn-icon-delete:hover {
            background-color: #ffebee;
            color: #d32f2f;
        }
        
        .material-icons {
            font-size: 20px;
            vertical-align: middle;
        }

        .button {
            display: inline-flex;
            align-items: center;
            gap: 8px;
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border-radius: 4px;
            text-decoration: none;
            font-weight: 500;
            transition: background-color 0.3s;
        }

        .button:hover {
            background-color: #0056b3;
        }

        h1 {
            margin-bottom: 20px;
        }

        .button-container {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/includes/header.jsp" />
    
    <div class="container">
        <h1>Manutenções Agendadas</h1>
        <div class="button-container">
            <a href="${pageContext.request.contextPath}/manutencao/novo" class="button">
                <span class="material-icons">add</span>
                Agendar Nova Manutenção
            </a>
        </div>
        
        <table>
            <thead>
                <tr>
                    <th>Obra</th>
                    <th>Tipo de Manutenção</th>
                    <th>Data Agendada</th>
                    <th>Responsável</th>
                    <th>Status</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="manutencao" items="${manutencoes}">
                    <tr>
                        <td>${manutencao.nomeObra}</td>
                        <td>${manutencao.nomeManutencao}</td>
                        <td><fmt:formatDate value="${manutencao.dataManutencao}" pattern="dd/MM/yyyy" type="date"/></td>
                        <td>${manutencao.nomeFuncionario}</td>
                        <td>
                            <span class="status status-${manutencao.status.toLowerCase()}">${manutencao.status}</span>
                        </td>
                        <td class="actions">
                            <a href="${pageContext.request.contextPath}/manutencao/editar?id=${manutencao.idManutencao}" class="btn-icon" title="Editar">
                                <span class="material-icons">edit</span>
                            </a>
                            <a href="${pageContext.request.contextPath}/manutencao/excluir?id=${manutencao.idManutencao}" 
                               class="btn-icon btn-icon-delete" 
                               title="Excluir" 
                               onclick="return confirm('Tem certeza que deseja excluir esta manutenção?')">
                                <span class="material-icons">delete</span>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html> 