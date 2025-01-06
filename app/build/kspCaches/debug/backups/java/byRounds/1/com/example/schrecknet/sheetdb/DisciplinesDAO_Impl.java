package com.example.schrecknet.sheetdb;

import android.database.Cursor;
import androidx.annotation.NonNull;
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
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class DisciplinesDAO_Impl implements DisciplinesDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Discipline> __insertionAdapterOfDiscipline;

  private final EntityDeletionOrUpdateAdapter<Discipline> __deletionAdapterOfDiscipline;

  public DisciplinesDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDiscipline = new EntityInsertionAdapter<Discipline>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `Discipline` (`characterName`,`name`,`level`,`power1`,`power2`,`power3`,`power4`,`power5`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Discipline entity) {
        statement.bindString(1, entity.getCharacterName());
        statement.bindString(2, entity.getName());
        statement.bindLong(3, entity.getLevel());
        statement.bindString(4, entity.getPower1());
        statement.bindString(5, entity.getPower2());
        statement.bindString(6, entity.getPower3());
        statement.bindString(7, entity.getPower4());
        statement.bindString(8, entity.getPower5());
      }
    };
    this.__deletionAdapterOfDiscipline = new EntityDeletionOrUpdateAdapter<Discipline>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `Discipline` WHERE `characterName` = ? AND `name` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Discipline entity) {
        statement.bindString(1, entity.getCharacterName());
        statement.bindString(2, entity.getName());
      }
    };
  }

  @Override
  public long insertDiscipline(final Discipline discipline) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfDiscipline.insertAndReturnId(discipline);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteDiscipline(final Discipline discipline) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfDiscipline.handle(discipline);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Flow<List<Discipline>> getDisciplinesForCharacter(final String characterName) {
    final String _sql = "SELECT * FROM Discipline WHERE characterName = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, characterName);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"Discipline"}, new Callable<List<Discipline>>() {
      @Override
      @NonNull
      public List<Discipline> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCharacterName = CursorUtil.getColumnIndexOrThrow(_cursor, "characterName");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfPower1 = CursorUtil.getColumnIndexOrThrow(_cursor, "power1");
          final int _cursorIndexOfPower2 = CursorUtil.getColumnIndexOrThrow(_cursor, "power2");
          final int _cursorIndexOfPower3 = CursorUtil.getColumnIndexOrThrow(_cursor, "power3");
          final int _cursorIndexOfPower4 = CursorUtil.getColumnIndexOrThrow(_cursor, "power4");
          final int _cursorIndexOfPower5 = CursorUtil.getColumnIndexOrThrow(_cursor, "power5");
          final List<Discipline> _result = new ArrayList<Discipline>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Discipline _item;
            final String _tmpCharacterName;
            _tmpCharacterName = _cursor.getString(_cursorIndexOfCharacterName);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpPower1;
            _tmpPower1 = _cursor.getString(_cursorIndexOfPower1);
            final String _tmpPower2;
            _tmpPower2 = _cursor.getString(_cursorIndexOfPower2);
            final String _tmpPower3;
            _tmpPower3 = _cursor.getString(_cursorIndexOfPower3);
            final String _tmpPower4;
            _tmpPower4 = _cursor.getString(_cursorIndexOfPower4);
            final String _tmpPower5;
            _tmpPower5 = _cursor.getString(_cursorIndexOfPower5);
            _item = new Discipline(_tmpCharacterName,_tmpName,_tmpLevel,_tmpPower1,_tmpPower2,_tmpPower3,_tmpPower4,_tmpPower5);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
