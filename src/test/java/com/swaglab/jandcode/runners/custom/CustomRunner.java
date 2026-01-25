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
import java.lang.reflect.Modifier;

/**
 * Custom runner can find and update all files .feature before they executed
 */
public class CustomRunner extends Runner {

    private static final Logger LOGGER = LogManager.getLogger(CustomRunner.class.getName());
    private final Class<?> testClass;
    private CucumberWithSerenity cucumberRunner;

    public CustomRunner(Class<?> testClass) throws InitializationError {
        this.testClass = testClass;
        this.cucumberRunner = new CucumberWithSerenity(this.testClass);
    }

    private void runningMethodsAnnotate() throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Method[] methods = this.testClass.getMethods();
        Object instance = null;
        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                boolean isStatic = Modifier.isStatic(method.getModifiers());
                if (!isStatic) {
                    if (instance == null) {
                        instance = this.testClass.getDeclaredConstructor().newInstance();
                    }
                    method.invoke(instance);
                } else {
                    method.invoke(null);
                }
            }
        }
    }

    @Override
    public Description getDescription() {
        return cucumberRunner.getDescription();
    }

    @Override
    public void run(RunNotifier notifier) {
        try {
            runningMethodsAnnotate();
            this.cucumberRunner = new CucumberWithSerenity(this.testClass);
        } catch (Exception e) {
            LOGGER.error("Error running BeforeSuite methods", e);
        }
        cucumberRunner.run(notifier);
    }
}
