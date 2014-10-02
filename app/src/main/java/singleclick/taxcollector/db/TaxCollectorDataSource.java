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
                        TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID,
                        TaxCollectorHelper.DOP_PETA
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
                        TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID,
                        TaxCollectorHelper.DOP_NO_FORMULIR_SPOP,
                        TaxCollectorHelper.DOP_NO_PERSIL,
                        TaxCollectorHelper.DOP_RW_OP,
                        TaxCollectorHelper.DOP_RT_OP,
                        TaxCollectorHelper.DOP_KD_STATUS_CABANG,
                        TaxCollectorHelper.DOP_KD_STATUS_WP,
                        TaxCollectorHelper.DOP_TOTAL_LUAS_BUMI,
                        TaxCollectorHelper.DOP_TOTAL_LUAS_BNG,
                        TaxCollectorHelper.DOP_NJOP_BUMI,
                        TaxCollectorHelper.DOP_NJOP_BNG,
                        TaxCollectorHelper.DOP_STATUS_PETA_OP,
                        TaxCollectorHelper.DOP_JNS_TRANSAKSI_OP,
                        TaxCollectorHelper.DOP_TGL_PENDATAAN_OP,
                        TaxCollectorHelper.DOP_NIP_PENDATA,
                        TaxCollectorHelper.DOP_TGL_PEMERIKSAAN_OP,
                        TaxCollectorHelper.DOP_NIP_PEMERIKSA_OP,
                        TaxCollectorHelper.DOP_TGL_PEREKAMAN_OP,
                        TaxCollectorHelper.DOP_NIP_PEREKAM_OP,
                        TaxCollectorHelper.DOP_KD_UNIT,
                        TaxCollectorHelper.DOP_PETA
        }, // column names
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
     * SELECT OBJEK PAJAK -->> ALAMAT
     */
    public Cursor selectObjekPajakAlamat(String searchKey) {
        String whereClause = TaxCollectorHelper.DOP_JALAN_OP + " like ? ";


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
                        TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID,
                        TaxCollectorHelper.DOP_PETA}, // column names
                whereClause, // where clause
                new String[] { "%"+ searchKey +"%" }, // where params
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
     * SELECT OBJEK USAHA PARKIR -->> NPWPD
     */
    public Cursor selectParkirNPWPD(String searchKey) {
        String whereClause = TaxCollectorHelper.DOU_NPWPD + " = ?";


        Cursor cursor = mDatabase.query(
                TaxCollectorHelper.DAT_OU_PARKIR, // table
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
                        TaxCollectorHelper.DOU_KAPASITAS_MOBIL_OU,
                        TaxCollectorHelper.DOU_KAPASITAS_MOTOR_OU,
                        TaxCollectorHelper.DOU_TRF_MOBIL_JAM_PERTAMA_OU,
                        TaxCollectorHelper.DOU_TRF_MOBIL_JAM_SELANJUTNYA_OU,
                        TaxCollectorHelper.DOU_TRF_MOBIL_MAX_OU,
                        TaxCollectorHelper.DOU_TRF_MOBIL_BULANAN_OU,
                        TaxCollectorHelper.DOU_TRF_MOTOR_JAM_PERTAMA_OU,
                        TaxCollectorHelper.DOU_TRF_MOTOR_JAM_SELANJUTNYA_OU,
                        TaxCollectorHelper.DOU_TRF_MOTOR_MAX_OU,
                        TaxCollectorHelper.DOU_TRF_MOTOR_BULANAN_OU,
                        TaxCollectorHelper.DOU_TRF_BOX_JAM_PERTAMA_OU,
                        TaxCollectorHelper.DOU_TRF_BOX_JAM_SELANJUTNYA_OU,
                        TaxCollectorHelper.DOU_TRF_BOX_MAX_OU,
                        TaxCollectorHelper.DOU_TRF_BOX_BULANAN_OU
                }, // column names
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
     * UPDATE OBJEK PAJAK NOP
     */
    public int updateObjekPajakNOP(String searchKey,
                                   String input_DOP_JALAN_OP,
                                   String input_DOP_BLOK_KAV_NO_OP,
                                   String input_DSP_SUBJEK_PAJAK_ID,
                                   String input_DOP_NO_FORMULIR_SPOP,
                                   String input_DOP_NO_PERSIL,
                                   String input_DOP_RW_OP,
                                   String input_DOP_RT_OP,
                                   String input_DOP_KD_STATUS_CABANG,
                                   String input_DOP_KD_STATUS_WP,
                                   String input_DOP_TOTAL_LUAS_BUMI,
                                   String input_DOP_TOTAL_LUAS_BNG,
                                   String input_DOP_NJOP_BUMI,
                                   String input_DOP_NJOP_BNG,
                                   String input_DOP_STATUS_PETA_OP,
                                   String input_DOP_JNS_TRANSAKSI_OP,
                                   String input_DOP_TGL_PENDATAAN_OP,
                                   String input_DOP_NIP_PENDATA,
                                   String input_DOP_TGL_PEMERIKSAAN_OP,
                                   String input_DOP_NIP_PEMERIKSA_OP,
                                   String input_DOP_TGL_PEREKAMAN_OP,
                                   String input_DOP_NIP_PEREKAM_OP,
                                   String input_DOP_KD_UNIT,
                                   String input_DOP_PETA) {


        ContentValues values = new ContentValues();
        values.put(TaxCollectorHelper.DOP_KD_PROPINSI,  searchKey.substring(0, 2));
        values.put(TaxCollectorHelper.DOP_KD_DATI2,  searchKey.substring(2, 4));
        values.put(TaxCollectorHelper.DOP_KD_KECAMATAN, searchKey.substring(4, 7));
        values.put(TaxCollectorHelper.DOP_KD_KELURAHAN, searchKey.substring(7, 10));
        values.put(TaxCollectorHelper.DOP_KD_BLOK, searchKey.substring(10, 13));
        values.put(TaxCollectorHelper.DOP_NO_URUT, searchKey.substring(13, 17));
        values.put(TaxCollectorHelper.DOP_KD_JNS_OP, searchKey.substring(17, 18));
        values.put(TaxCollectorHelper.DOP_JALAN_OP, input_DOP_JALAN_OP);
        values.put(TaxCollectorHelper.DOP_BLOK_KAV_NO_OP, input_DOP_BLOK_KAV_NO_OP);
        values.put(TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID, input_DSP_SUBJEK_PAJAK_ID);
        values.put(TaxCollectorHelper.DOP_NO_FORMULIR_SPOP, input_DOP_NO_FORMULIR_SPOP);
        values.put(TaxCollectorHelper.DOP_NO_PERSIL, input_DOP_NO_PERSIL);
        values.put(TaxCollectorHelper.DOP_RW_OP, input_DOP_RW_OP);
        values.put(TaxCollectorHelper.DOP_RT_OP, input_DOP_RT_OP);
        values.put(TaxCollectorHelper.DOP_KD_STATUS_CABANG, input_DOP_KD_STATUS_CABANG);
        values.put(TaxCollectorHelper.DOP_KD_STATUS_WP, input_DOP_KD_STATUS_WP);
        values.put(TaxCollectorHelper.DOP_TOTAL_LUAS_BUMI, input_DOP_TOTAL_LUAS_BUMI);
        values.put(TaxCollectorHelper.DOP_TOTAL_LUAS_BNG, input_DOP_TOTAL_LUAS_BNG);
        values.put(TaxCollectorHelper.DOP_NJOP_BUMI, input_DOP_NJOP_BUMI);
        values.put(TaxCollectorHelper.DOP_NJOP_BNG, input_DOP_NJOP_BNG);
        values.put(TaxCollectorHelper.DOP_STATUS_PETA_OP, input_DOP_STATUS_PETA_OP);
        values.put(TaxCollectorHelper.DOP_JNS_TRANSAKSI_OP, input_DOP_JNS_TRANSAKSI_OP);
        values.put(TaxCollectorHelper.DOP_TGL_PENDATAAN_OP, input_DOP_TGL_PENDATAAN_OP);
        values.put(TaxCollectorHelper.DOP_NIP_PENDATA, input_DOP_NIP_PENDATA);
        values.put(TaxCollectorHelper.DOP_TGL_PEMERIKSAAN_OP, input_DOP_TGL_PEMERIKSAAN_OP);
        values.put(TaxCollectorHelper.DOP_NIP_PEMERIKSA_OP, input_DOP_NIP_PEMERIKSA_OP);
        values.put(TaxCollectorHelper.DOP_TGL_PEREKAMAN_OP, input_DOP_TGL_PEREKAMAN_OP);
        values.put(TaxCollectorHelper.DOP_NIP_PEREKAM_OP, input_DOP_NIP_PEREKAM_OP);
        values.put(TaxCollectorHelper.DOP_KD_UNIT, input_DOP_KD_UNIT);
        values.put(TaxCollectorHelper.DOP_PETA, input_DOP_PETA);


        String whereClause = TaxCollectorHelper.DOP_KD_PROPINSI + " = ? AND " +
                TaxCollectorHelper.DOP_KD_DATI2 + " = ? AND " +
                TaxCollectorHelper.DOP_KD_KECAMATAN + " = ? AND " +
                TaxCollectorHelper.DOP_KD_KELURAHAN + " = ? AND " +
                TaxCollectorHelper.DOP_KD_BLOK + " = ? AND " +
                TaxCollectorHelper.DOP_NO_URUT + " = ? AND " +
                TaxCollectorHelper.DOP_KD_JNS_OP + " = ?";

        int rowsUpdated = mDatabase.update(
                TaxCollectorHelper.DAT_OBJEK_PAJAK, // table
                values, // values
                whereClause,   // where clause
                new String[] { searchKey.substring(0, 2),
                        searchKey.substring(2, 4),
                        searchKey.substring(4, 7),
                        searchKey.substring(7, 10),
                        searchKey.substring(10, 13),
                        searchKey.substring(13, 17),
                        searchKey.substring(17, 18) }    // where params
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
     * UPDATE OBJEK USAHA PARKIR
     */
    public int updateObjekUsahaParkir(String inputSearchKey,
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
                                     String input_DOU_KAPASITAS_MOBIL_OU,
                                     String input_DOU_KAPASITAS_MOTOR_OU,
                                     String input_DOU_TRF_MOBIL_JAM_PERTAMA_OU,
                                     String input_DOU_TRF_MOBIL_JAM_SELANJUTNYA_OU,
                                     String input_DOU_TRF_MOBIL_MAX_OU,
                                     String input_DOU_TRF_MOBIL_BULANAN_OU,
                                     String input_DOU_TRF_MOTOR_JAM_PERTAMA_OU,
                                     String input_DOU_TRF_MOTOR_JAM_SELANJUTNYA_OU,
                                     String input_DOU_TRF_MOTOR_MAX_OU,
                                     String input_DOU_TRF_MOTOR_BULANAN_OU,
                                     String input_DOU_TRF_BOX_JAM_PERTAMA_OU,
                                     String input_DOU_TRF_BOX_JAM_SELANJUTNYA_OU,
                                     String input_DOU_TRF_BOX_MAX_OU,
                                     String input_DOU_TRF_BOX_BULANAN_OU
                                     ) {


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

        values.put(TaxCollectorHelper.DOU_KAPASITAS_MOBIL_OU, input_DOU_KAPASITAS_MOBIL_OU);
        values.put(TaxCollectorHelper.DOU_KAPASITAS_MOTOR_OU, input_DOU_KAPASITAS_MOTOR_OU);
        values.put(TaxCollectorHelper.DOU_TRF_MOBIL_JAM_PERTAMA_OU, input_DOU_TRF_MOBIL_JAM_PERTAMA_OU);
        values.put(TaxCollectorHelper.DOU_TRF_MOBIL_JAM_SELANJUTNYA_OU, input_DOU_TRF_MOBIL_JAM_SELANJUTNYA_OU);
        values.put(TaxCollectorHelper.DOU_TRF_MOBIL_MAX_OU, input_DOU_TRF_MOBIL_MAX_OU);
        values.put(TaxCollectorHelper.DOU_TRF_MOBIL_BULANAN_OU, input_DOU_TRF_MOBIL_BULANAN_OU);
        values.put(TaxCollectorHelper.DOU_TRF_MOTOR_JAM_PERTAMA_OU, input_DOU_TRF_MOTOR_JAM_PERTAMA_OU);
        values.put(TaxCollectorHelper.DOU_TRF_MOTOR_JAM_SELANJUTNYA_OU, input_DOU_TRF_MOTOR_JAM_SELANJUTNYA_OU);
        values.put(TaxCollectorHelper.DOU_TRF_MOTOR_MAX_OU, input_DOU_TRF_MOTOR_MAX_OU);
        values.put(TaxCollectorHelper.DOU_TRF_MOTOR_BULANAN_OU, input_DOU_TRF_MOTOR_BULANAN_OU);
        values.put(TaxCollectorHelper.DOU_TRF_BOX_JAM_PERTAMA_OU, input_DOU_TRF_BOX_JAM_PERTAMA_OU);
        values.put(TaxCollectorHelper.DOU_TRF_BOX_JAM_SELANJUTNYA_OU, input_DOU_TRF_BOX_JAM_SELANJUTNYA_OU);
        values.put(TaxCollectorHelper.DOU_TRF_BOX_MAX_OU, input_DOU_TRF_BOX_MAX_OU);
        values.put(TaxCollectorHelper.DOU_TRF_BOX_BULANAN_OU, input_DOU_TRF_BOX_BULANAN_OU);


        String whereClause = TaxCollectorHelper.DOU_NPWPD + " = ?";

        int rowsUpdated = mDatabase.update(
                TaxCollectorHelper.DAT_OU_PARKIR, // table
                values, // values
                whereClause,   // where clause
                new String[] { inputSearchKey }    // where params
        );

        values = new ContentValues();
        values.put(TaxCollectorHelper.DOU_NM_OU, input_DOU_NM_OU);
        values.put(TaxCollectorHelper.DOU_NPWPD, input_DOU_NPWPD);
        values.put(TaxCollectorHelper.DOU_TIPE_OU, "PARKIR");
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
     * INSERT OBJEK PAJAK
     */
    public void addObjekPajak(String input_DSP_NOP,
                               String input_DOP_JALAN_OP,
                               String input_DOP_BLOK_KAV_NO_OP,
                               String input_DSP_SUBJEK_PAJAK_ID,
                               String input_DOP_NO_FORMULIR_SPOP,
                               String input_DOP_NO_PERSIL,
                               String input_DOP_RW_OP,
                               String input_DOP_RT_OP,
                               String input_DOP_KD_STATUS_CABANG,
                               String input_DOP_KD_STATUS_WP,
                               String input_DOP_TOTAL_LUAS_BUMI,
                               String input_DOP_TOTAL_LUAS_BNG,
                               String input_DOP_NJOP_BUMI,
                               String input_DOP_NJOP_BNG,
                               String input_DOP_STATUS_PETA_OP,
                               String input_DOP_JNS_TRANSAKSI_OP,
                               String input_DOP_TGL_PENDATAAN_OP,
                               String input_DOP_NIP_PENDATA,
                               String input_DOP_TGL_PEMERIKSAAN_OP,
                               String input_DOP_NIP_PEMERIKSA_OP,
                               String input_DOP_TGL_PEREKAMAN_OP,
                               String input_DOP_NIP_PEREKAM_OP,
                               String input_DOP_KD_UNIT,
                               String input_DOP_PETA) {

        mDatabase.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(TaxCollectorHelper.DOP_KD_PROPINSI,  input_DSP_NOP.substring(0, 2));
            values.put(TaxCollectorHelper.DOP_KD_DATI2,  input_DSP_NOP.substring(2, 4));
            values.put(TaxCollectorHelper.DOP_KD_KECAMATAN, input_DSP_NOP.substring(4, 7));
            values.put(TaxCollectorHelper.DOP_KD_KELURAHAN, input_DSP_NOP.substring(7, 10));
            values.put(TaxCollectorHelper.DOP_KD_BLOK, input_DSP_NOP.substring(10, 13));
            values.put(TaxCollectorHelper.DOP_NO_URUT, input_DSP_NOP.substring(13, 17));
            values.put(TaxCollectorHelper.DOP_KD_JNS_OP, input_DSP_NOP.substring(17, 18));
            values.put(TaxCollectorHelper.DOP_JALAN_OP, input_DOP_JALAN_OP);
            values.put(TaxCollectorHelper.DOP_BLOK_KAV_NO_OP, input_DOP_BLOK_KAV_NO_OP);
            values.put(TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID, input_DSP_SUBJEK_PAJAK_ID);
            values.put(TaxCollectorHelper.DOP_NO_FORMULIR_SPOP, input_DOP_NO_FORMULIR_SPOP);
            values.put(TaxCollectorHelper.DOP_NO_PERSIL, input_DOP_NO_PERSIL);
            values.put(TaxCollectorHelper.DOP_RW_OP, input_DOP_RW_OP);
            values.put(TaxCollectorHelper.DOP_RT_OP, input_DOP_RT_OP);
            values.put(TaxCollectorHelper.DOP_KD_STATUS_CABANG, input_DOP_KD_STATUS_CABANG);
            values.put(TaxCollectorHelper.DOP_KD_STATUS_WP, input_DOP_KD_STATUS_WP);
            values.put(TaxCollectorHelper.DOP_TOTAL_LUAS_BUMI, input_DOP_TOTAL_LUAS_BUMI);
            values.put(TaxCollectorHelper.DOP_TOTAL_LUAS_BNG, input_DOP_TOTAL_LUAS_BNG);
            values.put(TaxCollectorHelper.DOP_NJOP_BUMI, input_DOP_NJOP_BUMI);
            values.put(TaxCollectorHelper.DOP_NJOP_BNG, input_DOP_NJOP_BNG);
            values.put(TaxCollectorHelper.DOP_STATUS_PETA_OP, input_DOP_STATUS_PETA_OP);
            values.put(TaxCollectorHelper.DOP_JNS_TRANSAKSI_OP, input_DOP_JNS_TRANSAKSI_OP);
            values.put(TaxCollectorHelper.DOP_TGL_PENDATAAN_OP, input_DOP_TGL_PENDATAAN_OP);
            values.put(TaxCollectorHelper.DOP_NIP_PENDATA, input_DOP_NIP_PENDATA);
            values.put(TaxCollectorHelper.DOP_TGL_PEMERIKSAAN_OP, input_DOP_TGL_PEMERIKSAAN_OP);
            values.put(TaxCollectorHelper.DOP_NIP_PEMERIKSA_OP, input_DOP_NIP_PEMERIKSA_OP);
            values.put(TaxCollectorHelper.DOP_TGL_PEREKAMAN_OP, input_DOP_TGL_PEREKAMAN_OP);
            values.put(TaxCollectorHelper.DOP_NIP_PEREKAM_OP, input_DOP_NIP_PEREKAM_OP);
            values.put(TaxCollectorHelper.DOP_KD_UNIT, input_DOP_KD_UNIT);
            values.put(TaxCollectorHelper.DOP_PETA, input_DOP_PETA);

            mDatabase.insertOrThrow(
                    TaxCollectorHelper.DAT_OBJEK_PAJAK, // table
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

    /*
     * INSERT OBJEK USAHA PARKIR
     */
    public void addObjekUsahaParkir(String input_DOU_NPWPD,
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
                                    String input_DOU_KAPASITAS_MOBIL_OU,
                                    String input_DOU_KAPASITAS_MOTOR_OU,
                                    String input_DOU_TRF_MOBIL_JAM_PERTAMA_OU,
                                    String input_DOU_TRF_MOBIL_JAM_SELANJUTNYA_OU,
                                    String input_DOU_TRF_MOBIL_MAX_OU,
                                    String input_DOU_TRF_MOBIL_BULANAN_OU,
                                    String input_DOU_TRF_MOTOR_JAM_PERTAMA_OU,
                                    String input_DOU_TRF_MOTOR_JAM_SELANJUTNYA_OU,
                                    String input_DOU_TRF_MOTOR_MAX_OU,
                                    String input_DOU_TRF_MOTOR_BULANAN_OU,
                                    String input_DOU_TRF_BOX_JAM_PERTAMA_OU,
                                    String input_DOU_TRF_BOX_JAM_SELANJUTNYA_OU,
                                    String input_DOU_TRF_BOX_MAX_OU,
                                    String input_DOU_TRF_BOX_BULANAN_OU) {


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

            values.put(TaxCollectorHelper.DOU_KAPASITAS_MOBIL_OU, input_DOU_KAPASITAS_MOBIL_OU);
            values.put(TaxCollectorHelper.DOU_KAPASITAS_MOTOR_OU, input_DOU_KAPASITAS_MOTOR_OU);
            values.put(TaxCollectorHelper.DOU_TRF_MOBIL_JAM_PERTAMA_OU, input_DOU_TRF_MOBIL_JAM_PERTAMA_OU);
            values.put(TaxCollectorHelper.DOU_TRF_MOBIL_JAM_SELANJUTNYA_OU, input_DOU_TRF_MOBIL_JAM_SELANJUTNYA_OU);
            values.put(TaxCollectorHelper.DOU_TRF_MOBIL_MAX_OU, input_DOU_TRF_MOBIL_MAX_OU);
            values.put(TaxCollectorHelper.DOU_TRF_MOBIL_BULANAN_OU, input_DOU_TRF_MOBIL_BULANAN_OU);
            values.put(TaxCollectorHelper.DOU_TRF_MOTOR_JAM_PERTAMA_OU, input_DOU_TRF_MOTOR_JAM_PERTAMA_OU);
            values.put(TaxCollectorHelper.DOU_TRF_MOTOR_JAM_SELANJUTNYA_OU, input_DOU_TRF_MOTOR_JAM_SELANJUTNYA_OU);
            values.put(TaxCollectorHelper.DOU_TRF_MOTOR_MAX_OU, input_DOU_TRF_MOTOR_MAX_OU);
            values.put(TaxCollectorHelper.DOU_TRF_MOTOR_BULANAN_OU, input_DOU_TRF_MOTOR_BULANAN_OU);
            values.put(TaxCollectorHelper.DOU_TRF_BOX_JAM_PERTAMA_OU, input_DOU_TRF_BOX_JAM_PERTAMA_OU);
            values.put(TaxCollectorHelper.DOU_TRF_BOX_JAM_SELANJUTNYA_OU, input_DOU_TRF_BOX_JAM_SELANJUTNYA_OU);
            values.put(TaxCollectorHelper.DOU_TRF_BOX_MAX_OU, input_DOU_TRF_BOX_MAX_OU);
            values.put(TaxCollectorHelper.DOU_TRF_BOX_BULANAN_OU, input_DOU_TRF_BOX_BULANAN_OU);

            mDatabase.insertOrThrow(
                    TaxCollectorHelper.DAT_OU_PARKIR, // table
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
            values.put(TaxCollectorHelper.DOU_TIPE_OU, "PARKIR");
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
