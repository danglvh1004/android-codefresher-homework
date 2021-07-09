package com.example.lessson10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlbumListAdapter adapter;
    private List<Album> albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        albumList = new ArrayList<>();

        adapter = new AlbumListAdapter(this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        callApi();
    }

    private void initUI() {
        recyclerView = findViewById(R.id.recyclerview);
    }

    private void callApi() {
        ApiService.apiService.getAllAlbums().enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                albumList = response.body();

                if (albumList != null) {
                    adapter.setData(albumList);
                    displayToast("Call Api Sucessfully");
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                displayToast("Call Api Fail");
            }
        });
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}