package bddMenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;




//MODIFIE LA BASE DE DONNEES (AJOUTS, SUPPRESSIONS, MISES A JOUR...)
public class PlatDAO extends DAOBase {

	public static final String TABLE_NAME = "plat";
	public static final String KEY = "id";
	public static final String CATEGORIE = "categorie";
	public static final String NOM = "nom";
	public static final String PRIX = "prix";
	public static final String QUANTITE = "quantite";
	public static final String INFO = "info";
	public static final String PHOTO = "photo";

	public static final String PLAT_TABLE_CREATE =
			"CREATE TABLE " + TABLE_NAME + " (" +
					KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					CATEGORIE + " TEXT, " +
					NOM + " TEXT UNIQUE ON CONFLICT REPLACE, " +
					PRIX + " TEXT, " + QUANTITE + " TEXT, " + INFO + " TEXT, " +PHOTO + " TEXT);";


	public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";


	private static PlatDAO mInstance = null;
	private Context mCtx;

	public static PlatDAO getInstance(Context ctx) {
		if (mInstance == null) {
			mInstance = new PlatDAO(ctx.getApplicationContext());
		}
		return mInstance;
	}

	private PlatDAO(Context ctx) {
		super(ctx);
		this.mCtx = ctx;
	}

	  // AJOUTE L'UTILISATEUR u DANS LA BASE DE DONNEES
	  // LE CHAMP ID S'INCREMENTE SEUL

	  public void add(Plat p) {
		  ContentValues value = new ContentValues();
		  value.put(PlatDAO.CATEGORIE, p.getCategorie());
		  value.put(PlatDAO.NOM, p.getNom());
		  value.put(PlatDAO.PRIX, p.getPrix());
		  value.put(PlatDAO.QUANTITE, p.getQuantite());
		  value.put(PlatDAO.INFO, p.getInfo());
		  value.put(PlatDAO.PHOTO, p.getPhoto());
		  Log.i("PLATDAO", "Bien ajouté : "+p.toString());
		  mDb.insert(PlatDAO.TABLE_NAME, null, value);
	  }

	  //PAS UTILISEE ICI : ON CONSIDERE QU'ON NE PEUT SUPPRIMER QUE DES CLIENTS
	  /*public void delete(String login) {
		  int ok = mDb.delete(TABLE_NAME, LOGIN + " = ?", new String[] {String.valueOf(login)});
		  if (ok > 0)
		  	Log.i("UserDAO", "Je delete "+login);
		  else
			  Log.i("UserDAO", "J'ai pas pu delete "+login);

	  }*/

	public int deletePlat(String nom) {
		int ok = mDb.delete(TABLE_NAME, NOM + " = ?", new String[]{String.valueOf(nom)});
		if (ok > 0)
			Log.i("PLATDAO", "Je delete le plat "+nom);
		else
			Log.i("PLATDAO", "J'ai pas pu delete le plat "+nom);
		return ok;
	}

	  public void deleteAll(){
		  mDb.delete(TABLE_NAME, null, null);
	  }


	  //MISE A JOUR DE LA QUANTITE DE u
	  public void update(Plat p) {
		  ContentValues value = new ContentValues();
		  value.put(bddMenu.DatabaseHandler.PLAT_QUANTITE, p.getQuantite());
		  mDb.update(TABLE_NAME, value, KEY + " = ?", new String[]{String.valueOf(p.getId())});

	  }

	  //RECUPERE UNE PHOTO DE PLAT A PARTIR DE SON NOM
	  public String selectPhotoByName(String nom) {
		  Cursor c = mDb.rawQuery("select * from " + TABLE_NAME + " where nom = ?", new String[]{nom});
		  c.moveToFirst();
		  String photo = c.getString(c.getColumnIndex(bddMenu.DatabaseHandler.PLAT_PHOTO));
		  c.close();
		  Log.i("PLATDAO", "Path de la photo " + photo);

		  return photo;
	  }

