# Genie


Esse projeto tem como objetivo ser a API de lista de compras(Favoritos) unificada entre Buscapé e Bondfaro.


###### Sistemas:
- 2U - Sistema de Usuário Único
- Genie - Sistema de lista de compras(Favoritos)

##### Premissas
- Vamos usar o idParceiro para saber de qual site se originou o Favorito e/ou a lista de compras, evitando que os outros sistemas precisem ser cadastrado no Genie.
- Na v0.1 vamos usar uma tabela de usuário com vida útil já definida , pois quando o 2U for criado (I hope) as listas de compras serão preenchidas com a referência para o uniqueUserId do 2U.  
- Na v0.1 vamos usar o email como chave para buscar a lista de compras de usuários logados e o device ID para não logados.
- Na v1.0 será mudado o recurso de usuário, no lugar de usar o email para buscar as listas do usuário, será usado o uniqueUserId do 2U.

---

## v0.1
Como ainda não temos o 2U e um uniqueUserId, precisamos desenvolver uma forma de associar os identificadores que temos e que são espalhados em:

###### Mobile
 - device ID

###### Web
- email
- ID vip
- ID do login unico (person ID ou account ID)

##### Recursos

 - /users
 - /users/{email}
 - /users/{email}/lists
 - /users/{email}/lists/{id}
 - /users/{email}/lists/{id}/favorites
 - /users/{email}/lists/{id}/favorites/{id}
 - /devices/{deviceId}
 - /devices/{deviceId}/lists
 - /devices/{deviceId}/lists/{id}
 - /devices/{deviceId}/lists/{id}/favorites
 - /devices/{deviceId}/lists/{id}/favorites/{id}


#### Criar lista de compras

###### Para um usuário com login único/id vip:
```shell
HTTP POST /users/fulano@gmail.com/lists
```
```json
{
  "partnerId": 2,
  "name": "Lista de Casamento",
  "public": true  
}
```
No response temos o header Location com o caminho do recurso criado (Ex: http://localhost:8080/genie/users/fulano@gmail.com/lists/2).

Com isso temos vinculado apenas email dentro do favoritos, caso tenha o email e também os dados como vipId(area vip) ou uniqueLoginId(Login Unico), pode vincular também:
```shell
HTTP POST /users/fulano@gmail.com/lists
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
