# Comissao


Esse projeto tem como objetivo ser a API WebServices de pedidos (vendas)  

##### Premissas
- Ao realizar uma compra o programa ira colher alguams informaçoes dos modulos ja existentes e executara calculos de descontos 
- e enviara um email para o solicitante com os dados do seu pedido
---

##### Recursos

/pedido
/pedido/{id}
/pedido/desconto/aprovado/{id}
/pedido/desconto/reprovado/{id}

#### Criar Pedido

- Para um pedido com usuario e clientes ja cadastrados, na primeira venda o sistema realiza um calculo inicial de  5% de desconto
  mais o valor do frete, logo apos o pedido ser persistido um email sera enviado para o solicitante com os dados do pedido
  obs(tabela cliente campo email "heitoramartins@gmail.com")
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
para cnsultar o pedido registrado
```shell
HTTP GET localhost:8081/pedido/1
```
```json
{
  "partnerId": 2,
  "name": "Lista de Casamento",
  "public": true,
  "user":{
    "vipId":5656565,
    "uniqueLoginId":7784874
  }
}
```
No response temos o header Location com o caminho do recurso criado (Ex: http://localhost:8080/genie/users/fulano@gmail.com/lists/2).

###### Para um usuário de device que não tem login único/id vip:
```shell
HTTP POST /devices/3D3F43RDVV00230/lists
```
```json
{
  "partnerId": 2,
  "name": "Lista de Casamento",
  "plataform": "ios",
  "public": true  
}
```
No response temos o header Location com o caminho do recurso criado (Ex: http://localhost:8080/genie/devices/3D3F43RDVV00230/lists/2).    

#### Adicionar item na lista de compras

###### Para um usuário com login único/id vip:
```shell
HTTP POST /users/fulano@gmail.com/lists/2/favorites
```
```json
{
  "partnerId": 2,
  "productId": 665545,
  "alreadyBought":false,
  "amount":true
}
```
No response temos o header Location com o caminho do recurso criado (Ex: http://localhost:8080/genie/users/fulano@gmail.com/lists/2/favorites/55).

###### Para um usuário de device que não tem login único/id vip:
```shell
HTTP POST /devices/3D3F43RDVV00230/lists/4/favorites
```
```json
{
  "partnerId": 2,
  "plataform": "ios",
  "productId": 665545,
  "alreadyBought":false,
  "amount":true
}
```
No response temos o header Location com o caminho do recurso criado (Ex: http://localhost:8080/genie/devices/3D3F43RDVV00230/lists/2/favorites/55).


#### Buscar listas de compras

###### Para um usuário com login único/id vip:
```shell
HTTP GET /users/fulano@gmail.com/lists
```
```json
[
  {
    "id":1,
    "name":"Meus gadgets",
    "public": false,
    "partnerId":4,
    "favorites": [
      {"productId": 11111, "partnerId":4, "alreadyBought":false, "amount":1, "source":"mobile"},
      {"productId": 22222, "partnerId":4, "alreadyBought":false, "amount":1, "source":"web"}]
  },
  {
    "id":2,
    "name":"Lista de casamento",
    "public": true,
    "partnerId":4,
    "favorites": [
      {"productId": 46465, "partnerId":4, "alreadyBought":false, "amount":1, "source":"mobile"},
      {"productId": 78788, "partnerId":4, "alreadyBought":false, "amount":1, "source":"web"}]
  }
]
```

###### Para um usuário de device que não tem login único/id vip:
```shell
HTTP GET /devices/3D3F43RDVV00230/lists
```
```json
[
  {
    "id":1,
    "name":"Meus gadgets",
    "public": false,
    "partnerId":4,
    "plataform":"ios",
    "favorites": [
      {"productId": 11111, "partnerId":4, "alreadyBought":false, "amount":1, "source":"mobile", "plataform":"ios"},
      {"productId": 22222, "partnerId":4, "alreadyBought":false, "amount":1, "source":"mobile", "plataform":"ios"}]
  },
  {
    "id":2,
    "name":"Lista de casamento",
    "public": true,
    "partnerId":4,
    "plataform":"ios",
    "favorites": [
      {"productId": 46465, "partnerId":4, "alreadyBought":false, "amount":1, "source":"mobile", "plataform":"ios"},
      {"productId": 78788, "partnerId":4, "alreadyBought":false, "amount":1, "source":"mobile", "plataform":"ios"}]
  }
]
```

#### Atualizar lista de compras

###### Para um usuário com login único/id vip:
```shell
HTTP PUT /users/fulano@gmail.com/lists/2
```
```json
{
  "partnerId": 2,
  "name": "Lista de Casamento atualizada",
  "public": false  
}
```
No response temos o header Location com o caminho do recurso criado (Ex: http://localhost:8080/genie/users/fulano@gmail.com/lists/2).

###### Para um usuário de device que não tem login único/id vip:
```shell
HTTP PUT /devices/3D3F43RDVV00230/lists/2
```
```json
{
  "partnerId": 2,
  "name": "Lista de Casamento atualizada",
  "plataform": "ios",
  "public": true  
}
```
No response temos o header Location com o caminho do recurso criado (Ex: http://localhost:8080/genie/devices/3D3F43RDVV00230/lists/2).    

#### Remover item na lista de compras

###### Para um usuário com login único/id vip:
```shell
HTTP DELETE /users/fulano@gmail.com/lists/2/favorites/1
```
###### Para um usuário de device que não tem login único/id vip:
```shell
HTTP DELETE /devices/3D3F43RDVV00230/lists/4/favorites/1
```

#### Remover lista de compras

###### Para um usuário com login único/id vip:
```shell
HTTP DELETE /users/fulano@gmail.com/lists/2
```
###### Para um usuário de device que não tem login único/id vip:
```shell
HTTP DELETE /devices/3D3F43RDVV00230/lists/4
```

#### Atualizar dados do usuário

Caso precise atualizar email do usuário ou seus ids externos como vipId (area vip) e uniqueLoginId(Login Unico).

###### Para um usuário com login único/id vip:
```shell
// Usar o email antigo pois ele é a chave
HTTP PUT /users/fulano@gmail.com
```
```json
{
  "email": "fulaninho@gmail.com",
  "vipId": 656577,
  "uniqueLoginId": 5656566  
}
```

#### Buscar usuários

Para buscar um ou mais usuários por seus vipIds(area vip) ou uniqueLoginId(Login Unico). OBS: Limitado a no máximo 20 ids no total:
```shell
HTTP GET /users?vipIds=265656,566777
HTTP GET /users?uniqueLoginIds=65688,448474
```
```json
{
  "users":[
    {
      "email":"fulano@gmail.com",
      "vipId":265656,
      "uniqueLoginId":12355
    },
    {

      "email":"sicrano@gmail.com",
      "uniqueLoginId":65688
    }
  ]
}
```


#### Buscar devices

```shell
HTTP GET /devices?ids=455EDFTCVF434-EFR4,DDFD356V-CDF4
```
```json
{
  "devices":[
    {
      "plataform":"android",
      "deviceId":"455EDFTCVF434-EFR4"
    },
    {
      "plataform":"ios",
      "deviceId":"DDFD356V-CDF4"
    }
  ]
}
```

---

## v1.0

A partir do momento que exista o 2U no Buscapé, o Genie só precisará se vincular ao uniqueUserId e não se preocupar com Device, Email e outros dados do usuário. Fazendo com que a API seja muito mais simples e não extrapole o seu contexto que é lista de compras.

##### Recursos

 - /users
 - /users/{uniqueUserId}
 - /users/{uniqueUserId}/lists
 - /users/{uniqueUserId}/lists/{id}
 - /users/{uniqueUserId}/lists/{id}/favorites
 - /users/{uniqueUserId}/lists/{id}/favorites/{id}
