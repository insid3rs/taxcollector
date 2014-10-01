package singleclick.taxcollector.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.EditText;

import singleclick.taxcollector.R;

/**
 * Created by nandha on 9/16/14.
 */
public class TaxCollectorDataSource {

    private SQLiteDatabase mDatabase; //Actual DB
    private TaxCollectorHelper mTaxCollectorHelper; // Helper class for creating and opening the DB
    private Context mContext;

    public TaxCollectorDataSource (Context context) {
        mContext = context;
        mTaxCollectorHelper = new TaxCollectorHelper(mContext);
    }

    /*
     * Open the db. Will create if it doesn't exist
     */
    public void open() throws SQLException {
        mDatabase = mTaxCollectorHelper.getWritableDatabase();
    }

    /*
     * We always need to close our db connections
     */
    public void close() {
        mDatabase.close();
    }

    //Insert
    public void insertForecast(Object forecast) {
        mDatabase.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            //values.put(TaxCollectorHelper.COLUMN_NAME, "name");
            mDatabase.insert(TaxCollectorHelper.TABLE_SUBJEK_PAJAK, null, values);
            mDatabase.setTransactionSuccessful();
        }
        finally {
            mDatabase.endTransaction();
        }
    }

    /*
     * SELECT ALL SUBJEK PAJAK
     */
    public Cursor selectAllSubjekPajak() {
        Cursor cursor = mDatabase.query(
                TaxCollectorHelper.DAT_SUBJEK_PAJAK, // table
                new String[] { TaxCollectorHelper.DSP_NM_WP,
                        TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID,
                        TaxCollectorHelper.DSP_NOP,
                        TaxCollectorHelper.DSP_JALAN_WP,
                        TaxCollectorHelper.DSP_BLOK_KAV_NO_WP
                }, // column names
                null, // where clause
                null, // where params
                null, // groupby
                null, // having
                null  // orderby
        );

        return cursor;
    }

    /*
     * SELECT ALL OBJEK PAJAK
     */
    public Cursor selectAllObjekPajak() {
        Cursor cursor = mDatabase.query(
                TaxCollectorHelper.DAT_OBJEK_PAJAK, // table
                new String[] { TaxCollectorHelper.DOP_KD_PROPINSI,
                        TaxCollectorHelper.DOP_KD_DATI2,
                        TaxCollectorHelper.DOP_KD_KECAMATAN,
                        TaxCollectorHelper.DOP_KD_KELURAHAN,
                        TaxCollectorHelper.DOP_KD_BLOK,
                        TaxCollectorHelper.DOP_NO_URUT,
                        TaxCollectorHelper.DOP_KD_JNS_OP,
                        TaxCollectorHelper.DOP_JALAN_OP,
                        TaxCollectorHelper.DOP_BLOK_KAV_NO_OP,
                        TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID
                }, // column names
                null, // where clause
                null, // where params
                null, // groupby
                null, // having
                null  // orderby
        );

        return cursor;
    }

    /*
     * SELECT ALL OBJEK USAHA
     */
    public Cursor selectAllObjekUsaha() {
        Cursor cursor = mDatabase.query(
                TaxCollectorHelper.DAT_PROPERTY_USAHA, // table
                new String[] { TaxCollectorHelper.DOU_NM_OU,
                        TaxCollectorHelper.DOU_NPWPD,
                        TaxCollectorHelper.DOU_TIPE_OU,
                        TaxCollectorHelper.DSP_NOP}, // column names
                null, // where clause
                null, // where params
                null, // groupby
                null, // having
                null  // orderby
        );

        return cursor;
    }

    /*
     * SELECT SUBJEK PAJAK -->> NIK
     */
    public Cursor selectSubjekPajakNIK(String searchKey) {
        String whereClause = TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID + " like ?";
        Cursor cursor = mDatabase.query(
                TaxCollectorHelper.DAT_SUBJEK_PAJAK, // table
                new String[] { TaxCollectorHelper.DSP_NM_WP,
                        TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID,
                        TaxCollectorHelper.DSP_BLOK_KAV_NO_WP,
                        TaxCollectorHelper.DSP_EMAIL_WP,
                        TaxCollectorHelper.DSP_HP_WP,
                        TaxCollectorHelper.DSP_JALAN_WP,
                        TaxCollectorHelper.DSP_KD_POS_WP,
                        TaxCollectorHelper.DSP_KELURAHAN_WP,
                        TaxCollectorHelper.DSP_KOTA_WP,
                        TaxCollectorHelper.DSP_NID_WP,
                        TaxCollectorHelper.DSP_NOP,
                        TaxCollectorHelper.DSP_RT_WP,
                        TaxCollectorHelper.DSP_RW_WP,
                        TaxCollectorHelper.DSP_NPWP,
                        TaxCollectorHelper.DSP_STATUS_PEKERJAAN_WP,
                        TaxCollectorHelper.DSP_TELP_WP}, // column names
                whereClause, // where clause
                new String[] { "%"+ searchKey +"%" }, // where params
                null, // groupby
                null, // having
                null  // orderby
        );

        return cursor;
    }

    /*
     * SELECT SUBJEK PAJAK -->> NOP
     */
    public Cursor selectSubjekPajakNOP(String searchKey) {
        String whereClause = TaxCollectorHelper.DSP_NOP + " like ?";

        Cursor cursor = mDatabase.query(
                TaxCollectorHelper.DAT_SUBJEK_PAJAK, // table
                new String[] { TaxCollectorHelper.DSP_NM_WP,
                        TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID,
                        TaxCollectorHelper.DSP_BLOK_KAV_NO_WP,
                        TaxCollectorHelper.DSP_EMAIL_WP,
                        TaxCollectorHelper.DSP_HP_WP,
                        TaxCollectorHelper.DSP_JALAN_WP,
                        TaxCollectorHelper.DSP_KD_POS_WP,
                        TaxCollectorHelper.DSP_KELURAHAN_WP,
                        TaxCollectorHelper.DSP_KOTA_WP,
                        TaxCollectorHelper.DSP_NID_WP,
                        TaxCollectorHelper.DSP_NOP,
                        TaxCollectorHelper.DSP_RT_WP,
                        TaxCollectorHelper.DSP_RW_WP,
                        TaxCollectorHelper.DSP_NPWP,
                        TaxCollectorHelper.DSP_STATUS_PEKERJAAN_WP,
                        TaxCollectorHelper.DSP_TELP_WP}, // column names
                whereClause, // where clause
                new String[] { "%"+ searchKey +"%" }, // where params
                null, // groupby
                null, // having
                null  // orderby
        );

        return cursor;
    }

    /*
     * SELECT SUBJEK PAJAK -->> NAMA WP
     */
    public Cursor selectSubjekPajakNamaWP(String searchKey) {
        String whereClause = TaxCollectorHelper.DSP_NM_WP + " like ?";

        Cursor cursor = mDatabase.query(
                TaxCollectorHelper.DAT_SUBJEK_PAJAK, // table
                new String[] { TaxCollectorHelper.DSP_NM_WP,
                        TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID,
                        TaxCollectorHelper.DSP_BLOK_KAV_NO_WP,
                        TaxCollectorHelper.DSP_EMAIL_WP,
                        TaxCollectorHelper.DSP_HP_WP,
                        TaxCollectorHelper.DSP_JALAN_WP,
                        TaxCollectorHelper.DSP_KD_POS_WP,
                        TaxCollectorHelper.DSP_KELURAHAN_WP,
                        TaxCollectorHelper.DSP_KOTA_WP,
                        TaxCollectorHelper.DSP_NID_WP,
                        TaxCollectorHelper.DSP_NOP,
                        TaxCollectorHelper.DSP_RT_WP,
                        TaxCollectorHelper.DSP_RW_WP,
                        TaxCollectorHelper.DSP_NPWP,
                        TaxCollectorHelper.DSP_STATUS_PEKERJAAN_WP,
                        TaxCollectorHelper.DSP_TELP_WP}, // column names
                whereClause, // where clause
                new String[] { "%"+ searchKey +"%" }, // where params
                null, // groupby
                null, // having
                null  // orderby
        );

        return cursor;
    }

    /*
     * SELECT OBJEK PAJAK -->> NOP
     */
    public Cursor selectObjekPajakNOP(String searchKey) {
        String whereClause = TaxCollectorHelper.DOP_KD_PROPINSI + " = ? AND " +
                                TaxCollectorHelper.DOP_KD_DATI2 + " = ? AND " +
                                TaxCollectorHelper.DOP_KD_KECAMATAN + " = ? AND " +
                                TaxCollectorHelper.DOP_KD_KELURAHAN + " = ? AND " +
                                TaxCollectorHelper.DOP_KD_BLOK + " = ? AND " +
                                TaxCollectorHelper.DOP_NO_URUT + " = ? AND " +
                                TaxCollectorHelper.DOP_KD_JNS_OP + " = ?";


        Cursor cursor = mDatabase.query(
                TaxCollectorHelper.DAT_OBJEK_PAJAK, // table
                new String[] { TaxCollectorHelper.DOP_KD_PROPINSI,
                        TaxCollectorHelper.DOP_KD_DATI2,
                        TaxCollectorHelper.DOP_KD_KECAMATAN,
                        TaxCollectorHelper.DOP_KD_KELURAHAN,
                        TaxCollectorHelper.DOP_KD_BLOK,
                        TaxCollectorHelper.DOP_NO_URUT,
                        TaxCollectorHelper.DOP_KD_JNS_OP,
                        TaxCollectorHelper.DOP_JALAN_OP,
                        TaxCollectorHelper.DOP_BLOK_KAV_NO_OP,
                        TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID}, // column names
                whereClause, // where clause
                new String[] { searchKey.substring(0, 2),
                        searchKey.substring(2, 4),
                        searchKey.substring(4, 7),
                        searchKey.substring(7, 10),
                        searchKey.substring(10, 13),
                        searchKey.substring(13, 17),
                        searchKey.substring(17, 18)}, // where params
                null, // groupby
                null, // having
                null  // orderby
        );

        return cursor;
    }

    /*
     * SELECT PROPERTI USAHA -->> NPWPD
     */
    public Cursor selectObjekUsahaNPWPD(String searchKey) {
        String whereClause = TaxCollectorHelper.DOU_NPWPD + " like ?";

        Cursor cursor = mDatabase.query(
                TaxCollectorHelper.DAT_PROPERTY_USAHA, // table
                new String[] { TaxCollectorHelper.DOU_NM_OU,
                        TaxCollectorHelper.DOU_NPWPD,
                        TaxCollectorHelper.DOU_TIPE_OU,
                        TaxCollectorHelper.DSP_NOP}, // column names
                whereClause, // where clause
                new String[] { "%"+ searchKey +"%" }, // where params
                null, // groupby
                null, // having
                null  // orderby
        );

        return cursor;
    }

    /*
     * SELECT PROPERTI USAHA -->> NOP
     */
    public Cursor selectObjekUsahaNOP(String searchKey) {
        String whereClause = TaxCollectorHelper.DSP_NOP + " = ?";

        Cursor cursor = mDatabase.query(
                TaxCollectorHelper.DAT_PROPERTY_USAHA, // table
                new String[] { TaxCollectorHelper.DOU_NM_OU,
                        TaxCollectorHelper.DOU_NPWPD,
                        TaxCollectorHelper.DOU_TIPE_OU,
                        TaxCollectorHelper.DSP_NOP}, // column names
                whereClause, // where clause
                new String[] { searchKey }, // where params
                null, // groupby
                null, // having
                null  // orderby
        );

        return cursor;
    }

    /*
     * SELECT OBJEK USAHA HOTEL -->> NPWPD
     */
    public Cursor selectHotelNPWPD(String searchKey) {
        String whereClause = TaxCollectorHelper.DOU_NPWPD + " = ?";


        Cursor cursor = mDatabase.query(
                TaxCollectorHelper.DAT_OU_HOTEL, // table
                new String[] { TaxCollectorHelper.DOU_NPWPD,
                        TaxCollectorHelper.DSP_NOP,
                        TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID,
                        TaxCollectorHelper.DOU_KLASIFIKASI_OU,
                        TaxCollectorHelper.DOU_NM_OU,
                        TaxCollectorHelper.DOU_TLP_OU,
                        TaxCollectorHelper.DOU_EMAIL_OU,
                        TaxCollectorHelper.DOU_JALAN_OU,
                        TaxCollectorHelper.DOU_NO_PBB,
                        TaxCollectorHelper.DOU_NO_IZIN_OU,
                        TaxCollectorHelper.DOU_TGL_IZIN,
                        TaxCollectorHelper.DOU_JNS_PEGAWAI_OU,
                        TaxCollectorHelper.DOU_TARIF_OU,
                        TaxCollectorHelper.DOU_KD_FASILITAS}, // column names
                whereClause, // where clause
                new String[] { searchKey }, // where params
                null, // groupby
                null, // having
                null  // orderby
        );

        return cursor;
    }

    /*
     * UPDATE SUBJEK PAJAK NOP
     */
    public int updateSubjekPajakNOP(String inputSearchKey,
                                    String input_DSP_NOP,
                                    String input_DSP_NID_WP,
                                    String input_DSP_Nama,
                                    String input_DSP_BLOK_KAV_NO_WP,
                                    String input_DSP_EMAIL_WP,
                                    String input_DSP_HP_WP,
                                    String input_DSP_JALAN_WP,
                                    String input_DSP_KD_POS_WP,
                                    String input_DSP_KELURAHAN_WP,
                                    String input_DSP_KOTA_WP,
                                    String input_DSP_RT_WP,
                                    String input_DSP_RW_WP,
                                    String input_DSP_NPWP,
                                    String input_DSP_TELP_WP) {


        ContentValues values = new ContentValues();
        values.put(TaxCollectorHelper.DSP_NOP, input_DSP_NOP);
        values.put(TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID, input_DSP_NID_WP);
        values.put(TaxCollectorHelper.DSP_NM_WP, input_DSP_Nama);
        values.put(TaxCollectorHelper.DSP_BLOK_KAV_NO_WP, input_DSP_BLOK_KAV_NO_WP);
        values.put(TaxCollectorHelper.DSP_EMAIL_WP, input_DSP_EMAIL_WP);
        values.put(TaxCollectorHelper.DSP_HP_WP, input_DSP_HP_WP);
        values.put(TaxCollectorHelper.DSP_JALAN_WP, input_DSP_JALAN_WP);
        values.put(TaxCollectorHelper.DSP_KD_POS_WP, input_DSP_KD_POS_WP);
        values.put(TaxCollectorHelper.DSP_KELURAHAN_WP, input_DSP_KELURAHAN_WP);
        values.put(TaxCollectorHelper.DSP_KOTA_WP, input_DSP_KOTA_WP);
        values.put(TaxCollectorHelper.DSP_RT_WP, input_DSP_RT_WP);
        values.put(TaxCollectorHelper.DSP_RW_WP, input_DSP_RW_WP);
        values.put(TaxCollectorHelper.DSP_NPWP, input_DSP_NPWP);
        values.put(TaxCollectorHelper.DSP_TELP_WP, input_DSP_TELP_WP);

        String whereClause = TaxCollectorHelper.DSP_NOP + " = ?";

        int rowsUpdated = mDatabase.update(
                TaxCollectorHelper.DAT_SUBJEK_PAJAK, // table
                values, // values
                whereClause,   // where clause
                new String[] { inputSearchKey }    // where params
        );

        return rowsUpdated;
    }

    /*
     * UPDATE SUBJEK PAJAK NIK
     */
    public int updateSubjekPajakNIK(String inputSearchKey,
                                    String input_DSP_NOP,
                                    String input_DSP_NID_WP,
                                    String input_DSP_Nama,
                                    String input_DSP_BLOK_KAV_NO_WP,
                                    String input_DSP_EMAIL_WP,
                                    String input_DSP_HP_WP,
                                    String input_DSP_JALAN_WP,
                                    String input_DSP_KD_POS_WP,
                                    String input_DSP_KELURAHAN_WP,
                                    String input_DSP_KOTA_WP,
                                    String input_DSP_RT_WP,
                                    String input_DSP_RW_WP,
                                    String input_DSP_NPWP,
                                    String input_DSP_TELP_WP) {


        ContentValues values = new ContentValues();
        values.put(TaxCollectorHelper.DSP_NOP, input_DSP_NOP);
        values.put(TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID, input_DSP_NID_WP);
        values.put(TaxCollectorHelper.DSP_NM_WP, input_DSP_Nama);
        values.put(TaxCollectorHelper.DSP_BLOK_KAV_NO_WP, input_DSP_BLOK_KAV_NO_WP);
        values.put(TaxCollectorHelper.DSP_EMAIL_WP, input_DSP_EMAIL_WP);
        values.put(TaxCollectorHelper.DSP_HP_WP, input_DSP_HP_WP);
        values.put(TaxCollectorHelper.DSP_JALAN_WP, input_DSP_JALAN_WP);
        values.put(TaxCollectorHelper.DSP_KD_POS_WP, input_DSP_KD_POS_WP);
        values.put(TaxCollectorHelper.DSP_KELURAHAN_WP, input_DSP_KELURAHAN_WP);
        values.put(TaxCollectorHelper.DSP_KOTA_WP, input_DSP_KOTA_WP);
        values.put(TaxCollectorHelper.DSP_RT_WP, input_DSP_RT_WP);
        values.put(TaxCollectorHelper.DSP_RW_WP, input_DSP_RW_WP);
        values.put(TaxCollectorHelper.DSP_NPWP, input_DSP_NPWP);
        values.put(TaxCollectorHelper.DSP_TELP_WP, input_DSP_TELP_WP);

        String whereClause = TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID + " = ?";

        int rowsUpdated = mDatabase.update(
                TaxCollectorHelper.DAT_SUBJEK_PAJAK, // table
                values, // values
                whereClause,   // where clause
                new String[] { inputSearchKey }    // where params
        );

        return rowsUpdated;
    }

    /*
     * UPDATE OBJEK USAHA HOTEL
     */
    public int updateObjekUsahaHotel(String inputSearchKey,
                                     String input_DOU_NPWPD,
                                     String input_DSP_NOP,
                                     String input_DSP_SUBJEK_PAJAK_ID,
                                     String input_DOU_KLASIFIKASI_OU,
                                     String input_DOU_NM_OU,
                                     String input_DOU_TLP_OU,
                                     String input_DOU_EMAIL_OU,
                                     String input_DOU_JALAN_OU,
                                     String input_DOU_NO_PBB,
                                     String input_DOU_NO_IZIN_OU,
                                     String input_DOU_TGL_IZIN,
                                     String input_DOU_JNS_PEGAWAI_OU,
                                     String input_DOU_TARIF_OU,
                                     String input_DOU_KD_FASILITAS) {


        ContentValues values = new ContentValues();
        values.put(TaxCollectorHelper.DOU_NPWPD, input_DOU_NPWPD);
        values.put(TaxCollectorHelper.DSP_NOP, input_DSP_NOP);
        values.put(TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID, input_DSP_SUBJEK_PAJAK_ID);
        values.put(TaxCollectorHelper.DOU_KLASIFIKASI_OU, input_DOU_KLASIFIKASI_OU);
        values.put(TaxCollectorHelper.DOU_NM_OU, input_DOU_NM_OU);
        values.put(TaxCollectorHelper.DOU_TLP_OU, input_DOU_TLP_OU);
        values.put(TaxCollectorHelper.DOU_EMAIL_OU, input_DOU_EMAIL_OU);
        values.put(TaxCollectorHelper.DOU_JALAN_OU, input_DOU_JALAN_OU);
        values.put(TaxCollectorHelper.DOU_NO_PBB, input_DOU_NO_PBB);
        values.put(TaxCollectorHelper.DOU_NO_IZIN_OU, input_DOU_NO_IZIN_OU);
        values.put(TaxCollectorHelper.DOU_TGL_IZIN, input_DOU_TGL_IZIN);
        values.put(TaxCollectorHelper.DOU_JNS_PEGAWAI_OU, input_DOU_JNS_PEGAWAI_OU);
        values.put(TaxCollectorHelper.DOU_TARIF_OU, input_DOU_TARIF_OU);
        values.put(TaxCollectorHelper.DOU_KD_FASILITAS, input_DOU_KD_FASILITAS);

        String whereClause = TaxCollectorHelper.DOU_NPWPD + " = ?";

        int rowsUpdated = mDatabase.update(
                TaxCollectorHelper.DAT_OU_HOTEL, // table
                values, // values
                whereClause,   // where clause
                new String[] { inputSearchKey }    // where params
        );

        values = new ContentValues();
        values.put(TaxCollectorHelper.DOU_NM_OU, input_DOU_NM_OU);
        values.put(TaxCollectorHelper.DOU_NPWPD, input_DOU_NPWPD);
        values.put(TaxCollectorHelper.DOU_TIPE_OU, "HOTEL");
        values.put(TaxCollectorHelper.DSP_NOP, input_DSP_NOP);

        whereClause = TaxCollectorHelper.DOU_NPWPD + " = ?";

        rowsUpdated = mDatabase.update(
                TaxCollectorHelper.DAT_PROPERTY_USAHA, // table
                values, // values
                whereClause,   // where clause
                new String[] { inputSearchKey }    // where params
        );

        return rowsUpdated;
    }

    /*
     * INSERT SUBJEK PAJAK
     */
    public void addSubjekPajak(String input_DSP_NOP,
                                    String input_DSP_NID_WP,
                                    String input_DSP_Nama,
                                    String input_DSP_BLOK_KAV_NO_WP,
                                    String input_DSP_EMAIL_WP,
                                    String input_DSP_HP_WP,
                                    String input_DSP_JALAN_WP,
                                    String input_DSP_KD_POS_WP,
                                    String input_DSP_KELURAHAN_WP,
                                    String input_DSP_KOTA_WP,
                                    String input_DSP_RT_WP,
                                    String input_DSP_RW_WP,
                                    String input_DSP_NPWP,
                                    String input_DSP_TELP_WP) {

        mDatabase.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(TaxCollectorHelper.DSP_NOP, input_DSP_NOP);
            values.put(TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID, input_DSP_NID_WP);
            values.put(TaxCollectorHelper.DSP_NID_WP, "0");
            values.put(TaxCollectorHelper.DSP_NM_WP, input_DSP_Nama);
            values.put(TaxCollectorHelper.DSP_BLOK_KAV_NO_WP, input_DSP_BLOK_KAV_NO_WP);
            values.put(TaxCollectorHelper.DSP_EMAIL_WP, input_DSP_EMAIL_WP);
            values.put(TaxCollectorHelper.DSP_HP_WP, input_DSP_HP_WP);
            values.put(TaxCollectorHelper.DSP_JALAN_WP, input_DSP_JALAN_WP);
            values.put(TaxCollectorHelper.DSP_KD_POS_WP, input_DSP_KD_POS_WP);
            values.put(TaxCollectorHelper.DSP_KELURAHAN_WP, input_DSP_KELURAHAN_WP);
            values.put(TaxCollectorHelper.DSP_KOTA_WP, input_DSP_KOTA_WP);
            values.put(TaxCollectorHelper.DSP_RT_WP, input_DSP_RT_WP);
            values.put(TaxCollectorHelper.DSP_RW_WP, input_DSP_RW_WP);
            values.put(TaxCollectorHelper.DSP_NPWP, input_DSP_NPWP);
            values.put(TaxCollectorHelper.DSP_TELP_WP, input_DSP_TELP_WP);

            mDatabase.insertOrThrow(
                    TaxCollectorHelper.DAT_SUBJEK_PAJAK, // table
                    null,
                    values // values
            );
            mDatabase.setTransactionSuccessful();
        }
        finally {
            mDatabase.endTransaction();
        }
    }

    /*
     * INSERT OBJEK USAHA HOTEL
     */
    public void addObjekUsahaHotel(String input_DOU_NPWPD,
                               String input_DSP_NOP,
                               String input_DSP_SUBJEK_PAJAK_ID,
                               String input_DOU_KLASIFIKASI_OU,
                               String input_DOU_NM_OU,
                               String input_DOU_TLP_OU,
                               String input_DOU_EMAIL_OU,
                               String input_DOU_JALAN_OU,
                               String input_DOU_NO_PBB,
                               String input_DOU_NO_IZIN_OU,
                               String input_DOU_TGL_IZIN,
                               String input_DOU_JNS_PEGAWAI_OU,
                               String input_DOU_TARIF_OU,
                               String input_DOU_KD_FASILITAS) {


        mDatabase.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(TaxCollectorHelper.DOU_NPWPD, input_DOU_NPWPD);
            values.put(TaxCollectorHelper.DSP_NOP, input_DSP_NOP);
            values.put(TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID, input_DSP_SUBJEK_PAJAK_ID);
            values.put(TaxCollectorHelper.DOU_KLASIFIKASI_OU, input_DOU_KLASIFIKASI_OU);
            values.put(TaxCollectorHelper.DOU_NM_OU, input_DOU_NM_OU);
            values.put(TaxCollectorHelper.DOU_TLP_OU, input_DOU_TLP_OU);
            values.put(TaxCollectorHelper.DOU_EMAIL_OU, input_DOU_EMAIL_OU);
            values.put(TaxCollectorHelper.DOU_JALAN_OU, input_DOU_JALAN_OU);
            values.put(TaxCollectorHelper.DOU_NO_PBB, input_DOU_NO_PBB);
            values.put(TaxCollectorHelper.DOU_NO_IZIN_OU, input_DOU_NO_IZIN_OU);
            values.put(TaxCollectorHelper.DOU_TGL_IZIN, input_DOU_TGL_IZIN);
            values.put(TaxCollectorHelper.DOU_JNS_PEGAWAI_OU, input_DOU_JNS_PEGAWAI_OU);
            values.put(TaxCollectorHelper.DOU_TARIF_OU, input_DOU_TARIF_OU);
            values.put(TaxCollectorHelper.DOU_KD_FASILITAS, input_DOU_KD_FASILITAS);

            mDatabase.insertOrThrow(
                    TaxCollectorHelper.DAT_OU_HOTEL, // table
                    null,
                    values // values
            );
            mDatabase.setTransactionSuccessful();
        }
        finally {
            mDatabase.endTransaction();
        }

        mDatabase.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(TaxCollectorHelper.DOU_NM_OU, input_DOU_NM_OU);
            values.put(TaxCollectorHelper.DOU_NPWPD, input_DOU_NPWPD);
            values.put(TaxCollectorHelper.DOU_TIPE_OU, "HOTEL");
            values.put(TaxCollectorHelper.DSP_NOP, input_DSP_NOP);

            mDatabase.insertOrThrow(
                    TaxCollectorHelper.DAT_PROPERTY_USAHA, // table
                    null,
                    values // values
            );
            mDatabase.setTransactionSuccessful();
        }
        finally {
            mDatabase.endTransaction();
        }

    }




}
