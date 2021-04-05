package com.example.testtask;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.SimpleViewHolder> {

    private ArrayList<String> arrayList = new ArrayList<>();
    private OnImageListener monImageListener;

    public RecyclerViewAdapter(OnImageListener onImageListener) {
        this.monImageListener = onImageListener;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items, parent, false);
        return new SimpleViewHolder(view, monImageListener);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.bind(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setItems(ArrayList<String> strings) {
        arrayList.clear();
        arrayList.addAll(strings);
        notifyDataSetChanged();
    }

    class SimpleViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        OnImageListener onImageListener;

        public SimpleViewHolder(View itemView, OnImageListener onImageListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            this.onImageListener = onImageListener;

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                onImageListener.onImageClick(position);
            });
        }

        public void bind(String string) {
            Picasso.get()
                    .load(string)
                    .error(R.drawable.image_error)
                    .into(imageView);
        }
    }

    public interface OnImageListener {
        void onImageClick(int position);
    }
}