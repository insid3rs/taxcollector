package singleclick.taxcollector;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class DataParkirFragment extends Fragment {

    protected View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_data_parkir, container, false);

        rootView.findViewById(R.id.layout_UpdateSubjekPajakButton).setVisibility(View.GONE);
        rootView.findViewById(R.id.layout_AddSubjekPajakButton).setVisibility(View.GONE);


        return rootView;
    }
}
