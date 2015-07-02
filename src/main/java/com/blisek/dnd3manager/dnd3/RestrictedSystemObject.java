package com.blisek.dnd3manager.dnd3;

import java.util.Map;

public interface RestrictedSystemObject extends SystemObject {

	/**
	 * Informuje, czy można ten obiekt przypisać do danego modelu (czy model
	 * spełnia wymagania tego obiektu).
	 * @param model model postaci.
	 * @param extraParams dodatkowe parametry.
	 * @return true jeśli może być przypisany.
	 */
	public boolean isAvailableFor(CreatureModel model, Map<String, Object> extraParams);

}
