package com.laureanray.codesimulatorandchecker.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.laureanray.codesimulatorandchecker.MainActivity;
import com.laureanray.codesimulatorandchecker.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.home_fragment, container, false);


        final TextView firstname = root.findViewById(R.id.home_firstname);
        if (((MainActivity) getActivity()).getStudent() != null) {
            firstname.setText(((MainActivity) getActivity()).getStudent().getFirstName());
        }

        return root;
    }


}