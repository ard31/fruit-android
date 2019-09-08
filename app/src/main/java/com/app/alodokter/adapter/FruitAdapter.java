package com.app.alodokter.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alodokter.R;
import com.app.alodokter.ui.activity.fruit.DetailFruitActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.MyViewHolder> {

    private Context context;
    private List fruit;

    public FruitAdapter(Context context, List fruit) {
        this.context = context;
        this.fruit = fruit;
    }

    @NonNull
    @Override
    public FruitAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fruit, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitAdapter.MyViewHolder holder, int position) {

        holder.cvFruit.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailFruitActivity.class);
            intent.putExtra("fruit", true);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return fruit.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cvFruit)
        CardView cvFruit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
