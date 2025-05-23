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
                                         <span class="material-icons">edit</span>
                                       onclick="return confirm('Tem certeza que deseja excluir este cargo?');">
                                        <span class="material-icons">delete</span>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
               <main class="main-content-popup">
                   <section class="content-area">
                       <div class="content-header-popup">
                           </button>
                       </div>
                       <div class="form-container">
                               <div class="form-group">
                                   <label for="nomeCargo">Nome do Cargo</label>
                               </div>
                               <div class="form-group">
                                   <label for="setorCargo">Setor</label>
                                   <select id="setorCargo" name="id_setor" required>
                                       <c:forEach var="setor" items="${setores}">
                                       </c:forEach>
                                   </select>
                               </div>
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
