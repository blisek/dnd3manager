package com.blisek.dnd3manager.dnd3;

public class StringConstants {
	public static final String STRENGTH = "str";
	public static final String DEXTERITY = "dex";
	public static final String CONSTITUTION = "con";
	public static final String INTELLIGENCE = "int";
	public static final String WISDOM = "wis";
	public static final String CHARISMA = "cha";
	public static final String ALIGNMENT = "alignment";
	
	public static final String KEY_FEATS = "feats";
	public static final String KEY_SKILLS = "skills";
	public static final String KEY_CLASS = "classes";
	
	public static final String FORTITUDE = "fortitude";
	public static final String REFLEX = "reflex";
	public static final String WILL = "will";
	
	public static final String RACE = "race";
	public static final String SIZE = "size";
	public static final String SPEED = "speed";
	public static final String LANGUAGES = "languages";
	
	
	// PARAMETRY PRZEKAZYWANE PRZY TWORZENIU
	/**
	 * Pod tym kluczem w extraParams umieszczana
	 * jest liczba rang.
	 */
	public static final String P_RANKS = "ranks";
	
	/**
	 * Pod tym kluczem extraParams umieszczana
	 * jest liczba atutów.
	 */
	public static final String P_FEATS = "feats";
	
	/**
	 * Z tym parametrem związana jest lista atutów z
	 * których FeatsFactory czerpie.
	 */
	public static final String P_FEATS_LIST = "feats_list";
	
	/**
	 * Specjalny parametr wykorzystywany w metodzie turnOnFor
	 * klasy skills. Jeżeli obecny, liczba rang w umiejętności dla
	 * której wywoływana jest metoda turnOnFor zostanie ustawiona na
	 * tę wartość.
	 */
	public static final String P_RANKS_FOR_THIS_SKILL = "current_skill_ranks";
	
	/**
	 * Z tym kluczem związany jest poziom na który wchodzi postać.
	 */
	public static final String P_LEVEL = "level";
	
	/**
	 * Klucz z wartością informującą czy to 1. poziom w ogóle
	 * w tym modelu.
	 */
	public static final String P_FIRST_LEVEL = "first_level";
}
