package com.library.steps;

import com.library.utility.ConfigurationReader;
import com.library.utility.LibraryAPI_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class APIStepDefs {
    /**
     * US 01 RELATED STEPS
     */
    String token;
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    
    
    @Given("I logged Library api as a {string}")
    public void i_logged_library_api_as_a(String userType) {
        requestSpecification=given().log().uri()
                .header("x-library-token", LibraryAPI_Util.getToken(userType));
    }
    
    @Given("Accept header is {string}")
    public void accept_header_is(String contentType) {
        requestSpecification.accept(contentType);
    }
    
    @When("I send GET request to {string} endpoint")
    public void i_send_get_request_to_endpoint(String endPoint) {
        response =requestSpecification.when().get(ConfigurationReader.getProperty("library.baseUri")+endPoint);
        validatableResponse = response.then();
    }
    
    @Then("status code should be {int}")
    public void status_code_should_be(Integer statusCode) {
        validatableResponse.statusCode(statusCode);
        
    }
    
    @Then("Response Content type is {string}")
    public void response_content_type_is(String contentType) {
        validatableResponse.contentType(contentType);
    }
    
    @Then("{string} field should not be null")
    public void field_should_not_be_null(String field) {
        validatableResponse.body(field, everyItem(is(notNullValue())));
        
    }
}
