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
import com.laureanray.codesimulatorandchecker.app.OkHttpClientInstance;
import com.laureanray.codesimulatorandchecker.app.RetrofitAuthClientInstance;
import com.laureanray.codesimulatorandchecker.app.RetrofitClientInstance;
import com.laureanray.codesimulatorandchecker.app.SharedPreferencesManager;
import com.laureanray.codesimulatorandchecker.app.Util;
import com.laureanray.codesimulatorandchecker.data.model.Student;
import com.laureanray.codesimulatorandchecker.data.model.Token;
import com.laureanray.codesimulatorandchecker.data.services.StudentService;
import com.laureanray.codesimulatorandchecker.data.services.TokenService;

import okhttp3.Request;
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

        loginButton = root.findViewById(R.id.register_btn);
        loginButton.setEnabled(false);

        username = root.findViewById(R.id.et_r_un);
        password = root.findViewById(R.id.et_r_cpassword);

        username.addTextChangedListener(loginTextWatcher);
        password.addTextChangedListener(loginTextWatcher);

        // Event listeners
        loginButton.setOnClickListener(this::onLoginClicked);
        return root;
    }

    private void onLoginClicked(View view) {
        // Hide keyboard
        Util.hideKeyboard(getActivity());

        // Clear errors if any
        username.setError(null);
        password.setError(null);

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

        Request request = call.request();
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Log.d("LOGIN_FRAGMENT", response.message());
                progressDialogHandler.removeCallbacks(progressRunnable);

                if (response.raw().code() == 200) {


                    // Set the login value
                    assert response.body() != null;
                    getStudentDetails(username.getText().toString(), response.body().getAccessToken());

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

    private void getStudentDetails(String username, String token) {
        StudentService studentService = RetrofitAuthClientInstance.getRetrofitInstance(OkHttpClientInstance.getOkHttpClientInstance(token))
                .create(StudentService.class);
        Call<Student> student = studentService.getUserByUsername(username);

        student.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if(response.raw().code() == 200){
                    Student student = response.body();
                    SharedPreferencesManager.setIsLoggedInValue(getContext(), true);
                    SharedPreferencesManager.setTokenValue(getContext(), token);
                    SharedPreferencesManager.setStudentValue(getContext(), student);
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    Log.d("Status", String.valueOf(response.raw().code()));
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                Log.d("LOGIN_FRAGMENT", call.toString());
                t.printStackTrace();
            }
        });

    }
}
