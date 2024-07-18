# Инструкция по установке проекта с GitHub репозитория

## Шаг 1: Скачивание проекта с GitHub
### Открытие консоли и скачивание репозитория 
Linux
```sh
git clone https://github.com/Aleksey-Vashchenko/Digital_Chief_Test_Task.git
```

## Шаг 2: Переход в папку с проектом

```bash sh
cd Digital_Chief_Test_Task
```

## Шаг 3: Установить переменные среды для подключения к базе данных

# Windows (command line, not PowerShell)
```bash sh
set SPRING_DATASOURCE_USERNAME "your_username" 
set SPRING_DATASOURCE_PASSWORD "your_password" 
set SPRING_DATASOURCE_URL "jdbc:postgresql://your_host/your_db" 
```
# Windows (PowerShell)
```bash sh
$Env:SPRING_DATASOURCE_USERNAME = "your_username"
$Env:SPRING_DATASOURCE_PASSWORD = "your_password" 
$Env:SPRING_DATASOURCE_URL = "jdbc:postgresql://your_host/your_db" 
```
# Linux
```sh
export  SPRING_DATASOURCE_URL='jdbc:postgresql://your_host/your_db'
export  SPRING_DATASOURCE_PASSWORD='your_password'
export  SPRING_DATASOURCE_USERNAME='your_username'
```

## Шаг 4: Запуск приложения
# Linux/PowerShell
```bash sh
./mvnw spring-boot:run
```
# Windows (command line, not PowerShell)
```bash sh
mvnw spring-boot:run
```
