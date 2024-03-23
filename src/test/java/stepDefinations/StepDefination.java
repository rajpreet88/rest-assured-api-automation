package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification reqChain;
	ResponseSpecification res;
	Response resChain;
	TestDataBuild testData = new TestDataBuild();
	static String place_id;

	@Given("Add Place API Payload {double} {double} {int} {string} {string} {string} {string} {string}")
	public void add_place_api_payload(Double latitude, Double longitude, Integer accuracy, String name,
			String phone_number, String address, String website, String language) throws IOException {

		reqChain = given().spec(requestSpecification()).body(testData.addPlacePayload(latitude, longitude, accuracy,
				name, phone_number, address, website, language));
	}

	@When("user calls {string} with HTTP {string} request")
	public void user_calls_with_http_post_request(String apiName, String httpMethod) {

		APIResources resourceAPI = APIResources.valueOf(apiName);
		System.out.println(resourceAPI.getResource());

		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
//		System.out.println();
		if (httpMethod.equalsIgnoreCase("POST")) {
			resChain = reqChain.when().post(resourceAPI.getResource());
		} else if (httpMethod.equalsIgnoreCase("GET")) {
			resChain = reqChain.when().get(resourceAPI.getResource());
		} else if (httpMethod.equalsIgnoreCase("DELETE")) {
			resChain = reqChain.when().delete(resourceAPI.getResource());
		} else if (httpMethod.equalsIgnoreCase("PUT")) {
			resChain = reqChain.when().put(resourceAPI.getResource());
		}
		System.out.println(resChain.asString());
	}

	@Then("the API call is success with status code {int}")
	public Response the_api_call_is_success_with_status_code(Integer statusCode) {
		// Write code here that turns the phrase above into concrete actions
		Response status = resChain.then().spec(res).extract().response();
		Assert.assertEquals(resChain.getStatusCode(), statusCode);
		return status;
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String resKey, String resValue) {
		// Write code here that turns the phrase above into concrete actions
		String value = getJSONPath(resChain, resKey);
		Assert.assertEquals(value, resValue);
	}

	@Then("verify place_id created maps to {string} using {string} with HTTTP {string} request")
	public void verify_created_maps_to_using(String placeName, String apiName, String methodName) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		place_id = getJSONPath(resChain, "place_id");
		reqChain = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_post_request(apiName, methodName);

		Response status = resChain.then().spec(res).extract().response();
		Assert.assertEquals(getJSONPath(resChain, "name"), placeName);
	}

	@Given("Delete Place API Payload")
	public void delete_place_api_payload() throws IOException {
		// Write code here that turns the phrase above into concrete actions
		reqChain = given().spec(requestSpecification()).body(testData.deletePayload(place_id));

	}

}
