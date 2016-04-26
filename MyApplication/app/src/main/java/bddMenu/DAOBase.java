package bddMenu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import bddMenu.DatabaseHandler;


//ACCES A LA BASE DE DONNEES DES PLATS (OPEN/CLOSE)

public abstract class DAOBase {
	  protected final static int VERSION = 1;
	  //NOM DU FICHIER
	  protected final static String NOM = "databasePlat.db";
	    
	  protected SQLiteDatabase mDb = null;
	  protected bddMenu.DatabaseHandler mHandler = null;

	  public DAOBase(Context pContext) {
	    this.mHandler = new bddMenu.DatabaseHandler(pContext);
	  }
	    
	  public SQLiteDatabase open() {
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

