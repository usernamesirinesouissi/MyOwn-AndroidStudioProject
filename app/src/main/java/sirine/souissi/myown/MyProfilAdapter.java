package sirine.souissi.myown;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyProfilAdapter extends BaseAdapter {
    Context cont;
    ArrayList<Profil> data;




    MyProfilAdapter(Context con,ArrayList<Profil> data) {

        //le role de l'adapter c'est de créer les views:
        this.cont=cont;
        this.data=data;


    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //convertir le code xml à un code java
        LayoutInflater inf = LayoutInflater.from(cont);
        View v = inf.inflate(R.layout.activity_view_profil,null);

        TextView tvname= v.findViewById(R.id.tvedlastname_vp);
        TextView tvlastname= v.findViewById(R.id.tvedlastname_vp);
        TextView tvnumber = v.findViewById(R.id.tvednum_vp);

        ImageView imgcall= v.findViewById(R.id.imgcall_vp);
        ImageView imgedit = v.findViewById(R.id.imgedit_vp);
        ImageView imgdel = v.findViewById(R.id.imgdelete_vp);

        //recuperation des données
        Profil pr = data.get(position);

        //les actions sur les holders: (delete,edit,call)

        imgdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerte = new AlertDialog.Builder(cont);
                alerte.setTitle("Suppression");
                alerte.setMessage("Confirmer la suppression?");
                alerte.setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Suppression :
                        data.remove(position);
                        //Refresh :
                        notifyDataSetChanged();
                    }
                });

                alerte.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerte.setNeutralButton("Exit",null);
                alerte.show();

            }
        });

        imgcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //numerotation
                Intent i = new Intent();
                i.setAction(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+pr.number));
                cont.startActivity(i);

            }
        });

        imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start an activity to edit the profile data
                Intent editIntent = new Intent(cont, view_profil.class);

                cont.startActivity(editIntent);
            }
        });



        return v;
    }
}
