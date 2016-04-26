package bddUser;

//REPRESENTE UN UTILISATEUR DU SYSTEME DANS LA BASE DE DONNEES
public class User{
	  private long id;
	  private String login;
	  private String password;
	//CATEGORIE PARMI "CLIENT","SERVEUR" OU "CUISINIER"
	  private String categorie;
	  
	  public User(String login, String password, String categorie) {
	    super();
	    this.login = login;
	    this.password = password;
		this.categorie = categorie;
	  }

	public User(int id, String login, String password, String categorie) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.categorie = categorie;
	}

	public User(){}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCategorie() { return categorie; }

	public void setCategorie(String categorie){
		this.categorie = categorie;
	}

	public String toString(){
		return "ID = "+id+", login = "+login+", pass = "+password+", categorie = "+categorie+"\n";
	}

	}

