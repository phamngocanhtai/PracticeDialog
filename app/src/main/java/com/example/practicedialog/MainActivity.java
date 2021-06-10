package com.example.practicedialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements CustomDialogFragment.OnCompleteListener {

    Button btnToast, btnAlertDialog;
    Button btnCustom, btnYes, btnNo;
    Button btnCustomFragmentDialog;
    TextView txtData;
    Button btnDatePicker, btnTimePicker;
    Calendar calendar;
    int year, month, day;
    int hour, min, second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToast = findViewById(R.id.btnToast);
        btnAlertDialog = findViewById(R.id.btnDialog);

        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Hello Everybody", Toast.LENGTH_SHORT).show();
            }
        });

        btnAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Terminator");
                builder.setMessage("Are you sure that you want to quit?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "YES", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        btnCustom = findViewById(R.id.btnCustomDialog);
        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.customdialog);

                EditText editUser = dialog.findViewById(R.id.editUser);
                EditText editPass = dialog.findViewById(R.id.editPass);

                btnYes = dialog.findViewById(R.id.btnYes);
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String username = editUser.getText().toString();
                        String password = editPass.getText().toString();
                        Toast.makeText(MainActivity.this, username + " " + password, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                btnNo = dialog.findViewById(R.id.btnNo);
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                                                WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.show();
            }
        });

        btnCustomFragmentDialog = findViewById(R.id.btnFragmentDialog);
        btnCustomFragmentDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 //               FragmentManager fragmentManager = getSupportFragmentManager();
 //               CustomDialogFragment customDialogFragment = new CustomDialogFragment();
 //               customDialogFragment.show(fragmentManager, "Custom Dialog Fragment");

                //Activity ---> DialogFragment

                FragmentManager fragmentManager = getSupportFragmentManager();
                CustomDialogFragment customDialogFragment = CustomDialogFragment.newInstance("user", "pass");
                customDialogFragment.show(fragmentManager, "Custom Dialog Fragment");
            }
        });

        txtData = findViewById(R.id.txtData);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        btnDatePicker = findViewById(R.id.btnDatePicker);
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int years, int months, int dayOfMonth) {
                                txtData.setText(String.format("%02d/%02d/%d",dayOfMonth, month+1, year));
                                year = years;
                                month = months;
                                day = dayOfMonth;
                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

        hour = calendar.get(Calendar.HOUR);
        min = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);

        btnTimePicker = findViewById(R.id.btnTimePicker);
        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        txtData.setText(String.format("%02d:%02d", hourOfDay, minute));
                    }
                },
                hour, min, true);
                timePickerDialog.show();
            }
        });
    }

    @Override
    public void onComplete(String data) {
        txtData.setText(data);
    }
}