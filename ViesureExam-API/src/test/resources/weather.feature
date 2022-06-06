Feature: Weather API

  Scenario: Check the weather description changed based on Fahrenheit temperature = 30
    When Set tempInFahrenheit = 30
    Then Check tempInFahrenheit = 30, tempInCelsius, description = 'freezing' and city = 'vienna'
  Scenario: Check the weather description changed based on Fahrenheit temperature = 32
    When Set tempInFahrenheit = 32
    Then Check tempInFahrenheit = 32, tempInCelsius, description = 'freezing' and city = 'vienna'
  Scenario: Check the weather description changed based on Fahrenheit temperature = 34
    When Set tempInFahrenheit = 34
    Then Check tempInFahrenheit = 34, tempInCelsius, description = 'cold' and city = 'vienna'
  Scenario: Check the weather description changed based on Fahrenheit temperature = 48
    When Set tempInFahrenheit = 48
    Then Check tempInFahrenheit = 48, tempInCelsius, description = 'cold' and city = 'vienna'
  Scenario: Check the weather description changed based on Fahrenheit temperature = 50
    When Set tempInFahrenheit = 50
    Then Check tempInFahrenheit = 50, tempInCelsius, description = 'mild' and city = 'vienna'
  Scenario: Check the weather description changed based on Fahrenheit temperature = 66
    When Set tempInFahrenheit = 66
    Then Check tempInFahrenheit = 66, tempInCelsius, description = 'mild' and city = 'vienna'
  Scenario: Check the weather description changed based on Fahrenheit temperature = 68
    When Set tempInFahrenheit = 68
    Then Check tempInFahrenheit = 68, tempInCelsius, description = 'warm' and city = 'vienna'
  Scenario: Check the weather description changed based on Fahrenheit temperature = 75
    When Set tempInFahrenheit = 75
    Then Check tempInFahrenheit = 75, tempInCelsius, description = 'warm' and city = 'vienna'
  Scenario: Check the weather description changed based on Fahrenheit temperature = 77
    When Set tempInFahrenheit = 77
    Then Check tempInFahrenheit = 77, tempInCelsius, description = 'hot' and city = 'vienna'
  Scenario: Check the weather description changed based on Fahrenheit temperature = 79
    When Set tempInFahrenheit = 79
    Then Check tempInFahrenheit = 79, tempInCelsius, description = 'hot' and city = 'vienna'

  Scenario: Check the weather condition and icon changed based on condition ID = 1
    When Set conditionId = 1
    Then Check condition = 'clear', icon = 'clear' and city = 'vienna'
  Scenario: Check the weather condition and icon changed based on condition ID = 2
    When Set conditionId = 2
    Then Check condition = 'windy', icon = 'windy' and city = 'vienna'
  Scenario: Check the weather condition and icon changed based on condition ID = 3
    When Set conditionId = 3
    Then Check condition = 'mist', icon = 'mist' and city = 'vienna'
  Scenario: Check the weather condition and icon changed based on condition ID = 4
    When Set conditionId = 4
    Then Check condition = 'drizzle', icon = 'drizzle' and city = 'vienna'
  Scenario: Check the weather condition and icon changed based on condition ID = 5
    When Set conditionId = 5
    Then Check condition = 'dust', icon = 'dust' and city = 'vienna'