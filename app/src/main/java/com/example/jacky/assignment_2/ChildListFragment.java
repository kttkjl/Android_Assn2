package com.example.jacky.assignment_2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class ChildListFragment extends ListFragment {

    private ChildListListener listener;
    private MainActivity parentActivity;
    private ChildrenItemAdapter children_list_adapter;

    public ChildListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentActivity = (MainActivity) getActivity();
        ArrayList<Child> children = parentActivity.getChildren();

        children_list_adapter = new ChildrenItemAdapter(parentActivity, children);
        setListAdapter(children_list_adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (ChildListListener) getActivity();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener != null) {
            listener.itemClicked(parentActivity.children.get(position));
        }
    }

    public void updateListFragment(ArrayList<Child> children) {
        children_list_adapter.clear();
        children_list_adapter.addAll(children);
        setListAdapter(children_list_adapter);
        children_list_adapter.notifyDataSetChanged();
    }

}
