package br.com.artvision.controllers;

import br.com.artvision.dto.CargoDTO;
import br.com.artvision.models.Cargo;
import br.com.artvision.services.CargoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/sistema/cargo")
public class CargoController extends HttpServlet {

    private CargoService cargoService = new CargoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.equals("listar")) {
            listarCargos(request, response);
        } else if (action.equals("buscar")) {
            buscarCargo(request, response);
        } else if (action.equals("excluir")) {
            excluirCargo(request, response);
        } else if (action.equals("novo")) {
            abrirPopupCadastro(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/sistema/cargo?action=listar");
        }
    }

    private void listarCargos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CargoDTO> cargos = cargoService.listarCargos();
        request.setAttribute("cargos", cargos);
        request.getRequestDispatcher("/sistema/cargo.jsp").forward(request, response);
    }

    private void buscarCargo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id_cargo"));
            Cargo cargo = cargoService.buscarCargoPorId(id);
            
            if (cargo != null) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                String json = String.format("{\"id\":%d,\"nome\":\"%s\",\"idSetor\":%d}", 
                    cargo.getId(), cargo.getNome(), cargo.getIdSetor());
                response.getWriter().write(json);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Cargo não encontrado.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
        }
    }

    private void excluirCargo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id_cargo"));
            cargoService.excluirCargo(id);
            response.sendRedirect(request.getContextPath() + "/sistema/cargo?action=listar");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
        }
    }

    private void abrirPopupCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CargoDTO> cargos = cargoService.listarCargos();
        request.setAttribute("cargos", cargos);
        request.setAttribute("abrirPopupCadastro", true);
        request.getRequestDispatcher("/sistema/cargo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");

        if ("cadastrar".equals(action)) {
            cadastrarCargo(request, response);
        } else if ("atualizar".equals(action)) {
            atualizarCargo(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/sistema/cargo?action=listar");
        }
    }

    private void cadastrarCargo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String nome = request.getParameter("nome_cargo");
            int idSetor = Integer.parseInt(request.getParameter("id_setor"));

            Cargo cargo = new Cargo();
            cargo.setNome(nome);
            cargo.setIdSetor(idSetor);

            cargoService.cadastrarCargo(cargo);

            response.sendRedirect(request.getContextPath() + "/sistema/cargo?action=listar");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetro inválido.");
        }
    }

    private void atualizarCargo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id_cargo"));
            String nome = request.getParameter("nome_cargo");
            int idSetor = Integer.parseInt(request.getParameter("id_setor"));

            Cargo cargo = new Cargo();
            cargo.setId(id);
            cargo.setNome(nome);
            cargo.setIdSetor(idSetor);

            cargoService.atualizarCargo(cargo);

            response.sendRedirect(request.getContextPath() + "/sistema/cargo?action=listar");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetro inválido.");
        }
    }
}
