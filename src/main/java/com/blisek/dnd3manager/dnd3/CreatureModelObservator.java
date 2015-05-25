package com.blisek.dnd3manager.dnd3;

public interface CreatureModelObservator {
	default void onNewKeyPut(CreatureModel sender, String key, Object oldValue, Object newValue) {}
	default void onKeyReplaced(CreatureModel sender, String key, Object oldValue, Object newValue) {}
	default void onKeyRemoved(CreatureModel sender, String key, Object oldValue) {}
}
