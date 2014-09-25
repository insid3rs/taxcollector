package singleclick.taxcollector;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import singleclick.taxcollector.db.TaxCollectorDataSource;
import singleclick.taxcollector.db.TaxCollectorHelper;


public class MainListActivity extends ListActivity {

    protected TaxCollectorDataSource mDataSource;

    public static final String TAG = MainListActivity.class.getSimpleName();
    protected ProgressBar mProgressBar;
    protected JSONObject mWPSearchData;
    protected EditText searchTextEdit;
    protected Spinner searchSpinner;
    protected ArrayList<HashMap<String, String>> mWPSearchList;


    public static final int NUMBER_OF_WP = 20;

    private final String KEY_SEARCH = "searchKey";
    private final String KEY_NAME = "name";

    private String namaDSP;
    private String subjekPajakID;
    private String subjekPajakNOP;
    private String subjekPajakNPWPD;
    private String objekUsahaType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        mDataSource = new TaxCollectorDataSource(MainListActivity.this);
        mWPSearchList = new ArrayList<HashMap<String, String>>();
        mDataSource.open();

        searchTextEdit = (EditText)findViewById(R.id.WPSearchText);
        searchSpinner = (Spinner)findViewById(R.id.WPSearchSpinner);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBarMainListView);
        mProgressBar.setVisibility(View.INVISIBLE);

        searchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(searchSpinner.getSelectedItem().toString().equals("NIK")){
                    Cursor cursor = mDataSource.selectSubjekPajakNIK(searchTextEdit.getText().toString());
                    updateList(cursor);
                }else if(searchSpinner.getSelectedItem().toString().equals("NOP")) {
                    Cursor cursor = mDataSource.selectSubjekPajakNOP(searchTextEdit.getText().toString());
                    updateList(cursor);
                }else if(searchSpinner.getSelectedItem().toString().equals("NPWPD")){
                    Cursor cursor = mDataSource.selectObjekUsahaNPWPD(searchTextEdit.getText().toString());
                    updateList(cursor);
                }else if(searchSpinner.getSelectedItem().toString().equals("Nama Subjek Pajak")){
                    Cursor cursor = mDataSource.selectSubjekPajakNamaWP(searchTextEdit.getText().toString());
                    updateList(cursor);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        searchTextEdit.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){

                if(searchSpinner.getSelectedItem().toString().equals("NIK")){
                    Cursor cursor = mDataSource.selectSubjekPajakNIK(searchTextEdit.getText().toString());
                    updateList(cursor);
                }else if(searchSpinner.getSelectedItem().toString().equals("NOP")) {
                    Cursor cursor = mDataSource.selectSubjekPajakNOP(searchTextEdit.getText().toString());
                    updateList(cursor);
                }else if(searchSpinner.getSelectedItem().toString().equals("NPWPD")) {
                    Cursor cursor = mDataSource.selectObjekUsahaNPWPD(searchTextEdit.getText().toString());
                    updateList(cursor);
                }else if(searchSpinner.getSelectedItem().toString().equals("Nama Subjek Pajak")){
                    Cursor cursor = mDataSource.selectSubjekPajakNamaWP(searchTextEdit.getText().toString());
                    updateList(cursor);
                }
            }
        });

        //INI UNTUK WEB SERVICE -> JSON
        /*searchTextEdit.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){

                if(isNetworkAvailable()){
                    mProgressBar.setVisibility(View.VISIBLE);

                    GetWPTask GetWPTask = new GetWPTask();
                    GetWPTask.execute();
                }else{
                    //Toast.makeText(this, "Network is not Available", Toast.LENGTH_LONG).show();
                    System.out.println("No network");
                }
            }
        });
        */

    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("searchKey", mWPSearchList.get(position).get(KEY_SEARCH).toString());
        intent.putExtra("searchType", searchSpinner.getSelectedItem().toString());
        intent.putExtra("objekUsahaType", objekUsahaType);

        startActivity(intent);

        //INI UNTUK WEB SERVICE -> JSON
        /*try {
            JSONArray jsonPosts = mWPSearchData.getJSONArray("nop");
            JSONObject jsonPost = jsonPosts.getJSONObject(position);
            System.out.println("Position "+position);

            Intent intent = new Intent(this, MainActivity.class);

            intent.putExtra("nopJSON", jsonPost.toString());

            startActivity(intent);

        } catch (JSONException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        mDataSource.open();

        if(searchSpinner.getSelectedItem().toString().equals("NOP")) {
            Cursor cursor = mDataSource.selectAllSubjekPajak();
            updateList(cursor);
        }else if(searchSpinner.getSelectedItem().toString().equals("NIK")){
            Cursor cursor = mDataSource.selectAllSubjekPajak();
            updateList(cursor);
        }else if(searchSpinner.getSelectedItem().toString().equals("NPWPD")){
            Cursor cursor = mDataSource.selectAllObjekUsaha();
            updateList(cursor);
        }
    }

    private void updateList(Cursor cursor) {
        mWPSearchList.clear();

        cursor.moveToFirst();
        while( !cursor.isAfterLast() ) {
            // do stuff

            HashMap<String, String> nopItem = new HashMap<String, String>();

            if(searchSpinner.getSelectedItem().toString().equals("NIK")) {
                namaDSP = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DSP_NM_WP));
                subjekPajakID = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID));
                nopItem.put(KEY_SEARCH, subjekPajakID);
            }else if(searchSpinner.getSelectedItem().toString().equals("NOP") || searchSpinner.getSelectedItem().toString().equals("Nama Subjek Pajak")) {
                namaDSP = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DSP_NM_WP));
                subjekPajakNOP = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DSP_NOP));
                nopItem.put(KEY_SEARCH, subjekPajakNOP);
            }else if(searchSpinner.getSelectedItem().toString().equals("NPWPD")) {
                namaDSP = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_NM_OU));
                subjekPajakNPWPD = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_NPWPD));
                objekUsahaType = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_TIPE_OU));
                nopItem.put(KEY_SEARCH, subjekPajakNPWPD);
            }
            nopItem.put(KEY_NAME, namaDSP);

            mWPSearchList.add(nopItem);

            //System.out.println(namaDSP);
            //System.out.println(subjekPajakID);

            cursor.moveToNext();
        }

        String[] keys = {KEY_SEARCH, KEY_NAME};
        int[] ids = {R.id.text1, R.id.text2};
        SimpleAdapter adapter = new SimpleAdapter(this, mWPSearchList, R.layout.search_item_layout, keys, ids);

        setListAdapter(adapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDataSource.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_tambahSubjekPajak) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("searchKey", "");
            intent.putExtra("searchType", "tambahSubjekPajak");
            startActivity(intent);
            return true;
        }else if (id == R.id.action_tambahHotel) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("searchKey", "");
            intent.putExtra("searchType", "tambahHotel");
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*amba
    private class GetWPTask extends AsyncTask<Object, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(Object... arg0) {
            int responseCode = -1;
            JSONObject jsonResponse = null;
            try {
                //URL wpSearchUrl = new URL("http://blog.teamtreehouse.com/api/get_recent_summary/?count=" + NUMBER_OF_WP);
                //URL wpSearchUrl = new URL("http://10.0.2.2/DPP/objekpajak.json");
                URL wpSearchUrl = new URL("http://192.168.56.1/DPP/objekpajak.json");
                //URL wpSearchUrl = new URL("http://192.168.1.20/DPP/objekpajak.json");

                HttpURLConnection connection = (HttpURLConnection) wpSearchUrl.openConnection();
                connection.connect();

                responseCode = connection.getResponseCode();
                if(responseCode == HttpURLConnection.HTTP_OK){
                    InputStream inputStream = connection.getInputStream();
                    Reader reader = new InputStreamReader(inputStream);

                    int contentLength = connection.getContentLength();
                    char[] charArray = new char[contentLength];
                    reader.read(charArray);
                    String responseData = new String(charArray);

                    jsonResponse = new JSONObject(responseData);

                    Log.v(TAG, responseData);

                }else{
                    Log.i(TAG, "Unsucsessfull !" + responseCode);
                }

                Log.i(TAG, "Code :" + responseCode);
            }
            catch (MalformedURLException e){
                Log.e(TAG, "Exception Cought : ", e);
            }
            catch (Exception e){
                Log.e(TAG, "Exception Cought : ", e);
            }

            return jsonResponse;
        }

        @Override
        protected void onPostExecute(JSONObject result){
            mWPSearchData = result;
            handleWPSearchResponse();
        }
    }

    private void handleWPSearchResponse() {
        mProgressBar.setVisibility(View.INVISIBLE);

        if (mWPSearchData == null){
            updateDisplayForError();

        }else{
            try {
                JSONArray jsonPost = mWPSearchData.getJSONArray("nop");
                ArrayList<HashMap<String, String>> NopObject = new ArrayList<HashMap<String, String>>();

                for(int i = 0;i<jsonPost.length();i++){
                    JSONObject post = jsonPost.getJSONObject(i);
                    String nop = post.getString(KEY_SEARCH);
                    nop = Html.fromHtml(nop).toString();
                    String nopName = post.getString(KEY_NAME);
                    nopName = Html.fromHtml(nopName).toString();

                    HashMap<String, String> nopItem = new HashMap<String, String>();
                    nopItem.put(KEY_SEARCH, nop);
                    nopItem.put(KEY_NAME, nopName);


                    if (searchSpinner.getSelectedItem().toString().equals("NOP")){

                        if(nop.toLowerCase().contains(searchTextEdit.getText().toString().toLowerCase())){
                            NopObject.add(nopItem);
                        }
                    }else if(searchSpinner.getSelectedItem().toString().equals("Nama Subjek Pajak")){

                        if(nopName.toLowerCase().contains(searchTextEdit.getText().toString().toLowerCase())){
                            NopObject.add(nopItem);
                        }
                    }

                }

                String[] keys = {KEY_SEARCH, KEY_NAME};
                int[] ids = {R.id.text1, R.id.text2};
                //SimpleAdapter adapter = new SimpleAdapter(this, NopObject, android.R.layout.simple_list_item_2, keys, ids);
                SimpleAdapter adapter = new SimpleAdapter(this, NopObject, R.layout.search_item_layout, keys, ids);

                setListAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateDisplayForError() {
        AlertDialog.Builder builder =  new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.error_title));
        builder.setMessage(getString(R.string.error_msg));
        builder.setPositiveButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();

        TextView emptyTextView = (TextView) getListView().getEmptyView();
        emptyTextView.setText(getString(R.string.no_items));
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if( networkInfo != null && networkInfo.isConnected()){
            isAvailable = true;
        }

        return isAvailable;
    }
    */
}
