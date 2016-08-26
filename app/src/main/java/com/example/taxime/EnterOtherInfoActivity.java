package com.example.taxime;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class EnterOtherInfoActivity extends AppCompatActivity {

    private Button dateBtn;
    private Button timeBtn;
    private Button addBtn;
    private Button nextBtn;

    private EditText dateField;
    private EditText timeField;
    private EditText notesField;
    private EditText contactNameField;
    private EditText contactPhoneField;

    private Calendar calendar;

    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("d-M-YYYY");
    private final SimpleDateFormat timeFormatter = new SimpleDateFormat("k':'m");

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private AlertDialog.Builder notesDialog;

    private static final String TAG = "EnterOtherInfoActivity.java Logs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_other_info);

        //associate views e.g buttn and textviews
        findViewsById();

        //set current date and time in fields
        initializeViews();

        //create date picker dialog
        //create time picker dialog
        //create notes list dialog
        //and their associated on set listeners
        setupPickerDialogs();


        //show dialogs onClick buttons
        addActivityListeners();
    }

    private void addActivityListeners() {
        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show date picker dialog
                datePickerDialog.show();
            }
        });

        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show time picker dialog
                timePickerDialog.show();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show notes dialog
                notesDialog.show();
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save all fields in User data
                User.date = dateField.getText().toString();
                User.time = timeField.getText().toString();
                User.contactName = contactNameField.getText().toString();
                User.contactNumber = contactPhoneField.getText().toString();
                User.notesToDriver = notesField.getText().toString();

                //log the saved data... Just testing if everything is correct
                Log.i(TAG, User.date);
                Log.i(TAG, User.time);
                Log.i(TAG, User.contactName);
                Log.i(TAG, User.contactNumber);
                Log.i(TAG, User.notesToDriver);

                startActivity(new Intent(getBaseContext(), ReviewBookingActivity.class));
            }
        });
    }

    private void findViewsById() {
        dateBtn = (Button) findViewById(R.id.dateBtn);
        timeBtn = (Button) findViewById(R.id.timeBtn);
        addBtn = (Button) findViewById(R.id.addBtn);
        nextBtn = (Button) findViewById(R.id.nextBtn);

        dateField = (EditText) findViewById(R.id.dateField);
        timeField = (EditText) findViewById(R.id.timeField);
        notesField = (EditText) findViewById(R.id.notesField);
        contactNameField = (EditText) findViewById(R.id.nameField);
        contactPhoneField = (EditText) findViewById(R.id.phoneField);
    }

    private void initializeViews() {

        calendar = Calendar.getInstance();


        String date = dateFormatter.format(calendar.getTime());


        String time = timeFormatter.format(calendar.getTime());

        //initialize time and date fields
        dateField.setText(date);
        timeField.setText(time);


    }

    private void setupPickerDialogs(){

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(year, monthOfYear, dayOfMonth);
                dateField.setText(dateFormatter.format(calendar.getTime()));
            }

        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                timeField.setText(timeFormatter.format(calendar.getTime()));
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);

        //AlertDialog really have long code
        setupNotesDialog();

    }

    private void setupNotesDialog() {
        //get the notes_array out of the resources file and load it into our array
        final String[] notesArray = getResources().getStringArray(R.array.notes_array);
        final ArrayList<Integer> selectedNotes = new ArrayList<Integer>();
        notesDialog = new AlertDialog.Builder(this);
        notesDialog.setTitle(R.string.notes_dialog_title)
                .setMultiChoiceItems(R.array.notes_array, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                        if(isChecked){
                            selectedNotes.add(which);
                        }else if(selectedNotes.contains(which))
                            selectedNotes.remove(Integer.valueOf(which));
                    }
                })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String notes = "";
                        String toastMsg = "";
                        for (Integer integer : selectedNotes)       //advanced!
                            if(notesField.getText().toString().contains(notesArray[Integer.valueOf(integer)]))
                                toastMsg = toastMsg + notesArray[Integer.valueOf(integer)] +"has already been added!" + "\n";
                            else
                                notes = notes + notesArray[Integer.valueOf(integer)] + "\n";
                        notesField.append(notes);
                        if(!toastMsg.equals(""))
                            Toast.makeText(EnterOtherInfoActivity.this, toastMsg, Toast.LENGTH_LONG).show();
                        selectedNotes.clear();  //damn you
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        notesDialog.create();
    }


}
