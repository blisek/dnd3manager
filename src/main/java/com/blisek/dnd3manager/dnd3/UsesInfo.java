package com.blisek.dnd3manager.dnd3;

/**
 * Reprezentuje dostępną liczbę użyć np. atutu.
 * @author bartek
 *
 */
public final class UsesInfo {
	public final int usesPerTimeUnit;
	public final TimeUnit timeUnit;
	
	public UsesInfo(int usesPerTimeUnit, TimeUnit timeUnit) {
		super();
		this.usesPerTimeUnit = usesPerTimeUnit;
		this.timeUnit = timeUnit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((timeUnit == null) ? 0 : timeUnit.hashCode());
		result = prime * result + usesPerTimeUnit;
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
		UsesInfo other = (UsesInfo) obj;
		if (timeUnit != other.timeUnit)
			return false;
		if (usesPerTimeUnit != other.usesPerTimeUnit)
			return false;
		return true;
	}
	
}
