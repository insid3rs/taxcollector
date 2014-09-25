package singleclick.taxcollector;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.method.TextKeyListener;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import singleclick.taxcollector.db.TaxCollectorDataSource;
import singleclick.taxcollector.db.TaxCollectorHelper;


public class MainActivity extends FragmentActivity implements
        ActionBar.TabListener {

    protected TaxCollectorDataSource mDataSource;

    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = { "Data Lokasi", "Objek Usaha" };

    //protected String mUrl;
    protected Intent intent;
    protected String searchKey;
    protected String searchType;


    private String WPNIK;
    private String WPName;
    private String WPJalan;
    private String WPNoJalan;
    private String WPRT;
    private String WPRW;
    private String WPKelurahan;
    private String WPKota;
    private String WPKodePos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataSource = new TaxCollectorDataSource(MainActivity.this);

        Intent intent = getIntent();
        searchKey = intent.getStringExtra("searchKey");
        searchType = intent.getStringExtra("searchType");

        //INI UNTUK WEB SERVICE -> JSON
        /*try {
            JSONObject jsonObj = new JSONObject(intent.getStringExtra("nopJSON"));

            WPNIK = jsonObj.getString("nop");
            WPName = jsonObj.getString("name");
            WPJalan = jsonObj.getString("jalan");
            WPNoJalan = jsonObj.getString("blok");
            WPRT = jsonObj.getString("rt");
            WPRW = jsonObj.getString("rw");
            WPKelurahan = jsonObj.getString("kelurahan");
            WPKota = jsonObj.getString("kota");
            WPKodePos = jsonObj.getString("kodepos");

            System.out.println(WPNIK);
            System.out.println(jsonObj.toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        */

        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        //pass variable into Tabs
        mAdapter.setSearchKey(searchKey);
        mAdapter.setSearchType(searchType);

        if(searchType.equals("NIK")){
            tabs = new String[] { "Subjek Pajak", "Objek Pajak"};
        }else{
            tabs = new String[] { "Objek Pajak"};
        }

        viewPager.setAdapter(mAdapter);
        //actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }



        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }


            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }



    @Override
    protected void onStart(){
        super.onStart();


    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();
        //mDataSource.close();
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_cari) {
            Intent intent = new Intent(this, MainListActivity.class);
            startActivity(intent);
        }else if (id == R.id.action_update) {
            updateSubjekPajakDB();
        }


        return super.onOptionsItemSelected(item);
    }

    private void updateSubjekPajakDB() {

        if(searchType.equals("NIK")){
            findViewById(R.id.layout_UpdateSubjekPajakButton).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.layout_UpdateObjekPajakButton).setVisibility(View.VISIBLE);
        }


        ((EditText) findViewById(R.id.editText_NIK)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_DSP_Nama)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_DSP_BLOK_KAV_NO_WP)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_DSP_EMAIL_WP)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_DSP_HP_WP)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_DSP_JALAN_WP)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_DSP_KD_POS_WP)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_DSP_KELURAHAN_WP)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_DSP_KOTA_WP)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_DSP_NOP)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_DSP_RT_WP)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_DSP_RW_WP)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_DSP_NPWP)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_DSP_TELP_WP)).setBackgroundResource(R.drawable.border_edit_text);


        ((EditText) findViewById(R.id.editText_NIK)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_DSP_Nama)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_DSP_BLOK_KAV_NO_WP)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_DSP_EMAIL_WP)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_DSP_HP_WP)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_DSP_JALAN_WP)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_DSP_KD_POS_WP)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_DSP_KELURAHAN_WP)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_DSP_KOTA_WP)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_DSP_NOP)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_DSP_RT_WP)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_DSP_RW_WP)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_DSP_NPWP)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_DSP_TELP_WP)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
    }

}
