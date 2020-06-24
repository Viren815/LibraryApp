package com.example.library;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AccountFragment extends Fragment implements View.OnClickListener {
    @Nullable
    // @Override
    private TextView textViewUsername, textViewUserEmail;
    private Button buttonLogout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        textViewUsername = (TextView) view.findViewById(R.id.textViewUsername);
        textViewUserEmail = (TextView) view.findViewById(R.id.textViewUseremail);

        textViewUsername.setText(SharedPrefManager.getInstance(getContext()).getUsername());
        textViewUserEmail.setText(SharedPrefManager.getInstance(getContext()).getUserEmail());


        buttonLogout = (Button) view.findViewById(R.id.buttonLogout);
      buttonLogout.setOnClickListener(this);


        return view;
    }
    public void onClick(View v){
        SharedPrefManager.getInstance(getActivity()).logout();
        mainIntent();
    }

    private void mainIntent() {
        Intent main = new Intent(getContext(),LoginActivity.class);
        startActivity(main);
    }
}
