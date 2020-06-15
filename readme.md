# Senior Hotel

Cadastro e consultas de novos e antigos clientes hospedados
Suporta 
* Cadastro de novas reservas;
* Busca por todas as reservas abertas(OPEN);
* Busca por todas as reservas já finalizadas(CLOSE);
* Busca por nome, documento e telefone do hóspede;
* Apresentação de valor da reserva até a data da consulta.

## Build

```bash
mvn clean install -Dmaven.test.skip=true
```

Na primeira execução, deixar ativada a criação  de ddl auto no arquivo de propriedades, nas demais execuções, comentar propriedade no arquivo:

senior-hotel/src/main/resources/application.properties

```bash
spring.jpa.hibernate.ddl-auto=create
```

Mude também usuário do banco de dados postgres no mesmo arquivo citado acima:
```bash
spring.datasource.username=postgres
spring.datasource.password=123456
```



Por padrão, a aplicação inicia na porta 8888, para mudar, configure arquivo senior-hotel/src/main/resources/application.yml

```bash
server:
  port: 8888
```



## Request Postman

Todos os request para criação e busca estão no arquivo na raiz do 


```bash
projetosenior-hotel-requests.postman_collection.json ``



## License
[MIT](https://choosealicense.com/licenses/mit/)
