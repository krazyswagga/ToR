package tor.server.plugin.Listeners;

import org.bukkit.Bukkit;
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
    public void onPlayerJoin(final PlayerJoinEvent e) {
        Rplayer = new RPlayer(plugin);
        Rplayer.createPlayerFile(e.getPlayer().getName(), e.getPlayer());
        Rplayer.setMana(0);
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                    Rplayer.setMana(Rplayer.getMana() + 10);
                    e.getPlayer().sendMessage("Player Mana: " + Rplayer.getMana());
                    e.getPlayer().sendMessage("Player Health : " + Rplayer.getHealth());
                
            }
        }, 10L, 20L);



    }
}
