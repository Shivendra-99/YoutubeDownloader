package com.marwaeltayeb.youtubedownloader.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.marwaeltayeb.youtubedownloader.R;
import com.marwaeltayeb.youtubedownloader.adapter.ItemAdapter;
import com.marwaeltayeb.youtubedownloader.models.Item;
import com.marwaeltayeb.youtubedownloader.network.ItemViewModel;

public class PlaylistActivity extends AppCompatActivity implements ItemAdapter.ItemAdapterOnClickHandler{

    public static String keyWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        Intent intent = getIntent();
        keyWord = intent.getStringExtra(SearchActivity.KEYWORD);

        SetUpRecyclerView();
    }

    private void SetUpRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.playlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        // Getting our ItemViewModel
        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);

        // Creating the Adapter
        final ItemAdapter adapter = new ItemAdapter(this,this);


        // Observing the itemPagedList from ViewModel
        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<Item>>() {
            @Override
            public void onChanged(@Nullable PagedList<Item> items) {
                // In case of any changes, submitting the items to adapter
                adapter.submitList(items);
            }
        });

        // Setting the adapter
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(String videoId) {
        Intent intent = new Intent(PlaylistActivity.this, DownloadActivity.class);
        // Pass the id of the video
        intent.putExtra("id", videoId);
        startActivity(intent);
    }

}
