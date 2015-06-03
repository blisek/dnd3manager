package com.blisek.dnd3manager.dnd3;

/**
 * Reprezentuje jednostkę czasu. Dla każdej z
 * jednostek można pobrać ich czas trwania w
 * rundach.
 * @author bartek
 *
 */
public enum TimeUnit {
	/**
	 * Rundy.
	 */
	ROUND(1),
	
	/**
	 * Tura.
	 */
	TOUR(10),
	
	/**
	 * Dzień.
	 */
	DAY(14400),
	
	/**
	 * Tydzień.
	 */
	WEEK(100800),
	
	/**
	 * Rok.
	 */
	YEAR(36792000);
	
	
	private TimeUnit(int durationInRounds) {
		this.roundDuration = durationInRounds;
	}
	
	private final int roundDuration;
	
	public int getRoundDuration() {
		return roundDuration;
	}
}
