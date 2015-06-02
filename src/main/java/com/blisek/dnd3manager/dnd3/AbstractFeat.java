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
	 * Informuje czy model w swoim aktualnym stanie może mieć aktywowany
	 * ten atut. Domyślnie zwraca wartość przeciwną do isPassive, czyli
	 * domyślnie jeśli atut może być aktywowany, to metoda zwraca true
	 * za każdym razem.
	 * @param model model postaci.
	 * @param extraParams dodatkowe parametry.
	 * @return true jeśli można aktywować go dla obecnego stanu modelu postaci.
	 */
	public boolean canBeActivatedFor(CreatureModel model, Map<String, Object> extraParams) {
		return !isPassive();
	}
	
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
	
	/**
	 * Zwraca typ atutu. Domyślnie FeatType.GENERAL. 
	 * Na podstawie zwracanego typu można określić
	 * podklasę, jeśli występuje.
	 * @return typ atutu.
	 */
	public FeatType getFeatType() {
		return FeatType.GENERAL;
	}
}
