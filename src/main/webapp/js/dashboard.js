var ambiente = JSON.parse(sessionStorage.AMBIENTE);

let proximaAtualizacao;

function exibirDadosAmbiente(idAmbiente) {
    console.log("Teste" + idAmbiente)
    document.getElementById("container-titulo").innerHTML += `
        <p id="titulo-header${idAmbiente}">   
            <a href="sensores.html"> <i class="fa fa-fw fa-solid fa-arrow-left"></i> ${ambiente[0].nome_ambiente} - ${ambiente[0].andar}</a>
        </p>
    `

    document.getElementById("dashboard").innerHTML = `
        <div class="container-main-content">
        <div class="container-row row1">
            <div class="container-column" >
                <div class="grafico">
                    <canvas id="kpi_chart${idAmbiente}"></canvas>
                    <canvas id='kpi_chartUmd${idAmbiente}' style="display: none;"></canvas>
                </div>
                <div>
                    <select name="select_grafico" id="select_grafico" onchange="tipoGrafico(${idAmbiente})">
                        <option value="opt_temp"> Temperatura </option>
                        <option value="opt_umd"> Umidade </option>
                    </select>
                </div>
                <table class="tabela-sensor" id='sensores'>
                    <thead>
                        <tr>
                            <th> ID </th>
                            <th> Sensor </th>
                            <th> Temperatura </th>
                            <th> Umidade </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>DHT11(1)</td>
                            <td id='temp_tabela${idAmbiente}'>19º</td>
                            <td id='umd_tabela${idAmbiente}'>40%</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="container-log">
                <div class="log-header">
                    <p> Histórico de Alertas </p>
                </div>
                <div id='historicoAlerta${idAmbiente}'>

                </div>
            </div>
        </div>
        <h2>KPI's Art Vision Tech</h2>
        <div class="container-row row2">
            <div class="kpi-donut">
                <canvas id="kpi_temperatura${idAmbiente}"></canvas>
            </div>
            <div class="kpi-donut">
                <canvas id="kpi_umidade${idAmbiente}"></canvas>
            </div>
            <div class="kpi-donut">
                <canvas id="kpi_ip${idAmbiente}"></canvas>
            </div>
        </div>
    </div>
    `

    obtarDadosAmbiente(idAmbiente);
    obtarDadosKpi(idAmbiente);
}

function alterarTitulo(idAmbiente) {
    var elementoTitulo = document.getElementById(`titulo-header${idAmbiente}`)
    var nome_ambiente = JSON.parse(sessionStorage.AMBIENTE).find(item => item.idAmbiente == idAmbiente).nome_ambiente;
    var andar = JSON.parse(sessionStorage.AMBIENTE).find(item => item.idAmbiente == idAmbiente).andar;

    elementoTitulo.innerHTML = "<a href='sensores.html'> <i class='fa fa-fw fa-solid fa-arrow-left'></i> " + nome_ambiente + " - " + andar + "</a>";
}


function obtarDadosAmbiente(idAmbiente) {

    alterarTitulo(sessionStorage.ID_AMBIENTE);

    if (proximaAtualizacao != undefined) {
        clearTimeout(proximaAtualizacao);
    }

    fetch(`/medidas/ultimas/${idAmbiente}`, { cache: 'no-store' }).then(function (response) {
        if (response.ok) {
            response.json().then(function (resposta) {
                console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);
                resposta.reverse();

                plotarGraficoTemp(resposta, idAmbiente);
                plotarGraficoUmd(resposta, idAmbiente);

            });
        } else {
            console.error('Nenhum dado encontrado ou erro na API');
        }
    })
        .catch(function (error) {
            console.error(`Erro na obtenção dos dados p/ gráfico: ${error.message}`);
        });
}

