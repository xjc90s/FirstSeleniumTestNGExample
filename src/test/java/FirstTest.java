import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
 
/*****************************************************************************
 * Author:      Onur Baskirt
 * Description: This is the first Selenium TestNG test.
 *              It opens swtestacademy homepage and prints and checks its title.
*******************************************************************************/
 
public class FirstTest {
 
    //-----------------------------------Global Variables-----------------------------------
    //Declare a Webdriver variable
    private static WebDriver driver = null;
    private static WebElement element = null, table=null;
 
    //Declare a test URL variable
    private String testURL = "https://www.getapp.com/";
    private String keyword = "pdf";
    private String resulturl = null;
    //-----------------------------------Test Setup-----------------------------------
    @BeforeMethod
    public void setupTest (){
    	
    	System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
        //Create a new ChromeDriver
        driver = new ChromeDriver();
 
        //Go to www.swtestacademy.com
        driver.navigate().to(testURL);
        driver.manage().window().maximize();
    }
 
    //-----------------------------------Tests-----------------------------------
    @Test
    public void test01VerifyTitle () {
        //Get page title
        String title = driver.getTitle();
 
        //Print page's title
        System.out.println("Page Title: " + title);
 
        //Assertion
        Assert.assertEquals(title, "Business Software Reviews, SaaS & Cloud Applications Directory | GetApp�", "Title assertion is failed!");
    }
    @Test
    public void test02SearchApps () {

    
    	WebDriverWait wait = new WebDriverWait(driver, 10);
    	element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//form[2]//input[@name='query']")));
    	element.click();
    	element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]//form//span[2]//input[@name='search_keywords']")));
        element.sendKeys(keyword+Keys.RETURN);
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fullSearch']")));
        element.click();

 
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Reader Plus")));  
        Assert.assertEquals(element.getText(),"Reader Plus","Searched element assertion is failed!");
        element.click();
        /*
        wait = new WebDriverWait(driver, 10);
        System.out.println("\nELEMENT TO CLICK\n"+element.getText());
        element.findElement(By.xpath("//div[2]//div//div[2]//h2//a[@href='/collaboration-software/a/reader-plus/']"));
        
        element.click();
        */
        
    }
 
    //-----------------------------------Test TearDown-----------------------------------
    @AfterMethod
    public void teardownTest (){
        //Close browser and end the session
        driver.quit();
    }
}