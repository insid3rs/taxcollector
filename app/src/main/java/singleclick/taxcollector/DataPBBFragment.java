package singleclick.taxcollector;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import singleclick.taxcollector.db.TaxCollectorDataSource;
import singleclick.taxcollector.db.TaxCollectorHelper;

public class DataPBBFragment extends Fragment {

    protected TaxCollectorDataSource mDataSource;
    protected String searchKey;
    protected String searchType;
    protected String objekUsahaPBB;
    protected String objekUsahaType;
    protected String coordinates;
    protected Cursor cursorSubjekPajak;
    protected TextView tvMaps;

    protected android.hardware.Camera camera;
    private int cameraId = 0;

    // Activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    private static final int CAMERA_PIC_REQUEST = 1111;
    private ImageView mImage;

    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "Location_Photo";

    private Uri fileUri; // file url to store image/video
    private ImageView imgPreview;
    private ImageAdapter imageAdapter;


    protected View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();

        searchKey = bundle.getString("searchKey");
        searchType = bundle.getString("searchType");
        objekUsahaType = bundle.getString("objekUsahaType");
        coordinates = bundle.getString("dataCoord");

        mDataSource = new TaxCollectorDataSource(getActivity());
        mDataSource.open();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_data_pbb, container, false);

        if(searchType.equals("NOP")) {
            cursorSubjekPajak = mDataSource.selectObjekPajakNOP(searchKey);
            updateLayoutObjekPajak(cursorSubjekPajak);
            normalMode();
        }else if(searchType.equals("tambahPBB")) {
            rootView.findViewById(R.id.layout_UpdateObjekPajakButton).setVisibility(View.GONE);
            rootView.findViewById(R.id.captureFront).setVisibility(View.GONE);
}
        /*else if(searchType.equals("NPWPD") && objekUsahaType.equalsIgnoreCase("hotel")){
            cursorSubjekPajak = mDataSource.selectHotelNPWPD(searchKey);
            updateLayoutObjekUsahaFindNIK(cursorSubjekPajak);
            normalMode();
        }else if(searchType.equals("NPWPD") && objekUsahaType.equalsIgnoreCase("parkir")){
            cursorSubjekPajak = mDataSource.selectParkirNPWPD(searchKey);
            updateLayoutObjekUsahaFindNIK(cursorSubjekPajak);
            normalMode();
        }*/

        //imgPreview = (ImageView) rootView.findViewById(R.id.imgPreview);
        rootView.findViewById(R.id.layout_UpdateObjekPajakButton).setVisibility(View.GONE);
        rootView.findViewById(R.id.captureFront).setVisibility(View.GONE);

        Button buttonUpdateObjekPajak = (Button) rootView.findViewById(R.id.button_UpdateObjekPajak);
        Button buttonAddObjekPajak = (Button) rootView.findViewById(R.id.button_AddObjekPajak);
        Button buttonCamera = (Button) rootView.findViewById(R.id.captureFront);


        refreshGridContent();


        buttonCamera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (!getActivity().getPackageManager()
                        .hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                    System.out.println("No camera on this device");
                } else {

                    /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

                    // start the image capture Intent
                    startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);*/

                    //mImage = (ImageView) rootView.findViewById(R.id.imgPreview);
                    //1
                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);

                }
            }
        });


        buttonUpdateObjekPajak.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mDataSource.open();

                mDataSource.updateObjekPajakNOP(searchKey,
                        ((EditText) rootView.findViewById(R.id.editText_DOP_JALAN_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DOP_BLOK_KAV_NO_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_NIK)).getText().toString(),
                        "0", // input_DOP_NO_FORMULIR_SPOP
                        "0", // input_DOP_NO_PERSIL
                        ((EditText) rootView.findViewById(R.id.editText_DOP_RW_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DOP_RT_WP)).getText().toString(),
                        "0", // input_DOP_KD_STATUS_CABANG
                        "0", // input_DOP_KD_STATUS_WP
                        ((EditText) rootView.findViewById(R.id.editText_LuasTanah)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_JumlahBangunan)).getText().toString(),
                        "0", // input_DOP_NJOP_BUMI
                        "0", // input_DOP_NJOP_BNG
                        "0", // input_DOP_STATUS_PETA_OP
                        "0", // input_DOP_JNS_TRANSAKSI_OP
                        "0", // input_DOP_TGL_PENDATAAN_OP
                        "0", // input_DOP_NIP_PENDATA
                        "0", // input_DOP_TGL_PEMERIKSAAN_OP
                        "0", // input_DOP_NIP_PEMERIKSA_OP
                        "0", // input_DOP_TGL_PEREKAMAN_OP
                        "0", // input_DOP_NIP_PEREKAM_OP
                        "0", // input_DOP_KD_UNIT
                        ((EditText) rootView.findViewById(R.id.editText_DOP_Map_Coordinate)).getText().toString()
                );

                normalMode();
            }
        });

        buttonAddObjekPajak.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mDataSource.open();

                mDataSource.addObjekPajak(
                        ((EditText) rootView.findViewById(R.id.editText_DSP_NOP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DOP_JALAN_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DOP_BLOK_KAV_NO_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_NIK)).getText().toString(),
                        "0", // input_DOP_NO_FORMULIR_SPOP
                        "0", // input_DOP_NO_PERSIL
                        ((EditText) rootView.findViewById(R.id.editText_DOP_RW_WP)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_DOP_RT_WP)).getText().toString(),
                        "0", // input_DOP_KD_STATUS_CABANG
                        "0", // input_DOP_KD_STATUS_WP
                        ((EditText) rootView.findViewById(R.id.editText_LuasTanah)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.editText_JumlahBangunan)).getText().toString(),
                        "0", // input_DOP_NJOP_BUMI
                        "0", // input_DOP_NJOP_BNG
                        "0", // input_DOP_STATUS_PETA_OP
                        "0", // input_DOP_JNS_TRANSAKSI_OP
                        "0", // input_DOP_TGL_PENDATAAN_OP
                        "0", // input_DOP_NIP_PENDATA
                        "0", // input_DOP_TGL_PEMERIKSAAN_OP
                        "0", // input_DOP_NIP_PEMERIKSA_OP
                        "0", // input_DOP_TGL_PEREKAMAN_OP
                        "0", // input_DOP_NIP_PEREKAM_OP
                        "0", // input_DOP_KD_UNIT
                        ((EditText) rootView.findViewById(R.id.editText_DOP_Map_Coordinate)).getText().toString()
                );

                normalMode();
            }
        });

        tvMaps = (TextView)rootView.findViewById(R.id.textView2);
        tvMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditableVectorFileMapActivity.class).putExtra("nop", ((EditText) rootView.findViewById(R.id.editText_DSP_NOP)).getText().toString()));
            }
        });

        ((EditText) rootView.findViewById(R.id.editText_DOP_Map_Coordinate)).setText(coordinates);

        return rootView;
    }

    private void refreshGridContent() {
        GridView gridview = (GridView) rootView.findViewById(R.id.gridview);

        imageAdapter = new ImageAdapter(getActivity());
        gridview.setAdapter(imageAdapter);

        String ExternalStorageDirectoryPath = Environment
                .getExternalStorageDirectory()
                .getAbsolutePath();
        String targetPath = ExternalStorageDirectoryPath + "/DCIM/Camera";

        Toast.makeText(getActivity().getApplicationContext(), targetPath, Toast.LENGTH_LONG).show();
        File targetDirector = new File(targetPath);

        File[] files = targetDirector.listFiles();

        System.out.println(files.length);

        for (File file : files){
            if(file.getName().substring(0,searchKey.length()).equals(searchKey)){
                imageAdapter.add(file.getAbsolutePath());
                System.out.println(file.getName().substring(0,searchKey.length()));
                System.out.println(searchKey);
                System.out.println(file.getName().toString());
                System.out.println(file.getAbsolutePath());
            }
        }
    }
