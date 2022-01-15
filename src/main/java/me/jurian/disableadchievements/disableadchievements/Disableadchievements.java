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
        System.out.println("Disable-adchievements is enabled!");
        getServer().getPluginManager().registerEvents(this, this);
    }
    private void VerbergAdchievements(World world) {
        world.setGameRuleValue("announceAdvancements", "false");
        System.out.println("Achievements zijn nu verborgen voor wereld: '" + world.getName() + "'.");
    }

    /* verberg advancements voor nieuwe werelden */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onWorldLoad(WorldLoadEvent event) {
        VerbergAdchievements(event.getWorld());
    }
}
