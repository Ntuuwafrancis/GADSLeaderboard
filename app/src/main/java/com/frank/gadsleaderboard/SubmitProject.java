package com.frank.gadsleaderboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SubmitProject extends AppCompatActivity {
    private Toolbar mToolbar;
    private Button mSubmitBtn;
    private Button mCancelBtn;
    private Button mAcceptSubmitBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_project);

        mToolbar = (Toolbar) findViewById(R.id.submit_activity_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Project Submission");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSubmitBtn = findViewById(R.id.submit_project_btn);
        mCancelBtn = findViewById(R.id.cancel_btn);
        mAcceptSubmitBtn = findViewById(R.id.accept_btn);

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
                showSuccessDialog();
                alertDialog.dismiss();
            }
        });
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();
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

        alertDialog.show();

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

        alertDialog.show();

    }


}