package com.openclassrooms.entrevoisins.service;

import android.support.v4.app.FragmentManagerNonConfig;
import android.util.Log;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest
{

    private NeighbourApiService service;

    @Before
    public void setup()
    {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess()
    {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }


    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    //TODO : Test 1 : Récupérer les favoris
    @Test
    public void getFavoriteNeighboursWithSuccess()
    {
        //First step : we can adjust the amount of neighbour to add for the test
        int nb_neighbourtoAdd = 1004;
        int nb_neighboursFav = 0;
        Neighbour neighbour;

        //Second step : we add neighbors
        for (int z=1; z<=nb_neighbourtoAdd; z++)
        {
            service.addFavoriteNeighbour(DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(new Random().nextInt(DummyNeighbourGenerator.DUMMY_NEIGHBOURS.size())));
        }

        nb_neighboursFav = service.getFavoritesNeighbours().size();

        assertEquals(nb_neighbourtoAdd, nb_neighboursFav );

    }

    //TODO : Test 2 : Supprimer favoris
    @Test
    public void deleteFavoriteNeighbourWithSuccess() {
        //First step : we copy a neighbour form the neighbour list
        Neighbour neighbourFavToDelete = service.getNeighbours().get(0);

        //Second step : we clean the favorite list
        service.getFavoritesNeighbours().clear();
        assertEquals(0, service.getFavoritesNeighbours().size());

        //Third step : we add the neighbour from first step to the favoritelsit
        service.addFavoriteNeighbour(neighbourFavToDelete);
        assertEquals(1, service.getFavoritesNeighbours().size());

        //Fourth step : we check if we have the same object in the list with the variable neighbourFavToDelete
        assertEquals(service.getFavoritesNeighbours().get(0), neighbourFavToDelete);

        //step : we delete the neighbour from fav list and check it
        service.removeFavoriteNeighbour(neighbourFavToDelete);
        assertEquals(0, service.getFavoritesNeighbours().size());

        assertFalse(service.getFavoritesNeighbours().contains(neighbourFavToDelete));

    }


    //TODO : Test 3 : Ajoute un favoris
    @Test
    public void addFavoriteNeighbourWithSuccess()
    {
        //First step : we copy a neighbour form the neighbour list
        Neighbour neighbourFavToAdd = service.getNeighbours().get(0);

        //Second step : we clean the favorite list
        service.getFavoritesNeighbours().clear();
        assertEquals(0, service.getFavoritesNeighbours().size());

        //Third step : we add the neighbour from first step to the favoritelsit
        service.addFavoriteNeighbour(neighbourFavToAdd);
        assertEquals(1, service.getFavoritesNeighbours().size());
    }







}
