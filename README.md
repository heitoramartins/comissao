###### EXECUTE NO MYSQL	

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cargo`
-- ----------------------------
DROP TABLE IF EXISTS `cargo`;
CREATE TABLE `cargo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cargo
-- ----------------------------
INSERT INTO `cargo` VALUES ('1', 'VENDEDOR');

-- ----------------------------
-- Table structure for `categoria`
-- ----------------------------
DROP TABLE IF EXISTS `categoria`;
CREATE TABLE `categoria` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `fk_categoria` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5bavcwlt8t96r5qilx1go4sxk` (`fk_categoria`),
  CONSTRAINT `FK5bavcwlt8t96r5qilx1go4sxk` FOREIGN KEY (`fk_categoria`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of categoria
-- ----------------------------
INSERT INTO `categoria` VALUES ('1', 'eletronico', '1');
INSERT INTO `categoria` VALUES ('2', 'eletrodomesticos', '2');
INSERT INTO `categoria` VALUES ('9', 'computadores', '1');
INSERT INTO `categoria` VALUES ('10', 'video games', '1');
INSERT INTO `categoria` VALUES ('12', 'utilitarios ', '2');

-- ----------------------------
-- Table structure for `cliente`
-- ----------------------------
DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cnpj` varchar(255) DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `doc_receita_federal` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `tipo` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cliente
-- ----------------------------
INSERT INTO `cliente` VALUES ('1', '19270451000177', null, '564889', 'armarinhos@gmail.com', 'Armarinhos Fernandes', 'JURIDICA');
INSERT INTO `cliente` VALUES ('2', null, '98176897272', null, 'heitoramartins@gmail.com', 'Heitor Araujo', 'FISICA');