function obtarDadosKpi(idAmbiente) {
    if (proximaAtualizacao != undefined) {
        clearTimeout(proximaAtualizacao);
    }

    fetch(`/medidas/kpi/${idAmbiente}`, { cache: 'no-store' }).then(function (response) {
        if (response.ok) {
            response.json().then(function (resposta) {
                console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);
                resposta.reverse();

                plotarKpi(resposta, idAmbiente);

            });
        } else {
            console.error('Nenhum dado encontrado ou erro na API');
        }
    })
    .catch(function (error) {
            console.error(`Erro na obtenção dos dados p/ gráfico: ${error.message}`);
    });
}


function tipoGrafico(idAmbiente) {
    const kpi_chartTemp = document.getElementById(`kpi_chart${idAmbiente}`)
    const kpi_chartUmd = document.getElementById(`kpi_chartUmd${idAmbiente}`)
    let tipo_grafico = select_grafico.value;
    console.log(tipo_grafico)
    if (tipo_grafico == "opt_umd") {
        kpi_chartTemp.style.display = "none";
        kpi_chartUmd.style.display = "block";
    } else {
        kpi_chartTemp.style.display = "block";
        kpi_chartUmd.style.display = "none";
    }

    // Função para alterar o gráfico
}

function plotarGraficoTemp(resposta, idAmbiente) {
    const ctx = document.getElementById(`kpi_chart${idAmbiente}`).getContext('2d');

    console.log('iniciando plotagem do gráfico...');

    let labelsTemp = [];

    let dataTempLine = {
        labels: labelsTemp,
        datasets: [
            {
                yAxisID: 'A',
                label: 'DHT11(1) Cº',
                data: [],
                fill: false,
                borderWidth: 1,
                tension: 0.1,
                backgroundColor: 'orange',
                borderColor: 'orange'
            },
        ]
    }

    const opcoes_temp = {
        responsive: true,
        scales: {
            A: {
                type: 'linear',
                position: 'left',
                ticks: { beginAtZero: true, color: 'orange', stepSize: 5 },
                grid: { display: false },
                suggestedMin: 5,
                suggestedMax: 35,
            },
        },
    };

    /*     console.log('----------------------------------------------')
        console.log('Estes dados foram recebidos pela funcao "obterDadosGrafico" e passados para "plotarGrafico":')
        console.log(resposta) */

    // Inserindo valores recebidos em estrutura para plotar o gráfico
    for (i = 0; i < resposta.length; i++) {
        var registro = resposta[i];
        labelsTemp.push(registro.momento_grafico);
        dataTempLine.datasets[0].data.push(registro.temperatura);
    }

/*     console.log('----------------------------------------------')
    console.log('O gráfico será plotado com os respectivos valores:')
    console.log('Labels:') */
    console.log(labelsTemp)
/*     console.log('Dados:') */
    console.log(dataTempLine.datasets)
/*     console.log('----------------------------------------------') */

    const configTempLine = {
        type: 'line',
        data: dataTempLine,
        options: opcoes_temp
    }

    let chartTempLine = new Chart(
        ctx,
        configTempLine
    );

    setTimeout(() => atualizarGraficoTemp(idAmbiente, dataTempLine, chartTempLine), 2000);

}

function plotarGraficoUmd(resposta, idAmbiente) {
    const ctxUmd = document.getElementById(`kpi_chartUmd${idAmbiente}`).getContext('2d');
    let labelsUmd = []

    let dataUmdLine = {
        labels: labelsUmd,
        datasets: [
            {
                yAxisID: 'A',
                label: 'DHT11(1) UR%',
                data: [],
                borderWidth: 1,
                backgroundColor: 'blue',
                borderColor: 'blue'
            },
        ]
    }

    const opcoes_umd = {
        responsive: true,
        scales: {
            A: {
                type: 'linear',
                position: 'left',
                ticks: { beginAtZero: true, color: 'blue', stepSize: 10 },
                grid: { display: false },
                suggestedMin: 10,
                suggestedMax: 90,
            },
            x: { ticks: { beginAtZero: true } },
        }
    };

    for (i = 0; i < resposta.length; i++) {
        var registro = resposta[i];
        labelsUmd.push(registro.momento_grafico);
        dataUmdLine.datasets[0].data.push(registro.umidade);
    }

    const configUmdLine = { 
        type: 'line',
        data: dataUmdLine,
        options: opcoes_umd
    }

    let chartUmd = new Chart(ctxUmd, configUmdLine);

    setTimeout(() => atualizarGraficoUmd(idAmbiente, dataUmdLine, chartUmd), 2000);
}


