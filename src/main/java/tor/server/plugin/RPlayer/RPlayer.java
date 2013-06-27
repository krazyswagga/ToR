package tor.server.plugin.RPlayer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import tor.server.plugin.ToR;

import tor.server.plugin.data.Attributes;
import tor.server.plugin.data.Skill;

public class RPlayer {

    ToR plugin;
    public FileConfiguration customConfig;
    public File customConfigFile;
    public Map<Attributes, Integer> attributes = new HashMap<Attributes, Integer>();
    public Map<Skill, Integer> skillExp = new HashMap<Skill, Integer>();
    public Map<Skill, Integer> skillLevels = new HashMap<Skill, Integer>();

    public RPlayer(ToR plugin) {
        this.plugin = plugin;
    }

    public int getMana() {
        return customConfig.getInt("Mana.Mana");
    }

    public int getMaxMana() {
        return customConfig.getInt("Mana.maxMana");
    }

    public void setMana(int mana) {

        if (mana >= getMaxMana()) {
            mana = getMaxMana();
        }
        if (mana < 0) {
            mana = 0;
        }
        customConfig.set("Mana.Mana", mana);
        saveCustomConfig();

    }

    public void setMaxMana(int MaxMana) {
        customConfig.set("Mana.maxMana", MaxMana);
        saveCustomConfig();
    }

    public int getManaRegen() {
        return customConfig.getInt("Mana.manaRegen");


    }

    public void setManaRegen(int regen) {
        customConfig.set("Mana.manaRegen", regen);
        saveCustomConfig();
    }

    public int getHealth() {
        return customConfig.getInt("Health.Health");
    }

    public void setHealth(int health) {
        if (health >= getMaxHealth()) {
            health = getMaxHealth();
        }
        if (health < 0) {
            health = 0;
        }
        customConfig.set("Health.Health", health);
        saveCustomConfig();
    }

    public int getMaxHealth() {
        return customConfig.getInt("Health.maxHealth");
    }

    public void setMaxHealth(int MaxHealth) {
        customConfig.set("Health.maxHealth", MaxHealth);
        saveCustomConfig();
    }

    public int getHealthRegen() {
        return customConfig.getInt("Health.healthRegen");

    }

    public void setHealthRegen(int regen) {
        customConfig.set("Health.healthRegen", regen);
        saveCustomConfig();
    }

    public int getLevel(Skill skill) {
        return skillLevels.get(skill);
    }

    public void addLevel(Skill skill, int levelamount) {
        skillLevels.put(skill, skillLevels.get(skill) + levelamount);
        saveCustomConfig();

    }

    public void setLevel(Skill skill, int level) {
        skillLevels.put(skill, level);
        saveCustomConfig();
    }

    public int getExp(Skill skill) {
        return skillExp.get(skill);
    }

    public void addExp(Skill skill, int expamount) {
        skillExp.put(skill, skillExp.get(skill) + expamount);
        saveCustomConfig();
    }

    public void setExp(Skill skill, int expamount) {
        skillExp.put(skill, expamount);
        saveCustomConfig();
    }

    public void createPlayerFile(String playerName, Player player) {

        File directory = new File("plugins/ToR/Data/" + playerName + "/");

        if (!directory.exists()) {
            directory.mkdirs();
        }
        customConfigFile = new File("plugins/ToR/Data/" + playerName + "/" + playerName + ".yml");
        customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
        setSkillsInMapLv();
        setSkillInMapExp();
        if (!customConfigFile.exists()) {
            try {
                customConfigFile.createNewFile();
                InputStream defConfigStream = plugin.getResource(playerName + ".yml");
                if (defConfigStream != null) {
                    YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                    customConfig.setDefaults(defConfig);
                }
                customConfig.set("Mana.maxMana", 100);
                customConfig.set("Mana.Mana", 100);
                customConfig.set("Mana.manaRegen", 1);
                customConfig.set("Health.maxHealth", 100);
                customConfig.set("Health.Health", 100);
                customConfig.set("Health.healthRegen", 1);
                AllTheSkills();
                saveCustomConfig();

                player.sendMessage(ChatColor.DARK_PURPLE + "Welcome to Tales of Runebrire!");
            } catch (IOException e) {
                plugin.log.info("Error creating playerfile!");
            }
        }
    }

