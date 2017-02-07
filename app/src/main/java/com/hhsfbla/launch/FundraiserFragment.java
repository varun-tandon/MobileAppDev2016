package com.hhsfbla.launch;

import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Varun on 1/25/2017.
 */

public class FundraiserFragment extends Fragment {

    private View fundraiserView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fundraiserView = inflater.inflate(R.layout.fragment_fundraiser, container, false);

        Bundle data = getArguments();
        ImageView fundraiserImg = (ImageView) fundraiserView.findViewById(R.id.fundraiser_image);
        fundraiserImg.setImageBitmap((Bitmap) data.getParcelable("bitmap"));
        setTextForTextView(R.id.campaign_title_textview, data.getString("purpose"));
        setTextForTextView(R.id.nonprofit_org_name_textview, data.getString("orgname"));
        setTextForTextView(R.id.homepage_campaign_description, data.getString("description"));
        setTextForTextView(R.id.progress_text, data.getString("progressText"));

        LinearLayout buy = (LinearLayout) fundraiserView.findViewById(R.id.buy_button);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new BrowseItemsFragment()).commit();
            }
        });

        return fundraiserView;
    }

    public void setTextForTextView(int id, String text) {
        View v = fundraiserView.findViewById(id);
        if (v instanceof TextView) {
            ((TextView) v).setText(text);
        }
    }
}
