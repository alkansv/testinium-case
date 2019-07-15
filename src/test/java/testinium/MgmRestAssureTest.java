package testinium;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MgmRestAssureTest {

	@Test
	public void testMGMApiWeather() {

		RestAssured.baseURI = "https://servis.mgm.gov.tr/web";
		RequestSpecification request = RestAssured
				.given()
				.header("Origin", "https://www.mgm.gov.tr")
				.config(RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames()));


		Response ilceResponse = request
				.when()
				.get("/merkezler/ililcesi?il=Istanbul")
				.then()
				.statusCode(200)
				.extract().response();

		int ilceResponseCode = ilceResponse.getStatusCode();
		ResponseBody ilceResponseBody = ilceResponse.getBody();
		int merkezId = ilceResponseBody.jsonPath().get("[6].merkezId");
		String il = ilceResponseBody.jsonPath().get("[6].il");

		assertEquals(200, ilceResponseCode);
		assertEquals("İstanbul", il);
		assertEquals(93401, merkezId);

		///-------------

		Response sondurumResponse = request
				.when()
				.get("/sondurumlar?merkezid=" + merkezId)
				.then()
				.statusCode(200)
				.extract().response();

		int sondurumResponseCode = sondurumResponse.getStatusCode();
		ResponseBody sondurumResponseBody = sondurumResponse.getBody();
		String hadiseKodu = sondurumResponseBody.jsonPath().get("[0].hadiseKodu");
		int istNo = sondurumResponseBody.jsonPath().get("[0].istNo");

		assertEquals(200, sondurumResponseCode);
		assertEquals(17060, istNo);

		///-------------

		Response ucdegerler = request
				.when()
				.get("/ucdegerler?merkezid=93401&ay=7&gun=15")
				.then()
				.statusCode(200)
				.extract().response();

		int ucdegerlerResponseCode = ucdegerler.getStatusCode();
		ResponseBody ucdegerlerResponseBody = ucdegerler.getBody();
		int merkezIdResponse = ucdegerlerResponseBody.jsonPath().get("[0].merkezId");
		int istNoResponse  = ucdegerlerResponseBody.jsonPath().get("[0].istNo");
		int ay  = ucdegerlerResponseBody.jsonPath().get("[0].ay");
		int gun  = ucdegerlerResponseBody.jsonPath().get("[0].gun");

		assertEquals(200, ucdegerlerResponseCode);
		assertEquals(93401, merkezIdResponse);
		assertEquals(93401, istNoResponse);
		assertEquals(7, ay);
		assertEquals(15, gun);


		Response ucdegerlerGun16 = request
				.when()
				.get("/ucdegerler?merkezid=93401&ay=7&gun=16")
				.then()
				.statusCode(200)
				.extract().response();

		int ucdegerlerResponseCodeGun16 = ucdegerlerGun16.getStatusCode();
		ResponseBody ucdegerlerResponseBodyGun16 = ucdegerlerGun16.getBody();
		int merkezIdResponseGun16 = ucdegerlerResponseBodyGun16.jsonPath().get("[0].merkezId");
		int istNoResponseGun16  = ucdegerlerResponseBodyGun16.jsonPath().get("[0].istNo");
		int ayGun16  = ucdegerlerResponseBodyGun16.jsonPath().get("[0].ay");

		assertEquals(200, ucdegerlerResponseCodeGun16);
		assertEquals(93401, merkezIdResponseGun16);
		assertEquals(93401, istNoResponseGun16);
		assertEquals(7, ayGun16);
	}

}
