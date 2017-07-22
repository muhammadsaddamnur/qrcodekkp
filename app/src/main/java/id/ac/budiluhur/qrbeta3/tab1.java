package id.ac.budiluhur.qrbeta3;

/**
 * Created by Muhammad Saddam Nur on 4/19/2017.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class tab1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tabmenu,container,false);

/*
        SharedPreferences prefs = getActivity().getSharedPreferences("myData", Context.MODE_PRIVATE);
        String signin = prefs.getString("signin", "");

        TextView txtmasuk = (TextView) getActivity().findViewById(R.id.txtmasuk);
        TextView txtmasuk2 = (TextView) getActivity().findViewById(R.id.txtmasuk2);
        txtmasuk2.setText(prefs.getString("signin",""));

*/
        return v;

    }

}