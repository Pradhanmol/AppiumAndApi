package org.fos.mobile.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class VisitCreationPage {

    public AndroidDriver driver;

    @AndroidFindBy(xpath="//*[@text='PORTFOLIO']")
    public WebElement portfolioButton;

    @AndroidFindBy(xpath="//*[@text='Search']")
    public WebElement searchButton;

    @AndroidFindBy(xpath="//*[@resource-id='FOS_TOUCHABLE_PORTFOLIO_ROW_5788']")
    public WebElement selectLoan;

    @AndroidFindBy(xpath="//*[@class='android.widget.Button']")
    public WebElement plusIcon;

    @AndroidFindBy(xpath="//*[@resource-id='RNE__ICON__CONTAINER']")
    public WebElement scheduleVisit;

    @AndroidFindBy(xpath="//*[@text='Select Visit Date']")
    public WebElement visitDate;

    @AndroidFindBy(xpath="//*[@text='Select Visit Time']")
    public WebElement visitTime;

    @AndroidFindBy(xpath="//*[@text='OK']")
    public WebElement OKButton;

    @AndroidFindBy(xpath="//*[@text='Create']")
    public WebElement createButton ;

    @AndroidFindBy(xpath="//*[@content-desc='Next month']")
    public WebElement nextButton ;

    @AndroidFindBy(xpath="//*[@text='1']")
    public WebElement selectDate ;

    public VisitCreationPage(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

        this.driver=driver;

    }

}

