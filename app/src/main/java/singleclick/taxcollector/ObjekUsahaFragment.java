package singleclick.taxcollector;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import singleclick.taxcollector.db.TaxCollectorDataSource;
import singleclick.taxcollector.db.TaxCollectorHelper;

public class ObjekUsahaFragment extends Fragment {

    protected TaxCollectorDataSource mDataSource;
    protected String searchKey;
    protected String searchType;
    protected String objekUsahaPBB;
    protected Cursor cursorSubjekPajak;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();

        searchKey = bundle.getString("searchKey");
        searchType = bundle.getString("searchType");

        mDataSource = new TaxCollectorDataSource(getActivity());
        mDataSource.open();
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {


		View rootView = inflater.inflate(R.layout.fragment_objek_pajak, container, false);

        if(searchType.equals("NIK")) {
            cursorSubjekPajak = mDataSource.selectObjekUsahaNOP(searchKey);
            updateLayoutObjekUsaha(cursorSubjekPajak);
        }else if(searchType.equals("NOP")) {
            cursorSubjekPajak = mDataSource.selectObjekUsahaNOP(searchKey);
            updateLayoutObjekUsaha(cursorSubjekPajak);
        }


        // get the listview
        expListView = (ExpandableListView) rootView.findViewById(R.id.lvExp);

        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                /*Toast.makeText(
                        getActivity(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();*/
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                /*Toast.makeText(getActivity(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();*/
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                /*Toast.makeText(getActivity(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();*/

            }
        });

        return rootView;
	}

    private void updateLayoutObjekUsaha(Cursor cursor) {

        int countPBB = 0;
        int countHiburan = 0;
        int countHotel = 0;
        int countParkir = 0;
        int countReklame = 0;
        int countRestoran = 0;


        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        List<String> PBB = new ArrayList<String>();
        List<String> HIBURAN = new ArrayList<String>();
        List<String> HOTEL = new ArrayList<String>();
        List<String> PARKIR = new ArrayList<String>();
        List<String> REKLAME = new ArrayList<String>();
        List<String> RESTORAN = new ArrayList<String>();


        cursorSubjekPajak.moveToFirst();
        while( !cursorSubjekPajak.isAfterLast() ) {
            String DSP_NOP = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DSP_NOP));
            String DOU_NM_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_NM_OU));
            String DOU_NPWPD = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_NPWPD));
            String DOU_TIPE_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_TIPE_OU));

            System.out.println("masuk "+DOU_TIPE_OU);

            if(DOU_TIPE_OU.equalsIgnoreCase("pbb")){
                PBB.add(DOU_NM_OU + " (" + DOU_NPWPD +")");
                countPBB++;
            }else if(DOU_TIPE_OU.equalsIgnoreCase("hiburan")){
                HIBURAN.add(DOU_NM_OU + " (" + DOU_NPWPD +")");
                countHiburan++;
            }else if(DOU_TIPE_OU.equalsIgnoreCase("hotel")){
                HOTEL.add(DOU_NM_OU + " (" + DOU_NPWPD +")");
                countHotel++;
            }else if(DOU_TIPE_OU.equalsIgnoreCase("parkir")){
                PARKIR.add(DOU_NM_OU + " (" + DOU_NPWPD +")");
                countParkir++;
            }else if(DOU_TIPE_OU.equalsIgnoreCase("reklame")){
                REKLAME.add(DOU_NM_OU + " (" + DOU_NPWPD +")");
                countReklame++;
            }else if(DOU_TIPE_OU.equalsIgnoreCase("restoran")){
                RESTORAN.add(DOU_NM_OU + " (" + DOU_NPWPD +")");
                countRestoran++;
            }

            cursorSubjekPajak.moveToNext();
        }

        // Adding child data
        listDataHeader.add("PBB (" + countPBB + ")");
        listDataHeader.add("HIBURAN (" + countHiburan + ")");
        listDataHeader.add("HOTEL (" + countHotel + ")");
        listDataHeader.add("PARKIR (" + countParkir + ")");
        listDataHeader.add("REKLAME (" + countReklame + ")");
        listDataHeader.add("RESTORAN (" + countRestoran + ")");

        PBB.add("Tambah Data...");
        HIBURAN.add("Tambah Data...");
        HOTEL.add("Tambah Data...");
        PARKIR.add("Tambah Data...");
        REKLAME.add("Tambah Data...");
        RESTORAN.add("Tambah Data...");

        listDataChild.put(listDataHeader.get(0), PBB); // Header, Child data
        listDataChild.put(listDataHeader.get(1), HIBURAN);
        listDataChild.put(listDataHeader.get(2), HOTEL);
        listDataChild.put(listDataHeader.get(3), PARKIR);
        listDataChild.put(listDataHeader.get(4), REKLAME);
        listDataChild.put(listDataHeader.get(5), RESTORAN);

        //normalMode();

    }


}
