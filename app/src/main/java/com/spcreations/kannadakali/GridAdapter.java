package com.spcreations.kannadakali;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class GridAdapter extends ArrayAdapter<Word> {
    private int mColorResourceId;

    /*
    * Included in app version 2
     */

    public GridAdapter(@NonNull Context context, ArrayList<Word> words,int ColorResourceId) {
        super(context, 0, words);
        mColorResourceId = ColorResourceId;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_items, parent, false);
        }
        Word currentGrid = getItem(position);
        TextView convType = listitemView.findViewById(R.id.idConvType);
        TextView convTypeK = listitemView.findViewById(R.id.idConvTypeK);
       // ImageView convImage = listitemView.findViewById(R.id.idConvIcon);
        convType.setText(currentGrid.getDefaultTranslation());
      //  convImage.setImageResource(currentGrid.getImageResourceId());
        convTypeK.setText(currentGrid.getKannadaTranslation());


        //set the theme color for the list item
        View gridContainer = listitemView.findViewById(R.id.gridContainer);

        //Find the color that the resource id maps to
        int color = ContextCompat.getColor(getContext(),mColorResourceId);
        //set the background color of the text container view
        gridContainer.setBackgroundColor(color);
        return listitemView;
    }

}
