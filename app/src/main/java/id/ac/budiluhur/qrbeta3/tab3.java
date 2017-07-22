package id.ac.budiluhur.qrbeta3;

/**
 * Created by Muhammad Saddam Nur on 4/19/2017.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class tab3 extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private List<UserModel> results = new ArrayList<>();
    private RecyclerViewAdapter2 viewAdapter;
    SwipeRefreshLayout swipeLayout;

    private static final int REQUEST_READ_PHONE_STATE = 0; //variabel request read phone status/
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tabmenu3,container,false);
        ButterKnife.bind(getActivity());

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView2);

        viewAdapter = new RecyclerViewAdapter2(getActivity(), results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);
        TelephonyManager tel;
        tel = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String teli0 = tel.getDeviceId();
        md5 md5 = new md5();
        String teli = md5.rmd5(teli0);

        LoadData(teli);

        swipeLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_container2);
        swipeLayout.setOnRefreshListener(this);

        return v;

    }

    private void LoadData(String search) {
        ApiService6 apiService = ApiClient.getClient().create(ApiService6.class);
        Call<ListUserModel> call = apiService.search(search);
        call.enqueue(new Callback<ListUserModel>() {
            @Override
            public void onResponse(Call<ListUserModel> call, Response<ListUserModel> response) {
                ListUserModel listUserModel = response.body();
                if(listUserModel.getStatus()==1){
                    List<UserModel> listQr = listUserModel.getQR();
                    RecyclerViewAdapter2 QrAdapter = new RecyclerViewAdapter2(getActivity(),listQr);
                    RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView2);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(QrAdapter);

                }else{
                    Toast.makeText(getActivity(), listUserModel.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ListUserModel> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public void onRefresh() {
        TelephonyManager tel;
        tel = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String teli0 = tel.getDeviceId();
        md5 md5 = new md5();
        String teli = md5.rmd5(teli0);

        LoadData(teli);
        swipeLayout.setRefreshing(false);
    }

}