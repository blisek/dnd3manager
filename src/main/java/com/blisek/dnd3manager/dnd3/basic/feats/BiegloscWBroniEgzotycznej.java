package com.blisek.dnd3manager.dnd3.basic.feats;

import com.blisek.dnd3manager.dnd3.CreatureModel;
import com.blisek.dnd3manager.dnd3.ExoticWeapons;
import com.blisek.dnd3manager.dnd3.ExtraParamsHelper;
import com.blisek.dnd3manager.dnd3.StringConstants;

public class BiegloscWBroniEgzotycznej extends Bieglosc<ExoticWeapons> {
	public static final String SYSTEM_NAME = "bieg_egz";
	
	public BiegloscWBroniEgzotycznej() {
		super(ExoticWeapons.class);
	}

	@Override
	public boolean isAvailableFor(CreatureModel model, Object... extraParams) {
		// TODO Dodać osobne zasady dla krasnoludzkiego toporu bojowego.
		return ExtraParamsHelper.getIntegerDefault0(model, StringConstants.BASE_ATTACK) > 0;
	}

	@Override
	public String getSystemName() {
		return BiegloscWBroniEgzotycznej.SYSTEM_NAME;
	}

}
