package com.radicalmedia.politicalactivist;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Home extends AppCompatActivity implements Adapter{

    android.support.v7.app.ActionBar ab;
    ListView listView;

    private DrawerLayout drawerLayout;
    private String[] objs;
    private ListView navListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // list view set up
        listView = (ListView) findViewById(R.id.list_home);
        listView.setAdapter(new ListViewAdapter(this));

        // Drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navListView = (ListView) findViewById(R.id.left_drawer);
        objs = getResources().getStringArray(R.array.navOptions);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.home_toolbar);
        myToolbar.setTitle("Current Activity");
        myToolbar.setTitleTextAppearance(this, R.style.MyTitleTextAppearance);
        setSupportActionBar(myToolbar);

        ab = getSupportActionBar();
    }
}

class SingleItem {
    String title;
    String description;
    String date;
    SingleItem(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }
}

class ListViewAdapter extends BaseAdapter {

    ArrayList<SingleItem> list;
    Context context;
    ListViewAdapter(Context c) {
        list = new ArrayList<SingleItem>();
        context = c;

        Resources res = c.getResources();
        String[] titles = res.getStringArray(R.array.titles);
        String[] descriptions = res.getStringArray(R.array.descriptions);
        String[] date = res.getStringArray(R.array.dates);

        for (int i = 0; i < titles.length; i++) {
            list.add(new SingleItem(titles[i], descriptions[i], date[i]));
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row, viewGroup, false);
        TextView title = (TextView) row.findViewById(R.id.txtTitle);
        TextView description = (TextView) row.findViewById(R.id.txtDescription);
        TextView date = (TextView) row.findViewById(R.id.txtDate);

        SingleItem temp = list.get(i);
        title.setText(temp.title);
        date.setText(temp.date);
        description.setText(temp.description);
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if(res_id == 16908332) {
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawerLayout.openDrawer(Gravity.LEFT);
        }

        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 3) {
            Intent transfer = new Intent(this, activity_vote_rayan.class);
            startActivity(transfer);
        } else if (position == 0) {
            Intent transfer = new Intent(this, MainActivity.class);
            startActivity(transfer);
        } else if (position == 1) {
            Intent transfer = new Intent(this, activity_ASB_advice.class);
            startActivity(transfer);
        } else if (position == 2) {
            Intent transfer = new Intent(this, activity_clubs.class);
            startActivity(transfer);
        } else if (position == 4) {
            Intent transfer = new Intent(this, activity_share.class);
            startActivity(transfer);
        }
    }
}