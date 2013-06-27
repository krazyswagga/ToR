package tor.server.plugin.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import tor.server.plugin.RPlayer.RPlayer;

import tor.server.plugin.ToR;

public class SkillsLVListener implements Listener {

    ToR plugin;
    RPlayer Rplayer;

    public SkillsLVListener(ToR plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {
        Rplayer = new RPlayer(plugin);
        Rplayer.createPlayerFile(e.getPlayer().getName(), e.getPlayer());
    }

    @EventHandler
    public void entityDamage(EntityDamageByEntityEvent e) {
        
    }
}
