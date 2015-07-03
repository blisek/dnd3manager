package com.blisek.dnd3manager.dnd3;

import com.blisek.dnd3manager.dnd3.equipment.ArmorItem;
import com.blisek.dnd3manager.dnd3.equipment.WeaponItem;
import com.blisek.dnd3manager.dnd3.equipment.WearableItem;

/**
 * Ekwipunek obecnie noszony/używany przez postać.
 * Do zastosowania dla humanoidów.
 * @author bartek
 *
 */
public class Inventory {
	// TODO dodać zgłaszanie zdarzeń.
	
	/**
	 * Korony, diademy, hełmy, kapelusze etc.
	 */
	public WearableItem head;
	
	/**
	 * Okulary, soczewki, maski, trzecie oko etc.
	 */
	public WearableItem face;
	
	/**
	 * Amulety, brosze, obroże, naszyjniki,
	 * szarfy etc.
	 */
	public WearableItem throat;
	
	/**
	 * Płaszcze, peleryny etc.
	 */
	public WearableItem shoulders;
	
	/**
	 * Zbroje, stroje etc.
	 */
	public ArmorItem body;
	
	/**
	 * Koszule, tuniki etc.
	 */
	public WearableItem torso;
	
	/**
	 * Opaski, bransolety, karwasze etc.
	 */
	public WearableItem arms;
	
	/**
	 * Rękawice etc.
	 */
	public WearableItem hands;
	
	/**
	 * Pasy etc.
	 */
	public WearableItem waist;
	
	/**
	 * Pierścienie.
	 */
	public WearableItem ring1;
	public WearableItem ring2;
	
	/**
	 * Broń główna.
	 */
	public WeaponItem weapon1;
	
	/**
	 * Broń podręczna, broń używana w drugiej ręce.
	 */
	public WeaponItem weapon2;
	
	/**
	 * Buty.
	 */
	public WearableItem feet;
}
