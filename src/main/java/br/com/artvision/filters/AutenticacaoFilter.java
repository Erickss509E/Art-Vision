package br.com.artvision.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("*.jsp")
public class AutenticacaoFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String url = req.getRequestURI();
        boolean usuarioLogado = (session != null && session.getAttribute("usuario") != null);


        String[] paginasPublicas = {
                "home.jsp", "sobre.jsp", "calculadora.jsp", "login.jsp"// Páginas públicas
        };


        String[] paginasProtegidas = {
                "sistema.jsp", "funcionarios.jsp", "cargos.jsp", "usuarios.jsp", "obras.jsp"// Páginas privadas
        };

        boolean ehPaginaPublica = contemNaLista(url, paginasPublicas);
        boolean ehPaginaProtegida = contemNaLista(url, paginasProtegidas);

        if (ehPaginaPublica || (ehPaginaProtegida && usuarioLogado)) {
            chain.doFilter(request, response); // Continua a requisição
        } else {
            res.sendRedirect("login.jsp"); // Redireciona para login se não estiver autenticado
        }
    }

    private boolean contemNaLista(String url, String[] lista) {
        for (String pagina : lista) {
            if (url.endsWith(pagina)) {
                return true;
            }
        }
        return false;
    }

    public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}

