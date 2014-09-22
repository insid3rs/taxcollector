package singleclick.taxcollector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ObjekUsahaFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_objek_pajak, container, false);

        Button buttonPBB = (Button) rootView.findViewById(R.id.buttonPBB);
        Button buttonHiburan = (Button) rootView.findViewById(R.id.buttonHiburan);
        Button buttonHotel = (Button) rootView.findViewById(R.id.buttonHotel);
        Button buttonParkir = (Button) rootView.findViewById(R.id.buttonParkir);
        Button buttonRestoran = (Button) rootView.findViewById(R.id.buttonRestoran);

        buttonPBB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DataReklame.class);
                startActivity(intent);
            }
        });

        buttonHiburan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DataHiburan.class);
                startActivity(intent);
            }
        });

        buttonHotel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DataHotel.class);
                startActivity(intent);
            }
        });

        buttonParkir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DataParkir.class);
                startActivity(intent);
            }
        });

        buttonRestoran.setOnClickListener(new View.OnClickListener() {
            @Override
            //On click function
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DataRestoran.class);
                startActivity(intent);
            }
        });

		return rootView;
	}



}
