package com.example.assignment6.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment6.R;
import com.example.assignment6.adapter.MyAdapter;
import com.example.assignment6.adapter.PostAdapter;
import com.example.assignment6.api.ApiClient;
import com.example.assignment6.api.ApiInterface;
import com.example.assignment6.modal.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailsFragment extends Fragment  {
    private ImageView imageView;
    private TextView tvName, tvUserName;
    private Button btnShowPosts;
    private ApiInterface apiInterface;
    ArrayList<User>userArrayList=new ArrayList<>();
    private Context mContext;
    private ImageView crossImageView;
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_details_fragment, container, false);

        imageView = view.findViewById(R.id.image_view);
        tvName = view.findViewById(R.id.rv_tv_name);
        tvUserName = view.findViewById(R.id.rv_tv_user_name);
        btnShowPosts = view.findViewById(R.id.btn_show_post);

        btnShowPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(mContext);
                dialog.setContentView(R.layout.dialog);
                crossImageView=dialog.findViewById(R.id.cross_image_view);
                crossImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.setCancelable(false);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                recyclerView = dialog.findViewById(R.id.recycler_view1);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                recyclerView.setLayoutManager(linearLayoutManager);
                apiInterface= ApiClient.getClient().create(ApiInterface.class);
                Call<ArrayList<User>>call=apiInterface.getUserPost();
                call.enqueue(new Callback<ArrayList<User>>() {
                    @Override
                    public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                        userArrayList.addAll(response.body());
                        postAdapter = new PostAdapter(userArrayList);
                        recyclerView.setAdapter(postAdapter);
                        postAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<ArrayList<User>> call, Throwable t) {

                    }
                });

                dialog.show();

            }

        });

        return view;
    }

    public void showResult(User user) {
     tvName.setText(String.format("Name:%s", user.getName()));
     tvUserName.setText(String.format("UserName:%s", user.getUserName()));
    }
}
