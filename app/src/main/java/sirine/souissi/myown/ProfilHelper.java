package sirine.souissi.myown;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ProfilHelper extends SQLiteOpenHelper {

    public static final  String table_profil="Profils";
    public static final  String col_name="name";
    public static final  String col_lastname="lastname";
    public static final  String col_number="number";

    String req="create table "+table_profil+"("+col_name +" Text not null," +col_lastname+" Text not null,"+col_number+" Text not null"+")";

    public ProfilHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        //lors de l'ouverture de la base pour la premier fois:
        db.execSQL(req);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //modification de la version
        db.execSQL(" drop table "+table_profil);
        onCreate(db);

    }


}
