package com.blisek.dnd3manager.dnd3.converters.infinityengine;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.blisek.dnd3manager.dnd3.CreatureModel;
import com.blisek.dnd3manager.dnd3.converters.CreatureConverter;
import com.blisek.dnd3manager.dnd3.converters.ParseException;

/**
 * Klasa obsługująca pliki CRE z InfinityEngine w wersji 1.0.
 * Wersja ta jest używana w grach Baldur's Gate i Baldur's Gate 2.
 * @author bartek
 *
 */
public class Cre_1_0 implements CreatureConverter {
	private static final String FILE_SIGNATURE = "CRE V1.0";
	
	protected Cre_1_0() {
		
	}
	
	private static boolean checkSignature(DataInputStream dis) throws IOException {
		byte[] tmp = new byte[FILE_SIGNATURE.length()];
		dis.read(tmp);
		String parsed = new String(tmp, StandardCharsets.US_ASCII);
		return FILE_SIGNATURE.equals(parsed);
	}

	@Override
	public CreatureModel convertToCreatureModel(Map<String, Object> extraParams) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Ładuje dane z przekazanego strumienia.
	 * @param inputStream strumień do danych w odpowiednim formacie.
	 * @return sparsowane dane ze źródła.
	 * @throws ParseException jeśli na jakimś etapie parsowania wystąpi błąd odczytu lub
	 * dane będą miały niepoprawny format zostanie rzucony ten wyjątek.
	 */
	public static Cre_1_0 loadFromFile(InputStream inputStream) throws ParseException {
		Cre_1_0 cre = new Cre_1_0();
		try {
			DataInputStream is = new DataInputStream(inputStream);
			if(!checkSignature(is)) {
				throw new ParseException("Wrong CRE file signature");
			}

			// wczytanie offsetu nazwy postaci.
			is.skipBytes(4);
			
			// pominięcie skrótowej nazwy postaci
			is.skipBytes(4);
			
			// informacja, czy postać jest jedno-, dwu- lub wieloklasowa
			cre.classFlags = is.readByte();
			
			// flagi postaci jak np. czy jest to upadły paladyn
			cre.creatureFlags = is.readByte();
			
			// nieużywane
			is.skipBytes(2);
			
			// XP za zabicie tej postaci (niepotrzebne)
			is.skipBytes(4);
			
			// doświadczenie
			cre.experience = is.readInt();
			
			// sztuki złota
			cre.gold = is.readInt();
			
			// stałe flagi postaci (nieśmiertelny, niewidzialny etc.)
			is.skipBytes(4);
			
			// obecne HP
			is.skipBytes(2);
			
			// maksymalne HP
			cre.hp = is.readShort();
			
			// id animacji
			is.skipBytes(2);
			
			// nieużywane
			is.skipBytes(2);
			
			// niepotrzebne
			is.skipBytes(24);
			
			// reputacja
			cre.reputation = is.readByte();
			
			// ukrywanie się
			cre.hideInShadows = is.readByte();
			
			// naturalna klasa pancerza
			cre.AC_natural = is.readShort();
			
			// efektywna klasa pancerza
			cre.AC_effective = is.readShort();
			
			// premia do KP przeciwko obuchowym
			cre.AC_crushing = is.readShort();
			
			// premia do KP przeciwko pociskom
			cre.AC_missile = is.readShort();
			
			// premia do KP przeciwko kłutym
			cre.AC_piercing = is.readShort();
			
			// premia do KP sieczne
			cre.AC_slashing = is.readShort();
			
			// THAC0 (1-25)
			cre.THAC0 = is.readByte();
			
			// liczba ataków
			cre.numOfAttacks = is.readByte();
			
			// rzut obronny przeciwko śmierci
			cre.saveDeath = is.readByte();
			
			// rzut obronny przeciwko różdżkom
			cre.saveWands = is.readByte();
			
			// rzut obronny przeciwko polimorfii
			cre.savePoly = is.readByte();
			
			cre.saveBreath = is.readByte();
			
			// rzut obronny przeciwko czarom
			cre.saveSpell = is.readByte();
			
			// niewrażliwość na ogień (1-100)
			cre.resistFire = is.readByte();
			
			cre.resistCold = is.readByte();
			
			cre.resistElectricity = is.readByte();
			
			cre.resistAcid = is.readByte();
			
			cre.resistMagic = is.readByte();
			
			cre.resistMagicFire = is.readByte();
			
			cre.resistMagicCold = is.readByte();
			
			cre.resistSlashing = is.readByte();
			
			cre.resistCrushing = is.readByte();
			
			cre.resistPiercing = is.readByte();
			
			cre.resistMissile = is.readByte();
			
			// wykrywanie iluzji
			cre.detectIllusion = is.readByte();
			
			// rozstawianie pułapek
			cre.setTraps = is.readByte();
			
			// wiedza
			cre.loreSkill = is.readByte();
			
			// umiejętności łotrzyka
			cre.lockpickingSkill = is.readByte();
			cre.stealthSkill = is.readByte();
			cre.findTrapsSkill = is.readByte();
			cre.pickPocketsSkill = is.readByte();
			
			// zmęczenie
			is.skipBytes(1);
			
			// zatrucie
			is.skipBytes(1);
			
			// szczęście
			cre.luck = is.readByte();
			
			// biegłości
			is.skipBytes(8);
			
			// nieużywane
			is.skipBytes(13);
			
			// tajniki dziczy?
			cre.trackingSkills = is.readByte();
			
			// nieużywane
			is.skipBytes(32);
			
			// dźwięki
			is.skipBytes(400);
			
			// poziomy
			cre.firstClassLevel = is.readByte();
			cre.secondClassLevel = is.readByte();
			cre.thirdClassLevel = is.readByte();
			
			// nieużywane
			is.skipBytes(1);
			
			// atrybuty
			cre.strength = is.readByte();
			is.skipBytes(1);
			cre.intelligence = is.readByte();
			cre.wisdom = is.readByte();
			cre.dexterity = is.readByte();
			cre.constitution = is.readByte();
			cre.charisma = is.readByte();
			
			// morale
			is.skipBytes(2);
			
			// wróg rasowy
			cre.racialEnemy = is.readByte();
			
			is.skipBytes(2);
			
			// informacje o zestawie? (można wyczytać szkołę magii maga bądź
			// specjalizacje w innych klasach z BG2 np. berserker, sługa Talosa)
			cre.creatureKit = is.readInt();
			
			// skrypty
			is.skipBytes(40);
			
			is.skipBytes(1);
			
			// generalne
			cre.general = is.readByte();
			
			// rasa
			cre.race = is.readByte();
			
			// klasa
			cre.classInfo = is.readByte();
			
			// specyficzne
			cre.specific = is.readByte();
			
			// płeć
			cre.gender = is.readByte();
			
			// nieznane
			is.skipBytes(5);
			
			// charakter
			cre.alignment = is.readByte();
			
			// nieznane
			is.skipBytes(4);
			
			// offsety do znanych czarów, przedmiotów itp.
			
		} catch(IOException io) {
			throw new ParseException(io);
		}
		return cre;
	}

