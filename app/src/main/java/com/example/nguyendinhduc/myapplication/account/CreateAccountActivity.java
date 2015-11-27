package com.example.nguyendinhduc.myapplication.account;

import android.content.Intent;
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

import static com.example.nguyendinhduc.myapplication.Constant.USER_NAME;
import static com.example.nguyendinhduc.myapplication.Constant.USER_FULLNAME;
import static com.example.nguyendinhduc.myapplication.Constant.USER_ID;
import static com.example.nguyendinhduc.myapplication.Constant.USER_TABLE;
import static com.example.nguyendinhduc.myapplication.Constant.USER_EMAIL;
import static com.example.nguyendinhduc.myapplication.Constant.USER_ACCESS_LEVEL;
import static com.example.nguyendinhduc.myapplication.Constant.PASS;

import com.example.nguyendinhduc.myapplication.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class CreateAccountActivity extends AppCompatActivity {
    Spinner accessLevel;
    EditText edtName,edtFullName,edtEmail,edtPass;
    List<String> accounts=new ArrayList<>();
    List<ParseObject> addedAccountsParse=new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView listAccessLevel;
    String[] accessLv = {"Admin", "Manager", "Tester", "Developer", "QualityController"};
    Button btSubmit;
    Account account=new Account();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        initView();
        getWidgetControll();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        accessLevel = (Spinner) findViewById(R.id.functionCreate);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, accessLv);
        accessLevel.setAdapter(adapter);
        btSubmit= (Button) findViewById(R.id.submitCreate);
        edtName= (EditText) findViewById(R.id.usernameCreate);
        edtFullName=(EditText) findViewById(R.id.realNameCreate);
        edtEmail=(EditText) findViewById(R.id.emailCreate);
        edtPass=(EditText) findViewById(R.id.passCreate);

        ParseQuery<ParseObject> query=ParseQuery.getQuery(USER_TABLE);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    for (ParseObject user:objects){
                        accounts.add(user.getString(USER_NAME));
                    }
                }
            }
        });



    }

    /**
     * xử lý sự kiện thêm tài khoản
     */
    private void getWidgetControll() {
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                account.put(USER_NAME,edtName.getText().toString());
                account.put(USER_FULLNAME,edtFullName.getText().toString());
                account.put(USER_EMAIL,edtEmail.getText().toString());
                account.put(USER_ACCESS_LEVEL,accessLevel.getSelectedItemPosition());
                account.put(PASS,edtPass.getText().toString());

                AccountAction accountAction=new AccountAction();
                accountAction.action(new CreatAccountCommand(getBaseContext(),account));
                setResult(RESULT_OK);
                finish();
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
            setResult(RESULT_CANCELED);
            finish();
        }
        return true;
    }


}
