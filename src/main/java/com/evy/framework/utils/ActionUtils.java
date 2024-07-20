package com.evy.framework.utils;

import io.qameta.allure.Allure;

import java.util.function.Supplier;

public final class ActionUtils {

    private ActionUtils() {}

    public static void execVoid(Class<?> cls, Runnable exec, String successMsg, String errorMsg) {
        int attempts = 0;
        boolean success = false;

        while (!success) {
            try {
                exec.run(); // Execute the action
                LoggerUtils.info(cls, successMsg); // Log success message
                Allure.step(successMsg); // Report success message to Allure
                success = true; // Mark success as true to exit the loop
            } catch (Exception e) {
                attempts++;
                if (attempts >= 5) {
                    // Log and throw RuntimeException for the final failed attempt
                    LoggerUtils.error(cls, errorMsg, e); // Log failure message and exception
                    Allure.step(errorMsg); // Report failure message to Allure
                    throw new RuntimeException(errorMsg, e); // Throw runtime exception with failure message and cause
                }
            }
        }
    }

    public static String execString(Class<?> cls, Supplier<String> exec, String successMsg, String errorMsg) {
        int attempts = 0;

        while (attempts < 5) {
            try {
                String result = exec.get(); // Execute the action
                LoggerUtils.info(cls, successMsg); // Log success message
                Allure.step(successMsg); // Report success message to Allure
                return result; // Return the result on success
            } catch (RuntimeException e) {
                attempts++;
                if (attempts >= 5) {
                    // Log and throw RuntimeException for the final failed attempt
                    LoggerUtils.error(cls, errorMsg, e); // Log failure message and exception
                    Allure.step(errorMsg); // Report failure message to Allure
                    throw new RuntimeException(errorMsg, e); // Throw runtime exception with failure message and cause
                }
            }
        }

        return null; // This line will never be reached due to the throw statement above
    }

    public static boolean execBoolean(Class<?> cls, Supplier<Boolean> exec, String successMsg, String errorMsg) {
        int attempts = 0;
        boolean success = false;

        while (!success) {
            try {
                boolean result = exec.get(); // Execute the action
                LoggerUtils.info(cls, successMsg); // Log success message
                Allure.step(successMsg); // Report success message to Allure
                success = true; // Mark success as true to exit the loop
                return result;
            } catch (Exception e) {
                attempts++;
                if (attempts >= 5) {
                    // Log and throw RuntimeException for the final failed attempt
                    LoggerUtils.error(cls, errorMsg, e); // Log failure message and exception
                    Allure.step(errorMsg); // Report failure message to Allure
                    throw new RuntimeException(errorMsg, e); // Throw runtime exception with failure message and cause
                }
            }
        }
        return false; // This line will never be reached, but it's needed for compilation
    }
}
