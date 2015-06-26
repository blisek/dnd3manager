package com.blisek.dnd3manager.dnd3;

/**
 * Przechowuje numery bitów różnych stanów postaci.
 * @author bartek
 *
 */
public final class CreatureFlags {
	
	/**
	 * Jeśli flaga jest włączona postać posiada redukcję obrażeń jakiegoś typu.
	 */
	public static final int HAS_DAMAGE_REDUCTION = 12;
	
	/**
	 * Jeśli flaga jest włączona postać nie może być flankowana.
	 */
	public static final int CANNOT_BE_FLANKED = 13;
	
	/**
	 * Jeśli flaga jest włączona premia ze zręczności może być
	 * wliczana jeśli postać została zaskoczona.
	 */
	public static final int DONT_LOOSE_DEX_AC_BONUS = 14;

	/**
	 * Jeśli ustawiony na true postać nie może biegać.
	 */
	public static final int CANT_RUN = 15;
	
	/**
	 * Jeśli ustawiony na true postać nie może szarżować.
	 */
	public static final int CANT_CHARGE = 16;
}
