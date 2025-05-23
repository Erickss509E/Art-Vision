<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Listagem de Cargos - ArtVision</title>
    <link rel="stylesheet" href="../css/cargo.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>

<div class="dashboard-container">
    <main class="main-content">
        <section class="content-area">

            <div class="content-header">
                <h1>Cargos</h1>
                <button onclick="abrirPopupCadastro()" class="btn btn-primary">
                    <span class="material-icons">add</span> Adicionar Novo Cargo
                </button>
            </div>

            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Nome do Cargo</th>
                            <th>Setor</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cargo" items="${cargos}">
                            <tr>
                                <td>${cargo.nome}</td>
                                <td>${cargo.nomeSetor}</td> <!-- cargo.nomeSetor não está no DTO, considerar ajustar -->
                                <td class="actions">
                                    <a href="/sistema/cargo?action=buscar&id_cargo=${cargo.id}" class="btn-icon" title="Editar">
                                        <span class="material-icons">edit</span>
                                    </a>
                                    <a href="/sistema/cargo?action=excluir&id_cargo=${cargo.id}" class="btn-icon btn-icon-delete" title="Excluir"
                                       onclick="return confirm('Tem certeza que deseja excluir este cargo?');">
                                        <span class="material-icons">delete</span>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div id="popup_add" class="popup
                ${cargo != null ? 'opened' : ''}
                ${empty cargo && abrirPopupCadastro == true ? 'opened' : ''}">

                <main class="main-content-popup">
                    <section class="content-area">
                        <div class="content-header-popup">
                            <h1>${cargo != null ? 'Editar Cargo' : 'Adicionar Novo Cargo'}</h1>
                            <button class="btn btn-secondary" onclick="handlePopupCad(false)">
                                <span class="material-icons">arrow_back</span> Voltar
                            </button>
                        </div>

                        <div class="form-container">
                            <form id="formCargo" method="post" action="/sistema/cargo">

                                <input type="hidden" name="action" value="${cargo != null ? 'atualizar' : 'cadastrar'}">
                                <c:if test="${cargo != null}">
                                    <input type="hidden" name="id_cargo" value="${cargo.id}">
                                </c:if>

                                <div class="form-section">
                                    <h3>Informações do Cargo</h3>

                                    <div class="form-group">
                                        <label for="nomeCargo">Nome do Cargo</label>
                                        <input type="text" id="nomeCargo" name="nome_cargo" required value="${cargo != null ? cargo.nome : ''}">
                                    </div>

                                    <div class="form-group">
                                        <label for="setorCargo">Setor</label>
                                        <select id="setorCargo" name="id_setor" required>
                                            <option value="">Selecione o setor</option>
                                            <c:forEach var="setor" items="${setores}">
                                                <option value="${setor.id}"
                                                  ${cargo != null && cargo.idSetor == setor.id ? 'selected' : ''}>
                                                  ${setor.nome}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <button type="submit" class="btn btn-primary">Salvar</button>
                            </form>
                        </div>

                    </section>
                </main>
            </div>

        </section>
    </main>
</div>

<script>
    function handlePopupCad(status) {
        const popup = document.getElementById('popup_add');
        if (status) {
            popup.classList.add('opened');
        } else {
            popup.classList.remove('opened');
            // Redireciona para a lista sem popup aberto
            window.location.href = '/sistema/cargo?action=listar';
        }
    }

    function abrirPopupCadastro() {
        handlePopupCad(true);
        document.getElementById('formCargo').reset();
        document.querySelector('input[name="action"]').value = "cadastrar";
        const inputId = document.querySelector('input[name="id_cargo"]');
        if (inputId) {
            inputId.remove();
        }
    }
</script>

</body>
</html>
