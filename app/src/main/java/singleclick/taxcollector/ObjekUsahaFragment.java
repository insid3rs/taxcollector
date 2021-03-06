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

                if(listDataHeader.get(groupPosition).toString().contains("HOTEL")){
                    String item = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);

                    if (item.equalsIgnoreCase("Tambah Data Hotel...")){
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("searchKey", "");
                        intent.putExtra("searchType", "tambahHotel");
                        startActivity(intent);

                    }else {

                        int start = item.lastIndexOf(" ( NPWPD : ") + 11;
                        int end = item.lastIndexOf(")");

                        String NPWPD = item.substring(start, end);

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("searchKey", NPWPD);
                        intent.putExtra("searchType", "NPWPD");
                        intent.putExtra("objekUsahaType", "HOTEL");

                        startActivity(intent);
                        System.out.println(NPWPD);
                    }


                }else if(listDataHeader.get(groupPosition).toString().contains("PARKIR")){
                    String item = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);

                    if (item.equalsIgnoreCase("Tambah Data Parkir...")){
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("searchKey", "");
                        intent.putExtra("searchType", "tambahParkir");
                        startActivity(intent);
                    }else {
                        int start = item.lastIndexOf(" ( NPWPD : ") + 11;
                        int end = item.lastIndexOf(")");

                        String NPWPD = item.substring(start, end);

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("searchKey", NPWPD);
                        intent.putExtra("searchType", "NPWPD");
                        intent.putExtra("objekUsahaType", "PARKIR");

                        startActivity(intent);
                        System.out.println(NPWPD);
                    }
                }else if(listDataHeader.get(groupPosition).toString().contains("RESTORAN")){
                    String item = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);

                    if (item.equalsIgnoreCase("Tambah Data Restoran...")){
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("searchKey", "");
                        intent.putExtra("searchType", "tambahRestoran");
                        startActivity(intent);

                    }else {
                        int start = item.lastIndexOf(" ( NPWPD : ") + 11;
                        int end = item.lastIndexOf(")");

                        String NPWPD = item.substring(start, end);

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("searchKey", NPWPD);
                        intent.putExtra("searchType", "NPWPD");
                        intent.putExtra("objekUsahaType", "RESTORAN");

                        startActivity(intent);
                        System.out.println(NPWPD);
                    }
                }else if(listDataHeader.get(groupPosition).toString().contains("HIBURAN")){
                    String item = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);

                    if (item.equalsIgnoreCase("Tambah Data Hiburan...")){
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("searchKey", "");
                        intent.putExtra("searchType", "tambahHiburan");
                        startActivity(intent);

                    }else {
                        int start = item.lastIndexOf(" ( NPWPD : ") + 11;
                        int end = item.lastIndexOf(")");

                        String NPWPD = item.substring(start, end);

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("searchKey", NPWPD);
                        intent.putExtra("searchType", "NPWPD");
                        intent.putExtra("objekUsahaType", "HIBURAN");

                        startActivity(intent);
                        System.out.println(NPWPD);
                    }
                }


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

        int countHiburan = 0;
        int countHotel = 0;
        int countParkir = 0;
        int countRestoran = 0;


        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        List<String> HIBURAN = new ArrayList<String>();
        List<String> HOTEL = new ArrayList<String>();
        List<String> PARKIR = new ArrayList<String>();
        List<String> RESTORAN = new ArrayList<String>();


        cursorSubjekPajak.moveToFirst();
        while( !cursorSubjekPajak.isAfterLast() ) {
            String DSP_NOP = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DSP_NOP));
            String DOU_NM_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_NM_OU));
            String DOU_NPWPD = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_NPWPD));
            String DOU_TIPE_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_TIPE_OU));

            //System.out.println("masuk "+DOU_TIPE_OU);

            if(DOU_TIPE_OU.equalsIgnoreCase("hiburan")){
                HIBURAN.add(DOU_NM_OU + " ( NPWPD : " + DOU_NPWPD +")");
                countHiburan++;
            }else if(DOU_TIPE_OU.equalsIgnoreCase("hotel")){
                HOTEL.add(DOU_NM_OU + " ( NPWPD : " + DOU_NPWPD +")");
                countHotel++;
            }else if(DOU_TIPE_OU.equalsIgnoreCase("parkir")){
                PARKIR.add(DOU_NM_OU + " ( NPWPD : " + DOU_NPWPD +")");
                countParkir++;
            }else if(DOU_TIPE_OU.equalsIgnoreCase("restoran")){
                RESTORAN.add(DOU_NM_OU + " ( NPWPD : " + DOU_NPWPD +")");
                countRestoran++;
            }

            cursorSubjekPajak.moveToNext();
        }

        // Adding child data
        listDataHeader.add("HIBURAN (" + countHiburan + ")");
        listDataHeader.add("HOTEL (" + countHotel + ")");
        listDataHeader.add("PARKIR (" + countParkir + ")");
        listDataHeader.add("RESTORAN (" + countRestoran + ")");

        HIBURAN.add("Tambah Data Hiburan...");
        HOTEL.add("Tambah Data Hotel...");
        PARKIR.add("Tambah Data Parkir...");
        RESTORAN.add("Tambah Data Restoran...");

        listDataChild.put(listDataHeader.get(0), HIBURAN); // Header, Child data
        listDataChild.put(listDataHeader.get(1), HOTEL);
        listDataChild.put(listDataHeader.get(2), PARKIR);
        listDataChild.put(listDataHeader.get(3), RESTORAN);

        //normalMode();

    }


}
