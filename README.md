# ArtVision

## Funcionalidades do sistema

### 1.1. Gerenciamento de Preservação de Obras (Funcionalidade Principal)
1.	Monitoramento e Controle do Estado de Conservação: 
* O sistema permitirá o registro detalhado do estado de conservação de cada obra de arte, com a possibilidade de incluir descrições, imagens e histórico de manutenção.
* Alertas automáticos serão gerados para obras que estejam próximas de necessitar manutenção.
2.	Registro de Manutenções e Preservações Periódicas:
* Os usuários poderão registrar todas as manutenções realizadas, incluindo a data, tipo de manutenção, responsável pela preservação e observações adicionais.
* Um calendário integrado ajudará na organização de manutenções futuras.
3.	Emissão de Relatórios:
* Relatórios periódicos sobre o estado das obras serão gerados automaticamente, com gráficos e dados organizados para facilitar a análise.
* Os relatórios poderão ser exportados em formatos como PDF ou enviados diretamente aos administradores e clientes.

### 1.2. Gerenciamento de Funcionários
1.	Cadastro e Gerenciamento de Funcionários:
* O sistema permitirá o registro de informações básicas dos funcionários, como nome, CPF, telefone e e-mail.
* Será possível adicionar informações específicas, como cargo, setor, horário de trabalho e histórico de atividades.
2.	Controle da Quantidade de Funcionários Ativos:
* O painel administrativo exibirá o número total de funcionários ativos, separados por setor e função.
3.	Registro de Cargos e Funções:
* Cargos e funções serão cadastrados previamente, permitindo que os dados sejam associados corretamente a cada funcionário.
4.	Armazenamento de Dados:
* O sistema armazenará o histórico de entrada e saída dos funcionários, além de informações relacionadas a treinamentos e qualificações.
* Níveis de acesso serão definidos para cada funcionário, garantindo maior segurança.

### 1.3. Gerenciamento de Obras
1.	Registro de Obras:
*	Cada obra poderá ser registrada com informações como:
*	Nome da obra.
*	Nome do autor.
*	Data de entrada no museu.
*	Valor estimado.
*	Localização dentro do museu.
2.	Monitoramento de Manutenção:
*	O sistema exibirá a última data de manutenção de cada obra e calculará automaticamente o tempo decorrido desde a última preservação.
3.	Cálculo do Tempo de Preservação:
*	Com base na data de entrada e no histórico de manutenções, o sistema calculará o tempo total desde a última intervenção.
4.	Geração de Relatórios:
*	Relatórios detalhados sobre cada obra estarão disponíveis, incluindo informações sobre valor estimado, localização atual, e histórico de manutenção.
5.	Localização das Obras:
*	O sistema incluirá um mapa interno do museu, mostrando a localização exata das obras em cada sala ou setor.
6.	Registro do Valor Estimado:
*	O valor estimado de cada obra será armazenado no sistema, permitindo análises financeiras e relatórios para seguros ou auditorias.

### 1.4. Gerenciamento de Salas do Museu
1.	Organização por Áreas do Museu:
*	O museu será dividido em áreas principais (Sul, Norte, Leste, Oeste), e cada área poderá conter várias salas e setores.
2.	Cadastro de Setores e Departamentos:
*	Os setores e departamentos serão cadastrados para facilitar a organização interna do museu. Cada setor será associado a uma área específica.
3.	Controle de Equipamentos:
*	Será possível registrar e monitorar os equipamentos disponíveis em cada sala, como iluminação, climatização ou dispositivos de segurança.

