package singleclick.taxcollector;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import singleclick.taxcollector.db.TaxCollectorDataSource;

public class DataLokasiFragment extends Fragment {

    protected TaxCollectorDataSource mDataSource;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_data_lokasi, container, false);

		return rootView;
	}
}
