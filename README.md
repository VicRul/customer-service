# customer-service
Тестовое задание - web-сервис, с помощью которого можно создавать, изменять и находить клиентов в базе данных
* SpringBoot
* Spring Data JPA
* PostgreSQL
* Lombok
* JUnit
* Gson
## Установка и запуск
DDL:
```SQL
CREATE TABLE address (
id bigserial NOT NULL,
contry varchar(255),
region varchar(255),
city varchar(255),
street varchar(255),
house varchar(255),
flat varchar(255),
created timestamp,
modified timestamp,
CONSTRAINT pk_address PRIMARY KEY (id)
);

CREATE TABLE customer (
id bigserial NOT NULL,
registred_address_id bigint NOT NULL,
actual_address_id bigint NOT NULL,
first_name varchar(255) NULL,
last_name varchar(255) NULL,
middle_name varchar(255) NULL,
sex varchar(6) NOT NULL,
CONSTRAINT ck_customer_sex CHECK (((sex)::text = ANY ((ARRAY['male'::character
varying, 'female'::character varying])::text[]))),
CONSTRAINT pk_customer PRIMARY KEY (id),
CONSTRAINT fk_registred_address_id FOREIGN KEY (registred_address_id)
REFERENCES address(id) ON UPDATE RESTRICT ON DELETE RESTRICT,
CONSTRAINT fk_actual_address_id FOREIGN KEY (actual_address_id) REFERENCES
address(id) ON UPDATE RESTRICT ON DELETE RESTRICT
);
```
Приложение можно скомпилировать в исполняемый WAR файл с помощью команды Maven:
`mvn clean package`.</br>
Команда должна быть запущена из корневой директории приложения. Скомпилированый файл лежит в папке `target/customerservice-1.0.war`.
</br>
Для запуска приложения используйте команду `java -jar customerservice-1.0.war`.
## Примеры запросов
Поддерживаются следующие типы запросов:
* Поиск клиентов по фамилии и имени:
```http
(GET) http://localhost:8080/api/find
```
    Param(firstName) - имя
    Param(lastName) - фамилия
* Создание нового клиента: 
```http
(POST) http://localhost:8080/api/new
```
    Param(isSameAddresses) - фактический адрес и адрес регистрации совпадают (boolean)
    Body - новый клиент
* Изменение фактического адреса клиента: 
```http
(PUT) http://localhost:8080/api/update
```
    Param(customerId) - Id клиента
    Body - новый текущий адрес
