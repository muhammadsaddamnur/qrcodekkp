package id.ac.budiluhur.qrbeta3;

/*Adapter untuk profil*/

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by saddamnur on 09/06/17.
 */

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder> {


    private Context context;
    private List<UserModel> results;

    public RecyclerViewAdapter2(Context context, List<UserModel> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_recyclerview2, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserModel result = results.get(position);


        try {

            String key = "MINDREACH";


            VigenereCipher VigenereCipher = new VigenereCipher();

            String nama = VigenereCipher.vigenere_cipher(result.getNama().toString(), key, false);
            String email = VigenereCipher.vigenere_cipher(result.getEmail().toString(), key, false);
            String notelepon = VigenereCipher.vigenere_cipher(result.getNotelepon().toString(), key, false);
            String noktp = VigenereCipher.vigenere_cipher(result.getNoktp().toString(), key, false);
            String tgllahir = VigenereCipher.vigenere_cipher(result.getTgllahir().toString(), key, false);
            String alamat = VigenereCipher.vigenere_cipher(result.getAlamat().toString(), key, false);

            holder.txtid.setText(result.getImei());
            holder.txtnama.setText(nama);
            holder.txtemail.setText(email);
            holder.txtnotelepon.setText(notelepon);
            holder.txtnoktp.setText(noktp);
            holder.txttgllahir.setText(tgllahir);
            holder.txtalamat.setText(alamat);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public Context getContext() {
        return context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtid;
        public TextView txtnama;
        public TextView txtemail;
        public TextView txtnotelepon;
        public TextView txtnoktp;
        public TextView txttgllahir;
        public TextView txtalamat;


        public ViewHolder(View itemView) {
            super(itemView);
            txtid = (TextView) itemView.findViewById(R.id.textid);
            txtnama = (TextView) itemView.findViewById(R.id.textnama);
            txtemail = (TextView) itemView.findViewById(R.id.textemail);
            txtnotelepon = (TextView) itemView.findViewById(R.id.txtnotelp);
            txtnoktp = (TextView) itemView.findViewById(R.id.txtnktp);
            txttgllahir = (TextView) itemView.findViewById(R.id.txttlahir);
            txtalamat = (TextView) itemView.findViewById(R.id.txtalmt);

        }
    }
}
