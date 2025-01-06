package com.example.schrecknet.sheetdb;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CharacterSheetDAO_Impl implements CharacterSheetDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CharacterSheet> __insertionAdapterOfCharacterSheet;

  private final EntityDeletionOrUpdateAdapter<CharacterSheet> __deletionAdapterOfCharacterSheet;

  public CharacterSheetDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCharacterSheet = new EntityInsertionAdapter<CharacterSheet>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `CharacterSheet` (`name`,`player`,`chronicle`,`concept`,`ambition`,`predator`,`sire`,`clan`,`generation`,`strength`,`dexterity`,`stamina`,`charisma`,`manipulation`,`composure`,`intelligence`,`wits`,`resolve`,`athletics`,`brawl`,`craft`,`drive`,`firearms`,`melee`,`larceny`,`stealth`,`survival`,`animalKen`,`etiquette`,`insight`,`intimidation`,`leadership`,`performance`,`persuasion`,`streetwise`,`subterfuge`,`academics`,`awareness`,`finance`,`investigation`,`medicine`,`occult`,`politics`,`science`,`technology`,`chronicleTenets`,`touchstonesAndConvictions`,`clanBane`,`totalHealth`,`superficialDamageHealth`,`aggravatedDamageHealth`,`totalWillpower`,`superficialDamageWillpower`,`aggravatedDamageWillpower`,`totalHumanity`,`damageHumanity`,`bloodPotency`,`bloodSurges`,`mendAmount`,`powerBonus`,`rouseReRoll`,`feedingPenaly`,`baneSeverity`,`resonance`,`hunting`,`expTotal`,`expSpent`,`hunger`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CharacterSheet entity) {
        statement.bindString(1, entity.getName());
        statement.bindString(2, entity.getPlayer());
        statement.bindString(3, entity.getChronicle());
        statement.bindString(4, entity.getConcept());
        statement.bindString(5, entity.getAmbition());
        statement.bindString(6, entity.getPredator());
        statement.bindString(7, entity.getSire());
        statement.bindString(8, entity.getClan());
        statement.bindString(9, entity.getGeneration());
        statement.bindLong(10, entity.getStrength());
        statement.bindLong(11, entity.getDexterity());
        statement.bindLong(12, entity.getStamina());
        statement.bindLong(13, entity.getCharisma());
        statement.bindLong(14, entity.getManipulation());
        statement.bindLong(15, entity.getComposure());
        statement.bindLong(16, entity.getIntelligence());
        statement.bindLong(17, entity.getWits());
        statement.bindLong(18, entity.getResolve());
        statement.bindLong(19, entity.getAthletics());
        statement.bindLong(20, entity.getBrawl());
        statement.bindLong(21, entity.getCraft());
        statement.bindLong(22, entity.getDrive());
        statement.bindLong(23, entity.getFirearms());
        statement.bindLong(24, entity.getMelee());
        statement.bindLong(25, entity.getLarceny());
        statement.bindLong(26, entity.getStealth());
        statement.bindLong(27, entity.getSurvival());
        statement.bindLong(28, entity.getAnimalKen());
        statement.bindLong(29, entity.getEtiquette());
        statement.bindLong(30, entity.getInsight());
        statement.bindLong(31, entity.getIntimidation());
        statement.bindLong(32, entity.getLeadership());
        statement.bindLong(33, entity.getPerformance());
        statement.bindLong(34, entity.getPersuasion());
        statement.bindLong(35, entity.getStreetwise());
        statement.bindLong(36, entity.getSubterfuge());
        statement.bindLong(37, entity.getAcademics());
        statement.bindLong(38, entity.getAwareness());
        statement.bindLong(39, entity.getFinance());
        statement.bindLong(40, entity.getInvestigation());
        statement.bindLong(41, entity.getMedicine());
        statement.bindLong(42, entity.getOccult());
        statement.bindLong(43, entity.getPolitics());
        statement.bindLong(44, entity.getScience());
        statement.bindLong(45, entity.getTechnology());
        statement.bindString(46, entity.getChronicleTenets());
        statement.bindString(47, entity.getTouchstonesAndConvictions());
        statement.bindString(48, entity.getClanBane());
        statement.bindLong(49, entity.getTotalHealth());
        statement.bindLong(50, entity.getSuperficialDamageHealth());
        statement.bindLong(51, entity.getAggravatedDamageHealth());
        statement.bindLong(52, entity.getTotalWillpower());
        statement.bindLong(53, entity.getSuperficialDamageWillpower());
        statement.bindLong(54, entity.getAggravatedDamageWillpower());
        statement.bindLong(55, entity.getTotalHumanity());
        statement.bindLong(56, entity.getDamageHumanity());
        statement.bindLong(57, entity.getBloodPotency());
        statement.bindString(58, entity.getBloodSurges());
        statement.bindString(59, entity.getMendAmount());
        statement.bindString(60, entity.getPowerBonus());
        statement.bindString(61, entity.getRouseReRoll());
        statement.bindString(62, entity.getFeedingPenaly());
        statement.bindString(63, entity.getBaneSeverity());
        statement.bindString(64, entity.getResonance());
        statement.bindString(65, entity.getHunting());
        statement.bindLong(66, entity.getExpTotal());
        statement.bindLong(67, entity.getExpSpent());
        statement.bindLong(68, entity.getHunger());
      }
    };
    this.__deletionAdapterOfCharacterSheet = new EntityDeletionOrUpdateAdapter<CharacterSheet>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `CharacterSheet` WHERE `name` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CharacterSheet entity) {
        statement.bindString(1, entity.getName());
      }
    };
  }

  @Override
  public Object insertCharacterSheet(final CharacterSheet characterSheet,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfCharacterSheet.insertAndReturnId(characterSheet);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteCharacterSheet(final CharacterSheet characterSheet,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfCharacterSheet.handle(characterSheet);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getCharacterSheetByName(final String name,
      final Continuation<? super CharacterSheet> $completion) {
    final String _sql = "SELECT * FROM CharacterSheet WHERE name = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, name);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<CharacterSheet>() {
      @Override
      @Nullable
      public CharacterSheet call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPlayer = CursorUtil.getColumnIndexOrThrow(_cursor, "player");
          final int _cursorIndexOfChronicle = CursorUtil.getColumnIndexOrThrow(_cursor, "chronicle");
          final int _cursorIndexOfConcept = CursorUtil.getColumnIndexOrThrow(_cursor, "concept");
          final int _cursorIndexOfAmbition = CursorUtil.getColumnIndexOrThrow(_cursor, "ambition");
          final int _cursorIndexOfPredator = CursorUtil.getColumnIndexOrThrow(_cursor, "predator");
          final int _cursorIndexOfSire = CursorUtil.getColumnIndexOrThrow(_cursor, "sire");
          final int _cursorIndexOfClan = CursorUtil.getColumnIndexOrThrow(_cursor, "clan");
          final int _cursorIndexOfGeneration = CursorUtil.getColumnIndexOrThrow(_cursor, "generation");
          final int _cursorIndexOfStrength = CursorUtil.getColumnIndexOrThrow(_cursor, "strength");
          final int _cursorIndexOfDexterity = CursorUtil.getColumnIndexOrThrow(_cursor, "dexterity");
          final int _cursorIndexOfStamina = CursorUtil.getColumnIndexOrThrow(_cursor, "stamina");
          final int _cursorIndexOfCharisma = CursorUtil.getColumnIndexOrThrow(_cursor, "charisma");
          final int _cursorIndexOfManipulation = CursorUtil.getColumnIndexOrThrow(_cursor, "manipulation");
          final int _cursorIndexOfComposure = CursorUtil.getColumnIndexOrThrow(_cursor, "composure");
          final int _cursorIndexOfIntelligence = CursorUtil.getColumnIndexOrThrow(_cursor, "intelligence");
          final int _cursorIndexOfWits = CursorUtil.getColumnIndexOrThrow(_cursor, "wits");
          final int _cursorIndexOfResolve = CursorUtil.getColumnIndexOrThrow(_cursor, "resolve");
          final int _cursorIndexOfAthletics = CursorUtil.getColumnIndexOrThrow(_cursor, "athletics");
          final int _cursorIndexOfBrawl = CursorUtil.getColumnIndexOrThrow(_cursor, "brawl");
          final int _cursorIndexOfCraft = CursorUtil.getColumnIndexOrThrow(_cursor, "craft");
          final int _cursorIndexOfDrive = CursorUtil.getColumnIndexOrThrow(_cursor, "drive");
          final int _cursorIndexOfFirearms = CursorUtil.getColumnIndexOrThrow(_cursor, "firearms");
          final int _cursorIndexOfMelee = CursorUtil.getColumnIndexOrThrow(_cursor, "melee");
          final int _cursorIndexOfLarceny = CursorUtil.getColumnIndexOrThrow(_cursor, "larceny");
          final int _cursorIndexOfStealth = CursorUtil.getColumnIndexOrThrow(_cursor, "stealth");
          final int _cursorIndexOfSurvival = CursorUtil.getColumnIndexOrThrow(_cursor, "survival");
          final int _cursorIndexOfAnimalKen = CursorUtil.getColumnIndexOrThrow(_cursor, "animalKen");
          final int _cursorIndexOfEtiquette = CursorUtil.getColumnIndexOrThrow(_cursor, "etiquette");
          final int _cursorIndexOfInsight = CursorUtil.getColumnIndexOrThrow(_cursor, "insight");
          final int _cursorIndexOfIntimidation = CursorUtil.getColumnIndexOrThrow(_cursor, "intimidation");
          final int _cursorIndexOfLeadership = CursorUtil.getColumnIndexOrThrow(_cursor, "leadership");
          final int _cursorIndexOfPerformance = CursorUtil.getColumnIndexOrThrow(_cursor, "performance");
          final int _cursorIndexOfPersuasion = CursorUtil.getColumnIndexOrThrow(_cursor, "persuasion");
          final int _cursorIndexOfStreetwise = CursorUtil.getColumnIndexOrThrow(_cursor, "streetwise");
          final int _cursorIndexOfSubterfuge = CursorUtil.getColumnIndexOrThrow(_cursor, "subterfuge");
          final int _cursorIndexOfAcademics = CursorUtil.getColumnIndexOrThrow(_cursor, "academics");
          final int _cursorIndexOfAwareness = CursorUtil.getColumnIndexOrThrow(_cursor, "awareness");
          final int _cursorIndexOfFinance = CursorUtil.getColumnIndexOrThrow(_cursor, "finance");
          final int _cursorIndexOfInvestigation = CursorUtil.getColumnIndexOrThrow(_cursor, "investigation");
          final int _cursorIndexOfMedicine = CursorUtil.getColumnIndexOrThrow(_cursor, "medicine");
          final int _cursorIndexOfOccult = CursorUtil.getColumnIndexOrThrow(_cursor, "occult");
          final int _cursorIndexOfPolitics = CursorUtil.getColumnIndexOrThrow(_cursor, "politics");
          final int _cursorIndexOfScience = CursorUtil.getColumnIndexOrThrow(_cursor, "science");
          final int _cursorIndexOfTechnology = CursorUtil.getColumnIndexOrThrow(_cursor, "technology");
          final int _cursorIndexOfChronicleTenets = CursorUtil.getColumnIndexOrThrow(_cursor, "chronicleTenets");
          final int _cursorIndexOfTouchstonesAndConvictions = CursorUtil.getColumnIndexOrThrow(_cursor, "touchstonesAndConvictions");
          final int _cursorIndexOfClanBane = CursorUtil.getColumnIndexOrThrow(_cursor, "clanBane");
          final int _cursorIndexOfTotalHealth = CursorUtil.getColumnIndexOrThrow(_cursor, "totalHealth");
          final int _cursorIndexOfSuperficialDamageHealth = CursorUtil.getColumnIndexOrThrow(_cursor, "superficialDamageHealth");
          final int _cursorIndexOfAggravatedDamageHealth = CursorUtil.getColumnIndexOrThrow(_cursor, "aggravatedDamageHealth");
          final int _cursorIndexOfTotalWillpower = CursorUtil.getColumnIndexOrThrow(_cursor, "totalWillpower");
          final int _cursorIndexOfSuperficialDamageWillpower = CursorUtil.getColumnIndexOrThrow(_cursor, "superficialDamageWillpower");
          final int _cursorIndexOfAggravatedDamageWillpower = CursorUtil.getColumnIndexOrThrow(_cursor, "aggravatedDamageWillpower");
          final int _cursorIndexOfTotalHumanity = CursorUtil.getColumnIndexOrThrow(_cursor, "totalHumanity");
          final int _cursorIndexOfDamageHumanity = CursorUtil.getColumnIndexOrThrow(_cursor, "damageHumanity");
          final int _cursorIndexOfBloodPotency = CursorUtil.getColumnIndexOrThrow(_cursor, "bloodPotency");
          final int _cursorIndexOfBloodSurges = CursorUtil.getColumnIndexOrThrow(_cursor, "bloodSurges");
          final int _cursorIndexOfMendAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "mendAmount");
          final int _cursorIndexOfPowerBonus = CursorUtil.getColumnIndexOrThrow(_cursor, "powerBonus");
          final int _cursorIndexOfRouseReRoll = CursorUtil.getColumnIndexOrThrow(_cursor, "rouseReRoll");
          final int _cursorIndexOfFeedingPenaly = CursorUtil.getColumnIndexOrThrow(_cursor, "feedingPenaly");
          final int _cursorIndexOfBaneSeverity = CursorUtil.getColumnIndexOrThrow(_cursor, "baneSeverity");
          final int _cursorIndexOfResonance = CursorUtil.getColumnIndexOrThrow(_cursor, "resonance");
          final int _cursorIndexOfHunting = CursorUtil.getColumnIndexOrThrow(_cursor, "hunting");
          final int _cursorIndexOfExpTotal = CursorUtil.getColumnIndexOrThrow(_cursor, "expTotal");
          final int _cursorIndexOfExpSpent = CursorUtil.getColumnIndexOrThrow(_cursor, "expSpent");
          final int _cursorIndexOfHunger = CursorUtil.getColumnIndexOrThrow(_cursor, "hunger");
          final CharacterSheet _result;
          if (_cursor.moveToFirst()) {
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpPlayer;
            _tmpPlayer = _cursor.getString(_cursorIndexOfPlayer);
            final String _tmpChronicle;
            _tmpChronicle = _cursor.getString(_cursorIndexOfChronicle);
            final String _tmpConcept;
            _tmpConcept = _cursor.getString(_cursorIndexOfConcept);
            final String _tmpAmbition;
            _tmpAmbition = _cursor.getString(_cursorIndexOfAmbition);
            final String _tmpPredator;
            _tmpPredator = _cursor.getString(_cursorIndexOfPredator);
            final String _tmpSire;
            _tmpSire = _cursor.getString(_cursorIndexOfSire);
            final String _tmpClan;
            _tmpClan = _cursor.getString(_cursorIndexOfClan);
            final String _tmpGeneration;
            _tmpGeneration = _cursor.getString(_cursorIndexOfGeneration);
            final int _tmpStrength;
            _tmpStrength = _cursor.getInt(_cursorIndexOfStrength);
            final int _tmpDexterity;
            _tmpDexterity = _cursor.getInt(_cursorIndexOfDexterity);
            final int _tmpStamina;
            _tmpStamina = _cursor.getInt(_cursorIndexOfStamina);
            final int _tmpCharisma;
            _tmpCharisma = _cursor.getInt(_cursorIndexOfCharisma);
            final int _tmpManipulation;
            _tmpManipulation = _cursor.getInt(_cursorIndexOfManipulation);
            final int _tmpComposure;
            _tmpComposure = _cursor.getInt(_cursorIndexOfComposure);
            final int _tmpIntelligence;
            _tmpIntelligence = _cursor.getInt(_cursorIndexOfIntelligence);
            final int _tmpWits;
            _tmpWits = _cursor.getInt(_cursorIndexOfWits);
            final int _tmpResolve;
            _tmpResolve = _cursor.getInt(_cursorIndexOfResolve);
            final int _tmpAthletics;
            _tmpAthletics = _cursor.getInt(_cursorIndexOfAthletics);
            final int _tmpBrawl;
            _tmpBrawl = _cursor.getInt(_cursorIndexOfBrawl);
            final int _tmpCraft;
            _tmpCraft = _cursor.getInt(_cursorIndexOfCraft);
            final int _tmpDrive;
            _tmpDrive = _cursor.getInt(_cursorIndexOfDrive);
            final int _tmpFirearms;
            _tmpFirearms = _cursor.getInt(_cursorIndexOfFirearms);
            final int _tmpMelee;
            _tmpMelee = _cursor.getInt(_cursorIndexOfMelee);
            final int _tmpLarceny;
            _tmpLarceny = _cursor.getInt(_cursorIndexOfLarceny);
            final int _tmpStealth;
            _tmpStealth = _cursor.getInt(_cursorIndexOfStealth);
            final int _tmpSurvival;
            _tmpSurvival = _cursor.getInt(_cursorIndexOfSurvival);
            final int _tmpAnimalKen;
            _tmpAnimalKen = _cursor.getInt(_cursorIndexOfAnimalKen);
            final int _tmpEtiquette;
            _tmpEtiquette = _cursor.getInt(_cursorIndexOfEtiquette);
            final int _tmpInsight;
            _tmpInsight = _cursor.getInt(_cursorIndexOfInsight);
            final int _tmpIntimidation;
            _tmpIntimidation = _cursor.getInt(_cursorIndexOfIntimidation);
            final int _tmpLeadership;
            _tmpLeadership = _cursor.getInt(_cursorIndexOfLeadership);
            final int _tmpPerformance;
            _tmpPerformance = _cursor.getInt(_cursorIndexOfPerformance);
            final int _tmpPersuasion;
            _tmpPersuasion = _cursor.getInt(_cursorIndexOfPersuasion);
            final int _tmpStreetwise;
            _tmpStreetwise = _cursor.getInt(_cursorIndexOfStreetwise);
            final int _tmpSubterfuge;
            _tmpSubterfuge = _cursor.getInt(_cursorIndexOfSubterfuge);
            final int _tmpAcademics;
            _tmpAcademics = _cursor.getInt(_cursorIndexOfAcademics);
            final int _tmpAwareness;
            _tmpAwareness = _cursor.getInt(_cursorIndexOfAwareness);
            final int _tmpFinance;
            _tmpFinance = _cursor.getInt(_cursorIndexOfFinance);
            final int _tmpInvestigation;
            _tmpInvestigation = _cursor.getInt(_cursorIndexOfInvestigation);
            final int _tmpMedicine;
            _tmpMedicine = _cursor.getInt(_cursorIndexOfMedicine);
            final int _tmpOccult;
            _tmpOccult = _cursor.getInt(_cursorIndexOfOccult);
            final int _tmpPolitics;
            _tmpPolitics = _cursor.getInt(_cursorIndexOfPolitics);
            final int _tmpScience;
            _tmpScience = _cursor.getInt(_cursorIndexOfScience);
            final int _tmpTechnology;
            _tmpTechnology = _cursor.getInt(_cursorIndexOfTechnology);
            final String _tmpChronicleTenets;
            _tmpChronicleTenets = _cursor.getString(_cursorIndexOfChronicleTenets);
            final String _tmpTouchstonesAndConvictions;
            _tmpTouchstonesAndConvictions = _cursor.getString(_cursorIndexOfTouchstonesAndConvictions);
            final String _tmpClanBane;
            _tmpClanBane = _cursor.getString(_cursorIndexOfClanBane);
            final int _tmpTotalHealth;
            _tmpTotalHealth = _cursor.getInt(_cursorIndexOfTotalHealth);
            final int _tmpSuperficialDamageHealth;
            _tmpSuperficialDamageHealth = _cursor.getInt(_cursorIndexOfSuperficialDamageHealth);
            final int _tmpAggravatedDamageHealth;
            _tmpAggravatedDamageHealth = _cursor.getInt(_cursorIndexOfAggravatedDamageHealth);
            final int _tmpTotalWillpower;
            _tmpTotalWillpower = _cursor.getInt(_cursorIndexOfTotalWillpower);
            final int _tmpSuperficialDamageWillpower;
            _tmpSuperficialDamageWillpower = _cursor.getInt(_cursorIndexOfSuperficialDamageWillpower);
            final int _tmpAggravatedDamageWillpower;
            _tmpAggravatedDamageWillpower = _cursor.getInt(_cursorIndexOfAggravatedDamageWillpower);
            final int _tmpTotalHumanity;
            _tmpTotalHumanity = _cursor.getInt(_cursorIndexOfTotalHumanity);
            final int _tmpDamageHumanity;
            _tmpDamageHumanity = _cursor.getInt(_cursorIndexOfDamageHumanity);
            final int _tmpBloodPotency;
            _tmpBloodPotency = _cursor.getInt(_cursorIndexOfBloodPotency);
            final String _tmpBloodSurges;
            _tmpBloodSurges = _cursor.getString(_cursorIndexOfBloodSurges);
            final String _tmpMendAmount;
            _tmpMendAmount = _cursor.getString(_cursorIndexOfMendAmount);
            final String _tmpPowerBonus;
            _tmpPowerBonus = _cursor.getString(_cursorIndexOfPowerBonus);
            final String _tmpRouseReRoll;
            _tmpRouseReRoll = _cursor.getString(_cursorIndexOfRouseReRoll);
            final String _tmpFeedingPenaly;
            _tmpFeedingPenaly = _cursor.getString(_cursorIndexOfFeedingPenaly);
            final String _tmpBaneSeverity;
            _tmpBaneSeverity = _cursor.getString(_cursorIndexOfBaneSeverity);
            final String _tmpResonance;
            _tmpResonance = _cursor.getString(_cursorIndexOfResonance);
            final String _tmpHunting;
            _tmpHunting = _cursor.getString(_cursorIndexOfHunting);
            final int _tmpExpTotal;
            _tmpExpTotal = _cursor.getInt(_cursorIndexOfExpTotal);
            final int _tmpExpSpent;
            _tmpExpSpent = _cursor.getInt(_cursorIndexOfExpSpent);
            final int _tmpHunger;
            _tmpHunger = _cursor.getInt(_cursorIndexOfHunger);
            _result = new CharacterSheet(_tmpName,_tmpPlayer,_tmpChronicle,_tmpConcept,_tmpAmbition,_tmpPredator,_tmpSire,_tmpClan,_tmpGeneration,_tmpStrength,_tmpDexterity,_tmpStamina,_tmpCharisma,_tmpManipulation,_tmpComposure,_tmpIntelligence,_tmpWits,_tmpResolve,_tmpAthletics,_tmpBrawl,_tmpCraft,_tmpDrive,_tmpFirearms,_tmpMelee,_tmpLarceny,_tmpStealth,_tmpSurvival,_tmpAnimalKen,_tmpEtiquette,_tmpInsight,_tmpIntimidation,_tmpLeadership,_tmpPerformance,_tmpPersuasion,_tmpStreetwise,_tmpSubterfuge,_tmpAcademics,_tmpAwareness,_tmpFinance,_tmpInvestigation,_tmpMedicine,_tmpOccult,_tmpPolitics,_tmpScience,_tmpTechnology,_tmpChronicleTenets,_tmpTouchstonesAndConvictions,_tmpClanBane,_tmpTotalHealth,_tmpSuperficialDamageHealth,_tmpAggravatedDamageHealth,_tmpTotalWillpower,_tmpSuperficialDamageWillpower,_tmpAggravatedDamageWillpower,_tmpTotalHumanity,_tmpDamageHumanity,_tmpBloodPotency,_tmpBloodSurges,_tmpMendAmount,_tmpPowerBonus,_tmpRouseReRoll,_tmpFeedingPenaly,_tmpBaneSeverity,_tmpResonance,_tmpHunting,_tmpExpTotal,_tmpExpSpent,_tmpHunger);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
