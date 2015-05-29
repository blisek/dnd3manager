package com.blisek.dnd3manager.dnd3;

import java.util.Collection;

public interface RaceInfo {

	/**
	 * Zwraca rasy wewnątrz pakietu.
	 * @return kolekcja ras.
	 */
	public Collection<AbstractRace> getRaces();
}
