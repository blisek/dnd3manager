package com.blisek.dnd3manager.dnd3.equipment;

/**
 * Klasa bazowa dla wszystkich przedmiotów, które
 * można używać np. hełmy, płaszcze, buty, miecze etc.
 * @author bartek
 *
 */
public abstract class WearableItem extends Item {
	
	/**
	 * Podaje odpowiednie miejsce w ekwipunku postaci (tym 
	 * używanym na daną chwilę).
	 * @return InventoryPlace.
	 */
	public abstract InventoryPlace getInventoryPlace();
}
