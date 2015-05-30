package com.blisek.dnd3manager.dnd3;

import java.util.Map;

/**
 * Metody pomocnicze przy operowaniu
 * słownikiem extraParams w systemowych
 * obiektach.
 * @author bartek
 *
 */
public class ExtraParamsHelper {

	/**
	 * Zwiększa liczbę dostępnych rang do rozdania
	 * na umiejętności (przy tworzeniu modelu bądź
	 * przy awansowaniu).
	 * @param extraParams
	 */
	public static void increaseSkillsRanks(Map<String, Object> extraParams, int additional_ranks) {
		Object ranks = extraParams.get(StringConstants.P_RANKS);
		if(ranks == null)
			extraParams.put(StringConstants.P_RANKS, Math.max(0, additional_ranks));
		else 
			extraParams.put(StringConstants.P_RANKS, Math.max(0, (int)ranks + additional_ranks));
	}
	
	/**
	 * Zwiększa liczbę atutów o additional_rank. Gwarantowane jest
	 * że z kluczem P_FEATS zostanie związana liczba >= 0.
	 * @param extraParams słownik dod. parametrów.
	 * @param additional_ranks dodatkowe rangi. Może być liczbą ujemną.
	 */
	public static void increaseFeatsCount(Map<String, Object> extraParams, int additional_ranks) {
		Object ranks = extraParams.get(StringConstants.P_FEATS);
		if(ranks == null)
			extraParams.put(StringConstants.P_FEATS, Math.max(0, additional_ranks));
		else
			extraParams.put(StringConstants.P_FEATS, Math.max(0, (int)ranks + additional_ranks));
	}
	
	/**
	 * Zwraca int z podanego klucza.
	 * @param extraParams słownik paramterów.
	 * @param param nazwa parametru.
	 * @return int lub 0 jeśli klucz nie istnieje.
	 */
	public static int getInteger(Map<String, Object> extraParams, String param) {
		Object integer = extraParams.get(param);
		if(integer == null)
			return 0;
		else
			return (int)integer;
	}
	
	/**
	 * Zwraca wartość boolowską, bądź null jeśli klucza nie ma w słowniku.
	 * @param extraParams
	 * @param param
	 * @return
	 */
	public static Boolean getBoolean(Map<String, Object> extraParams, String param) {
		Object bool = extraParams.get(param);
		if(bool == null)
			return null;
		else
			return (Boolean)bool;
	}
}
