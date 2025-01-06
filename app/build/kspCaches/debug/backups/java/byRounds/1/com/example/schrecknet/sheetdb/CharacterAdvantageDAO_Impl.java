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
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CharacterAdvantageDAO_Impl implements CharacterAdvantageDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CharacterAdvantage> __insertionAdapterOfCharacterAdvantage;

  private final EntityDeletionOrUpdateAdapter<CharacterAdvantage> __deletionAdapterOfCharacterAdvantage;

  public CharacterAdvantageDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCharacterAdvantage = new EntityInsertionAdapter<CharacterAdvantage>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `CharacterAdvantage` (`type`,`name`,`characterName`,`power`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CharacterAdvantage entity) {
        statement.bindString(1, entity.getType());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getCharacterName());
        statement.bindLong(4, entity.getPower());
      }
    };
    this.__deletionAdapterOfCharacterAdvantage = new EntityDeletionOrUpdateAdapter<CharacterAdvantage>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `CharacterAdvantage` WHERE `characterName` = ? AND `name` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CharacterAdvantage entity) {
        statement.bindString(1, entity.getCharacterName());
        statement.bindString(2, entity.getName());
      }
    };
  }

  @Override
  public long insertCharacterAdvantage(final CharacterAdvantage characterAdvantage) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfCharacterAdvantage.insertAndReturnId(characterAdvantage);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Object deleteCharacterAdvantage(final CharacterAdvantage characterAdvantage,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfCharacterAdvantage.handle(characterAdvantage);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<CharacterAdvantage>> getCharacterAdvantageForCharacter(
      final String characterName) {
    final String _sql = "SELECT * FROM CharacterAdvantage WHERE characterName = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, characterName);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"CharacterAdvantage"}, new Callable<List<CharacterAdvantage>>() {
      @Override
      @NonNull
      public List<CharacterAdvantage> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCharacterName = CursorUtil.getColumnIndexOrThrow(_cursor, "characterName");
          final int _cursorIndexOfPower = CursorUtil.getColumnIndexOrThrow(_cursor, "power");
          final List<CharacterAdvantage> _result = new ArrayList<CharacterAdvantage>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CharacterAdvantage _item;
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpCharacterName;
            _tmpCharacterName = _cursor.getString(_cursorIndexOfCharacterName);
            final int _tmpPower;
            _tmpPower = _cursor.getInt(_cursorIndexOfPower);
            _item = new CharacterAdvantage(_tmpType,_tmpName,_tmpCharacterName,_tmpPower);
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
