package singleclick.taxcollector;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends FragmentActivity implements
        ActionBar.TabListener {

    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = { "Data Lokasi", "Subjek Pajak", "Objek Pajak" };

    //protected String mUrl;
    protected Intent intent;

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

        Intent intent = getIntent();
        try {
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

        //Uri blogUri = intent.getData();
        //mUrl = blogUri.toString();

        //WebView webView = (WebView) findViewById(R.id.webView1);
        //webView.loadUrl(mUrl);

        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

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

                TextView textElementNIK = (TextView) findViewById(R.id.textViewNIK);
                textElementNIK.setText(WPNIK);

                TextView textElementName = (TextView) findViewById(R.id.textViewName);
                textElementName.setText(WPName);

                TextView textElementJalan = (TextView) findViewById(R.id.textViewJalan);
                textElementJalan.setText(WPJalan);

                TextView textElementBlok = (TextView) findViewById(R.id.textViewNomor);
                textElementBlok.setText(WPNoJalan);

                TextView textElementRT = (TextView) findViewById(R.id.textViewRT);
                textElementRT.setText(WPRT);

                TextView textElementRW = (TextView) findViewById(R.id.textViewRW);
                textElementRW.setText(WPRW);

                TextView textElementKelurahan = (TextView) findViewById(R.id.textViewKelurahan);
                textElementKelurahan.setText(WPKelurahan);

                TextView textElementKota = (TextView) findViewById(R.id.textViewKota);
                textElementKota.setText(WPKota);

                TextView textElementKodePos = (TextView) findViewById(R.id.textViewKodePos);
                textElementKodePos.setText(WPKodePos);


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
        }
        return super.onOptionsItemSelected(item);
    }

}
