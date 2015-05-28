package com.blisek.dnd3manager.dnd3.basic.feats;

import java.util.Arrays;
import java.util.Collection;

import com.blisek.dnd3manager.dnd3.AbstractFeat;

public class Feats {
	private static AbstractFeat[] feats = {
		new BlyskawicznyRefleks(),
		new Czujnosc()
	};
	
	public static Collection<AbstractFeat> getFeats() {
		return Arrays.asList(feats);
	}
}
