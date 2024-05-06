package sirine.souissi.myown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddProfil extends AppCompatActivity {

    EditText edfn,edln,ednum;
    Button btnsave,btncancel,btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profil);

        edfn = findViewById(R.id.edfirstname_add);
        edln = findViewById(R.id.edLastname_add);
        ednum = findViewById(R.id.ednum_add);
        btnsave = findViewById(R.id.btnsave_add);
        btncancel = findViewById(R.id.btncancel_add);
        btnback = findViewById(R.id.btnback_add);

        ProfilManager manager= new ProfilManager(AddProfil.this);
        manager.ouvrir();

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edfn.getText().toString();
                String lastname = edln.getText().toString();
                String number = ednum.getText().toString();

                //il faut remplir tt les champs
                if (name.isEmpty() || lastname.isEmpty() || number.isEmpty()) {
                    Toast.makeText(AddProfil.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();

                
                }else {
                    manager.ajout(name, lastname, number);
                    // affichage de msg suuccessully
                    Toast.makeText(AddProfil.this, "Profile saved successfully", Toast.LENGTH_SHORT).show();

                    edfn.setText("");
                    edln.setText("");
                    ednum.setText("");

                    //Intent intent = new Intent(AddProfil.this,ListProfil.class);
                    //startActivity(intent);

                }

            }
        });




        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                edfn.setText("");
                edln.setText("");
                ednum.setText("");
                // reset les champs
                Toast.makeText(AddProfil.this, "Fields cleared", Toast.LENGTH_SHORT).show();
            }
        });


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddProfil.this.finish();
            }
        });
    }
}