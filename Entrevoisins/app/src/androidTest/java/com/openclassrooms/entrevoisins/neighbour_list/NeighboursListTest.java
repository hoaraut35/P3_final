
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private ListNeighbourActivity mActivity;
    private int  countFavItems;
    private NeighbourApiService mApiServiceForTest = DI.getNeighbourApiService();
    private List<Neighbour> mNeighbourTestList = mApiServiceForTest.getNeighbours();
    private List<Neighbour> mNeighbourFavoriteTestList = mApiServiceForTest.getFavoritesNeighbours();

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule = new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    //TODO: Test si liste vide
    @Test
    public void myNeighboursList_shouldNotBeEmpty()
    {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem()
    {

        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }

    /**
     * Test 1 : Check if the new activity is well opened after a click on item list
     */
    //TODO : Test 1 : ouverture activité détail suite clic sur la liste voisin
    @Test
    public void openDetailAfterOnClickOnNeighbourItem()
    {
        //Fist step : we click on the first element of recyclerview
        onView(ViewMatchers.withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        //Second step : we check is v_image (avatar imageview) is visible at screen
        onView(withId(R.id.v_image)).check(matches(isDisplayed()));
    }

    /**
     * Test 2 : Check if the textview is up dated with the name of neighbour item
     */
    //TODO : Test 2 : vérifier le nom du voisin sur l'activité détail
    @Test
    public void myNeighboursList_checkNeighbourName_updateWithSuccess()
    {
        //First step : we click on the first item list from recyclerview to pen the detail activity Caroline in this case
        onView(ViewMatchers.withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        //Second step : we check is the v_name_general is matching with Caroline
        onView(withId(R.id.v_name_general)).check(matches(withText("Caroline")));
    }


    /**
     * Test3 : Vérifier qu'à la suppression la liste favoris deremente de 1
     */
    //TODO : Test 3 : suppression favoris avec vérification liste - 1
    @Test
    public void myNeighboursFavoritesList_deleteAction_shouldRemoveItem()
    {

        //Fist step : we add 2 neighbors
        int i;

        for (i =0 ; i<2 ; i++)
        {
            onView(ViewMatchers.withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(i,click()));
            onView(withId(R.id.float_button)).perform(click());
            onView(withId(R.id.toolbar_view)).perform(click());

        }

        //Second step : we swipe to the favorites list
        onView(withId(R.id.container)).perform(swipeLeft());

        //Third Step : clic on delete favorite
        onView(ViewMatchers.withId(R.id.list_favorites_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));

        // countFavItems  = mNeighbourFavoriteTestList.size();

        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_favorites_neighbours)).check(withItemCount(i-1));


    }

    /**
     * Test 4 : Check is favorite tab show only favorites neighbours tagged
     */
    //TODO: Test 4 : Vérifier que l'onglet Favoris n'affiche que les voisins marqués comme favoris
    @Test
    public void checkIfFavoriteListDisplayOnlyFavoriteNeighbour()
    {

        mNeighbourFavoriteTestList.clear();

        //Fist step : we add some neighbors
        int i;

        for (i =0 ; i<=2 ; i++)
        {
            onView(ViewMatchers.withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(i,click()));
            onView(withId(R.id.float_button)).perform(click());
            onView(withId(R.id.toolbar_view)).perform(click());
            //taille actuelle de la liste
        //    countFavItems += 1;

        }


        //Second step : we swipe to the favorites list
        onView(withId(R.id.container)).perform(swipeLeft());

        //Third step : we check if we have five neighbors in the favorite list
       // onView(withId(R.id.container)).check(withItemCount(5));
        onView(ViewMatchers.withId(R.id.list_favorites_neighbours)).check(withItemCount(3));
    }



}