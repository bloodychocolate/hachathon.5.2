package com.example.admin.prjsaturn;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    User locUs;
    String ResponseString;
    String urlhost = "http://projectsaturn.tk.host1512310.serv66.hostland.pro";
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        Globals appState = ((Globals) getApplicationContext());
        locUs = appState.getLocalUser();
        appState.activ = this;

        View.OnClickListener oclBtnAddPrem = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String frm = String.format("q=%s&method=getIdByLogin", "hello");
                GetAccessTask tsk = new GetAccessTask(urlhost, frm, 1);
            }
        };

        Button btn = (Button)findViewById(R.id.button4);
        btn.setOnClickListener(oclBtnAddPrem);
    }

    String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    public void callbackLoad() {
        Globals appState = ((Globals) getApplicationContext());
        locUs = appState.getLocalUser();

        TextView name = (TextView) findViewById(R.id.textView2);
        name.setText(locUs.getName());

        TextView group = (TextView) findViewById(R.id.textView6);
        String gp = "";
        switch(locUs.getGroup())
        {
            case 1:
                gp = "Группа активных продаж";
                break;
            case 2:
                gp = "Группа продаж и обслуживания";
                break;
            case 3:
                gp = "Группа по работе с К.К.М";
                break;
            case 4:
                gp = "Группа по работе с Г.К.М";
                break;
        }
        group.setText(gp);

        TextView XP = (TextView) findViewById(R.id.textView15);
        XP.setText(locUs.getXp());
    }

    public void callbackGet(int id, InputStream Response) {
        String ResponseString = convertStreamToString(Response);
        JsonParser pr = new JsonParser();
        switch(id)
        {
            case 1:
                JsonElement elem = pr.parse(ResponseString);
                JsonObject obj = elem.getAsJsonObject();
                JsonElement resp = obj.get("response");
                this.ResponseString = resp.getAsString();

                break;
            case 2:
                //JsonElement elem
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class GetAccessTask extends AsyncTask<Void, Void, Boolean> {
        private String url;
        private String query;
        private int id;

        String convertStreamToString(java.io.InputStream is) {
            java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }

        GetAccessTask(String url, String query, int id) {
            this.url = url;
            this.query = query;
            this.id = id;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            String charset = "UTF-8";

            URLConnection connection;
            InputStream response;
            try {
                connection = new URL(url + "?" + query).openConnection();
                connection.setRequestProperty("Accept-Charset", charset);
                response = connection.getInputStream();

                Globals appState = ((Globals)getApplicationContext());
                appState.activ.callbackGet(id, response);
            }catch(java.io.IOException e){};
            return true;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            int sNum = getArguments().getInt(ARG_SECTION_NUMBER);
            View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
            switch (sNum) {
                case 1:
                    rootView = inflater.inflate(R.layout.fragment_profile, container, false);
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_group1, container, false);
                    break;
                case 3:
                    rootView = inflater.inflate(R.layout.fragment_prodazh, container, false);
                    break;
                case 4:
                    rootView = inflater.inflate(R.layout.fragment_group, container, false);
                    break;
                case 5:
                    rootView = inflater.inflate(R.layout.fragment_task, container, false);
                    break;
                case 6:
                    rootView = inflater.inflate(R.layout.fragment_admin, container, false);
                    break;


            }
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 6 total pages.
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Profile";
                case 1:
                    return "Group1";
                case 2:
                    return "Group2";
                case 3:
                    return "Group3";
                case 4:
                    return "Group4";
                case 5:
                    return "Admin";
            }
            return null;
        }
    }
}


