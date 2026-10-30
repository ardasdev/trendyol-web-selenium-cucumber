package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/cart" ,
        glue = {"stepdefs", "hooks"} ,
        plugin = {"pretty","html:target/cucumber-html-report"}

)

public class CartRunner {


}
