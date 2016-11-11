package alpha;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by sriramangajala on 27/08/16.
 */
public class MyStepdefs {

    WebDriver webdriver ;




    @Before
    public void start()
    {
        System.setProperty("webdriver.chrome.driver","chromedriver");

        webdriver = new ChromeDriver();

        webdriver.get("http://demo.nopcommerce.com");
        webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Given("^User can go to home page$")
    public void User_can_go_to_home_page() throws Throwable {
       checkTitle();
    }

    @After
    public void end()
    {
        webdriver.quit();
    }

    public void checkTitle()
    {
        String title = webdriver.getTitle();
        System.out.println(title);
        Assert.assertTrue(title.equals("nopCommerce demo store"));

    }

    @When("^user registers as new user$")
    public void user_registers_as_new_user() throws Throwable {

        gotoRegisterScreen();
        enterRegisterDetails();


    }

    private void enterRegisterDetails() {


        selectGender(true);

        WebElement firstName = webdriver.findElement(By.name("FirstName"));
        firstName.sendKeys("Barack");

        WebElement lastName = webdriver.findElement(By.name("LastName"));
        lastName.sendKeys("Obama");


        WebElement day = webdriver.findElement(By.name("DateOfBirthDay"));
        Select day_select = new Select(day);
        day_select.selectByVisibleText("1");

        WebElement month = webdriver.findElement(By.name("DateOfBirthMonth"));
        Select month_select = new Select(month);
        month_select.selectByVisibleText("June");

        WebElement year = webdriver.findElement(By.name("DateOfBirthYear"));
        Select year_select = new Select(year);
        year_select.selectByVisibleText("1980");

        WebElement email = webdriver.findElement(By.id("Email"));
        email.sendKeys((new Random()).nextInt()+"_test1@test.com");

        System.out.print("Wait");
WebElement newsletter = webdriver.findElement(By.id("Newsletter"));
        newsletter.click();

        String password_text = "Password1";

        WebElement password = webdriver.findElement(By.id("Password"));
        password.sendKeys(password_text);


        WebElement ConfirmPassword = webdriver.findElement(By.id("ConfirmPassword"));
        ConfirmPassword.sendKeys(password_text);


        WebElement submit = webdriver.findElement(By.name("register-button"));
        System.out.println("Get attribute demo-get value:" + submit.getAttribute("value"));
        submit.click();

        checkForText("Your registration completed");

        System.out.print("Wait");

    }

    public void checkForText(String text)
    {
        WebElement content_ui = webdriver.findElement(By.tagName("body"));

        System.out.println(content_ui.getText());

        Assert.assertTrue(content_ui.getText().contains(text));
    }

    public void selectGender(boolean male)
    {
        if (male)
        {
            WebElement gender_male = webdriver.findElement(By.id("gender-male"));
            gender_male.click();
        }
        else {

            WebElement gender_female = webdriver.findElement(By.id("gender-female"));
            gender_female.click();
        }
    }

    public void gotoRegisterScreen()
    {
        WebElement register_link = webdriver.findElement(By.className("ico-register"));
        register_link.click();
    }

    @And("^he search the product with keyword \"([^\"]*)\"$")
    public void he_search_the_product_with_keyword(String keyword) throws Throwable {

        WebElement search = webdriver.findElement(By.name("q"));
        search.sendKeys(keyword);

        WebElement search_button = webdriver.findElement(By.xpath("//input[@value='Search']"));
        search_button.click();

    }

    @Then("^he should see equal (\\d+) result$")
    public void he_should_see_atleast_result(int expectedCount) throws Throwable {

        List<WebElement> results = webdriver.findElements(By.className("item-box"));

        int result_count = results.size();
        Assert.assertEquals(expectedCount,result_count);

//        for(WebElement result:results)
//        {
//         System.out.println(result.getText());
//        }

        //loop
        //get cunt
        //refer n


    }


    @Then("^no results found message is shown$")
    public void no_results_found_message_is_shown() throws Throwable {

        checkForText("No products were found that matched your criteria.");


    }

    @Then("^the ui should show message \"([^\"]*)\"$")
    public void the_ui_should_show_message(String arg1) throws Throwable {
        checkForText(arg1);
    }

    @When("^user select the first result and it should be added$")
    public void user_select_the_first_result() throws Throwable {

        List<WebElement> results = webdriver.findElements(By.className("item-box"));
        WebElement first_result = results.get(results.size()-1);
        WebElement buttons = first_result.findElement(By.className("buttons"));
        WebElement add_card = buttons.findElement(By.tagName("input"));
        add_card.click();

        Assert.assertTrue(webdriver.findElement(By.className("cart-qty")).getText().contains("1"));
        System.out.print("Wait");


    }

    @And("^contiue to add to basket$")
    public void contiue_to_add_to_basket() throws Throwable {

    }

    @Then("^item should be in basket$")
    public void item_should_be_in_basket() throws Throwable {

    }
}
