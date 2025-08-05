### Compilar e Rodar a Aplicação
```sh
./gradlew clean build
./gradlew bootRun
```

### Rodar testes de integração
```sh
./gradlew test
```

### Exemplos de request
```sh
GET
curl --location 'http://localhost:8080/api/products?productName=RTIX'

curl --location 'http://localhost:8080/api/products?maxPrice=100&page=1&size=5'

POST
curl --location 'http://localhost:8080/api/products' \
--header 'Content-Type: application/json' \
--data '{
    "product": "SGMS",
    "quantity": 34,
    "price": "$3.28",
    "type": "M",
    "industry": "EDP Services",
    "origin": "AZ"
}'
```
