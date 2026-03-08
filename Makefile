# En una terminal, para levantar el servidor
start_server:
	mvn spring-boot:run
	# localhost:7000


# En otra terminal, para que se vean los cambios
compile:
	mvn compile

install:
	mvn clean install -U