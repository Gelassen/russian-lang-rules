package ru.home.russianlang;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        ExpandableListView view = findViewById(R.id.list);
        AppExpandableListAdapter adapter = new AppExpandableListAdapter(this);
        view.setAdapter(adapter);

        List<Rule> dataset = new ArrayList<>();

        Rule rule = new Rule();
        rule.getSubrules().add(new Rule());
        dataset.add(rule);

        adapter.setData(dataset);
    }

    public static class Rule {

        private String name = "test-test";

        private List<Rule> subrules = new ArrayList<>();

        public String getName() {
            return name;
        }

        public List<Rule> getSubrules() {
            return subrules;
        }

    }

    public static class AppExpandableListAdapter extends BaseExpandableListAdapter {

        private LayoutInflater inflater;
        private List<Rule> data = new ArrayList<>();

        public void setData(List<Rule> data) {
            this.data.clear();
            this.data = data;
            notifyDataSetChanged();
        }

        public AppExpandableListAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getGroupCount() {
            return data.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return data.get(groupPosition).getSubrules().size();
        }

        @Override
        public Object getGroup(int position) {
            return data.get(position);
        }

        @Override
        public Object getChild(int groupPos, int childPos) {
            return data.get(groupPos).getSubrules().get(childPos);
        }

        @Override
        public long getGroupId(int pos) {
            return 0;
        }

        @Override
        public long getChildId(int groupPos, int childPos) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int pos, boolean isExpanded, View convertView, ViewGroup viewGroup) {
            // TODO move to view holder pattern
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.view_group, viewGroup, false);
            }

            ((TextView) convertView.findViewById(R.id.text)).setText(data.get(pos).getName());
            return convertView;
        }

        @Override
        public View getChildView(int pos, int childPos, boolean isExpanded, View convertView, ViewGroup viewGroup) {
            // TODO move to view holder pattern
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.view_group_item, viewGroup, false);
            }

            ((TextView) convertView.findViewById(R.id.text)).setText(data.get(pos).getSubrules().get(childPos).getName());
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }
    }
}
