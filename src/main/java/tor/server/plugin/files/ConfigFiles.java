package tor.server.plugin.files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import tor.server.plugin.ToR;


public final class ConfigFiles {
	public FileConfiguration customConfig;
	public File customConfigFile;
	ToR plugin;
	public ConfigFiles(String playerName, String filename, ToR plugin, Player player){
		this.plugin = plugin;
		File directory = new File("plugins/ToR/Data/" + playerName + "/");
		if(!directory.exists()){
			directory.mkdirs();
		}
		customConfigFile = new File("plugins/ToR/Data/" + playerName + "/" + filename + ".yml");
		customConfig = YamlConfiguration.loadConfiguration(customConfigFile);

		if(!customConfigFile.exists()){
			try {
				customConfigFile.createNewFile();
				InputStream defConfigStream = plugin.getResource(filename + ".yml");
				if (defConfigStream != null) {
					YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
					customConfig.setDefaults(defConfig);
				}
				getCustomConfig().set("Mana.maxMana", 100);
                                getCustomConfig().set("Mana.getMana", 100);
                                getCustomConfig().set("Health.maxHealth", 100);
                                getCustomConfig().set("Health.getHealth", 100);


				saveCustomConfig();
				plugin.Rplayer.setMaxMana(getCustomConfig().getInt("Mana.MaxMana"));
                                plugin.Rplayer.setMana(getCustomConfig().getInt("Mana.getMana"));
                                plugin.Rplayer.setHealth(getCustomConfig().getInt("Health.getHealth"));
                                plugin.Rplayer.setMaxHealth(getCustomConfig().getInt("Health.maxHealth"));

				player.sendMessage(ChatColor.DARK_PURPLE + "Welcome to Tales of Runebrire!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public  FileConfiguration getCustomConfig(){
		return customConfig;
	}
	public void saveCustomConfig() {
		if (customConfig == null || customConfigFile == null) {
			plugin.getLogger().log(Level.SEVERE, "No file was found by that name");
		}
		try {
			getCustomConfig().save(customConfigFile);
		} catch (IOException ex) {
			plugin.getLogger().log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
		}
	}
}
