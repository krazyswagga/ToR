package tor.server.plugin.Listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import tor.server.plugin.ToR;
class SkillListeners implements Listener {
    ToR plugin;
    public SkillListeners(ToR plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void playerMineEvent(EntityDeathEvent e){
        Entity damager = e.getEntity().getKiller();
        if(damager instanceof Player){
            
        }
    }
    
}
