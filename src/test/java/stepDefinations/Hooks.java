package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;
import io.restassured.response.Response;
import resources.Utils;

public class Hooks extends Utils {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {

		StepDefination sd = new StepDefination();
		if (StepDefination.place_id == null) {

			sd.add_place_api_payload(25.14578, 99.55787, 15, "Freemont Valley", "(+1) 789 654 1230",
					"Palo Alto, Nevada, California", "https://www.tesla.com", "EN-US");
			sd.user_calls_with_http_post_request("addPlaceAPI", "POST");
			sd.verify_created_maps_to_using("Freemont Valley", "getPlaceAPI", "GET");
		}
	}
}
