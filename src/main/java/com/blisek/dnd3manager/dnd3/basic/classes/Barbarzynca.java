package com.blisek.dnd3manager.dnd3.basic.classes;

import static com.blisek.dnd3manager.dnd3.ExtraParamsHelper.getBooleanDefaultFalse;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.blisek.dnd3manager.dnd3.AbstractClass;
import com.blisek.dnd3manager.dnd3.AbstractSpecialAbility;
import com.blisek.dnd3manager.dnd3.Context;
import com.blisek.dnd3manager.dnd3.CreatureFlags;
import com.blisek.dnd3manager.dnd3.CreatureHelper;
import com.blisek.dnd3manager.dnd3.CreatureModel;
import com.blisek.dnd3manager.dnd3.DamageType;
import com.blisek.dnd3manager.dnd3.ExtraParamsHelper;
import com.blisek.dnd3manager.dnd3.MartialWeapons;
import com.blisek.dnd3manager.dnd3.NumericConstants;
import com.blisek.dnd3manager.dnd3.SimpleWeapons;
import com.blisek.dnd3manager.dnd3.StringConstants;
import com.blisek.dnd3manager.dnd3.TimeUnit;
import com.blisek.dnd3manager.dnd3.UsesInfo;
import com.blisek.dnd3manager.dnd3.basic.feats.BiegloscWBroni;
import com.blisek.dnd3manager.dnd3.basic.feats.BiegloscWBroniProstej;
import com.blisek.dnd3manager.dnd3.basic.feats.BiegloscWBroniZolnierskiej;
import com.blisek.dnd3manager.dnd3.basic.feats.BiegloscWPancerzuCiezkim;
import com.blisek.dnd3manager.dnd3.basic.feats.BiegloscWPancerzuLekkim;
import com.blisek.dnd3manager.dnd3.basic.feats.BiegloscWPancerzuSrednim;
import com.blisek.dnd3manager.dnd3.basic.skills.Jezdziectwo;
import com.blisek.dnd3manager.dnd3.basic.skills.Nasluchiwanie;
import com.blisek.dnd3manager.dnd3.basic.skills.Plywanie;
import com.blisek.dnd3manager.dnd3.basic.skills.PostepowanieZeZwierzetami;
import com.blisek.dnd3manager.dnd3.basic.skills.RzemiosloInne;
import com.blisek.dnd3manager.dnd3.basic.skills.RzemiosloPlatnerstwo;
import com.blisek.dnd3manager.dnd3.basic.skills.RzemiosloWytwarzanieBroni;
import com.blisek.dnd3manager.dnd3.basic.skills.RzemiosloWytwarzanieLukow;
import com.blisek.dnd3manager.dnd3.basic.skills.Skakanie;
import com.blisek.dnd3manager.dnd3.basic.skills.Wspinaczka;
import com.blisek.dnd3manager.dnd3.basic.skills.WyczucieKierunku;
import com.blisek.dnd3manager.dnd3.basic.skills.Zastraszanie;
import com.blisek.dnd3manager.dnd3.basic.skills.ZnajomoscDziczy;
import com.blisek.dnd3manager.dnd3.basic.specialabilities.NieswiadomyUnik;
import com.blisek.dnd3manager.dnd3.basic.specialabilities.Szal;
import com.blisek.dnd3manager.dnd3.basic.specialabilities.SzybkiePoruszanie;

public class Barbarzynca extends AbstractClass {
	public static final String SYSTEM_NAME = "barbarzynca";
	public static final String[] CLASS_SKILLS = { Jezdziectwo.SYSTEM_NAME,
			Nasluchiwanie.SYSTEM_NAME, Plywanie.SYSTEM_NAME,
			PostepowanieZeZwierzetami.SYSTEM_NAME, RzemiosloInne.SYSTEM_NAME,
			RzemiosloPlatnerstwo.SYSTEM_NAME,
			RzemiosloWytwarzanieBroni.SYSTEM_NAME,
			RzemiosloWytwarzanieLukow.SYSTEM_NAME, Skakanie.SYSTEM_NAME,
			Wspinaczka.SYSTEM_NAME, WyczucieKierunku.SYSTEM_NAME,
			Zastraszanie.SYSTEM_NAME, ZnajomoscDziczy.SYSTEM_NAME };

	public static final String[] ABILITIES_PRIORITY = {
			StringConstants.STRENGTH, StringConstants.DEXTERITY,
			StringConstants.CONSTITUTION, StringConstants.WISDOM,
			StringConstants.CHARISMA, StringConstants.INTELLIGENCE };

