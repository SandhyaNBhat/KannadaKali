package com.spcreations.kannadakali;

import android.app.Activity;

import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<com.spcreations.kannadakali.Word> {

    private int mColorResourceId;
    /*
     * This is a custom custructor
     * Context is used to inflate the layout file
     * List is the data we want to populate in the lists
     */

    public WordAdapter(Activity context, ArrayList<com.spcreations.kannadakali.Word> words, int ColorResourceId) {
        super(context, 0, words);
        mColorResourceId = ColorResourceId;
    }


    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the Word Object position from the list
        com.spcreations.kannadakali.Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID default text view
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the default translation from the current Word object and
        // set this text on the default TextView
        defaultTextView.setText(currentWord.getDefaultTranslation());

        // Find the TextView in the list_item.xml layout with the ID Kannada text view
        TextView kannadaTextView = (TextView) listItemView.findViewById(R.id.kannada_text_view);
        // Get the kannada translation from the current Word object and
        // set this text on the kannada TextView
        kannadaTextView.setText(currentWord.getKannadaTranslation());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        // Get the image resource ID from the current Word object and
        // set the image to iconView

        if (currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getImageResourceId());
            imageView.setVisibility(convertView.VISIBLE);
        } else
        {
            imageView.setVisibility(convertView.GONE);
        }

        //set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        //Find the color that the resource id maps to
        int color = ContextCompat.getColor(getContext(),mColorResourceId);
        //set the background color of the text container view
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
