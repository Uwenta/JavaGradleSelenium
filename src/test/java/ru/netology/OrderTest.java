package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest {
    private WebDriver driver;


    @BeforeAll
    static void setUpAll() {
        //System.setProperty("webdriver.chrome.driver", "./drivers/win/chromedriver.exe");
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestNameEva() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Ева Нилович");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79024420002");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button_view_extra")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success] ")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());

    }

    @Test
    void shouldTestNameAnna_Maria() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Анна-Мария Нилович");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79024420002");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button_view_extra")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success] ")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());

    }

    @Test
    void shouldTestNameAlena() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Алёна Нилович");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79024420002");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button_view_extra")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success] ")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    void shouldTestInvalidName() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Angelina Vasina");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79024421234");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button_view_extra")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText();

        assertEquals("Имя и Фамилия указаны неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }

    @Test
    void shouldTestInvalidPhone() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Липова Крисс");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+7902442123410");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button_view_extra")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    void shouldTestShortPhone() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Липова Крисс");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+7902");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button_view_extra")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    void shouldTestEmptyName() {
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79024421234");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button_view_extra")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText();

        assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    void shouldTestEmptyPhone() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Ева Нилович");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button_view_extra")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText();

        assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    void shouldTestEmptyCheckBox() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Ева Нилович");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79024421234");
        driver.findElement(By.cssSelector("button.button_view_extra")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=agreement].input_invalid .checkbox__text")).getText();

        assertEquals("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй", text.trim());
    }

}
