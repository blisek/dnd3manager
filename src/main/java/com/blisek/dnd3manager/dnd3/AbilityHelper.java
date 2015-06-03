package com.blisek.dnd3manager.dnd3;

import java.util.EnumMap;
import java.util.Map;

/**
 * Metody pomocnicze przy obsłudze atrybutów.
 * @author bartek
 *
 */
public final class AbilityHelper {
	@SuppressWarnings("serial")
	private static Map<AbilityType, String> abilityTypeToPrefix = 
			new EnumMap<AbilityType, String>(AbilityType.class) {{
				put(AbilityType.STRENGTH, StringConstants.STRENGTH);
				put(AbilityType.DEXTERITY, StringConstants.DEXTERITY);
				put(AbilityType.CONSTITUTION, StringConstants.CONSTITUTION);
				put(AbilityType.INTELLIGENCE, StringConstants.INTELLIGENCE);
				put(AbilityType.WISDOM, StringConstants.WISDOM);
				put(AbilityType.CHARISMA, StringConstants.CHARISMA);
			}};
			
	/**
	 * Zwraca nazwę prefiksu wykorzystywanego przy tym atucie w modelu postaci.
	 * @param at typ atrybutu.
	 * @return prefix atrybutu.
	 */
	public static String abilityTypeToAbilityPrefix(AbilityType at) {
		return abilityTypeToPrefix.get(at);
	}
	
	public static int getModifier(CreatureController controller, String prefix) {
		return getModifier(controller, prefix, false);
	}
	
	
	public static int getModifier(CreatureController controller, String prefix, boolean recalculate) {
		if(controller == null || prefix == null)
			throw new NullPointerException();
		switch(prefix)
		{
		case StringConstants.STRENGTH:
			return controller.getStrengthMod(recalculate);
		case StringConstants.DEXTERITY:
			return controller.getDexterityMod(recalculate);
		case StringConstants.CONSTITUTION:
			return controller.getConstitutionMod(recalculate);
		case StringConstants.INTELLIGENCE:
			return controller.getIntelligenceMod(recalculate);
		case StringConstants.WISDOM:
			return controller.getWisdomMod(recalculate);
		case StringConstants.CHARISMA:
			return controller.getCharismaMod(recalculate);
		default:
			throw new IllegalArgumentException("prefix");
		}
	}
	
}
