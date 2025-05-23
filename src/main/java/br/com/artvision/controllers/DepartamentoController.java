package br.com.artvision.controllers;

import br.com.artvision.dto.DepartamentoDTO;
import br.com.artvision.dto.FuncionarioDTO;
import br.com.artvision.models.Departamento;
import br.com.artvision.models.Funcionario;
import br.com.artvision.services.DepartamentoService;
import br.com.artvision.services.FuncionarioService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/sistema/departamento")
public class DepartamentoController extends HttpServlet {

    private DepartamentoService departamentoService = new DepartamentoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");

        try {
            switch (acao != null ? acao : "") {
                case "buscar":
                    buscarDepartamentoPorId(request, response);
                    break;
                case "listar":
                case "":
                    listarDepartamentos(request, response);
                    break;
                default:
                    listarDepartamentos(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listarDepartamentos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<DepartamentoDTO> lista = departamentoService.listarDepartamentos();
        request.setAttribute("departamentos", lista);
        request.getRequestDispatcher("/sistema/departamento.jsp").forward(request, response);
    }

    private void buscarDepartamentoPorId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id_depto"));
            Departamento departamento = departamentoService.buscarDeptoPorId(id);

            if (departamento != null) {
                request.setAttribute("departamento", departamento);
                request.getRequestDispatcher("/sistema/departamento/editar_departamento.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Departamento não encontrado.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
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

    private void cadastrarDepto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Departamento departamento = new Departamento();
        departamento.setNomeDepto(request.getParameter("nome_depto"));
        departamento.setIdSetor(Integer.parseInt(request.getParameter("id_setor")));

        if (departamentoService.cadastrarDepto(departamento)) {
            response.sendRedirect(request.getContextPath() + "/sistema/departamento.jsp");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao cadastrar departamento!");
        }
    }

    private void atualizarDepartamento(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            Departamento departamento = new Departamento();
            departamento.setIdDepto(Integer.parseInt(request.getParameter("id_depto")));
            departamento.setNomeDepto(request.getParameter("nome_depto"));
            departamento.setIdSetor(Integer.parseInt(request.getParameter("id_setor")));

            if (departamentoService.atualizarDepto(departamento)) {
                response.sendRedirect(request.getContextPath() + "/sistema/departamento.jsp");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao atualizar departamento!");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido!");
        }
    }

    private void excluirDepartamento(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id_depto"));
            if (departamentoService.excluirDepto(id)) {
                listarDepartamentos(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao excluir departamento!");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido!");
        }
    }
}
