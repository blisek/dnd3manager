package com.blisek.dnd3manager.dnd3;

import java.util.Collection;

public interface ClassInfo {
	
	/**
	 * Zwraca listę atutów znajdujących się w pakiecie.
	 * @return kolekcja klas.
	 */
	public Collection<AbstractClass> getClasses();
}
