package com.app.alodokter.ui.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alodokter.R;
import com.app.alodokter.adapter.FruitAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.rvFruit)
    RecyclerView rvFruit;

    FruitAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_home, null, false);
        ButterKnife.bind(this, layout);

        List<String> request = new ArrayList<>();
        request.add("test");
        request.add("test");
        request.add("test");

        adapter = new FruitAdapter(getContext(), request);
        rvFruit.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFruit.setAdapter(adapter);

        return layout;
    }

}
