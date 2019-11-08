package com.example.assignment6.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment6.R;
import com.example.assignment6.modal.User;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    private ArrayList<User>userArrayList;

    public PostAdapter(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public PostAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.post_rv_layout,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.MyViewHolder holder, int position) {
        holder.userId.setText(String.valueOf(userArrayList.get(position).getUserId()));
        holder.namePost.setText(userArrayList.get(position).getName());
        holder.titlePost.setText(userArrayList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return userArrayList==null?0:userArrayList.size() ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView userId,namePost,titlePost;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userId=itemView.findViewById(R.id.tv_user_id_post);
            namePost=itemView.findViewById(R.id.tv_name_post);
            titlePost=itemView.findViewById(R.id.tv_user_title_post);
        }
    }
}
