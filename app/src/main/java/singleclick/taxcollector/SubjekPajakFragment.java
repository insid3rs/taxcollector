package singleclick.taxcollector;

import android.content.Intent;
import android.database.Cursor;
import android.location.GpsStatus;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.KeyListener;
import android.text.method.TextKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import singleclick.taxcollector.db.TaxCollectorDataSource;
import singleclick.taxcollector.db.TaxCollectorHelper;

public class SubjekPajakFragment extends Fragment {

    protected TaxCollectorDataSource mDataSource;
    protected String searchKey;
    protected String searchType;
    protected Cursor cursorSubjekPajak;

    protected View rootView;

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

        rootView = inflater.inflate(R.layout.fragment_subjek_pajak, container, false);

        if(searchType.equals("NIK") || searchType.equals("Nama Subjek Pajak")) {
            cursorSubjekPajak = mDataSource.selectSubjekPajakNIK(searchKey);
            updateLayoutSubjekPajak(cursorSubjekPajak);
            normalMode();
        }else if(searchType.equals("tambahSubjekPajak")) {
            rootView.findViewById(R.id.layout_UpdateSubjekPajakButton).setVisibility(View.GONE);
        }

        Button buttonUpdateSubjekPajak = (Button) rootView.findViewById(R.id.button_UpdateSubjekPajak);
        Button buttonAddSubjekPajak = (Button) rootView.findViewById(R.id.button_AddSubjekPajak);

        buttonUpdateSubjekPajak.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mDataSource.open();

