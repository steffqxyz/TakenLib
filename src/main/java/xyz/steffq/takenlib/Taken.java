package xyz.steffq.takenlib;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import xyz.steffq.takenlib.configuration.ConfigManager;

public final class Taken {

    @Getter
    private static ConfigManager configManager;

    public static void onLoad(@NotNull ConfigManager configManager) {
        Taken.configManager = configManager;
    }

}
