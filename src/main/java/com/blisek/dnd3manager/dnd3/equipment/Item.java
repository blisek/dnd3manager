package com.blisek.dnd3manager.dnd3.equipment;

import java.util.Map;

import com.blisek.dnd3manager.dnd3.CreatureModel;
import com.blisek.dnd3manager.dnd3.RestrictedSystemObject;

/**
 * Abstrakcyjna klasa dla wszystkich przedmiotów,
 * wliczając np. surowce.
 * @author bartek
 *
 */
public abstract class Item implements RestrictedSystemObject {
	
	/**
	 * Cena/wartość przedmiotu w SZTUKACH MIEDZI.
	 * @return wartość w SM.
	 */
	public abstract long getPrice();
	
	/**
	 * Zwraca typ przedmiotu np. broń, tarcza, minerał.
	 * @return typ z enumeracji ItemType.
	 */
	public abstract ItemType getType();

	@Override
	public boolean isOnFor(CreatureModel model, Map<String, Object> extraParams) {
		return false;
	}

	@Override
	public void turnOnFor(CreatureModel model, Map<String, Object> extraParams) {
	}

	@Override
	public void turnOffFor(CreatureModel model, Map<String, Object> extraParams) {
	}

	@Override
	public boolean isAvailableFor(CreatureModel model,
			Map<String, Object> extraParams) {
		return true;
	}

}
