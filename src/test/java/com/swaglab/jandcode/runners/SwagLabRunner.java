package com.swaglab.jandcode.runners;

import com.swaglab.jandcode.runners.custom.CustomRunner;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(CustomRunner.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "com.swaglab.jandcode.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "@remove_shopping_cart"
)
public class SwagLabRunner {
}