    public void saveCustomConfig() {
        if (customConfig == null || customConfigFile == null) {
            plugin.getLogger().log(Level.SEVERE, "No file was found by that name");
        }
        try {
            saveSkillsInConfig();
            customConfig.save(customConfigFile);
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
        }
    }

    private void AllTheSkills() {
        customConfig.set("Wisdom.Points", 10);
        //
        //
        customConfig.set("Wisdom.Destruction.exp", 0);
        customConfig.set("Wisdom.Destruction.level", 1);

        customConfig.set("Wisdom.Summoning", 0);
        customConfig.set("Wisdom.Summoning.level", 1);

        customConfig.set("Wisdom.Healing.exp", 0);
        customConfig.set("Wisdom.Healing.level", 1);

        customConfig.set("Wisdom.Illusion.exp", 0);
        customConfig.set("Wisdom.Illusion.level", 1);

        customConfig.set("Wisdom.Druidry.exp", 0);
        customConfig.set("Wisdom.Druidry.level", 1);
        //
        //
        customConfig.set("DarkMagic.Points", 10);
        //
        //
        customConfig.set("DarkMagic.Shadowmoor.exp", 0);
        customConfig.set("DarkMagic.Shadowmoor.level", 1);
        //
        //
        customConfig.set("Strength.Points", 10);
        //
        //
        customConfig.set("Strength.HandToHand.exp", 0);
        customConfig.set("Strength.HandToHand.level", 1);

        customConfig.set("Strength.Sword.exp", 0);
        customConfig.set("Strength.Sword.level", 1);

        customConfig.set("Strength.Axe.exp", 0);
        customConfig.set("Strength.Axe.level", 1);

        customConfig.set("Strength.Shield.exp", 0);
        customConfig.set("Strength.Shield.level", 1);

        customConfig.set("Strength.HeavyArmor.exp", 0);
        customConfig.set("Strength.HeavyArmor.level", 1);
        //
        //
        customConfig.set("Dexterity.Points", 10);
        //
        //
        customConfig.set("Dexterity.Accuracy.exp", 0);
        customConfig.set("Dexterity.Accuracy.level", 1);

        customConfig.set("Dexterity.Power.exp", 0);
        customConfig.set("Dexterity.Power.level", 1);

        customConfig.set("Dexterity.Tracking.exp", 0);
        customConfig.set("Dexterity.Tracking.level", 1);

        customConfig.set("Dexterity.Trapping.exp", 0);
        customConfig.set("Dexterity.Trapping.level", 1);

        customConfig.set("Dexterity.Taming.exp", 0);
        customConfig.set("Dexterity.Taming.level", 1);

        customConfig.set("Dexterity.LightArmor.exp", 0);
        customConfig.set("Dexterity.LightArmor.level", 1);
        //
        //
        customConfig.set("Resourcefulness.Points", 10);
        //
        //
        customConfig.set("Resourcefulness.Mining.exp", 0);
        customConfig.set("Resourcefulness.Mining.level", 1);

        customConfig.set("Resourcefulness.Woodcutting.exp", 0);
        customConfig.set("Resourcefulness.Woodcutting.level", 1);

        customConfig.set("Resourcefulness.Farming.exp", 0);
        customConfig.set("Resourcefulness.Farming.level", 1);

        customConfig.set("Resourcefulness.Herbalism.exp", 0);
        customConfig.set("Resourcefulness.Herbalism.level", 1);

        customConfig.set("Resourcefulness.Fishing.exp", 0);
        customConfig.set("Resourcefulness.Fishing.level", 1);
        //
        //
        customConfig.set("Agility.Points", 10);
        //
        //
        customConfig.set("Agility.Athleticism.exp", 0);
        customConfig.set("Agility.Athleticism.level", 1);

        customConfig.set("Agility.Sneaking.exp", 0);
        customConfig.set("Agility.Sneaking.level", 1);

        customConfig.set("Agility.Lockpicking.exp", 0);
        customConfig.set("Agility.Lockpicking.level", 1);

        customConfig.set("Resourcefulness.Pickpocketing.exp", 0);
        customConfig.set("Agility.Pickpocketing.level", 1);

        customConfig.set("Agility.Acrobatics.exp", 0);
        customConfig.set("Agility.Acrobatics.level", 1);
        //
        //
        customConfig.set("Charisma.Points", 10);
        //
        //
        customConfig.set("Charisma.Humour.exp", 0);
        customConfig.set("Charisma.Humour.level", 1);

        customConfig.set("Charisma.Mercantile.exp", 0);
        customConfig.set("Charisma.Mercantile.level", 1);

        customConfig.set("Charisma.Charm.exp", 0);
        customConfig.set("Charisma.Charm.level", 1);

        customConfig.set("Charisma.Music.exp", 0);
        customConfig.set("Charisma.Music.level", 1);
        //
        //

    }

