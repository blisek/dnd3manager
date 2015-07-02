package com.blisek.dnd3manager.dnd3.basic.specialabilities;

import java.util.Map;

import com.blisek.dnd3manager.dnd3.AbstractFeat;
import com.blisek.dnd3manager.dnd3.CreatureModel;
import com.blisek.dnd3manager.dnd3.StringConstants;

// TODO Dodać reakcję na zmianę pancerza.
public class SzybkiePoruszanie extends AbstractFeat {
	public static final String SYSTEM_NAME = "szybkiepor";
	public static final String KEY_NAME = 
			String.format("%s_%s", StringConstants.SPEED, SzybkiePoruszanie.SYSTEM_NAME);

	@Override
	public boolean isAvailableFor(CreatureModel model, Map<String, Object> extraParams) {
		return false;
	}

	@Override
	public String getSystemName() {
		return SzybkiePoruszanie.SYSTEM_NAME;
	}

	@Override
	public void turnOnFor(CreatureModel model, Map<String, Object> extraParams) {
		model.put(KEY_NAME, 3);
		model.getFeatsMap().put(SYSTEM_NAME, 1);
	}

	@Override
	public void turnOffFor(CreatureModel model, Map<String, Object> extraParams) {
		model.remove(KEY_NAME);
		model.getFeatsMap().remove(SYSTEM_NAME);
	}

	@Override
	public boolean isPassive() {
		return true;
	}

}
