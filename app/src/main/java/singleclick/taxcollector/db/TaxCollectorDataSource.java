package singleclick.taxcollector.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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
                        TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID}, // column names
                null, // where clause
                null, // where params
                null, // groupby
                null, // having
                null  // orderby
        );

        return cursor;
    }

    /*
     * GET SUBJEK PAJAK
     */
    public Cursor selectSubjekPajak(String searchKey) {
        String whereClause = TaxCollectorHelper.DSP_SUBJEK_PAJAK_ID + " = ?";

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
                new String[] { searchKey }, // where params
                null, // groupby
                null, // having
                null  // orderby
        );

        return cursor;
    }

    /*
     * UPDATE
     */
    public int updateSubjekPajak(double newTemp) {
        ContentValues values = new ContentValues();
        values.put(TaxCollectorHelper.COLUMN_NAME, newTemp);
        int rowsUpdated = mDatabase.update(
                TaxCollectorHelper.TABLE_SUBJEK_PAJAK, // table
                values, // values
                null,   // where clause
                null    // where params
        );

        return rowsUpdated;
    }

}
