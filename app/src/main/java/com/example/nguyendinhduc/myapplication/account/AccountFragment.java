package com.example.nguyendinhduc.myapplication.account;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nguyendinhduc.myapplication.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import static com.example.nguyendinhduc.myapplication.Constant.CREATE_PROJECT_REQUEST_CODE;
import static com.example.nguyendinhduc.myapplication.Constant.DELETE_PROJECT_RESULT_CODE;
import static com.example.nguyendinhduc.myapplication.Constant.DETAIL_PROJECT_REQUEST_CODE;
import static com.example.nguyendinhduc.myapplication.Constant.USER_ID;
import static com.example.nguyendinhduc.myapplication.Constant.USER_TABLE;
import static com.example.nguyendinhduc.myapplication.Constant.CREATED_AT;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    ListView accountList;
    AccountAdapter adapter;
    Context context;
    FloatingActionButton createAccount;
    List<ParseObject> accounts;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        accountList = (ListView) view.findViewById(R.id.accountList);
        createAccount = (FloatingActionButton) view.findViewById(R.id.createAccount);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ParseQuery<ParseObject> query = ParseQuery.getQuery(USER_TABLE);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(final List<ParseObject> account, ParseException e) {
                accounts = account;
                adapter = new AccountAdapter(context, R.layout.item_account_list, accounts);
                accountList.setAdapter(adapter);
                accountList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(context, DetailAccountActivity.class);
                        intent.putExtra(USER_ID,account.get(position).getObjectId());
                        intent.putExtra("position",position);
                        startActivityForResult(intent,DETAIL_PROJECT_REQUEST_CODE);
                    }
                });
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateAccountActivity.class);
                startActivityForResult(intent, CREATE_PROJECT_REQUEST_CODE);
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {   if(requestCode==DETAIL_PROJECT_REQUEST_CODE){
            if(resultCode==DELETE_PROJECT_RESULT_CODE){
                accounts.remove(data.getIntExtra("position",-1));
            }

    }
        if (requestCode == CREATE_PROJECT_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                //Lenh truy van tat ca cac user co trong csdl
                ParseQuery<ParseObject> query = ParseQuery.getQuery(USER_TABLE);

                query.orderByDescending(CREATED_AT);
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (e == null) {
                            accounts.add(object);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        }

    }
}