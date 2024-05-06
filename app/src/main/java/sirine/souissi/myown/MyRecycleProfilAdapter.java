package sirine.souissi.myown;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

public class MyRecycleProfilAdapter extends RecyclerView.Adapter<MyRecycleProfilAdapter.MyViewHolder>{

    Context cont;
    ArrayList <Profil> data;
    private ArrayList<Profil> searchData;





    public MyRecycleProfilAdapter(Context con, ArrayList<Profil> data) {
        this.cont = con;
        this.data = data;
        this.searchData= searchData;


    }


    @NonNull
    @Override
    //Crée une nouvelle vue pour chaque élément de la liste.
    public MyRecycleProfilAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(cont);
        View v = inf.inflate(R.layout.activity_view_profil,null);
        return new MyViewHolder(v);
    }

    @Override
    //Associe les données de chaque profil à une vue spécifique dans la RecyclerView.
    public void onBindViewHolder(@NonNull MyRecycleProfilAdapter.MyViewHolder holder, int position) {
        //onbind : modification des holders:
        Profil pr = data.get(position);
        //affecter les views : holder
        holder.tvname.setText(pr.name);
        holder.tvlastname.setText(pr.lastname);
        holder.tvnumber.setText(pr.number);

    }

    @Override
    public int getItemCount() {
        //nombre totale des views
        return data.size();
    }

    public void setData(ArrayList<Profil> searchResults) {
        this.data.clear(); // Clear the existing data
        this.data.addAll(searchResults); // Add new data
        notifyDataSetChanged();
    }


    //Classe interne MyViewHolder yextendi ml recyclerview
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvname,tvlastname,tvnumber;

        ImageView imgDelete,imgEdit,imgCall,imgProfile;




        public MyViewHolder(@NonNull View v) {

            super(v);
            tvname=v.findViewById(R.id.tvedname_vp);
            tvlastname=v.findViewById(R.id.tvedlastname_vp);
            tvnumber = v.findViewById(R.id.tvednum_vp);
            imgDelete=v.findViewById(R.id.imgdelete_vp);
            imgEdit=v.findViewById(R.id.imgedit_vp);
            imgCall=v.findViewById(R.id.imgcall_vp);





            ProfilManager manager=new ProfilManager(cont);
            manager.ouvrir();

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alerte= new AlertDialog.Builder(cont);
                    alerte.setTitle("Delete");
                    alerte.setMessage("Confirm the deletion ? ");
                    alerte.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int position=getAdapterPosition();
                            manager.supprimer(data.get(position).number);
                            data.remove(position);//suppression ml data
                            notifyDataSetChanged();//Updated
                        }
                    });
                    alerte.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alerte.setNeutralButton("Exit",null);
                    alerte.show();
                }
            });

            imgCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent i = new Intent();
                    i.setAction(Intent.ACTION_DIAL);

                    i.setData(Uri.parse("tel:" + data.get(position).number));
                    cont.startActivity(i);

                    String phoneNumber = data.get(position).number;


                    //tester si les permissions sont accodrée
                    if (ContextCompat.checkSelfPermission(cont, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        //demander les permissions
                        ActivityCompat.requestPermissions((Activity) cont, new String[]{android.Manifest.permission.CALL_PHONE},
                               1);
                    } else {

                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + phoneNumber));
                        cont.startActivity(callIntent);
                    }

                }






            });

            imgEdit.setOnClickListener(new View.OnClickListener() {
                AlertDialog dialog;

                @Override
                public void onClick(View v) {

                    AlertDialog.Builder alert = new AlertDialog.Builder(cont);
                    //alert.setTitle("Update");
                    //alert.setMessage("Confirm Update Profile ?");
                    LayoutInflater inf= LayoutInflater.from(cont);
                    v = inf.inflate(R.layout.activity_view_dialog, null);


                    //recuperation les views
                    EditText ednom=v.findViewById((R.id.edname_vd));
                    EditText edprenom=v.findViewById((R.id.edlastname_vd));
                    EditText ednumero=v.findViewById((R.id.ednumber_vd));
                    ednumero.setEnabled(false);

                    Button btnsave=v.findViewById(R.id.btn_save_vd);
                    Button btncancel=v.findViewById(R.id.btncancel_vd);



                    btnsave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //trim tfasakh les espaces au debut et au fin les chaine de caractéres
                            String newNom = ednom.getText().toString().trim();
                            String newPrenom = edprenom.getText().toString().trim();


                            manager.updateProfile(data.get(getAdapterPosition()).number, newNom, newPrenom);

                            Profil updatedProfile = data.get(getAdapterPosition());
                            updatedProfile.name = newNom;
                            updatedProfile.lastname = newPrenom;
                            data.set(getAdapterPosition(), updatedProfile);

                            notifyDataSetChanged();//refresh

                            dialog.dismiss();
                        }
                    });

                    btncancel.setOnClickListener((new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();

                        }
                    }));

                    alert.setView(v);

                    dialog=alert.create();
                    dialog.show();

                }



            });


        }


    }







}




