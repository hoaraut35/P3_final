package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.OpenDetailNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder>  {

    private final List<Neighbour> mNeighbours;
    private String version;

    //TODO : modification du constructeur pour gérer les deux méthodes de suppression
    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items, String version)
    {
        this.mNeighbours = items;
        this.version = version;
     }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_neighbour, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        Neighbour neighbour = mNeighbours.get(position);

        holder.mNeighbourName.setText(neighbour.getName());

        Glide.with(holder.mNeighbourAvatar.getContext())
            .load(neighbour.getAvatarUrl())
            .apply(RequestOptions.circleCropTransform())
            .into(holder.mNeighbourAvatar);


        //TODO: méthode  avec event
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (version == "base")
                {
                    EventBus.getDefault().post(new DeleteNeighbourEvent(mNeighbours.get(position)));
                }
                else
                {
                    EventBus.getDefault().post(new DeleteFavoriteNeighbourEvent(mNeighbours.get(position)));
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new OpenDetailNeighbourEvent(mNeighbours.get(position)));
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return mNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;

        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;

        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        public ViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
