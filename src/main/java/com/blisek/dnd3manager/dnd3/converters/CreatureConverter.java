package com.blisek.dnd3manager.dnd3.converters;

import java.util.Map;

import com.blisek.dnd3manager.dnd3.CreatureModel;

/**
 * Interfejs konwersji postaci do modelu. 
 * @author bartek
 *
 */
public interface CreatureConverter {

	/**
	 * Zwraca model postaci.
	 * @param extraParams dodatkowe parametry do kustomizacji działania metody.
	 * @return
	 */
	public CreatureModel convertToCreatureModel(Map<String, Object> extraParams);
}
