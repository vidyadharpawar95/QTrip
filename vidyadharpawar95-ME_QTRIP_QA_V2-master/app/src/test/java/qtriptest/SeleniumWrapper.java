package qtriptest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumWrapper {

    public static boolean click(WebElement elementToClick, WebDriver driver ){
      
        return true;
    }

    public static boolean sendkeys(WebElement input, String keysToSend){
        return false;
        
    }

    public static boolean navigate(WebDriver driver, String url){
        return false;
        
    }

    public static WebElement findElementWithRetry(WebDriver driver,By by,int retryCount) throws Exception{
        return null;
      
        
    }
}
