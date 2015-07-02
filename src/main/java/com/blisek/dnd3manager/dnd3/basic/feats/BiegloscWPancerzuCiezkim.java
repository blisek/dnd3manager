package com.blisek.dnd3manager.dnd3.basic.feats;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import com.blisek.dnd3manager.dnd3.AbstractFeat;
import com.blisek.dnd3manager.dnd3.ChainedRestrictedSystemObject;
import com.blisek.dnd3manager.dnd3.CreatureModel;
import com.blisek.dnd3manager.dnd3.SystemObject;

public class BiegloscWPancerzuCiezkim extends AbstractFeat implements
		ChainedRestrictedSystemObject {
	public static final String SYSTEM_NAME = "bwpciezkim";
	protected final Collection<SystemObject> required = Arrays.asList(
		new BiegloscWPancerzuLekkim(),
		new BiegloscWPancerzuSrednim()
	);

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
	public Collection<SystemObject> getRequiredSystemObjects() {
		return required;
	}

	@Override
	public boolean isPassive() {
		return true;
	}

}
