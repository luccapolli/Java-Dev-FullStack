package cadastroee.servlets;

import cadastroee.model.Produto;
import cadastroee.controller.ProdutoFacadeLocal;
import java.io.IOException;
import java.util.List;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "ServletProdutoFC", urlPatterns = {"/ServletProdutoFC"})
public class ServletProdutoFC extends HttpServlet {

    @EJB
    private ProdutoFacadeLocal facade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        String destino = "ProdutoLista.jsp";
        
        if (acao != null) {
            if (acao.equals("listar")) {
                List<Produto> produtos = facade.findAll();
                request.setAttribute("produtos", produtos);
            } else if (acao.equals("formAlterar")) {
                String idProdutoStr = request.getParameter("idProduto");
                if (idProdutoStr != null) {
                    Integer idProduto = Integer.parseInt(idProdutoStr);
                    Produto produto = facade.find(idProduto);
                    request.setAttribute("produto", produto);
                    destino = "ProdutoDados.jsp";
                }
            } else if (acao.equals("formIncluir")) {
                destino = "ProdutoDados.jsp";
            } else if (acao.equals("excluir")) {
                String idProdutoStr = request.getParameter("idProduto");
                if (idProdutoStr != null) {
                    Integer idProduto = Integer.parseInt(idProdutoStr);
                    Produto produto = facade.find(idProduto);
                    if (produto != null) {
                        facade.remove(produto);
                    }
                    List<Produto> produtos = facade.findAll();
                    request.setAttribute("produtos", produtos);
                }
            } else if (acao.equals("alterar") || acao.equals("incluir")) {
                String idProdutoStr = request.getParameter("idProduto");
                String nome = request.getParameter("nome");
                String quantidadeStr = request.getParameter("quantidade");
                String precoVendaStr = request.getParameter("precoVenda");
                
                Integer idProduto = null;
                Integer quantidade = null;
                Float precoVenda = null;
                
                if (idProdutoStr != null && !idProdutoStr.isEmpty()) {
                    idProduto = Integer.parseInt(idProdutoStr);
                }
                if (quantidadeStr != null && !quantidadeStr.isEmpty()) {
                    quantidade = Integer.parseInt(quantidadeStr);
                }
                if (precoVendaStr != null && !precoVendaStr.isEmpty()) {
                    precoVenda = Float.parseFloat(precoVendaStr);
                }
                
                Produto produto;
                if (acao.equals("alterar")) {
                    produto = facade.find(idProduto);
                    produto.setNome(nome);
                    produto.setQuantidade(quantidade);
                    produto.setPrecoVenda(precoVenda);
                } else {
                    produto = new Produto(idProduto, nome, quantidade, precoVenda);
                }
                facade.edit(produto);
                
                List<Produto> produtos = facade.findAll();
                request.setAttribute("produtos", produtos);
            }
        }
        
        request.getRequestDispatcher(destino).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "ServletProdutoFC";
    }
}
