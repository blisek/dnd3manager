package com.blisek.dnd3manager.dnd3;

/**
 * Bazowa klasa dla umiejętności.
 * @author bartek
 *
 */
public abstract class AbstractSkill implements SystemObject {

	@Override
	public boolean isOnFor(CreatureModel model, Object... extraParams) {
		return model.getSkillsMap().containsKey(getSystemName());
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
	
	/**
	 * Informuje, czy daną umiejętność mogą używać tylko istoty
	 * wyszkolone w niej.
	 * @return true jeśli tylko wyszkolone.
	 */
	public abstract boolean trainedOnly();
	
	/**
	 * Informuje, czy model ma w danej umiejętności co najmniej 1 rangę.
	 * @param model model postaci.
	 * @param extraParams dodatkowe parametry.
	 * @return
	 */
	public boolean hasAtLeastOneRank(CreatureModel model, Object... extraParams) {
		Integer ranks = model.getSkillsMap().get(getSystemName());
		return (ranks != null) && (ranks >= 1);
	}
	
}
