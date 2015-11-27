package com.example.nguyendinhduc.myapplication.issue;


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
import com.example.nguyendinhduc.myapplication.issue.CreateIssueActivity;
import com.example.nguyendinhduc.myapplication.issue.DetailIssueActivity;
import com.example.nguyendinhduc.myapplication.issue.IssueAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class IssueFragment extends Fragment {
    Context context;
    ListView errorList;
    IssueAdapter adapter;
    FloatingActionButton createIssue;

    public IssueFragment() {
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
        View view = inflater.inflate(R.layout.fragment_issue, container, false);
        errorList = (ListView) view.findViewById(R.id.errorList);
        createIssue = (FloatingActionButton) view.findViewById(R.id.createIssue);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new IssueAdapter(context, R.layout.item_error_list, null);
        errorList.setAdapter(adapter);
        errorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DetailIssueActivity.class);
                startActivity(intent);
            }
        });
        createIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateIssueActivity.class);
                startActivity(intent);
            }
        });
    }
}
