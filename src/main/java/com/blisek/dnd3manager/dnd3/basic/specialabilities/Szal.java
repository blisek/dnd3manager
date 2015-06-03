package com.blisek.dnd3manager.dnd3.basic.specialabilities;

import java.util.Map;

import com.blisek.dnd3manager.dnd3.AbstractSpecialAbility;
import com.blisek.dnd3manager.dnd3.CreatureController;
import com.blisek.dnd3manager.dnd3.CreatureModel;
import com.blisek.dnd3manager.dnd3.Duration;
import com.blisek.dnd3manager.dnd3.ExtraParamsHelper;
import com.blisek.dnd3manager.dnd3.FeatType;
import com.blisek.dnd3manager.dnd3.StringConstants;
import com.blisek.dnd3manager.dnd3.TimeUnit;
import com.blisek.dnd3manager.dnd3.UsesInfo;

public class Szal extends AbstractSpecialAbility {
	public static final String SYSTEM_NAME = "szal";
	public static final String P_ULEPSZONY_SZAL = "ul_szal";
	
	private static final int ELEMENT_USESINFO = 0;
	private static final int ELEMENT_ISIMPROVED = 1;
	private static final int ELEMENT_LEFTUSES = 2;
	
	private static final String PARAM_STR_BONUS = String.format("%s_%s", StringConstants.STRENGTH, Szal.SYSTEM_NAME);
	private static final String PARAM_CON_BONUS = String.format("%s_%s", StringConstants.CONSTITUTION, Szal.SYSTEM_NAME);
	private static final String PARAM_KP_BONUS = String.format("%s_%s", StringConstants.AC, Szal.SYSTEM_NAME);

	@Override
	public boolean isAvailableFor(CreatureModel model, Object... extraParams) {
		return false;
	}

	@Override
	public String getSystemName() {
		return Szal.SYSTEM_NAME;
	}

	@Override
	public void turnOnFor(CreatureModel model, Map<String, Object> extraParams) {
		Object uiObj = extraParams.get(StringConstants.P_USES_COUNT);
		UsesInfo ui = (uiObj == null) ? new UsesInfo(1, TimeUnit.DAY) : (UsesInfo)uiObj;
		// atut przechowuje parę obiektów (UsesInfo, boolean), gdzie pierwszy element
		// jest liczbą użyć, a drugi jeśli jest ustawiony na true oznacza, że
		// postać posiada potężniejszy szał.
		boolean isImproved = ExtraParamsHelper.getBooleanDefaultFalse(extraParams, P_ULEPSZONY_SZAL);
		model.getSpecialAbilitiesMap().put(Szal.SYSTEM_NAME, new Object[] { ui, isImproved, ui.usesPerTimeUnit });
	}

	@Override
	public void turnOffFor(CreatureModel model, Map<String, Object> extraParams) {
		model.getSpecialAbilitiesMap().remove(Szal.SYSTEM_NAME);
	}

	@Override
	public boolean isPassive() {
		return false;
	}

	@Override
	public boolean canBeActivatedFor(CreatureModel model, CreatureController controller,
			Map<String, Object> extraParams) {
		Object[] params = (Object[])model.getSpecialAbilitiesMap().get(Szal.SYSTEM_NAME);
		return (int)params[ELEMENT_LEFTUSES] > 0;
	}

	@Override
	public UsesInfo getUsesCountFor(CreatureModel model, CreatureController controller,
			Map<String, Object> extraParams) {
		Object[] params = (Object[])model.getSpecialAbilitiesMap().get(Szal.SYSTEM_NAME);
		return (UsesInfo)params[ELEMENT_USESINFO];
	}

	@Override
	public int getUsesLeftFor(CreatureModel model, CreatureController controller,
			Map<String, Object> extraParams) {
		Object[] params = (Object[])model.getSpecialAbilitiesMap().get(Szal.SYSTEM_NAME);
		return (int)params[ELEMENT_LEFTUSES];
	}

	@Override
	public void resetUsesLeftCounterFor(CreatureModel model, CreatureController controller,
			Map<String, Object> extraParams) {
		Object[] params = (Object[])model.getSpecialAbilitiesMap().get(Szal.SYSTEM_NAME);
		params[ELEMENT_LEFTUSES] = ((UsesInfo)params[ELEMENT_USESINFO]).usesPerTimeUnit;
	}

	@Override
	public Duration getDurationFor(CreatureModel model, CreatureController controller,
			Map<String, Object> extraParams) {
		return new Duration(3 + controller.getConstitutionMod(false), TimeUnit.ROUND);
	}

	@Override
	public void activateFor(CreatureModel model, CreatureController controller, Map<String, Object> extraParams) {
		Object[] params = (Object[])model.getSpecialAbilitiesMap().get(Szal.SYSTEM_NAME);
		
		if((boolean)params[ELEMENT_ISIMPROVED]) {
			model.put(PARAM_STR_BONUS, 6);
			model.put(PARAM_CON_BONUS, 6);
			ExtraParamsHelper.putIfGreater(model, StringConstants.WILL_MORALE, 3);
			model.put(PARAM_KP_BONUS, -2);
		} else {
			model.put(PARAM_STR_BONUS, 4);
			model.put(PARAM_CON_BONUS, 4);
			ExtraParamsHelper.putIfGreater(model, StringConstants.WILL_MORALE, 2);
			model.put(PARAM_KP_BONUS, -2);
		}
		
		params[ELEMENT_LEFTUSES] = (int)params[ELEMENT_LEFTUSES] - 1;
	}

	@Override
	public void deactivateFor(CreatureModel model, CreatureController controller,
			Map<String, Object> extraParams) {
		Object[] params = (Object[])model.getSpecialAbilitiesMap().get(Szal.SYSTEM_NAME);
		
		if((boolean)params[ELEMENT_ISIMPROVED]) {
			model.remove(StringConstants.WILL_MORALE, 3);
		} else {
			model.remove(StringConstants.WILL_MORALE, 2);
		}
		
		model.remove(PARAM_STR_BONUS);
		model.remove(PARAM_CON_BONUS);
		model.remove(PARAM_KP_BONUS);
		
		params[ELEMENT_LEFTUSES] = (int)params[ELEMENT_LEFTUSES] - 1;
	}

	@Override
	public FeatType getFeatType() {
		return FeatType.FIGHTER_BONUS;
	}


}
