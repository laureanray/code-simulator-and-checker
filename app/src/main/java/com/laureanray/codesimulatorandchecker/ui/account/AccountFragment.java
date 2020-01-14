package com.laureanray.codesimulatorandchecker.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.laureanray.codesimulatorandchecker.R;
import com.laureanray.codesimulatorandchecker.app.SharedPreferencesManager;
import com.laureanray.codesimulatorandchecker.ui.login.LoginActivity;

public class AccountFragment extends Fragment {

    private AccountViewModel notificationsViewModel;


    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);

        final Button logoutButton = root.findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                SharedPreferencesManager.setIsLoggedInValue(getContext(), false);
                startActivity(intent);
                getActivity().finish();
            }
        });

//        final TextView textView = root.findViewById(R.id.accountFullName);
//        notificationsViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                textView.setText(s);
//            }
//        });
//        final TextView textView = root.findViewById(R.id.text_account);

//        notificationsViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        return root;
    }
}