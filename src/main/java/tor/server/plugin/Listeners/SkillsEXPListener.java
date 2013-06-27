package tor.server.plugin.Listeners;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import tor.server.plugin.RPlayer.RPlayer;

import tor.server.plugin.ToR;
import tor.server.plugin.data.Skill;

public class SkillsEXPListener implements Listener {

    ToR plugin;
    RPlayer Rplayer;
    private Inventory trapInv;

    public SkillsEXPListener(ToR plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {
        Rplayer = new RPlayer(plugin);
        Rplayer.createPlayerFile(e.getPlayer().getName(), e.getPlayer());
        trapInv = Bukkit.getServer().createInventory(e.getPlayer(), 9);
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                Rplayer.setMana(Rplayer.getMana() + Rplayer.getManaRegen());
                Rplayer.setHealth(Rplayer.getHealth() + Rplayer.getHealthRegen());
            }
        }, 20L, 20L);

    }

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent e) {
        Player killer = e.getEntity().getKiller();
        Player hurt = e.getEntity();
        if (hurt instanceof Player && killer instanceof Player) {
            ItemStack item = killer.getItemInHand();
            Rplayer.addExp(Skill.HANDTOHAND, 10);
            killer.sendMessage(ChatColor.YELLOW + "Earned 10xp points for Hand To Hand");
            if (item.getType() == Material.BOW) {
                Rplayer.addExp(Skill.POWER, 10);
                killer.sendMessage(ChatColor.YELLOW + "Earned 10xp points for Power");
            }
            if (item.getType() == Material.WOOD_SWORD || item.getType() == Material.STONE_SWORD || item.getType() == Material.IRON_SWORD || item.getType() == Material.DIAMOND_SWORD) {
                Rplayer.addExp(Skill.SWORD, 10);
                killer.sendMessage(ChatColor.YELLOW + "Earned 10xp points for Sword");
            }
        }

    }

    @EventHandler
    public void mobDeathEvent(EntityDeathEvent e) {
        Player killer = e.getEntity().getKiller();
        Entity hurt = e.getEntity();
        if (killer instanceof Player && hurt.getType() != EntityType.PLAYER) {
            ItemStack item = killer.getItemInHand();
            DamageCause deathCause = hurt.getLastDamageCause().getCause();
            if (deathCause == DamageCause.PROJECTILE) {
                killer.sendMessage(ChatColor.YELLOW + "Earned 10xp points for Power");
                Rplayer.addExp(Skill.POWER, 10);
            }
            if (item.getType() == Material.WOOD_SWORD || item.getType() == Material.STONE_SWORD || item.getType() == Material.IRON_SWORD || item.getType() == Material.DIAMOND_SWORD) {
                Rplayer.addExp(Skill.SWORD, 10);
                killer.sendMessage(ChatColor.YELLOW + "Earned 10xp points for Sword");
            }
        }
    }

    @EventHandler
    public void playerDmgEvent(EntityDamageEvent e) {
        Entity entity = e.getEntity();

    }

    @EventHandler
    public void blockPlaceEvent(final BlockPlaceEvent e) {
        final Block blockPlaced = e.getBlockPlaced();
        if (blockPlaced.getType() == Material.SPONGE) {
            e.getPlayer().sendMessage(ChatColor.BOLD.GREEN + "You have placed a trap!");

            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                @Override
                public void run() {
                   // Random random = new Random();
                    //    int randomNumber = random.nextInt(300) + 1;
                    //   int randomGuess = random.nextInt(100) + 1;
                    for (Entity en : blockPlaced.getWorld().getEntities()) {
                        if (en.getType() != EntityType.PLAYER) {
                            double distance = en.getLocation().distance(blockPlaced.getLocation());
                            if (distance <= 10) {
                                Entity entity = en;
                                entity.remove();
                                trapInv.addItem(new ItemStack(Material.MONSTER_EGG, 1, (short) entity.getType().getTypeId()));
                                e.getPlayer().sendMessage(ChatColor.BOLD.GREEN + "An Entity has been caught!");
                                e.getPlayer().sendMessage(ChatColor.YELLOW + "Earned 10xp points for Trapping");

                                Rplayer.addExp(Skill.TRACKING, 10);
                            }

                        }

                    }
                }
            }, 20L, 20L);
        }
    }

    @EventHandler
    public void playerInteract(PlayerInteractEvent e) {

        Action action = e.getAction();
        Block block = e.getClickedBlock();
        if (block.getType() == Material.SPONGE) {
            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                e.getPlayer().openInventory(trapInv);
            }
        }
    }

    @EventHandler
    public void playerTaming(EntityTameEvent e) {
        Player player = (Player) e.getOwner();
        Rplayer.addExp(Skill.TRACKING, 10);
        player.sendMessage(ChatColor.YELLOW + "Earned 10xp points for Taming");
    }

    @EventHandler
    public void playerRunEvent(PlayerMoveEvent e) {
        int fromX = (int) e.getFrom().getX();
        int fromY = (int) e.getFrom().getY();
        int fromZ = (int) e.getFrom().getZ();
        int toX = (int) e.getTo().getX();
        int toY = (int) e.getTo().getY();
        int toZ = (int) e.getTo().getZ();

        if (fromX != toX || fromZ != toZ || fromY != toY) {
            Rplayer.addExp(Skill.TRACKING, 10);
            e.getPlayer().sendMessage(ChatColor.YELLOW + "Earned 10xp points for Athleticism");
        }
    }

    @EventHandler
    public void playerMine(BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.IRON_ORE || e.getBlock().getType() == Material.GOLD_ORE || e.getBlock().getType() == Material.DIAMOND_ORE) {
            Rplayer.addExp(Skill.MINING, 10);
            e.getPlayer().sendMessage(ChatColor.YELLOW + "Earned 10xp points for Mining");
        }
    }
 
}