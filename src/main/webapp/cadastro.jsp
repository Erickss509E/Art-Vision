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
            <div class="formulario">
                <div class="campos">
                    <input type="text" id="input_nome" placeholder="Nome">
                    <div id="div_nome"></div>
                </div>

                <div class="campos">
                    <input type="text" id="input_email" placeholder="Email">
                    <div id="div_email"></div>
                </div>

                <div class="campos">
                    <span>Empresas</span>
                    <select id="listaMuseus">
                        <option selected></option>
                    </select>
                    <div id="div_empresa"></div>
                </div>

                <div class="campos">
                    <input id="input_CPF" placeholder="CPF">
                    <div id="div_cpf"></div>
                </div>

                <div class="campos">
                    <input type="password" id="input_senha" placeholder="Senha">
                    <div id="div_senha"></div>
                </div>
            </div>

            <button onclick="cadastrar()" class="botao">Criar conta</button>

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

<script>
    function cadastrar() {
        const nome = document.getElementById("input_nome").value;
        const email = document.getElementById("input_email").value;
        const senha = document.getElementById("input_senha").value;
        const cpf = document.getElementById("input_CPF").value;
        const empresa = document.getElementById("listaMuseus").value;

        fetch("/art-vision/usuario?acao=cadastrar", {
  method: "POST",
  headers: {
    "Content-Type": "application/x-www-form-urlencoded"
  },
  body: `nome=${encodeURIComponent(nome)}&email=${encodeURIComponent(email)}&senha=${encodeURIComponent(senha)}&cpf=${encodeURIComponent(cpf)}&empresa=${encodeURIComponent(empresa)}`
});




    }

</script>
