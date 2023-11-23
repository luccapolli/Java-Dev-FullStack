CREATE TABLE Movimento (
  idMovimento INTEGER IDENTITY(1,1) NOT NULL PRIMARY KEY,  -- ID único do movimento
  idUsuario INTEGER NOT NULL CHECK (idUsuario > 0),       -- ID do usuário relacionado
  idPessoa INTEGER NOT NULL CHECK (idPessoa > 0),         -- ID da pessoa relacionada
  idProduto INTEGER NOT NULL CHECK (idProduto > 0),       -- ID do produto relacionado
  quantidade INTEGER NULL CHECK (quantidade > 0),         
  tipo CHAR(1) NULL,                                      
  valorUnitario NUMERIC(10, 2) NULL,                      -- Valor unitário da transação
  FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario),  -- Chave estrangeira para a tabela 'Usuario'
  FOREIGN KEY (idPessoa) REFERENCES Pessoa(idPessoa),     -- Chave estrangeira para a tabela 'Pessoa'
  FOREIGN KEY (idProduto) REFERENCES Produto(idProduto)  -- Chave estrangeira para a tabela 'Produto'
);

CREATE INDEX Movimento_FKIndex1 ON Movimento(idUsuario);  -- Índice para a chave estrangeira 'idUsuario'
CREATE INDEX Movimento_FKIndex2 ON Movimento(idProduto);  -- Índice para a chave estrangeira 'idProduto'
CREATE INDEX Movimento_FKIndex3 ON Movimento(idPessoa);  -- Índice para a chave estrangeira 'idPessoa'


CREATE SEQUENCE PessoaSeq 
 AS [int]
 START WITH 1
 INCREMENT BY 1
 MINVALUE -2147483648
 MAXVALUE 2147483647
 CACHE;


CREATE TABLE PessoaFisica (
  idPessoa INT NOT NULL PRIMARY KEY,  -- ID da pessoa (relacionado à sequência 'PessoaSeq')
  cpf VARCHAR(11) NULL,               
  FOREIGN KEY (idPessoa) REFERENCES Pessoa(idPessoa)  -- Chave estrangeira para a tabela 'Pessoa'
);

CREATE INDEX PessaFisica_FKIndex1 ON PessoaFisica(idPessoa);  -- Índice para a chave estrangeira 'idPessoa'


CREATE TABLE Pessoa (
  idPessoa INTEGER NOT NULL PRIMARY KEY,  -- ID da pessoa (relacionado à sequência 'PessoaSeq')
  nome VARCHAR(255) NULL,                
  logradouro VARCHAR(255) NULL,          
  cidade VARCHAR(255) NULL,              
  estado CHAR(2) NULL,                   
  telefone VARCHAR(11) NULL,             
  email VARCHAR(255) NULL                
);


CREATE TABLE PessoaJuridica (
    idPessoa INT NOT NULL PRIMARY KEY,  -- ID da pessoa (relacionado à sequência 'PessoaSeq')
    cnpj VARCHAR(14) NULL,             
    FOREIGN KEY (idPessoa) REFERENCES Pessoa(idPessoa)  -- Chave estrangeira para a tabela 'Pessoa'
);

CREATE INDEX PessoaJuridica_FKIndex1 ON PessoaJuridica(idPessoa);  -- Índice para a chave estrangeira 'idPessoa'


CREATE TABLE Produto (
  idProduto INTEGER IDENTITY(1,1) NOT NULL PRIMARY KEY,  -- ID único do produto
  nome VARCHAR(255) NULL,               
  quantidade INTEGER NULL CHECK (quantidade > 0),      
  precoVenda NUMERIC(10, 2) NULL   -- Preço de venda do produto (com precisão de 2 casas decimais)
);


CREATE TABLE Usuario (
  idUsuario INTEGER IDENTITY(1,1) NOT NULL PRIMARY KEY,  -- ID único do usuário
  login VARCHAR(20) NULL,               
  senha VARCHAR(20) NULL                
);
