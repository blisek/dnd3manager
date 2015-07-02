package com.blisek.dnd3manager.dnd3;

import java.util.Collection;
import java.util.Map;

/**
 * Interfesj z domyślną implementacją metody isAvailableFor dla
 * obiektów, których dostępność jest zależna od wcześniej aktywowanych
 * innych systemowych obiektów.
 * @author bartek
 *
 */
public interface ChainedRestrictedSystemObject extends RestrictedSystemObject {

	/**
	 * Zwraca kolekcję obiektów systemowych, które muszą być włączone
	 * dla danego modelu postaci.
	 * @return kolekcja SystemObject.
	 */
	public Collection<SystemObject> getRequiredSystemObjects();
	
	/**
	 * Domyślna implementacja sprawdzająca, czy wszystkie obiekty zwrócone przez metodę 
	 * getRequiredSystemObjects są włączone (isOnFor) dla danego modelu.
	 */
	default public boolean isAvailableFor(CreatureModel model, Map<String, Object> extraParams) {
		return getRequiredSystemObjects().stream().allMatch(so -> so.isOnFor(model, extraParams));
	}

}
