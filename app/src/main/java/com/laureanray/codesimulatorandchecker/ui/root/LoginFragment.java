package com.laureanray.codesimulatorandchecker.ui.root;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.laureanray.codesimulatorandchecker.MainActivity;
import com.laureanray.codesimulatorandchecker.R;
import com.laureanray.codesimulatorandchecker.app.RetrofitClientInstance;
import com.laureanray.codesimulatorandchecker.app.SharedPreferencesManager;
import com.laureanray.codesimulatorandchecker.app.Util;
import com.laureanray.codesimulatorandchecker.data.model.Login;
import com.laureanray.codesimulatorandchecker.data.model.Student;
import com.laureanray.codesimulatorandchecker.data.model.Token;
import com.laureanray.codesimulatorandchecker.data.services.StudentService;
import com.laureanray.codesimulatorandchecker.data.services.TokenService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private TokenService tokenService;
    private TextWatcher loginTextWatcher;
    private Button loginButton;
    private EditText username;
    private EditText password;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        tokenService = RetrofitClientInstance.getRetrofitInstance().create(TokenService.class);

        loginTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(TextUtils.isEmpty(username.getText()) || TextUtils.isEmpty(password.getText())){
                    // Disable button
                    loginButton.setEnabled(false);
                } else {
                    loginButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        };

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.login_fragment, container, false);

        loginButton = root.findViewById(R.id.login_btn);
        loginButton.setEnabled(false);

        username = root.findViewById(R.id.et_r_un);
        password = root.findViewById(R.id.et_password);

        username.addTextChangedListener(loginTextWatcher);
        password.addTextChangedListener(loginTextWatcher);

        // Event listeners
        loginButton.setOnClickListener(this::onLoginClicked);
        return root;
    }

    private void onLoginClicked(View view) {
        // Hide keyboard

        Util.hideKeyboard(getActivity());


        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Logging you in...");
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Handler progressDialogHandler = new Handler();

        Runnable progressRunnable = new Runnable() {
            @Override
            public void run() {

                Snackbar.make(view, R.string.no_internet, Snackbar.LENGTH_LONG).show();
                progressDialog.cancel();
            }
        };


        progressDialogHandler.postDelayed(progressRunnable, 3000);

        Call<Token> call = tokenService.getToken(username.getText().toString(),
                                                 password.getText().toString(),
                                                 "password");

        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Log.d("LOGIN_FRAGMENT", response.message());
                progressDialogHandler.removeCallbacks(progressRunnable);

                if (response.raw().code() == 200) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);


                    // Set the login value
                    SharedPreferencesManager.setIsLoggedInValue(getContext(), true);
                    SharedPreferencesManager.setTokenValue(getContext(), response.body().getAccessToken());
                    getActivity().finish();


                } else if (response.raw().code() == 400) {
                    Snackbar.make(view, R.string.invalid_credentials, Snackbar.LENGTH_LONG).show();
                    password.setError("Incorrect password");
                } else {
                    Snackbar.make(view, R.string.invalid_credentials, Snackbar.LENGTH_LONG).show();
                    username.setError("User doesn't exist");
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Log.d("LOGIN_FRAGMENT", call.toString());
                t.printStackTrace();
            }
        });
    }
}