function plotarKpi(resposta, idAmbiente) {
    const ctxTemp = document.getElementById(`kpi_temperatura${idAmbiente}`).getContext('2d');
    const ctxUr = document.getElementById(`kpi_umidade${idAmbiente}`).getContext('2d');
    const ctxIp = document.getElementById(`kpi_ip${idAmbiente}`).getContext('2d');

    const dataValueTemp = []

    for(var i = 0; i < resposta.length; i++) {
        var registro = resposta[i];
        if(registro.temperatura < 24 && registro.temperatura > 20 ) {
            dataValueTemp.push(registro.temperatura);
        }
    }

    const totalValor = resposta.length;
    const totalRegistrosValidos = dataValueTemp.length;

    var porcentagem = totalRegistrosValidos / totalValor * 100

    const dataTemp = {
        labels: ['Temperatura ideal'],
        datasets: [{
            label: 'Temperatura',
            data: [parseInt(porcentagem), 100 - parseInt(porcentagem)],
            backgroundColor: ['rgba(255, 118, 51, 0.8)', 'rgba(255, 255, 255, 1)'],
            borderColor: ['rgb(255, 118, 22)', 'rgb(255, 118, 22)'],
            borderWidth: 1,
            cutout: '75%',
            radius: '90%',
        }]
    }

    const textoPorcentagemTemp = {
        id: 'textoPorcentagemTemp',
        afterDatasetsDraw(chart, args, pluginOptions) {
            const { ctx } = chart;
            ctx.save();
            ctx.textAlign = 'center';
            ctx.textBaseline = 'middle';
            ctx.font = 'bold 21px sans-serif';
            ctx.fillStyle = '#ff7616'
            const texto = `${porcentagem.toFixed(2)}%`;
            const textoWidth = ctx.measureText(texto).width

            const x = chart.getDatasetMeta(0).data[0].x;
            const y = chart.getDatasetMeta(0).data[0].y;
            ctx.fillText(texto, x, y);

        }
    }

    const configTemp = {
        type: 'doughnut',
        data: dataTemp,
        options: {
            plugins: {
                title: {
                    display: true,
                    text: 'Temperatura',
                    color: '#ff7616',
                    font: {
                        size: 20,
                    }
                }
            }
        },
        plugins: [textoPorcentagemTemp],
    }

    const dataValueUmd = []

    for(var i = 0; i < resposta.length; i++) {
        var registro = resposta[i];
        if(registro.umidade < 60 && registro.umidade > 45) {
            dataValueUmd.push(registro.umidade);
        }
    }

    const totalValorUmd = resposta.length;
    const totalRegistrosValidosUmd = dataValueUmd.length;


    var porcentagemUmd = totalRegistrosValidosUmd / totalValorUmd * 100


    const dataUr = {
        labels: ['Umidade ideal'],
        datasets: [{
            label: 'Umidade',
            data: [parseInt(porcentagemUmd), 100 - parseInt(porcentagemUmd)],
            backgroundColor: ['rgba(0, 142, 252, 0.8)', 'rgba(255, 255, 255, 1)'],
            borderColor: ['rgb(0, 31, 252)', 'rgb(0, 31, 252)'],
            borderWidth: 1,
            cutout: '75%',
            radius: '90%',
        }]
    }

    const textoPorcentagemUr = {
        id: 'textoPorcentagemUr',
        afterDatasetsDraw(chart, args, pluginOptions) {
            const { ctx } = chart;
            ctx.save();
            ctx.textAlign = 'center';
            ctx.textBaseline = 'middle';
            ctx.font = 'bold 21px sans-serif';
            ctx.fillStyle = '#001ffc'
            const texto = `${porcentagemUmd.toFixed(2)}%`;
            const textoWidth = ctx.measureText(texto).width


            const x = chart.getDatasetMeta(0).data[0].x;
            const y = chart.getDatasetMeta(0).data[0].y;
            ctx.fillText(texto, x, y);
        }
    }

    const configUr = {
        type: 'doughnut',
        data: dataUr,
        options: {
            plugins: {
                title: {
                    display: true,
                    text: 'Umidade',
                    color: '#001ffc',
                    font: {
                        size: 20,
                    }
                }
            }
        },
        plugins: [textoPorcentagemUr],
    }

    // Gráfico IP

    const dataValueIpOtimo = [];
    const dataValueIpOk = [];
    const dataValueIpRuim = [];
    
    for(var i = 0; i < resposta.length; i++) {
        var registro = resposta[i];
        var ip = Math.exp((95220 - 134.9 * registro.umidade) / (8.314 * (registro.temperatura + 273.15)) + (0.0284 * registro.umidade) - 28.023) / 365;
        if(ip >= 70) {
            dataValueIpOtimo.push(ip);
            console.log("IP ótimo: " + ip);
        } else if(ip >= 45 && ip <= 70) {
            dataValueIpOk.push(ip)
            console.log("IP ok: " + ip);
        } else {
            dataValueIpRuim.push(ip);
            console.log("IP ruim: " + ip);
        }
        
    }

    const dataValueIpOtimoTamanho = dataValueIpOtimo.length
    const dataValueIpOkTamnho = dataValueIpOk.length
    const dataValueIpRuimTamanho = dataValueIpRuim.length

/*     console.log(dataValueIpOtimoTamanho)
    console.log(dataValueIpOkTamnho)
    console.log(dataValueIpRuimTamanho) */

    const data = {
        labels: ['Ótimo', 'Normal', 'Ruim'],
        datasets: [{
            label: 'IETP',
            data: [dataValueIpOtimoTamanho, dataValueIpOkTamnho, dataValueIpRuimTamanho],
            backgroundColor: [
                'rgb(0, 128, 0)',
                'rgba(159, 159, 159)',
                'rgba(250, 0, 0)',
            ],
            color: 'rgb(3, 166, 60)',
            borderWidth: 1
        }]
    };

    // Calculate percentages
    const total = data.datasets[0].data.reduce((acc, value) => acc + value, 0);
    const percentages = data.datasets[0].data.map(value => ((value / total) * 100).toFixed(2));


    // config
    const config = {
        type: 'pie',
        data: {
            labels: data.labels.map((label, index) => `${label}`),
            datasets: [{
                label: 'IETP',
                data: percentages,
                backgroundColor: data.datasets[0].backgroundColor,
                borderWidth: 1,
                color: '#03A63C',
            }]
        },
        options: {
            scales: {},
            plugins: {
                tooltip: {
                    enabled: false
                },
                datalabels: {
                    color: 'rgb(255, 255, 255)',
                    font: {
                        size: 10
                    },
                    formatter: (value, context) => {
                        const percentage = percentages[context.dataIndex];
                        return `${percentage}%`; // Exibe valor original e porcentagem
                    }
                },
                title: {
                    display: true,
                    text: 'IP',
                    color: 'rgb(0, 128, 0)',
                    font: {
                        size: 20,
                    }
                }
            },
        },
        plugins: [ChartDataLabels]
    };

    const kpiTemp = new Chart(ctxTemp, configTemp);
    const kpiUr = new Chart(ctxUr, configUr);
    new Chart(ctxIp, config);
}

