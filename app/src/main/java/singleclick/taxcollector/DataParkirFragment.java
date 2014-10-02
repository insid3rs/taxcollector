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

public class DataParkirFragment extends Fragment {

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

        rootView = inflater.inflate(R.layout.fragment_data_parkir, container, false);

        if(searchType.equals("NPWPD")) {
            cursorSubjekPajak = mDataSource.selectParkirNPWPD(searchKey);
            updateLayoutObjekUsaha(cursorSubjekPajak);
            normalMode();
        }else if(searchType.equals("tambahParkir")) {
            rootView.findViewById(R.id.layout_UpdateSubjekPajakButton).setVisibility(View.GONE);
        }

        Button buttonUpdateSubjekPajak = (Button) rootView.findViewById(R.id.button_UpdateSubjekPajak);
        Button buttonAddSubjekPajak = (Button) rootView.findViewById(R.id.button_AddSubjekPajak);

        buttonUpdateSubjekPajak.setText("Update Objek Usaha ( PARKIR )");
        buttonAddSubjekPajak.setText("Tambah Objek Usaha ( PARKIR )");


        buttonUpdateSubjekPajak.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mDataSource.open();

                mDataSource.updateObjekUsahaParkir(searchKey,
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
                        ((EditText) rootView.findViewById(R.id.editText_KapasitasMobil)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_KapasitasMotor)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_JamPertamaMobil)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_TiapJamMobil)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_MaksimumMobil)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_BulananMobil)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_JamPertamaMotor)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_TiapJamMotor)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_MaksimumMotor)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_BulananMotor)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_JamPertamaMobilBox)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_TiapJamMobilBox)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_MaksimumMobilBox)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_BulananMobilBox)).getText().toString()
                );

                normalMode();
            }
        });

        buttonAddSubjekPajak.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mDataSource.open();
                mDataSource.addObjekUsahaParkir(
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
                        ((EditText) rootView.findViewById(R.id.editText_KapasitasMobil)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_KapasitasMotor)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_JamPertamaMobil)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_TiapJamMobil)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_MaksimumMobil)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_BulananMobil)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_JamPertamaMotor)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_TiapJamMotor)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_MaksimumMotor)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_BulananMotor)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_JamPertamaMobilBox)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_TiapJamMobilBox)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_MaksimumMobilBox)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_BulananMobilBox)).getText().toString()
                );

                normalMode();
            }
        });


        return rootView;
    }

    private void updateLayoutObjekUsaha(Cursor cursor) {
        cursor.moveToFirst();
        while( !cursor.isAfterLast() ) {
            // do stuff

            String DOU_NPWPD = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_NPWPD));
            String DSP_NOP = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DSP_NOP));
            String DSP_SUBJEK_PAJAK_ID = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID));
            String DOU_KLASIFIKASI_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_KLASIFIKASI_OU));
            String DOU_NM_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_NM_OU));
            String DOU_TLP_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_TLP_OU));
            String DOU_EMAIL_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_EMAIL_OU));
            String DOU_JALAN_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_JALAN_OU));
            String DOU_NO_PBB = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_NO_PBB));
            String DOU_NO_IZIN_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_NO_IZIN_OU));
            String DOU_TGL_IZIN = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_TGL_IZIN));
            //String DOU_KD_FASILITAS = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOU_KD_FASILITAS));

            String DOU_KAPASITAS_MOBIL_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_KAPASITAS_MOBIL_OU));
            String DOU_KAPASITAS_MOTOR_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_KAPASITAS_MOTOR_OU));
            String DOU_TRF_MOBIL_JAM_PERTAMA_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_TRF_MOBIL_JAM_PERTAMA_OU));
            String DOU_TRF_MOBIL_JAM_SELANJUTNYA_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_TRF_MOBIL_JAM_SELANJUTNYA_OU));
            String DOU_TRF_MOBIL_MAX_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_TRF_MOBIL_MAX_OU));
            String DOU_TRF_MOBIL_BULANAN_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_TRF_MOBIL_BULANAN_OU));
            String DOU_TRF_MOTOR_JAM_PERTAMA_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_TRF_MOTOR_JAM_PERTAMA_OU));
            String DOU_TRF_MOTOR_JAM_SELANJUTNYA_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_TRF_MOTOR_JAM_SELANJUTNYA_OU));
            String DOU_TRF_MOTOR_MAX_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_TRF_MOTOR_MAX_OU));
            String DOU_TRF_MOTOR_BULANAN_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_TRF_MOTOR_BULANAN_OU));
            String DOU_TRF_BOX_JAM_PERTAMA_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_TRF_BOX_JAM_PERTAMA_OU));
            String DOU_TRF_BOX_JAM_SELANJUTNYA_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_TRF_BOX_JAM_SELANJUTNYA_OU));
            String DOU_TRF_BOX_MAX_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_TRF_BOX_MAX_OU));
            String DOU_TRF_BOX_BULANAN_OU = cursor.getString(cursor.getColumnIndex(TaxCollectorHelper.DOU_TRF_BOX_BULANAN_OU));


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

            ((EditText) rootView.findViewById(R.id.editText_KapasitasMobil)).setText(DOU_KAPASITAS_MOBIL_OU);
            ((EditText) rootView.findViewById(R.id.editText_KapasitasMotor)).setText(DOU_KAPASITAS_MOTOR_OU);
            ((EditText) rootView.findViewById(R.id.editText_JamPertamaMobil)).setText(DOU_TRF_MOBIL_JAM_PERTAMA_OU);
            ((EditText) rootView.findViewById(R.id.editText_TiapJamMobil)).setText(DOU_TRF_MOBIL_JAM_SELANJUTNYA_OU);
            ((EditText) rootView.findViewById(R.id.editText_MaksimumMobil)).setText(DOU_TRF_MOBIL_MAX_OU);
            ((EditText) rootView.findViewById(R.id.editText_BulananMobil)).setText(DOU_TRF_MOBIL_BULANAN_OU);
            ((EditText) rootView.findViewById(R.id.editText_JamPertamaMotor)).setText(DOU_TRF_MOTOR_JAM_PERTAMA_OU);
            ((EditText) rootView.findViewById(R.id.editText_TiapJamMotor)).setText(DOU_TRF_MOTOR_JAM_SELANJUTNYA_OU);
            ((EditText) rootView.findViewById(R.id.editText_MaksimumMotor)).setText(DOU_TRF_MOTOR_MAX_OU);
            ((EditText) rootView.findViewById(R.id.editText_BulananMotor)).setText(DOU_TRF_MOTOR_BULANAN_OU);
            ((EditText) rootView.findViewById(R.id.editText_JamPertamaMobilBox)).setText(DOU_TRF_BOX_JAM_PERTAMA_OU);
            ((EditText) rootView.findViewById(R.id.editText_TiapJamMobilBox)).setText(DOU_TRF_BOX_JAM_SELANJUTNYA_OU);
            ((EditText) rootView.findViewById(R.id.editText_MaksimumMobilBox)).setText(DOU_TRF_BOX_MAX_OU);
            ((EditText) rootView.findViewById(R.id.editText_BulananMobilBox)).setText(DOU_TRF_BOX_BULANAN_OU);


            cursor = mDataSource.selectSubjekPajakNIK(DSP_SUBJEK_PAJAK_ID);
            updateLayoutSubjekPajak(cursor);


            cursor.moveToNext();

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

        ((EditText) rootView.findViewById(R.id.editText_KapasitasMobil)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_KapasitasMotor)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_JamPertamaMobil)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_TiapJamMobil)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_MaksimumMobil)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_BulananMobil)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_JamPertamaMotor)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_TiapJamMotor)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_MaksimumMotor)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_BulananMotor)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_JamPertamaMobilBox)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_TiapJamMobilBox)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_MaksimumMobilBox)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_BulananMobilBox)).setBackgroundResource(R.drawable.border_normal_text);



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

        ((EditText) rootView.findViewById(R.id.editText_KapasitasMobil)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_KapasitasMotor)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_JamPertamaMobil)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_TiapJamMobil)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_MaksimumMobil)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_BulananMobil)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_JamPertamaMotor)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_TiapJamMotor)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_MaksimumMotor)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_BulananMotor)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_JamPertamaMobilBox)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_TiapJamMobilBox)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_MaksimumMobilBox)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_BulananMobilBox)).setKeyListener(null);


    }
}
