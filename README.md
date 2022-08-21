# remoto_tfm

Run locally:
gradle clean build bootRun -Pspring.profiles.active=local

Run with dev profile:
gradle clean build bootRun -Pspring.profiles.active=dev -Pjasypt.encryptor.password=<secretKey>

Heroku url:
https://remoto-tfm.herokuapp.com

Swagger:
http://localhost:8080/swagger-ui/index.html
https://remoto-tfm.herokuapp.com/swagger-ui/index.html
