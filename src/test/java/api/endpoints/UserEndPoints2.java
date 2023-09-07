package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

// CRUD methods implementations to perform CRUD operations: Create, Read, Update, Delete
// requests the user API
public class UserEndPoints2 {

//  Load properties
    public static ResourceBundle getURL() {
        return ResourceBundle.getBundle("routes");
    }

    public static Response createUser(User payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(getURL().getString("post_url"));

    }

    public static Response readUser(String username) {
        return given()
                .pathParam("username", username)
                .when()
                .get(getURL().getString("get_url"));
    }

    public static Response updateUser(String username, User payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .body(payload)
                .when()
                .put(getURL().getString("update_url"));

    }

    public static Response deleteUser(String username) {
        return given()
                .pathParam("username", username)
                .when()
                .delete(getURL().getString("delete_url"));
    }
}


