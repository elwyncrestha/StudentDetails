package com.softwarica.studentdetails;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private RadioGroup rgGender;
    private Spinner spCountry;
    private AutoCompleteTextView actvBatch;
    private Button btnSave;
    private TextView tvName, tvGender, tvCountry, tvBatch;
    private AlertDialog.Builder builder;

    private String[] countries = {"Nepal", "China", "India"};
    private String[] batches = {"22A", "22B", "22C"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        builder = new AlertDialog.Builder(this);

        this.etName = findViewById(R.id.etName);
        this.rgGender = findViewById(R.id.rgGender);
        this.spCountry = findViewById(R.id.spCountry);
        this.actvBatch = findViewById(R.id.actvBatch);
        this.btnSave = findViewById(R.id.btnSave);
        this.tvName = findViewById(R.id.tvName);
        this.tvGender = findViewById(R.id.tvGender);
        this.tvCountry = findViewById(R.id.tvCountry);
        this.tvBatch = findViewById(R.id.tvBatch);

        ArrayAdapter countryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countries);
        spCountry.setAdapter(countryAdapter);

        ArrayAdapter batchAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, batches);
        actvBatch.setAdapter(batchAdapter);
        actvBatch.setThreshold(1);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Do you really want to save?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    tvName.setText(String.format("Name: %s", String.valueOf(etName.getText())));
                                    RadioButton selectedGender = findViewById(rgGender.getCheckedRadioButtonId());
                                    tvGender.setText(String.format("Gender: %s", String.valueOf(selectedGender.getText())));
                                    tvCountry.setText(String.format("Country: %s", spCountry.getSelectedItem().toString()));
                                    tvBatch.setText(String.format("Batch: %s", String.valueOf(actvBatch.getText())));
                                } catch (Exception e) {
                                    Toast.makeText(MainActivity.this, "Sorry! Error occurred", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(), "Cancelled!!!", Toast.LENGTH_SHORT).show();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle("Confirm");
                alertDialog.show();
            }
        });
    }
}
