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
     * SELECT ALL
     */
    public Cursor selectAllSubjekPajak() {
        Cursor cursor = mDatabase.query(
                TaxCollectorHelper.DAT_SUBJEK_PAJAK, // table
                new String[] { TaxCollectorHelper.DSP_NM_WP,
                        TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID,
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
     * SELECT SUBJEK PAJAK -->> NOP
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
     * INSERT
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

}
