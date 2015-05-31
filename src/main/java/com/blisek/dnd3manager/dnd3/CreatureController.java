package com.blisek.dnd3manager.dnd3;

public class CreatureController implements MapObservator {
	private CreatureModel model;

	public CreatureController(CreatureModel model) {
		if(model == null)
			throw new NullPointerException("model");
		this.model = model;
	}
	
	/**
	 * Zwraca modyfikator z Siły.
	 * @param recalculate wylicza modyfikator ponownie.
	 * @return modyfikator z siły.
	 */
	public int getStrengthMod(boolean recalculate) {
		if(strMod == null || recalculate)
			strMod = calculateMod(StringConstants.STRENGTH);
		return strMod;
	}
	
	/**
	 * Zwraca modyfikator ze Zręczności.
	 * @param recalculate wylicza modyfikator ponownie.
	 * @return modyfikator.
	 */
	public int getDexterityMod(boolean recalculate) {
		if(dexMod == null || recalculate)
			dexMod = calculateMod(StringConstants.DEXTERITY);
		return dexMod;
	}
	
	/**
	 * Zwraca modyfikator z Budowy.
	 * @param recalculate wylicza modyfikator ponownie.
	 * @return modyfikator.
	 */
	public int getConstitutionMod(boolean recalculate) {
		if(conMod == null || recalculate)
			conMod = calculateMod(StringConstants.CONSTITUTION);
		return conMod;
	}
	
	/**
	 * Zwraca modyfikator z Inteligencji.
	 * @param recalculate wylicza modyfikator ponownie.
	 * @return modyfikator.
	 */
	public int getIntelligenceMod(boolean recalculate) {
		if(intMod == null || recalculate)
			intMod = calculateMod(StringConstants.INTELLIGENCE);
		return intMod;

	}
	
	/**
	 * Zwraca modyfikator z Roztropności.
	 * @param recalculate wylicza modyfikator ponownie.
	 * @return modyfikator.
	 */
	public int getWisdomMod(boolean recalculate) {
		if(wisMod == null || recalculate)
			wisMod = calculateMod(StringConstants.WISDOM);
		return wisMod;

	}
	
	/**
	 * Zwraca modyfikator z Charyzmy.
	 * @param recalculate wylicza modyfikator ponownie.
	 * @return modyfikator.
	 */
	public int getCharismaMod(boolean recalculate) {
		if(chaMod == null || recalculate)
			chaMod = calculateMod(StringConstants.CHARISMA);
		return chaMod;

	}
	
	
	@Override
	public void onNewKeyPut(Object sender, Object key, Object oldValue,
			Object newValue) {
		String k = (String)key;
		if(k.startsWith(StringConstants.STRENGTH)) {
			getStrengthMod(true);
		}
		else if(k.startsWith(StringConstants.DEXTERITY)) {
			getDexterityMod(true);
		}
		else if(k.startsWith(StringConstants.CONSTITUTION)) {
			getConstitutionMod(true);
		}
		else if(k.startsWith(StringConstants.INTELLIGENCE)) {
			getIntelligenceMod(true);
		}
		else if(k.startsWith(StringConstants.WISDOM)) {
			getWisdomMod(true);
		}
		else if(k.startsWith(StringConstants.CHARISMA)) {
			getCharismaMod(true);
		}
	}


	@Override
	public void onKeyReplaced(Object sender, Object key,
			Object oldValue, Object newValue) {
		String k = (String)key;
		if(k.startsWith(StringConstants.STRENGTH)) {
			getStrengthMod(true);
		}
		else if(k.startsWith(StringConstants.DEXTERITY)) {
			getDexterityMod(true);
		}
		else if(k.startsWith(StringConstants.CONSTITUTION)) {
			getConstitutionMod(true);
		}
		else if(k.startsWith(StringConstants.INTELLIGENCE)) {
			getIntelligenceMod(true);
		}
		else if(k.startsWith(StringConstants.WISDOM)) {
			getWisdomMod(true);
		}
		else if(k.startsWith(StringConstants.CHARISMA)) {
			getCharismaMod(true);
		}
	}


	@Override
	public void onKeyRemoved(Object sender, Object key, Object oldValue) {
		String k = (String)key;
		if(k.startsWith(StringConstants.STRENGTH)) {
			getStrengthMod(true);
		}
		else if(k.startsWith(StringConstants.DEXTERITY)) {
			getDexterityMod(true);
		}
		else if(k.startsWith(StringConstants.CONSTITUTION)) {
			getConstitutionMod(true);
		}
		else if(k.startsWith(StringConstants.INTELLIGENCE)) {
			getIntelligenceMod(true);
		}
		else if(k.startsWith(StringConstants.WISDOM)) {
			getWisdomMod(true);
		}
		else if(k.startsWith(StringConstants.CHARISMA)) {
			getCharismaMod(true);
		}
	}

	/**
	 * Sumuje wszystkie wartości przypisane do kluczy modelu postaci, które zaczynają się od prefix.
	 * @param prefix prefiks nazw kluczy.
	 * @return suma wszystkich wartości spełniających warunek.
	 */
	private int calculateMod(String prefix) {
		int sum = model.entrySet().parallelStream()
				.filter((entry) -> entry.getKey().startsWith(prefix))
				.mapToInt((entry) -> { Object obj = entry.getValue(); return obj == null ? 0 : (int)obj; })
				.sum();	
		return sum < 10 ? (sum - 11) / 2 : (sum - 10) / 2;
	}


	private Integer strMod, dexMod, conMod, intMod, wisMod, chaMod;
}
