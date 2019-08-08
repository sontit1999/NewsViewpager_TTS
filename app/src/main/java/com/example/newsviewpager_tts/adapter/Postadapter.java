package com.example.newsviewpager_tts.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newsviewpager_tts.R;
import com.example.newsviewpager_tts.unity.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Postadapter extends RecyclerView.Adapter<Postadapter.myviewhoder> {
    Context context;
    ArrayList<Post> arrayList;
    Postlistener listener;
    public Postadapter(Context context, ArrayList<Post> arrayList, Postlistener listener) {
        this.context = context;
        this.arrayList = arrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public myviewhoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post,null);
        return new myviewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewhoder myviewhoder, int i) {
              final Post post = arrayList.get(i);
              Glide.with(context).load(post.getLinkthumbail()).into(myviewhoder.img);
              myviewhoder.txttittle.setText(post.getTittle());
              myviewhoder.txttimeandfrom.setText(post.getFromnew()+" - "+post.getTimepost());

              myviewhoder.containerpost.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      listener.onClickitem(post);
                  }
              });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class myviewhoder extends RecyclerView.ViewHolder{
        LinearLayout containerpost;
        ImageView img;
        TextView txttittle,txttimeandfrom;
        public myviewhoder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imageviewcontent);
            txttittle = (TextView) itemView.findViewById(R.id.textviewtittlecontent);
            txttimeandfrom  = (TextView) itemView.findViewById(R.id.textviewtimeandfrom);
            containerpost = (LinearLayout) itemView.findViewById(R.id.containerpost);
        }
    }
    public interface Postlistener{
        void onClickitem(Post post);
    }
}
