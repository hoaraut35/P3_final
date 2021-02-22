package com.openclassrooms.entrevoisins.events;

import android.util.Log;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Created by asrock on 04/02/21.
 */
public class DeleteFavoriteNeighbourEvent
{
    /**
     * Neighbour to delete
     */
    public Neighbour neighbour;

    public DeleteFavoriteNeighbourEvent(Neighbour neighbour)
    {
        Log.i("THOMAS","[EventBus] Suppression du voisin favoris " + neighbour.getName());
        this.neighbour = neighbour;
    }
}


