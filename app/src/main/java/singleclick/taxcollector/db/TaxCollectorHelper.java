package singleclick.taxcollector.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nandha on 9/16/14.
 */
public class TaxCollectorHelper extends SQLiteOpenHelper {
    // cd /Applications/Android\ Studio.app
    // cd sdk/platform-tools/
    // ./adb -s 192.168.56.101:5555 shell
    // sqlite3 /data/data/singleclick.taxcollector/databases/WP.db

    /* Table Info */
    /* TABLE DAT_SUBJEK_PAJAK */
    public static final String DAT_SUBJEK_PAJAK = "DAT_SUBJEK_PAJAK";
    public static final String DSP_SUBJEK_PAJAK_ID = "SUBJEK_PAJAK_ID";
    public static final String DSP_NID_WP = "NID_WP";
    public static final String DSP_NM_WP = "NM_WP";
    public static final String DSP_JALAN_WP  = "JALAN_WP";
    public static final String DSP_BLOK_KAV_NO_WP = "BLOK_KAV_NO_WP";
    public static final String DSP_RW_WP = "RW_WP";
    public static final String DSP_RT_WP ="RT_WP";
    public static final String DSP_KELURAHAN_WP = "KELURAHAN_WP";
    public static final String DSP_KOTA_WP ="KOTA_WP";
    public static final String DSP_KD_POS_WP ="KD_POS_WP";
    public static final String DSP_TELP_WP ="TELP_WP";
    public static final String DSP_HP_WP ="HP_WP";
    public static final String DSP_EMAIL_WP ="EMAIL_WP";
    public static final String DSP_NPWP =  "NPWP";
    public static final String DSP_STATUS_PEKERJAAN_WP = "STATUS_PEKERJAAN_WP";
    public static final String DSP_NOP ="NOP";

    public static final String TABLE_SUBJEK_PAJAK = "TABLE_SUBJEK_PAJAK";
    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_NIK = "NIK";
    public static final String COLUMN_NOP = "NOP";
    public static final String COLUMN_NAME = "NAME";

    public static final String DB_NAME = "WP.db";
    private static final int DB_VERSION = 2;
    private static final String DB_CREATE =
            "CREATE TABLE " + TABLE_SUBJEK_PAJAK + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NIK + " INTEGER, " +
                    COLUMN_NOP + " INTEGER, " +
                    COLUMN_NAME + " INTEGER)";

    private static final String DB_ALTER =
            "ALTER TABLE " + TABLE_SUBJEK_PAJAK + " ADD COLUMN " + COLUMN_NAME + " INTEGER";


    public TaxCollectorHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DB_ALTER);
    }
}
