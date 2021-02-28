package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

//TODO : implémenter de l'interface listener
public class NeighbourFavoritesFragment extends Fragment implements MyNeighbourRecyclerViewAdapter.Listener
{
    private List<Neighbour> mFavoritesNeighbours;
    private NeighbourApiService mApiService;
    private RecyclerView mRecyclerView;

    public NeighbourFavoritesFragment() {         // Required empty public constructor
    }

    public static NeighbourFavoritesFragment newInstance()
    {
        NeighbourFavoritesFragment fragment = new NeighbourFavoritesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_neighbour_favorites, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mFavoritesNeighbours  = mApiService.getFavoritesNeighbours();
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mFavoritesNeighbours,this ));

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    /**
     * Init the List of neighbours
     */
    private void initList()
    {
        mFavoritesNeighbours  = mApiService.getFavoritesNeighbours();
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mFavoritesNeighbours,this));
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
    */
    //TODO: eventbus sur suppression d'un favoris
    @Subscribe
    public void onDeleteNeighbour(DeleteFavoriteNeighbourEvent event)
    {
        mApiService.removeFavoriteNeighbour(event.neighbour);
        initList();
    }

    //TODO: callback du 18/02/2021
    @Override
    public void onClickDelete(int position, Neighbour voisinToDel)
    {
        Log.i("THOMAS", "[NeighbourFavoritesFragment] [callback] Clic sur un voisin à la position " + position);
        mApiService.removeFavoriteNeighbour(voisinToDel);
        initList();
        //EventBus.getDefault().post(new DeleteFavoriteNeighbourEvent(voisinToDel));
    }

    @Override
    public void onClickDetail(int position, Neighbour voisinToOpen) {
        //ne rien faire car cette méthode concerne uniquement le voisin non favoris
    }
}