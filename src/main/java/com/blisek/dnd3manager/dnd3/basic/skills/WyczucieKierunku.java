package com.blisek.dnd3manager.dnd3.basic.skills;

import com.blisek.dnd3manager.dnd3.AbstractSkill;
import com.blisek.dnd3manager.dnd3.StringConstants;

public class WyczucieKierunku extends AbstractSkill {
	public static final String SYSTEM_NAME = "wyczuciekierunku";

	@Override
	public String getSystemName() {
		return WyczucieKierunku.SYSTEM_NAME;
	}

	@Override
	public String getKeyAbilityPrefix() {
		return StringConstants.WISDOM;
	}

	@Override
	public boolean trainedOnly() {
		return true;
	}

}
