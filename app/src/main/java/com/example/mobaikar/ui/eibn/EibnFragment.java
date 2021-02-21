package com.example.mobaikar.ui.eibn;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobaikar.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class EibnFragment extends Fragment  implements AdapterView.OnItemClickListener {

    private EibnViewModel mViewModel;
    ListView listView;
    SimpleAdapter adapter;
    ProgressDialog loading;
    EditText searchText;

    public static EibnFragment newInstance() {
        return new EibnFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eibn, container, false);
        listView = (ListView) view.findViewById(R.id.item_listing);
        listView.setOnItemClickListener(this);
        searchText = (EditText) view.findViewById(R.id.searchData);
        getItems();

        /*
        searchText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                searchText.setTranslationY(+150f);
                return false;
            }
        });

        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager in = (InputMethodManager) getContext().getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(searchText.getApplicationWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
                searchText.setTranslationY(0f);
                return false;
            }
        });*/
        return view;
    }

    private void getItems() {
        loading = ProgressDialog.show(getContext(), "Loading Items", "Please wait");

        StringRequest stringRequest = new StringRequest(Request.Method.GET
                , "https://script.google.com/macros/s/AKfycbwqWuQ0WL6RY5Yvm-bX8HwQ8PywcNiwxLOEc5-_LLBV1KviQo2fqnxL/exec?action=getAllItems"
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseItems(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        int socketTimeout = 50000; //milliseconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeout, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequest);
    }

    private void parseItems(String response) {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        try {
            JSONObject jobj = new JSONObject(response);
            JSONArray jarr = jobj.getJSONArray("data");

            for(int i = 0; i <jarr.length(); i++) {
                JSONObject jo = jarr.getJSONObject(i);

                HashMap<String,String> item  = new HashMap<>();
                item.put("Date", jo.getString("Date"));
                item.put("Business Name", jo.getString("Business Name"));
                item.put("Owner Name", jo.getString("Owner Name"));
                item.put("Business Details", jo.getString("Business Details"));
                item.put("Primary Number", jo.getString("Primary Number"));
                item.put("Secondary Number", jo.getString("Secondary Number"));
                item.put("Email", jo.getString("Email"));
                item.put("Business Type", jo.getString("Business Type"));
                item.put("Address Line 1", jo.getString("Address Line 1"));
                item.put("Address Line 2", jo.getString("Address Line 2"));
                item.put("Landmark", jo.getString("Landmark"));
                item.put("Area", jo.getString("Area"));
                item.put("City", jo.getString("City"));
                item.put("Pincode", jo.getString("Pincode"));
                item.put("State", jo.getString("State"));
                item.put("Country", jo.getString("Country"));
                item.put("Latitude", jo.getString("Latitude"));
                item.put("Longitude", jo.getString("Longitude"));
                item.put("Website", jo.getString("Website"));
                item.put("Facebook", jo.getString("Facebook"));
                item.put("Youtube", jo.getString("Youtube"));
                item.put("Instagram", jo.getString("Instagram"));
                list.add(item);
            }
        } catch(JSONException e) {
            e.printStackTrace();
        }

        adapter = new SimpleAdapter(getContext(), list, R.layout.eibn_list_item_row
                , new String[] {"Business Name", "City",
                "Date",
                "Owner Name",
                "Business Details",
                "Primary Number",
                "Secondary Number",
                "Email",
                "Business Type",
                "Address Line 1",
                "Address Line 2",
                "Landmark",
                "Area",
                "Pincode",
                "State",
                "Country",
                "Latitude",
                "Longitude",
                "Website",
                "Facebook",
                "Youtube",
                "Instagram"}, new int[] {R.id.textViewName, R.id.textViewCity});
        listView.setAdapter(adapter);
        loading.dismiss();

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EibnFragment.this.adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EibnViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String, String> map = (HashMap) parent.getItemAtPosition(position);

        //Intent intent = new Intent(getActivity(), EibnItemDetails.class);
        Bundle bundle = new Bundle();
        bundle.putString("Date", map.get("Date").toString());
        bundle.putString("Business Name", map.get("Business Name").toString());
        bundle.putString("Owner Name", map.get("Owner Name").toString());
        bundle.putString("Business Details", map.get("Business Details").toString());
        bundle.putString("Primary Number", map.get("Primary Number").toString());
        bundle.putString("Secondary Number", map.get("Secondary Number").toString());
        bundle.putString("Email", map.get("Email").toString());
        bundle.putString("Business Type", map.get("Business Type").toString());
        bundle.putString("Address Line 1", map.get("Address Line 1").toString());
        bundle.putString("Address Line 2", map.get("Address Line 2").toString());
        bundle.putString("Landmark", map.get("Landmark").toString());
        bundle.putString("Area", map.get("Area").toString());
        bundle.putString("City", map.get("City").toString());
        bundle.putString("Pincode", map.get("Pincode").toString());
        bundle.putString("State", map.get("State").toString());
        bundle.putString("Country", map.get("Country").toString());
        bundle.putString("Latitude", map.get("Latitude").toString());
        bundle.putString("Longitude", map.get("Longitude").toString());
        bundle.putString("Website", map.get("Website").toString());
        bundle.putString("Facebook", map.get("Facebook").toString());
        bundle.putString("Youtube", map.get("Youtube").toString());
        bundle.putString("Instagram", map.get("Instagram").toString());
        //startActivity(intent);

        /*
        FragmentTransaction fTran = getFragmentManager().beginTransaction();
        EibnItemDetails eibn = EibnItemDetails.newInstance();
        eibn.setArguments(bundle);
        fTran.replace(R.id.nav_host_fragment, eibn).addToBackStack(null).commit();
        */
        AlertDialog detDialog;
        AlertDialog.Builder detDialogBuilder = new AlertDialog.Builder(getContext());
        final View dView = getLayoutInflater().inflate(R.layout.eibn_item_details, null);
        ((TextView) dView.findViewById(R.id.detail_id)).setText(map.get("Business Name").toString());
        ((TextView) dView.findViewById(R.id.detail_name)).setText(map.get("Owner Name").toString());
        ((TextView) dView.findViewById(R.id.detail_city)).setText(map.get("City").toString());
        detDialogBuilder.setView(dView);
        detDialog = detDialogBuilder.create();
        detDialog.show();

        ((Button) dView.findViewById(R.id.btnClose)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detDialog.dismiss();
            }
        });

        //dialog.show(getActivity().getSupportFragmentManager(), "Business Detailed Info");
    }

}