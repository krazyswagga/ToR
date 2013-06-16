package tor.server.plugin;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import tor.server.plugin.Listeners.DataFileListener;

public class ToR extends JavaPlugin {

    public static final Logger log = Logger.getLogger("Minecraft");
    public DataFileListener dl = new DataFileListener(this);

    @Override
    public void onEnable() {

        log.info("[ToR] Enabled = Alpha 1.0 =");
        // * Listeners * \\
        getServer().getPluginManager().registerEvents(new DataFileListener(this), this);
        
    }

    @Override
    public void onLoad() {
        // * Configs * \\
    }

    @Override
    public void onDisable() {
        log.info("[ToR] Disabled");

    }

   
}