function atualizarGraficoTemp(idAmbiente, dados, myChart) {

    fetch(`/medidas/tempo-real/${idAmbiente}`, { cache: 'no-store' }).then(function (response) {
        if (response.ok) {
            response.json().then(function (novoRegistro) {

                obterdados(idAmbiente);
                // alertar(novoRegistro, idAmbiente);
                console.log(`Dados recebidos: ${JSON.stringify(novoRegistro)}`);
                console.log(`Dados atuais do gráfico:`);
                console.log(dados);

                if (novoRegistro[0].momento_grafico == dados.labels[dados.labels.length - 1]) {
                    console.log("---------------------------------------------------------------")
                    console.log("Como não há dados novos para captura, o gráfico não atualizará.")

                    console.log("Horário do novo dado capturado:")
                    console.log(novoRegistro[0].momento_grafico)
                    console.log("Horário do último dado capturado:")
                    console.log(dados.labels[dados.labels.length - 1])
                    console.log("---------------------------------------------------------------")
                } else {
                    // tirando e colocando valores no gráfico
                    dados.labels.shift(); // apagar o primeiro
                    dados.labels.push(novoRegistro[0].momento_grafico); // incluir um novo momento

                    dados.datasets[0].data.shift();  // apagar o primeiro de temperatura
                    dados.datasets[0].data.push(novoRegistro[0].temperatura); // incluir uma nova medida de temperatura

                    myChart.update();
                }

                // Altere aqui o valor em ms se quiser que o gráfico atualize mais rápido ou mais devagar
                proximaAtualizacao = setTimeout(() => atualizarGraficoTemp(idAmbiente, dados, myChart), 2000);
            });
        } else {
            console.error('Nenhum dado encontrado ou erro na API');
            // Altere aqui o valor em ms se quiser que o gráfico atualize mais rápido ou mais devagar
            proximaAtualizacao = setTimeout(() => atualizarGraficoTemp(idAmbiente, dados, myChart), 2000);
        }
    })
        .catch(function (error) {
            console.error(`Erro na obtenção dos dados p/ gráfico: ${error.message}`);
        });

}

