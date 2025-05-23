<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inter" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles_login.css" />
  <title>Login</title>
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

      <% String erro = (String) request.getAttribute("erroLogin"); %>
      <% if (erro != null) { %>
      <div style="color:red;"><%= erro %></div>
      <% } %>

      <form action="login" method="post">
        <div class="formulario">
          <div class="campos">
            <input type="text" name="email" placeholder="Email" />
            <div style="color: red;" id="div_email"></div>
          </div>
          <div class="campos">
            <input type="password" name="senha" placeholder="Senha" />
            <div style="color: red;" id="div_senha"></div>
          </div>
        </div>
        <button type="submit" class="botao">Entrar</button>
      </form>

      <div class="form-footer">
        <b class="registro">Ainda não possui cadastro?
          <a href="${pageContext.request.contextPath}/cadastro.jsp" style="text-decoration: none">
            <span style="color: #8c2a44"><b>Registre-se.</b></span>
          </a></b>
      </div>
    </div>
  </div>
</div>
</body>