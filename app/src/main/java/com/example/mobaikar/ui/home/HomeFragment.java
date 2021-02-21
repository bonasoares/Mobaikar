package com.example.mobaikar.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.mobaikar.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    GridView gridView;

    String[] menuNames = {"News","About","Donate","Demands","History","Business"};
    int[] menuImages = {R.drawable.ic_menu_news, R.drawable.ic_menu_about, R.drawable.ic_menu_donate
            , R.drawable.ic_menu_demand,R.drawable.ic_menu_history, R.drawable.ic_menu_eibn};
    int[] menuLinks = {R.id.nav_news, R.id.nav_about, R.id.nav_donate
            , R.id.nav_demand,R.id.nav_history, R.id.nav_eibn};

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        /*
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        */

        gridView = root.findViewById(R.id.home_grid);
        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), menuNames[position].toString(), Toast.LENGTH_SHORT).show();

                View menuView = getActivity().findViewById(menuLinks[position]);
                menuView.callOnClick();
            }
        });
        return root;
    }

    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return menuImages.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.home_grid_item, null);
            TextView textView = view.findViewById(R.id.grid_item_text);
            ImageView imageView = view.findViewById(R.id.grid_item_image);
            textView.setText(menuNames[position]);
            imageView.setImageResource(menuImages[position]);
            return view;
        }
    }
}