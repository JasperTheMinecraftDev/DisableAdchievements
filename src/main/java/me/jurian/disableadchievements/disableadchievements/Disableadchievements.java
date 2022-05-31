package me.jurian.disableadchievements.disableadchievements;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Disableadchievements extends JavaPlugin implements Listener {

    @Override()
    public void onEnable()
    {
        System.out.println("Disable-adchievements is now enabled!");
        getServer().getPluginManager().registerEvents(this, this);
    }
    private void hideadchievements(World world) {
        world.setGameRuleValue("announceAdvancements", "false");
        System.out.println("Achievements are now hidden for the world: '" + world.getName() + "'.");
    }

    /* Hide advancements for new worlds. */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onWorldLoad(WorldLoadEvent event) {
        hideadchievements(event.getWorld());
    }
}
