package com.blisek.dnd3manager.dnd3.basic.specialabilities;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.blisek.dnd3manager.dnd3.AbstractSpecialAbility;
import com.blisek.dnd3manager.dnd3.CreatureModel;
import com.blisek.dnd3manager.dnd3.ExtraParamsHelper;
import com.blisek.dnd3manager.dnd3.ObservableMap;

public class NieswiadomyUnik extends AbstractSpecialAbility {
	public static final String SYSTEM_NAME = "nieswunik";
	public static final String P_BONUS_TYPE = "nubontype";
	public static final String P_BONUS_VALUE = "nubonval";

	@Override
	public boolean isAvailableFor(CreatureModel model,
			Map<String, Object> extraParams) {
		// tej specjalnej zdolności nie może wybrać nikt, przychodzi z pewnymi klasami.
		return false;
	}

	@Override
	public String getSystemName() {
		return SYSTEM_NAME;
	}

	// TODO Poprawić
	@Override
	public void turnOnFor(CreatureModel model, Map<String, Object> extraParams) {
		ObservableMap<Integer, List<Integer>> bonuses = 
				(ObservableMap<Integer,List<Integer>>)ExtraParamsHelper
				.<Integer, List<Integer>>getMapOrCreateOne(model.getSpecialAbilitiesMap(), SYSTEM_NAME);
		int typ = ExtraParamsHelper.takeIntegerDefault0(extraParams, P_BONUS_TYPE);
		List<Integer> list = ExtraParamsHelper.getListOrCreateNewOne(bonuses, typ);
		int val = ExtraParamsHelper.takeIntegerDefault0(extraParams, P_BONUS_VALUE);
		list.add(val);
		list.sort(Comparator.reverseOrder());
	}

	@Override
	public void turnOffFor(CreatureModel model, Map<String, Object> extraParams) {
		List<Integer> list = ExtraParamsHelper.getListOrCreateNewOne(
				ExtraParamsHelper.<Integer, List<Integer>>getMapOrCreateOne(model.getSpecialAbilitiesMap(), SYSTEM_NAME),
				ExtraParamsHelper.takeIntegerDefault0(extraParams, P_BONUS_TYPE));
		list.remove((Object)ExtraParamsHelper.takeIntegerDefault0(extraParams, P_BONUS_VALUE));
		list.sort(Comparator.reverseOrder());
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean isOnFor(CreatureModel model, Map<String, Object> extraParams) {
		Object obj = model.getSpecialAbilitiesMap().get(SYSTEM_NAME);
		if(obj == null)
			return false;
		int type = ExtraParamsHelper.getIntegerDefault0(extraParams, P_BONUS_TYPE);
		return ((Map<Integer, List<Integer>>)obj).containsKey(type);
	}

	@Override
	public boolean isPassive() {
		return true;
	}
	
	
	public static class BonusType {
		
		public static final int OTHER = 0;
		
		public static final int AGAINST_TRAPS = 1;
	}
}
