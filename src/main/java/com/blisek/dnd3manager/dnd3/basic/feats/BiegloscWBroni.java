package com.blisek.dnd3manager.dnd3.basic.feats;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import com.blisek.dnd3manager.dnd3.AbstractFeat;
import com.blisek.dnd3manager.dnd3.CreatureModel;

/**
 * Klasa bazowa dla wszystkich biegłości.
 * @author bartek
 *
 * @param <WeaponEnum> enumeracja z typami broni.
 */
public abstract class BiegloscWBroni<WeaponEnum extends Enum<WeaponEnum>> extends AbstractFeat {
	public static final String P_WEAPON_TYPE = "pweapont";
	public static final String P_WEAPON_TYPE_LIST = "pweapontl";
	private Class<WeaponEnum> weaponEnumClass;

	protected BiegloscWBroni(Class<WeaponEnum> weaponEnumClass) {
		super();
		this.weaponEnumClass = weaponEnumClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void turnOnFor(CreatureModel model, Map<String, Object> extraParams) {
		Map<String, Object> feats = model.getFeatsMap();
		String sysName = getSystemName();
		Object obj = feats.get(sysName);
		Set<WeaponEnum> weaponSet;
		if(obj == null) {
			weaponSet = EnumSet.<WeaponEnum>noneOf(weaponEnumClass);
			feats.put(sysName, weaponSet);
		} else {
			weaponSet = (Set<WeaponEnum>)obj;
		}
		
		if(extraParams.containsKey(P_WEAPON_TYPE_LIST))
			weaponSet.addAll((Collection<WeaponEnum>)extraParams.remove(P_WEAPON_TYPE_LIST));
		
		if(extraParams.containsKey(P_WEAPON_TYPE))
			weaponSet.add((WeaponEnum)extraParams.remove(P_WEAPON_TYPE));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void turnOffFor(CreatureModel model, Map<String, Object> extraParams) {
		Map<String, Object> feats = model.getFeatsMap();
		Object obj = feats.get(getSystemName());
		if(obj == null) return;
		
		Set<WeaponEnum> weapons = (Set<WeaponEnum>)obj;
		
		if(extraParams.containsKey(P_WEAPON_TYPE_LIST))
			weapons.removeAll((Collection<WeaponEnum>)extraParams.remove(P_WEAPON_TYPE_LIST));
		
		if(extraParams.containsKey(P_WEAPON_TYPE))
			weapons.remove((WeaponEnum)extraParams.remove(P_WEAPON_TYPE));
	}

	@Override
	public boolean isPassive() {
		return true;
	}

}
