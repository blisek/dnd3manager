package com.blisek.dnd3manager.dnd3;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasa zawierająca kontekst, używany przez inne
 * narzędzia, udostępniający informacje o wszystkich,
 * w ramach tego kontekstu, dostępnych klasach, rasach,
 * atutach itp.
 * @author bartek
 *
 */
public enum Context {
	INSTANCE;
	
	public Map<String, AbstractEffect> getEffects() {
		return effects;
	}
	
	public Map<String, AbstractFeat> getFeats()	{
		return feats;
	}
	
	public Map<String, AbstractSpecialAbility> getSpecialAbilities() {
		return specab;
	}
	
	
	private Map<String, AbstractEffect> effects = new HashMap<String, AbstractEffect>();
	private Map<String, AbstractFeat> feats = new HashMap<String, AbstractFeat>();
	private Map<String, AbstractSpecialAbility> specab = new HashMap<String, AbstractSpecialAbility>();
}
