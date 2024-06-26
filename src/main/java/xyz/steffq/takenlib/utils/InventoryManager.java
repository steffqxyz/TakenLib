package xyz.steffq.takenlib.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    private File inventoryFile;
    private FileConfiguration inventoryConfig;

    public InventoryManager(Plugin plugin, String fileName) {
        inventoryFile = new File(plugin.getDataFolder(), fileName);
        if (!inventoryFile.exists()) {
            try {
                if (!inventoryFile.getParentFile().exists()) {
                    inventoryFile.getParentFile().mkdirs();
                }
                inventoryFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        inventoryConfig = YamlConfiguration.loadConfiguration(inventoryFile);
    }

    public void savePlayerInventory(Player player) {
        if (player == null) {
            return;
        }
        Inventory inventory = player.getInventory();
        if (inventory == null) {
            return;
        }
        Map<Integer, ItemStack> contents = new HashMap<>();
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null) {
                contents.put(i, item.clone());
            }
        }
        inventoryConfig.set(player.getUniqueId().toString(), contents);
        try {
            inventoryConfig.save(inventoryFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadPlayerInventory(Player player) {
        if (player == null) {
            return;
        }
        if (!inventoryConfig.contains(player.getUniqueId().toString())) {
            return;
        }
        Object inv = inventoryConfig.get(player.getUniqueId().toString());
        if (!(inv instanceof Map)) {
            return;
        }
        Map<Integer, ItemStack> contents = (Map<Integer, ItemStack>) inv;
        Inventory inventory = player.getInventory();
        if (inventory == null) {
            return;
        }
        inventory.clear();
        for (Map.Entry<Integer, ItemStack> entry : contents.entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue());
        }
    }

    public void deletePlayerInventory(Player player) {
        if (player == null) {
            return;
        }
        inventoryConfig.set(player.getUniqueId().toString(), null);
        try {
            inventoryConfig.save(inventoryFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
