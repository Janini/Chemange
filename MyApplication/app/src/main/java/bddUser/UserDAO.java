package bddUser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

//MODIFIE LA BASE DE DONNEES (AJOUTS, SUPPRESSIONS, MISES A JOUR...)
public class UserDAO extends DAOBase {

	public static final String TABLE_NAME = "user";
	public static final String KEY = "id";
	public static final String LOGIN = "login";
	public static final String PASSWORD = "password";
	public static final String CATEGORIE = "categorie";

	public static final String TABLE_CREATE =
			"CREATE TABLE " + TABLE_NAME + " (" +
					KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					LOGIN + " TEXT UNIQUE ON CONFLICT REPLACE, " +
					PASSWORD + " TEXT, "+ CATEGORIE + " TEXT);";
	public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";


	private static UserDAO mInstance = null;
	private Context mCtx;

	public static UserDAO getInstance(Context ctx) {
		if (mInstance == null) {
			mInstance = new UserDAO(ctx.getApplicationContext());
		}
		return mInstance;
	}

	private UserDAO(Context ctx) {
		super(ctx);
		this.mCtx = ctx;
	}

	  // AJOUTE L'UTILISATEUR u DANS LA BASE DE DONNEES
	  // LE CHAMP ID S'INCREMENTE SEUL
	  public void add(User u) {
		  ContentValues value = new ContentValues();
		  value.put(UserDAO.LOGIN, u.getLogin());
		  value.put(UserDAO.PASSWORD, u.getPassword());
		  value.put(UserDAO.CATEGORIE, u.getCategorie());
		  mDb.insert(UserDAO.TABLE_NAME, null, value);
	  }

	  //PAS UTILISEE ICI : ON CONSIDERE QU'ON NE PEUT SUPPRIMER QUE DES CLIENTS
	  /*public void delete(String login) {
		  int ok = mDb.delete(TABLE_NAME, LOGIN + " = ?", new String[] {String.valueOf(login)});
		  if (ok > 0)
		  	Log.i("UserDAO", "Je delete "+login);
		  else
			  Log.i("UserDAO", "J'ai pas pu delete "+login);

	  }*/

	public int deleteClient(String login) {
		int ok = mDb.delete(TABLE_NAME, LOGIN + " = ? AND " + CATEGORIE + " = ?", new String[]{String.valueOf(login),"client"});
		if (ok > 0)
			Log.i("UserDAO", "Je delete le client "+login);
		else
			Log.i("UserDAO", "J'ai pas pu delete le client "+login);
		return ok;
	}
	  
	  public void deleteAll(){
		  mDb.delete(TABLE_NAME, null, null);
	  }


	  //MISE A JOUR DU MOT DE PASSE DE u
	  public void update(User u) {
		  ContentValues value = new ContentValues();
		  value.put(DatabaseHandler.USER_PASSWORD, u.getPassword());
		  mDb.update(TABLE_NAME, value, KEY  + " = ?", new String[] {String.valueOf(u.getId())});
		  
	  }

	  //RECUPERE UNE CATEGORIE D'UTILISATEUR A PARTIR DE SON LOGIN
	  public String select(String login) {
		  Cursor c = mDb.rawQuery("select * from " + TABLE_NAME + " where login = ?", new String[]{login});
		  c.moveToFirst();
		  String categorie = c.getString(c.getColumnIndex(DatabaseHandler.USER_CATEGORIE));
		  c.close();
		 return categorie;
	  }

		//RENVOIE UN UTILISATEUR A PARTIR DE SON LOGIN ET DE SON MOT DE PASSE
		public User select(String login, String mdp) {
			User u = null;
			Cursor c = mDb.rawQuery("select * from " + TABLE_NAME + " where login = ? and password = ?", new String[]{login,mdp});
			if (c.moveToFirst()) {
				u = new User(login, mdp,c.getString(c.getColumnIndex(DatabaseHandler.USER_CATEGORIE)));
				c.close();
			}
			return u;
		}
	  
	  public List<User> selectAll() {
		  List<User> users = new ArrayList<User>();
		  Cursor c = mDb.rawQuery("select * from " + TABLE_NAME,null);

		  if (c .moveToFirst()) {
	            while (c.isAfterLast() == false) {
					User u = new User();
					u.setId(Integer.parseInt(c.getString(c.getColumnIndex(DatabaseHandler.USER_KEY))));
					u.setLogin(c.getString(c.getColumnIndex(DatabaseHandler.USER_LOGIN)));
					u.setPassword(c.getString(c.getColumnIndex(DatabaseHandler.USER_PASSWORD)));
					u.setCategorie(c.getString(c.getColumnIndex(DatabaseHandler.USER_CATEGORIE)));
					//String name = c.getString(c.getColumnIndex(DatabaseHandler.USER_LOGIN));
	                users.add(u);
	                c.moveToNext();
	            }
	        }
		  c.close();
		 return users;
	  }
	  
	  
	  
		public int getProfilesCount() {
		    String countQuery = "SELECT  * FROM " + TABLE_NAME;
		    SQLiteDatabase db = mHandler.getReadableDatabase();
		    Cursor cursor = db.rawQuery(countQuery, null);
		    int cnt = cursor.getCount();
		    cursor.close();
		    return cnt;
		}
		

}
