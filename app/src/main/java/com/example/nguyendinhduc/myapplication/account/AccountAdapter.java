package com.example.nguyendinhduc.myapplication.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.nguyendinhduc.myapplication.Constant;
import com.example.nguyendinhduc.myapplication.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.parse.ParseObject;
import com.parse.ParseRole;

import static com.example.nguyendinhduc.myapplication.Constant.USER_ACCESS_LEVEL;
import static com.example.nguyendinhduc.myapplication.Constant.USER_FULLNAME;
import static com.example.nguyendinhduc.myapplication.Constant.USER_NAME;

import java.util.List;

/**
 * Created by khuatduytoan on 11/22/15.
 */
public class AccountAdapter extends ArrayAdapter<ParseObject> {
    Context context;
    int layoutId;
    String jobLevels[];
    int[] avatars = {R.drawable.face1, R.drawable.face2, R.drawable.face3,R.drawable.face4};
    List<ParseObject> users;

    public AccountAdapter(Context context, int resource, List<ParseObject> objects) {
        super(context, resource, objects);
        this.context = context;
        layoutId = resource;
        this.users = objects;
        jobLevels=context.getResources().getStringArray(R.array.accessLv);
    }

    // ô k có mấy cái hàm override getPosition các kiểu à.chỗ nào nhỉ
    // ô thử debug chưa.rồi

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutId, null);
        }
        ParseObject user = users.get(position);
        CircularImageView circularImageView = (CircularImageView) convertView.findViewById(R.id.avatarLvItem);
        TextView username = (TextView) convertView.findViewById(R.id.usernameLvItem);
        TextView jobLevel = (TextView) convertView.findViewById(R.id.jobLevelLvItem);
        TextView realName = (TextView) convertView.findViewById(R.id.realNameLvItem);

        username.setText(user.getString(USER_NAME));
        jobLevel.setText(jobLevels[(int) user.getNumber(USER_ACCESS_LEVEL)]);
        realName.setText(user.getString(USER_FULLNAME));
        return convertView;
    }

}
