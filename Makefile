.PHONY: build infra auth run clean env-up env-down


infra:
	@docker-compose up -d keycloak postgresDB rabbitmq

# auth:
#     @docker-compose up -d rabbitmq postgresqlDB

unit-tests:
	@docker-compose up --abort-on-container-exit --exit-code-from unit-tests unit-tests

integration-tests:
	@docker-compose up --abort-on-container-exit --exit-code-from integration-tests integration-tests

clean:
	@docker-compose down

env-down:
	@docker-compose down -v