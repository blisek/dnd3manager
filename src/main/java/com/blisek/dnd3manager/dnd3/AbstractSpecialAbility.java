package com.blisek.dnd3manager.dnd3;

import java.util.Map;

public abstract class AbstractSpecialAbility extends AbstractFeat {

	@Override
	public boolean isOnFor(CreatureModel model, Map<String, Object> extraParams) {
		if(model == null)
			throw new NullPointerException();
		return model.getSpecialAbilitiesMap().containsKey(getSystemName());
	}

	@Override
	public FeatType getFeatType() {
		return FeatType.SPECIAL_ABILITY;
	}
	
}
