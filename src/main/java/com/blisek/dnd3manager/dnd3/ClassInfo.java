package com.blisek.dnd3manager.dnd3;

public final class ClassInfo {

	public ClassInfo(String systemName, int level) {
		super();
		if(systemName == null)
			throw new NullPointerException("systemName");
		this.systemName = systemName;
		if(level <= 0)
			throw new IllegalArgumentException("level");
		this.level = level;
	}

	public final String systemName;
	public final int level;
}
