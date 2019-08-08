package com.example.newsviewpager_tts;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.newsviewpager_tts.adapter.Postadapter;
import com.example.newsviewpager_tts.unity.Post;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class PostSaveActivity extends AppCompatActivity implements Postadapter.Postlistener {
    Realm realm;
    @BindView(R.id.recyclerview) RecyclerView recyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    ArrayList<Post> arrayList;
    Postadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_save);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        initview();
        initActionbar();
        initdata();
        setuprecyclerview();
    }

    private void initdata() {
        arrayList.clear();
        RealmResults results = realm.where(Post.class).findAll();
        arrayList.addAll(realm.copyFromRealm(results));
        adapter.notifyDataSetChanged();
    }

    private void initview() {
        arrayList = new ArrayList<>();
        adapter = new Postadapter(this,arrayList,this);
    }

    @Override
    public void onClickitem(Post post) {
        Intent intent = new Intent(PostSaveActivity.this, DetailActivity.class);
        intent.putExtra("link",post.getLinkpost());
        startActivity(intent);
    }
    // set up toolbar
    private void initActionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Realm database");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    // setup recyclerview
    public void setuprecyclerview(){
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    }
}
