package com.example.administrator.quanmingzhibo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.quanmingzhibo.R;


public class LanmuFragment extends Fragment {
    private RecyclerView recyclerView;

    public LanmuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lanmu, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);

        return view;
    }


}
