package com.example.mobaikar.ui.message;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobaikar.MainActivity;
import com.example.mobaikar.R;
import com.example.mobaikar.ui.message.MessageViewModel;

public class MessageFragment extends Fragment {

    private MessageViewModel mViewModel;

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View retView =  inflater.inflate(R.layout.fragment_message, container, false);

        Button startBtn = (Button) retView.findViewById(R.id.buttonSendMessage);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
            }

            private void sendEmail() {
                Log.i("Send email", "");
                String[] TO = {"mgpmobai@gmail.com"};
                String name = ((EditText) retView.findViewById(R.id.editTextName)).getText().toString().trim();
                String phone = ((EditText) retView.findViewById(R.id.editTextPhone)).getText().toString().trim();
                String subject = ((EditText) retView.findViewById(R.id.editTextSubject)).getText().toString().trim();
                String message = ((EditText) retView.findViewById(R.id.editTextMessage)).getText().toString().trim();
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject + " : " + name + " : " + phone);
                emailIntent.putExtra(Intent.EXTRA_TEXT, message);

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    Log.i("Finished sending email", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return retView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MessageViewModel.class);
        // TODO: Use the ViewModel
    }

}