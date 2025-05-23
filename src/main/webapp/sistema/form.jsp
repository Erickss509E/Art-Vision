<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${manutencao == null ? 'Novo Serviço' : 'Editar Serviço'} - ArtVision</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manutencoes_formulario.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
    <div class="dashboard-container">
        <main class="main-content">
            <section class="content-area">
                <div class="content-header">
                    <h1>${manutencao == null ? 'Agendar Novo Serviço' : 'Editar Serviço'}</h1>
                    <a href="${pageContext.request.contextPath}/sistema/manutencao" class="btn btn-secondary">
                        <span class="material-icons">arrow_back</span> Voltar para Listagem
                    </a>
                </div>

                <c:if test="${not empty erro}">
                    <div class="alert alert-danger" role="alert">
                        ${erro}
                    </div>
                </c:if>

                <div class="form-container">
                    <form id="formServico" action="${pageContext.request.contextPath}/sistema/manutencao" method="post">
                        <input type="hidden" name="action" value="${manutencao == null ? 'cadastrar' : 'atualizar'}">
                        <c:if test="${manutencao != null}">
                            <input type="hidden" name="id_manutencao" value="${manutencao.idManutencao}">
                        </c:if>

                        <div class="form-section">
                            <h3>Detalhes do Serviço</h3>
                            
                            <div class="form-group">
                                <label for="id_obra">Obra de Arte</label>
                                <select id="id_obra" name="id_obra" required>
                                    <option value="" disabled ${manutencao == null ? 'selected' : ''}>Selecione a Obra</option>
                                    <c:forEach var="obra" items="${obras}">
                                        <option value="${obra.idObra}" 
                                            ${manutencao.idObra == obra.idObra ? 'selected' : ''}>
                                            ${obra.nomeObra}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="nome_manutencao">Tipo de Serviço</label>
                                <select id="nome_manutencao" name="nome_manutencao" required>
                                    <option value="" disabled ${manutencao == null ? 'selected' : ''}>Selecione o Tipo</option>
                                    <option value="Preventiva" ${manutencao.nomeManutencao == 'Preventiva' ? 'selected' : ''}>Preventiva</option>
                                    <option value="Corretiva" ${manutencao.nomeManutencao == 'Corretiva' ? 'selected' : ''}>Corretiva</option>
                                    <option value="Restauração" ${manutencao.nomeManutencao == 'Restauração' ? 'selected' : ''}>Restauração</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="data_manutencao">Data Agendada</label>
                                <input type="date" id="data_manutencao" name="data_manutencao" 
                                       value="<fmt:formatDate value='${manutencao.dataManutencao}' pattern='yyyy-MM-dd'/>" required>
                            </div>

                            <div class="form-group">
                                <label for="id_func">Responsável</label>
                                <select id="id_func" name="id_func" required>
                                    <option value="" disabled ${manutencao == null ? 'selected' : ''}>Selecione o Responsável</option>
                                    <c:forEach var="funcionario" items="${funcionarios}">
                                        <option value="${funcionario.idFunc}" 
                                            ${manutencao.idFunc == funcionario.idFunc ? 'selected' : ''}>
                                            ${funcionario.nomeFuncionario}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="status">Status</label>
                                <select id="status" name="status" required>
                                    <option value="Agendada" ${manutencao.status == 'Agendada' ? 'selected' : ''}>Agendada</option>
                                    <option value="Em Andamento" ${manutencao.status == 'Em Andamento' ? 'selected' : ''}>Em Andamento</option>
                                    <option value="Concluída" ${manutencao.status == 'Concluída' ? 'selected' : ''}>Concluída</option>
                                    <option value="Pendente" ${manutencao.status == 'Pendente' ? 'selected' : ''}>Pendente</option>
                                    <option value="Cancelada" ${manutencao.status == 'Cancelada' ? 'selected' : ''}>Cancelada</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="observacao">Descrição Detalhada</label>
                                <textarea id="observacao" name="observacao" rows="4" 
                                    placeholder="Descreva os procedimentos a serem realizados...">${manutencao.observacao}</textarea>
                            </div>
                        </div>

                        <div class="form-actions">
                            <a href="${pageContext.request.contextPath}/sistema/manutencao" class="btn btn-cancel">Cancelar</a>
                            <button type="submit" class="btn btn-primary">
                                <span class="material-icons">save</span>
                                ${manutencao == null ? 'Salvar Serviço' : 'Atualizar Serviço'}
                            </button>
                        </div>
                    </form>
                </div>
            </section>
        </main>
    </div>

    <script>
        document.getElementById('formServico').addEventListener('submit', function(e) {
            if (!confirm('Deseja realmente ' + (document.querySelector('input[name="action"]').value === 'cadastrar' ? 'cadastrar' : 'atualizar') + ' este serviço?')) {
                e.preventDefault();
            }
        });
    </script>
</body>
</html> 