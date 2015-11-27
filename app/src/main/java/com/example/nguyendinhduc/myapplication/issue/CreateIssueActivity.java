package com.example.nguyendinhduc.myapplication.issue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.nguyendinhduc.myapplication.R;

public class CreateIssueActivity extends AppCompatActivity {
    String[] assignedUsers = {"mafiaboss0605", "boydeptrai", "boyxauxi", "girlxinh", "girlxau", "girlbinhthuong"};
    String[] category = {"Mobile", "Web", "Window"};
    String[] priority = {"low", "medium", "high", "urgent"};
    Spinner categorySpinnerInput, prioritySpinnerInput, assignToSpinnerInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_issue);
        initView();
        getWidgetControll();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categorySpinnerInput = (Spinner) findViewById(R.id.categorySpinnerInput);
        prioritySpinnerInput = (Spinner) findViewById(R.id.prioritySpinnerInput);
        assignToSpinnerInput = (Spinner) findViewById(R.id.assignToSpinnerInput);

        categorySpinnerInput.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, category));
        prioritySpinnerInput.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, priority));
        assignToSpinnerInput.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, assignedUsers));
    }

    private void getWidgetControll() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }


}
