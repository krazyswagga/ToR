package tor.server.plugin.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tor.server.plugin.RPlayer.RPlayer;

import tor.server.plugin.ToR;

public class DataFileListener implements Listener {

    ToR plugin;
    RPlayer Rplayer;
    public DataFileListener(ToR plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Rplayer = new RPlayer(plugin);
        Rplayer.createPlayerFile(e.getPlayer().getName(), e.getPlayer());
        e.getPlayer().sendMessage("Player Mana: " + Rplayer.getMana());
        e.getPlayer().sendMessage("Player Health : " + Rplayer.getHealth());
    }
}
