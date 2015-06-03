package com.blisek.dnd3manager.dnd3;

import java.util.Map;

public abstract class AbstractFeat implements RestrictedSystemObject {
	protected static final UsesInfo noUses = new UsesInfo(0, TimeUnit.ROUND);
	protected static final Duration noDuration = new Duration(0, TimeUnit.ROUND);

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
	public boolean canBeActivatedFor(CreatureModel model, CreatureController controller, Map<String, Object> extraParams) {
		return !isPassive();
	}
	
	/**
	 * Informuje o maksymalnej liczbie użyć tego atutu dla
	 * danego modelu np. barbarzyńca na 4p. może użyć szału
	 * 2/d, dla tej sytuacji metoda zwróci UsesInfo(2, TimeUnit.DAY).
	 * @param model model postaci.
	 * @param extraParams dodatkowe parametry.
	 * @return nienadpisana metoda zwraca (0, TimeUnit.ROUND).
	 */
	public UsesInfo getUsesCountFor(CreatureModel model, CreatureController controller, Map<String, Object> extraParams) {
		return noUses;
	}
	
	/**
	 * Informuje ile jeszcze zostało użyć danego atutu. Domyślnie
	 * zwraca to samo co getUsesCountFor. 
	 * @param model model postaci.
	 * @param extraParams dodatkowe parametry.
	 * @return liczbę pozostałych użyć, bez jednostki czasu (sama liczba).
	 */
	public int getUsesLeftFor(CreatureModel model, CreatureController controller,Map<String, Object> extraParams) {
		return getUsesCountFor(model, controller, extraParams).usesPerTimeUnit;
	}
	
	/**
	 * Resetuje licznik użyć tego atutu. Wartość licznika pobiera się
	 * metodą getUsesLeftFor.
	 * @param model model postaci.
	 * @param extraParams dodatkowe parametry.
	 */
	public void resetUsesLeftCounterFor(CreatureModel model, CreatureController controller, Map<String, Object> extraParams) {}
	
	/**
	 * Informuje ile ten efekt będzie trwać jeśli zostałby aktywowany
	 * dla obecnego stanu modelu. Domyślnie zwraca 0 rund.
	 * @param model model postaci.
	 * @param extraParams dodatkowe parametry.
	 * @return czas trwania.
	 */
	public Duration getDurationFor(CreatureModel model, CreatureController controller, Map<String, Object> extraParams) {
		return noDuration;
	}
	
	/**
	 * Jeśli atut jest aktywny (niepasywny) ta metoda aktywuje wszystkie modyfikatory
	 * dane przezeń. Jeśli jest pasywny nie powinien nic robić.
	 * @param model model dla którego atut zostanie aktywowany.
	 * @param extraParams dodatkowe parametry.
	 */
	public void activateFor(CreatureModel model, CreatureController controller, Map<String, Object> extraParams) {}
	
	/**
	 * Deaktywuje wcześniej aktywowany atut dla danego modelu. Jeśli atut
	 * nie był wcześniej aktywowany metoda nie powinna w żaden sposób wpłynąć
	 * na zmianę wewnętrznego stanu modelu.
	 * @param model model postaci.
	 * @param extraParams dodatkowe parametry.
	 */
	public void deactivateFor(CreatureModel model, CreatureController controller, Map<String, Object> extraParams) {}
	
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
