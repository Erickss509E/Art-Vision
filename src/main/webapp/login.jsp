<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inter" />
  <title>Login - ArtVision</title>
  <link rel="stylesheet" href="css/styles_cadastro.css" />
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
      <h1 class="titulo">Login</h1>

      <% if (request.getAttribute("erroLogin") != null) { %>
      <div class="erro-login">
        <%= request.getAttribute("erroLogin") %>
      </div>
      <% } %>

      <form action="login" method="post" class="formulario">
        <div class="campos">
          <input type="text" id="input_email" name="email" placeholder="Email" required />
        </div>
        <div class="campos">
          <input type="password" id="input_senha" name="senha" placeholder="Senha" required />
        </div>
        <button type="submit" class="botao">Entrar</button>
      </form>

      <div class="form-footer">
        <b class="registro">
          Não possui uma conta?
          <a href="cadastro.jsp" style="text-decoration: none;">
            <span style="color: #8c2a44;"><b>Registre-se</b></span>
          </a>
        </b>
      </div>
    </div>
  </div>
</div>
</body>
</html>