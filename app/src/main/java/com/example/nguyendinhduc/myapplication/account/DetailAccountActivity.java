package com.example.nguyendinhduc.myapplication.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.nguyendinhduc.myapplication.R;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import static com.example.nguyendinhduc.myapplication.Constant.DELETE_PROJECT_RESULT_CODE;
import static com.example.nguyendinhduc.myapplication.Constant.DETAIL_PROJECT_RESULT_CODE;
import static com.example.nguyendinhduc.myapplication.Constant.EDIT_PROJECT_REQUEST_CODE;
import static com.example.nguyendinhduc.myapplication.Constant.USER_ACCESS_LEVEL;
import static com.example.nguyendinhduc.myapplication.Constant.USER_EMAIL;
import static com.example.nguyendinhduc.myapplication.Constant.USER_FULLNAME;
import static com.example.nguyendinhduc.myapplication.Constant.USER_ID;
import static com.example.nguyendinhduc.myapplication.Constant.USER_NAME;
import static com.example.nguyendinhduc.myapplication.Constant.USER_TABLE;

public class DetailAccountActivity extends AppCompatActivity {
    String[] accessLevel;
    TextView tvName,tvFullName,tvEmail,tvAccessLevel;
    Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_account);
        initView();
        getWidgetControl();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        accessLevel=getResources().getStringArray(R.array.access_level);
        tvName=(TextView)findViewById(R.id.usernameDetail);
        tvFullName=(TextView)findViewById(R.id.realNameDetail);
        tvEmail=(TextView)findViewById(R.id.emailDetail);
        tvAccessLevel=(TextView)findViewById(R.id.accessLvDetail);

        String objectId=getIntent().getStringExtra(USER_ID);
        ParseQuery<ParseObject> query=ParseQuery.getQuery(USER_TABLE);
        query.getInBackground(objectId, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e==null) {
                    account = (Account) object;
                    tvName.setText(object.getString(USER_NAME));
                    tvFullName.setText(object.getString(USER_FULLNAME));
                    tvEmail.setText(object.getString(USER_EMAIL));
                    tvAccessLevel.setText(accessLevel[(int) object.getNumber(USER_ACCESS_LEVEL)]);
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
    public void editAccount(View view){
        Intent intent=new Intent(this,EditAccountActivity.class);
        intent.putExtra(USER_ID,getIntent().getStringExtra(USER_ID));
        startActivityForResult(intent,EDIT_PROJECT_REQUEST_CODE);
    }
    public void deleteAccount(View view){
        AccountAction action = new AccountAction();
        action.action(new RemoveAccountCommand(this, account));
        setResult(DELETE_PROJECT_RESULT_CODE, getIntent());
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

    }
}
