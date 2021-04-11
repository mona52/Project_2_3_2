package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;
import java.time.Duration;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.DataGenerator.LoginRequest.*;


public class LoginTest {
    SelenideElement name = $("[data-test-id=login] .input__control");
    SelenideElement password = $("[data-test-id=password] .input__control");
    SelenideElement button = $("[data-test-id='action-login'] .button__text");

    @BeforeEach
    void openSite() {
        open("http://localhost:9999/");
    }

    @Test
    public void shouldLoginActive() {
        LoginData user = account("active");
        name.setValue(user.getLogin());
        password.setValue(user.getPassword());
        button.submit();
        $(withText("Личный кабинет")).shouldBe(Condition.visible, Duration.ofSeconds(5));
    }


    @Test
    public void shouldShowErrorIfInvalidLogin() {
        LoginData user = account("active");
        name.setValue(user.getLogin() + "er");
        password.setValue(user.getPassword());
        button.submit();
        $(withText("Ошибка")).shouldBe(Condition.visible, Duration.ofSeconds(5));
    }

    @Test
    public void shouldShowErrorIfInvalidPassword() {
        LoginData user = account("active");
        name.setValue(user.getLogin());
        password.setValue(user.getPassword() + "er");
        button.submit();
        $(withText("Ошибка")).shouldBe(Condition.visible, Duration.ofSeconds(5));
    }

    @Test
    public void shouldShowErrorIfInvalidPasswordAndLogin() {
        LoginData user = account("active");
        name.setValue(user.getLogin() + "er");
        password.setValue(user.getPassword() + "er");
        button.submit();
        $(withText("Ошибка")).shouldBe(Condition.visible, Duration.ofSeconds(5));
    }


    @Test
    public void shouldLoginBlocked() {
        LoginData user = account("blocked");
        name.setValue(user.getLogin());
        password.setValue(user.getPassword());
        button.submit();
        $(withText("заблокирован")).shouldBe(Condition.visible, Duration.ofSeconds(5));
    }

    @Test
    public void shouldLoginEmpty() {
        LoginData user = account("active");
        name.setValue("");
        password.setValue("");
        button.submit();
        $(withText("Поле обязательно для заполнения")).shouldBe(Condition.visible, Duration.ofSeconds(5));
    }
}



