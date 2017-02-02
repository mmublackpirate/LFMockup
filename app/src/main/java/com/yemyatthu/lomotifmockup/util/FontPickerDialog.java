package com.yemyatthu.lomotifmockup.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yemyatthu.lomotifmockup.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;

public class FontPickerDialog extends DialogFragment {
    // Use this instance of the interface to deliver action events
    FontPickerDialogListener mListener;
    // Keeps the font file paths and names in separate arrays
    private List<String> mFontPaths; // list of file paths for the available fonts
    private List<String> mFontNames; // font names of the available fonts. These indices match up with mFontPaths
    private Context mContext; // The calling activities context.
    private String mSelectedFont; // The font that was selected

    // Override the Fragment.onAttach() method to instantiate the
    // FontPickerDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the
            // host
            mListener = (FontPickerDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement FontPickerDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // get context
        mContext = getActivity();

        // Let FontManager find available fonts
        HashMap<String, String> fonts = FontManager.enumerateFonts();
        mFontPaths = new ArrayList<String>();
        mFontNames = new ArrayList<String>();

        // add fonts to List
        for (String path : fonts.keySet()) {
            mFontPaths.add(path);
            mFontNames.add(fonts.get(path));
        }

        // Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View contView = inflater.inflate(R.layout.font_picker_dialog, null);
        RecyclerView recyclerView = ButterKnife.findById(contView, R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FontAdapter adapter = new FontAdapter(mContext);
        recyclerView.setAdapter(adapter);
        builder.setView(contView);
        builder.setTitle("Select A Font");

        // Add the buttons
        builder.setNegativeButton("Cancel",
                (view, id) -> {
                    // don't have to do anything on cancel
                });

        // Get the AlertDialog from create()
        AlertDialog dialog = builder.create();

        return dialog;
    }

    /**
     * Callback method that that is called once a font has been
     * selected and the fontpickerdialog closes.
     *
     * @return The pathname of the font that was selected
     */
    public String getSelectedFont() {
        return mSelectedFont;
    }


    // create callback method to bass back the selected font
    public interface FontPickerDialogListener {
        /**
         * This method is called when a font is selected
         * in the FontPickerDialog
         *
         * @param dialog The dialog used to pick the font. Use dialog.getSelectedFont() to access the pathname of the chosen font
         */
        public void onFontSelected(FontPickerDialog dialog);
    }

    /**
     * Create an adapter to show the fonts in the dialog.
     * Each font will be a text view with the text being the
     * font name, written in the style of the font
     */
    private class FontAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private Context mContext;

        public FontAdapter(Context c) {
            mContext = c;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_font_item, parent, false);
            return new FontHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof FontHolder)
                ((FontHolder) holder).bind(position);
        }

        @Override
        public long getItemId(int position) {
            // We use the position as ID
            return position;
        }

        @Override
        public int getItemCount() {
            return mFontNames.size();
        }
    }

    class FontHolder extends RecyclerView.ViewHolder {

        public FontHolder(View itemView) {
            super(itemView);
        }

        public void bind(int position) {
            Typeface tface = Typeface.createFromFile(mFontPaths.get(position));
            ((TextView) itemView).setTypeface(tface);
            ((TextView) itemView).setText(mFontNames.get(position));
            itemView.setOnClickListener(v -> {
                int magicNumber = position;
                mSelectedFont = mFontPaths.get(magicNumber);
                mListener.onFontSelected(FontPickerDialog.this);
            });
        }
    }
}