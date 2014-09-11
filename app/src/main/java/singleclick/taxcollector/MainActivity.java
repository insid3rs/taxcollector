package singleclick.taxcollector;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
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

    public String NIK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle element = new Bundle();


        Intent intent = getIntent();
        try {
            JSONObject jsonObj = new JSONObject(intent.getStringExtra("nopJSON"));

            String NIK = jsonObj.getString("nop");
            System.out.println(NIK);
            System.out.println(jsonObj.toString());

            element.putString("NIK", NIK);

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


        mAdapter.getItem(0).setArguments(element);


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
                textElementNIK.setText("317401000401703000");

                TextView textElementName = (TextView) findViewById(R.id.textViewName);
                textElementName.setText("MURSID");

                TextView textElementJalan = (TextView) findViewById(R.id.textViewJalan);
                textElementJalan.setText("JL  KEMANGGISAN RAYA");

                TextView textElementBlok = (TextView) findViewById(R.id.textViewNomor);
                textElementBlok.setText(".");

                TextView textElementRT = (TextView) findViewById(R.id.textViewRT);
                textElementRT.setText("9");

                TextView textElementRW = (TextView) findViewById(R.id.textViewRW);
                textElementRW.setText("4");

                TextView textElementKelurahan = (TextView) findViewById(R.id.textViewKelurahan);
                textElementKelurahan.setText("PALMERAH");

                TextView textElementKota = (TextView) findViewById(R.id.textViewKota);
                textElementKota.setText("JAKARTA BARAT");

                TextView textElementKodePos = (TextView) findViewById(R.id.textViewKodePos);
                textElementKodePos.setText("");
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
        getMenuInflater().inflate(R.menu.main_list, menu);
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
