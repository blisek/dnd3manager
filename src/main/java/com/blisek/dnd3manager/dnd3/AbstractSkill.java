package com.blisek.dnd3manager.dnd3;

/**
 * Bazowa klasa dla umiejętności.
 * @author bartek
 *
 */
public abstract class AbstractSkill implements SystemObject {

	@Override
	public boolean isOnFor(CreatureModel model, Object... extraParams) {
		return model.containsKey(getSystemName());
	}

	@Override
	public void turnOnFor(CreatureModel model, Object... extraParams) {
		model.getSkillsMap().put(getSystemName(), extraParams[0] instanceof Integer ? (int)extraParams[0] : 1);
	}

	@Override
	public void turnOffFor(CreatureModel model, Object... extraParams) {
		model.getSkillsMap().remove(getSystemName());
	}
	
	/**
	 * Zwraca odpowiedni prefix atrybutu (z klasy StringConstants).
	 * @return łańcuch znaków z prefiksem atrybutu np. str, dex itd.
	 */
	public abstract String getKeyAbilityPrefix();
	
}
