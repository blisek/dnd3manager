package com.blisek.dnd3manager.dnd3;

import java.util.Map;

/**
 * Pojedynczy efekt. Efekt jest częścią większego obiektu.
 * Np. atut Czujnosc sklada sie z dwoch efektow: +2 do 
 * Nasluchiwania i +2 do Zauwazania.
 * @author bartek
 *
 */
public class Effect implements SystemObject, MapObservator {
	protected static int uniqueCounter = 0;
	protected final String paramName;
	protected final Object value;
	
	public Effect(String prefixName, Object value) {
		if(prefixName == null || value == null)
			throw new NullPointerException();
		paramName = String.format("%s_%s", prefixName, value.toString());
		this.value = value; 
	}

	@Override
	public boolean isOnFor(CreatureModel model, Map<String, Object> extraParams) {
		return model.containsKey(paramName);
	}

	@Override
	public void turnOnFor(CreatureModel model, Map<String, Object> extraParams) {
		model.put(paramName, value);
		model.addObservator(this);
	}

	@Override
	public void turnOffFor(CreatureModel model, Map<String, Object> extraParams) {
		model.removeObservator(this);
		model.remove(paramName);
	}

	@Override
	public void onKeyReplaced(Object sender, Object key, Object oldValue,
			Object newValue) {
		if(!value.equals(newValue))
			((CreatureModel)sender).replace(paramName, value);
	}

	@Override
	public void onKeyRemoved(Object sender, Object key, Object oldValue) {
		if(key.equals(paramName))
			((CreatureModel)sender).put(paramName, value);
	}

	@Override
	public String getSystemName() {
		return "eff" + paramName;
	}
	
	
}
