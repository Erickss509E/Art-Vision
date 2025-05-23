<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listagem de Departamentos - ArtVision</title>
    <link rel="stylesheet" href="../css/departamento.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<div class="dashboard-container">
    <main class="main-content">
        <section class="content-area">
            <div class="content-header">
                <h1>Departamentos</h1>
                <button class="btn btn-primary" onclick="abrirPopupCadastro()">
                    <span class="material-icons">add</span> Adicionar Novo Departamento
                </button>
            </div>

            <div class="table-container">
                <table>
                    <thead>
                    <tr>
                        <th>Nome do Departamento</th>
                        <th>Setor</th>
                        <th>Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="departamento" items="${departamentos}">
                        <tr>
                            <td>${departamento.nomeDepto}</td>
                            <td>${departamento.setorNome}</td>
                            <td class="actions">
                                <button class="btn-icon" title="Editar"
                                        onclick="abrirPopupEdicao(${departamento.idDepto}, '${departamento.nomeDepto}', ${departamento.idSetor})">
                                    <span class="material-icons">edit</span>
                                </button>
                                <button class="btn-icon btn-icon-delete" title="Excluir"
                                        onclick="excluirDepartamento(${departamento.idDepto})">
                                    <span class="material-icons">delete</span>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div id="popup_departamento" class="popup">
                <main class="main-content-popup">
                    <section class="content-area">
                        <div class="content-header-popup">
                            <h1 id="tituloForm">Adicionar Novo Departamento</h1>
                            <button class="btn btn-secondary" onclick="fecharPopup()">
                                <span class="material-icons">arrow_back</span> Voltar para Listagem
                            </button>
                        </div>
                        <div class="form-container">
                            <form id="formDepartamento" action="/sistema/departamento" method="POST">
                                <input type="hidden" name="action" id="formAction" value="cadastrar">
                                <input type="hidden" name="id_depto" id="idDeptoInput">
                                <div class="form-group">
                                    <label for="nomeDepartamento">Nome do Departamento</label>
                                    <input type="text" id="nomeDepartamento" name="nome_depto" required>
                                </div>
                                <div class="form-group">
                                    <label for="setorDepartamento">Setor</label>
                                    <select id="setorDepartamento" name="id_setor" required>
                                        <option value="" disabled selected>Selecione o Setor</option>
                                        <c:forEach var="setor" items="${setores}">
                                            <option value="${setor.idSetor}">${setor.nomeSetor}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-actions">
                                    <button type="button" class="btn btn-cancel" onclick="fecharPopup()">Cancelar</button>
                                    <button type="submit" class="btn btn-primary">Salvar Departamento</button>
                                </div>
                            </form>
                        </div>
                    </section>
                </main>
            </div>
        </section>
    </main>
</div>

<script>
    const popup = document.getElementById('popup_departamento');
    const form = document.getElementById('formDepartamento');
    const tituloForm = document.getElementById('tituloForm');
    const formAction = document.getElementById('formAction');
    const idDeptoInput = document.getElementById('idDeptoInput');
    const nomeInput = document.getElementById('nomeDepartamento');
    const setorInput = document.getElementById('setorDepartamento');

    function abrirPopupCadastro() {
        tituloForm.textContent = "Adicionar Novo Departamento";
        formAction.value = "cadastrar";
        idDeptoInput.value = "";
        nomeInput.value = "";
        setorInput.value = "";
        popup.classList.add('opened');
    }

    function abrirPopupEdicao(id, nome, setor) {
        tituloForm.textContent = "Editar Departamento";
        formAction.value = "atualizar";
        idDeptoInput.value = id;
        nomeInput.value = nome;
        setorInput.value = setor;
        popup.classList.add('opened');
    }

    function fecharPopup() {
        popup.classList.remove('opened');
    }

    function excluirDepartamento(id) {
        if (confirm("Tem certeza que deseja excluir este departamento?")) {
            window.location.href = `/sistema/departamento?action=excluir&id_depto=${id}`;
        }
    }
</script>
</body>
</html>
