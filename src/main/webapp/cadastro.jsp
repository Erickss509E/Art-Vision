<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="./js/sessao.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inter">
    <title>Tela de Cadastro</title>
    <link rel="stylesheet" href="css/styles_cadastro.css">
</head>

<body>
<div class="container">
    <div class="quadro1">
        <div class="texto-quadro1">
            <h1 class="titulo1">Bem-Vindo!</h1>
            <p class="titulo2">A ArtVision Tech Software</p>
        </div>
    </div>

    <div class="quadro2">
        <div class="container-quadro2">
            <h1 class="titulo">Registre-se</h1>

            <form action="usuario?acao=cadastrar" method="post" class="formulario">

                <div class="campos">
                    <input type="text" id="input_nome" name="nome" placeholder="Nome" required>
                    <div id="div_nome"></div>
                </div>

                <div class="campos">
                    <input type="text" id="input_email" name="email" placeholder="Email" required>
                    <div id="div_email"></div>
                </div>

                <div class="campos">
                    <span>Empresas</span>
                    <select id="listaMuseus" name="empresa" required>
                        <option value="">Selecione</option>
                        <option value="Empresa A">Empresa A</option>
                        <option value="Empresa B">Empresa B</option>
                    </select>
                    <div id="div_empresa"></div>
                </div>

                <div class="campos">
                    <input id="input_CPF" name="cpf" placeholder="CPF" required>
                    <div id="div_cpf"></div>
                </div>

                <div class="campos">
                    <input type="password" id="input_senha" name="senha_usuario" placeholder="Senha" required>
                    <div id="div_senha"></div>
                </div>

                <button type="submit" class="botao">Criar conta</button>
            </form>

            <div class="form-footer">
                <b class="registro">
                    Já possui uma conta?
                    <a href="login.jsp" style="text-decoration: none;">
                        <span style="color: #8c2a44;"><b>Login</b></span>
                    </a>
                </b>
            </div>
        </div>
    </div>
</div>
</body>
</html>
