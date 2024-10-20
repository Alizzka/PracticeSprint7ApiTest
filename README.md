## Спринт 7 "Тестирование Api"

Практические примеры теории спринта. Описание в самих тестовых классах.

##### Для создания отчета в Allure:

1. mvn clean test (запускаем тесты в Git Bash)

2. mvn allure:report (создаем отчет в папке target/allure-results в папке проекта)
- чтобы открыть отчет в браузере, открываем файл index.html, который находится в tagret/site/allure-maven-plugin

2.1 mvn allure:serve (сразу откроет отчет в браузере но не создаст папку site и файл index.html)

2.2 llure serve target/surefire-reports/ (также сразу откроет отчет в браузере но не создаст папку site и файл index.html)