package sirine.souissi.myown;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    Button buttonLogin, btnex;
    CheckBox cbremlog;

    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "my_shared_pref";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_REMEMBER = "remember";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextUsername = findViewById(R.id.edemail_log);
        editTextPassword = findViewById(R.id.edpwd_log);
        buttonLogin = findViewById(R.id.btnlog_log);
        btnex = findViewById(R.id.btnex_log);
        cbremlog = findViewById(R.id.cbrem_log);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);


        /*if (sharedPreferences.getBoolean(KEY_REMEMBER, false)) {
            String savedUsername = sharedPreferences.getString(KEY_USERNAME, "");
            String savedPassword = sharedPreferences.getString(KEY_PASSWORD, "");
            editTextUsername.setText(savedUsername);
            editTextPassword.setText(savedPassword);
            cbremlog.setChecked(true);
        }*/

        //set a click listener for the exit button:
        btnex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
            }
        });

        // Set a click listener for the login button:
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //les champs sont editable (gettext)
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();


                if (username.equals("Sirine") && password.equals("123")) {
                    if (cbremlog.isChecked()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(KEY_USERNAME, username);
                        editor.putString(KEY_PASSWORD, password);
                        editor.putBoolean(KEY_REMEMBER, true);
                        editor.apply();
                    } else {

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(KEY_USERNAME);
                        editor.remove(KEY_PASSWORD);
                        editor.remove(KEY_REMEMBER);
                        editor.apply();
                    }

                    // Redirect to HomeProfil activity
                    Intent intent = new Intent(getApplicationContext(), HomeProfil.class);
                    intent.putExtra("USER", username);
                    startActivity(intent);

                    // Successful login
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                } else {
                    // Failed login
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
