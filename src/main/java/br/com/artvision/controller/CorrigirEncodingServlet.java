package br.com.artvision.controller;

import br.com.artvision.dao.ObraDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/corrigirEncoding")
public class CorrigirEncodingServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        try {
            int idObra = Integer.parseInt(request.getParameter("idObra"));
            String novoNome = "Testes e atualização de periféricos";
            
            ObraDAO obraDAO = new ObraDAO();
            boolean sucesso = obraDAO.corrigirEncodingNome(idObra, novoNome);
            
            if (sucesso) {
                response.getWriter().write("{\"success\": true, \"message\": \"Nome atualizado com sucesso\"}");
            } else {
                response.getWriter().write("{\"success\": false, \"message\": \"Erro ao atualizar o nome\"}");
            }
            
        } catch (Exception e) {
            response.getWriter().write("{\"success\": false, \"message\": \"" + e.getMessage() + "\"}");
        }
    }
} 