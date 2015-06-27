package com.blisek.dnd3manager.dnd3.basic.feats;

import com.blisek.dnd3manager.dnd3.CreatureModel;
import com.blisek.dnd3manager.dnd3.SimpleWeapons;

public class BiegloscWBroniProstej extends Bieglosc<SimpleWeapons> {
	public static final String SYSTEM_NAME = "bwbroniprostej";

	public BiegloscWBroniProstej() {
		super(SimpleWeapons.class);
	}

	@Override
	public boolean isAvailableFor(CreatureModel model, Object... extraParams) {
		return true;
	}

	@Override
	public String getSystemName() {
		return BiegloscWBroniProstej.SYSTEM_NAME;
	}

	
}
