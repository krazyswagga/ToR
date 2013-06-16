package tor.server.plugin.RPlayer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import tor.server.plugin.ToR;

import tor.server.plugin.data.Attributes;
import tor.server.plugin.data.Skill;

public class RPlayer {

    ToR plugin;
    public FileConfiguration customConfig;
    public File customConfigFile;
    private int MaxMana;
    private int MaxHealth;
    private int health;
    public Map<Attributes, Integer> attributes = new EnumMap<Attributes, Integer>(Attributes.class);
    public Map<Skill, Integer> skillExp = new EnumMap<Skill, Integer>(Skill.class);
    public Map<Skill, Integer> skillLevels = new EnumMap<Skill, Integer>(Skill.class);

    public RPlayer(ToR plugin) {
        this.plugin = plugin;
    }

    public int getMana() {
        return customConfig.getInt("Mana.Mana");
    }

    public void setMana(int mana) {
       customConfig.set("Mana.Mana", mana);
    }

    public int getMaxMana() {
        return customConfig.getInt("Mana.maxMana");    
    }

    public void setMaxMana(int MaxMana) {
        customConfig.set("Mana.maxMana", MaxMana);   
    }

    public int getHealth() {
        return customConfig.getInt("Health.Health");
    }

    public void setHealth(int health) {
       customConfig.set("Health.Health", health);
    }

    public int getMaxHealth() {
        return customConfig.getInt("Health.maxHealth");
    }

    public void setMaxHealth(int MaxHealth) {
        customConfig.set("Health.maxHealth", MaxHealth);

    }

    public void getLevel(Skill skill) {
        skillLevels.get(skill);
    }

    public void getExp(Skill skill) {
        skillExp.get(skill);
    }

    public void addExp(Skill skill, int addValue) {
        skillExp.put(skill, skillExp.get(skill) + addValue);
    }

    public void getAttribute(Attributes att) {
        attributes.get(att);
    }

    public void createPlayerFile(String playerName, Player player) {

        File directory = new File("plugins/ToR/Data/" + playerName + "/");

        if (!directory.exists()) {
            directory.mkdirs();
        }
        customConfigFile = new File("plugins/ToR/Data/" + playerName + "/" + playerName + ".yml");
        customConfig = YamlConfiguration.loadConfiguration(customConfigFile);

        if (!customConfigFile.exists()) {
            try {
                customConfigFile.createNewFile();
                InputStream defConfigStream = plugin.getResource(playerName + ".yml");
                if (defConfigStream != null) {
                    YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                    customConfig.setDefaults(defConfig);
                }
                customConfig.set("Mana.maxMana", 100);
                customConfig.set("Mana.Mana", 100);
                customConfig.set("Health.maxHealth", 100);
                customConfig.set("Health.Health", 100);

                saveCustomConfig();
                
                player.sendMessage(ChatColor.DARK_PURPLE + "Welcome to Tales of Runebrire!");
            } catch (IOException e) {
                plugin.log.info("Error creating playerfile!");
            }
        }
    }
        public void saveCustomConfig() {
        if (customConfig == null || customConfigFile == null) {
            plugin.getLogger().log(Level.SEVERE, "No file was found by that name");
        }
        try {
            customConfig.save(customConfigFile);
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
        }
    }
}
