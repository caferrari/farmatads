package app.controller;

import core.Banco;
import core.Controller;
import core.Link;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Controller de Cliente
 * 
 * Camada responsável por integrar View e Model
 *
 * @group MyLastJavaApp
 */

public class RelatorioController extends Controller {

    /**
     * Página Principal do Relatório
     */
    public void index()
    {

    }

    /**
     * Página do Relatório de Produto
     */
    public void produto()
    {
        this.disableView(true);
        this.disableTemplate(true);

        // é administrador?
        if (!this._env.request.getSession().getAttribute("f_administrador").equals(true)) {
            this.POSTerror("Você não tem permissão para acessar esta area", Link.criar("relatorio"));
            return;
        }

        try {

            Map parameters = new HashMap();
            String reportFilename = this._env.request.getRealPath("/WEB-INF/report/") + "/produto.jrxml";

            parameters.put("SELECT", "SELECT * FROM produto ORDER BY nome, laboratorio ASC");

            JasperReport compileReport = JasperCompileManager.compileReport(reportFilename);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters,  Banco.getConexao());

            if (this.getPar("tipo").equals("print")) {

                // Abre Relatório para Impressão
                JasperViewer.viewReport(jasperPrint, false);

                // Volta para a Página de Relatório
                this.redirect(Link.criar("relatorio"));

            } else {

                byte[] buffer = JasperExportManager.exportReportToPdf(jasperPrint);

                this._env.response.setContentType("application/pdf");
                this._env.response.setHeader("Content-Disposition", "attachment; filename=produto.pdf");
                this._env.response.setContentLength(buffer.length);
                ServletOutputStream ouputStream = this._env.response.getOutputStream();
                ouputStream.write(buffer, 0, buffer.length);
                ouputStream.flush();
                ouputStream.close();
            }

        } catch(Exception e) {
            System.out.println(e);
        }
    }




    /**
     * Página do Relatório de Produto
     */
    public void notafiscal()
    {
        this.disableView(true);
        this.disableTemplate(true);

        try {
            String link = "venda";
            if (this.getPar("origem").equals("relatorio")) {
                link = "relatorio";
            }

            // Se o não passar codigo redireciona para a Página de Vendas
            if (this.getPar("codigo").equals("")) {
                this.redirect(Link.criar(link));
                return;
            }
            
            Map parameters = new HashMap();
            String reportFilename = this._env.request.getRealPath("/WEB-INF/report/") + "/notafiscal.jrxml";

            String select = "SELECT v.*, iv.id_produto, iv.quantidade, iv.valor_venda, " +
                            "p.nome AS produto, c.nome AS cliente, f.nome AS funcionario " +
                            "FROM venda v " +
                            "INNER JOIN item_venda iv ON iv.id_venda = v.id " +
                            "INNER JOIN produto p ON p.id = iv.id_produto " +
                            "INNER JOIN pessoa c ON c.id = v.id_cliente " +
                            "INNER JOIN pessoa f ON f.id = v.id_funcionario " +
                            "WHERE v.fechada=1 AND v.id = " + this.getPar("codigo");

            parameters.put("SELECT", select);

            JasperReport compileReport = JasperCompileManager.compileReport(reportFilename);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters,  Banco.getConexao());

            if (this.getPar("tipo").equals("print")) {

                // Abre Relatório para Impressão
                JasperViewer.viewReport(jasperPrint, false);

                // Volta para a Página de Relatório
                this.redirect(Link.criar(link));

            } else {

                byte[] buffer = JasperExportManager.exportReportToPdf(jasperPrint);

                this._env.response.setContentType("application/pdf");
                this._env.response.setHeader("Content-Disposition", "attachment; filename=notafiscal.pdf");
                this._env.response.setContentLength(buffer.length);
                ServletOutputStream ouputStream = this._env.response.getOutputStream();
                ouputStream.write(buffer, 0, buffer.length);
                ouputStream.flush();
                ouputStream.close();
            }

        } catch(Exception e) {
            System.out.println(e);
        }
    }

}