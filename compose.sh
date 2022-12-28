docker compose down

docker build -t mock-user-info .mock-user-info/.
docker compose build zookeeper kafka pgsql-dev desafio-south init-kafka mock-user-info
docker compose up -d --force-recreate zookeeper kafka pgsql-dev desafio-south init-kafka mock-user-info

