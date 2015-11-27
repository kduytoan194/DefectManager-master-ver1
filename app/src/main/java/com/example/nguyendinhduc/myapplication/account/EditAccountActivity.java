package com.example.nguyendinhduc.myapplication.account;

import android.content.Intent;
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
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import static com.example.nguyendinhduc.myapplication.Constant.PASS;
import static com.example.nguyendinhduc.myapplication.Constant.USER_ACCESS_LEVEL;
import static com.example.nguyendinhduc.myapplication.Constant.USER_EMAIL;
import static com.example.nguyendinhduc.myapplication.Constant.USER_FULLNAME;
import static com.example.nguyendinhduc.myapplication.Constant.USER_ID;
import static com.example.nguyendinhduc.myapplication.Constant.USER_NAME;
import static com.example.nguyendinhduc.myapplication.Constant.USER_TABLE;

public class EditAccountActivity extends AppCompatActivity {
    Spinner accessLevel;
    EditText edtName,edtFullName,edtEmail,edtPass;
    List<String> accounts=new ArrayList<>();
    List<ParseObject> addedAccountsParse=new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView listAccessLevel;
    String[] accessLv = {"Admin", "Manager", "Tester", "Developer", "QualityController"};
    Button btSubmit;
    Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        new GetDataTask().execute();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        accessLevel = (Spinner) findViewById(R.id.accessLvInput);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, accessLv);
        accessLevel.setAdapter(adapter);
        accessLevel.setSelection((Integer) account.getNumber(USER_ACCESS_LEVEL));

        edtName = (EditText) findViewById(R.id.usernameInput);
        edtFullName = (EditText) findViewById(R.id.realnameInput);
        edtEmail = (EditText) findViewById(R.id.emailInput);
        edtPass=(EditText)findViewById(R.id.passInput);

        edtName.setText(account.getString(USER_NAME));
        edtFullName.setText(account.getString(USER_FULLNAME));
        edtEmail.setText(account.getString(USER_EMAIL));
        edtPass.setText(account.getString(PASS));

        btSubmit=(Button) findViewById(R.id.btEdit);

    }

    private void getWidgetControll() {
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account.setData(edtName.getText().toString(), edtFullName.getText().toString(), edtPass.getText().toString(), edtEmail.getText().toString(), accessLevel.getSelectedItemPosition());
                AccountAction action=new AccountAction();
                action.action(new EditAccountCommand(getBaseContext(),account));

            }
        });

    }


    private class GetDataTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            String objecdID= getIntent().getStringExtra(USER_ID);
            ParseQuery<ParseObject> query=ParseQuery.getQuery(USER_TABLE);
            try{
                account=(Account) query.get(objecdID);
            }
            catch (ParseException e){
                e.printStackTrace();
            }
            return null;
        }
    @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            initView();
            getWidgetControll();
        }
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