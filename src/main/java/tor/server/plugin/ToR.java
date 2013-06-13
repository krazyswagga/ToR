package tor.server.plugin;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import tor.server.plugin.Listeners.DataFileListener;
import tor.server.plugin.RPlayer.RPlayer;



public class ToR extends JavaPlugin {
	
	public RPlayer Rplayer = new RPlayer();
	public Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable(){
		
		log.info("[ToR] Enabled = Alpha 1.0 =");
		// * Listeners * \\
		getServer().getPluginManager().registerEvents(new DataFileListener(this), this);	

	}
	public void onLoad(){
		// * Configs * \\

	}
	public void onDisable(){
		log.info("[ToR] Disabled");

	}
}
