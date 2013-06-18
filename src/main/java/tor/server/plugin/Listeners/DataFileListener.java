package tor.server.plugin.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tor.server.plugin.RPlayer.RPlayer;

import tor.server.plugin.ToR;
import tor.server.plugin.data.Skill;

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
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                Rplayer.setMana(Rplayer.getMana() + Rplayer.getManaRegen());
                Rplayer.setHealth(Rplayer.getHealth() + Rplayer.getHealthRegen());
                e.getPlayer().sendMessage("Skills Level: " +Rplayer.skillLevels.get(Skill.DESTRUCTION));

            }
        }, 20L, 20L);



    }
}
