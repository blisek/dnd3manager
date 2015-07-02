package com.blisek.dnd3manager.dnd3.basic.feats;

import java.util.Map;

import com.blisek.dnd3manager.dnd3.AbstractFeat;
import com.blisek.dnd3manager.dnd3.CreatureModel;

public class BiegloscWPancerzuLekkim extends AbstractFeat {
	
	public static final String SYSTEM_NAME = "bwplekkim";

	@Override
	public String getSystemName() {
		return SYSTEM_NAME;
	}

	@Override
	public void turnOnFor(CreatureModel model, Map<String, Object> extraParams) {
		model.getFeatsMap().put(SYSTEM_NAME, 1);
	}

	@Override
	public void turnOffFor(CreatureModel model, Map<String, Object> extraParams) {
		model.getFeatsMap().remove(SYSTEM_NAME);
	}

	@Override
	public boolean isPassive() {
		return true;
	}

	@Override
	public boolean isAvailableFor(CreatureModel model,
			Map<String, Object> extraParams) {
		return true;
	}
	
	

}
