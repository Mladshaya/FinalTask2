@api
Feature: Тестирование API на примере создания объекта
  Scenario: Считывание данных из файла, отправка запроса с измененными данными
    When Считать данные из json-файла
    Then Изменить данные в json-объекте
    And Отправить запрос и проверяем корректность данных
