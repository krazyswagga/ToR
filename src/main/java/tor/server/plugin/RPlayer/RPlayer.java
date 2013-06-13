package tor.server.plugin.RPlayer;

import java.util.HashMap;
import java.util.Map;

import tor.server.plugin.data.Attributes;
import tor.server.plugin.data.Skill;

public class RPlayer {
	private int mana;
	private int MaxMana;
	
	public Map<Skill, Integer> attributes = new HashMap<Skill, Integer>();
	public Map<Skill, Integer> skillExp = new HashMap<Skill, Integer>();
	public Map<Skill, Integer> skillLevels = new HashMap<Skill, Integer>();

	
	public RPlayer(){
		
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
