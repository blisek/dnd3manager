package com.blisek.dnd3manager.dnd3;

import java.util.ArrayList;
import java.util.List;
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
	public static int getIntegerDefault0(Map<String, Object> extraParams, String param) {
		Object integer = extraParams.get(param);
		if(integer == null)
			return 0;
		else
			return (int)integer;
	}
	
	/**
	 * Zwraca int z podanego klucza. Usuwa wartość związaną z tym kluczem.
	 * @param extraParams słownik paramterów.
	 * @param param nazwa parametru.
	 * @return int lub 0 jeśli klucz nie istnieje.
	 */
	public static int takeIntegerDefault0(Map<String, Object> extraParams, String param) {
		Object integer = extraParams.remove(param);
		if(integer == null)
			return 0;
		else
			return (int)integer;
	}

	/**
	 * Zwraca wartość boolowską, bądź null jeśli klucza nie ma w słowniku.
	 * @param extraParams
	 * @param param
	 * @return wartość boolowską jeśli jest w słowniku, inaczej null.
	 */
	public static Boolean getBoolean(Map<String, Object> extraParams, String param) {
		Object bool = extraParams.get(param);
		if(bool == null)
			return null;
		else
			return (Boolean)bool;
	}
	
	/**
	 * Zwraca wartość boolowską. Jeśli klucz nie jest obecny w słowniku zwracana
	 * jest wartość false.
	 * @param extraParams słownik parametrów.
	 * @param param nazwa klucza w extraParams.
	 * @return false jeśli klucz jest nieobecny w słowniku bądź wartość false
	 * jest związana z tym kluczem. W innym wypadku - true.
	 */
	public static boolean getBooleanDefaultFalse(Map<String, Object> extraParams, String param) {
		Object bool = extraParams.get(param);
		return (bool == null) ? false : (boolean)bool;
	}
	
	/**
	 * Zwraca wartość boolowską. Jeśli klucz nie jest obecny w słowniku zwracana
	 * jest wartość false. Jeśli klucz jest obecny, jest usuwany ze słownika.
	 * @param extraParams słownik parametrów.
	 * @param param nazwa klucza w extraParams.
	 * @return false jeśli klucz jest nieobecny w słowniku bądź wartość false
	 * jest związana z tym kluczem. W innym wypadku - true.
	 */
	public static boolean takeBooleanDefaultFalse(Map<String, Object> extraParams, String param) {
		Object bool = extraParams.remove(param);
		return (bool == null) ? false : (boolean)bool;
	}
	
	/**
	 * Zwiększa wartość int związaną z danym kluczem. Jeśli klucz nie istnieje
	 * przyjmuje się jego poprzednią wartość za 0. W słowniku zostanie zapisana
	 * wartość >= 0 nawet jeśli inc_val jest < 0.
	 * @param extraParams słownik parametrów.
	 * @param param nazwa parametru.
	 * @param inc_val wartość o jaką zostanie zwiększona/zmniejszona obecna.
	 */
	public static void increaseInteger(Map<String, Object> extraParams, String param, int inc_val) {
		int prev = getIntegerDefault0(extraParams, param);
		extraParams.put(param, Math.max(prev + inc_val, 0));
	}
	
	public static void putIfGreater(Map<String, Object> extraParams, String param, int val) {
		Object obj = extraParams.get(param);
		int prev = (obj == null) ? 0 : (int)obj;
		if(val > prev)
			extraParams.put(param, val);
	}
	
	/**
	 * Zwraca listę przechowywaną pod danym kluczem. Jeśli lista nie istnieje
	 * tworzy nową przy użyciu ArrayList.
	 * @param extraParams słownik parametrów.
	 * @param param nazwa parametru.
	 * @return lista.
	 */
	@SuppressWarnings("unchecked")
	public static <E, K, V> List<E> getListOrCreateNewOne(Map<K, V> extraParams, K param) {
		Object obj = extraParams.get(param);
		List<E> retList;
		if(obj == null) {
			retList = new ArrayList<E>();
			extraParams.put(param, (V)retList);
		} else {
			retList = (List<E>)obj;
		}
		
		return retList;
	}
	
	/**
	 * Zwraca mapę związaną z danym kluczem. Jeśli klucz nie jest związany (nie jest
	 * do niego przypisana mapa) tworzy nową mapę typu ObservableMap wiąże ją z kluczem
	 * i zwraca.
	 * @param extraParams słownik w którym mapa jest poszukiwana.
	 * @param param klucz w extraParams.
	 * @return mapa istniejąca, bądź nowoutworzona typu ObservableMap o żądanym typie.
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K,V> getMapOrCreateOne(Map<String, Object> extraParams, String param) {
		Object obj = extraParams.get(param);
		Map<K, V> map;
		if(obj == null) {
			map = new ObservableMap<>(false);
			extraParams.put(param, map);
		} else {
			map = (Map<K,V>)obj;
		}
		
		return map;
	}
}
