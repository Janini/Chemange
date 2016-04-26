package bddUser;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
  public static final String USER_KEY = "id";
  public static final String USER_LOGIN = "LOGIN";
  public static final String USER_PASSWORD = "PASSWORD";
  public static final String USER_CATEGORIE = "CATEGORIE";
    
  public static final String USER_TABLE_NAME = "USER";
  public static final String USER_TABLE_CREATE =
    "CREATE TABLE " + USER_TABLE_NAME + " (" +
      USER_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
      USER_LOGIN + " TEXT UNIQUE ON CONFLICT REPLACE, " +
      USER_PASSWORD + " TEXT, "+ USER_CATEGORIE + " TEXT);";
  
  

  public DatabaseHandler(Context context) {
    super(context, USER_TABLE_NAME, null, 1);
  }

	  @Override
	  public void onCreate(SQLiteDatabase db) {
	    db.execSQL(USER_TABLE_CREATE);
	  }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS " + UserDAO.TABLE_NAME);
      onCreate(db);
	}
	
	

	

}
