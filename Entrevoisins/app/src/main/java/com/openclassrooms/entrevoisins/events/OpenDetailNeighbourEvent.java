package com.openclassrooms.entrevoisins.events;

import android.util.Log;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class OpenDetailNeighbourEvent {

    public Neighbour neighbour;

    public OpenDetailNeighbourEvent(Neighbour neighbour)
    {
        Log.i("THOMAS","[EventBus] Ouverture détail du voisin " + neighbour.getName());
        this.neighbour = neighbour;


    }
}


