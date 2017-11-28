package com.uca.apps.isi.nct.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tumblr.remember.Remember;
import com.uca.apps.isi.nct.Adapters.PictureAdapter;
import com.uca.apps.isi.nct.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.uca.apps.isi.nct.activities.ComplaintAddActivity;
import com.uca.apps.isi.nct.Adapters.ComplaintsAdapter;
import com.uca.apps.isi.nct.api.Api;
import com.uca.apps.isi.nct.models.AccessToken;
import com.uca.apps.isi.nct.models.Complaint;
import com.uca.apps.isi.nct.models.Picture;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComplaintsFragment extends Fragment {

    private RecyclerView recyclerView;


    public ComplaintsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_complaints, container, false);


        //Call init method
        ini(view);
        init(view);
        return view;
    }

    /**
     * This method instance variables
     * @param view
     */

    private void init(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        Call<List<Complaint>> call = Api.instance().getComplaints(Remember.getString("access_token", ""));
        call.enqueue(new Callback<List<Complaint>>() {
            @Override
            public void onResponse(Call<List<Complaint>> call, Response<List<Complaint>> response) {
                if (response.body() != null) {
                    //ComplaintsAdapter complaintsAdapter = new ComplaintsAdapter(response.body());
                    ComplaintsAdapter complaintsAdapter = new ComplaintsAdapter(getContext(), response.body());
                    recyclerView.setAdapter(complaintsAdapter);
                } else {
                    Log.e("Error", "Error al cargar las denuncias");
                    //Toast.makeText(getContext(), "Error al cargar las denuncias", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Complaint>> call, Throwable t) {
                Log.e("xD", t.getMessage());

            }
        });
    }

    private void ini(View view){
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Call<List<Picture>> call = Api.instance().getPictures(Remember.getString("access_token", ""));
        call.enqueue(new Callback<List<Picture>>() {
            @Override
            public void onResponse(Call<List<Picture>> call, Response<List<Picture>> response) {
                    if(response.body() != null) {

                    /*for(Complaint complaint : response.body()) {
                        Log.i("DEBUG", complaint.getTitle());
                    }*/

                    PictureAdapter pictureAdapter = new PictureAdapter(response.body());
                    recyclerView.setAdapter(pictureAdapter);
                }
                else{
                    Toast.makeText(getContext(), "Error al cargar las denuncias", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Picture>> call, Throwable t) {
                Log.e("xD", t.getMessage());

            }
        });
    }
}
