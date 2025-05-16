package br.com.artvision.controllers;

import br.com.artvision.dao.EstadoConservacaoDAO;
import br.com.artvision.dao.ObraDAO;
import br.com.artvision.models.EstadoConservacao;
import br.com.artvision.models.Obra;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/estadoConservacao")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
        maxFileSize = 1024 * 1024 * 10,      
        maxRequestSize = 1024 * 1024 * 50)   
public class EstadoConservacaoServlet extends HttpServlet {

    private EstadoConservacaoDAO estadoDAO = new EstadoConservacaoDAO();
    private ObraDAO obraDAO = new ObraDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String obraIdStr = request.getParameter("obraId");
        if (obraIdStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetro obraId é obrigatório");
            return;
        }

        int obraId = Integer.parseInt(obraIdStr);
        Obra obra = obraDAO.buscarPorId(obraId);
        if (obra == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Obra não encontrada");
            return;
        }

        List<EstadoConservacao> estados = estadoDAO.listarPorObra(obraId);

        request.setAttribute("obra", obra);
        request.setAttribute("estados", estados);
        request.getRequestDispatcher("/Sistema/estadoConservacao.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String obraIdStr = request.getParameter("obraId");
        String descricao = request.getParameter("descricao");
        String historico = request.getParameter("historicoManutencao");

        if (obraIdStr == null || descricao == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetros obrigatórios ausentes");
            return;
        }

        int obraId = Integer.parseInt(obraIdStr);

        // Processar upload de imagens
        StringBuilder imagensCaminhos = new StringBuilder();
        for (Part part : request.getParts()) {
            if (part.getName().equals("imagens") && part.getSize() > 0) {
                String fileName = extractFileName(part);
                String newFileName = UUID.randomUUID().toString() + "_" + fileName;
                String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                part.write(uploadPath + File.separator + newFileName);
                if (imagensCaminhos.length() > 0) {
                    imagensCaminhos.append(",");
                }
                imagensCaminhos.append("uploads/").append(newFileName);
            }
        }

        EstadoConservacao estado = new EstadoConservacao();
        estado.setObraId(obraId);
        estado.setDescricao(descricao);
        estado.setHistoricoManutencao(historico);
        estado.setImagens(imagensCaminhos.toString());

        estadoDAO.cadastrarEstadoConservacao(estado);

        response.sendRedirect(request.getContextPath() + "/estadoConservacao?obraId=" + obraId);
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}
