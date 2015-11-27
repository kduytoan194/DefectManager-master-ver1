package com.example.nguyendinhduc.myapplication.issue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nguyendinhduc.myapplication.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

/**
 * Created by nguyendinhduc on 11/8/15.
 */
public class IssueAdapter extends ArrayAdapter<String> {
    Context context;
    int layoutId;
    String[] summaries = {"Crash when login", "Can't connect the internet",
            "Loading infinitive when open app", "Disconnect to server", "Can't buy the item", "Crash when watch video"};
    int[] avatars = {R.drawable.face1, R.drawable.face2, R.drawable.face3, R.drawable.face2, R.drawable.face4, R.drawable.face5};
    String[] assign = {"mafiaboss0605", "boydeptrai", "boyxauxi", "girlxinh", "girlxau", "girlbinhthuong"};

    public IssueAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        layoutId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutId, null);
        }
        CircularImageView circularImageView = (CircularImageView) convertView.findViewById(R.id.avatarLvItem);
        TextView summary = (TextView) convertView.findViewById(R.id.summaryLvItem);
        TextView assignTo = (TextView) convertView.findViewById(R.id.assignToLvItem);
        TextView date = (TextView) convertView.findViewById(R.id.dateLvItem);
        circularImageView.setImageResource(avatars[position]);
        summary.setText(summaries[position]);
        assignTo.setText("Assigned To: " + assign[position]);
        return convertView;
    }

    @Override
    public int getCount() {
        return avatars.length;
    }
}
