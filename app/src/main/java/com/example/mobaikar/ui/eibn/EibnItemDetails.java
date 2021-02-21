package com.example.mobaikar.ui.eibn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mobaikar.R;

public class EibnItemDetails extends Fragment {

    TextView textId, textName, textCity;
    String strId, strName, strCity;


    public static EibnItemDetails newInstance() {
        return new EibnItemDetails();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.eibn_item_details, container, false);
        Bundle bundle = this.getArguments();
        strId = bundle.getString("Business Name").toString();
        strName = bundle.getString("Owner Name").toString();
        strCity = bundle.getString("City").toString();

        textId = (TextView) view.findViewById(R.id.detail_id);
        textName = (TextView) view.findViewById(R.id.detail_name);
        textCity = (TextView) view.findViewById(R.id.detail_city);

        textId.setText(strId);
        textName.setText(strName);
        textCity.setText(strCity);
        return view;
    }
}
