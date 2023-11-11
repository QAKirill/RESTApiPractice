<h1 >Проект по автоматизации API тестирования сервиса <a href="https://reqres.in/ ">Reqres.in</a></h1>

<p align="center">  
<img src="attach/Logo/ReqresIn.png" alt="MainLogo" width="950"/></a>  
</p>

# 🧾 Содержание:

- [Технологии и инструменты](#технологии-и-инструменты)
- [Запуск тестов (Сборка в Jenkins и параметры)](#запуск-тестов-сборка-в-jenkins)
- [Запуск тестов (Из терминала)](#запуск-тестов-из-терминала)
- [Интеграция с Allure Report](#allure-report)
- [Интеграция с Allure TestOps](#интеграция-с-allure-testops)
- [Интеграция с Jira](#интеграция-с-jira)
- [Уведомление в Telegram о результатах выполнения автоматизированных тестов](#уведомление-в-telegram-о-результатах-выполнения-автоматизированных-тестов)

<a id="технологии-и-инструменты"></a>

## 🔨 Технологии и инструменты:

<p align="center">
<img width="6%" title="IntelliJ IDEA" src="attach/Logo/Intelij_IDEA.svg">
<img width="6%" title="Java" src="attach/Logo/Java.svg">
<img width="6%" title="Selenide" src="attach/Logo/Selenide.svg">
<img width="6%" title="Selenoid" src="attach/Logo/Selenoid.svg">
<img width="6%" title="Allure Report" src="attach/Logo/Allure_Report.svg">
<img width="5%" title="Allure TestOps" src="attach/Logo/AllureTestOps.svg">
<img width="6%" title="Gradle" src="attach/Logo/Gradle.svg">
<img width="6%" title="JUnit5" src="attach/Logo/JUnit5.svg">
<img width="6%" title="GitHub" src="attach/Logo/GitHub.svg">
<img width="6%" title="Jenkins" src="attach/Logo/Jenkins.svg">
<img width="6%" title="Telegram" src="attach/Logo/Telegram.svg">
</p>

- В данном проекте автотесты написаны на языке <code>Java</code> с использованием фреймворка для тестирования Selenide. 
- В качестве сборщика был использован - <code>Gradle</code>.  
- Использованы фреймворки <code>JUnit 5</code> и [Selenide](https://selenide.org/).
- При прогоне тестов браузер запускается в [Selenoid](https://aerokube.com/selenoid/).
- Для удаленного запуска реализована джоба в <code>Jenkins</code> с формированием Allure-отчета и отправкой результатов в <code>Telegram</code> при помощи бота. 
- Осуществлена интеграция с <code>Allure TestOps</code>

Содержание Allure-отчета:
* Шаги теста;
* Логи HTTP запросов
* Скриншот страницы на последнем шаге (только для Ui тестов);
* Page Source (только для Ui тестов);
* Логи браузерной консоли (только для Ui тестов);
* Видео выполнения автотеста (только для Ui тестов).

## :arrow_forward: Запуск автотестов

### Запуск тестов из терминала
Demoqa.com тест
```
gradle clean demoqa_test
```

Reqres.in тест
```
gradle clean reqres_test
```
  
Также можно переопределить параметры запуска:
```
clean
${TESTS}_test
-Dbrowser=${BROWSER}
-DbrowserVersion=${BROWSER_VERSION}
-DbrowserSize=${BROWSER_SIZE}
-DremoteDriverUrl=${REMOTE_DRIVER_URL}/wd/hub
```

## <img name="jenkins" src="attach/Logo/Jenkins.svg" title="Jenkins" width="4%"/> <a href="https://jenkins.autotests.cloud/job/21-Yejik-java-TinkoffWebTests" target="blank">Сборка в Jenkins</a>

### Параметры сборки

* <code>BROWSER</code> – браузер, в котором будут выполняться тесты. По-умолчанию - <code>chrome</code>.
* <code>BROWSER_VERSION</code> – версия браузера, в которой будут выполняться тесты. По-умолчанию - <code>100.0</code>.
* <code>BROWSER_SIZE</code> – размер окна браузера, в котором будут выполняться тесты. По-умолчанию - <code>1920x1080</code>.
* <code>TESTS</code> – Набор тестов для Demoqa.com или Reqres.in.

## <img name="allure" src="attach/Logo/Allure_Report.svg" title="Allure Report" width="4%"/> <a href="https://jenkins.autotests.cloud/job/21-Yejik-java-TinkoffWebTests/3/allure" target="_blank">Пример Allure-отчета</a>

### Overview

<p align="center">
<img title="Allure Overview" src="attach/Reports/allureReport.png">
</p>

### Результат выполнения теста

<p align="center">
<img title="Test Results in Alure" src="attach/Reports/ResultTest.png">
</p>

## <img src="attach/Logo/AllureTestOps.svg" title="Allure TestOps" width="4%"/> <a href="https://allure.autotests.cloud/launch/30934" target="_blank">Интеграция с Allure TestOps</a>

Выполнена интеграция сборки <code>Jenkins</code> с <code>Allure TestOps</code>.
Результат выполнения автотестов отображается в <code>Allure TestOps</code>
На Dashboard в <code>Allure TestOps</code> отображена статистика пройденных тестов.

<p align="center">
<img title="Allure TestOps DashBoard" src="attach/Reports/allureAutotestCloud.png">
</p>

## <img name="telega" width="4%" style="vertical-align:middle" title="Telegram" src="attach/Logo/Telegram.svg"> Уведомления в Telegram с использованием бота

После завершения сборки, бот созданный в <code>Telegram</code>, автоматически обрабатывает и отправляет сообщение с результатом.

<p align="center">
<img width="70%" title="Telegram Notifications" src="attach/Reports/notification.jpg">
</p>
