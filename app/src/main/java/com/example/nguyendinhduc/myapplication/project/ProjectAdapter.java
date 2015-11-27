package com.example.nguyendinhduc.myapplication.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nguyendinhduc.myapplication.R;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import static com.example.nguyendinhduc.myapplication.Constant.MANAGER_ROLE;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_NAME;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_STATUS;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_TABLE;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_USER;
import static com.example.nguyendinhduc.myapplication.Constant.USER_ACCESS_LEVEL;
import static com.example.nguyendinhduc.myapplication.Constant.USER_NAME;

/**
 * Created by nguyendinhduc on 11/8/15.
 */
public class ProjectAdapter extends ArrayAdapter<ParseObject> {
    Context context;
    int layoutId;
    String[] projectStatuses;
    List<ParseObject> projects;

    public ProjectAdapter(Context context, int resource, List<ParseObject> projects) {
        super(context, resource, projects);
        this.context = context;
        layoutId = resource;
        this.projects = projects;
        projectStatuses = context.getResources().getStringArray(R.array.project_status);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutId, null);
        }

        ParseObject project = projects.get(position);
        TextView projectName = (TextView) convertView.findViewById(R.id.projectNameLvItem);
        TextView projectStatus = (TextView) convertView.findViewById(R.id.projectStatusLvItem);
        TextView projectManager = (TextView) convertView.findViewById(R.id.projectManagerLvItem);

        projectName.setText(project.getString(PROJECT_NAME));
        projectStatus.setText(projectStatuses[((int) project.getNumber(PROJECT_STATUS))]);
        List<ParseUser> users = project.getList(PROJECT_USER);
        if (users != null) {
            for (ParseUser user : users) {
                if ((int) user.getNumber(USER_ACCESS_LEVEL) == MANAGER_ROLE) {
                    projectManager.setText("Manager: " + user.getString(USER_NAME));
                }
            }
        }

        return convertView;
    }
}