function atualizarGraficoUmd(idAmbiente, dados, myChart) {

    fetch(`/medidas/tempo-real/${idAmbiente}`, { cache: 'no-store' }).then(function (response) {
        if (response.ok) {
            response.json().then(function (novoRegistro) {

                obterdados(idAmbiente);
                // alertar(novoRegistro, idAmbiente);
                console.log(`Dados recebidos: ${JSON.stringify(novoRegistro)}`);
                console.log(`Dados atuais do gráfico:`);
                console.log(dados);

                if (novoRegistro[0].momento_grafico == dados.labels[dados.labels.length - 1]) {
                    console.log("---------------------------------------------------------------")
                    console.log("Como não há dados novos para captura, o gráfico não atualizará.")

                    console.log("Horário do novo dado capturado:")
                    console.log(novoRegistro[0].momento_grafico)
                    console.log("Horário do último dado capturado:")
                    console.log(dados.labels[dados.labels.length - 1])
                    console.log("---------------------------------------------------------------")
                } else {
                    // tirando e colocando valores no gráfico
                    dados.labels.shift(); // apagar o primeiro
                    dados.labels.push(novoRegistro[0].momento_grafico); // incluir um novo momento

                    dados.datasets[0].data.shift();  // apagar o primeiro de umidade
                    dados.datasets[0].data.push(novoRegistro[0].umidade); // incluir uma nova medida de umidade

                    myChart.update();
                }

                // Altere aqui o valor em ms se quiser que o gráfico atualize mais rápido ou mais devagar
                proximaAtualizacao = setTimeout(() => atualizarGraficoUmd(idAmbiente, dados, myChart), 2000);
            });
        } else {
            console.error('Nenhum dado encontrado ou erro na API');
            // Altere aqui o valor em ms se quiser que o gráfico atualize mais rápido ou mais devagar
            proximaAtualizacao = setTimeout(() => atualizarGraficoUmd(idAmbiente, dados, myChart), 2000);
        }
    })
        .catch(function (error) {
            console.error(`Erro na obtenção dos dados p/ gráfico: ${error.message}`);
        });

}


window.onload = function () {
    exibirDadosAmbiente(sessionStorage.ID_AMBIENTE);

}