	@Override
	public boolean isAvailableFor(CreatureModel model,
			Map<String, Object> extraParams) {
		int alignment = ExtraParamsHelper.getIntegerDefault0(model,
				StringConstants.ALIGNMENT);
		return (alignment & getAlignmentsFlag()) > 0;
	}

	@Override
	public String getSystemName() {
		return Barbarzynca.SYSTEM_NAME;
	}

	@Override
	public boolean isOnFor(CreatureModel model, Map<String, Object> extraParams) {
		return model.sumLevelsIn(Barbarzynca.SYSTEM_NAME) > 0;
	}

	@Override
	public void turnOnFor(CreatureModel model, Map<String, Object> extraParams) {
		// pierwszy poziom w klasie
		if (getBooleanDefaultFalse(extraParams, StringConstants.P_FIRST_LEVEL)) {
			firstLevelEver(model, extraParams); // dla poziomu 1. dla całej
												// postaci

		} else { // kolejne poziomy w klasie, oczekiwany parametr P_LEVEL
			nextLevel(model, extraParams); // dla poziomów od 1. do ..
		}
	}

	private void nextLevel(CreatureModel model, Map<String, Object> extraParams) {
		// poziom postaci
		int level = model.sumLevelsIn(Barbarzynca.SYSTEM_NAME);

		int tmp;
		// bazowa premia do ataku
		tmp = getBaseAttackMod(level + 1) - getBaseAttackMod(level);
		if (tmp > 0)
			ExtraParamsHelper.increaseInteger(model,
					StringConstants.BASE_ATTACK, tmp);

		// rzuty obronne
		// WYTRWAŁOŚĆ
		tmp = getFortitudeMod(level + 1) - getFortitudeMod(level);
		if (tmp > 0)
			ExtraParamsHelper.increaseInteger(model, StringConstants.FORTITUDE,
					tmp);

		// REFLEKS
		tmp = getReflexMod(level + 1) - getReflexMod(level);
		if (tmp > 0)
			ExtraParamsHelper.increaseInteger(model, StringConstants.REFLEX,
					tmp);

		// WOLA
		tmp = getWillMod(level + 1) - getWillMod(level);
		if (tmp > 0)
			ExtraParamsHelper.increaseInteger(model, StringConstants.WILL, tmp);

		// UMIEJĘTNOŚCI
		tmp = getSkillRanksForLevel()
				+ CreatureHelper.calculateMod(model,
						StringConstants.INTELLIGENCE);
		if (tmp > 0)
			ExtraParamsHelper.increaseSkillsRanks(model, tmp);

		assignSpecialFeats(model, extraParams, level + 1);

		model.addLevelTo(Barbarzynca.SYSTEM_NAME);
	}

	private void assignSpecialFeats(CreatureModel model,
			Map<String, Object> extraParams, int level) {
		
		Szal szal = new Szal();

		// SZAŁ
		int rageUses = (level / 4) + 1, prevRageUses = (level - 1) / 4 + 1;
		if (rageUses - prevRageUses > 0 || level == 1) {
			extraParams.put(StringConstants.P_USES_COUNT, new UsesInfo(
					rageUses, TimeUnit.DAY));
			szal.turnOnFor(model, extraParams);
		}

		// POTĘŻNIEJSZY SZAŁ
		if (level == 15) {
			extraParams.put(StringConstants.P_USES_COUNT, new UsesInfo(
					rageUses, TimeUnit.DAY));
			extraParams.put(Szal.P_ULEPSZONY_SZAL, true);
			szal.turnOnFor(model, extraParams);
		}

		// BEZ ZMĘCZENIA
		if (level == 20) {
			extraParams.put(StringConstants.P_USES_COUNT, new UsesInfo(
					rageUses, TimeUnit.DAY));
			extraParams.put(Szal.P_ULEPSZONY_SZAL, true);
			extraParams.put(Szal.P_BEZ_ZMECZENIA, true);
			szal.turnOnFor(model, extraParams);
		}

		// Szybkie poruszanie się i biegłości.
		if (level == 1) {
			new SzybkiePoruszanie().turnOnFor(model, extraParams);

			extraParams.put(BiegloscWBroni.P_WEAPON_TYPE_LIST,
					Arrays.asList(SimpleWeapons.values()));
			new BiegloscWBroniProstej().turnOnFor(model, extraParams);
			extraParams.put(BiegloscWBroni.P_WEAPON_TYPE_LIST,
					Arrays.asList(MartialWeapons.values()));
			new BiegloscWBroniZolnierskiej().turnOnFor(model, extraParams);

			// pancerze
			new BiegloscWPancerzuLekkim().turnOnFor(model, extraParams);
			new BiegloscWPancerzuSrednim().turnOnFor(model, extraParams);
			new BiegloscWPancerzuCiezkim().turnOnFor(model, extraParams);
		}

		// Zachowuje premię do KP gdy zaskoczony
		if (level == 2)
			model.turnFlagOn(CreatureFlags.DONT_LOOSE_DEX_AC_BONUS);

		// Nie można flankować
		if (level == 5)
			model.turnFlagOn(CreatureFlags.CANNOT_BE_FLANKED);

		// TODO Nieświadomy unik?
		// nieświadomy unik
		if((level - 10) % 3 == 0) {
			NieswiadomyUnik nu = new NieswiadomyUnik();
			a;
		}

		// Redukcja obrażeń
		if (level >= 11 && (level - 11) % 3 == 0) {
			int dr = (level - 11) / 3 + 1;
			if (model.getDamageReduction(DamageType.ALL_TYPES) < dr)
				model.setDamageReduction(DamageType.ALL_TYPES, dr);
		}
	}

