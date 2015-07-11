package com.blisek.dnd3manager.dnd3.fight;

public enum ActionType {
	/**
	 * Inny typ akcji.
	 */
	OTHER,
	/**
	 * Akcja ruchu.
	 */
	MOVE,
	/**
	 * Atak.
	 */
	OFFENSIVE,
	/**
	 * Obrona, np. przyjecie postawy defensywnej.
	 */
	DEFENSIVE,
	/**
	 * Atak zakleciem.
	 */
	OFFENSIVE_SPELL,
	/**
	 * Zaklecie obronne, np. Tarcza,
	 * ale takze Leczenie lekkich ran itd.
	 */
	DEFENSIVE_SPELL,
	
}
