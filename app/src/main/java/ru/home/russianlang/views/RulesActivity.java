package ru.home.russianlang.views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ru.home.russianlang.R;
import ru.home.russianlang.content.RulesGen;
import ru.home.russianlang.model.Rule;
import ru.home.russianlang.model.Rules;

public class RulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        ExpandableListView view = findViewById(R.id.list);
        AppExpandableListAdapter adapter = new AppExpandableListAdapter(this);
        view.setAdapter(adapter);


        List<Rules> rules = new RulesGen().readRules(this);

        adapter.setData(rules);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.openPrivacyPolicy:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getString(R.string.privacyPolicy)));
                startActivity(intent);
                return true;
            case R.id.openCredits:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getString(R.string.credits)));
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static class AppExpandableListAdapter extends BaseExpandableListAdapter {

        private LayoutInflater inflater;
        private List<Rules> data = new ArrayList<>();

        public void setData(List<Rules> data) {
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
            return data.get(groupPosition).getRules().size();
        }

        @Override
        public Object getGroup(int position) {
            return data.get(position);
        }

        @Override
        public Object getChild(int groupPos, int childPos) {
            return data.get(groupPos).getRules().get(childPos);
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

            ((TextView) convertView.findViewById(R.id.text)).setText(data.get(pos).getTitle());
            return convertView;
        }

        @Override
        public View getChildView(int pos, int childPos, boolean isExpanded, View convertView, final ViewGroup viewGroup) {
            // TODO move to view holder pattern
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.view_group_item, viewGroup, false);
            }

            final Rule model = (Rule) data.get(pos).getRules().get(childPos);
            ((TextView) convertView.findViewById(R.id.text)).setText(model.getTitle());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.Companion.start(viewGroup.getContext(), model);
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
