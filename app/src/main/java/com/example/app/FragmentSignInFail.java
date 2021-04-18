package com.example.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class FragmentSignInFail extends Fragment implements View.OnClickListener {
    private Button sign_in_fail_back;


    public FragmentSignInFail() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_fail, container, false);
    }

    @Override

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        sign_in_fail_back = (Button) view.findViewById(R.id.sign_in_fail_back);
        sign_in_fail_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        getFragmentManager().popBackStackImmediate();
    }
}