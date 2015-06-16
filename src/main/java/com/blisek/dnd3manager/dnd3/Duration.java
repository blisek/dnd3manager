package com.blisek.dnd3manager.dnd3;


/**
 * Reprezentuje czas trwania lub liczbę użyć.
 * @author bartek
 *
 */
public class Duration implements Comparable<Duration> {
	// SPECJALNE CZASY
	public static final Duration UNTIL_END_OF_FIGHT = new Duration(-1, TimeUnit.ROUND) {
		@Override
		public boolean equals(Object obj) {
			if(this == obj)
				return true;
			return false;
		}
		
		@Override
		public int hashCode() {
			return System.identityHashCode(this);
		}
	};
	
	public final int duration;
	public final TimeUnit timeUnit;
	
	public Duration(int duration, TimeUnit timeUnit) {
		super();
		this.duration = duration;
		this.timeUnit = timeUnit;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + duration;
		result = prime * result
				+ ((timeUnit == null) ? 0 : timeUnit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Duration other = (Duration) obj;
		if (duration != other.duration)
			return false;
		if (timeUnit != other.timeUnit)
			return false;
		return true;
	}



	@Override
	public int compareTo(Duration o) {
		return Integer.compare(duration * timeUnit.getRoundDuration(), 
				o.duration * o.timeUnit.getRoundDuration());
	}



	public static Duration sum(Duration d1, Duration d2) {
		if(d1 == null || d2 == null)
			throw new NullPointerException();
		
		int roundDuration1 = d1.timeUnit.getRoundDuration();
		int roundDuration2 = d2.timeUnit.getRoundDuration();
		
		if(roundDuration1 < roundDuration2) {
			return new Duration(d1.duration + d2.duration * (roundDuration2 / roundDuration1), d1.timeUnit);
		} else {
			return new Duration(d2.duration + d1.duration * (roundDuration1 / roundDuration2), d2.timeUnit);
		}
	}
	
}
