package com.example.jacky.assignment_2;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChildrenItemAdapter extends ArrayAdapter<Child> {
    Context _context;
    ArrayList<Child> children;

    public ChildrenItemAdapter(Context context, ArrayList<Child> children) {
        super(context, 0, children);
        this.children = children;
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;
        Child child = getItem(position);
        // Get the data item for this position
        String child_name_str = getItem(position).getFname() + " " + getItem(position).getLname();
        System.out.println("get view: " + child_name_str);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            System.out.println("setting text: " + child_name_str);
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.children_list, parent, false);
        }
        TextView tv_child = convertView.findViewById(R.id.children_list_item);
        tv_child.setText(child_name_str);
        // Return the completed view to render on screen
        return convertView;
    }

}
