package com.blisek.dnd3manager.dnd3;

import java.util.Collection;

/**
 * Klasa bazowa dla wszystkich klas postaci.
 * @author bartek
 *
 */
public abstract class AbstractClass implements RestrictedSystemObject {
	
	/**
	 * Informuje o kości wytrzymałości tej klasy.
	 * @return liczba ścian KW.
	 */
	public abstract int getHitDice();
	
	/**
	 * Zwraca liczbę rang dostępnych na każdym poziomie.
	 * @return liczba rang.
	 */
	public abstract int getSkillRanksForLevel();
	
	/**
	 * Zwraca modyfikator za Wytrzymałość na danym poziomie.
	 * @param level poziom postaci w tej klasie.
	 * @return modyfikator za wytrwałość.
	 */
	public abstract int getFortitudeMod(int level);
	
	/**
	 * Zwraca modyfikator za Refleks na danym poziomie.
	 * @param level poziom postaci w tej klasie.
	 * @return modyfikator z refleksu.
	 */
	public abstract int getReflexMod(int level);
	
	/**
	 * Zwraca modyfikator z Woli na danym poziomie.
	 * @param level poziom postaci w tej klasie.
	 * @return modyfikator z woli.
	 */
	public abstract int getWillMod(int level);
	
	/**
	 * Zwraca bazową premię do ataku.
	 * @param level poziom postaci w tej klasie.
	 * @return bazowa premia do ataku.
	 */
	public abstract int getBaseAttackMod(int level);
	
	/**
	 * Zwraca listę umiejętności klasowych.
	 * @return Iterable od listy umiejętności.
	 */
	public abstract Collection<String> getClassSkills();
	
	/**
	 * Zwraca listę atrybutów w kolejności malejącej pod względem
	 * znaczenia dla klasy (najważniejsze na początku).
	 * @return lista prefiksów atrybutów.
	 */
	public abstract Collection<String> getAbilitiesByPriorityDescending();
	
	/**
	 * Zwraca liczbę ataków na danym poziomie.
	 * @param level poziom postaci.
	 * @return liczba ataków.
	 */
	public abstract int getAttacksCount(int level);
	
	/**
	 * Zwraca dopuszczalne charaktery dla tej klasy. Można sprawdzić czy dla danego
	 * charakteru klasa jest dostępna przez operator bitowy AND.
	 * Np. (getAlignmentsFlag() & NumericConstants.AL_CHAOTIC_GOOD) > 0
	 * @return int z flagami ustawionymi na 1 przy dostępnych charakterach.
	 */
	public int getAlignmentsFlag() {
		return NumericConstants.AL_CHAOTIC_EVIL | NumericConstants.AL_CHAOTIC_GOOD |
				NumericConstants.AL_CHAOTIC_NEUTRAL | NumericConstants.AL_LAWFUL_EVIL |
				NumericConstants.AL_LAWFUL_GOOD | NumericConstants.AL_LAWFUL_NEUTRAL |
				NumericConstants.AL_NEUTRAL | NumericConstants.AL_NEUTRAL_EVIL |
				NumericConstants.AL_NEUTRAL_GOOD;
	}

}
