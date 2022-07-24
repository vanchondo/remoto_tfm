# remoto_tfm

Run locally:
gradle bootRun -Pspring.profiles.active=local

Run with dev profile:
gradle bootRun -Pspring.profiles.active=dev -Pjasypt.encryptor.password=<secretKey>