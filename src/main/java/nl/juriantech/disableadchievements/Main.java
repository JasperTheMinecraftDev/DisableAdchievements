package nl.juriantech.disableadchievements;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().severe("Disable-adchievements is now enabled!");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().severe("Disable-adchievements is now disabled!");
    }
    private void hideAdchievements(World world) {
        world.setGameRuleValue("announceAdvancements", "false");
        getLogger().info("Achievements are now hidden for the world: " + world.getName() + ".");
    }

    /* Hide advancements for new worlds. */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onWorldLoad(WorldLoadEvent event) {
        hideAdchievements(event.getWorld());
    }
}
