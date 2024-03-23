package resources;

//Enum is a special class in Java which is a collection of constants or methods
public enum APIResources {

	addPlaceAPI("/maps/api/place/add/json"), getPlaceAPI("/maps/api/place/get/json"),
	updatePlaceAPI("/maps/api/place/update/json"), deletePlaceAPI("/maps/api/place/delete/json");

	private String resource;

	APIResources(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}
}
