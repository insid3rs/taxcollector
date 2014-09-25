package singleclick.taxcollector;

import android.content.Intent;
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

public class ObjekUsahaFragment extends Fragment {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_objek_pajak, container, false);

        // get the listview
        expListView = (ExpandableListView) rootView.findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

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
                Toast.makeText(
                        getActivity(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        return rootView;
	}

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("PBB");
        listDataHeader.add("HIBURAN");
        listDataHeader.add("HOTEL");
        listDataHeader.add("PARKIR");
        listDataHeader.add("REKLAME");
        listDataHeader.add("RESTORAN");



        // Adding child data
        List<String> PBB = new ArrayList<String>();
        PBB.add("Tambah Data...");

        List<String> HIBURAN = new ArrayList<String>();
        HIBURAN.add("Tambah Data...");

        List<String> HOTEL = new ArrayList<String>();
        HOTEL.add("Tambah Data...");

        List<String> PARKIR = new ArrayList<String>();
        PARKIR.add("Tambah Data...");

        List<String> REKLAME = new ArrayList<String>();
        REKLAME.add("Tambah Data...");

        List<String> RESTORAN = new ArrayList<String>();
        RESTORAN.add("Tambah Data...");


        listDataChild.put(listDataHeader.get(0), PBB); // Header, Child data
        listDataChild.put(listDataHeader.get(1), HIBURAN);
        listDataChild.put(listDataHeader.get(2), HOTEL);
        listDataChild.put(listDataHeader.get(3), PARKIR);
        listDataChild.put(listDataHeader.get(4), REKLAME);
        listDataChild.put(listDataHeader.get(5), RESTORAN);
    }


}
