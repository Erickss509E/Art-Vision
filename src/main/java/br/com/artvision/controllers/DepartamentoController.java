package br.com.artvision.controllers;

import br.com.artvision.dto.DepartamentoDTO;
import br.com.artvision.dto.FuncionarioDTO;
import br.com.artvision.models.Departamento;
import br.com.artvision.models.Funcionario;
import br.com.artvision.services.DepartamentoService;
import br.com.artvision.services.FuncionarioService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/departamento")
public class DepartamentoController extends HttpServlet {

    private DepartamentoService departamentoService = new DepartamentoService();

    @Override
    public void init() {
        departamentoService = new DepartamentoService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        try {
            switch (acao != null ? acao : "") {
                case "listar":
                    listarDepartamentos(request, response);
                    break;
                case "buscar":
                    buscarDepartamentoPorId(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ação GET não reconhecida.");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetro 'action' é obrigatório.");
            return;
        }

        switch (action) {
            case "cadastrar":
                cadastrarDepto(request, response);
                break;
            case "atualizar":
                atualizarDepartamento(request, response);
                break;
            case "excluir":
                excluirDepartamento(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ação POST não reconhecida.");
        }
    }


    private void listarDepartamentos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<DepartamentoDTO> lista = departamentoService.listarDepartamentos();
        request.setAttribute("departamento", lista); //mudar aqui nome da página front
        request.getRequestDispatcher("departamento").forward(request, response); // mudar aqui nome da página front
    }

    private void buscarDepartamentoPorId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id_depto"));
            Departamento departamento = departamentoService.buscarDeptoPorId(id);

            if (departamento != null) {
                request.setAttribute("departamento", departamento); //mudar aqui nome da página front
                request.getRequestDispatcher("editar-departamento.jsp").forward(request, response); //aqui tbm
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Departamento não encontrado.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
        }
    }

    private void cadastrarDepto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Departamento departamento = new Departamento();
        departamento.setNomeDepto(request.getParameter("nome_depto"));
        departamento.setIdSetor(request.getParameter("id_setor"));

        boolean sucesso = departamentoService.cadastrarDepto(departamento);
        if (sucesso) {
            response.sendRedirect("/departamento"); // aqui tbm
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao cadastrar departamento!");
        }
    }

    private void atualizarDepartamento(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            Departamento departamento = new Departamento();
            departamento.setIdDepto(Integer.parseInt(request.getParameter("id_depto")));
            departamento.setNomeDepto(request.getParameter("nome_depto"));
            departamento.setIdSetor(request.getParameter("id_setor"));

            boolean sucesso = departamentoService.cadastrarDepto(departamento);
            if (sucesso) {
                response.sendRedirect("/departamento"); // aqui tbm
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao atualizar departamento!");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido!");
        }
    }

    private void excluirDepartamento(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id_depto"));
        boolean sucesso = departamentoService.excluirDepto(id);

        if (sucesso) {
            response.sendRedirect("/departamento"); // aqui tbm
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao excluir departamento!");
        }
    }
}
