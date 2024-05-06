package sirine.souissi.myown;

import static java.util.Locale.filter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import java.util.ArrayList;
import java.util.Locale;

public class ListProfil extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<Profil> data;
    MyRecycleProfilAdapter adapter;
    ArrayList<Profil> profils;
    SearchView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_profil);

        ProfilManager manager=new ProfilManager(ListProfil.this);
        manager.ouvrir();

        rv=findViewById(R.id.rv);
        sv=findViewById(R.id.sv);



        ArrayList<Profil> profils = manager.getAllProfiles();
        /*ArrayAdapter ad= new ArrayAdapter(ListProfil.this, android.R.layout.simple_list_item_1, Home.data);
        listview.setAdapter(ad);*/

        // a faire :: recuperer les donnees data depuis la base

        MyRecycleProfilAdapter ad=new MyRecycleProfilAdapter(ListProfil.this , profils);
        rv.setAdapter(ad);
        /*LinearLayoutManager layoutManager=new LinearLayoutManager(
                ListProfil.this,
                LinearLayoutManager.HORIZONTAL,
                true);*/
        //GridLayoutManager bech yafficher les profils fi grille verticale
        GridLayoutManager layoutManager=new GridLayoutManager(
                ListProfil.this,1,
                GridLayoutManager.VERTICAL,
                true);
        rv.setLayoutManager(layoutManager);
        ad.notifyDataSetChanged();




        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Recherche des profils correspondant au texte saisi
                ArrayList<Profil> searchResults = manager.searchProfiles(newText);
                // Mise à jour des données de l'adaptateur avec les résultats de la recherche
                ad.setData(searchResults);
                return true;
            }
        });

        /*sv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            //texte en train de modif
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                filterProfiles(s.toString());}


            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
    private void filterProfiles(String searchText) {
        ArrayList<Profil> filteredList = new ArrayList<>();
        //parcourir chaque profil dans data
        for (Profil profile : data) {
            //verifier si insensible  a la case
            if (profile.getName().toLowerCase().contains(searchText.toLowerCase()) ||
                    profile.getLastname().toLowerCase().contains(searchText.toLowerCase()) ||
                    profile.getNumber().contains(searchText)) {
                filteredList.add(profile);
            }
        }
        //creer new adapter avec la new liste filtrer
        adapter=new MyRecycleProfilAdapter(ListProfil.this, filteredList );
        rv.setAdapter(adapter);


    }*/


} }