    private void setSkillsInMapLv() {
        skillLevels.put(Skill.DRUIDRY, customConfig.getInt("Wisdom.Druidry.level"));
        skillLevels.put(Skill.DESTRUCTION, customConfig.getInt("Wisdom.Destruction.level"));
        skillLevels.put(Skill.SUMMONING, customConfig.getInt("Wisdom.Summoning.level"));
        skillLevels.put(Skill.HEALING, customConfig.getInt("Wisdom.Healing.level"));
        skillLevels.put(Skill.ILLUSION, customConfig.getInt("Wisdom.Illusion.level"));
        skillLevels.put(Skill.SHADOWMOOR, customConfig.getInt("DarkMagic.Shadowmoor.level"));
        skillLevels.put(Skill.HANDTOHAND, customConfig.getInt("Strength.HandToHand.level"));
        skillLevels.put(Skill.SWORD, customConfig.getInt("Strength.Sword.level"));
        skillLevels.put(Skill.AXE, customConfig.getInt("Strength.Axe.level"));
        skillLevels.put(Skill.SHIELD, customConfig.getInt("Strength.Shield.level"));
        skillLevels.put(Skill.HEAVYARMOR, customConfig.getInt("Strength.HeavyArmor.level"));
        skillLevels.put(Skill.ACCURACY, customConfig.getInt("Dexterity.Accuracy.level"));
        skillLevels.put(Skill.POWER, customConfig.getInt("Dexterity.Power.level"));
        skillLevels.put(Skill.TRACKING, customConfig.getInt("Dexterity.Tracking.level"));
        skillLevels.put(Skill.TRAPPING, customConfig.getInt("Dexterity.Trapping.level"));
        skillLevels.put(Skill.TAMING, customConfig.getInt("Dexterity.Taming.level"));
        skillLevels.put(Skill.LIGHTARMOR, customConfig.getInt("Dexterity.LightArmor.level"));
        skillLevels.put(Skill.ATHLETICISM, customConfig.getInt("Resourcefulness.Athleticism.level"));
        skillLevels.put(Skill.SNEAKING, customConfig.getInt("Resourcefulness.Sneaking.level"));
        skillLevels.put(Skill.LOCKPICKING, customConfig.getInt("Resourcefulness.Lockpicking.level"));
        skillLevels.put(Skill.PICKPOCKETING, customConfig.getInt("Resourcefulness.Pickpocketing.level"));
        skillLevels.put(Skill.ACROBATICS, customConfig.getInt("Resourcefulness.Acrobatics.level"));
        skillLevels.put(Skill.HUMOR, customConfig.getInt("Charisma.Humour.level"));
        skillLevels.put(Skill.MERCANTILE, customConfig.getInt("Charisma.Mercantile.level"));
        skillLevels.put(Skill.CHARM, customConfig.getInt("Charisma.Charm.level"));
        skillLevels.put(Skill.MUSIC, customConfig.getInt("Charisma.Music.level"));
        skillLevels.put(Skill.HERBALISM, customConfig.getInt("Charisma.Herbalism.Music.level"));
        skillLevels.put(Skill.MINING, customConfig.getInt("Resourcefulness.Mining.level"));


    }

