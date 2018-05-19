package com.udacian.tourguideapp;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import java.util.List;
import helper.DatabaseHelper;
import model.City;
/**
 * Created by Eslam Nagy on 04/04/2018.
 */
public class CityFragment extends Fragment {
    private static final String LOG_TAG = "CityFragment";
    private View view;
    private String mSelectedCity;
    private Typeface mCustomFont;
    // Variables for City
    private String mCountry;
    private String mLanguage;
    private String mAirport;
    private String mTransport;
    private String mAboutCity;
    private int mCityImage;
    private String mCityTimeZone;
    // UI identifiers
    private ImageView mImageViewCity;
    private TextView mTextViewCountry;
    private TextView mTextViewLanguage;
    private TextView mTextViewAirport;
    private TextView mTextViewTransport;
    private TextClock mTextClockTime;
    private TextView mTextViewAbout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get Intent Extras
        if (getActivity().getIntent().getExtras() != null) {
            Bundle bundle = getActivity().getIntent().getExtras();
            mSelectedCity = bundle.getString("city");
        }
        // Extract City Details from Database
        extractCityDetails();
        // Set custom typeface
        mCustomFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/opensans_regular.ttf");
        // Inflate view object
        view = inflater.inflate(R.layout.fragment_city, container, false);
        // Initialize UI objects
        mImageViewCity = (ImageView) view.findViewById(R.id.image_city);
        mTextViewCountry = (TextView) view.findViewById(R.id.text_country);
        mTextViewLanguage = (TextView) view.findViewById(R.id.text_language);
        mTextViewAirport = (TextView) view.findViewById(R.id.text_airport);
        mTextViewTransport = (TextView) view.findViewById(R.id.text_transport);
        mTextClockTime = (TextClock) view.findViewById(R.id.textclock_localtime);
        mTextViewAbout = (TextView) view.findViewById(R.id.text_panel_about);
        // Display city data
        displayCityRecord();
        return view;
    }
    /**
     * This method retrieves data from City table for specified city
     */
    public void extractCityDetails() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity().getApplicationContext());
        List<City> cityRecord = databaseHelper.readCityRecord(mSelectedCity);
        for (City ct : cityRecord) {
            mCountry = ct.getCountry().trim();
            mLanguage = ct.getLanguage().trim();
            mAirport = ct.getAirport().trim();
            mTransport = ct.getTransport().trim();
            mAboutCity = ct.getDescription().trim();
            mCityImage = ct.getCityImage();
            mCityTimeZone = ct.getCityTimeZone().trim();
        }
    }
    /**
     * Calls methods for displaying individual view on City screen
     */
    public void displayCityRecord() {
        setCustomTypeface();
        displayCityImage();
        displayCityFacts();
        displayCityDesc();
        displayLocalTime();
    }
    /**
     * This method sets custom font for all views
     */
    public void setCustomTypeface() {
        mTextViewCountry.setTypeface(mCustomFont);
        mTextViewLanguage.setTypeface(mCustomFont);
        mTextViewAirport.setTypeface(mCustomFont);
        mTextViewTransport.setTypeface(mCustomFont);
        mTextClockTime.setTypeface(mCustomFont);
        mTextViewAbout.setTypeface(mCustomFont);
    }
    /**
     * This method displays photo of the city
     */
    public void displayCityImage() {
        mImageViewCity.setImageResource(mCityImage);
    }
    /**
     * This method displays the key facts of the selected city
     */
    public void displayCityFacts() {
        mTextViewCountry.setText(mCountry);
        mTextViewLanguage.setText(mLanguage);
        mTextViewAirport.setText(mAirport);
        mTextViewTransport.setText(mTransport);
    }
    /**
     * This method displays short description of the city
     */
    public void displayCityDesc() {
        mTextViewAbout.setText(mAboutCity);
    }
    /**
     * This method displays local time at the selected city
     */
    public void displayLocalTime() {
        mTextClockTime.setTimeZone(mCityTimeZone);
    }
}