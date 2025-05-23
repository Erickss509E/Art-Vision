<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulário de Funcionário - ArtVision</title>
    <link rel="stylesheet" href="../css/add_funcionario.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<div class="dashboard-container">
    <main class="main-content">
        <section class="content-area">
            <div class="content-header">
                <h1>Adicionar Novo Funcionário</h1>
                <a href="funcionario.jsp" class="btn btn-secondary" onclick="window.location.href='funcionarios_listagem.html'">
                    <span class="material-icons">arrow_back</span> Voltar para Listagem
                </a>
            </div>
            <div class="form-container">
                <form id="formFuncionario">
                    <div class="form-section">
                        <h3>Dados Pessoais</h3>
                        <div class="form-row">
                            <div class="form-group input-half">
                                <label for="nomeCompleto">Nome Completo</label>
                                <input type="text" id="nomeCompleto" name="nomeCompleto" required>
                            </div>
                            <div class="form-group input-half">
                                <label for="cpf">CPF</label>
                                <input type="text" id="cpf" name="cpf" required placeholder="000.000.000-00">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group input-half">
                                <label for="email">E-mail</label>
                                <input type="email" id="email" name="email" required placeholder="exemplo@dominio.com">
                            </div>
                            <div class="form-group input-half">
                                <label for="telefone">Telefone</label>
                                <input type="tel" id="telefone" name="telefone" placeholder="(00) 00000-0000">
                            </div>
                        </div>
                    </div>

                    <div class="form-section">
                        <h3>Dados Profissionais</h3>
                        <div class="form-row">
                            <div class="form-group input-half">
                                <label for="matricula">Matrícula</label>
                                <input type="text" id="matricula" name="matricula" required placeholder="0000">
                            </div>
                            <div class="form-group input-half">
                                <label for="cargo">Cargo</label>
                                <select id="cargo" name="cargo" required>
                                    <option value="" disabled selected>Selecione o Cargo</option>
                                    <option value="1">Curador Chefe</option>
                                    <option value="2">Restauradora Júnior</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group input-half">
                                <label for="setor">Setor</label>
                                <select id="setor" name="setor" required>
                                    <option value="" disabled selected>Selecione o Setor</option>
                                    <option value="1">Conservação</option>
                                    <option value="2">Restauração</option>
                                </select>
                            </div>
                            <div class="form-group input-half">
                                <label for="departamento">Departamento</label>
                                <select id="departamento" name="departamento">
                                    <option value="" disabled selected>Selecione o Departamento</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="escala">Escala</label>
                            <select id="escala" name="escala" required>
                                <option value="" disabled selected>Selecione a Escala</option>
                                <option value="1">5x2 - 08:00-17:00</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-actions">
                        <button type="button" class="btn btn-cancel">Cancelar</button>
                        <button type="submit" class="btn btn-primary">Salvar Funcionário</button>
                    </div>
                </form>
            </div>
        </section>
    </main>
</div>
</body>
</html>
