package com.example.ihm.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

import bddUser.User;
import bddUser.UserDAO;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Configuration config = getBaseContext().getResources().getConfiguration();
        Locale mLocale = new Locale("fr");
        if (config.locale.equals(mLocale)) {
            config.locale = mLocale;
            getBaseContext().getResources().updateConfiguration(config, null);
        } else {
            config.locale = new Locale("en");
            getBaseContext().getResources().updateConfiguration(config, null);
        }
    }

    /** Switch back to main activity */
    public void backToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void login(View view) {
        String username = ((EditText)findViewById(R.id.editTextUsername)).getText().toString();
        String password = ((EditText)findViewById(R.id.editTextPassword)).getText().toString();

        User usr;
        Intent intent = new Intent();

        // RECUPERER USER.
        UserDAO dao = UserDAO.getInstance(this);
        bddUser.DatabaseHandler bd = new bddUser.DatabaseHandler(this);
        dao.open();
        usr = dao.select(username,password);
        dao.close();


        if (usr == null)
            ((TextView)findViewById(R.id.textViewErrLogin)).setText(R.string.erreurLogin);
        else {
            String type = usr.getCategorie();
            if (type.equals("serveur")) {
                intent = new Intent(this, serveurMainActivity.class);
            }
            if (type.equals("cuisinier")) {
                intent = new Intent(this, cuisinierMainActivity.class);
            }
            if (type.equals("client")) {
                intent = new Intent(this, OrderActivity.class);
            }
            intent.putExtra("name",usr.getLogin());
            startActivity(intent);
        }
    }

}