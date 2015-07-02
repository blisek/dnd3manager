package com.blisek.dnd3manager.dnd3.basic.feats;

import java.util.Map;

import com.blisek.dnd3manager.dnd3.CreatureModel;
import com.blisek.dnd3manager.dnd3.MartialWeapons;

public class BiegloscWBroniZolnierskiej extends BiegloscWBroni<MartialWeapons> {
	public static final String SYSTEM_NAME = "bwbzolnierskiej";

	public BiegloscWBroniZolnierskiej() {
		super(MartialWeapons.class);
	}

	@Override
	public boolean isAvailableFor(CreatureModel model, Map<String, Object> extraParams) {
		return true;
	}

	@Override
	public String getSystemName() {
		return BiegloscWBroniZolnierskiej.SYSTEM_NAME;
	}

}
