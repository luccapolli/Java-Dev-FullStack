<%-- 
    Documento: ProdutoDados
    ~ Lucca Polli
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Produto</title>
    
    <!-- link para o Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    
    <!-- link para o Bootstrap JavaScript (copiado do Bootstrap CDN) -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body class="container"> <!-- Define a classe 'container' para o body -->
    <h1>Cadastro de Produto</h1>
    
    <form action="ServletProdutoFC" method="post" class="form"> <!-- Define o formulário com a classe 'form' -->
        <input type="hidden" name="acao" value="${empty produto ? 'incluir' : 'alterar'}">
        <!-- Define um campo oculto 'acao' com valor 'incluir' se 'produto' estiver vazio, caso contrário, 'alterar' -->
        <c:if test="${not empty produto}">
            <!-- Verifica se 'produto' não está vazio -->
            <input type="hidden" name="idProduto" value="${produto.idProduto}">
            <!-- Define um campo oculto 'idProduto' com o valor do ID do produto se 'produto' não estiver vazio -->
        </c:if>
        
        <div class="mb-3"> 
            <!-- Define uma divisão com margem inferior -->
            <label for="nome" class="form-label">Nome:</label> 
            <!-- Cria um rótulo 'Nome' com a classe 'form-label' -->
            <input type="text" id="nome" name="nome" value="${empty produto ? '' : produto.nome}" required class="form-control"> 
            <!-- Cria um campo de texto 'Nome' com a classe 'form-control', preenchido com o valor de 'produto.nome', se disponível -->
        </div>
        
        <div class="mb-3"> 
            <!-- Define uma divisão com margem inferior -->
            <label for="quantidade" class="form-label">Quantidade:</label> 
            <!-- Cria um rótulo 'Quantidade' com a classe 'form-label' -->
            <input type="number" id="quantidade" name="quantidade" value="${empty produto ? '' : produto.quantidade}" required class="form-control"> 
            <!-- Cria um campo numérico 'Quantidade' com a classe 'form-control', preenchido com o valor de 'produto.quantidade', se disponível -->
        </div>
        
        <div class="mb-3"> 
            <!-- Define uma divisão com margem inferior -->
            <label for="precoVenda" class="form-label">Preço de Venda:</label> 
            <!-- Cria um rótulo 'Preço de Venda' com a classe 'form-label' -->
            <input type="number" id="precoVenda" name="precoVenda" value="${empty produto ? '' : produto.precoVenda}" required class="form-control"> 
            <!-- Cria um campo numérico 'Preço de Venda' com a classe 'form-control', preenchido com o valor de 'produto.precoVenda', se disponível -->
        </div>
        
        <input type="submit" value="${empty produto ? 'Incluir' : 'Alterar'} Produto" class="btn btn-primary"> 
        <!-- Cria um botão de envio com texto 'Incluir Produto' se 'produto' estiver vazio, caso contrário, 'Alterar Produto' -->
    </form>
</body>
</html>
