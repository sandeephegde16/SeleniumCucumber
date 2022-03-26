package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPage extends pageObjectMethods.LoginPage{
	 @Given("^User provides username and password$")
	    public void user_provides_username_and_password() throws Throwable {
	        System.out.println("Suspeded!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	        getUserName();
	    }

	    @When("^User clicks on submit button$")
	    public void user_clicks_on_submit_button() throws Throwable {
	        
	    }

	    @Then("^User is taken to home page$")
	    public void user_is_taken_to_home_page() throws Throwable {
	        
	    }
}
