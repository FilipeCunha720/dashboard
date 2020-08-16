### Running the environment

To build the environment, run `./gradlew bootJar` in the terminal. This will build a `.jar` file that will be used when running the environment.

To run the environment, run `docker-compose up --force-recreate` in the terminal. This will bring up the terminal, and the api service.

Once the environment is running, in another shell you should be able to run `./smoke_tests.sh` which will test that the service returns at least one dashboard, and it has the correct properties.

The api is available on `http://localhost/` and the mysql server is available on mysql:3306.

Running `docker-compose down` will stop the environment and destroy the containers for you, the next time you bring the environment up the containers will be created again.