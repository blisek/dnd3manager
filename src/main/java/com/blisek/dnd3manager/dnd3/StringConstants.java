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
	public static final String KEY_SPECIAL_ABILITIES = "special_abilities";
	public static final String KEY_EFFECTS = "effects_on";
	
	public static final String FORTITUDE = "fortitude";
	public static final String REFLEX = "reflex";
	public static final String WILL = "will";
	public static final String WILL_MORALE = WILL + "_morale";
	
	public static final String RACE = "race";
	public static final String SIZE = "size";
	public static final String SPEED = "speed";
	public static final String LANGUAGES = "languages";
	
	public static final String AC = "ac"; // KP
	
	
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
	 * w tym modelu. Wartość boolowska.
	 */
	public static final String P_FIRST_LEVEL = "first_level";
	
	/**
	 * Liczba dodatkowych języków do wybrania.
	 */
	public static final String P_PREMIUM_LANGUAGES = "premium_lang";
	
	/**
	 * Liczba użyć. Parametr może być wykorzystywany przy ustawianiu
	 * liczby użyć dla jakiejś zdolności/atutu.
	 */
	public static final String P_USES_COUNT = "uses_count";
	
	/**
	 * Lista efektów do zastosowania. Jeśli istnieje taki klucz w słowniku,
	 * powinien być typem List<Effect>.
	 */
	public static final String P_EFFECTS = "effects";
}
