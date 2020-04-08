package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TaskTest {
	
	public  WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks/");
		driver.findElement(By.id("addTodo")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAplicacao();
		try {
		driver.findElement(By.id("task")).sendKeys("Teste automatizado");
		driver.findElement(By.id("dueDate")).sendKeys("10/04/2020");
		driver.findElement(By.id("saveButton")).click();
		String text = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Success!",text);
		}finally {
			driver.quit();	
		}
		
	}
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		WebDriver driver = new ChromeDriver();
		try {
		driver.navigate().to("http://localhost:8001/tasks/");
		driver.findElement(By.id("addTodo")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("task")).sendKeys("Teste automatizado");
		driver.findElement(By.id("dueDate")).sendKeys("10/04/2010");
		driver.findElement(By.id("saveButton")).click();
		String text = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Due date must not be in past",text);
		}finally {
			driver.quit();	
		}
		
	}
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = new ChromeDriver();
		try {
		driver.navigate().to("http://localhost:8001/tasks/");
		driver.findElement(By.id("addTodo")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("dueDate")).sendKeys("10/04/2020");
		driver.findElement(By.id("saveButton")).click();
		String text = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the task description",text);
	}finally {
		driver.quit();	
	}	
	}
	@Test
	public void naoDeveSalvarTarefaSemData() {
		WebDriver driver = new ChromeDriver();
		try {
		driver.navigate().to("http://localhost:8001/tasks/");
		driver.findElement(By.id("addTodo")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("task")).sendKeys("Teste automatizado");
		driver.findElement(By.id("saveButton")).click();
		String text = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the due date",text);
		}finally {
			driver.quit();	
		}
	}
}
