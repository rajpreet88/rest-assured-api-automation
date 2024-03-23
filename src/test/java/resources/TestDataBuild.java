package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddBaseSerializePojo;
import pojo.LocationPojo;

public class TestDataBuild {
	public AddBaseSerializePojo addPlacePayload(Double latitude, Double longitude, Integer accuracy, String name,
			String phone_number, String address, String website, String language) {
		AddBaseSerializePojo asp = new AddBaseSerializePojo();

		asp.setAccuracy(accuracy);
		asp.setName(name);
		asp.setPhone_number(phone_number);
		asp.setAddress(address);
		asp.setWebsite(website);
		asp.setLanguage(language);

		List<String> myList = new ArrayList<String>();
		myList.add("theatrical");
		myList.add("3rd-person");
		asp.setTypes(myList);

		LocationPojo lp = new LocationPojo();
		lp.setLat(latitude);
		lp.setLng(longitude);
		asp.setLocation(lp);
		return asp;
	}

	public String deletePayload(String placeID) {
		return "{\r\n" + "    \"place_id\":\"" + placeID + "\"\r\n" + "}";
	}
}
