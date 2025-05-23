<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="br.com.artvision.dto.SetorDTO" %>
<%@page import="br.com.artvision.dto.CargoDTO" %>
<%@page import="br.com.artvision.dto.DepartamentoDTO" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="setor" class="br.com.artvision.services.SetorService" />
<jsp:useBean id="cargo" class="br.com.artvision.services.CargoService" />
<jsp:useBean id="departamento" class="br.com.artvision.services.DepartamentoService" />
<%
    List<SetorDTO> setores = setor.listarSetor();
    request.setAttribute("setores", setores);
    List<CargoDTO> cargos = cargo.listarCargos();
    request.setAttribute("cargos", cargos);
    List<DepartamentoDTO> departamentos = departamento.listarDepartamentos();
    request.setAttribute("departamentos", departamentos);
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulário de Funcionário - ArtVision</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/add_funcionario.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<div class="dashboard-container">
    <main class="main-content">
        <section class="content-area">
            <div class="content-header">
                <h1>${funcionario != null ? 'Editar' : 'Adicionar Novo'} Funcionário</h1>
                <a href="${pageContext.request.contextPath}/sistema/funcionario?action=listar" class="btn btn-secondary">
                    <span class="material-icons">arrow_back</span> Voltar para Listagem
                </a>
            </div>
            <div class="form-container">
                <form action="${pageContext.request.contextPath}/sistema/funcionario" method="post">
                    <input type="hidden" name="action" value="${funcionario != null ? 'atualizar' : 'cadastrar'}">
                    <input type="hidden" name="id_func" value="${funcionario.idFunc}">
                    <div class="form-section">
                        <h3>Dados Pessoais</h3>
                        <div class="form-row">
                            <div class="form-group input-half">
                                <label for="nome_func">Nome Completo</label>
                                <input type="text" id="nome_func" name="nome_func" value="${funcionario.nomeFunc}" required>
                            </div>
                            <div class="form-group input-half">
                                <label for="cpf_func">CPF</label>
                                <input type="text" id="cpf_func" name="cpf_func" value="${funcionario.cpfFunc}" required placeholder="000.000.000-00">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group input-half">
                                <label for="email_func">E-mail</label>
                                <input type="email" id="email_func" name="email_func" value="${funcionario.emailFunc}" required placeholder="exemplo@dominio.com">
                            </div>
                            <div class="form-group input-half">
                                <label for="telefone_func">Telefone</label>
                                <input type="tel" id="telefone_func" name="telefone_func" value="${funcionario.telefoneFunc}" placeholder="(00) 00000-0000">
                            </div>
                        </div>
                    </div>

                    <div class="form-section">
                        <h3>Dados Profissionais</h3>
                        <div class="form-row">
                            <div class="form-group input-half">
                                <label for="matricula_func">Matrícula</label>
                                <input type="text" id="matricula_func" name="matricula_func" value="${funcionario.matriculaFunc}" required placeholder="0000">
                            </div>
                            <div class="form-group input-half">
                                <label for="id_cargo">Cargo</label>
                                <select id="id_cargo" name="id_cargo" required>
                                    <option value="" disabled ${funcionario == null ? 'selected' : ''}>Selecione o Cargo</option>
                                    <c:forEach var="cargo" items="${cargos}">
                                        <option value="${cargo.id}" ${cargo.id == funcionario.idCargo ? 'selected' : ''}>${cargo.nome}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group input-half">
                                <label for="id_setor">Setor</label>
                                <select id="id_setor" name="id_setor" required>
                                    <option value="" disabled ${funcionario == null ? 'selected' : ''}>Selecione o Setor</option>
                                    <c:forEach var="setor" items="${setores}">
                                        <option value="${setor.id}" ${setor.id == funcionario.idSetor ? 'selected' : ''}>${setor.nome}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group input-half">
                                <label for="id_depto">Departamento</label>
                                <select id="id_depto" name="id_depto">
                                    <option value="" disabled ${funcionario == null ? 'selected' : ''}>Selecione o Departamento</option>
                                    <c:forEach var="departamento" items="${departamentos}">
                                        <option value="${departamento.idDepto}" ${departamento.idDepto == funcionario.idDepto ? 'selected' : ''}>${departamento.nomeDepto}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-actions">
                        <button type="reset" class="btn btn-cancel">Cancelar</button>
                        <button type="submit" class="btn btn-primary">Salvar Funcionário</button>
                    </div>
                </form>
            </div>
        </section>
    </main>
</div>
</body>
</html>