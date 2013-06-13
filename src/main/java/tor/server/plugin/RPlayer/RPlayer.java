package tor.server.plugin.RPlayer;

import java.util.HashMap;
import java.util.Map;
import tor.server.plugin.ToR;

import tor.server.plugin.data.Attributes;
import tor.server.plugin.data.Skill;

public class RPlayer {
        ToR plugin;
	private int mana;
	private int MaxMana;
        private int MaxHealth;
	private int health;
	public Map<Attributes, Integer> attributes = new HashMap<Attributes, Integer>();
	public Map<Skill, Integer> skillExp = new HashMap<Skill, Integer>();
	public Map<Skill, Integer> skillLevels = new HashMap<Skill, Integer>();

	
	public RPlayer(ToR plugin){
            this.plugin = plugin;
	}
	public int getMana(){
		return mana;
	}
	public int setMana(int mana){
		this.mana = mana;

		if(mana > MaxMana){
			mana = MaxMana;
		}
		if(mana < 0){
			mana = 0;
		}
		return mana;
	}
	public int getMaxMana(){
		return MaxMana;
	}
	public void setMaxMana(int MaxMana){
		this.MaxMana = MaxMana;
	}
        public int getHealth(){
                return health;
        }
        public int setHealth(int health){
		this.health = health;

		if(health > MaxHealth){
			health = MaxHealth;
		}
		if(health < 0){
			health = 0;
		}
		return health;
	}
        public int getMaxHealth(int health){
            return MaxHealth;
        }
        public void setMaxHealth(int MaxHealth){
            this.MaxHealth = MaxHealth;
        }
	public void getLevel(Skill skill){
		skillLevels.get(skill);
	}
	public void getExp(Skill skill){
		skillExp.get(skill);
	}
	public void addExp(Skill skill, int addValue){
		skillExp.put(skill, skillExp.get(skill) + addValue);
	}
	public void getAttribute(Attributes att){
		attributes.get(att);
	}
}
