package com.evy.framework.config;

import com.evy.framework.constants.BrowserType;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

/**
 * Converter class for transforming a String representation of a browser type
 * to an enum constant of BrowserType.
 */
public final class BrowserTypeConverter implements Converter<BrowserType> {

    /**
     * Converts a String representation of a browser type to a BrowserType enum.
     *
     * @param method The method that requires the conversion.
     * @param browserType The string representation of the browser type.
     * @return The BrowserType enum constant.
     */
    @Override
    public BrowserType convert(Method method, String browserType) {
        return BrowserType.valueOf(browserType.toUpperCase());
    }
}
