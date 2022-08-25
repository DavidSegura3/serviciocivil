# serviciocivil
Proyecto Demo

El proyecto consta de 5 endpints

1. Crear persona:

curl --location --request POST 'http://localhost:8081/persona/tipo-documento-id/11' \
--header 'Content-Type: application/json' \
--data-raw '{
"nombre": "Lionel",
"apellido": "Messi",
"numeroDocumento": "48125",
"fechaNacimiento": "2003-02-09T00:00:00-05:00"
}'


2. Actualizar Persona

curl --location --request PUT 'http://localhost:8081/persona-mod/5' \
--header 'Content-Type: application/json' \
--data-raw '{
"nombre": "Lionel",
"apellido": "Messi",
"numeroDocumento": "1020304090",
"fechaNacimiento": "1986-02-09T00:00:00-05:00"
}'


3. Consultar Persona Por ID

curl --location --request GET 'http://localhost:8081/persona/persona-id/5'


4. Consultar Todas Las Personas Creadas


curl --location --request GET 'http://localhost:8081/personas'


5. Eliminar Persona por ID.

curl --location --request DELETE 'http://localhost:8081/persona/58'


El proyecto esta un microservicio en spring boot con maven y base de datos
en memoria H2 

