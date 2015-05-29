package com.blisek.dnd3manager.dnd3;

import java.util.Map;

public abstract class AbstractRace implements RestrictedSystemObject {

	@Override
	public boolean isOnFor(CreatureModel model, Map<String, Object> extraParams) {
		Object race = model.get(StringConstants.RACE);
		return (race != null) && ((SystemObject)race).getSystemName().equals(getSystemName());
			
	}

	@Override
	public boolean isAvailableFor(CreatureModel model, Object... extraParams) {
		return true;
	}

}
