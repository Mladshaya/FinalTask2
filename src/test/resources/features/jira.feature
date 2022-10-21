@ui
Feature: Авторизация в Jira, проверка списка задач, проверка версии и статуса задачи, создание задачи

  Background:
    Given Открыть главную страницу jira
    Then Ввести логин и пароль

  Scenario: Открываем проект Test
    Then Открыть проект Test

  Scenario: Проверяем количество созданных задач в проекте Test
    Then Открыть проект Test
    Then Проверить количество задач в проекте

  Scenario: Находим и проверяем версию и статус задачи TestSelenium_bug
    Then Открыть проект Test
    Then Проверить количество задач в проекте
    Then Найти и открыть задачу TestSelenium_bug
    Then Проверить затронутую версию
    Then Проверить статус задачи

  Scenario: Создаем баг и проводим по статусам
    Then Открыть проект Test
    Then Проверить количество задач в проекте
    Then Найти и открыть задачу TestSelenium_bug
    Then Проверить затронутую версию
    Then Проверить статус задачи
    Then Создать баг
    Then Провести баг по статусам
