package com.blisek.dnd3manager.dnd3.basic.skills;

import com.blisek.dnd3manager.dnd3.AbstractSkill;
import com.blisek.dnd3manager.dnd3.StringConstants;

public class Nasluchiwanie extends AbstractSkill {
	public static final String SYSTEM_NAME = "nasluchiwanie";

	@Override
	public boolean trainedOnly() {
		return false;
	}

	@Override
	public String getSystemName() {
		return Nasluchiwanie.SYSTEM_NAME;
	}

	@Override
	public String getKeyAbilityPrefix() {
		return StringConstants.WISDOM;
	}

}
