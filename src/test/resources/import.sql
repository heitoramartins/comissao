INSERT INTO `cargo` VALUES ('1', 'VENDEDOR');


INSERT INTO `categoria` VALUES ('1', 'eletronico', '1');
INSERT INTO `categoria` VALUES ('2', 'eletrodomesticos', '2');
INSERT INTO `categoria` VALUES ('9', 'computadores', '1');
INSERT INTO `categoria` VALUES ('10', 'video games', '1');
INSERT INTO `categoria` VALUES ('12', 'utilitarios ', '2');


INSERT INTO `cliente` VALUES ('1', '19270451000177', null, '564889', 'armarinhos@gmail.com', 'Armarinhos Fernandes', 'JURIDICA');
INSERT INTO `cliente` VALUES ('2', null, '98176897272', null, 'heitoramartins@gmail.com', 'Heitor Araujo', 'FISICA');


INSERT INTO `endereco` VALUES ('1', '02038030', 'Sao Paulo', 'ap 33', 'rua francisca julia', '46', 'SP', '1');
INSERT INTO `endereco` VALUES ('2', '02135023', 'Sao Paulo', 'ap 02', 'rua nova', '33', 'SP', '2');


INSERT INTO `item` VALUES ('66', '1', '1500.00', '1500.00', '39', '3');
INSERT INTO `item` VALUES ('67', '1', '1800.00', '1800.00', '39', '1');


INSERT INTO `pedido` VALUES ('39', '2017-01-26 01:29:05', '2017-01-26 01:22:59', '02038030', 'Sao Paulo', 'AP 33', 'Rua Jovita', '25', 'SP', 'BOLETO_BANCARIO', 'ORCAMENTO', '346.50', '30.00', '2848.50', '2', '2');


INSERT INTO `produto` VALUES ('1', 'XBOX 360', '24', '1800.00', '10');
INSERT INTO `produto` VALUES ('2', 'MAC HP 1258', '20', '4000.00', '9');
INSERT INTO `produto` VALUES ('3', 'MAQUINA DE LAVA', '44', '1500.00', '12');
INSERT INTO `produto` VALUES ('4', 'BATEDEIRA', '50', '400.00', '12');
INSERT INTO `produto` VALUES ('5', 'LIQUIDIFICADO', '20', '100.00', '12');

INSERT INTO `usuario` VALUES ('1', 'juliano@gmail.com', 'Juliano Dias', '2500.00', '123', '1');
INSERT INTO `usuario` VALUES ('2', 'marcelo@gmail.com', 'Marcelo Paes', '3500.00', '123', '1');