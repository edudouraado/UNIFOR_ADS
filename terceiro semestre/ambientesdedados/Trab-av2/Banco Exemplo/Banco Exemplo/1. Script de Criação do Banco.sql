-- Criando o Schema BD_Vendas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS bd_vendas DEFAULT CHARACTER SET utf8 ;
USE bd_vendas ;

-- Criando Tabelas e PKs
-- -----------------------------------------------------
-- Table categoria
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS categoria (
  idcategoria INT NOT NULL,
  Descricao VARCHAR(45) NOT NULL,
  PRIMARY KEY (idcategoria))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table produto
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS produto (
  idproduto INT NOT NULL,
  Nome VARCHAR(45) NOT NULL,
  Descricao VARCHAR(200) NULL,
  Preco DECIMAL(18,2) NOT NULL DEFAULT 0,
  QuantEstoque DECIMAL(10,2) NOT NULL DEFAULT 0,
  categoria_idcategoria INT NOT NULL,
  PRIMARY KEY (idproduto)
  )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table tipocliente
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tipocliente (
  idtipocliente INT NOT NULL,
  Descricao VARCHAR(45) NULL,
  PRIMARY KEY (idtipocliente))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table cliente
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cliente (
  idcliente INT NOT NULL,
  Nome VARCHAR(45) NOT NULL,
  Email VARCHAR(100) NOT NULL,
  Nascimento DATETIME NULL,
  Senha VARCHAR(200) NULL,
  tipocliente_idtipocliente INT NOT NULL,
  DataRegistro DATETIME NOT NULL DEFAULT Now(),
  PRIMARY KEY (idcliente))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table tipoendereco
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tipoendereco (
  idtipoendereco INT NOT NULL,
  Descricao VARCHAR(45) NOT NULL,
  PRIMARY KEY (idtipoendereco))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Endereco
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS endereco (
  idEndereco INT NOT NULL,
  EnderecoPadrao TINYINT NOT NULL DEFAULT 0,
  Logradouro VARCHAR(45) NULL,
  Numero VARCHAR(45) NULL,
  Complemento VARCHAR(45) NULL,
  Bairro VARCHAR(45) NULL,
  Cidade VARCHAR(45) NULL,
  UF VARCHAR(2) NULL,
  CEP VARCHAR(8) NULL,
  tipoendereco_idtipoendereco INT NOT NULL,
  cliente_idcliente INT NOT NULL,
  PRIMARY KEY (idEndereco))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Telefone
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS telefone (
  Numero VARCHAR(42) NOT NULL,
  cliente_idcliente INT NOT NULL,
  PRIMARY KEY (Numero, cliente_idcliente))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table status
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS status (
  idstatus INT NOT NULL,
  Descricao VARCHAR(45) NOT NULL,
  PRIMARY KEY (idstatus))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table pedido
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pedido (
  idpedido INT NOT NULL,
  status_idstatus INT NOT NULL,
  Datapedido DATETIME NOT NULL DEFAULT Now(),
  ValorTotalpedido DECIMAL(18,2) NOT NULL DEFAULT 0,
  cliente_idcliente INT NOT NULL,
  PRIMARY KEY (idpedido)  )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table pedido_has_produto
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pedido_has_produto (
  idpedidoproduto INT NOT NULL AUTO_INCREMENT,
  pedido_idpedido INT NOT NULL,
  produto_idproduto INT NOT NULL,
  Quantidade DECIMAL(10,2) NOT NULL,
  PrecoUnitario DECIMAL(18,2) NOT NULL,
  PRIMARY KEY (idpedidoproduto))
ENGINE = InnoDB;

-- Criando FKs.

CREATE INDEX fk_produto_categoria_idx ON produto (categoria_idcategoria ASC) ;

Alter Table produto 
add CONSTRAINT fk_produto_categoria
    FOREIGN KEY (categoria_idcategoria)
    REFERENCES categoria (idcategoria)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;


CREATE INDEX fk_cliente_tipocliente_idx ON cliente (tipocliente_idtipocliente ASC) ;
Alter Table cliente
ADD CONSTRAINT fk_cliente_tipocliente
    FOREIGN KEY (tipocliente_idtipocliente)
    REFERENCES tipocliente (idtipocliente)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;


CREATE INDEX fk_Endereco_tipoendereco1_idx ON endereco (tipoendereco_idtipoendereco ASC) ;

CREATE INDEX fk_Endereco_cliente1_idx ON endereco (cliente_idcliente ASC) ;

Alter Table endereco
ADD  CONSTRAINT fk_Endereco_tipoendereco
    FOREIGN KEY (tipoendereco_idtipoendereco)
    REFERENCES tipoendereco (idtipoendereco)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
ADD  CONSTRAINT fk_Endereco_cliente
    FOREIGN KEY (cliente_idcliente)
    REFERENCES cliente (idcliente)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;


CREATE INDEX fk_Telefone_cliente_idx ON telefone (cliente_idcliente ASC) ;

Alter Table telefone
ADD  CONSTRAINT fk_Telefone_cliente
    FOREIGN KEY (cliente_idcliente)
    REFERENCES cliente (idcliente)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
    
    
    CREATE INDEX fk_pedido_status1_idx ON pedido (status_idstatus ASC) ;

CREATE INDEX fk_pedido_cliente1_idx ON pedido (cliente_idcliente ASC) ;

Alter Table pedido
ADD CONSTRAINT fk_pedido_status
    FOREIGN KEY (status_idstatus)
    REFERENCES status (idstatus)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
ADD CONSTRAINT fk_pedido_cliente
    FOREIGN KEY (cliente_idcliente)
    REFERENCES cliente (idcliente)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
    
CREATE INDEX fk_pedido_has_produto_produto_idx ON pedido_has_produto (produto_idproduto ASC) ;

CREATE INDEX fk_pedido_has_produto_pedido_idx ON pedido_has_produto (pedido_idpedido ASC) ;



Alter Table pedido_has_produto
ADD CONSTRAINT fk_pedido_has_produto_pedido
    FOREIGN KEY (pedido_idpedido)
    REFERENCES pedido (idpedido)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
ADD  CONSTRAINT fk_pedido_has_produto_produto
    FOREIGN KEY (produto_idproduto)
    REFERENCES produto (idproduto)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
