package TestFramework;

import org.testng.annotations.Test;



import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import resources.*;
public class excelDriven {

    @Test
    public void addBook() throws IOException {
        dataDriven d = new dataDriven();
        ArrayList data = d.getData("RestAddbook","RestAssured");

        HashMap<String, Object>  map = new HashMap<String, Object>();
        map.put("team_id", data.get(1));
        map.put("team_name", data.get(2));
        map.put("role_id", data.get(3));
        //map.put("author", data.get(4));

        RestAssured.baseURI = "xxxx";
        Response resp = given().
                header("Content-Type","application/json").
                body(map).
                when().
                post("/management/team").
                then().assertThat().statusCode(200).
                extract().response();
        JsonPath js = ReusableMethods.rawToJson(resp);
        String id = js.get("message");
        System.out.println(id);

    }

    /*
    public static String GenerateStringFromResource(String path) throws IOException {

        return new String(Files.readAllBytes(Paths.get(path)));

    }
    */
}
