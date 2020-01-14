package com.laureanray.codesimulatorandchecker.ui.root;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.laureanray.codesimulatorandchecker.R;
import com.laureanray.codesimulatorandchecker.RootActivity;

public class RootFragment extends Fragment {

    private RootViewModel mViewModel;

    public static RootFragment newInstance() {
        return new RootFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.root_fragment, container, false);

        final Button registerBtn = root.findViewById(R.id.root_register_btn);
        final Button loginBtn = root.findViewById(R.id.root_login_btn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("RootFrag", "click");
                ((RootActivity) getActivity()).replaceFragment(RegisterFragment.class);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RootActivity) getActivity()).replaceFragment(LoginFragment.class);
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RootViewModel.class);
        // TODO: Use the ViewModel
    }

}
