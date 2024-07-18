## Информация о сервере:
1. Техническое задание [technical_task.txt](technical_task.txt)
2. Коллекция запросов Postman [Digital Chief.postman_collection.json](Digital%20Chief.postman_collection.json)
3. Используется самоподписанный HTTPS протокол 
4. Сервер работает на 8443 порту


# Инструкция по установке проекта с GitHub репозитория

## Шаг 1: Скачивание проекта с GitHub
### Открытие консоли и скачивание репозитория 
```sh
git clone https://github.com/Aleksey-Vashchenko/Digital_Chief_Test_Task.git
```

## Шаг 2: Переход в папку с проектом

```bash sh
cd Digital_Chief_Test_Task
```

## Шаг 3: Установить переменные среды для подключения к базе данных

### Windows (command line, not PowerShell)
```bash sh
set SPRING_DATASOURCE_USERNAME "your_username" 
set SPRING_DATASOURCE_PASSWORD "your_password" 
set SPRING_DATASOURCE_URL "jdbc:postgresql://your_host/your_db" 
```
### Windows (PowerShell)
```bash sh
$Env:SPRING_DATASOURCE_USERNAME = "your_username"
$Env:SPRING_DATASOURCE_PASSWORD = "your_password" 
$Env:SPRING_DATASOURCE_URL = "jdbc:postgresql://your_host/your_db" 
```
### Linux
```sh
export  SPRING_DATASOURCE_URL='jdbc:postgresql://your_host/your_db'
export  SPRING_DATASOURCE_PASSWORD='your_password'
export  SPRING_DATASOURCE_USERNAME='your_username'
```

## Шаг 4: Запуск приложения
### Linux/PowerShell
```bash sh
./mvnw spring-boot:run
```
### Windows (command line, not PowerShell)
```bash sh
mvnw spring-boot:run
```



### TODO:
1. Покрытие тестами
2. JWT authentication
3. dockerfile
