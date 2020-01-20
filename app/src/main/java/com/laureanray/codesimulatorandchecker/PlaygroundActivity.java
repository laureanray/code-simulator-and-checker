package com.laureanray.codesimulatorandchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.laureanray.codesimulatorandchecker.app.RetrofitClientInstance;
import com.laureanray.codesimulatorandchecker.data.model.CompileTask;
import com.laureanray.codesimulatorandchecker.data.services.JavacService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaygroundActivity extends AppCompatActivity {
    private JavacService javacService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground);

        javacService = RetrofitClientInstance.getRetrofitInstance().create(JavacService.class);


        final EditText source = findViewById(R.id.source_code);
        final Button runButton = findViewById(R.id.play_run_btn);
        final EditText result = findViewById(R.id.result);
        final TextView compileResult = findViewById(R.id.compile_result);

        runButton.setEnabled(true);

        TextWatcher textWatcher = new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(TextUtils.isEmpty(charSequence)){
                    runButton.setEnabled(false);
                } else {
                    runButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };


        source.addTextChangedListener(textWatcher);

        runButton.setOnClickListener(view -> {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Compiling");
            progressDialog.setMessage("Please wait");
            progressDialog.setCancelable(false);
            progressDialog.show();

            CompileTask compileTask = new CompileTask(source.getText().toString(), null, null, false, null);

            Call<CompileTask> compileTaskCall = javacService.compile(compileTask);

            compileTaskCall.enqueue(new Callback<CompileTask>() {
                @Override
                public void onResponse(Call<CompileTask> call, Response<CompileTask> response) {
                    if(response.raw().code() == 200){
                        // everything went well in the transmission
                        // check for null

                        if(response.body().getCompileResult()){
                            // success in compile
                            compileResult.setText("Compilation Success");

                            if(response.body().getResult() != null) {
                                result.setText(response.body().getResult());
                            }
                        } else {
                            // fail
                            compileResult.setText("Compilation Failed!");
                            result.setText(response.body().getCompileMessage());

                        }
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<CompileTask> call, Throwable t) {

                }
            });
        });




    }
}
