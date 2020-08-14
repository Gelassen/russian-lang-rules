package ru.home.russianlang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ru.home.russianlang.content.RulesGen;
import ru.home.russianlang.evaluator.Node;

public class RulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        ExpandableListView view = findViewById(R.id.list);
        AppExpandableListAdapter adapter = new AppExpandableListAdapter(this);
        view.setAdapter(adapter);

        List<Rule> dataset = RulesGen.generateRules();

        adapter.setData(dataset);
    }

    public static class Rule<T extends Node> {

        private T payload;
        private String name = "test-test";
        private List<Rule> subrules = new ArrayList<>();

        public Rule() {}

        public Rule(String name) {
            this.name = name;
        }

        public Rule(T payload) {
            this.payload = payload;
            this.name = payload.getTitle();
        }

        public Rule(String name, T payload) {
            this.payload = payload;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public T getPayload() {
            return payload;
        }

        public List<Rule> getSubrules() {
            return subrules;
        }

        public void addSubRule(Rule rule) {
            subrules.add(rule);
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
        public View getChildView(int pos, int childPos, boolean isExpanded, View convertView, final ViewGroup viewGroup) {
            // TODO move to view holder pattern
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.view_group_item, viewGroup, false);
            }

            final Rule model = (Rule) data.get(pos).getSubrules().get(childPos);
            ((TextView) convertView.findViewById(R.id.text)).setText(model.getName());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.Companion.start(viewGroup.getContext(), model.getPayload());
                }
            });

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }
    }
}
