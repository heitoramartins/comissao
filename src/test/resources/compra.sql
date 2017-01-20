-- ----------------------------
-- Records of cargo
-- ----------------------------
INSERT INTO `cargo` VALUES ('1', 'VENDEDOR');

-- ----------------------------
-- Records of categoria
-- ----------------------------
INSERT INTO `categoria` VALUES ('1', 'eletronico', '1');
INSERT INTO `categoria` VALUES ('2', 'eletrodomesticos', '2');
INSERT INTO `categoria` VALUES ('9', 'computadores', '1');
INSERT INTO `categoria` VALUES ('10', 'video games', '1');
INSERT INTO `categoria` VALUES ('12', 'utilitarios ', '2');


-- ----------------------------
-- Records of cliente
-- ----------------------------
INSERT INTO `cliente` VALUES ('1', '19270451000177', null, '564889', 'armarinhos@gmail.com', 'Armarinhos Fernandes', 'JURIDICA');
INSERT INTO `cliente` VALUES ('2', null, '98176897272', null, 'heitor@gmail.com', 'Heitor Araujo', 'FISICA');

-- ----------------------------
-- Records of endereco
-- ----------------------------
INSERT INTO `endereco` VALUES ('1', '02038030', 'Sao Paulo', 'ap 33', 'rua francisca julia', '46', 'SP', '1');
INSERT INTO `endereco` VALUES ('2', '02135023', 'Sao Paulo', 'ap 02', 'rua nova', '33', 'SP', '2');


-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('1', '2', '1500.00', '3', '2');
INSERT INTO `item` VALUES ('2', '1', '1800.00', '1', '2');


-- ----------------------------
-- Records of produto
-- ----------------------------
INSERT INTO `produto` VALUES ('1', 'XBOX 360', '20', '1800.00', '10');
INSERT INTO `produto` VALUES ('2', 'MAC HP 1258', '5', '4000.00', '9');
INSERT INTO `produto` VALUES ('3', 'MAQUINA DE LAVA', '6', '1500.00', '12');
INSERT INTO `produto` VALUES ('4', 'BATEDEIRA', '50', '400.00', '12');
INSERT INTO `produto` VALUES ('5', 'LIQUIDIFICADO', '20', '100.00', '12');

-- ----------------------------
-- Records of usuario
-- ----------------------------
INSERT INTO `usuario` VALUES ('1', 'juliano@gmail.com', 'Juliano Dias', '2500.00', '123', '1');
INSERT INTO `usuario` VALUES ('2', 'marcelo@gmail.com', 'Marcelo Paes', '3500.00', '123', '1');

-- ----------------------------
-- Records of venda
-- ----------------------------
INSERT INTO `venda` VALUES ('2', null, '2017-01-20 13:07:09', '02038032', 'Sao Paulo', 'AP 33', 'Rua Jovita', '25', 'SP', 'DINHEIRO', 'CARTAO_DEBITO', '250.00', '20.00', '4570.00', '2', '2');
