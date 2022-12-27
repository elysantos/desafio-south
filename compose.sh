docker compose down

docker build -t mock-user-info .mock-user-info/.
docker run -d -p 8099:8080 mock-user-info
docker compose build zookeeper kafka pgsql-dev desafio-south init-kafka
docker compose up -d --force-recreate zookeeper kafka pgsql-dev desafio-south init-kafka

