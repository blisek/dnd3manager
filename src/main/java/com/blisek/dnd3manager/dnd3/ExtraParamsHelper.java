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
			extraParams.put(StringConstants.P_RANKS, additional_ranks);
		else
			extraParams.put(StringConstants.P_RANKS, (int)ranks + additional_ranks);
	}
}
