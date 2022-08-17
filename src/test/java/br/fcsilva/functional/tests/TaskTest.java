package br.fcsilva.functional.tests;

import java.time.Duration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TaskTest {
    WebDriver driver = new ChromeDriver();

    @Before
    @Test
    public void iniciaBrowser() {
        driver.get("http://localhost:8080/tasks");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    @Test
    public void quitBrowser() {
        driver.quit();
    }

    @Test
    public void criarTarefaComSucesso() {
        driver.findElement(By.id("addTodo")).click();
        driver.findElement(By.id("task")).sendKeys("Tarefa Teste via Selenium");
        driver.findElement(By.id("dueDate")).sendKeys("10/10/2022");
        driver.findElement(By.id("saveButton")).click();
        String message = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Sucess!", message);
    }

    @Test
    public void criarTarefaSemDescricao() {
        driver.findElement(By.id("addTodo")).click();
        driver.findElement(By.id("dueDate")).sendKeys("10/10/2022");
        driver.findElement(By.id("saveButton")).click();
        String message = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Fill the task description", message);
    }

    @Test
    public void criarTarefaSemData() {
        driver.findElement(By.id("addTodo")).click();
        driver.findElement(By.id("task")).sendKeys("Tarefa Teste via Selenium");
        driver.findElement(By.id("saveButton")).click();
        String message = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Fill the due date", message);
    }

    @Test
    public void criarTarefaComDataPassada() {
        driver.findElement(By.id("addTodo")).click();
        driver.findElement(By.id("task")).sendKeys("Tarefa Teste via Selenium");
        driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");
        driver.findElement(By.id("saveButton")).click();
        String message = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Due date must not be in past", message);
    }
}
