package com.blisek.dnd3manager.dnd3;

import java.util.Collection;
import java.util.Map;
import java.util.function.BiPredicate;

/**
 * Implementacja pasywnego/biernego atutu, skladajacego sie tylko
 * z efektow i predykatow wplywajacych na wynik metody isAvailableFor.
 * @author bartek
 *
 */
public abstract class EffectCombinedFeat extends AbstractFeat {
	protected Collection<BiPredicate<CreatureModel, Map<String,Object>>> requirements;
	protected Collection<EffectPair> effects;
	
	public EffectCombinedFeat(Collection<BiPredicate<CreatureModel, Map<String,Object>>> requirements,
			Collection<EffectPair> effectsWithPredicates) {
		this.requirements = requirements;
		this.effects = effectsWithPredicates;
	}

	@Override
	public boolean isAvailableFor(CreatureModel model,
			Map<String, Object> extraParams) {
		if(requirements == null)
			return true;
		boolean result = true;
		for(BiPredicate<CreatureModel, Map<String,Object>> p : requirements) {
			if(!result)
				break;
			result = result && p.test(model, extraParams);
		}
		return result;
	}

	@Override
	public void turnOnFor(CreatureModel model, Map<String, Object> extraParams) {
		effects.stream().filter(p -> p.first.test(model, extraParams))
			.forEach(p -> p.second.turnOnFor(model, extraParams));
	}

	@Override
	public void turnOffFor(CreatureModel model, Map<String, Object> extraParams) {
		effects.stream().forEach(p -> p.second.turnOffFor(model, extraParams));
	}

	@Override
	public boolean isPassive() {
		return true;
	}

	
	public static class EffectPair extends Pair<BiPredicate<CreatureModel, Map<String,Object>>, Effect> {

		public EffectPair(
				BiPredicate<CreatureModel, Map<String, Object>> first,
				Effect second) {
			super(first, second);
		}
		
	}
}
