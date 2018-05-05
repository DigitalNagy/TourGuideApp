package com.udacian.tourguideapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import model.Places;

/**
 * Created by Eslam Nagy on 03/04/2018.
 */

public class PlacesAdapter extends ArrayAdapter<Places> {

    private Typeface mCustomFont;
    private Typeface mCustomFontBold;
    private Context mContext;

    /**
     * Default Constructor
     * @param context
     * @param places
     */
    public PlacesAdapter (Activity context, ArrayList<Places> places) {
        super(context, 0, places);
        mContext = context;
    }

    /**
     * Prepares and returns listview
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Set custom typeface
        mCustomFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/opensans_regular.ttf");
        mCustomFontBold = Typeface.createFromAsset(mContext.getAssets(), "fonts/opensans_bold.ttf");

        final Places currentPlace = getItem(position);

      //  View listItemView = convertView;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_places, parent, false);

        }

        ImageView imagePlace = (ImageView) convertView.findViewById(R.id.place_image);
        int resId = mContext.getResources().getIdentifier(currentPlace.getPlaceImage(), "drawable", mContext.getPackageName());
        imagePlace.setImageResource(resId);

        TextView textPlace = (TextView) convertView.findViewById(R.id.text_place);
        textPlace.setTypeface(mCustomFontBold);
        textPlace.setText(currentPlace.getPlace());

        TextView textPlaceDesc = (TextView) convertView.findViewById(R.id.text_desc);
        textPlaceDesc.setTypeface(mCustomFont);
        textPlaceDesc.setText(currentPlace.getPlaceDesc());

        return convertView;
    }
}