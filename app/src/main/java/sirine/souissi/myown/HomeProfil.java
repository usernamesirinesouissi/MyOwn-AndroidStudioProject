package sirine.souissi.myown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeProfil extends AppCompatActivity {

    TextView tvuser; // Déclaration de TextView
    Button btnadd, btnlistpro, btnlogout; // Déclaration des boutons


    //on utilise les sharedpreferneces khater aana stockage des donnéess
    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "my_shared_pref";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_REMEMBER = "remember";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_profil);


        tvuser = findViewById(R.id.tvuser_home);
        btnadd = findViewById(R.id.btnadd_home);
        btnlistpro = findViewById(R.id.btnlistpro_home);
        btnlogout = findViewById(R.id.btnlogout_home);


        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        // Récupération des données ml activity eli kbal
        Intent x = getIntent();
        Bundle b = x.getExtras();
        String u = b.getString("USER");


        tvuser.setText("Welcome M." + u);


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirection vers l'activité d'ajout de profil
                Intent intent = new Intent(getApplicationContext(), AddProfil.class);
                startActivity(intent);
            }
        });


        btnlistpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ListProfil.class);
                startActivity(intent);
            }
        });


        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vérifier si "Remember Me" était cochée et effacer les clés eli dakhalthomlou ki naamel fl remeber me bech hakek
                //maymesech les donnes static eli hatithom
                if (sharedPreferences.getBoolean(KEY_REMEMBER, false)) {
                    //sharedpreferneces.editor est une interface utiliser pour faire la modification
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(KEY_USERNAME);
                    editor.remove(KEY_PASSWORD);
                    editor.remove(KEY_REMEMBER);
                    editor.apply();
                } else {

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(KEY_REMEMBER);
                    editor.apply();
                }


                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                finish();
            }
        });
    }
}
