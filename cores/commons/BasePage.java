import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.List;
import java.util.Set;

public class BasePage {
    /* Web Browser */

    public void openPageURL(WebDriver driver, String pageURL) {
        driver.get(pageURL);
    }


    public String getPageURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitForAlertPresence(WebDriver driver){
        return new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.alertIsPresent());
    }
    public void acceptAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    public void cancelAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    public void sendkeyToAlert(WebDriver driver, String valueToSendkey) {
        waitForAlertPresence(driver).sendKeys(valueToSendkey);
    }

    public String getAlertText(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    public void switchToWindowByID(WebDriver driver, String expectedID) {
        Set<String> allTabIDs = driver.getWindowHandles();

        for (String id : allTabIDs) {
            if (!id.equals(expectedID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
        Set<String> allTabIDs = driver.getWindowHandles();

        for (String id : allTabIDs) {
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(expectedTitle)) {
                break;
            }
        }
    }

    public boolean closeAllWindowsWithoutParent(WebDriver driver, String parentID){
        Set<String> allWindows = driver.getWindowHandles();

        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
        if (driver.getWindowHandles().size() ==1)
            return true;
        else
            return false;
    }

    /* Web Element*/

    public By getByXpath(String locator){
        return By.xpath(locator);
    }
    public WebElement getWebElement(WebDriver driver, String locator){
        return driver.findElement(getByXpath(locator));
    }

    public List<WebElement> getListElements(WebDriver driver, String locator){
        return driver.findElements(getByXpath(locator));
    }
    public void clickToElement(WebDriver driver, String locator){
        getWebElement(driver, locator).click();
    }

    public void sendkeyToElement(WebDriver driver, String locator, String valueToInput){
        WebElement element = getWebElement(driver, locator);
        element.clear();
        element.sendKeys(valueToInput);
    }

    public String getElementText(WebDriver driver, String locator){
        return getWebElement(driver, locator).getText();
    }

    public String getElementAttributeValue(WebDriver driver, String locator, String attributeName){
        return getWebElement(driver, locator).getAttribute(attributeName);
    }

    public String getElementCssValue(WebDriver driver, String locator, String propertyName){
        return getWebElement(driver, locator).getCssValue(propertyName);
    }

    public int getListElementSize(WebDriver driver, String locator){
        return getListElements(driver, locator).size();
    }

    public void checkToCheckboxOrRadio(WebDriver driver, String locator){
        WebElement element = getWebElement(driver, locator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void unCheckToCheckbox(WebDriver driver, String locator){
        WebElement element = getWebElement(driver, locator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator){
        return getWebElement(driver, locator).isDisplayed();
    }

    public boolean isElementEnable(WebDriver driver, String locator){
        return getWebElement(driver, locator).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String locator){
        return getWebElement(driver, locator).isSelected();
    }

    public void switchToIFrame(WebDriver driver, String locator){
        driver.switchTo().frame(getWebElement(driver, locator));
    }

    public void switchToDefaultContent(WebDriver driver, String locator){
        driver.switchTo().defaultContent();
    }

    public void hoverMouseToElement(WebDriver driver, String locator){
        Actions actions = new Actions(driver);
        actions.moveToElement(getWebElement(driver, locator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator){
        Actions actions = new Actions(driver);
        actions.contextClick(getWebElement(driver, locator)).perform();
    }

    public void doubleClickToElement(WebDriver driver, String locator){
        Actions actions = new Actions(driver);
        actions.doubleClick(getWebElement(driver, locator)).perform();
    }

    public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator){
        Actions actions = new Actions(driver);
        actions.dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator)).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key ){
        Actions actions = new Actions(driver);
        actions.sendKeys(getWebElement(driver, locator), key).perform();
    }

    public void hightlightElement(WebDriver driver, String locator){
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
    }

    public void scrollToElementOnTop(WebDriver driver, String locator){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void scrollToElementOnDown(WebDriver driver, String locator){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator){
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value','" + value +"');", getWebElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String attributeRemove){
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator){
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;",getWebElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator){
        boolean status = (boolean) ((JavascriptExecutor) driver).executeScript("return argument[0].complete && typeof argument[0].naturalWildth != 'udefined'",getWebElement(driver, locator));
        if(status){
            return true;
        }
        return false;
    }

    private long longTimeOut = 30;
    private long shortTimeOut = 5;
    public void waitForElementVisible(WebDriver driver, String locator){
        new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator){
        new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator){
        new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }


    public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemText){
        Select select = new Select(getWebElement(driver, locator));
        select.selectByVisibleText(itemText);
    }

    public String getFirstSelectedTextItem(WebDriver driver, String locator){
        Select select = new Select(getWebElement(driver, locator));
        return select.getFirstSelectedOption().getText();
    }

    public boolean isDropDownMultiple(WebDriver driver, String locator){
        Select select = new Select(getWebElement(driver, locator));
        return select.isMultiple();
    }

    public  void sleepInSecond(long timeInSecond){
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedItemText){
        getWebElement(driver, parentXpath).click();
        sleepInSecond(2);

        List<WebElement> childItems = new WebDriverWait(driver,longTimeOut).until(ExpectedCondition.presenceOfAllElementsLocatedBy(getByXpath(childXpath)));

        for (WebElement tempElement : childItems){
            if(tempElement.getText().trim().equals(expectedItemText)){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", tempElement);
                sleepInSecond(1);
                tempElement.click();
                sleepInSecond(1);
                break;
            }
        }
    }
}


