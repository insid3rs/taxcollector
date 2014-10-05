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
    protected String objekUsahaType;

    private String WPNIK;
    private String WPName;
    private String WPJalan;
    private String WPNoJalan;
    private String WPRT;
    private String WPRW;
    private String WPKelurahan;
    private String WPKota;
    private String WPKodePos;
    private String coordinates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataSource = new TaxCollectorDataSource(MainActivity.this);

        Intent intent = getIntent();
        searchKey = intent.getStringExtra("searchKey");
        searchType = intent.getStringExtra("searchType");
        objekUsahaType = intent.getStringExtra("objekUsahaType");
        coordinates = intent.getStringExtra("dataCoord");

        //System.out.println(searchKey);
        //System.out.println(searchType);
        //System.out.println(objekUsahaType);

        if( searchType.equals("Alamat Objek Pajak")){
            searchType = "NOP";
        }else if( searchType.equals("Nama Subjek Pajak")){
            searchType = "NIK";
        }


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
        mAdapter.setObjekUsahaType(objekUsahaType);
        if(coordinates!=null)
            mAdapter.setCoordinates(coordinates);

        if(searchType.equals("NIK") || searchType.equals("tambahSubjekPajak")){
            tabs = new String[] { "Subjek Pajak"};
        }else if(searchType.equals("tambahPBB")){
            tabs = new String[] { "Data PBB"};
        }else if(searchType.equals("NOP")){
            tabs = new String[] { "Data PBB", "Objek Usaha"};
        }else if(searchType.equals("NPWPD")){
            tabs = new String[] { "Objek Usaha ( "+objekUsahaType+" )"};
        }else if(searchType.equals("tambahHotel")){
            tabs = new String[] { "Objek Usaha ( Hotel )"};
        }else if(searchType.equals("tambahParkir")){
            tabs = new String[] { "Objek Usaha ( Parkir )"};
        }else if(searchType.equals("tambahHiburan")){
            tabs = new String[] { "Objek Usaha ( Hiburan )"};
        }else if(searchType.equals("tambahRestoran")){
            tabs = new String[] { "Objek Usaha ( Restoran )"};
        }else if(searchType.equals("tambahReklame")){
            tabs = new String[] { "Objek Usaha ( Reklame )"};
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

        if(searchType.equals("tambahHotel") ||
                searchType.equals("tambahSubjekPajak") ||
                searchType.equals("tambahRestoran") ||
                searchType.equals("tambahParkir") ||
                searchType.equals("tambahHiburan") ||
                searchType.equals("tambahReklame") ||
                searchType.equals("tambahPBB")) {
            MenuItem item = menu.findItem(R.id.action_update);
            item.setVisible(false);
        }

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
            if(searchType.equals("NIK")){
                updateSubjekPajakDB();
            }else if(searchType.equals("NOP")){
                updatePBBDB();
            }else if(searchType.equals("NPWPD") && objekUsahaType.toLowerCase().equals("hotel")){
                updateObjekUsahaHotelDB();
            }else if(searchType.equals("NPWPD") && objekUsahaType.toLowerCase().equals("parkir")){
                updateObjekUsahaParkirDB();
            }
        }


        return super.onOptionsItemSelected(item);
    }

    private void updateSubjekPajakDB() {

        if(searchType.equals("NIK")){
            findViewById(R.id.layout_UpdateSubjekPajakButton).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.layout_UpdateObjekPajakButton).setVisibility(View.VISIBLE);
            findViewById(R.id.captureFront).setVisibility(View.VISIBLE);
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

    private void updatePBBDB() {

        if(searchType.equals("NIK")){
            findViewById(R.id.layout_UpdateSubjekPajakButton).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.layout_UpdateObjekPajakButton).setVisibility(View.VISIBLE);
            findViewById(R.id.captureFront).setVisibility(View.VISIBLE);
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

        ((EditText) findViewById(R.id.editText_DOP_Map_Coordinate)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_DOP_Map_Coordinate)).setText(coordinates);
        ((EditText) findViewById(R.id.editText_NIK)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_DSP_NOP)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_DOP_JALAN_WP)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_DOP_BLOK_KAV_NO_WP)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_DOP_RW_WP)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_DOP_RT_WP)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_DOP_KELURAHAN_WP)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_DOP_Kecamatan)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_LuasTanah)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_JumlahBangunan)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_ZNT)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_JumlahOPBB)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_TanahKosong)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_Kavling)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_TanahdanBangunan)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_FasilitasUmum)).setBackgroundResource(R.drawable.border_edit_text);

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

        ((EditText) findViewById(R.id.editText_DOP_Map_Coordinate)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_NIK)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_DSP_NOP)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_DOP_JALAN_WP)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_DOP_BLOK_KAV_NO_WP)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_DOP_RW_WP)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_DOP_RT_WP)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_DOP_KELURAHAN_WP)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_DOP_Kecamatan)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_LuasTanah)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_JumlahBangunan)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_ZNT)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_JumlahOPBB)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_TanahKosong)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_Kavling)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_TanahdanBangunan)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_FasilitasUmum)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));

    }

    private void updateObjekUsahaHotelDB() {
        findViewById(R.id.layout_UpdateSubjekPajakButton).setVisibility(View.VISIBLE);

        ((EditText) findViewById(R.id.editText_OU_NIK)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_OU_NPWPD)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_NOPPBBUsaha)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_KlasifikasiObjek)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_NamaUsaha)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_NoTlp)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_EmailUsaha)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_AlamatUsaha)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_NOPPBBUsaha)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_NoIzinUsaha)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_TanggalUsaha)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_JumlahPegawai)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_Tarif)).setBackgroundResource(R.drawable.border_edit_text);

        ((EditText) findViewById(R.id.editText_OU_NIK)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_OU_NPWPD)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_NOPPBBUsaha)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_KlasifikasiObjek)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_NamaUsaha)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_NoTlp)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_EmailUsaha)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_AlamatUsaha)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_NOPPBBUsaha)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_NoIzinUsaha)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_TanggalUsaha)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_JumlahPegawai)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_Tarif)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
    }

    private void updateObjekUsahaParkirDB() {
        findViewById(R.id.layout_UpdateSubjekPajakButton).setVisibility(View.VISIBLE);

        ((EditText) findViewById(R.id.editText_OU_NIK)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_OU_NPWPD)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_NOPPBBUsaha)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_KlasifikasiObjek)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_NamaUsaha)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_NoTlp)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_EmailUsaha)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_AlamatUsaha)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_NOPPBBUsaha)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_NoIzinUsaha)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_TanggalUsaha)).setBackgroundResource(R.drawable.border_edit_text);

        ((EditText) findViewById(R.id.editText_KapasitasMobil)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_KapasitasMotor)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_JamPertamaMobil)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_TiapJamMobil)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_MaksimumMobil)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_BulananMobil)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_JamPertamaMotor)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_TiapJamMotor)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_MaksimumMotor)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_BulananMotor)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_JamPertamaMobilBox)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_TiapJamMobilBox)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_MaksimumMobilBox)).setBackgroundResource(R.drawable.border_edit_text);
        ((EditText) findViewById(R.id.editText_BulananMobilBox)).setBackgroundResource(R.drawable.border_edit_text);


        ((EditText) findViewById(R.id.editText_OU_NIK)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_OU_NPWPD)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_NOPPBBUsaha)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_KlasifikasiObjek)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_NamaUsaha)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_NoTlp)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_EmailUsaha)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_AlamatUsaha)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_NOPPBBUsaha)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_NoIzinUsaha)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_TanggalUsaha)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));

        ((EditText) findViewById(R.id.editText_KapasitasMobil)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_KapasitasMotor)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_JamPertamaMobil)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_TiapJamMobil)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_MaksimumMobil)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_BulananMobil)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_JamPertamaMotor)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_TiapJamMotor)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_MaksimumMotor)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_BulananMotor)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_JamPertamaMobilBox)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_TiapJamMobilBox)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_MaksimumMobilBox)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));
        ((EditText) findViewById(R.id.editText_BulananMobilBox)).setKeyListener(new TextKeyListener(TextKeyListener.Capitalize.WORDS,true));

    }

}
