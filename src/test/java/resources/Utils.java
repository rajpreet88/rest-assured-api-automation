package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req;

	public RequestSpecification requestSpecification() throws IOException {
		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			List<Filter> logFilters = new ArrayList<Filter>();
			logFilters.add(RequestLoggingFilter.logRequestTo(log));
			logFilters.add(ResponseLoggingFilter.logResponseTo(log));

			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL")).addFilters(logFilters)
					.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	}

	public String getGlobalValue(String key) throws IOException {
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\mmyst\\eclipse-workspace\\APIFramework\\src\\test\\java\\resources\\Global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
	}

	public String getJSONPath(Response res, String resKey) {
		String apiResponse = res.asString();
		JsonPath js = new JsonPath(apiResponse);
		System.out.println(js.get(resKey).toString());
		return js.get(resKey).toString();
	}
}
