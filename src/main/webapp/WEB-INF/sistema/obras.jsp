<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listagem de Obras - ArtVision</title>
    <link rel="stylesheet" href="../css/obras.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<div class="dashboard-container">
    <main class="main-content">
        <section class="content-area">
            <div class="content-header">
                <h1>Obras</h1>
                <a href="add_obra.html" class="btn btn-primary" onclick="window.location.href='obras_formulario.html'">
                    <span class="material-icons">add</span> Adicionar Nova Obra
                </a>
            </div>
            <div class="table-container">
                <table>
                    <thead>
                    <tr>
                        <th>Nome da Obra</th>
                        <th>Autor</th>
                        <th>Data de Entrada</th>
                        <th>Data de Saída</th>
                        <th>Valor Estimado</th>
                        <th>Setor</th>
                        <th>Funcionário Responsável</th>
                        <th>Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Mona Lisa</td>
                        <td>Leonardo da Vinci</td>
                        <td>01/01/2023</td>
                        <td>-</td>
                        <td>R$ 5.000.000,00</td>
                        <td>Pinturas Clássicas</td>
                        <td>João Silva</td>
                        <td class="actions">
                            <button class="btn-icon" title="Editar"><span class="material-icons">edit</span></button>
                            <button class="btn-icon btn-icon-delete" title="Excluir"><span class="material-icons">delete</span></button>
                        </td>
                    </tr>
                    <tr>
                        <td>O Pensador</td>
                        <td>Auguste Rodin</td>
                        <td>15/03/2023</td>
                        <td>-</td>
                        <td>R$ 2.500.000,00</td>
                        <td>Esculturas</td>
                        <td>Maria Oliveira</td>
                        <td class="actions">
                            <button class="btn-icon" title="Editar"><span class="material-icons">edit</span></button>
                            <button class="btn-icon btn-icon-delete" title="Excluir"><span class="material-icons">delete</span></button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </section>
    </main>
</div>
</body>
</html>
