# Order Service

### Set up guide
To use create order api, set the property 'app.process-order-endpoint' in application.properties

Steps to set up:

    1. Install docker, docker compose
    2. Run development kafka, hazelcast: docker-compose up
    3. Install maven
    4. mvn clean install
    5. Run the project:
        mvn spring-boot:run -Dspring-boot.run.profiles=local

### Not Completed Items
- Verify token
- Create order detail error

### Architecture
[Overview flow chart](https://viewer.diagrams.net/?highlight=0000ff&nav=1&title=demo-e-commerce#R1Vpdc5s4FP01fmwHEF9%2BdJzE7XY7ccdt0vZlRwHZaI0RK0Qw%2B%2BtXmA%2BDhAP22Nj7FHQRAp17zrmSnBGYbrYzCkPvK3GRP9IUdzsC9yNNUw1D53%2BySJpHLNvIAyuK3aLTPrDA%2F6IiqBTRGLsoanRkhPgMh82gQ4IAOawRg5SSpNltSfzmW0O4QlJg4UBfjr5gl3l51NasffwTwiuvfLNqjvM7G1h2LmYSedAlSS0EHkZgSglh%2BdVmO0V%2BBl6JS%2F7c44G71YdRFLA%2BD%2FylacHfM%2FBPMo%2FtpfW8TKPA%2BFCM8gb9uJhw8bEsLRFIPMzQIoRO1k54lkfgzmMbn7dUfgmjMMd9ibeIv%2BpO%2Fq7yJYgytK2Fiu%2BcIbJBjKa8S3kXFJilJRkK0iT7FIxLXL0a%2FFUQFmlfVWPvkeEXBTjtQP3%2B8mrgaWTNX8YMq1%2FXIBmzD5oE1I8IUQksnuQwu4w3%2FsRhhHI8smljTqg%2F4Svy5yTCDJOAd3kljJFNrcPEx6vsBiMCxCRmPg7QtKK4ciaYzSbM2liRYAYtKJuXAtmQQJ7MP%2FPADDKUwFRCmw%2FJfYA37q5A0gqtEj1bRs9uQc%2B%2BFHqmhN4TdTOOKgtE3zCHRsSPkjhwMzx2jOrA8AyQ6XY34dQ2XV%2BMcZaE2SOf%2Fysh63fAUgcBSxPAUvUBwQIv%2Flvy6fucfDMIw5N79ZndtXjgDDEemFLkYj5RDH1ZooE7ycowbzk%2BjCLs9ASKQbpCrDt1yG0UcBnOGlxGC1pljCIfMvzWLPttEBZvmBMcsJqXCtkCipCFiMTUQcVT9TItDKQJta%2F6wHKgHBhpoF1Gq2mfnmRdSvIXuFzD2%2FJeS72e97aiJleuo5WAtpj9zKz4o1G0fjVa99vCp3eNtNaYI4r5NLjVdywNcg52r3I6tWfclvaESgxEB%2BytPV0YSB1We3IFdyjiax8eI3kpP5e7djLB6MkE86aYAEQXFs2zLxN00GHnF2aCvC6BsYvZyaaiHmEpB0kjJLkHi8xrksFUOtTclwyWYjcHEneZB8jAM5NtWqpuYdYhugRdbIkuc0rc2GE3vPjXr76eHUuohZQ4KIqu4Lem7LfvZfpW%2FNYScnqy32rCQAP7bWkNNS74OMo%2BMCyFdPJyzgR2zX3Vj4oKBlzStRTy%2FwOxRLMYn6mQV64zFLHkE9bhKjknC01zEmo56bJA%2FuTYrAL7h3etxtMnEfE9gnUSUb8pIpoif8QtZF8imiKjwcBEBBIRJzHzsmMbB%2B6OpG9nqWCI5%2F9XXyqo8rHIw9bxYLDKNmdPkCO5IzdZo2D3Nxsw4LIJsjOxMn6s5Cv5NqVrGR3KPZiCTvGVLLkR9VX7N%2FHA%2BOgyIOwFtMuVAcwe%2F%2Fi8SH%2F8si1XDdbPj8mT2fI72ySvAufUnCSwFg4c1Jzd48egi2muFTNNwmyQotkqgNNKni6r7jA7rqUx69C%2B7FiN2eKZiSjWkzXGm%2FvfyfPu%2B%2F82AA%2F%2FAQ%3D%3D)

- Default database is h2, can be changed to normal database by update application.properties
- Audit events are send to Kafka and will be handle by other service
- Order service hold all data related to order include order items, only rely on product service to check product quantity when create/update new order
- Authentication is out of scope, order service just need a JWT token with valid signature and contain userId in payload

### Key Libraries
- Spring Boot, Sprint Security, Spring Data, Spring Kafka
- Maven
- java-jwt, java-rsa

### Folder structure

- Follow standard maven structure, details:
    - config: contains configurations class
    - controller: contains web controller
    - dto: contains dto class
    - model: contains data model
    - repository: contains JPA repository
    - security: contains security related class
    - service: contains services

### APIs
- Create order:
    
    curl --location --request POST 'http://localhost:6060/api/v1/order' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwidXNlcklkIjoiZXJ3cjM0NTM0ZmdlZnJld3IiLCJpYXQiOjE1MTYyMzkwMjJ9.yy6RNHz1oL24eJlYCeU7Z3fzAIa_vrHLi9zLpj3clLE' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "orderItems": [
            {
                "code": "prod-100",
                "quantity": 2
            },
            {
                "code": "prod-100",
                "quantity": 2
            }
        ]
    }'
    
- Get order list
    
    curl --location --request GET 'http://localhost:6060/api/v1/order?page=0&size=2&sort=desc&sortBy=createdDate' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwidXNlcklkIjoiZXJ3cjM0NTM0ZmdlZnJld3IiLCJpYXQiOjE1MTYyMzkwMjJ9.yy6RNHz1oL24eJlYCeU7Z3fzAIa_vrHLi9zLpj3clLE' \
    --data-raw ''