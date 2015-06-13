package com.blisek.dnd3manager.dnd3;

import java.util.Map;

public abstract class AbstractEffect implements SystemObject {
	protected final static Duration NO_DURATION = new Duration(0, TimeUnit.ROUND);
	
	
	/**
	 * Zwraca czas działania efektu dla danej postaci.
	 * @param model model postaci.
	 * @param controller kontroler postaci.
	 * @param extraParams dodatkowe parametry.
	 * @return czas trwania efektu.
	 */
	public abstract Duration getDurationFor(CreatureModel model, 
			CreatureController controller, Map<String, Object> extraParams);


	@Override
	public boolean isOnFor(CreatureModel model, Map<String, Object> extraParams) {
		return model.getEffectsMap().containsKey(getSystemName());
	}
	
	
}
