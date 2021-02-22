package com.openclassrooms.entrevoisins.ui.neighbour_list;

/**
 * Using Gson library for data
 * https://github.com/google/gson/blob/master/UserGuide.md#TOC-Overview
 */

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailNeighbourActivity extends AppCompatActivity
{

    @BindView(R.id.toolbar_view)
    Toolbar mToolbar;

    @BindView(R.id.v_image)
    ImageView mAvatar;

    @BindView(R.id.v_name_general)
    TextView  mNameNeighbour;

    @BindView(R.id.v_name_secondary)
    TextView  mNameNeighbour2;

    @BindView(R.id.v_network_name)
    TextView  mNameNeighbour3;

    @BindView(R.id.address)
    TextView mAdresse;

    @BindView(R.id.myphone_view)
    TextView mPhone;

    @BindView(R.id.myText_view)
    TextView mAbout;

    @BindView(R.id.float_button)
    FloatingActionButton mFloatBtn;

    private NeighbourApiService mApiService = DI.getNeighbourApiService();
    private Neighbour mFavNeighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //TODO: ajout transition pour test
        overridePendingTransition(R.xml.fadein, R.xml.fadeout);

        setContentView(R.layout.activity_detail_neighbour_with_coordinator_layout_2);
        ButterKnife.bind(this);

        setupNeighbourViewsFromFirstActivity();

    }

    private void setupUpButton()
    {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setupFloatButton()
    {
        //Check if favorite neighbour is present in favorite list and adjust the fab button
        if (mApiService.getFavoritesNeighbours().contains(mFavNeighbour))
        {
            mFloatBtn.setImageResource(R.drawable.ic_star_yellow);
        }
        else
        {
            mFloatBtn.setImageResource(R.drawable.ic_white_star);
        }
    }

    private void setupNeighbourViewsFromFirstActivity()
    {
        Intent myIntent = getIntent();

        //TODO: GSON parcelablke serializable à voir
        Gson gson = new Gson();
        String new_neighbour = getIntent().getStringExtra("neighbour_object");
        //attribution des valeurs à l'objet
        mFavNeighbour = gson.fromJson(new_neighbour, Neighbour.class);

        mNameNeighbour.setText(mFavNeighbour.getName());
        mNameNeighbour2.setText(mFavNeighbour.getName());
        mNameNeighbour3.setText("www.facebook.fr/" + mFavNeighbour.getName().toLowerCase());

        Glide.with(this)
                .load(mFavNeighbour.getAvatarUrl().toString())
                .apply(RequestOptions.centerCropTransform())
                .into(mAvatar);

        mAdresse.setText(mFavNeighbour.getAddress());
        mPhone.setText(mFavNeighbour.getPhoneNumber());
        mAbout.setText(mFavNeighbour.getAboutMe());

        setupUpButton();
        setupFloatButton();

    }

    @OnClick(R.id.float_button)
    void updateNeighbour()
    {
        if (mApiService.getFavoritesNeighbours().contains(mFavNeighbour))
        {
            Log.i("THOMAS", "utilisateur deja dans la liste donc on le retire "+ mFavNeighbour.getName());
            mApiService.removeFavoriteNeighbour(mFavNeighbour);
        }
        else
        {
            Log.i("THOMAS", "utilisateur non présent dans la liste donc on l'ajoute "+ mFavNeighbour.getName());
            mApiService.addFavoriteNeighbour(mFavNeighbour);

        }

        Log.i("THOMAS", "Liste des favoris actuellement à  :" + Integer.toString(mApiService.getFavoritesNeighbours().size()));

        setupFloatButton();
    }

}