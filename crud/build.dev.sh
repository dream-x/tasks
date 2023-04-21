rm .env -f

# shellcheck disable=SC2129
echo "BACKEND_HOST=0.0.0.0" >> .env
echo "BACKEND_PORT=9000" >> .env
echo "DEBUG_FLAG=True" >> .env
echo "ALLOWED_HOSTS=*" >> .env
echo "SECRET_KEY=django-insecure-+x+)b&*c0!y3+(jqc2g3gx26ff+%w_tu2971" >> .env

echo "POSTGRES_DB=postgres_dev" >> .env
echo "POSTGRES_USER=postgres" >> .env
echo "POSTGRES_PASSWORD=postgres" >> .env
echo "POSTGRES_HOST=postgres" >> .env
echo "POSTGRES_PORT=5432" >> .env

docker compose -f ./docker-compose.dev.yml build