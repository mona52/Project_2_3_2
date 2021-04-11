package ru.netology;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class DataGenerator {

    private DataGenerator() {
    }

    public static class LoginRequest {

        public LoginRequest() {
        }

        private static final RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(9999)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        public static void createUser(LoginData user) {
            // сам запрос
            given() // "дано"
                    .spec(requestSpec) // указываем, какую спецификацию используем
                    .body(new Gson().toJson(user)) // передаём в теле объект, который будет преобразован в JSON
                    .when() // "когда"
                    .post("/api/system/users")// на какой путь, относительно BaseUri отправляем запрос
                    .then() // "тогда ожидаем"
                    .statusCode(200); // код 200 OK
        }

        public static LoginData account(String status) {
            Faker faker = new Faker();
            LoginData user = new LoginData(faker.name().username(), faker.internet().password(), status);
            createUser(user);
            return user;
        }
    }

}
