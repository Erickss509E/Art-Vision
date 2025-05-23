<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Obras - ArtVision</title>
    <link rel="stylesheet" type="text/css" href="../styles/style.css">
    <script>
        function corrigirEncoding(idObra) {
            fetch('/corrigirEncoding', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: 'idObra=' + idObra
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('Nome corrigido com sucesso!');
                    window.location.reload();
                } else {
                    alert('Erro ao corrigir o nome: ' + data.message);
                }
            })
            .catch(error => {
                alert('Erro ao fazer a requisição: ' + error);
            });
        }
    </script>
</head>
<body>
    <jsp:include page="/WEB-INF/includes/header.jsp" />
    
    <div class="container">
        <h1>Obras</h1>
        <div class="button-container">
            <a href="cadastrar.jsp" class="button">+ Adicionar Nova Obra</a>
        </div>
        
        <table>
            <thead>
                <tr>
                    <th>Nome da Obra</th>
                    <th>Autor</th>
                    <th>Data de Entrada</th>
                    <th>Valor Estimado</th>
                    <th>Setor</th>
                    <th>Funcionário</th>
                    <th>Usuário</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="obra" items="${obras}">
                    <tr>
                        <td>${obra.nomeObra}</td>
                        <td>${obra.autor}</td>
                        <td>${obra.dataEntrada}</td>
                        <td>${obra.valorEstimado}</td>
                        <td>${obra.nomeSetor}</td>
                        <td>${obra.nomeFuncionario}</td>
                        <td>${obra.nomeUsuario}</td>
                        <td>
                            <a href="editar?id=${obra.idObra}" class="edit-icon"><img src="../images/edit-icon.png" alt="Editar"></a>
                            <a href="excluir?id=${obra.idObra}" class="delete-icon"><img src="../images/delete-icon.png" alt="Excluir"></a>
                            <c:if test="${obra.nomeObra.contains('Ã')}">
                                <button onclick="corrigirEncoding(${obra.idObra})" class="fix-encoding-button">Corrigir Encoding</button>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html> 