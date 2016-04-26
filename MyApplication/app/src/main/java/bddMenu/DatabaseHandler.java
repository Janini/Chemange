package bddMenu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// CREATION ET MAJ DE LA BD
public class DatabaseHandler extends SQLiteOpenHelper {
  public static final String PLAT_KEY = "id";
  public static final String PLAT_CATEGORIE = "CATEGORIE";
  public static final String PLAT_NOM = "NOM";
  public static final String PLAT_PRIX = "PRIX";
  public static final String PLAT_QUANTITE = "QUANTITE";
  public static final String PLAT_INFO = "INFO";
  public static final String PLAT_PHOTO = "PHOTO";
  public static final String PLAT_TABLE_NAME = "plat";
  public static final String PLAT_TABLE_CREATE =
          "CREATE TABLE " + PLAT_TABLE_NAME + " (" +
                  PLAT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                  PLAT_CATEGORIE + " TEXT, "+
                  PLAT_NOM + " TEXT UNIQUE ON CONFLICT REPLACE, " +
                  PLAT_PRIX + " TEXT, " + PLAT_QUANTITE + " TEXT, " + PLAT_INFO + " TEXT, " + PLAT_PHOTO + " TEXT);";



  public DatabaseHandler(Context context) {
    super(context, PLAT_TABLE_NAME, null,1);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(PLAT_TABLE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + PlatDAO.TABLE_NAME);
    onCreate(db);
  }





}
