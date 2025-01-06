package com.example.schrecknet.sheetdb;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile CharacterSheetDAO _characterSheetDAO;

  private volatile DisciplinesDAO _disciplinesDAO;

  private volatile CharacterAdvantageDAO _characterAdvantageDAO;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `CharacterSheet` (`name` TEXT NOT NULL, `player` TEXT NOT NULL, `chronicle` TEXT NOT NULL, `concept` TEXT NOT NULL, `ambition` TEXT NOT NULL, `predator` TEXT NOT NULL, `sire` TEXT NOT NULL, `clan` TEXT NOT NULL, `generation` TEXT NOT NULL, `strength` INTEGER NOT NULL, `dexterity` INTEGER NOT NULL, `stamina` INTEGER NOT NULL, `charisma` INTEGER NOT NULL, `manipulation` INTEGER NOT NULL, `composure` INTEGER NOT NULL, `intelligence` INTEGER NOT NULL, `wits` INTEGER NOT NULL, `resolve` INTEGER NOT NULL, `athletics` INTEGER NOT NULL, `brawl` INTEGER NOT NULL, `craft` INTEGER NOT NULL, `drive` INTEGER NOT NULL, `firearms` INTEGER NOT NULL, `melee` INTEGER NOT NULL, `larceny` INTEGER NOT NULL, `stealth` INTEGER NOT NULL, `survival` INTEGER NOT NULL, `animalKen` INTEGER NOT NULL, `etiquette` INTEGER NOT NULL, `insight` INTEGER NOT NULL, `intimidation` INTEGER NOT NULL, `leadership` INTEGER NOT NULL, `performance` INTEGER NOT NULL, `persuasion` INTEGER NOT NULL, `streetwise` INTEGER NOT NULL, `subterfuge` INTEGER NOT NULL, `academics` INTEGER NOT NULL, `awareness` INTEGER NOT NULL, `finance` INTEGER NOT NULL, `investigation` INTEGER NOT NULL, `medicine` INTEGER NOT NULL, `occult` INTEGER NOT NULL, `politics` INTEGER NOT NULL, `science` INTEGER NOT NULL, `technology` INTEGER NOT NULL, `chronicleTenets` TEXT NOT NULL, `touchstonesAndConvictions` TEXT NOT NULL, `clanBane` TEXT NOT NULL, `totalHealth` INTEGER NOT NULL, `superficialDamageHealth` INTEGER NOT NULL, `aggravatedDamageHealth` INTEGER NOT NULL, `totalWillpower` INTEGER NOT NULL, `superficialDamageWillpower` INTEGER NOT NULL, `aggravatedDamageWillpower` INTEGER NOT NULL, `totalHumanity` INTEGER NOT NULL, `damageHumanity` INTEGER NOT NULL, `bloodPotency` INTEGER NOT NULL, `bloodSurges` TEXT NOT NULL, `mendAmount` TEXT NOT NULL, `powerBonus` TEXT NOT NULL, `rouseReRoll` TEXT NOT NULL, `feedingPenaly` TEXT NOT NULL, `baneSeverity` TEXT NOT NULL, `resonance` TEXT NOT NULL, `hunting` TEXT NOT NULL, `expTotal` INTEGER NOT NULL, `expSpent` INTEGER NOT NULL, `hunger` INTEGER NOT NULL, PRIMARY KEY(`name`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `Discipline` (`characterName` TEXT NOT NULL, `name` TEXT NOT NULL, `level` INTEGER NOT NULL, `power1` TEXT NOT NULL, `power2` TEXT NOT NULL, `power3` TEXT NOT NULL, `power4` TEXT NOT NULL, `power5` TEXT NOT NULL, PRIMARY KEY(`characterName`, `name`), FOREIGN KEY(`characterName`) REFERENCES `CharacterSheet`(`name`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE TABLE IF NOT EXISTS `CharacterAdvantage` (`type` TEXT NOT NULL, `name` TEXT NOT NULL, `characterName` TEXT NOT NULL, `power` INTEGER NOT NULL, PRIMARY KEY(`characterName`, `name`), FOREIGN KEY(`characterName`) REFERENCES `CharacterSheet`(`name`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '76c80048d3f3e800cb0a8683c72fd8fd')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `CharacterSheet`");
        db.execSQL("DROP TABLE IF EXISTS `Discipline`");
        db.execSQL("DROP TABLE IF EXISTS `CharacterAdvantage`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsCharacterSheet = new HashMap<String, TableInfo.Column>(68);
        _columnsCharacterSheet.put("name", new TableInfo.Column("name", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("player", new TableInfo.Column("player", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("chronicle", new TableInfo.Column("chronicle", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("concept", new TableInfo.Column("concept", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("ambition", new TableInfo.Column("ambition", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("predator", new TableInfo.Column("predator", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("sire", new TableInfo.Column("sire", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("clan", new TableInfo.Column("clan", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("generation", new TableInfo.Column("generation", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("strength", new TableInfo.Column("strength", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("dexterity", new TableInfo.Column("dexterity", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("stamina", new TableInfo.Column("stamina", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("charisma", new TableInfo.Column("charisma", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("manipulation", new TableInfo.Column("manipulation", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("composure", new TableInfo.Column("composure", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("intelligence", new TableInfo.Column("intelligence", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("wits", new TableInfo.Column("wits", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("resolve", new TableInfo.Column("resolve", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("athletics", new TableInfo.Column("athletics", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("brawl", new TableInfo.Column("brawl", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("craft", new TableInfo.Column("craft", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("drive", new TableInfo.Column("drive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("firearms", new TableInfo.Column("firearms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("melee", new TableInfo.Column("melee", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("larceny", new TableInfo.Column("larceny", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("stealth", new TableInfo.Column("stealth", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("survival", new TableInfo.Column("survival", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("animalKen", new TableInfo.Column("animalKen", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("etiquette", new TableInfo.Column("etiquette", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("insight", new TableInfo.Column("insight", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("intimidation", new TableInfo.Column("intimidation", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("leadership", new TableInfo.Column("leadership", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("performance", new TableInfo.Column("performance", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("persuasion", new TableInfo.Column("persuasion", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("streetwise", new TableInfo.Column("streetwise", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("subterfuge", new TableInfo.Column("subterfuge", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("academics", new TableInfo.Column("academics", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("awareness", new TableInfo.Column("awareness", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("finance", new TableInfo.Column("finance", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("investigation", new TableInfo.Column("investigation", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("medicine", new TableInfo.Column("medicine", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("occult", new TableInfo.Column("occult", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("politics", new TableInfo.Column("politics", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("science", new TableInfo.Column("science", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("technology", new TableInfo.Column("technology", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("chronicleTenets", new TableInfo.Column("chronicleTenets", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("touchstonesAndConvictions", new TableInfo.Column("touchstonesAndConvictions", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("clanBane", new TableInfo.Column("clanBane", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("totalHealth", new TableInfo.Column("totalHealth", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("superficialDamageHealth", new TableInfo.Column("superficialDamageHealth", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("aggravatedDamageHealth", new TableInfo.Column("aggravatedDamageHealth", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("totalWillpower", new TableInfo.Column("totalWillpower", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("superficialDamageWillpower", new TableInfo.Column("superficialDamageWillpower", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("aggravatedDamageWillpower", new TableInfo.Column("aggravatedDamageWillpower", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("totalHumanity", new TableInfo.Column("totalHumanity", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("damageHumanity", new TableInfo.Column("damageHumanity", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("bloodPotency", new TableInfo.Column("bloodPotency", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("bloodSurges", new TableInfo.Column("bloodSurges", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("mendAmount", new TableInfo.Column("mendAmount", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("powerBonus", new TableInfo.Column("powerBonus", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("rouseReRoll", new TableInfo.Column("rouseReRoll", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("feedingPenaly", new TableInfo.Column("feedingPenaly", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("baneSeverity", new TableInfo.Column("baneSeverity", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("resonance", new TableInfo.Column("resonance", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("hunting", new TableInfo.Column("hunting", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("expTotal", new TableInfo.Column("expTotal", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("expSpent", new TableInfo.Column("expSpent", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterSheet.put("hunger", new TableInfo.Column("hunger", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCharacterSheet = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCharacterSheet = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCharacterSheet = new TableInfo("CharacterSheet", _columnsCharacterSheet, _foreignKeysCharacterSheet, _indicesCharacterSheet);
        final TableInfo _existingCharacterSheet = TableInfo.read(db, "CharacterSheet");
        if (!_infoCharacterSheet.equals(_existingCharacterSheet)) {
          return new RoomOpenHelper.ValidationResult(false, "CharacterSheet(com.example.schrecknet.sheetdb.CharacterSheet).\n"
                  + " Expected:\n" + _infoCharacterSheet + "\n"
                  + " Found:\n" + _existingCharacterSheet);
        }
        final HashMap<String, TableInfo.Column> _columnsDiscipline = new HashMap<String, TableInfo.Column>(8);
        _columnsDiscipline.put("characterName", new TableInfo.Column("characterName", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiscipline.put("name", new TableInfo.Column("name", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiscipline.put("level", new TableInfo.Column("level", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiscipline.put("power1", new TableInfo.Column("power1", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiscipline.put("power2", new TableInfo.Column("power2", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiscipline.put("power3", new TableInfo.Column("power3", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiscipline.put("power4", new TableInfo.Column("power4", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiscipline.put("power5", new TableInfo.Column("power5", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDiscipline = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysDiscipline.add(new TableInfo.ForeignKey("CharacterSheet", "CASCADE", "NO ACTION", Arrays.asList("characterName"), Arrays.asList("name")));
        final HashSet<TableInfo.Index> _indicesDiscipline = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDiscipline = new TableInfo("Discipline", _columnsDiscipline, _foreignKeysDiscipline, _indicesDiscipline);
        final TableInfo _existingDiscipline = TableInfo.read(db, "Discipline");
        if (!_infoDiscipline.equals(_existingDiscipline)) {
          return new RoomOpenHelper.ValidationResult(false, "Discipline(com.example.schrecknet.sheetdb.Discipline).\n"
                  + " Expected:\n" + _infoDiscipline + "\n"
                  + " Found:\n" + _existingDiscipline);
        }
        final HashMap<String, TableInfo.Column> _columnsCharacterAdvantage = new HashMap<String, TableInfo.Column>(4);
        _columnsCharacterAdvantage.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterAdvantage.put("name", new TableInfo.Column("name", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterAdvantage.put("characterName", new TableInfo.Column("characterName", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterAdvantage.put("power", new TableInfo.Column("power", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCharacterAdvantage = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysCharacterAdvantage.add(new TableInfo.ForeignKey("CharacterSheet", "CASCADE", "NO ACTION", Arrays.asList("characterName"), Arrays.asList("name")));
        final HashSet<TableInfo.Index> _indicesCharacterAdvantage = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCharacterAdvantage = new TableInfo("CharacterAdvantage", _columnsCharacterAdvantage, _foreignKeysCharacterAdvantage, _indicesCharacterAdvantage);
        final TableInfo _existingCharacterAdvantage = TableInfo.read(db, "CharacterAdvantage");
        if (!_infoCharacterAdvantage.equals(_existingCharacterAdvantage)) {
          return new RoomOpenHelper.ValidationResult(false, "CharacterAdvantage(com.example.schrecknet.sheetdb.CharacterAdvantage).\n"
                  + " Expected:\n" + _infoCharacterAdvantage + "\n"
                  + " Found:\n" + _existingCharacterAdvantage);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "76c80048d3f3e800cb0a8683c72fd8fd", "b18a0428e34a4bb43d9dcffd1028dde8");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "CharacterSheet","Discipline","CharacterAdvantage");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `CharacterSheet`");
      _db.execSQL("DELETE FROM `Discipline`");
      _db.execSQL("DELETE FROM `CharacterAdvantage`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(CharacterSheetDAO.class, CharacterSheetDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(DisciplinesDAO.class, DisciplinesDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(CharacterAdvantageDAO.class, CharacterAdvantageDAO_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public CharacterSheetDAO characterSheetDao() {
    if (_characterSheetDAO != null) {
      return _characterSheetDAO;
    } else {
      synchronized(this) {
        if(_characterSheetDAO == null) {
          _characterSheetDAO = new CharacterSheetDAO_Impl(this);
        }
        return _characterSheetDAO;
      }
    }
  }

  @Override
  public DisciplinesDAO disciplineDao() {
    if (_disciplinesDAO != null) {
      return _disciplinesDAO;
    } else {
      synchronized(this) {
        if(_disciplinesDAO == null) {
          _disciplinesDAO = new DisciplinesDAO_Impl(this);
        }
        return _disciplinesDAO;
      }
    }
  }

  @Override
  public CharacterAdvantageDAO characterAdvantageDao() {
    if (_characterAdvantageDAO != null) {
      return _characterAdvantageDAO;
    } else {
      synchronized(this) {
        if(_characterAdvantageDAO == null) {
          _characterAdvantageDAO = new CharacterAdvantageDAO_Impl(this);
        }
        return _characterAdvantageDAO;
      }
    }
  }
}
