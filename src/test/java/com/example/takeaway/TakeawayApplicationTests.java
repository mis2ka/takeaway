package com.example.takeaway;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class TakeawayApplicationTests {

    public static WebDriver driver;

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    void openHomePage() {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        // otwieranie aplikacji www
        driver.navigate().to("http://localhost:8080/orders");
        driver.manage().window().maximize();
        String title = driver.getTitle();

        if(title.equalsIgnoreCase("Nie-gotuj.pl"))
            System.out.println("Tytuł się zgadza");
        else
            System.out.println(title);

        String tagname;
        tagname = driver.findElement(By.cssSelector("body > div > a")).getText();
        System.out.println(tagname);
    }

    @Test
    void sendEmail() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        // otwieranie aplikacji www
        driver.navigate().to("http://localhost:8080/orders");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        // Przycisk Koszyk
        driver.findElement(By.cssSelector("body > div > a")).click();
        Thread.sleep(2000);

        driver.findElement(By.linkText("Płacę")).click();
        Thread.sleep(2000);

        // Wpisanie tekstu w formularzu
        WebElement name=driver.findElement(By.cssSelector("#name"));
        name.clear();
        name.sendKeys("Jan");
        Thread.sleep(2000);
        WebElement email=driver.findElement(By.cssSelector("#email"));
        email.clear();
        email.sendKeys("kowalski@gmail.com");
        Thread.sleep(2000);

        // Wyslanie maila
        driver.findElement(By.cssSelector("body > div > div.page_content > form > div > button")).click();
        Thread.sleep(2000);
    }

    @Test
    void addToCartManyProducts() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.navigate().to("http://localhost:8080/orders");
        driver.manage().window().maximize();

        // przycisk kupuję przy opcji Pizza
        driver.findElement(By.cssSelector("body > div > article:nth-child(3) > div > a")).click();
        Thread.sleep(2000);

        // przycisk kontynuuj zakupy
        driver.findElement(By.cssSelector("body > div > a")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("body > div > article:nth-child(3) > div > a")).click();
        driver.findElement(By.cssSelector("body > div > a")).click();

        // przycisk kupuję przy opcji Kebab
        driver.findElement(By.cssSelector("body > div > article:nth-child(5) > div > a")).click();
        Thread.sleep(2000);
    }

    @Test
    void deleteFromCart() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.navigate().to("http://localhost:8080/orders");
        driver.manage().window().maximize();

        // przycisk kupuję przy opcji Pizza
        driver.findElement(By.cssSelector("body > div > article:nth-child(3) > div > a")).click();
        Thread.sleep(2000);

        // przycisk kontynuuj zakupy
        driver.findElement(By.xpath("/html/body/div/a")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("body > div > article:nth-child(4) > div > a")).click();
        Thread.sleep(2000);

        // przycisk Usuń
        driver.findElement(By.cssSelector("body > div > article:nth-child(3) > div > a")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("body > div > article:nth-child(2) > div > a")).click();
        Thread.sleep(2000);

        Assertions.assertEquals("http://localhost:8080/cart", driver.getCurrentUrl());
    }

    @Test
    void continueShopping() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.navigate().to("http://localhost:8080/cart");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        // przycisk Kontynuuj zakupy
        driver.findElement(By.cssSelector("body > div > a")).click();
        Thread.sleep(2000);
        Assertions.assertEquals("http://localhost:8080/orders", driver.getCurrentUrl());
    }

    @Test
    void returnToHomePage() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.navigate().to("http://localhost:8080/feedback/new");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        // przycisk Strona główna
        driver.findElement(By.cssSelector("#navbarToggle > div > a")).click();
        Thread.sleep(2000);
        Assertions.assertEquals("http://localhost:8080/orders", driver.getCurrentUrl());
    }
}