### Fluxo de Uso do Sistema
1.	Tabela Principal:
*	O usuário terá acesso a um painel inicial com métricas em tempo real, como:
*	Quantidade de obras cadastradas.
*	Estado de conservação das obras.
*	Funcionários ativos no momento.
*	Salas disponíveis e equipamentos em uso.
2.	Cadastro de Dados:
*	Através de formulários intuitivos, será possível cadastrar novos funcionários, obras, setores e equipamentos.
3.	Relatórios e Exportação:
*	O sistema permitirá a geração de relatórios detalhados para análise interna ou envio a clientes e administradores.
4.	Alertas e Notificações:
*	Notificações automáticas serão enviadas em casos de:
*	Obras necessitando de manutenção.
*	Funcionários ausentes sem justificativa.
*	Equipamentos com falhas ou necessidade de substituição.
Restrições e Requisitos Não Funcionais
*	Interface Responsiva:
*	O sistema será acessível em dispositivos móveis, tablets e desktops, com design adaptável para diferentes tamanhos de tela.
*	Segurança de Dados:
*	O banco de dados será protegido com criptografia para garantir a privacidade das informações das obras e dos funcionários.
*	Controle de Acesso:
*	Diferentes níveis de acesso serão configurados:
*	Administradores: Acesso total ao sistema.
*	Funcionários: Acesso limitado às suas funções e setores.
*	Acessibilidade via Web:
*	O sistema será acessado diretamente através de navegadores modernos, sem a necessidade de instalação de software local.

### Organograma Sistema ArtVision

  <img width=100% src="https://github.com/Erickss509E/Art-Vision/blob/main/ArtVision/src/main/webapp/img/Organograma-ArtVision.drawio.png" alt="Organograma">

### DER Modelo Lógico

  <img width=100% src="https://github.com/Erickss509E/Art-Vision/blob/main/ArtVision/src/main/webapp/img/modelo-logico.png" alt="Modelo Lógico">

### MER Modelo Conceitual

   <img width=100% src="https://github.com/Erickss509E/Art-Vision/blob/main/ArtVision/src/main/webapp/img/modelo-relacional.png" alt="Modelo Conceitual">
  
### Prototipo 
  <h3>Home Page:<h3/>
    <img width=50% src="https://github.com/Erickss509E/Art-Vision/blob/main/ArtVision/src/main/webapp/img/Home_Page.webp" alt="Texto Alternativo"><br/>
  <h3> Sobre:<h3/>
    <img width=50% src="https://github.com/Erickss509E/Art-Vision/blob/main/ArtVision/src/main/webapp/img/Sobre.webp" alt="Texto Alternativo"><br/>
  <h3> Login:<h3/>
    <img width=50% src="https://github.com/Erickss509E/Art-Vision/blob/main/ArtVision/src/main/webapp/img/Login.webp" alt="Texto Alternativo"><br/>
  <h3> Cadastro:<h3/>
    <img width=50% src="https://github.com/Erickss509E/Art-Vision/blob/main/ArtVision/src/main/webapp/img/Cadastro.webp" alt="Texto Alternativo"><br/>
  <h3> Relatorios:<h3/> 
     <img width=50% src="https://github.com/Erickss509E/Art-Vision/blob/main/ArtVision/src/main/webapp/img/Relatorios.webp" alt="Texto Alternativo"><br/>
  <h3> Sala:<br/><h3/>
    <img width=50% src="https://github.com/Erickss509E/Art-Vision/blob/main/ArtVision/src/main/webapp/img/Sala.webp" alt="Texto Alternativo">
  <h3> Dashboard:<h3/> 
     <img width=50% src="https://github.com/Erickss509E/Art-Vision/blob/main/ArtVision/src/main/webapp/img/Dashboard.webp" alt="Texto Alternativo"><br/>
  <h3> Calculador:<h3/> 
     <img width=50% src="https://github.com/Erickss509E/Art-Vision/blob/main/ArtVision/src/main/webapp/img/Calculadora.webp" alt="Texto Alternativo"><br/>
  <h3> Linha do Tempo:<h3/> <br/>
     <img width=50% src="https://github.com/Erickss509E/Art-Vision/blob/main/ArtVision/src/main/webapp/img/Linha_Tempo.webp" alt="Texto Alternativo">
  <h3> Ajustes:<h3/>
     <img width=50% src="https://github.com/Erickss509E/Art-Vision/blob/main/ArtVision/src/main/webapp/img/Ajustes.webp" alt="Texto Alternativo"><br/>

