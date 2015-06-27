package com.blisek.dnd3manager.dnd3;

import java.util.HashMap;
import java.util.Map;

public class CreatureController implements MapObservator, FlagObserver {
	private CreatureModel model;

	public CreatureController(CreatureModel model) {
		if(model == null)
			throw new NullPointerException("model");
		this.model = model;
		model.addObservator(this);
		model.addFlagObserver(this);
	}
	
	/**
	 * Zwraca modyfikator z Siły.
	 * @param recalculate wylicza modyfikator ponownie.
	 * @return modyfikator z siły.
	 */
	public int getStrengthMod(boolean recalculate) {
		if(strMod == null || recalculate)
			strMod = CreatureHelper.calculateMod(model, StringConstants.STRENGTH);
		return strMod;
	}
	
	/**
	 * Zwraca modyfikator ze Zręczności.
	 * @param recalculate wylicza modyfikator ponownie.
	 * @return modyfikator.
	 */
	public int getDexterityMod(boolean recalculate) {
		if(dexMod == null || recalculate)
			dexMod = CreatureHelper.calculateMod(model, StringConstants.DEXTERITY);
		return dexMod;
	}
	
	/**
	 * Zwraca modyfikator z Budowy.
	 * @param recalculate wylicza modyfikator ponownie.
	 * @return modyfikator.
	 */
	public int getConstitutionMod(boolean recalculate) {
		if(conMod == null || recalculate)
			conMod = CreatureHelper.calculateMod(model, StringConstants.CONSTITUTION);
		return conMod;
	}
	
	/**
	 * Zwraca modyfikator z Inteligencji.
	 * @param recalculate wylicza modyfikator ponownie.
	 * @return modyfikator.
	 */
	public int getIntelligenceMod(boolean recalculate) {
		if(intMod == null || recalculate)
			intMod = CreatureHelper.calculateMod(model, StringConstants.INTELLIGENCE);
		return intMod;

	}
	
	/**
	 * Zwraca modyfikator z Roztropności.
	 * @param recalculate wylicza modyfikator ponownie.
	 * @return modyfikator.
	 */
	public int getWisdomMod(boolean recalculate) {
		if(wisMod == null || recalculate)
			wisMod = CreatureHelper.calculateMod(model, StringConstants.WISDOM);
		return wisMod;

	}
	
	/**
	 * Zwraca modyfikator z Charyzmy.
	 * @param recalculate wylicza modyfikator ponownie.
	 * @return modyfikator.
	 */
	public int getCharismaMod(boolean recalculate) {
		if(chaMod == null || recalculate)
			chaMod = CreatureHelper.calculateMod(model, StringConstants.CHARISMA);
		return chaMod;

	}
	
	/**
	 * Zwraca, lub wylicza jeśli jest to pierwsze wywołanie metody, szybkość postaci.
	 * @param recalculate wymusza ponowne obliczenie szybkości postaci.
	 * @return szybkość postaci.
	 */
	public float getSpeed(boolean recalculate) {
		return (float)getDoubleValue(StringConstants.SPEED, recalculate);
	}
	
	/**
	 * Zwraca, bądź wylicza jeżeli jest to pierwsze wywołanie metody, wartość rzutu
	 * obronnego na wytrwałość.
	 * @param recalculate wymusza ponowne obliczenie wartości.
	 * @return wartość tego rzutu obronnego.
	 */
	public int getFortitude(boolean recalculate) {
		return getIntegerValue(StringConstants.FORTITUDE, recalculate);
	}
	
	/**
	 * Zwraca, bądź wylicza jeżeli jest to pierwsze wywołanie metody, wartość rzutu
	 * obronnego na refleks.
	 * @param recalculate wymusza ponowne obliczenie wartości.
	 * @return wartość tego rzutu obronnego.
	 */
	public int getReflex(boolean recalculate) {
		return getIntegerValue(StringConstants.REFLEX, recalculate);
	}
	
	/**
	 * Zwraca, bądź wylicza jeżeli jest to pierwsze wywołanie metody, wartość rzutu
	 * obronnego na wolę.
	 * @param recalculate wymusza ponowne obliczenie wartości.
	 * @return wartość tego rzutu obronnego.
	 */
	public int getWill(boolean recalculate) {
		return getIntegerValue(StringConstants.WILL, recalculate);
	}
	
	private int getIntegerValue(String prefix, boolean recalculate) {
		if(recalculate) {
			int sum = CreatureHelper.sumParamsBeginWith(model, prefix);
			bufferedValues.put(prefix, sum);
			return sum;
		} else {
			Number n = bufferedValues.get(prefix);
			int tmp;
			if(n == null) {
				tmp = CreatureHelper.sumParamsBeginWith(model, prefix);
				bufferedValues.put(prefix, tmp);
			} else 
				tmp = n.intValue();
			return tmp;
		}
	}
	
	private double getDoubleValue(String prefix, boolean recalculate) {
		if(recalculate) {
			double sum = CreatureHelper.sumDoubleParamsBeginWith(model, prefix);
			bufferedValues.put(prefix, sum);
			return sum;
		} else {
			Number n = bufferedValues.get(prefix);
			double tmp;
			if(n == null) {
				tmp = CreatureHelper.sumDoubleParamsBeginWith(model, prefix);
				bufferedValues.put(prefix, tmp);
			} else 
				tmp = n.doubleValue();
			return tmp;
		}
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


	@Override
	public void flagChanged(Object sender, int bitNum, boolean oldValue,
			boolean newValue) {
		
	}



	private Integer strMod, dexMod, conMod, intMod, wisMod, chaMod;
	private Map<String, Number> bufferedValues = new HashMap<>();
}
