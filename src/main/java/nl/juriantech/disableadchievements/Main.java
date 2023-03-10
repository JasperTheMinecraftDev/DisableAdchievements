package nl.juriantech.disableadchievements;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    private boolean showDisabledAchievementsMessage;
    private String disabledAchievementsMessage;
    private String[] ignoredAchievements;

    @Override
    public void onEnable() {
        getLogger().severe("Disable-achievements is now enabled!");
        getServer().getPluginManager().registerEvents(this, this);

        // Load configuration
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        showDisabledAchievementsMessage = config.getBoolean("show-disabled-achievements-message", true);
        disabledAchievementsMessage = config.getString("disabled-achievements-message", "Achievements are currently disabled!");
        ignoredAchievements = config.getStringList("ignored-achievements").toArray(new String[0]);
    }

    @Override
    public void onDisable() {
        getLogger().severe("Disable-achievements is now disabled!");
    }

    private boolean isIgnoredAchievement(String achievementName) {
        for (String ignored : ignoredAchievements) {
            if (achievementName.equals(ignored)) {
                return true;
            }
        }
        return false;
    }

    private void hideAchievements(World world) {
        world.setGameRuleValue("announceAdvancements", "false");
        getLogger().info("Achievements are now hidden for the world: " + world.getName() + ".");
    }

    /* Hide achievements for new worlds. */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onWorldLoad(WorldLoadEvent event) {
        hideAchievements(event.getWorld());
    }

    /* Show disabled achievements message to player. */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onAchievementCompleted(PlayerAdvancementDoneEvent event) {
        if (showDisabledAchievementsMessage && !isIgnoredAchievement(event.getAdvancement().getKey().toString())) {
            event.getPlayer().sendMessage(disabledAchievementsMessage);
        }
    }
}

