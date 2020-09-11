package com.frank.gadsleaderboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class SubmitProject extends AppCompatActivity {
    private Toolbar mToolbar;
    private Button mSubmitBtn;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etEmail;
    private EditText etGithubLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_project);

        mToolbar = findViewById(R.id.submit_activity_toolbar);
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        mSubmitBtn = findViewById(R.id.submit_project_btn);

        etFirstName = findViewById(R.id.txt_first_name);
        etLastName = findViewById(R.id.txt_last_name);
        etEmail = findViewById(R.id.txt_email);
        etGithubLink = findViewById(R.id.txt_github_link);

        confirmSubmitProject();

    }

    private void confirmSubmitProject() {

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWarningDialog();

            }
        });

    }

    private void showWarningDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(SubmitProject.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(SubmitProject.this).inflate(
                R.layout.layout_warning_dialog,
                (ConstraintLayout) findViewById(R.id.warning_dialog_container)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.txt_error)).setText(getResources().getString(R.string.are_you_sure));
        ((TextView) view.findViewById(R.id.txt_question_mark)).setText(getResources().getString(R.string.question_mark));
        ((Button) view.findViewById(R.id.accept_btn)).setText(getResources().getString(R.string.yes));
        ((ImageView) view.findViewById(R.id.cancel_img)).setImageResource(R.drawable.ic_baseline_cancel_24);


        final AlertDialog alertDialog = builder.create();
        view.findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

            }
        });

        view.findViewById(R.id.accept_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEntry();
                alertDialog.dismiss();
            }
        });
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        backgroundDisappear();

        if( backgroundDisappear()){
            alertDialog.show();
        }

        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                backgroundReAppear();
            }
        });
    }

    private void showSuccessDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(SubmitProject.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(SubmitProject.this).inflate(
                R.layout.layout_success_dialog,
                (ConstraintLayout) findViewById(R.id.success_dialog_container)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.txt_success)).setText(getResources().getString(R.string.submission_successful));
        ((ImageView) view.findViewById(R.id.success_img)).setImageResource(R.drawable.ic_success);

        final AlertDialog alertDialog = builder.create();
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        backgroundDisappear();

        if( backgroundDisappear()){
            alertDialog.show();
        }

        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                backgroundReAppear();
            }
        });
    }

    private void showErrorDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(SubmitProject.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(SubmitProject.this).inflate(
                R.layout.layout_error_dialog,
                (ConstraintLayout) findViewById(R.id.error_dialog_container)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.txt_error)).setText(getResources().getString(R.string.submission_not_successful));
        ((ImageView) view.findViewById(R.id.error_img)).setImageResource(R.drawable.ic_error);

        final AlertDialog alertDialog = builder.create();
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        backgroundDisappear();
        if( backgroundDisappear()){
            alertDialog.show();
        }

        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                backgroundReAppear();
            }
        });
    }

    private void validateEntry() {
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String githubLink = etGithubLink.getText().toString().trim();

        if (firstName.isEmpty()) {
            etFirstName.setError("First Name is required");
            etFirstName.requestFocus();
            return;
        }

        if (lastName.isEmpty()) {
            etLastName.setError("Last Name is required");
            etLastName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Enter a valid email");
            etEmail.requestFocus();
            return;
        }

        if (githubLink.isEmpty()) {
            etGithubLink.setError("Github Link for project is required");
            etGithubLink.requestFocus();
            return;
        }

        try {
            Call<ResponseBody> call = RetrofitClient
                    .getInstance()
                    .getGoogleFormsApi()
                    .submitProject(firstName, lastName, email, githubLink);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    showSuccessDialog();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    showErrorDialog();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog();
        }

    }

    private Boolean backgroundDisappear() {
        etFirstName.setVisibility(View.INVISIBLE);
        etLastName.setVisibility(View.INVISIBLE);
        etGithubLink.setVisibility(View.INVISIBLE);
        etEmail.setVisibility(View.INVISIBLE);
        mSubmitBtn.setVisibility(View.INVISIBLE);
        return true;
    }

    private Boolean backgroundReAppear() {
        etFirstName.setVisibility(View.VISIBLE);
        etLastName.setVisibility(View.VISIBLE);
        etGithubLink.setVisibility(View.VISIBLE);
        etEmail.setVisibility(View.VISIBLE);
        mSubmitBtn.setVisibility(View.VISIBLE);
        return true;
    }


}