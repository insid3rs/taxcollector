package singleclick.taxcollector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SubjekPajakFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_subjek_pajak, container, false);

        //Bundle element = this.getArguments();
        //System.out.println("Bundle " + element.getString("title"));



		return rootView;
	}
}
