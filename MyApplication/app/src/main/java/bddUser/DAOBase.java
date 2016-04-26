package bddUser;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


//ACCES A LA BASE DE DONNEES (OPEN/CLOSE)

public abstract class DAOBase {
	  protected final static int VERSION = 1;
	  //NOM DU FICHIER
	  protected final static String NOM = "database.db";
	    
	  protected SQLiteDatabase mDb = null;
	  protected DatabaseHandler mHandler = null;
	    
	  public DAOBase(Context pContext) {
	    this.mHandler = new DatabaseHandler(pContext);
	  }
	    
	  public SQLiteDatabase open() {
	    // Pas besoin de fermer la dernière base puisque getWritableDatabase s'en charge
	    mDb = mHandler.getWritableDatabase();
	    return mDb;
	  }
	    
	  public void close() {
	    mDb.close();
	  }
	    
	  public SQLiteDatabase getDb() {
	    return mDb;
	  }
	}