	private void firstLevelEver(CreatureModel model,
			Map<String, Object> extraParams) {
		int ranks = (getSkillRanksForLevel() * CreatureHelper.calculateMod(
				model, StringConstants.INTELLIGENCE)) * 4;
		ExtraParamsHelper.increaseSkillsRanks(extraParams, ranks);

		model.put(StringConstants.BASE_ATTACK, 1);
		model.put(StringConstants.FORTITUDE, 2);
		model.put(StringConstants.REFLEX, 0);
		model.put(StringConstants.WILL, 0);

		Map<String, AbstractSpecialAbility> specAbilities = Context.INSTANCE
				.getSpecialAbilities();
		specAbilities.get(SzybkiePoruszanie.SYSTEM_NAME).turnOnFor(model,
				extraParams);
		specAbilities.get(Szal.SYSTEM_NAME).turnOnFor(model, extraParams);

		extraParams.put(BiegloscWBroni.P_WEAPON_TYPE_LIST,
				Arrays.asList(SimpleWeapons.values()));
		new BiegloscWBroniProstej().turnOnFor(model, extraParams);
		extraParams.put(BiegloscWBroni.P_WEAPON_TYPE_LIST,
				Arrays.asList(MartialWeapons.values()));
		new BiegloscWBroniZolnierskiej().turnOnFor(model, extraParams);

		// pancerze
		new BiegloscWPancerzuLekkim().turnOnFor(model, extraParams);
		new BiegloscWPancerzuSrednim().turnOnFor(model, extraParams);
		new BiegloscWPancerzuCiezkim().turnOnFor(model, extraParams);

		model.addLevelTo(Barbarzynca.SYSTEM_NAME);
	}

	@Override
	public void turnOffFor(CreatureModel model, Map<String, Object> extraParams) {
		throw new NotImplementedException();
	}

	@Override
	public int getHitDice() {
		return 12;
	}

	@Override
	public int getSkillRanksForLevel() {
		return 4;
	}

	@Override
	public int getFortitudeMod(int level) {
		return (level / 2) + 2;
	}

	@Override
	public int getReflexMod(int level) {
		return level / 3;
	}

	@Override
	public int getWillMod(int level) {
		return level / 3;
	}

	@Override
	public int getBaseAttackMod(int level) {
		return Math.min(level, 20);
	}

	@Override
	public int getAttacksCount(int level) {
		return Math.min((level - 1) / 5 + 1, 4);
	}

	@Override
	public Collection<String> getClassSkills() {
		return Arrays.asList(Barbarzynca.CLASS_SKILLS);
	}

	@Override
	public Collection<String> getAbilitiesByPriorityDescending() {
		return Arrays.asList(Barbarzynca.ABILITIES_PRIORITY);
	}

	@Override
	public int getAlignmentsFlag() {
		return NumericConstants.AL_CHAOTIC_EVIL
				| NumericConstants.AL_CHAOTIC_GOOD
				| NumericConstants.AL_CHAOTIC_NEUTRAL
				| NumericConstants.AL_NEUTRAL
				| NumericConstants.AL_NEUTRAL_EVIL
				| NumericConstants.AL_NEUTRAL_GOOD;
	}

}
