package com.example.assignment6.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment6.R;
import com.example.assignment6.modal.User;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<User>userArrayList;
    // private Context mContext;
    private ItemClicked itemClicked;

    public MyAdapter(ArrayList<User> userArrayList,ItemClicked itemClicked) {
       this.userArrayList=userArrayList;
       this.itemClicked=itemClicked;
    }

    public MyAdapter(ArrayList<User> userArrayList) {
        this.userArrayList=userArrayList;
    }


    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        this.mContext=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_layout,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyAdapter.ViewHolder holder, int position) {
        holder.tvId.setText(String.valueOf(userArrayList.get(position).getId()));
        holder.name.setText(userArrayList.get(position).getName());
        holder.userName.setText(userArrayList.get(position).getUserName());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClicked.onItemClicked(holder.getAdapterPosition());
            }
        });


    }

    @Override
    public int getItemCount() {
        return userArrayList==null?0:userArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId,name,userName;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId= itemView.findViewById(R.id.tv_id);
            name=itemView.findViewById(R.id.tv_name);
            userName=itemView.findViewById(R.id.tv_user_name);
            linearLayout=itemView.findViewById(R.id.linear_layout);
        }
    }
    public interface ItemClicked{
        void onItemClicked(int position);
    }
}
