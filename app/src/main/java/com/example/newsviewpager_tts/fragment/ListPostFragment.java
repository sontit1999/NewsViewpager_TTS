package com.example.newsviewpager_tts.fragment;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.newsviewpager_tts.R;
import com.example.newsviewpager_tts.adapter.Postadapter;
import com.example.newsviewpager_tts.unity.Post;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListPostFragment extends Fragment implements Postadapter.Postlistener {
    @BindView(R.id.progress) ProgressBar progressBar;
    @BindView(R.id.imagetop) ImageView imgtop;
    @BindView(R.id.recyclerviewcontent) RecyclerView recyclerView;
    ArrayList<Post> arrayListPost;
    SwipeRefreshLayout swl;
    String link;
    Postadapter adapter;

    public ListPostFragment() {
    }

    @SuppressLint("ValidFragment")
    public ListPostFragment(String link) {
        this.link = link;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listpost,container,false);
        anhxa(view);
        init();
        initRecyclerview();
        sukienswipe();
        return view;
    }

    private void anhxa(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerviewcontent);
        progressBar = view.findViewById(R.id.progress);
        ImageView imgtop = view.findViewById(R.id.imagetop);
        swl = view.findViewById(R.id.swiperefresh);

        imgtop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(0);
            }
        });
    }

    private void init() {
        arrayListPost = new ArrayList<>();
        adapter = new Postadapter(getContext(),arrayListPost,this);

    }
    // setup recyclerview
    public void initRecyclerview(){
       // recyclerView.setHasFixedSize(true);
        // add divider item
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));
        recyclerView.addItemDecoration(itemDecorator);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


    }
    // lấy data
    public void initdata()
    {
        arrayListPost.clear();
        adapter.notifyDataSetChanged();
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                Elements items = document.select(".story");
                String linkthumbail,tittle,timeago,from,linkpost,timeStamp;
                String[] arr,arrtimenow;
                int hourpost,hournow,minutepost,minutenow;
                for(Element i:items)
                {
                    linkthumbail = i.select(".story__thumb a img").attr("src");
                    tittle = i.select(".story__heading a").text();
                    timeago = i.select(".story__meta .time").attr("datetime");
                    from = i.select(".story__meta .source").text();
                    linkpost = "https://baomoi.com" + i.select(".story__heading a").attr("href");
                    if(!(linkthumbail.length() == 0 || tittle.length() == 0 || timeago.length() == 0 || linkpost.length() ==0))
                    {
                        String[] time = timeago.split("T");
                        String daypost = time[0].replace('-', '/');
                        String timepost = time[1].substring(0,5);
                        String[] mangtime = timepost.split(":");
                        hourpost = Integer.parseInt(mangtime[0]);
                        minutepost = Integer.parseInt(mangtime[1]);
                        timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                        arrtimenow = timeStamp.split(":");
                        hournow = Integer.parseInt(arrtimenow[0]);
                        minutenow = Integer.parseInt(arrtimenow[1]);
                        if(hournow > hourpost) {
                            timeago = (hournow - hourpost) + " giờ trước";
                        }else{
                            timeago = (minutenow - minutepost) + " phút trước";
                        }
                        Log.d("time",hournow + ":" + minutenow);
                        arrayListPost.add(new Post(linkthumbail,linkpost,tittle,from,timeago));
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                }
                adapter.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(stringRequest);
    }
    // bắt sự kiện swipe
    public void sukienswipe()
    {
        swl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(), "Đã cập nhật ! ahihi ^^", Toast.LENGTH_SHORT).show();
                swl.setRefreshing(false);
            }
        });
    }

    @Override
    public void onClickitem(Post post) {
        Toast.makeText(getActivity(), "Fragment đã nhận dc", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initdata();
    }
}
