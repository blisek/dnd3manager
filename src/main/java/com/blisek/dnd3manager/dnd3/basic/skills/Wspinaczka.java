package com.blisek.dnd3manager.dnd3.basic.skills;

import com.blisek.dnd3manager.dnd3.AbstractSkill;
import com.blisek.dnd3manager.dnd3.StringConstants;

public class Wspinaczka extends AbstractSkill {
	public static final String SYSTEM_NAME = "wspinaczka";

	@Override
	public String getSystemName() {
		return Wspinaczka.SYSTEM_NAME;
	}

	@Override
	public String getKeyAbilityPrefix() {
		return StringConstants.STRENGTH;
	}

	@Override
	public boolean trainedOnly() {
		return false;
	}

	@Override
	public boolean armorCheckPenalty() {
		return true;
	}

}