                mDataSource.updateSubjekPajakNIK(searchKey,
                        ((EditText) rootView.findViewById(R.id.editText_DSP_NOP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_NIK)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DSP_Nama)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DSP_BLOK_KAV_NO_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DSP_EMAIL_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DSP_HP_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DSP_JALAN_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DSP_KD_POS_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DSP_KELURAHAN_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DSP_KOTA_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DSP_RT_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DSP_RW_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DSP_NPWP)).getText().toString(),
                        //((EditText) rootView.findViewById(R.id.editText_DSP_STATUS_PEKERJAAN_WP)).setText(DSP_STATUS_PEKERJAAN_WP);
                        ((EditText) rootView.findViewById(R.id.editText_DSP_TELP_WP)).getText().toString()
                );


                normalMode();
            }
        });

        buttonAddSubjekPajak.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mDataSource.open();

                mDataSource.addSubjekPajak(
                        ((EditText) rootView.findViewById(R.id.editText_DSP_NOP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_NIK)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DSP_Nama)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DSP_BLOK_KAV_NO_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DSP_EMAIL_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DSP_HP_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DSP_JALAN_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DSP_KD_POS_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DSP_KELURAHAN_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DSP_KOTA_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DSP_RT_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DSP_RW_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DSP_NPWP)).getText().toString(),
                        //((EditText) rootView.findViewById(R.id.editText_DSP_STATUS_PEKERJAAN_WP)).setText(DSP_STATUS_PEKERJAAN_WP);
                        ((EditText) rootView.findViewById(R.id.editText_DSP_TELP_WP)).getText().toString()
                );

                normalMode();
            }
        });



        return rootView;
    }

    private void updateLayoutSubjekPajak(Cursor cursorSubjekPajak) {
        cursorSubjekPajak.moveToFirst();
        while( !cursorSubjekPajak.isAfterLast() ) {
            // do stuff
            String WPNIK = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID));
            String DSP_NM_WP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DSP_NM_WP));
            String DSP_BLOK_KAV_NO_WP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DSP_BLOK_KAV_NO_WP));
            String DSP_EMAIL_WP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DSP_EMAIL_WP));
            String DSP_HP_WP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DSP_HP_WP));
            String DSP_JALAN_WP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DSP_JALAN_WP));
            String DSP_KD_POS_WP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DSP_KD_POS_WP));
            String DSP_KELURAHAN_WP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DSP_KELURAHAN_WP));
            String DSP_KOTA_WP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DSP_KOTA_WP));
            String DSP_NOP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DSP_NOP));
            String DSP_RT_WP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DSP_RT_WP));
            String DSP_RW_WP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DSP_RW_WP));
            String DSP_NPWP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DSP_NPWP));
            String DSP_STATUS_PEKERJAAN_WP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DSP_STATUS_PEKERJAAN_WP));
            String DSP_TELP_WP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DSP_TELP_WP));

            ((EditText) rootView.findViewById(R.id.editText_NIK)).setText(WPNIK);
            ((EditText) rootView.findViewById(R.id.editText_DSP_Nama)).setText(DSP_NM_WP);
            ((EditText) rootView.findViewById(R.id.editText_DSP_BLOK_KAV_NO_WP)).setText(DSP_BLOK_KAV_NO_WP);
            ((EditText) rootView.findViewById(R.id.editText_DSP_EMAIL_WP)).setText(DSP_EMAIL_WP);
            ((EditText) rootView.findViewById(R.id.editText_DSP_HP_WP)).setText(DSP_HP_WP);
            ((EditText) rootView.findViewById(R.id.editText_DSP_JALAN_WP)).setText(DSP_JALAN_WP);
            ((EditText) rootView.findViewById(R.id.editText_DSP_KD_POS_WP)).setText(DSP_KD_POS_WP);
            ((EditText) rootView.findViewById(R.id.editText_DSP_KELURAHAN_WP)).setText(DSP_KELURAHAN_WP);
            ((EditText) rootView.findViewById(R.id.editText_DSP_KOTA_WP)).setText(DSP_KOTA_WP);
            ((EditText) rootView.findViewById(R.id.editText_DSP_NOP)).setText(DSP_NOP);
            ((EditText) rootView.findViewById(R.id.editText_DSP_RT_WP)).setText(DSP_RT_WP);
            ((EditText) rootView.findViewById(R.id.editText_DSP_RW_WP)).setText(DSP_RW_WP);
            ((EditText) rootView.findViewById(R.id.editText_DSP_NPWP)).setText(DSP_NPWP);
            //((EditText) rootView.findViewById(R.id.editText_DSP_STATUS_PEKERJAAN_WP)).setText(DSP_STATUS_PEKERJAAN_WP);
            ((EditText) rootView.findViewById(R.id.editText_DSP_TELP_WP)).setText(DSP_TELP_WP);

            cursorSubjekPajak.moveToNext();

            normalMode();
        }
    }

    private void normalMode() {

        rootView.findViewById(R.id.layout_UpdateSubjekPajakButton).setVisibility(View.GONE);
        rootView.findViewById(R.id.layout_AddSubjekPajakButton).setVisibility(View.GONE);

        ((EditText) rootView.findViewById(R.id.editText_NIK)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_DSP_Nama)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_DSP_BLOK_KAV_NO_WP)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_DSP_EMAIL_WP)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_DSP_HP_WP)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_DSP_JALAN_WP)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_DSP_KD_POS_WP)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_DSP_KELURAHAN_WP)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_DSP_KOTA_WP)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_DSP_NOP)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_DSP_RT_WP)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_DSP_RW_WP)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_DSP_NPWP)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_DSP_TELP_WP)).setBackgroundResource(R.drawable.border_normal_text);


        ((EditText) rootView.findViewById(R.id.editText_NIK)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_DSP_Nama)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_DSP_BLOK_KAV_NO_WP)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_DSP_EMAIL_WP)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_DSP_HP_WP)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_DSP_JALAN_WP)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_DSP_KD_POS_WP)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_DSP_KELURAHAN_WP)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_DSP_KOTA_WP)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_DSP_NOP)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_DSP_RT_WP)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_DSP_RW_WP)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_DSP_NPWP)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_DSP_TELP_WP)).setKeyListener(null);

    }

}

