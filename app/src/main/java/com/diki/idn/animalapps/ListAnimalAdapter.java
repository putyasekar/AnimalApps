package com.diki.idn.animalapps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.BreakIterator;
import java.util.ArrayList;

public class ListAnimalAdapter extends RecyclerView.Adapter<ListAnimalAdapter.ViewHolder> {
    private ArrayList<Animal> listAnimal;
    private onItemClickCallBack onItemClickCallBack;

    public void setOnItemClickCallBack(ListAnimalAdapter.onItemClickCallBack onItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack;
    }

    public ListAnimalAdapter(ListAnimalAdapter.onItemClickCallBack onItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack;
    }

    public ListAnimalAdapter(ArrayList<Animal> listAnimal) {
        this.listAnimal = listAnimal;
    }

    @NonNull
    @Override
    public ListAnimalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_animal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListAnimalAdapter.ViewHolder holder, int position) {
        Animal animal = listAnimal.get(position);
        Glide.with(holder.itemView.getContext())
                .load(animal.getPhoto())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.imgPhoto);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallBack.onItemClicked(listAnimal.get(holder.getAdapterPosition()));
            }
        });
        holder.tvName.setText(animal.getName());
        holder.tvInfo.setText(animal.getInfo());

    }

    @Override
    public int getItemCount() {
        return listAnimal.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName;
        TextView tvInfo;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvInfo = itemView.findViewById(R.id.tv_item_info);
        }
    }

    public interface onItemClickCallBack {
        void onItemClicked(Animal data);
    }
}
