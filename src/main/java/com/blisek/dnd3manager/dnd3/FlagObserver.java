package com.blisek.dnd3manager.dnd3;

public interface FlagObserver {

	/**
	 * Informuje o zmianie stanu jakiejś flagi.
	 * @param bitNum numer bitu z klasy CreatureFlags.
	 * @param oldValue stara wartość.
	 * @param newValue nowa wartość.
	 */
	public void flagChanged(Object sender, int bitNum, boolean oldValue, boolean newValue);
}
