package com.blisek.dnd3manager.dnd3.basic.effects;

import java.util.Map;

import com.blisek.dnd3manager.dnd3.AbstractEffect;
import com.blisek.dnd3manager.dnd3.CreatureController;
import com.blisek.dnd3manager.dnd3.CreatureFlags;
import com.blisek.dnd3manager.dnd3.CreatureModel;
import com.blisek.dnd3manager.dnd3.Duration;
import com.blisek.dnd3manager.dnd3.FlagObserver;
import com.blisek.dnd3manager.dnd3.StringConstants;

public class ZmeczeniePoSzale extends AbstractEffect implements FlagObserver {
	public static final String SYSTEM_NAME = "e_zmposzale";
	public static final String STR_KEY = String.format("%s_%s", StringConstants.STRENGTH, SYSTEM_NAME);
	public static final String DEX_KEY = String.format("%s_%s", StringConstants.DEXTERITY, SYSTEM_NAME);

	@Override
	public String getSystemName() {
		return ZmeczeniePoSzale.SYSTEM_NAME;
	}

	@Override
	public void turnOnFor(CreatureModel model, Map<String, Object> extraParams) {
		model.getEffectsMap().put(SYSTEM_NAME, 1);
		model.put(STR_KEY, -2);
		model.put(DEX_KEY, -2);
		model.addFlagObserver(this);
		model.turnFlagOn(CreatureFlags.CANT_RUN);
		model.turnFlagOn(CreatureFlags.CANT_CHARGE);
	}

	@Override
	public void turnOffFor(CreatureModel model, Map<String, Object> extraParams) {
		model.remove(DEX_KEY);
		model.remove(STR_KEY);
		model.getEffectsMap().remove(SYSTEM_NAME);
		model.removeFlagObserver(this);
		model.turnFlagOff(CreatureFlags.CANT_CHARGE);
		model.turnFlagOff(CreatureFlags.CANT_RUN);
	}

	@Override
	public Duration getDurationFor(CreatureModel model,
			CreatureController controller, Map<String, Object> extraParams) {
		return Duration.UNTIL_END_OF_FIGHT;
	}

	// Jeżeli działanie innego efektu pokrywało się z działaniem tego i inny efekt kończą działanie
	// zmienił stan flag ustawiony przez ten efekt, to stan tych flag jest przywracany do poprawnego
	// stanu.
	@Override
	public void flagChanged(Object sender, int bitNum, boolean oldValue, boolean newValue) {
		if(bitNum != CreatureFlags.CANT_RUN && bitNum != CreatureFlags.CANT_CHARGE)
			return;
		CreatureModel model = (CreatureModel)sender;
		if(model.getEffectsMap().containsKey(SYSTEM_NAME)) {
			switch(bitNum)
			{
			case CreatureFlags.CANT_CHARGE:
				if(!newValue)
					model.turnFlagOn(bitNum);
				break;
			case CreatureFlags.CANT_RUN:
				if(!newValue)
					model.turnFlagOn(bitNum);
				break;
			}
		} else 
			model.removeFlagObserver(this);
	}

}
