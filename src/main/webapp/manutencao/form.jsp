<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${manutencao == null ? 'Nova' : 'Editar'} Manutenção - ArtVision</title>
    <link rel="stylesheet" type="text/css" href="../styles/style.css">
    <link rel="stylesheet" type="text/css" href="../styles/navigation.css">
    <style>
        .form-container {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            max-width: 600px;
            margin: 0 auto;
        }
        
        .form-group {
            margin-bottom: 15px;
        }
        
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: 500;
            color: #333;
        }
        
        .form-control {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
        }
        
        .form-control:focus {
            border-color: #8c2a44;
            outline: none;
            box-shadow: 0 0 0 2px rgba(140,42,68,0.2);
        }
        
        textarea.form-control {
            min-height: 100px;
            resize: vertical;
        }
        
        .button-group {
            display: flex;
            gap: 10px;
            justify-content: flex-end;
            margin-top: 20px;
        }
        
        .button-secondary {
            background-color: #6c757d;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
        }
        
        .button-secondary:hover {
            background-color: #5a6268;
        }

        .error-message {
            color: #dc3545;
            margin-bottom: 15px;
            padding: 10px;
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            border-radius: 4px;
        }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/includes/header.jsp" />
    
    <div class="container">
        <h1>${manutencao == null ? 'Agendar Nova' : 'Editar'} Manutenção</h1>
        
        <div class="form-container">
            <c:if test="${not empty erro}">
                <div class="error-message">${erro}</div>
            </c:if>
            
            <form action="${pageContext.request.contextPath}/manutencao" method="post">
                <input type="hidden" name="action" value="${manutencao == null ? 'cadastrar' : 'atualizar'}">
                <c:if test="${manutencao != null}">
                    <input type="hidden" name="id_manutencao" value="${manutencao.idManutencao}">
                </c:if>
                
                <div class="form-group">
                    <label for="nome_manutencao">Tipo de Manutenção</label>
                    <select id="nome_manutencao" name="nome_manutencao" class="form-control" required>
                        <option value="">Selecione o tipo</option>
                        <option value="Preventiva" ${manutencao.nomeManutencao == 'Preventiva' ? 'selected' : ''}>Preventiva</option>
                        <option value="Corretiva" ${manutencao.nomeManutencao == 'Corretiva' ? 'selected' : ''}>Corretiva</option>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="data_manutencao">Data da Manutenção</label>
                    <input type="date" id="data_manutencao" name="data_manutencao" class="form-control" 
                           value="<fmt:formatDate value="${manutencao.dataManutencao}" pattern="yyyy-MM-dd"/>" required>
                </div>
                
                <div class="form-group">
                    <label for="id_obra">Obra</label>
                    <select id="id_obra" name="id_obra" class="form-control" required>
                        <option value="">Selecione uma obra</option>
                        <c:forEach var="obra" items="${obras}">
                            <option value="${obra.idObra}" ${obra.idObra == manutencao.idObra ? 'selected' : ''}>
                                ${obra.nomeObra}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="id_func">Funcionário Responsável</label>
                    <select id="id_func" name="id_func" class="form-control" required>
                        <option value="">Selecione um funcionário</option>
                        <c:forEach var="funcionario" items="${funcionarios}">
                            <option value="${funcionario.idFunc}" ${funcionario.idFunc == manutencao.idFunc ? 'selected' : ''}>
                                ${funcionario.nome}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="observacao">Observações</label>
                    <textarea id="observacao" name="observacao" class="form-control">${manutencao.observacao}</textarea>
                </div>
                
                <div class="button-group">
                    <a href="${pageContext.request.contextPath}/manutencao/listar" class="button-secondary">Cancelar</a>
                    <button type="submit" class="button">
                        ${manutencao == null ? 'Agendar' : 'Atualizar'} Manutenção
                    </button>
                </div>
            </form>
        </div>
    </div>
</body>
</html> 