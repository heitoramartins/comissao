# Comissão


Esse projeto tem como objetivo ser uma API de compras 

- MySqL o scripti database encontra-se comissao\src\test\resources

###### Sistemas:
- Comissao - Sistema de lista de compras e envio de emails

##### tecnologias
- MySql 
- SpringBoot 1.4
- SprinData
- Velocity
- JPA
- Jackson

##### Premissas
- Vamos usar o idCliente, IdUsuario, valorFrete, formaPagamento que podem ser DINHEIRO, CARTAO_CREDITO, CARTAO_DEBITO, CHEQUE, BOLETO_BANCARIO também teremos endereco de
  entrega com logradouro, numero, complemento, cidade, uf, cep também teremos uma lista 
  chamada itens dentro desa lista terão idProduto, quantidade
---

##### Recursos
 - /pedido
 - /pedido/{id}
 
#### Criar Pedido

- Ao criar um pedido tremos algumas premissas 
  o primeiro pedido será sempre salvo como um orçamento
  tambem sera enviado um email para o cliente com a descrição
  do orçamento id, valor do produto , quantidade e valor total
  o cliente precisara ter ter
  um email cadastrado no campo email da tabela cliente

- Ao criar um pedido também sera dado 5% e descnto na compra
  que sera descontados do valor total mais o valor do frete
  tambem será incluido a data do orçamento
  automaticamente

```shell
HTTP POST localhost:8081/pedido
```
```json
{  
   "cliente":{  
      "id":2
   },
   "usuario":{  
      "id":1
   },
   "valorFrete":20,
   "formaPagamento":"DINHEIRO",
   "itens":[  
      {  
         "produto":{  
            "id":3
         },
         "quantidade":1
      },
      {  
         "produto":{  
            "id":1
         },
         "quantidade":1
      }
   ],
   "enderecoEntrega":{  
      "logradouro":"Rua Jovita",
      "numero":"25",
      "complemento":"AP 33",
      "cidade":"Sao Paulo",
      "uf":"SP",
      "cep":"02038030"
   }
}
```
No response temos o header Location com o caminho do recurso criado (Ex: http://localhost:8080/comissao/pedido/42).

######## Atualizações orcamento

- Atualizações de um orcamento o status devera estar setado como ORCAMENTO,
  sera necessario o Id do pedido feito isso é so trocar os campos desejados

```shell
HTTP PUT localhost:8081/pedido/42
```
```json
 {  
   "id":42,

   "cliente":{  
      "id":2
   },
   "usuario":{  
      "id":2
   },
   "valorFrete":30,
   "status":"ORCAMENTO",
   "formaPagamento":"BOLETO_BANCARIO",
   "itens":[  
      {  
         "id":72,
         "produto":{  
            "id":3,
            "vlrUnitario":1500
         },
         "quantidade":1
      },
      {  
         "id":73,
         "produto":{  
            "id":1,
            "vlrUnitario":1800
         },
         "quantidade":1
      }
   ],
   "enderecoEntrega":{  
      "logradouro":"Rua Jovita",
      "numero":"25",
      "complemento":"AP 33",
      "cidade":"Sao Paulo",
      "uf":"SP",
      "cep":"02038035"
   }
}
```
No response temos o header Location com o caminho do recurso criado (Ex: http://localhost:8080/comissao/pedido/42).

######## Atualizações cancelamento

- Atualizações de um orcamento para cancelamento devera conter o id do pedido e o status como
  CANCELAMENTO

```shell
HTTP PUT localhost:8081/pedido/42
```
```json
{
    "id": 42,
    "status":"CANCELADO"
 }
```
No response temos o header Location com o caminho do recurso criado (Ex: http://localhost:8080/comissao/pedido/42).  


######## Atualizações emissao

- Atualizações de um orcamento para emissao devera conter o id do pedido e o status como
  EMITIDO  

```shell
HTTP PUT localhost:8081/pedido/42
```
```json
 {
    "id": 42,
    "status":"EMITIDO"
 }
```
No response temos o header Location com o caminho do recurso criado (Ex: http://localhost:8080/comissao/pedido/42).



