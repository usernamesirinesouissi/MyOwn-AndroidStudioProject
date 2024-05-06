package sirine.souissi.myown;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ProfilManager {

    SQLiteDatabase db;
    Context con;
    ProfilManager (Context con){
        this.con=con;
    }





    public void ouvrir()  {
        ProfilHelper helper = new ProfilHelper(con, "mabase.db",null, 8);
        db= helper.getWritableDatabase();
    }
    public long ajout(String na,String ln,String nu) {
        //This is a data type in Java
        long a=0;
        //hashmap(clé,valeur)
        ContentValues values= new ContentValues();
        values.put(ProfilHelper.col_name, na);
        values.put(ProfilHelper.col_lastname, ln);
        values.put(ProfilHelper.col_number, nu);

        a= db.insert(ProfilHelper.table_profil, null,values);
        return a;
    }
    public ArrayList<Profil> getAllProfiles() {
        ouvrir();
        //liste jdida
        ArrayList<Profil> l = new ArrayList<>();
        //query method bech taamel recherche ml data
        Cursor cr = db.query( ProfilHelper.table_profil,
                new String[] {
                        ProfilHelper.col_name,
                        ProfilHelper.col_lastname,
                        ProfilHelper.col_number},null,null,null,null,null);
        cr.moveToFirst();
        while(!cr.isAfterLast()) {
            String i1 = cr.getString(0);
            String i2 = cr.getString(1);
            String i3 = cr.getString(2);
            l.add(new Profil(i1, i2, i3));
            cr.moveToNext();
        }
        return l;
    }

    public long supprimer(String number)  {
        //bech na9souuu ligne ml base
        int a=-1;
        a= db.delete(ProfilHelper.table_profil, ProfilHelper.col_number+"="+number,null);
        return a;


    }

    public void fermer()  {


    }

    public long updateProfile(String number, String newName, String newLastName) {
        ContentValues values = new ContentValues();
        values.put(ProfilHelper.col_name, newName);
        values.put(ProfilHelper.col_lastname, newLastName);


        int a = db.update(ProfilHelper.table_profil, values, ProfilHelper.col_number + " = ?", new String[]{number});

        return a;
    }
    public ArrayList<Profil> searchProfiles(String query) {
        ArrayList<Profil> searchResults = new ArrayList<>();
        Cursor cr = db.query(ProfilHelper.table_profil,
                new String[]{ProfilHelper.col_name, ProfilHelper.col_lastname, ProfilHelper.col_number},
                ProfilHelper.col_name + " LIKE ? OR " +
                        ProfilHelper.col_lastname + " LIKE ? OR " +
                        ProfilHelper.col_number + " LIKE ?",
                new String[]{"%" + query + "%", "%" + query + "%", "%" + query + "%"},
                null, null, null);
        cr.moveToFirst();
        while (!cr.isAfterLast()) {
            String name = cr.getString(0);
            String lastName = cr.getString(1);
            String number = cr.getString(2);
            searchResults.add(new Profil(name, lastName, number));
            cr.moveToNext();
        }
        cr.close();
        return searchResults;
    }




}
