package com.blisek.dnd3manager.dnd3.basic.skills;

import com.blisek.dnd3manager.dnd3.AbstractSkill;
import com.blisek.dnd3manager.dnd3.StringConstants;

public class Jezdziectwo extends AbstractSkill {
	public static final String SYSTEM_NAME = "jezdziectwo";

	@Override
	public String getSystemName() {
		return Jezdziectwo.SYSTEM_NAME;
	}

	@Override
	public String getKeyAbilityPrefix() {
		return StringConstants.DEXTERITY;
	}

	@Override
	public boolean trainedOnly() {
		return false;
	}

}
