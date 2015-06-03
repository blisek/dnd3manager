package com.blisek.dnd3manager.dnd3.basic.skills;

import com.blisek.dnd3manager.dnd3.AbstractSkill;
import com.blisek.dnd3manager.dnd3.StringConstants;

public class PostepowanieZeZwierzetami extends AbstractSkill {
	public static final String SYSTEM_NAME = "postzezw";

	@Override
	public String getSystemName() {
		return PostepowanieZeZwierzetami.SYSTEM_NAME;
	}

	@Override
	public String getKeyAbilityPrefix() {
		return StringConstants.CHARISMA;
	}

	@Override
	public boolean trainedOnly() {
		return true;
	}

}
