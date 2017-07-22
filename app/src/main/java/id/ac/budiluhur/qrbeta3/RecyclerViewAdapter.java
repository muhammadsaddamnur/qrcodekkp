package id.ac.budiluhur.qrbeta3;

/*Adapter untuk Rekap*/

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private Context context;
    private List<QrModel> results;

    public RecyclerViewAdapter(Context context, List<QrModel> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_recyclerview,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        QrModel result = results.get(position);

        try{
        holder.textViewdate.setText(result.getDate());
        holder.textViewsignin.setText(result.getSignin());
        holder.textViewsignout.setText(result.getSignout());
        holder.textViewtotal.setText(result.getTotal());
        }catch (Exception e){
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

        public TextView textViewdate;
        public TextView textViewsignin;
        public TextView textViewsignout;
        public TextView textViewtotal;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewdate = (TextView) itemView.findViewById(R.id.textdate);
            textViewsignin = (TextView) itemView.findViewById(R.id.textsignin);
            textViewsignout = (TextView) itemView.findViewById(R.id.textsignout);
            textViewtotal = (TextView) itemView.findViewById(R.id.texttotal);

        }
    }
}