package xyz.steffq.takenlib.utils;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    public static String color(@NotNull String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static List<String> colorList(@NotNull List<String> input) {
        List<String> colored = new ArrayList<>();
        for (String i : input) {
            colored.add(color(i));
        }

        return colored;
    }

}