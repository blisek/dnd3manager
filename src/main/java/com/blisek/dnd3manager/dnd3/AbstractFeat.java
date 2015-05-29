package com.blisek.dnd3manager.dnd3;

import java.util.Map;

public abstract class AbstractFeat implements RestrictedSystemObject {

	@Override
	public boolean isOnFor(CreatureModel model, Map<String, Object> extraParams) {
		if(model == null)
			throw new NullPointerException();
		return model.getFeatsMap().containsKey(getSystemName());
	}
	
	/**
	 * Informuje czy atut jest pasywny (działa non-stop).
	 * @return true jeśli jest pasywny inaczej false.
	 */
	public abstract boolean isPassive();
	
	/**
	 * Jeśli atut jest aktywny (niepasywny) ta metoda aktywuje wszystkie modyfikatory
	 * dane przezeń. Jeśli jest pasywny nie powinien nic robić.
	 * @param model model dla którego atut zostanie aktywowany.
	 * @param extraParams dodatkowe parametry.
	 */
	public void activateFor(CreatureModel model, Map<String, Object> extraParams) {}
	
	/**
	 * Deaktywuje wcześniej aktywowany atut dla danego modelu. Jeśli atut
	 * nie był wcześniej aktywowany metoda nie powinna w żaden sposób wpłynąć
	 * na zmianę wewnętrznego stanu modelu.
	 * @param model model postaci.
	 * @param extraParams dodatkowe parametry.
	 */
	public void deactivateFor(CreatureModel model, Map<String, Object> extraParams) {}
}
