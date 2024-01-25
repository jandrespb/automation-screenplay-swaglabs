package com.swaglab.jandcode.runners.custom;

import com.swaglab.jandcode.utils.excel.BeforeSuite;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Custom runner can find and update all files .feature before they executed
 */
public class CustomRunner extends Runner {
    private static final Logger LOGGER = LogManager.getLogger(CustomRunner.class.getName());
    private final Class<CucumberWithSerenity> CUCUMBER_WITH_SERENITY_CLASS;
    private CucumberWithSerenity CUCUMBER_WITH_SERENITY;

    // Constructor
    public CustomRunner(Class<CucumberWithSerenity> cucumberWithSerenityClass) throws InitializationError {
        CUCUMBER_WITH_SERENITY_CLASS = cucumberWithSerenityClass;
        CUCUMBER_WITH_SERENITY = new CucumberWithSerenity(CUCUMBER_WITH_SERENITY_CLASS);
    }

    private void runningMethodsAnnotate() throws InvocationTargetException, IllegalAccessException {
        if(!BeforeSuite.class.isAnnotation()){
            return;
        }
        Method[] accessMethod = this.CUCUMBER_WITH_SERENITY_CLASS.getMethods();
        for (Method method:
                accessMethod) {
            Annotation[] annotation = method.getAnnotations();
            for (Annotation item:
                    annotation) {
                if(item.annotationType().equals(BeforeSuite.class)) {
                    method.invoke(null);
                    break;
                }
            }
        }
    }

    @Override
    public Description getDescription() {
        return CUCUMBER_WITH_SERENITY.getDescription();
    }

    @Override
    public void run(RunNotifier customRunner) {
        try {
            runningMethodsAnnotate();
            CUCUMBER_WITH_SERENITY = new CucumberWithSerenity(CUCUMBER_WITH_SERENITY_CLASS);

        }catch (InvocationTargetException | IllegalAccessException | InitializationError e) {
            LOGGER.info(e);
        }
        CUCUMBER_WITH_SERENITY.run(customRunner);
    }
}
