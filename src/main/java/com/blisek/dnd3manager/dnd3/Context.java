package com.blisek.dnd3manager.dnd3;

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
	
	
	private Map<String, AbstractEffect> effects;
}