    private void setSkillInMapExp() {
        skillExp.put(Skill.DRUIDRY, customConfig.getInt("Wisdom.Druidry.exp"));
        skillExp.put(Skill.DESTRUCTION, customConfig.getInt("Wisdom.Destruction.exp"));
        skillExp.put(Skill.SUMMONING, customConfig.getInt("Wisdom.Summoning.exp"));
        skillExp.put(Skill.HEALING, customConfig.getInt("Wisdom.Healing.exp"));
        skillExp.put(Skill.ILLUSION, customConfig.getInt("Wisdom.Illusion.exp"));
        skillExp.put(Skill.SHADOWMOOR, customConfig.getInt("DarkMagic.Shadowmoor.exp"));
        skillExp.put(Skill.HANDTOHAND, customConfig.getInt("Strength.HandToHand.exp"));
        skillExp.put(Skill.SWORD, customConfig.getInt("Strength.Sword.exp"));
        skillExp.put(Skill.AXE, customConfig.getInt("Strength.Axe.exp"));
        skillExp.put(Skill.SHIELD, customConfig.getInt("Strength.Shield.exp"));
        skillExp.put(Skill.HEAVYARMOR, customConfig.getInt("Strength.HeavyArmor.exp"));
        skillExp.put(Skill.ACCURACY, customConfig.getInt("Dexterity.Accuracy.exp"));
        skillExp.put(Skill.POWER, customConfig.getInt("Dexterity.Power.exp"));
        skillExp.put(Skill.TRACKING, customConfig.getInt("Dexterity.Tracking.exp"));
        skillExp.put(Skill.TRAPPING, customConfig.getInt("Dexterity.Trapping.exp"));
        skillExp.put(Skill.TAMING, customConfig.getInt("Dexterity.Taming.exp"));
        skillExp.put(Skill.LIGHTARMOR, customConfig.getInt("Dexterity.LightArmor.exp"));
        skillExp.put(Skill.ATHLETICISM, customConfig.getInt("Resourcefulness.Athleticism.exp"));
        skillExp.put(Skill.SNEAKING, customConfig.getInt("Resourcefulness.Sneaking.exp"));
        skillExp.put(Skill.LOCKPICKING, customConfig.getInt("Resourcefulness.Lockpicking.exp"));
        skillExp.put(Skill.PICKPOCKETING, customConfig.getInt("Resourcefulness.Pickpocketing.exp"));
        skillExp.put(Skill.ACROBATICS, customConfig.getInt("Resourcefulness.Acrobatics.exp"));
        skillExp.put(Skill.HUMOR, customConfig.getInt("Charisma.Humour.exp"));
        skillExp.put(Skill.MERCANTILE, customConfig.getInt("Charisma.Mercantile.exp"));
        skillExp.put(Skill.CHARM, customConfig.getInt("Charisma.Charm.exp"));
        skillExp.put(Skill.MUSIC, customConfig.getInt("Charisma.Music.exp"));
        skillExp.put(Skill.HERBALISM, customConfig.getInt("Charisma.Herbalism.exp"));
        skillExp.put(Skill.MINING, customConfig.getInt("Resourcefulness.Mining.exp"));
    }

