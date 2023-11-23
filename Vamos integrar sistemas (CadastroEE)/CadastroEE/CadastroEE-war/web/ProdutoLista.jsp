<%-- 
    Documento: ProdutoDados
    ~ Lucca Polli
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="cadastroee.model.Produto" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Produtos</title>
    <!-- Link para o Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    
    <!-- Link para o Bootstrap JavaScript (copiado do Bootstrap CDN) -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body class="container"> 
    <h1>Lista de Produtos</h1>

    <!-- Link para a página de inclusão de novo produto -->
    <a href="ServletProdutoFC?acao=formIncluir" class="btn btn-primary m-2">Incluir Novo Produto</a> 

    <!-- Tabela de listagem de produtos -->
    <table class="table table-striped"> 
        <thead class="table-dark"> 
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Quantidade</th>
                <th>Preço</th>
                <th>Ações</th>
            </tr>
        </thead>
        <%
            // Obtém a lista de produtos a partir do atributo 'produtos' na requisição
            List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
            if (produtos != null && !produtos.isEmpty()) {
                for (Produto produto : produtos) {
        %>
        <tr>
            <td><%= produto.getIdProduto() %></td>
            <td><%= produto.getNome() %></td>
            <td><%= produto.getQuantidade() %></td>
            <td><%= produto.getPrecoVenda() %></td>
            <td>
                <!-- Links para as ações de 'Alterar' e 'Excluir' cada produto -->
                <a href="ServletProdutoFC?acao=formAlterar&idProduto=<%= produto.getIdProduto() %>" class="btn btn-primary btn-sm">Alterar</a> 
                <a href="ServletProdutoFC?acao=excluir&idProduto=<%= produto.getIdProduto() %>" class="btn btn-danger btn-sm">Excluir</a> 
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <!-- Mensagem de nenhum produto disponível, caso a lista esteja vazia -->
        <tr>
            <td colspan="5">Nenhum produto disponível.</td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
