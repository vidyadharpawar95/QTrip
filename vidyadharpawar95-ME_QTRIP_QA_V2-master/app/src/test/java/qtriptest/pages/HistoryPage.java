
package qtriptest.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class HistoryPage {
    RemoteWebDriver driver;
    String url="https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/reservations/index.html";


    @FindBy(xpath ="//tbody[@id='reservation-table']/tr/th")
    WebElement transactionId;

    @FindBy(xpath = "//button[text()='Cancel']")
    WebElement cancel_button;

    @FindBy(xpath="//div[contains(text(),'Oops! You have not made any reservations yet!')]")
    WebElement verify_cancel;

    @FindBy(xpath = "//div[text()='Logout']")
    WebElement log_out;
    
    public HistoryPage(RemoteWebDriver driver){
        this.driver=driver;
        AjaxElementLocatorFactory ajax= new AjaxElementLocatorFactory(driver,  10);
        PageFactory.initElements(ajax, this);
    }

    public void navigateToHistoryPage() throws InterruptedException{
          if(!driver.getCurrentUrl().equals(url)){
            driver.get(url);
        }
    }

    public String storeTransactionId(){
        try{
            return transactionId.getText();
        } catch(Exception e) {
        return null;
        }
    }

    public void cancelReservation() throws InterruptedException{
        Thread.sleep(2000);
        cancel_button.click();
        Thread.sleep(2000);
        driver.navigate().refresh();
    }


public boolean verifyCancelReservation(){
    if(verify_cancel.getText().contains("Oops! You have not made any reservations yet!")){
        return true;
      }else{
        return false;
      }
    }

    public void logout() throws InterruptedException{
        Thread.sleep(2000);
        log_out.click();
    }
}