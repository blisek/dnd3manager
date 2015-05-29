package com.blisek.dnd3manager.dnd3.basic.feats;

import java.util.Map;

import com.blisek.dnd3manager.dnd3.AbstractFeat;
import com.blisek.dnd3manager.dnd3.CreatureModel;
import com.blisek.dnd3manager.dnd3.basic.skills.Nasluchiwanie;
import com.blisek.dnd3manager.dnd3.basic.skills.Zauwazanie;

public class Czujnosc extends AbstractFeat {
	public static final String SYSTEM_NAME = "czujnosc";

	@Override
	public boolean isAvailableFor(CreatureModel model, Object... extraParams) {
		return !model.getFeatsMap().containsKey(getSystemName());
	}

	@Override
	public String getSystemName() {
		return Czujnosc.SYSTEM_NAME;
	}

	@Override
	public void turnOnFor(CreatureModel model, Map<String, Object> extraParams) {
		Map<String, Object> feats = model.getFeatsMap();
		Map<String, Integer> skills = model.getSkillsMap();
		
		Integer tmp = skills.get(Nasluchiwanie.SYSTEM_NAME);
		tmp = (tmp == null) ? 2 : tmp + 2;
		skills.put(Nasluchiwanie.SYSTEM_NAME, tmp);
		
		tmp = skills.get(Zauwazanie.SYSTEM_NAME);
		tmp = (tmp == null) ? 2 : tmp + 2;
		skills.put(Zauwazanie.SYSTEM_NAME, tmp);
		
		feats.put(getSystemName(), 1);
	}

	@Override
	public void turnOffFor(CreatureModel model, Map<String, Object> extraParams) {
		Map<String, Integer> skills = model.getSkillsMap();
		
		Integer tmp = skills.get(Nasluchiwanie.SYSTEM_NAME) - 2;
		skills.put(Nasluchiwanie.SYSTEM_NAME, tmp);
		
		tmp = skills.get(Zauwazanie.SYSTEM_NAME) - 2;
		skills.put(Zauwazanie.SYSTEM_NAME, tmp);
		
		model.getFeatsMap().remove(getSystemName());
	}

	@Override
	public boolean isPassive() {
		return true;
	}

}
