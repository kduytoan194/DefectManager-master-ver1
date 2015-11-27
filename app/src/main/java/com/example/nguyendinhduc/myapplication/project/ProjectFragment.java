package com.example.nguyendinhduc.myapplication.project;


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
import com.example.nguyendinhduc.myapplication.SetHeightListView;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import static com.example.nguyendinhduc.myapplication.Constant.CREATED_AT;
import static com.example.nguyendinhduc.myapplication.Constant.CREATE_PROJECT_REQUEST_CODE;
import static com.example.nguyendinhduc.myapplication.Constant.DELETE_PROJECT_RESULT_CODE;
import static com.example.nguyendinhduc.myapplication.Constant.DETAIL_PROJECT_REQUEST_CODE;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_ID;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_TABLE;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_USER;


/**
 * Fragment hien thi danh sach cac project co trong csdl
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends Fragment {
    ListView projectList;
    ProjectAdapter adapter;
    Context context;
    FloatingActionButton createProject;
    List<ParseObject> projects;

    public ProjectFragment() {
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
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        projectList = (ListView) view.findViewById(R.id.projectList);
        createProject = (FloatingActionButton) view.findViewById(R.id.createProject);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Lenh truy van tat ca project co trong csdl
        ParseQuery<ParseObject> query = ParseQuery.getQuery(PROJECT_TABLE);
        query.include(PROJECT_USER);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(final List<ParseObject> objects, ParseException e) {
                //neu khong co loi tra ve
                if (e == null) {
                    projects = objects;
                    adapter = new ProjectAdapter(context, R.layout.item_project_list, projects);
                    projectList.setAdapter(adapter);
                    projectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getContext(), DetailProjectActivity.class);
                            intent.putExtra(PROJECT_ID, objects.get(position).getObjectId());
                            intent.putExtra("position", position);
                            startActivityForResult(intent, DETAIL_PROJECT_REQUEST_CODE);
                        }
                    });
                }
            }
        });

        //Gan su kien cho float create button
        createProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateProjectActivity.class);
                startActivityForResult(intent, CREATE_PROJECT_REQUEST_CODE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DETAIL_PROJECT_REQUEST_CODE) {
            if (resultCode == DELETE_PROJECT_RESULT_CODE) {
                //xoa project da bi xoa va cap nhat lai listview
                projects.remove(data.getIntExtra("position", -1));
                adapter.notifyDataSetChanged();
            }
        } else if (requestCode == CREATE_PROJECT_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                //Lenh truy van tat ca cac project co trong csdl
                ParseQuery<ParseObject> query = ParseQuery.getQuery(PROJECT_TABLE);
                query.include(PROJECT_USER);
                query.orderByDescending(CREATED_AT);
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (e == null) {
                            projects.add(object);
                            adapter.notifyDataSetChanged();
                            SetHeightListView.setListViewHeightBasedOnChildren(projectList);
                        }
                    }
                });
            }
        }
    }
}