-- ----------------------------
-- Table structure for `endereco`
-- ----------------------------
DROP TABLE IF EXISTS `endereco`;
CREATE TABLE `endereco` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cep` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `uf` varchar(255) DEFAULT NULL,
  `fk_cliente` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6joug729xqkstyqgm6j6a0o5y` (`fk_cliente`),
  CONSTRAINT `FK6joug729xqkstyqgm6j6a0o5y` FOREIGN KEY (`fk_cliente`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of endereco
-- ----------------------------
INSERT INTO `endereco` VALUES ('1', '02038030', 'Sao Paulo', 'ap 33', 'rua francisca julia', '46', 'SP', '1');
INSERT INTO `endereco` VALUES ('2', '02135023', 'Sao Paulo', 'ap 02', 'rua nova', '33', 'SP', '2');

-- ----------------------------
-- Table structure for `item`
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quantidade` int(11) DEFAULT NULL,
  `vlr_unitario` decimal(19,2) DEFAULT NULL,
  `fk_pedido` bigint(20) DEFAULT NULL,
  `fk_produto` bigint(20) DEFAULT NULL,
  `vlr_total` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcp0qbq7w641kh6gjgyirhyqh9` (`fk_pedido`),
  KEY `FKqwdbn14f6jw35dabfaq2erv0i` (`fk_produto`),
  CONSTRAINT `FKcp0qbq7w641kh6gjgyirhyqh9` FOREIGN KEY (`fk_pedido`) REFERENCES `pedido` (`id`),
  CONSTRAINT `FKqwdbn14f6jw35dabfaq2erv0i` FOREIGN KEY (`fk_produto`) REFERENCES `produto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item
-- ----------------------------

-- ----------------------------
-- Table structure for `pedido`
-- ----------------------------
DROP TABLE IF EXISTS `pedido`;
CREATE TABLE `pedido` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_entrega` datetime DEFAULT NULL,
  `data_venda` datetime DEFAULT NULL,
  `entrega_cep` varchar(9) DEFAULT NULL,
  `entrega_cidade` varchar(60) DEFAULT NULL,
  `entrega_complemento` varchar(150) DEFAULT NULL,
  `entrega_logradouro` varchar(150) DEFAULT NULL,
  `entrega_numero` varchar(20) DEFAULT NULL,
  `entrega_uf` varchar(60) DEFAULT NULL,
  `forma_pagamento` varchar(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `status_desconto` varchar(255) DEFAULT NULL,
  `vlr_desconto` decimal(19,2) DEFAULT NULL,
  `vlr_frete` decimal(19,2) DEFAULT NULL,
  `vlr_total` decimal(19,2) DEFAULT NULL,
  `fk_cliente` bigint(20) DEFAULT NULL,
  `fk_funcionario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg3dm7v4kilvvujf01n8ikqeaq` (`fk_cliente`),
  KEY `FKi9rxbti6n0u0bn6indm4jg07x` (`fk_funcionario`),
  CONSTRAINT `FKg3dm7v4kilvvujf01n8ikqeaq` FOREIGN KEY (`fk_cliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FKi9rxbti6n0u0bn6indm4jg07x` FOREIGN KEY (`fk_funcionario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pedido
-- ----------------------------

-- ----------------------------
-- Table structure for `produto`
-- ----------------------------
DROP TABLE IF EXISTS `produto`;
CREATE TABLE `produto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `qtde_estoque` int(11) DEFAULT NULL,
  `vlr_unitario` decimal(19,2) DEFAULT NULL,
  `fk_categoria` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlw139dlmif9wtgon13eskibw3` (`fk_categoria`),
  CONSTRAINT `FKlw139dlmif9wtgon13eskibw3` FOREIGN KEY (`fk_categoria`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of produto
-- ----------------------------
INSERT INTO `produto` VALUES ('1', 'XBOX 360', '25', '1800.00', '10');
INSERT INTO `produto` VALUES ('2', 'MAC HP 1258', '20', '4000.00', '9');
INSERT INTO `produto` VALUES ('3', 'MAQUINA DE LAVA', '45', '1500.00', '12');
INSERT INTO `produto` VALUES ('4', 'BATEDEIRA', '50', '400.00', '12');
INSERT INTO `produto` VALUES ('5', 'LIQUIDIFICADO', '20', '100.00', '12');

-- ----------------------------
-- Table structure for `usuario`
-- ----------------------------
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `salario` decimal(19,2) DEFAULT NULL,
  `senha` varchar(80) DEFAULT NULL,
  `fk_cargo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe6feujycfh7ib72cktfu9ml7l` (`fk_cargo`),
  CONSTRAINT `FKe6feujycfh7ib72cktfu9ml7l` FOREIGN KEY (`fk_cargo`) REFERENCES `cargo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usuario
-- ----------------------------
INSERT INTO `usuario` VALUES ('1', 'juliano@gmail.com', 'Juliano Dias', '2500.00', '123', '1');
INSERT INTO `usuario` VALUES ('2', 'marcelo@gmail.com', 'Marcelo Paes', '3500.00', '123', '1');

######################################################### PROJETO COMISSAO ###########################################################################
Esse projeto tem como objetivo ser uma API WebServices de pedidos (vendas)  clone o projeto e execute a classe CompraApplication (javaApplication)

##### Premissas
- Ao realizar uma compra o programa ira colher alguams informaçoes dos modulos ja existentes e executara calculos de descontos 
- e enviara um email para o solicitante com os dados do seu pedido, tambem sera possivel apos uma devida aprovacao ou reprovacao
- ter um desconto extra 
---

##### Recursos

/pedido
/pedido/{id}
/pedido/desconto/aprovado/{id}
/pedido/desconto/reprovado/{id}

#### Criar Pedido

- Para um pedido com usuario e clientes ja cadastrados, na primeira venda o sistema realiza um calculo inicial de  5% de desconto
  mais o valor do frete, logo apos o pedido ser persistido um email sera enviado para o solicitante com os dados do pedido
  obs(cadastrar na tabela cliente campo email "heitoramartins@gmail.com")
###### 
```shell
HTTP POST localhost:8081/pedido
```
```json

  {
     "cliente": {"id": 2},
     "usuario": {"id": 1},
     "valorFrete": 20,
     "formaPagamento": "DINHEIRO",
    
     "itens": [{"produto":{"id": 3}, "quantidade": 1},
               {"produto":{"id": 1}, "quantidade": 1}],
    
     "enderecoEntrega": {
     "logradouro": "Rua Jovita",
     "numero": "25",
     "complemento": "AP 33",
     "cidade": "Sao Paulo",
     "uf": "SP",
     "cep": "02038030"
    }
   
 }

```
para consultar um pedido registrado
```shell
HTTP GET localhost:8081/pedido/1
```
```json return
{
  "id": 39,
  "cliente": {
    "id": 2,
    "nome": "Heitor Araujo",
    "cpf": "98176897272",
    "cnpj": null,
    "email": "heitoramartins@gmail.com",
    "documentoReceitaFederal": null,
    "tipo": "FISICA",
    "enderecos": [
      {
        "logradouro": "rua nova",
        "numero": "33",
        "complemento": "ap 02",
        "cidade": "Sao Paulo",
        "uf": "SP",
        "cep": "02135023"
      }
    ]
  },
  "itens": [
    {
      "id": 66,
      "produto": {
        "nome": "MAQUINA DE LAVA",
        "vlrUnitario": 1500,
        "quantidadeEstoque": 44,
        "categoria": {
          "id": 12,
          "descricao": "utilitarios "
        }
      },
      "quantidade": 1
    },
    {
      "id": 67,
      "produto": {
        "nome": "XBOX 360",
        "vlrUnitario": 1800,
        "quantidadeEstoque": 24,
        "categoria": {
          "id": 10,
          "descricao": "video games"
        }
      },
      "quantidade": 1
    }
  ],
  "dataVenda": "2017-01-26 01:22:59",
  "dataEntrega": "2017-01-26 01:29:05",
  "usuario": {
    "nome": "Marcelo Paes"
  },
  "valorTotal": 2848.5,
  "valorFrete": 30,
  "valorDesconto": 346.5,
  "status": "ORCAMENTO",
  "statusDesconto": "APROVADO",
  "formaPagamento": "BOLETO_BANCARIO",
  "enderecoEntrega": {
    "logradouro": "Rua Jovita",
    "numero": "25",
    "complemento": "AP 33",
    "cidade": "Sao Paulo",
    "uf": "SP",
    "cep": "02038030"
  }
}
```

###### A primeira compra sempre sera inicializada com status ORCAMENTO e estara com status_desconto EM_APROVACAO para um possivel desconto extra
- apos a primeira compra o solicitante podera efetuar uma emissao finalizando um pedido o status passara para EMITIDO
```shell
HTTP PUT localhost:8081/pedido/31
```
```json

  {
    "id": 1,
    "status":"EMITIDO"
 }


```
Caso o solicitante queira cancelar o orcamento 

HTTP PUT localhost:8081/pedido/1
```
```json

  {
    "id": 39,
    "status":"CANCELADO"
 }

enquanto um pedido estiver com status ORCAMENTO e status_desconto EM_APROVACAO ele podera receber uma aprovacao para ganhar um desconto extra 
esse desconto sera de 10% em cima do valor do orcamento o status_desconto passara para APROVADO sera enviado um email para o solicitante
```shell
HTTP PUT /pedido/desconto/aprovado/1
```
```json
{
    "id": 1
  
}
```
caso o solicitante queira reprovar o desconto

```shell
HTTP PUT /pedido/desconto/reprovado/1
```
```json
{
    "id": 1
  
}
```
tecnologias utilizadas  springboot , velocity, spring data, jpa , hibermate, valocity tools, mavem, MySql 