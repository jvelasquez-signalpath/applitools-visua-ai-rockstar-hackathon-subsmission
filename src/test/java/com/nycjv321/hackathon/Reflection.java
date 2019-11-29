package com.nycjv321.hackathon;

import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Optional;

public class Reflection {
    public static Optional<String> getTestDescription(Method method) {
        Test test = method.getAnnotation(Test.class);
        return Optional.ofNullable(test).map(Test::description);
    }


    public static String determineTestName() {
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            try {
                Method testMethod = Class.forName(stackTraceElement.getClassName()).getMethod(stackTraceElement.getMethodName());
                Optional<String> description  = getTestDescription(testMethod);
                if (description.isPresent()) {
                    return description.get();
                }
            } catch (NoSuchMethodException | NoClassDefFoundError | ClassNotFoundException e) {
                // this is only for a code submission
                // this was done to skip inner classes
                // production code would handle this more intelligently.
                // e.g. checking the class name for inner class naming syntax
                continue;
            }
        }

        throw new RuntimeException("unable to determine test name");
    }
}
