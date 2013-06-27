package tor.server.plugin;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;
import tor.server.plugin.Listeners.SkillsEXPListener;
import tor.server.plugin.Listeners.SkillsLVListener;

public class ToR extends JavaPlugin {

    public static final Logger log = Logger.getLogger("Minecraft");

    @Override
    public void onEnable() {

        log.info("[ToR] Enabled = Alpha 1.0 =");
        // * Listeners * \\
        getServer().getPluginManager().registerEvents(new SkillsEXPListener(this), this);
        getServer().getPluginManager().registerEvents(new SkillsLVListener(this), this);



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
