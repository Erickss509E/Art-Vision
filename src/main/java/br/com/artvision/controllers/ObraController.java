package br.com.artvision.controllers;

import br.com.artvision.models.Obra;
import br.com.artvision.services.ObraService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/sistema/obra")
public class ObraController extends HttpServlet {
    private ObraService obraService;

    @Override
    public void init() {
        obraService = new ObraService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("\n=== DEBUG: ObraController doGet ===");
        System.out.println("Action: " + action);

        try {
            if (action == null) {
                listarObras(request, response);
            } else {
                switch (action) {
                    case "listar":
                        listarObras(request, response);
                        break;
                    case "editar":
                        mostrarFormularioEdicao(request, response);
                        break;
                    case "excluir":
                        excluirObra(request, response);
                        break;
                    case "novo":
                        mostrarFormularioCadastro(request, response);
                        break;
                    default:
                        listarObras(request, response);
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro no doGet: " + e.getMessage());
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("\n=== DEBUG: ObraController doPost ===");
        System.out.println("Action: " + action);

        try {
            if ("cadastrar".equals(action)) {
                cadastrarObra(request, response);
            } else if ("atualizar".equals(action)) {
                atualizarObra(request, response);
            } else {
                System.out.println("Ação desconhecida no POST: " + action);
                response.sendRedirect(request.getContextPath() + "/sistema/obra?action=listar");
            }
        } catch (Exception e) {
            System.out.println("Erro no doPost: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao processar requisição: " + e.getMessage());
        }
    }

    private void listarObras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("obras", obraService.listarObras());
        request.getRequestDispatcher("/sistema/obras.jsp").forward(request, response);
    }

    private void mostrarFormularioCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/sistema/add_obra.jsp").forward(request, response);
    }

    private void mostrarFormularioEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Obra obra = obraService.buscarObraPorId(id);
        request.setAttribute("obra", obra);
        request.getRequestDispatcher("/sistema/add_obra.jsp").forward(request, response);
    }

    private void cadastrarObra(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("\n=== DEBUG: Iniciando cadastro de obra no Controller ===");
        try {
            System.out.println("Criando objeto Obra a partir dos dados do formulário...");
            Obra obra = criarObraAPartirDoRequest(request);
            
            System.out.println("Chamando obraService.cadastrarObra...");
            boolean sucesso = obraService.cadastrarObra(obra);
            
            System.out.println("Resultado do cadastro: " + (sucesso ? "Sucesso" : "Falha"));
            
            if (sucesso) {
                response.sendRedirect(request.getContextPath() + "/sistema/obra?action=listar");
            } else {
                System.out.println("Falha ao cadastrar obra");
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Falha ao cadastrar obra");
            }
        } catch (ParseException e) {
            System.out.println("Erro ao processar data: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Data inválida: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro geral ao cadastrar: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao cadastrar obra: " + e.getMessage());
        }
    }

    private void atualizarObra(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Obra obra = criarObraAPartirDoRequest(request);
            obra.setId(Integer.parseInt(request.getParameter("id")));
            obraService.atualizarObra(obra);
            response.sendRedirect(request.getContextPath() + "/sistema/obra?action=listar");
        } catch (ParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Data inválida");
        }
    }

    private void excluirObra(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        obraService.excluirObra(id);
        response.sendRedirect(request.getContextPath() + "/sistema/obra?action=listar");
    }

    private Obra criarObraAPartirDoRequest(HttpServletRequest request) throws ParseException {
        System.out.println("\n=== DEBUG: Processando dados do formulário ===");
        
        Obra obra = new Obra();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Capturando e logando cada campo
        String nomeObra = request.getParameter("nome_obra");
        String nomeAutor = request.getParameter("nome_autor");
        String dataEntradaStr = request.getParameter("data_entrada");
        String dataSaidaStr = request.getParameter("data_saida");
        String valorEstimadoStr = request.getParameter("valor_estimado");
        String idSetorStr = request.getParameter("id_setor");
        String idFuncStr = request.getParameter("id_func");
        String idUsuarioStr = request.getParameter("id_usuario");

        System.out.println("Dados recebidos do formulário:");
        System.out.println("nome_obra: '" + nomeObra + "'");
        System.out.println("nome_autor: '" + nomeAutor + "'");
        System.out.println("data_entrada: '" + dataEntradaStr + "'");
        System.out.println("data_saida: '" + dataSaidaStr + "'");
        System.out.println("valor_estimado: '" + valorEstimadoStr + "'");
        System.out.println("id_setor: '" + idSetorStr + "'");
        System.out.println("id_func: '" + idFuncStr + "'");
        System.out.println("id_usuario: '" + idUsuarioStr + "'");

        // Setando os valores no objeto Obra
        obra.setNomeObra(nomeObra);
        obra.setNomeAutor(nomeAutor);

        if (dataEntradaStr != null && !dataEntradaStr.trim().isEmpty()) {
            obra.setDataEntrada(sdf.parse(dataEntradaStr));
        }

        if (dataSaidaStr != null && !dataSaidaStr.trim().isEmpty()) {
            obra.setDataSaida(sdf.parse(dataSaidaStr));
        }

        if (valorEstimadoStr != null && !valorEstimadoStr.trim().isEmpty()) {
            obra.setValorEstimado(Double.parseDouble(valorEstimadoStr));
        }

        if (idSetorStr != null && !idSetorStr.trim().isEmpty()) {
            obra.setIdSetor(Integer.parseInt(idSetorStr));
        }

        if (idFuncStr != null && !idFuncStr.trim().isEmpty()) {
            obra.setIdFunc(Integer.parseInt(idFuncStr));
        }

        if (idUsuarioStr != null && !idUsuarioStr.trim().isEmpty()) {
            obra.setIdUsuario(Integer.parseInt(idUsuarioStr));
        }

        System.out.println("\nObjeto Obra criado com sucesso:");
        System.out.println("Nome: " + obra.getNomeObra());
        System.out.println("Autor: " + obra.getNomeAutor());
        System.out.println("Data Entrada: " + obra.getDataEntrada());
        System.out.println("Data Saída: " + obra.getDataSaida());
        System.out.println("Valor: " + obra.getValorEstimado());
        System.out.println("ID Setor: " + obra.getIdSetor());
        System.out.println("ID Func: " + obra.getIdFunc());
        System.out.println("ID Usuario: " + obra.getIdUsuario());

        return obra;
    }
}