	protected byte alignment;
	protected byte general, race, classInfo, specific, gender;
	protected int creatureKit;
	protected byte racialEnemy;
	protected byte strength, dexterity, constitution, intelligence, wisdom, charisma;
	protected byte firstClassLevel, secondClassLevel, thirdClassLevel;
	protected byte trackingSkills;
	protected byte luck;
	protected byte loreSkill;
	protected byte lockpickingSkill, stealthSkill, findTrapsSkill, pickPocketsSkill;
	protected byte classFlags;
	protected byte creatureFlags;
	protected int experience;
	protected int gold;
	protected int hp;
	protected byte reputation;
	protected byte hideInShadows, detectIllusion, setTraps;
	protected short AC_natural;
	protected short AC_effective;
	protected short AC_crushing;
	protected short AC_missile;
	protected short AC_piercing;
	protected short AC_slashing;
	protected byte THAC0;
	protected byte numOfAttacks;
	protected byte saveDeath;
	protected byte saveWands;
	protected byte savePoly;
	protected byte saveBreath;
	protected byte saveSpell;
	protected byte resistFire, resistCold, resistElectricity, resistAcid, resistMagic;
	protected byte resistMagicFire, resistMagicCold, resistSlashing, resistCrushing;
	protected byte resistPiercing, resistMissile;
	
	// flagi
	public static final int FLAG_IS_DUALCLASS = 0;
	public static final int FLAG_ORIGINAL_CLASS_FIGHTER = 8;
	public static final int FLAG_ORIGINAL_CLASS_MAGE = 16;
	public static final int FLAG_ORIGINAL_CLASS_CLERIC = 32;
	public static final int FLAG_ORIGINAL_CLASS_THIEF = 64;
	public static final int FLAG_ORIGINAL_CLASS_DRUID = 128;
	public static final int FLAG_ORIGINAL_CLASS_RANGER = 0;
	public static final int FLAG_FALLEN_PALADIN = 1;
	public static final int FLAG_FALLEN_RANGER = 2;
}
