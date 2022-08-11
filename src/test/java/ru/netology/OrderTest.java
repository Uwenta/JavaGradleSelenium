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

public class OrderTest {
    private WebDriver driver;


    @BeforeAll
    static void setUpAll() {
        //System.setProperty("webdriver.chrome.driver", "./drivers/win/chromedriver.exe");
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach

    void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown(){
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestNameEva(){
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Ева Нилович");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79024420002");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button_view_extra")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success] ")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());

    }

    @Test
    void shouldTestNameAnna_Maria(){
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Анна-Мария Нилович");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79024420002");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button_view_extra")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success] ")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());

    }

    @Test
    void shouldTestNameAlena(){
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Алёна Нилович");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79024420002");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button_view_extra")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success] ")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());

    }

}
