package com.blisek.dnd3manager.dnd3;

import java.util.Map;

/**
 * Bazowa klasa dla umiejętności.
 * @author bartek
 *
 */
public abstract class AbstractSkill implements SystemObject {

	@Override
	public boolean isOnFor(CreatureModel model, Map<String, Object> extraParams) {
		return model.getSkillsMap().containsKey(getSystemName());
	}

	// domyślnie po przypisaniu do słownika, rangi ustawiane są na 0
	@Override
	public void turnOnFor(CreatureModel model, Map<String, Object> extraParams) {
		Object ranks = extraParams.get(StringConstants.P_RANKS_FOR_THIS_SKILL);
		model.getSkillsMap().put(getSystemName(), ranks == null ? 0 : (int)ranks);
	}

	@Override
	public void turnOffFor(CreatureModel model, Map<String, Object> extraParams) {
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
	 * Obowiązuje kara do testów z pancerza.
	 * @return true jeśli należy doliczyć karę do testów z pancerza.
	 */
	public boolean armorCheckPenalty() {
		return false;
	}
	
	/**
	 * Informuje, czy model ma w danej umiejętności co najmniej 1 rangę.
	 * @param model model postaci.
	 * @param extraParams dodatkowe parametry.
	 * @return
	 */
	public boolean hasAtLeastOneRank(CreatureModel model, Map<String, Object> extraParams) {
		Integer ranks = model.getSkillsMap().get(getSystemName());
		return (ranks != null) && (ranks >= 1);
	}
	
}
