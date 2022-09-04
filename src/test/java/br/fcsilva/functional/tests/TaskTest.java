package br.fcsilva.functional.tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TaskTest {

    WebDriver driver = new ChromeDriver();

    @Before
    @Test
    public void acessaAplicacao() {
        driver.get("http://localhost:8080/tasks/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // ChromeOptions chromeOptions = new ChromeOptions();
        // chromeOptions.setCapability("browserVersion", "79");

        // chromeOptions.setCapability("browserVersion", "79.0.3945.36");
        // chromeOptions.setCapability("platformName", "Mac OS X");
        // WebDriver driver = new RemoteWebDriver(new
        // URL("http://192.168.1.235:4444/wd/hub"), chromeOptions);

        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // return driver;
    }

    @After
    @Test
    public void quitBrowser() throws MalformedURLException {
        // WebDriver driver = acessaAplicacao();
        driver.quit();
    }

    @Test
    public void criarTarefaComSucesso() throws MalformedURLException {
        // WebDriver driver = acessaAplicacao();
        driver.findElement(By.id("addTodo")).click();
        driver.findElement(By.id("task")).sendKeys("Tarefa Teste via Selenium");
        driver.findElement(By.id("dueDate")).sendKeys("10/10/2022");
        driver.findElement(By.id("saveButton")).click();
        String message = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Sucess!", message);
    }

    @Test
    public void criarTarefaSemDescricao() throws MalformedURLException {
        // WebDriver driver = acessaAplicacao();
        driver.findElement(By.id("addTodo")).click();
        driver.findElement(By.id("dueDate")).sendKeys("10/10/2022");
        driver.findElement(By.id("saveButton")).click();
        String message = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Fill the task description", message);
    }

    @Test
    public void criarTarefaSemData() throws MalformedURLException {
        // WebDriver driver = acessaAplicacao();
        driver.findElement(By.id("addTodo")).click();
        driver.findElement(By.id("task")).sendKeys("Tarefa Teste via Selenium");
        driver.findElement(By.id("saveButton")).click();
        String message = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Fill the due date", message);
    }

    @Test
    public void criarTarefaComDataPassada() throws MalformedURLException {
        // WebDriver driver = acessaAplicacao();
        driver.findElement(By.id("addTodo")).click();
        driver.findElement(By.id("task")).sendKeys("Tarefa Teste via Selenium");
        driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");
        driver.findElement(By.id("saveButton")).click();
        String message = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Due date must not be in past", message);
    }
}
