package com.laureanray.codesimulatorandchecker.ui.root;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.laureanray.codesimulatorandchecker.R;
import com.laureanray.codesimulatorandchecker.RootActivity;
import com.laureanray.codesimulatorandchecker.app.OkHttpClientInstance;
import com.laureanray.codesimulatorandchecker.app.RetrofitAuthClientInstance;
import com.laureanray.codesimulatorandchecker.app.SharedPreferencesManager;
import com.laureanray.codesimulatorandchecker.data.model.Student;
import com.laureanray.codesimulatorandchecker.data.services.StudentService;
import com.laureanray.codesimulatorandchecker.data.services.StudentServiceInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    // UI Elements
    private Button registerButton;
    private EditText firstName;
    private EditText lastName;
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private StudentService studentService;

    public RegisterFragment() {
        // Initialize service
    }

    public static RootFragment newInstance() {
        return new RootFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        studentService = StudentServiceInstance.getStudentService(getContext());


        View view = inflater.inflate(R.layout.register_fragment, container, false);

        registerButton = view.findViewById(R.id.register_btn);
        firstName = view.findViewById(R.id.et_fn);
        lastName = view.findViewById(R.id.et_ln);
        username = view.findViewById(R.id.et_r_un);
        email = view.findViewById(R.id.et_email);
        password = view.findViewById(R.id.et_r_password);
        confirmPassword = view.findViewById(R.id.et_r_cpassword);



        registerButton.setOnClickListener(this::registerClicked);

        return view;
    }

    private void registerClicked(View view){

        Student student = new Student();
        student.setFirstName(firstName.getText().toString());
        student.setLastName(lastName.getText().toString());
        student.setEmail(email.getText().toString());
        student.setUsername(username.getText().toString());
        student.setPassword(password.getText().toString());


        Call<Student> call = studentService.register(student);
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if(response.raw().code() == 201) {
                    ((RootActivity) getActivity()).replaceFragment(SubmittedFragment.class);
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {

            }
        });

    }



}
