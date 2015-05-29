package com.blisek.dnd3manager.dnd3;

import java.util.Map;

public interface SystemObject {
	
	/**
	 * Zwraca nazwę systemową.
	 * @return łańcuch znaków z nazwą systemową.
	 */
	public String getSystemName();
	
	/**
	 * Sprawdza czy efekt/obiekt jest włączony dla danej postaci.
	 * @param model model postaci.
	 * @param extraParams dodatkowe parametry dla metody.
	 * @return true jeśli efekt/obiekt jest przypisany do postaci inaczej false.
	 */
	public boolean isOnFor(CreatureModel model, Map<String, Object> extraParams);
	
	/**
	 * Przypisuje postaci ten efekt.
	 * @param model model postaci.
	 * @param extraParams dodatkowe parametry.
	 */
	public void turnOnFor(CreatureModel model, Map<String, Object> extraParams);
	
	/**
	 * Wyłącza efekt dla danej postaci. Jeśli efekt nie był wcześniej
	 * włączony możliwe jest, że ta metoda zmieni stan wewnętrzny modelu,
	 * nawet uszkadzając go.
	 * @param model model postaci.
	 * @param extraParams dodatkowe parametry.
	 */
	public void turnOffFor(CreatureModel model, Map<String, Object> extraParams);
}
