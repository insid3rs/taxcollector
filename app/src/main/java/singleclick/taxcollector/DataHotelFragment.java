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

public class DataHotelFragment extends Fragment {

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

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_data_hotel, container, false);

        if(searchType.equals("NPWPD")) {
            cursorSubjekPajak = mDataSource.selectHotelNPWPD(searchKey);
            updateLayoutObjekUsaha(cursorSubjekPajak);
            normalMode();
        }else if(searchType.equals("tambahHotel")) {
            rootView.findViewById(R.id.layout_UpdateSubjekPajakButton).setVisibility(View.GONE);
        }

        Button buttonUpdateSubjekPajak = (Button) rootView.findViewById(R.id.button_UpdateSubjekPajak);
        Button buttonAddSubjekPajak = (Button) rootView.findViewById(R.id.button_AddSubjekPajak);

        buttonUpdateSubjekPajak.setText("Update Objek Usaha ( HOTEL )");
        buttonAddSubjekPajak.setText("Tambah Objek Usaha ( HOTEL )");


        buttonUpdateSubjekPajak.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mDataSource.open();

                mDataSource.updateObjekUsahaHotel(searchKey,
                        ((EditText) rootView.findViewById(R.id.editText_OU_NPWPD)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_NOPPBBUsaha)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_OU_NIK)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_KlasifikasiObjek)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_NamaUsaha)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_NoTlp)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_EmailUsaha)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_AlamatUsaha)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_NOPPBBUsaha)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_NoIzinUsaha)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_TanggalUsaha)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_JumlahPegawai)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_Tarif)).getText().toString(),
                        "0"
                );

                normalMode();
            }
        });

        buttonAddSubjekPajak.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mDataSource.open();
                mDataSource.addObjekUsahaHotel(
                        ((EditText) rootView.findViewById(R.id.editText_OU_NPWPD)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_NOPPBBUsaha)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_OU_NIK)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_KlasifikasiObjek)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_NamaUsaha)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_NoTlp)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_EmailUsaha)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_AlamatUsaha)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_NOPPBBUsaha)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_NoIzinUsaha)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_TanggalUsaha)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_JumlahPegawai)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_Tarif)).getText().toString(),
                        "0"
                );

                normalMode();
            }
        });


        return rootView;
    }

    private void updateLayoutObjekUsaha(Cursor cursorSubjekPajak) {
        cursorSubjekPajak.moveToFirst();
        while( !cursorSubjekPajak.isAfterLast() ) {
            // do stuff


            String DOU_NPWPD = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOU_NPWPD));
            String DSP_NOP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DSP_NOP));
            String DSP_SUBJEK_PAJAK_ID = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID));
            String DOU_KLASIFIKASI_OU = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOU_KLASIFIKASI_OU));
            String DOU_NM_OU = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOU_NM_OU));
            String DOU_TLP_OU = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOU_TLP_OU));
            String DOU_EMAIL_OU = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOU_EMAIL_OU));
            String DOU_JALAN_OU = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOU_JALAN_OU));
            String DOU_NO_PBB = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOU_NO_PBB));
            String DOU_NO_IZIN_OU = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOU_NO_IZIN_OU));
            String DOU_TGL_IZIN = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOU_TGL_IZIN));
            String DOU_JNS_PEGAWAI_OU = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOU_JNS_PEGAWAI_OU));
            String DOU_TARIF_OU = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOU_TARIF_OU));
            //String DOU_KD_FASILITAS = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOU_KD_FASILITAS));

            ((EditText) rootView.findViewById(R.id.editText_OU_NPWPD)).setText(DOU_NPWPD);
            ((EditText) rootView.findViewById(R.id.editText_NOPPBBUsaha)).setText(DSP_NOP);
            ((EditText) rootView.findViewById(R.id.editText_OU_NIK)).setText(DSP_SUBJEK_PAJAK_ID);
            ((EditText) rootView.findViewById(R.id.editText_KlasifikasiObjek)).setText(DOU_KLASIFIKASI_OU);
            ((EditText) rootView.findViewById(R.id.editText_NamaUsaha)).setText(DOU_NM_OU);
            ((EditText) rootView.findViewById(R.id.editText_NoTlp)).setText(DOU_TLP_OU);
            ((EditText) rootView.findViewById(R.id.editText_EmailUsaha)).setText(DOU_EMAIL_OU);
            ((EditText) rootView.findViewById(R.id.editText_AlamatUsaha)).setText(DOU_JALAN_OU);
            ((EditText) rootView.findViewById(R.id.editText_NOPPBBUsaha)).setText(DOU_NO_PBB);
            ((EditText) rootView.findViewById(R.id.editText_NoIzinUsaha)).setText(DOU_NO_IZIN_OU);
            ((EditText) rootView.findViewById(R.id.editText_TanggalUsaha)).setText(DOU_TGL_IZIN);
            ((EditText) rootView.findViewById(R.id.editText_JumlahPegawai)).setText(DOU_JNS_PEGAWAI_OU);
            ((EditText) rootView.findViewById(R.id.editText_Tarif)).setText(DOU_TARIF_OU);

            cursorSubjekPajak = mDataSource.selectSubjekPajakNIK(DSP_SUBJEK_PAJAK_ID);
            updateLayoutSubjekPajak(cursorSubjekPajak);


            cursorSubjekPajak.moveToNext();

        }

        normalMode();
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

        ((EditText) rootView.findViewById(R.id.editText_OU_NIK)).setBackgroundResource(R.drawable.border_normal_text);
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


        ((EditText) rootView.findViewById(R.id.editText_OU_NIK)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_OU_NPWPD)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_NOPPBBUsaha)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_KlasifikasiObjek)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_NamaUsaha)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_NoTlp)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_EmailUsaha)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_AlamatUsaha)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_NOPPBBUsaha)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_NoIzinUsaha)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_TanggalUsaha)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_JumlahPegawai)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_Tarif)).setBackgroundResource(R.drawable.border_normal_text);


        ((EditText) rootView.findViewById(R.id.editText_OU_NIK)).setKeyListener(null);
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

        ((EditText) rootView.findViewById(R.id.editText_OU_NIK)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_OU_NPWPD)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_NOPPBBUsaha)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_KlasifikasiObjek)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_NamaUsaha)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_NoTlp)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_EmailUsaha)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_AlamatUsaha)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_NOPPBBUsaha)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_NoIzinUsaha)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_TanggalUsaha)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_JumlahPegawai)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_Tarif)).setKeyListener(null);

    }
}
