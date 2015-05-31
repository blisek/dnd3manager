package com.blisek.dnd3manager.dnd3;

public interface MapObservator {
	default void onNewKeyPut(Object sender, Object key, Object oldValue, Object newValue) {}
	default void onKeyReplaced(Object sender, Object key, Object oldValue, Object newValue) {}
	default void onKeyRemoved(Object sender, Object key, Object oldValue) {}
}
