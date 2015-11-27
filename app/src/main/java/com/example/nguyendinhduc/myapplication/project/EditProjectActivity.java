package com.example.nguyendinhduc.myapplication.project;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.nguyendinhduc.myapplication.R;
import com.example.nguyendinhduc.myapplication.SetHeightListView;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_CATEGORY;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_DESCRIPTION;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_ID;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_NAME;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_STATUS;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_TABLE;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_USER;
import static com.example.nguyendinhduc.myapplication.Constant.USER_NAME;
import static com.example.nguyendinhduc.myapplication.Constant.USER_TABLE;
//Lop xua ly giao dien sua noi dung cua project
public class EditProjectActivity extends AppCompatActivity {
    String[] statuses;
    List<String> accessAccounts = new ArrayList<>();
    List<String> addedAccounts = new ArrayList<>();
    List<String> addedCategories = new ArrayList<>();
    List<ParseObject> addedAccountsParse = new ArrayList<>();
    ArrayAdapter<String> addedCategoriesAdapter, addedAccountsAdapter;
    EditText projectNameInput, descriptionInput, categoryInput;
    Spinner statusInput, accessAccountInput;
    ListView listAccessAccount, listCategory;
    Button addCategoryBtn, addUserBtn, submitBtn;
    List<ParseObject> users;
    Project project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project);
        new GetDataTask().execute();

    }

    /**
     * Khoi tao cac thanh phan trong giao dien
     */
    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        statuses = getResources().getStringArray(R.array.project_status);
        projectNameInput = (EditText) findViewById(R.id.projectnameInput);
        descriptionInput = (EditText) findViewById(R.id.descriptionInput);
        categoryInput = (EditText) findViewById(R.id.categoryInput);
        listAccessAccount = (ListView) findViewById(R.id.listAccessAccount);
        listCategory = (ListView) findViewById(R.id.listCategories);
        statusInput = (Spinner) findViewById(R.id.statusInput);
        accessAccountInput = (Spinner) findViewById(R.id.accessAccountInput);
        addCategoryBtn = (Button) findViewById(R.id.addCategory);
        addUserBtn = (Button) findViewById(R.id.addAccount);
        submitBtn = (Button) findViewById(R.id.submitBtn);

        projectNameInput.setText(project.getString(PROJECT_NAME));
        descriptionInput.setText(project.getString(PROJECT_DESCRIPTION));
        statusInput.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_dropdown_item_1line, statuses));
        statusInput.setSelection((Integer) project.getNumber(PROJECT_STATUS));
        List<String> categories = project.getList(PROJECT_CATEGORY);
        List<ParseObject> projectUsers = project.getList(PROJECT_USER);
        if (categories != null) {
            addedCategories = categories;

        }
        addedCategoriesAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, addedCategories);
        listCategory.setAdapter(addedCategoriesAdapter);
        SetHeightListView.setListViewHeightBasedOnChildren(listCategory);

        if (projectUsers != null) {
            addedAccountsParse = projectUsers;
            for (ParseObject user : addedAccountsParse) {
                addedAccounts.add(user.getString(USER_NAME));
            }

        }
        addedAccountsAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, addedAccounts);
        listAccessAccount.setAdapter(addedAccountsAdapter);
        SetHeightListView.setListViewHeightBasedOnChildren(listAccessAccount);
        for (ParseObject user : users) {
            accessAccounts.add(user.getString(USER_NAME));
        }
        accessAccountInput.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, accessAccounts));


    }

    /**
     * Gan cac su kien cho cac button
     */
    private void getWidgetControl() {
        addCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = categoryInput.getText().toString();
                addedCategories.add(s);
                addedCategoriesAdapter.notifyDataSetChanged();
                SetHeightListView.setListViewHeightBasedOnChildren(listCategory);
                categoryInput.setText("");
            }
        });
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject user = users.get(accessAccountInput.getSelectedItemPosition());
                addedAccounts.add((String) accessAccountInput.getSelectedItem());
                addedAccountsAdapter.notifyDataSetChanged();
                addedAccountsParse.add(user);
                SetHeightListView.setListViewHeightBasedOnChildren(listAccessAccount);
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                project.setData(projectNameInput.getText().toString(), statusInput.getSelectedItemPosition(), descriptionInput.getText().toString(), addedCategories, addedAccountsParse);
                ProjectAction action = new ProjectAction();
                action.action(new EditProjectCommand(getBaseContext(), project));
            }
        });
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

    //Lop tao 1 thread khac de xu ly viec lay du lieu tu server
    private class GetDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            String objectId = getIntent().getStringExtra(PROJECT_ID);

            //Lenh truy van tat ca cac project co trong csdl
            ParseQuery<ParseObject> query = ParseQuery.getQuery(PROJECT_TABLE);
            query.include(PROJECT_USER);
            query.include(PROJECT_CATEGORY);
            try {
                project = (Project) query.get(objectId);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //Lenh truy van tat ca user co trong csdl
            ParseQuery<ParseObject> query1 = ParseQuery.getQuery(USER_TABLE);
            //Han che chi lay ra cot username
            query1.selectKeys(Collections.singletonList(USER_NAME));
            try {
                users = query1.find();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            initView();
            getWidgetControl();
        }
    }
}
