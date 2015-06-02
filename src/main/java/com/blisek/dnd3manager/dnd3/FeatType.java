package com.blisek.dnd3manager.dnd3;

/**
 * Typ atutu.
 * @author bartek
 *
 */
public enum FeatType {
	/**
	 * Atut uniwersalny bez szczególnych cech, 
	 * klasyfikujących go do pozostałych wymienionych
	 * kategorii. 
	 */
	GENERAL,
	
	/**
	 * Atuty wpływające na zdolność odganiania/karcenia
	 * nieumarłych.
	 */
	DIVINE,
	
	/**
	 * Atuty dla postaci epickich.
	 */
	EPIC,
	
	/**
	 * Atuty wpływające na walkę. Modyfikatory ataku,
	 * obrony itd.
	 */
	FIGHTER_BONUS,
	
	/**
	 * Atuty związane z wytwarzaniem przedmiotów.
	 */
	ITEM_CREATION,
	
	/**
	 * Atuty metamagiczne np. podniesienie czaru.
	 */
	METAMAGIC,
	
	/**
	 * Atuty metapsioniczne.
	 */
	METAPSIONIC,
	
	/**
	 * Atuty stosowalne dla postaci o innych cechach
	 * fizycznych odróżniających je od zwykłych ras.
	 * Dla przykładu: kreatury o wielu rękach.
	 */
	MONSTROUS,
	
	/**
	 * Atuty z Podręcznika Psioniki.
	 */
	PSIONIC,
	
	/**
	 * Atuty związane ze zdolnościami zmiany
	 * kształtów np. u druida.
	 */
	WILD
}
