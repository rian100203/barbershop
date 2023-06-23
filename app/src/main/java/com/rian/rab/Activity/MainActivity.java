package com.rian.rab.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rian.rab.API.APIRequestData;
import com.rian.rab.API.RetroServer;
import com.rian.rab.Adapter.AdapterBarbershop;
import com.rian.rab.Model.ModelBarbershop;
import com.rian.rab.Model.ModelResponse;
import com.rian.rab.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvBarbershop;
    private FloatingActionButton fabAdd;
    private ProgressBar pbBarbershop;
    private RecyclerView.Adapter adBarbershop;
    private RecyclerView.LayoutManager lmBarbershop;
    List<ModelBarbershop> listBarbershop = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvBarbershop = findViewById(R.id.rv_barbershop);
        fabAdd = findViewById(R.id.fab_add);
        pbBarbershop = findViewById(R.id.pb_barbershop);

        lmBarbershop = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvBarbershop.setLayoutManager(lmBarbershop);

    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveBarbershop();
    }

    public void retrieveBarbershop(){
        pbBarbershop.setVisibility(View.VISIBLE);

        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardRetrieve();
        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listBarbershop = response.body().getData();
                adBarbershop = new AdapterBarbershop(MainActivity.this, listBarbershop);
                rvBarbershop.setAdapter(adBarbershop);
                adBarbershop.notifyDataSetChanged();
                pbBarbershop.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {

            }
        });
    }
}