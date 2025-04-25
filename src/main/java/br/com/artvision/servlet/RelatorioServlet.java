package br.com.artvision.servlet;

import br.com.artvision.dao.ObraDAO;
import br.com.artvision.dao.ManutencaoDAO;
import br.com.artvision.model.Obra;
import br.com.artvision.model.Manutencao;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/relatorio")
public class RelatorioServlet extends HttpServlet {

    private ObraDAO obraDAO = new ObraDAO();
    private ManutencaoDAO manutencaoDAO = new ManutencaoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Obra> obras = obraDAO.listarTodasObras();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=relatorio_obras.pdf");

            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 12);

            Paragraph titulo = new Paragraph("Relatório de Obras e Manutenções", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(Chunk.NEWLINE);

            for (Obra obra : obras) {
                Paragraph pObra = new Paragraph("Obra: " + obra.getNomeObra() + " - Autor: " + obra.getNomeAutor(), fontNormal);
                document.add(pObra);

                List<Manutencao> manutencoes = manutencaoDAO.listarPorObra(obra.getIdObra());
                if (manutencoes.isEmpty()) {
                    document.add(new Paragraph("  Nenhuma manutenção registrada.", fontNormal));
                } else {
                    PdfPTable table = new PdfPTable(3);
                    table.setWidthPercentage(100);
                    table.setSpacingBefore(5f);
                    table.setSpacingAfter(5f);

                    PdfPCell c1 = new PdfPCell(new Phrase("Tipo de Manutenção", fontNormal));
                    PdfPCell c2 = new PdfPCell(new Phrase("Data", fontNormal));
                    PdfPCell c3 = new PdfPCell(new Phrase("Observação", fontNormal));
                    table.addCell(c1);
                    table.addCell(c2);
                    table.addCell(c3);

                    for (Manutencao m : manutencoes) {
                        table.addCell(m.getNomeManutencao());
                        table.addCell(m.getDataManutencao().toString());
                        table.addCell(m.getObservacao());
                    }
                    document.add(table);
                }
                document.add(Chunk.NEWLINE);
            }

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao gerar relatório");
        }
    }
}
