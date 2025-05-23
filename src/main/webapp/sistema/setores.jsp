<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="br.com.artvision.dto.SetorDTO" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="services" class="br.com.artvision.services.SetorService" />
<%
    List<SetorDTO> setores = services.listarSetor();
    request.setAttribute("setores", setores);
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8"/>
    <title>Listagem de Setores - ArtVision</title>
    <link rel="stylesheet" href="../css/cargo.css"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"/>
    <style>
        .popup {
            display: none;
        }
        .popup.opened {
            display: block !important;
        }
    </style>
</head>
<body>
<div class="dashboard-container">
    <main class="main-content">
        <section class="content-area">
            <div class="content-header">
                <h1>Setores</h1>
                <button onclick="handlePopupCad(true)" class="btn btn-primary">
                    <span class="material-icons">add</span> Adicionar Novo Setor
                </button>
            </div>

            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Nome do Setor</th>
                            <th>Ala</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="setor" items="${setores}">
                            <tr>
                                <td>${setor.nome}</td>
                                <td>${setor.ala}</td>
                                <td class="actions">
                                    <form action="setores" method="get" style="display:inline;">
                                        <input type="hidden" name="action" value="buscar"/>
                                        <input type="hidden" name="id_setor" value="${setor.id}"/>
                                        <button type="submit" class="btn-icon" title="Editar">
                                            <span class="material-icons">edit</span>
                                        </button>
                                    </form>
                                    <form action="setores" method="post" style="display:inline;" onsubmit="return confirm('Deseja realmente excluir este setor?');">
                                        <input type="hidden" name="action" value="excluir"/>
                                        <input type="hidden" name="id" value="${setor.id}"/>
                                        <button type="submit" class="btn-icon btn-icon-delete" title="Excluir">
                                            <span class="material-icons">delete</span>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div id="popup_add" class="popup">
                <main class="main-content-popup">
                    <section class="content-area">
                        <div class="content-header-popup">
                            <h1>
                                <c:choose>
                                    <c:when test="${not empty setorEdicao}">
                                        Editar Setor
                                    </c:when>
                                    <c:otherwise>
                                        Adicionar Novo Setor
                                    </c:otherwise>
                                </c:choose>
                            </h1>
                            <button class="btn btn-secondary" onclick="handlePopupCad(false)">
                                <span class="material-icons">arrow_back</span> Voltar para Listagem
                            </button>
                        </div>
                        <div class="form-container">
                            <form id="formSetor" method="post" action="setores" onsubmit="return validarFormulario()">
                                <input type="hidden" name="action" value="${not empty setorEdicao ? 'atualizar' : 'cadastrar'}"/>
                                <input type="hidden" name="id" value="${setorEdicao != null ? setorEdicao.id : ''}"/>

                                <div class="form-section">
                                    <h3>Informações do Setor</h3>
                                    <div class="form-group">
                                        <label for="nomeSetor">Nome do Setor</label>
                                        <input type="text" id="nomeSetor" name="nomeSetor" required
                                               value="${setorEdicao != null ? setorEdicao.nome : ''}"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="alaSetor">Ala</label>
                                        <input type="text" id="alaSetor" name="alaSetor" placeholder="Ex: Norte, Sul, Ala A"
                                               value="${setorEdicao != null ? setorEdicao.ala : ''}"/>
                                    </div>
                                </div>
                                <div class="form-actions">
                                    <button type="button" class="btn btn-cancel" onclick="handlePopupCad(false)">Cancelar</button>
                                    <button type="submit" class="btn btn-primary">Salvar Setor</button>
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
    const popup_add = document.getElementById('popup_add');

    function handlePopupCad(open) {
        if (open) {
            popup_add.classList.add('opened');
        } else {
            popup_add.classList.remove('opened');

            const setorEdicao = '${not empty setorEdicao ? "true" : "false"}';
            if (setorEdicao !== 'true') {
                document.getElementById('formSetor').reset();
            }
        }
    }

    window.onload = function () {
        const setorEdicao = '${not empty setorEdicao ? "true" : "false"}';
        if (setorEdicao === 'true') {
            handlePopupCad(true);
        }
    };

    function validarFormulario() {
        const nome = document.getElementById('nomeSetor').value.trim();
        if (nome.length === 0) {
            alert('O nome do setor é obrigatório.');
            return false;
        }
        return true;
    }
</script>
</body>
</html>
