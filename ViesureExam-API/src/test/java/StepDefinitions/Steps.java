package StepDefinitions;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class Steps {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://backend-interview.tools.gcp.viesure.io";
    }

    public Response getWeather() {
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest
                .get("/weather");

        int statusCode = response.getStatusCode();

        // Assert that correct status code is returned.
        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
        return response;
    }

    @When("Set tempInFahrenheit = {int}")
    public void setTempInFahrenheit(Integer tempInFahrenheit) {
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest
                .header("Content-type", "application/json")
                .and()
                .body("{\"tempInFahrenheit\":" + tempInFahrenheit + "}")
                .put("/weather/temp");

        int statusCode = response.getStatusCode();
        // Assert that correct status code is returned.
        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
    }

    @Then("Check tempInFahrenheit = {int}, tempInCelsius, description = {string} and city = {string}")
    public void checkDescription(Integer tempInFahrenheitExpected, String descriptionExpected, String cityExpected) {
        Response response = getWeather();

        // Convert Fahrenheit to Celsius formula
        Integer tempInCelsiusExpected = ((tempInFahrenheitExpected - 32) * 5) / 9;

        JsonPath jsonPathEvaluator = response.jsonPath();
        String descriptionActual = jsonPathEvaluator.get("description");
        String cityActual = jsonPathEvaluator.get("city");
        Integer tempInFahrenheitActual = jsonPathEvaluator.get("weather.tempInFahrenheit");
        Integer tempInCelsiusActual = jsonPathEvaluator.get("weather.tempInCelsius");

        // Validate the response
        Assert.assertEquals(tempInFahrenheitActual, tempInFahrenheitExpected,"Correct temperature in Fahrenheit received in the Response");
        Assert.assertEquals(tempInCelsiusActual, tempInCelsiusExpected, "Correct temperature in Celsius received in the Response");
        Assert.assertEquals(descriptionActual, "The weather is " + descriptionExpected, "Correct description received in the Response");
        Assert.assertEquals(cityActual, cityExpected, "Correct city received in the Response");
    }

    @When("Set conditionId = {int}")
    public void setConditionId(Integer conditionId) {
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest
                .header("Content-type", "application/json")
                .and()
                .body("{\"condition\":" + conditionId + "}")
                .put("/weather/condition");

        int statusCode = response.getStatusCode();
        // Assert that correct status code is returned.
        Assert.assertEquals(statusCode, 200, "Correct status code returned");
    }

    @Then("Check condition = {string}, icon = {string} and city = {string}")
    public void checkCondition(String conditionExpected, String iconExpected, String cityExpected) {
        Response response = getWeather();

        JsonPath jsonPathEvaluator = response.jsonPath();
        String conditionActual = jsonPathEvaluator.get("condition");
        String iconActual = jsonPathEvaluator.get("icon");
        String cityActual = jsonPathEvaluator.get("city");

        // Validate the response
        Assert.assertEquals(conditionActual, conditionExpected, "Correct condition received in the Response");
        Assert.assertEquals(iconActual ,iconExpected +".png", "Correct icon received in the Response");
        Assert.assertEquals(cityActual ,cityExpected, "Correct city received in the Response");
    }
}
