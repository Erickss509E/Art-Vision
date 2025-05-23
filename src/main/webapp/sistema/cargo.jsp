<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="br.com.artvision.dto.SetorDTO" %>
<%@page import="br.com.artvision.dto.CargoDTO" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="setor" class="br.com.artvision.services.SetorService" />
<jsp:useBean id="cargo" class="br.com.artvision.services.CargoService" />
<%
    List<SetorDTO> setores = setor.listarSetor();
    request.setAttribute("setores", setores);
    List<CargoDTO> cargos = cargo.listarCargos();
    request.setAttribute("cargos", cargos);
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Listagem de Cargos - ArtVision</title>
    <link rel="stylesheet" href="../css/cargo.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <style>
        .popup {
            display: none;
            position: fixed;
            top: 0; left: 0; right: 0; bottom: 0;
            background: rgba(0,0,0,0.5);
            justify-content: center;
            align-items: center;
        }
        .popup.opened {
            display: flex;
        }
        .popup-content {
            background: white;
            padding: 20px;
            border-radius: 8px;
            width: 400px;
        }
    </style>
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
                                <td>${cargo.nomeSetor}</td>
                                <td class="actions">
                                    <a href="${pageContext.request.contextPath}/sistema/cargo?action=buscar&id_cargo=${cargo.id}" title="Editar" onclick="abrirPopupEdicao(${cargo.id}); return false;">
                                        <span class="material-icons">edit</span>
                                    </a>
                                    <a href="${pageContext.request.contextPath}/sistema/cargo?action=excluir&id_cargo=${cargo.id}" title="Excluir" onclick="return confirm('Tem certeza que deseja excluir este cargo?');">
                                        <span class="material-icons">delete</span>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div id="popup_add" class="popup">
                <div class="popup-content">
                    <h2 id="popupTitle">Cadastro de Cargo</h2>
                    <form id="formCargo" action="${pageContext.request.contextPath}/sistema/cargo" method="post">
                        <input type="hidden" name="action" value="cadastrar" id="formAction" />
                        <input type="hidden" name="id_cargo" value="" id="idCargo" />
                        <div class="form-group">
                            <label for="nomeCargo">Nome do Cargo</label>
                            <input type="text" id="nomeCargo" name="nome_cargo" value="" required />
                        </div>
                        <div class="form-group">
                            <label for="setorCargo">Setor</label>
                            <select id="setorCargo" name="id_setor" required>
                                <c:forEach var="setor" items="${setores}">
                                    <option value="${setor.id}">${setor.nome}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-actions">
                            <button type="submit" class="btn btn-primary">Salvar</button>
                            <button type="button" class="btn btn-secondary" onclick="fecharPopupCadastro()">Cancelar</button>
                        </div>
                    </form>
                </div>
            </div>

        </section>
    </main>
</div>

<script>
    const contextPath = '${pageContext.request.contextPath}';
    
    function abrirPopupCadastro() {
        document.getElementById('popupTitle').textContent = 'Cadastro de Cargo';
        document.getElementById('formAction').value = 'cadastrar';
        document.getElementById('idCargo').value = '';
        document.getElementById('nomeCargo').value = '';
        document.getElementById('setorCargo').selectedIndex = 0;
        document.getElementById('popup_add').classList.add('opened');
    }

    function abrirPopupEdicao(id) {
        window.location.href = contextPath + '/sistema/cargo?action=buscar&id_cargo=' + id + '&popup=true';
    }

    function fecharPopupCadastro() {
        document.getElementById('popup_add').classList.remove('opened');
    }
</script>

</body>
</html>