/*
    private void updateLayoutObjekUsahaFindNIK(Cursor cursorSubjekPajak) {
        cursorSubjekPajak.moveToFirst();
        while( !cursorSubjekPajak.isAfterLast() ) {
            String DOU_NO_PBB = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOU_NO_PBB));

            cursorSubjekPajak = mDataSource.selectObjekPajakNOP(DOU_NO_PBB);
            updateLayoutObjekPajak(cursorSubjekPajak);

            normalMode();

            cursorSubjekPajak.moveToNext();
        }
        normalMode();
    }
*/

    /**
     * Receiving activity result method will be called after closing the camera
     * */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                //previewCapturedImage();

                //2

                //imgPreview.setVisibility(View.VISIBLE);
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                //imgPreview.setImageBitmap(thumbnail);
                //3
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                //4

                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());


                File file = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera" + File.separator
                        + searchKey +"_"+ timeStamp + ".jpg");
                try {
                    file.createNewFile();
                    FileOutputStream fo = new FileOutputStream(file);
                    //5
                    fo.write(bytes.toByteArray());
                    fo.close();

                    System.out.println("FIle created "+ file.getName());
                    //System.out.println("FIle location "+ file.getAbsolutePath());

                    refreshGridContent();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                refreshGridContent();
            }else if (resultCode == Activity.RESULT_CANCELED) {
                // user cancelled Image capture
                System.out.println("User cancelled image capture");
            } else {
                // failed to capture image
                System.out.println("Sorry! Failed to capture image");
            }
        }
    }

    /**
     * Here we store the file url as it will be null after returning from camera
     * app
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    /*
     * Here we restore the fileUri again

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }*/


    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /*
     * returning image / video
     */
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        System.out.println(mediaStorageDir.toString());

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                System.out.println("Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        }  else {
            return null;
        }

        return mediaFile;
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
            //String DSP_NOP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DSP_NOP));
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
            //((EditText) rootView.findViewById(R.id.editText_DSP_NOP)).setText(DSP_NOP);
            ((EditText) rootView.findViewById(R.id.editText_DSP_RT_WP)).setText(DSP_RT_WP);
            ((EditText) rootView.findViewById(R.id.editText_DSP_RW_WP)).setText(DSP_RW_WP);
            ((EditText) rootView.findViewById(R.id.editText_DSP_NPWP)).setText(DSP_NPWP);
            //((EditText) rootView.findViewById(R.id.editText_DSP_STATUS_PEKERJAAN_WP)).setText(DSP_STATUS_PEKERJAAN_WP);
            ((EditText) rootView.findViewById(R.id.editText_DSP_TELP_WP)).setText(DSP_TELP_WP);

            cursorSubjekPajak.moveToNext();

            normalMode();
        }
    }

    private void updateLayoutObjekPajak(Cursor cursorSubjekPajak) {
        cursorSubjekPajak.moveToFirst();
        while( !cursorSubjekPajak.isAfterLast() ) {
            // do stuff
            String WPNIK = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID));
            String DOP_KD_PROPINSI = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_KD_PROPINSI));
            String DOP_KD_DATI2 = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_KD_DATI2));
            String DOP_KD_KECAMATAN = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_KD_KECAMATAN));
            String DOP_KD_KELURAHAN = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_KD_KELURAHAN));
            String DOP_KD_BLOK = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_KD_BLOK));
            String DOP_NO_URUT = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_NO_URUT));
            String DOP_KD_JNS_OP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_KD_JNS_OP));
            String DOP_JALAN_OP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_JALAN_OP));
            String DOP_BLOK_KAV_NO_OP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_BLOK_KAV_NO_OP));
            String DSP_SUBJEK_PAJAK_ID = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID));
            String DOP_NO_FORMULIR_SPOP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_NO_FORMULIR_SPOP));
            String DOP_NO_PERSIL = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_NO_PERSIL));
            String DOP_RW_OP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_RW_OP));
            String DOP_RT_OP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_RT_OP));
            String DOP_KD_STATUS_CABANG = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_KD_STATUS_CABANG));
            String DOP_KD_STATUS_WP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_KD_STATUS_WP));
            String DOP_TOTAL_LUAS_BUMI = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_TOTAL_LUAS_BUMI));
            String DOP_TOTAL_LUAS_BNG = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_TOTAL_LUAS_BNG));
            String DOP_NJOP_BUMI = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_NJOP_BUMI));
            String DOP_NJOP_BNG = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_NJOP_BNG));
            String DOP_STATUS_PETA_OP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_STATUS_PETA_OP));
            String DOP_JNS_TRANSAKSI_OP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_JNS_TRANSAKSI_OP));
            String DOP_TGL_PENDATAAN_OP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_TGL_PENDATAAN_OP));
            String DOP_NIP_PENDATA = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_NIP_PENDATA));
            String DOP_TGL_PEMERIKSAAN_OP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_TGL_PEMERIKSAAN_OP));
            String DOP_NIP_PEMERIKSA_OP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_NIP_PEMERIKSA_OP));
            String DOP_TGL_PEREKAMAN_OP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_TGL_PEREKAMAN_OP));
            String DOP_NIP_PEREKAM_OP = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_NIP_PEREKAM_OP));
            String DOP_KD_UNIT = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_KD_UNIT));
            String DOP_PETA = cursorSubjekPajak.getString(cursorSubjekPajak.getColumnIndex(TaxCollectorHelper.DOP_PETA));

            ((EditText) rootView.findViewById(R.id.editText_DOP_Map_Coordinate)).setText(DOP_PETA);

            ((EditText) rootView.findViewById(R.id.editText_NIK)).setText(WPNIK);
            ((EditText) rootView.findViewById(R.id.editText_DSP_NOP)).setText(DOP_KD_PROPINSI + DOP_KD_DATI2 + DOP_KD_KECAMATAN + DOP_KD_KELURAHAN + DOP_KD_BLOK + DOP_NO_URUT + DOP_KD_JNS_OP);
            ((EditText) rootView.findViewById(R.id.editText_DOP_JALAN_WP)).setText(DOP_JALAN_OP);
            ((EditText) rootView.findViewById(R.id.editText_DOP_BLOK_KAV_NO_WP)).setText(DOP_BLOK_KAV_NO_OP);
            ((EditText) rootView.findViewById(R.id.editText_DOP_RW_WP)).setText(DOP_RW_OP);
            ((EditText) rootView.findViewById(R.id.editText_DOP_RT_WP)).setText(DOP_RT_OP);
            ((EditText) rootView.findViewById(R.id.editText_DOP_KELURAHAN_WP)).setText(DOP_KD_KELURAHAN);
            ((EditText) rootView.findViewById(R.id.editText_DOP_Kecamatan)).setText(DOP_KD_KECAMATAN);

            ((EditText) rootView.findViewById(R.id.editText_LuasTanah)).setText(DOP_TOTAL_LUAS_BUMI);
            ((EditText) rootView.findViewById(R.id.editText_JumlahBangunan)).setText(DOP_TOTAL_LUAS_BNG);
            /*((EditText) rootView.findViewById(R.id.editText_ZNT)).setText(DOP_KD_KECAMATAN);
            ((EditText) rootView.findViewById(R.id.editText_JumlahOPBB)).setText(DOP_KD_KECAMATAN);
            ((EditText) rootView.findViewById(R.id.editText_TanahKosong)).setText(DOP_KD_KECAMATAN);
            ((EditText) rootView.findViewById(R.id.editText_Kavling)).setText(DOP_KD_KECAMATAN);
            ((EditText) rootView.findViewById(R.id.editText_TanahdanBangunan)).setText(DOP_KD_KECAMATAN);
            ((EditText) rootView.findViewById(R.id.editText_FasilitasUmum)).setText(DOP_KD_KECAMATAN);
*/


            cursorSubjekPajak = mDataSource.selectSubjekPajakNIK(WPNIK);
            updateLayoutSubjekPajak(cursorSubjekPajak);


            cursorSubjekPajak.moveToNext();

            normalMode();
        }
    }

    private void normalMode() {

        rootView.findViewById(R.id.layout_UpdateObjekPajakButton).setVisibility(View.GONE);
        rootView.findViewById(R.id.layout_AddObjekPajakButton).setVisibility(View.GONE);
        rootView.findViewById(R.id.captureFront).setVisibility(View.GONE);

        ((EditText) rootView.findViewById(R.id.editText_NIK)).setKeyListener(null);
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

        ((EditText) rootView.findViewById(R.id.editText_DOP_Map_Coordinate)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_NIK)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_DSP_NOP)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_DOP_JALAN_WP)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_DOP_BLOK_KAV_NO_WP)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_DOP_RW_WP)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_DOP_RT_WP)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_DOP_KELURAHAN_WP)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_DOP_Kecamatan)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_LuasTanah)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_JumlahBangunan)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_ZNT)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_JumlahOPBB)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_TanahKosong)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_Kavling)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_TanahdanBangunan)).setBackgroundResource(R.drawable.border_normal_text);
        ((EditText) rootView.findViewById(R.id.editText_FasilitasUmum)).setBackgroundResource(R.drawable.border_normal_text);

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

        ((EditText) rootView.findViewById(R.id.editText_DOP_Map_Coordinate)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_NIK)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_DSP_NOP)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_DOP_JALAN_WP)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_DOP_BLOK_KAV_NO_WP)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_DOP_RW_WP)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_DOP_RT_WP)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_DOP_KELURAHAN_WP)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_DOP_Kecamatan)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_LuasTanah)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_JumlahBangunan)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_ZNT)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_JumlahOPBB)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_TanahKosong)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_Kavling)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_TanahdanBangunan)).setKeyListener(null);
        ((EditText) rootView.findViewById(R.id.editText_FasilitasUmum)).setKeyListener(null);

    }

}