    private void saveSkillsInConfig() {
        //
        //
        customConfig.set("Wisdom.Destruction.exp", skillExp.get(Skill.DESTRUCTION));
        customConfig.set("Wisdom.Destruction.level", skillLevels.get(Skill.DESTRUCTION));

        customConfig.set("Wisdom.Summoning", skillExp.get(Skill.SUMMONING));
        customConfig.set("Wisdom.Summoning.level", skillLevels.get(Skill.SUMMONING));

        customConfig.set("Wisdom.Healing.exp", skillExp.get(Skill.HEALING));
        customConfig.set("Wisdom.Healing.level", skillLevels.get(Skill.HEALING));

        customConfig.set("Wisdom.Illusion.exp", skillExp.get(Skill.ILLUSION));
        customConfig.set("Wisdom.Illusion.level", skillLevels.get(Skill.ILLUSION));

        customConfig.set("Wisdom.Druidry.exp", skillExp.get(Skill.DRUIDRY));
        customConfig.set("Wisdom.Druidry.level", skillLevels.get(Skill.DRUIDRY));
        //
        //
        customConfig.set("DarkMagic.Shadowmoor.exp", skillExp.get(Skill.SHADOWMOOR));
        customConfig.set("DarkMagic.Shadowmoor.level", skillLevels.get(Skill.SHADOWMOOR));
        //
        //
        customConfig.set("Strength.HandToHand.exp", skillExp.get(Skill.HANDTOHAND));
        customConfig.set("Strength.HandToHand.level", skillLevels.get(Skill.HANDTOHAND));

        customConfig.set("Strength.Sword.exp", skillExp.get(Skill.SWORD));
        customConfig.set("Strength.Sword.level", skillLevels.get(Skill.SWORD));

        customConfig.set("Strength.Axe.exp", skillExp.get(Skill.AXE));
        customConfig.set("Strength.Axe.level", skillLevels.get(Skill.AXE));

        customConfig.set("Strength.Shield.exp", skillExp.get(Skill.SHIELD));
        customConfig.set("Strength.Shield.level", skillLevels.get(Skill.SHIELD));

        customConfig.set("Strength.HeavyArmor.exp", skillExp.get(Skill.HEAVYARMOR));
        customConfig.set("Strength.HeavyArmor.level", skillLevels.get(Skill.HEAVYARMOR));
        //
        //
        customConfig.set("Dexterity.Accuracy.exp", skillExp.get(Skill.ACCURACY));
        customConfig.set("Dexterity.Accuracy.level", skillLevels.get(Skill.ACCURACY));

        customConfig.set("Dexterity.Power.exp", skillExp.get(Skill.POWER));
        customConfig.set("Dexterity.Power.level", skillLevels.get(Skill.POWER));

        customConfig.set("Dexterity.Tracking.exp", skillExp.get(Skill.TRACKING));
        customConfig.set("Dexterity.Tracking.level", skillLevels.get(Skill.TRACKING));

        customConfig.set("Dexterity.Trapping.exp", skillExp.get(Skill.TRAPPING));
        customConfig.set("Dexterity.Trapping.level", skillLevels.get(Skill.TRAPPING));

        customConfig.set("Dexterity.Taming.exp", skillExp.get(Skill.TAMING));
        customConfig.set("Dexterity.Taming.level", skillLevels.get(Skill.TAMING));

        customConfig.set("Dexterity.LightArmor.exp", skillExp.get(Skill.LIGHTARMOR));
        customConfig.set("Dexterity.LightArmor.level", skillLevels.get(Skill.LIGHTARMOR));
        //
        //
        customConfig.set("Resourcefulness.Mining.exp", skillExp.get(Skill.MINING));
        customConfig.set("Resourcefulness.Mining.level", skillLevels.get(Skill.MINING));

        customConfig.set("Resourcefulness.Woodcutting.exp", skillExp.get(Skill.WOODCUTTING));
        customConfig.set("Resourcefulness.Woodcutting.level", skillLevels.get(Skill.WOODCUTTING));

        customConfig.set("Resourcefulness.Farming.exp", skillExp.get(Skill.FARMING));
        customConfig.set("Resourcefulness.Farming.level", skillLevels.get(Skill.FARMING));

        customConfig.set("Resourcefulness.Herbalism.exp", skillExp.get(Skill.HERBALISM));
        customConfig.set("Resourcefulness.Herbalism.level", skillLevels.get(Skill.HERBALISM));

        customConfig.set("Resourcefulness.Fishing.exp", skillExp.get(Skill.FISHING));
        customConfig.set("Resourcefulness.Fishing.level", skillLevels.get(Skill.FISHING));
        //
        //
        customConfig.set("Agility.Athleticism.exp", skillExp.get(Skill.ATHLETICISM));
        customConfig.set("Agility.Athleticism.level", skillLevels.get(Skill.ATHLETICISM));

        customConfig.set("Agility.Sneaking.exp", skillExp.get(Skill.SNEAKING));
        customConfig.set("Agility.Sneaking.level", skillLevels.get(Skill.SNEAKING));

        customConfig.set("Agility.Lockpicking.exp", skillExp.get(Skill.LOCKPICKING));
        customConfig.set("Agility.Lockpicking.level", skillLevels.get(Skill.LOCKPICKING));

        customConfig.set("Resourcefulness.Pickpocketing.exp", skillExp.get(Skill.PICKPOCKETING));
        customConfig.set("Agility.Pickpocketing.level", skillLevels.get(Skill.PICKPOCKETING));

        customConfig.set("Agility.Acrobatics.exp", skillExp.get(Skill.ACROBATICS));
        customConfig.set("Agility.Acrobatics.level", skillLevels.get(Skill.ACROBATICS));
        //
        //
        customConfig.set("Charisma.Humour.exp", skillExp.get(Skill.HUMOR));
        customConfig.set("Charisma.Humour.level", skillLevels.get(Skill.HUMOR));

        customConfig.set("Charisma.Mercantile.exp", skillExp.get(Skill.MERCANTILE));
        customConfig.set("Charisma.Mercantile.level", skillLevels.get(Skill.MERCANTILE));

        customConfig.set("Charisma.Charm.exp", skillExp.get(Skill.CHARM));
        customConfig.set("Charisma.Charm.level", skillLevels.get(Skill.CHARM));

        customConfig.set("Charisma.Music.exp", skillExp.get(Skill.MUSIC));
        customConfig.set("Charisma.Music.level", skillLevels.get(Skill.MUSIC));
        //
        //

    }
}
