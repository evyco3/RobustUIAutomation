package com.evy.framework.config;

import org.aeonbits.owner.ConfigCache;

/**
 * ConfigManager is a thread-safe utility class for accessing the FrameworkConfig.
 */
public final class ConfigManager {

    private static final FrameworkConfig INSTANCE = ConfigCache.getOrCreate(FrameworkConfig.class);

    private ConfigManager() {
        // Private constructor to prevent instantiation
    }

    /**
     * Retrieves the FrameworkConfig instance.
     *
     * @return The FrameworkConfig instance.
     */
    public static FrameworkConfig get() {
        return INSTANCE;
    }
}