package com.laureanray.codesimulatorandchecker.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.laureanray.codesimulatorandchecker.R;
import com.laureanray.codesimulatorandchecker.RootActivity;
import com.laureanray.codesimulatorandchecker.app.SharedPreferencesManager;

public class AccountFragment extends Fragment {

    private AccountViewModel notificationsViewModel;


    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.account_fragment, container, false);

        final Button logoutButton = root.findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RootActivity.class);
                SharedPreferencesManager.setIsLoggedInValue(getContext(), false);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return root;
    }

}