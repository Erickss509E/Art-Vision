<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-sistema.css">
    <title>Sistema</title>
</head>
<body>
    <main>
        <div class="sidbar">
            <div>
                <div class="img">
                    <img src="${pageContext.request.contextPath}/assets/img/Museu_exemplo-removebg-preview.png">
                </div>
                <div class="nav">
                    <div>
                        <a href="./funcionario.jsp" target="main"><img src="${pageContext.request.contextPath}/assets/img/funcionario.png">Funcionario</a>
                    </div>
                    <div>
                        <a href="./perfil.jsp" target="main"><img src="${pageContext.request.contextPath}/assets/img/Person.png">Perfil</a>
                    </div>
                    <div>
                        <a href="./cargo.jsp" target="main"><img src="${pageContext.request.contextPath}/assets/img/cargo.png">Cargo</a>
                    </div>
                    <div>
                        <a href="./departamento.jsp" target="main"><img src="${pageContext.request.contextPath}/assets/img/Departmento.png">Departamento</a>
                    </div>
                    <div>
                        <a href="./setores.jsp" target="main"><img src="${pageContext.request.contextPath}/assets/img/setor.png">Setores</a>
                    </div>
                    <div>
                        <a href="./manutencao.jsp" target="main"><img src="${pageContext.request.contextPath}/assets/img/Wrench.png">Manutenção</a>
                    </div>
                    <div>
                        <a href="./obras.jsp" target="main"><img src="${pageContext.request.contextPath}/assets/img/Paintbrush.png">Obra</a>
                    </div>
                    <div>
                        <a href="${pageContext.request.contextPath}/logout"><img src="${pageContext.request.contextPath}/assets/img/logout.png">Sair</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="iframe">
        <iframe src="${pageContext.request.contextPath}/cargo.jsp" name="main"></iframe>
    </div>
</main>
</body>
</html>