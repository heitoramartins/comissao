/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : compra

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-01-20 19:05:06
*/

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
-- Table structure for `item`
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quantidade` int(11) DEFAULT NULL,
  `vlr_unitario` decimal(19,2) DEFAULT NULL,
  `fk_produto` bigint(20) DEFAULT NULL,
  `fk_venda` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqwdbn14f6jw35dabfaq2erv0i` (`fk_produto`),
  KEY `FK4cay2g8do2qfx3ou83ipxqase` (`fk_venda`),
  CONSTRAINT `FK4cay2g8do2qfx3ou83ipxqase` FOREIGN KEY (`fk_venda`) REFERENCES `venda` (`id`),
  CONSTRAINT `FKqwdbn14f6jw35dabfaq2erv0i` FOREIGN KEY (`fk_produto`) REFERENCES `produto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;


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
-- Table structure for `venda`
-- ----------------------------
DROP TABLE IF EXISTS `venda`;
CREATE TABLE `venda` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_entrega` datetime DEFAULT NULL,
  `data_venda` datetime DEFAULT NULL,
  `entrega_cep` varchar(9) NOT NULL,
  `entrega_cidade` varchar(60) NOT NULL,
  `entrega_complemento` varchar(150) DEFAULT NULL,
  `entrega_logradouro` varchar(150) DEFAULT NULL,
  `entrega_numero` varchar(20) NOT NULL,
  `entrega_uf` varchar(60) NOT NULL,
  `forma_pagamento` varchar(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `vlr_desconto` decimal(19,2) DEFAULT NULL,
  `vlr_frete` decimal(19,2) DEFAULT NULL,
  `vlr_total` decimal(19,2) DEFAULT NULL,
  `fk_cliente` bigint(20) DEFAULT NULL,
  `fk_funcionario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3t171nh5py588n541wu309clo` (`fk_cliente`),
  KEY `FKs1aub1m1uw1undtdx6to0wxv0` (`fk_funcionario`),
  CONSTRAINT `FK3t171nh5py588n541wu309clo` FOREIGN KEY (`fk_cliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FKs1aub1m1uw1undtdx6to0wxv0` FOREIGN KEY (`fk_funcionario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

