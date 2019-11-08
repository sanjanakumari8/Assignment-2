package com.example.assignment6.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment6.R;
import com.example.assignment6.activity.MainActivity;
import com.example.assignment6.adapter.MyAdapter;
import com.example.assignment6.api.ApiClient;
import com.example.assignment6.api.ApiInterface;
import com.example.assignment6.modal.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


public class UserListFragment extends Fragment implements MyAdapter.ItemClicked {
    private RecyclerView recyclerView;
    private Context mContext;
    private MyAdapter myAdapter;
    private ArrayList<User> userArrayList = new ArrayList<>();
    private ApiInterface apiInterface;
    private TextView tvInternetConnection;
    private Button btnRetry;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_list_fragment, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        tvInternetConnection=view.findViewById(R.id.tv_internet_available);
        btnRetry=view.findViewById(R.id.btn_retry);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        setRetrofit();
        return view;
    }

    public void setRetrofit(){
      apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<User>>call=apiInterface.getUserList();
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                userArrayList.addAll(response.body());
                myAdapter = new MyAdapter(userArrayList,UserListFragment.this);
                recyclerView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
                btnRetry.setVisibility(View.INVISIBLE);
                tvInternetConnection.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Toast.makeText(mContext, "failed to connect", Toast.LENGTH_SHORT).show();
                btnRetry.setVisibility(View.VISIBLE);
                tvInternetConnection.setVisibility(View.VISIBLE);
                btnRetry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        setRetrofit();


                    }
                });

            }
        });

    }

    @Override
    public void onItemClicked(int position) {
        User user=userArrayList.get(position);
        ((MainActivity)mContext).sendUserData(user);


    }
}
