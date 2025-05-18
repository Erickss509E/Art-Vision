package br.com.artvision.controllers;

import br.com.artvision.dto.CargoDTO;
import br.com.artvision.dto.DepartamentoDTO;
import br.com.artvision.dto.FuncionarioDTO;
import br.com.artvision.models.Cargo;
import br.com.artvision.models.Departamento;
import br.com.artvision.models.Funcionario;
import br.com.artvision.services.CargoService;
import br.com.artvision.services.DepartamentoService;
import br.com.artvision.services.FuncionarioService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cargo")
public class CargoController extends HttpServlet {

    private CargoService cargoService = new CargoService();

    @Override
    public void init() {
        cargoService = new CargoService();
    }

    private void listarCargos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CargoDTO> lista = cargoService.listarCargos();
        request.setAttribute("cargo", lista); //mudar aqui nome da página front
        request.getRequestDispatcher("cargo").forward(request, response); // mudar aqui nome da página front
    }

    private void buscarCargoPorId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id_cargo"));
            Cargo cargo = cargoService.buscarCargosPorId(id);

            if (cargo != null) {
                request.setAttribute("cargo", cargo); //mudar aqui nome da página front
                request.getRequestDispatcher("editar-cargo.html").forward(request, response); //aqui tbm
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Cargo não encontrado.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
        }
    }

    private void cadastrarCargo(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Cargo cargo = new Cargo();
        cargo.setNome(request.getParameter("nome_cargo"));
        cargo.setIdSetor(Integer.parseInt(request.getParameter("id_setor")));

        boolean sucesso = cargoService.cadastrarCargos(cargo);
        if (sucesso) {
            response.sendRedirect("/cargo"); // aqui tbm
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao cadastrar cargo!");
        }
    }

    private void atualizarCargo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cargo cargo = new Cargo();
        cargo.setNome(request.getParameter("nome_cargo"));
        cargo.setIdSetor(Integer.parseInt(request.getParameter("id_setor")));

        boolean sucesso = cargoService.cadastrarCargos(cargo);
        if (sucesso) {
            response.sendRedirect("/cargo"); // aqui tbm
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao atualizar cargo!");
        }
    }

    private void excluirCargo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id_cargo"));
        boolean sucesso = cargoService.excluirCargo(id);

        if (sucesso) {
            response.sendRedirect("/cargo"); // aqui tbm
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao excluir cargo!");
        }
    }
}
