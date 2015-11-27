package com.example.nguyendinhduc.myapplication.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nguyendinhduc.myapplication.R;
import com.example.nguyendinhduc.myapplication.SetHeightListView;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import static com.example.nguyendinhduc.myapplication.Constant.DELETE_PROJECT_RESULT_CODE;
import static com.example.nguyendinhduc.myapplication.Constant.DETAIL_PROJECT_RESULT_CODE;
import static com.example.nguyendinhduc.myapplication.Constant.EDIT_PROJECT_REQUEST_CODE;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_CATEGORY;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_DESCRIPTION;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_ID;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_NAME;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_STATUS;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_TABLE;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_USER;
import static com.example.nguyendinhduc.myapplication.Constant.USER_NAME;
//Lop xu ly giao dien hien thi thong tin cua 1 project
public class DetailProjectActivity extends AppCompatActivity {
    String[] statuses;

    List<String> accessAccounts = new ArrayList<>();
    List<ParseObject> accessAccountsParse = new ArrayList<>();
    List<String> categories = new ArrayList<>();
    TextView projectName, projectStatus, projectDescription;
    ListView listCategories, listAccounts;
    Project project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_project);
        initView();
        getWidgetControl();
    }

    /**
     * Khoi tao cac thanh phan trong giao dien
     */
    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        statuses = getResources().getStringArray(R.array.project_status);
        projectName = (TextView) findViewById(R.id.projectNameTv);
        projectStatus = (TextView) findViewById(R.id.projectStatusTv);
        projectDescription = (TextView) findViewById(R.id.projectDescriptionTv);
        listCategories = (ListView) findViewById(R.id.listCategories);
        listAccounts = (ListView) findViewById(R.id.listAccessAccount);


        final String objectId = getIntent().getStringExtra(PROJECT_ID);

        //Lenh truy van tat ca cac project co trong csdl
        ParseQuery<ParseObject> query = ParseQuery.getQuery(PROJECT_TABLE);
        query.include(PROJECT_USER);
        query.include(PROJECT_CATEGORY);
        query.getInBackground(objectId, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    project = (Project) object;
                    projectName.setText(object.getString(PROJECT_NAME));
                    projectStatus.setText(statuses[((int) object.getNumber(PROJECT_STATUS))]);
                    projectDescription.setText(object.getString(PROJECT_DESCRIPTION));
                    categories = object.getList(PROJECT_CATEGORY);
                    accessAccountsParse = object.getList(PROJECT_USER);
                    if (categories != null) {
                        listCategories.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, categories));
                    }
                    if (accessAccountsParse != null) {
                        for (ParseObject account : accessAccountsParse) {
                            accessAccounts.add(account.getString(USER_NAME));
                        }

                        listAccounts.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, accessAccounts));
                    }
                    SetHeightListView.setListViewHeightBasedOnChildren(listCategories);
                    SetHeightListView.setListViewHeightBasedOnChildren(listAccounts);
                }
            }
        });

    }

    private void getWidgetControl() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            setResult(DETAIL_PROJECT_RESULT_CODE);
            finish();
        }
        return true;
    }

    /**
     * Phuong thuc xu ly su kien button sua project duoc nhan
     * @param view button sua project
     */
    public void editProject(View view) {
        Intent intent = new Intent(this, EditProjectActivity.class);
        intent.putExtra(PROJECT_ID, getIntent().getStringExtra(PROJECT_ID));

        //Mo giao dien chinh sua project
        startActivityForResult(intent, EDIT_PROJECT_REQUEST_CODE);
    }

    /**
     * Phuong thuc xu ly su kien button xoa project duoc nhan
     * @param view button xoa project
     */
    public void deleteProject(View view) {
        //Thuc hien action xoa project tu lop Invoker ProjectAction
        ProjectAction action = new ProjectAction();
        action.action(new RemoveProjectCommand(this, project));
        setResult(DELETE_PROJECT_RESULT_CODE, getIntent());
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_PROJECT_REQUEST_CODE) {
            accessAccounts.clear();
            initView();
        }
    }


}
