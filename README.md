<h1 >Проект по автоматизации API тестирования сервиса <a href="https://reqres.in/ ">Reqres.in</a></h1>

<p align="center">  
<img src="images/logo/ReqresIn.png" alt="MainLogo" width="950"/></a>  
</p>

# 🧾 Содержание:

- [Технологии и инструменты](#технологии-и-инструменты)
- [Реализованные проверки](#Реализованные-проверки)
- [Запуск тестов (Сборка в Jenkins и параметры)](#запуск-тестов-сборка-в-jenkins)
- [Запуск тестов (Из терминала)](#запуск-тестов-из-терминала)
- [Интеграция с Allure Report](#allure-report)
- [Интеграция с Allure TestOps](#интеграция-с-allure-testops)
- [Интеграция с Jira](#интеграция-с-jira)
- [Уведомление в Telegram о результатах выполнения автоматизированных тестов](#уведомление-в-telegram-о-результатах-выполнения-автоматизированных-тестов)

<a id="технологии-и-инструменты"></a>

## 🔨 Технологии и инструменты:

| Java                                                                                                     | IntelliJ  <br>  Idea                                                                                               | GitHub                                                                                                     | JUnit 5                                                                                                           | Gradle                                                                                                     | RestAssured                                                                                                               | Allure <br> Report                                                                                                         | Jenkins                                                                                                          | Jira                                                                                                                                    | Telegram                                                                                                            |                                                                                            Allure <br> TestOps |
|:---------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------:|
| <a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"  alt="Java"/></a> | <a href="https://www.jetbrains.com/idea/"><img src="images/logo/Idea.svg" width="50" height="50"  alt="IDEA"/></a> | <a href="https://github.com/"><img src="images/logo/GitHub.svg" width="50" height="50"  alt="Github"/></a> | <a href="https://junit.org/junit5/"><img src="images/logo/Junit5.svg" width="50" height="50"  alt="JUnit 5"/></a> | <a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a> | <a href="https://rest-assured.io/"><img src="images/logo/RestAssured.svg" width="50" height="50"  alt="RestAssured"/></a> | <a href="https://github.com/allure-framework"><img src="images/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a> | <a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a> | <a href="https://www.atlassian.com/software/jira/"><img src="images/logo/Jira.svg" width="50" height="50" alt="Java" title="Java"/></a> | <a href="https://web.telegram.org/"><img src="images\logo\Telegram.svg" width="50" height="50" alt="Telegram"/></a> | <a href="https://qameta.io/"><img src="images\logo\Allure_TO.svg" width="50" height="50" alt="Allure_TO"/></a> |

В данном проекте автотесты написаны на **Java**. Для сборки проекта в
среде IntelliJ IDEA используется **Gradle**. **JUnit 5** задействован в качестве фреймворка модульного тестирования, а
**RestAssured** используется для тестирования API.
Запуск тестов выполняется из **Jenkins**, и вся работа по управлению проектом и отслеживанию задач выполняется с
использованием **Jira**. **Allure Report**, **AllureTestOps** и **Telegram Bot** используются для визуализации
результатов тестирования.

<a id="Реализованные-проверки"></a>

## 🏁 Реализованные проверки:

### Авторизация:

- Успешная с Email и Password
- Неуспешная без Email
- Неуспешная без Password
- Неуспешная с данными неизвестного пользователя

### Регистрация:

- Успешная с Email и Password
- Неуспешная без Email
- Неуспешная без Password
- Неуспешная с данными неизвестного пользователя

### Создание сотрудника:

- Успешное с Name и Job
- Успешное без Name
- Успешное без Job

### Обновление сотрудника:

- Успешное через метод PUT
- Успешное через метод PATCH

### Удаление сотрудника

### Список ресурсов

### Список пользователей

<a id="запуск-тестов-сборка-в-jenkins"></a>

## <img alt="Jenkins" height="25" src="images/logo/Jenkins.svg" width="25"/> Сборка в [Jenkins](https://jenkins.autotests.cloud/job/reqresin-product-autotests/)

<p align="center">  
<img src="images/screen/JenkinsMain.png" alt="Jenkins" width="950"/></a>  
</p>

## 📋 Параметры сборки в Jenkins:

- _TASK (Задачи, по умолчанию Smoke_test)_

<a id="запуск-тестов-из-терминала"></a>

## 🚀 Команда для запуска автотестов из терминала

```bash  
gradle clean test 
```

<a id="allure-report"></a>

## <img alt="Allure" height="25" src="images/logo/Allure.svg" width="25"/> </a>Интеграция с <a target="_blank" href="https://jenkins.autotests.cloud/job/reqresin-product-autotests/allure/">Allure Report</a>

## 🖨️ Основная страница отчёта

<p align="center">  
<img title="Allure Overview Dashboard" src="images/screen/AlMain.png" width="850">  
</p>  

## 📄 Тест-кейсы

<p align="center">  
<img title="Allure Tests" src="images/screen/AlCases.png" width="850">   
</p>

## 📊 Графики

<p align="center">   
<img title="Allure Graphics1" src="images/screen/AlGraph1.png" width="850">  
<img title="Allure Graphics2" src="images/screen/AlGraph2.png" width="850">  
</p>

<a id="интеграция-с-allure-testops"></a>

## <img alt="Allure_TO" height="25" src="images/logo/Allure_TO.svg" width="25"/> </a>Интеграция с <a target="_blank" href="https://allure.autotests.cloud/project/3737/dashboards">Allure TestOps</a>

## 🖨️ Основная страница отчёта

<p align="center">  
<img title="Allure TestOps Dashboard" src="images/screen/OpsDashboard.png" width="850">  
</p>  

## 📄 Автоматизированные тест-кейсы

<p align="center">  
<img title="Allure Tests" src="images/screen/OpsTestCases.png" width="850">  
</p>

<a id="интеграция-с-jira"></a>

## <img alt="Allure" height="25" src="images/logo/Jira.svg" width="25"/></a> Интеграция с <a target="_blank" href="https://jira.autotests.cloud/browse/HOMEWORK-922">Jira</a>

<p align="center">  
<img title="Jira" src="images/screen/JiraMain.png" width="">  
</p>

____

<a id="уведомление-в-telegram-о-результатах-выполнения-автоматизированных-тестов"></a>

## <img alt="Allure" height="25" src="images/logo/Telegram.svg" width="25"/></a> Уведомление в Telegram при помощи бота

____
<p align="center">  
<img title="TG Overview" src="images/screen/TGMain.png" width="550">  
</p>

____
