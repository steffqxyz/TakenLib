package xyz.steffq.takenlib.hotbar;

import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Hotbar {

    // Slot, HotbarItem
    public Map<Integer, HotbarItem> items = new HashMap<>();

    public void setItem(int slot, HotbarItem item) {
        items.put(slot, item);
    }

    public HotbarItem getItem(int slot) {
        return items.getOrDefault(slot, null);
    }

    public void give(Player player) {
        PlayerInventory inventory = player.getInventory();

        for (Map.Entry<Integer, HotbarItem> entry : items.entrySet()) {
            HotbarItem item = entry.getValue();
            inventory.setItem(entry.getKey(), item);
        }
    }

    public Collection<Integer> getSlots() {
        return items.keySet();
    }

}
