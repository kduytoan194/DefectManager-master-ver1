package com.example.nguyendinhduc.myapplication.issue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.nguyendinhduc.myapplication.R;
import com.example.nguyendinhduc.myapplication.account.EditAccountActivity;

public class EditIssueActivity extends AppCompatActivity {
    String[] assignedUsers = {"mafiaboss0605", "boydeptrai", "boyxauxi", "girlxinh", "girlxau", "girlbinhthuong"};
    String[] category = {"Mobile", "Web", "Window"};
    String[] priority = {"low", "medium", "high", "urgent"};
    EditText summaryInput, descriptionInput;
    Spinner categorySpinnerInput, prioritySpinnerInput, assignToSpinnerInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_issue);
        initView();
        getWidgetControll();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        summaryInput = (EditText) findViewById(R.id.summaryInput);
        descriptionInput = (EditText) findViewById(R.id.descriptionInput);
        categorySpinnerInput = (Spinner) findViewById(R.id.categorySpinnerInput);
        prioritySpinnerInput = (Spinner) findViewById(R.id.prioritySpinnerInput);
        assignToSpinnerInput = (Spinner) findViewById(R.id.assignToSpinnerInput);

        summaryInput.setText("Crash when login");
        descriptionInput.setText("When i login the application suddenly go black");
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

    public void editAccount(View view) {
        Intent intent = new Intent(this, EditAccountActivity.class);
        startActivity(intent);
    }

}