	public Plat selectPlatByName(String nom){
		Cursor c = mDb.rawQuery("select * from " + TABLE_NAME + " where nom = ?", new String[]{nom});
		c.moveToFirst();
		Plat p = new Plat(c.getString(c.getColumnIndex(DatabaseHandler.PLAT_CATEGORIE)),
				c.getString(c.getColumnIndex(DatabaseHandler.PLAT_NOM)),
						c.getString(c.getColumnIndex(bddMenu.DatabaseHandler.PLAT_PRIX)),
								c.getString(c.getColumnIndex(DatabaseHandler.PLAT_QUANTITE)),
										c.getString(c.getColumnIndex(bddMenu.DatabaseHandler.PLAT_INFO)),
												c.getString(c.getColumnIndex(bddMenu.DatabaseHandler.PLAT_PHOTO)));
		c.close();
		return p;
	}

	  public List<Plat> selectAll() {
		  List<Plat> plats = new ArrayList<Plat>();
		  Cursor c = mDb.rawQuery("select * from " + TABLE_NAME,null);

		  if (c .moveToFirst()) {
	            while (c.isAfterLast() == false) {
					Plat p = new Plat();
					p.setId(Integer.parseInt(c.getString(c.getColumnIndex(bddMenu.DatabaseHandler.PLAT_KEY))));
					p.setCategorie(c.getString(c.getColumnIndex(bddMenu.DatabaseHandler.PLAT_CATEGORIE)));
					p.setNom(c.getString(c.getColumnIndex(bddMenu.DatabaseHandler.PLAT_NOM)));
					p.setPrix(c.getString(c.getColumnIndex(bddMenu.DatabaseHandler.PLAT_PRIX)));
					p.setQuantite(c.getString(c.getColumnIndex(bddMenu.DatabaseHandler.PLAT_QUANTITE)));
					p.setInfo(c.getString(c.getColumnIndex(bddMenu.DatabaseHandler.PLAT_INFO)));
					p.setPhoto(c.getString(c.getColumnIndex(bddMenu.DatabaseHandler.PLAT_PHOTO)));

					//String name = c.getString(c.getColumnIndex(DatabaseHandler.USER_LOGIN));
	                plats.add(p);
	                c.moveToNext();
	            }
	        }
		  c.close();
		  Log.i("PLATDAO", "Liste de plats "+plats);

		  return plats;
	  }

	public List<Plat> selectAll(String categorie) {
		List<Plat> plats = new ArrayList<Plat>();
		Cursor c = mDb.rawQuery("select * from " + TABLE_NAME + " where categorie = ?", new String[]{categorie});

		if (c .moveToFirst()) {
			while (!c.isAfterLast()) {
				Plat p = new Plat();
				p.setId(Integer.parseInt(c.getString(c.getColumnIndex(bddMenu.DatabaseHandler.PLAT_KEY))));
				p.setCategorie(c.getString(c.getColumnIndex(bddMenu.DatabaseHandler.PLAT_CATEGORIE)));
				p.setNom(c.getString(c.getColumnIndex(bddMenu.DatabaseHandler.PLAT_NOM)));
				p.setPrix(c.getString(c.getColumnIndex(bddMenu.DatabaseHandler.PLAT_PRIX)));
				p.setQuantite(c.getString(c.getColumnIndex(bddMenu.DatabaseHandler.PLAT_QUANTITE)));
				p.setInfo(c.getString(c.getColumnIndex(bddMenu.DatabaseHandler.PLAT_INFO)));
				p.setPhoto(c.getString(c.getColumnIndex(bddMenu.DatabaseHandler.PLAT_PHOTO)));

				//String name = c.getString(c.getColumnIndex(DatabaseHandler.USER_LOGIN));
				plats.add(p);
				c.moveToNext();
			}
		}
		c.close();
		Log.i("PLATDAO", "Liste de plats "+plats);

		return plats;
	}
	  
	  
	  
		public int getProfilesCount() {
		    String countQuery = "SELECT  * FROM " + TABLE_NAME;
		    SQLiteDatabase db = mHandler.getReadableDatabase();
		    Cursor cursor = db.rawQuery(countQuery, null);
		    int cnt = cursor.getCount();
		    cursor.close();
			Log.i("PLATDAO", "J'ai" + cnt + "entrées");

			return cnt;
		}
		

}
