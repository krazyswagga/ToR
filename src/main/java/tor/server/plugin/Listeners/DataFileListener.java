package tor.server.plugin.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tor.server.plugin.ToR;
import tor.server.plugin.files.ConfigFiles;

public class DataFileListener implements Listener {
	ToR plugin;
	public DataFileListener(ToR plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		ConfigFiles file = new ConfigFiles(e.getPlayer().getName(), "Skills" , plugin, e.getPlayer());
	}

}
