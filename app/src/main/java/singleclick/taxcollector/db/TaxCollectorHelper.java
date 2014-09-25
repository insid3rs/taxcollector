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

        db.execSQL("CREATE TABLE DAT_OBJEK_PAJAK ( " +
                "KD_PROPINSI TEXT NOT NULL, " +
                "KD_DATI2 TEXT NOT NULL, " +
                "KD_KECAMATAN TEXT NOT NULL, " +
                "KD_KELURAHAN TEXT NOT NULL, " +
                "KD_BLOK TEXT NOT NULL, " +
                "NO_URUT TEXT NOT NULL, " +
                "KD_JNS_OP TEXT NOT NULL, " +
                "SUBJEK_PAJAK_ID TEXT, " +
                "NO_FORMULIR_SPOP TEXT, " +
                "NO_PERSIL TEXT, " +
                "JALAN_OP TEXT, " +
                "BLOK_KAV_NO_OP TEXT, " +
                "RW_OP TEXT, " +
                "RT_OP TEXT, " +
                "KD_STATUS_CABANG INTEGER NOT NULL, " +
                "KD_STATUS_WP INTEGER NOT NULL, " +
                "TOTAL_LUAS_BUMI INTEGER NOT NULL, " +
                "TOTAL_LUAS_BNG INTEGER NOT NULL, " +
                "NJOP_BUMI INTEGER NOT NULL, " +
                "NJOP_BNG INTEGER NOT NULL, " +
                "STATUS_PETA_OP INTEGER NOT NULL, " +
                "JNS_TRANSAKSI_OP TEXT NOT NULL, " +
                "TGL_PENDATAAN_OP TEXT, " +
                "NIP_PENDATA TEXT, " +
                "GL_PEMERIKSAAN_OP TEXT, " +
                "NIP_PEMERIKSA_OP TEXT, " +
                "TGL_PEREKAMAN_OP TEXT NOT NULL, " +
                "NIP_PEREKAM_OP TEXT, " +
                "KD_UNIT TEXT)");

        db.execSQL("CREATE TABLE DAT_OP_BANGUNAN (" +
                "KD_PROPINSI TEXT NOT NULL, " +
                "KD_DATI2 TEXT NOT NULL, " +
                "KD_KECAMATAN TEXT NOT NULL, " +
                "KD_KELURAHAN TEXT NOT NULL, " +
                "KD_BLOK TEXT NOT NULL, " +
                "NO_URUT TEXT NOT NULL, " +
                "KD_JNS_OP TEXT NOT NULL, " +
                "NO_BNG TEXT NOT NULL, " +
                "KD_JPB TEXT, " +
                "NO_FORMULIR_LSPOP TEXT, " +
                "THN_DIBANGUN_BNG TEXT, " +
                "THN_RENOVASI_BNG TEXT, " +
                "LUAS_BNG INTEGER, " +
                "JML_LANTAI_BNG INTEGER, " +
                "KONDISI_BNG TEXT, " +
                "JNS_KONSTRUKSI_BNG TEXT, " +
                "JNS_ATAP_BNG TEXT, " +
                "KD_DINDING TEXT, " +
                "KD_LANTAI TEXT, " +
                "KD_LANGIT_LANGIT TEXT, " +
                "NILAI_SISTEM_BNG INTEGER, " +
                "JNS_TRANSAKSI_BNG TEXT, " +
                "TGL_PENDATAAN_BNG TEXT, " +
                "NIP_PENDATA_BNG TEXT, " +
                "TGL_PEMERIKSAAN_BNG TEXT, " +
                "NIP_PEMERIKSA_BNG TEXT, " +
                "TGL_PEREKAMAN_BNG TEXT, " +
                "NIP_PEREKAM_BNG TEXT, " +
                "KD_UNIT TEXT PCTFREE)");

        db.execSQL("CREATE TABLE DAT_OP_BUMI (" +
                "KD_PROPINSI TEXT NOT NULL, " +
                "KD_DATI2 TEXT NOT NULL, " +
                "KD_KECAMATAN TEXT NOT NULL, " +
                "KD_KELURAHAN TEXT NOT NULL, " +
                "KD_BLOK TEXT NOT NULL, " +
                "NO_URUT TEXT NOT NULL, " +
                "KD_JNS_OP TEXT NOT NULL, " +
                "NO_BUMI INTEGER NOT NULL, " +
                "KD_ZNT TEXT, " +
                "LUAS_BUMI INTEGER, " +
                "JNS_BUMI TEXT, " +
                "NILAI_SISTEM_BUMI INTEGER)");

        db.execSQL("CREATE TABLE DAT_SUBJEK_PAJAK (" +
                "SUBJEK_PAJAK_ID TEXT NOT NULL, " +
                "NID_WP TEXT NOT NULL, " +
                "NM_WP TEXT NOT NULL, " +
                "JALAN_WP TEXT, " +
                "BLOK_KAV_NO_WP TEXT, " +
                "RW_WP TEXT, " +
                "RT_WP TEXT, " +
                "KELURAHAN_WP TEXT, " +
                "KOTA_WP TEXT, " +
                "KD_POS_WP TEXT, " +
                "TELP_WP TEXT, " +
                "HP_WP TEXT, " +
                "EMAIL_WP TEXT, " +
                "NPWP TEXT, " +
                "STATUS_PEKERJAAN_WP TEXT, " +
                "NOP TEXT)");

        db.execSQL("CREATE TABLE DAT_PROPERTY_USAHA (" +
                "NPWPD TEXT NOT NULL," +
                "NOP TEXT NOT NULL, " +
                "TIPE_OU TEXT, " +
                "NM_OU TEXT" +
                ")");

        db.execSQL("CREATE TABLE DAT_OU_HIBURAN (" +
                "NPWPD TEXT NOT NULL," +
                "NOP TEXT NOT NULL, " +
                "SUBJEK_PAJAK_ID TEXT NOT NULL, " +
                "KLASIFIKASI_OU TEXT, " +
                "NM_OU TEXT, " +
                "TLP_OU TEXT, " +
                "EMAIL_OU TEXT, " +
                "JALAN_OU TEXT," +
                "NO_PBB TEXT," +
                "NO_IZIN_OU TEXT," +
                "TGL_IZIN TEXT," +
                "JML_MEMBER_OU TEXT, " +
                "JNS_PEGAWAI_OU TEXT, " +
                "JNS_UNIT_OU TEXT, " +
                "LUAS_OU TEXT, " +
                "HARGA_SEWA_OU TEXT, " +
                "JAM_OPERASI_OU TEXT" +
                ")");

        db.execSQL("CREATE TABLE DAT_OU_HOTEL (" +
                "NPWPD TEXT NOT NULL," +
                "NOP TEXT NOT NULL, " +
                "SUBJEK_PAJAK_ID TEXT NOT NULL, " +
                "KLASIFIKASI_OU TEXT, " +
                "NM_OU TEXT, " +
                "TLP_OU TEXT, " +
                "EMAIL_OU TEXT, " +
                "JALAN_OU TEXT," +
                "NO_PBB TEXT," +
                "NO_IZIN_OU TEXT," +
                "TGL_IZIN TEXT, " +
                "JNS_PEGAWAI_OU TEXT," +
                "TARIF_OU TEXT, " +
                "KD_FASILITAS TEXT " +
                ")");

        db.execSQL("CREATE TABLE DAT_OU_PARKIR (" +
                "NPWPD TEXT NOT NULL," +
                "NOP TEXT NOT NULL, " +
                "SUBJEK_PAJAK_ID TEXT NOT NULL, " +
                "KLASIFIKASI_OU TEXT, " +
                "NM_OU TEXT, " +
                "TLP_OU TEXT, " +
                "EMAIL_OU TEXT, " +
                "JALAN_OU TEXT," +
                "NO_PBB TEXT," +
                "NO_IZIN_OU TEXT," +
                "TGL_IZIN TEXT," +
                "KAPASITAS_MOBIL_OU TEXT, " +
                "KAPASITAS_MOTOR_OU TEXT," +
                "TRF_MOBIL_JAM_PERTAMA_OU TEXT," +
                "TRF_MOBIL_JAM_SELANJUTNYA_OU TEXT,  " +
                "TRF_MOBIL_MAX_OU TEXT," +
                "TRF_MOBIL_BULANAN_OU TEXT," +
                "TRF_MOTOR_JAM_PERTAMA_OU TEXT," +
                "TRF_MOTOR_JAM_SELANJUTNYA_OU TEXT,  " +
                "TRF_MOTOR_MAX_OU TEXT," +
                "TRF_MOTOR_BULANAN_OU TEXT," +
                "TRF_BOX_JAM_PERTAMA_OU TEXT," +
                "TRF_BOX_JAM_SELANJUTNYA_OU TEXT,  " +
                "TRF_BOX_MAX_OU TEXT," +
                "TRF_BOX_BULANAN_OU TEXT" +
                ")");

        db.execSQL("CREATE TABLE DAT_OU_REKLAME ("+
                "NPWPD TEXT NOT NULL,"+
                "NOP TEXT NOT NULL, "+
                "KD_JNS_OU TEXT NOT NULL, "+
                "NO_BNG TEXT NOT NULL, "+
                "SUBJEK_PAJAK_ID TEXT NOT NULL, "+
                "KLASIFIKASI_OU TEXT, "+
                "NM_OU TEXT, "+
                "STATUS_PASANG_OU TEXT, "+
                "JALAN_OU TEXT,"+
                "NO_PBB TEXT, "+
                "LETAK_PASANG_OU TEXT,"+
                "PERIODE_PASANG_OU TEXT,"+
                "PANJANG_OU TEXT, "+
                "LEBAR_OU TEXT, "+
                "SISI_OU TEXT, "+
                "JUMLAH_OU TEXT, "+
                "TINGGI_OU TEXT, "+
                "JENIS_PRODUK_OU TEXT"+
                ")");

        db.execSQL("CREATE TABLE DAT_OU_RESTORAN (" +
                "NPWPD TEXT NOT NULL," +
                "NOP TEXT NOT NULL, " +
                "SUBJEK_PAJAK_ID TEXT NOT NULL, " +
                "KLASIFIKASI_OU TEXT, " +
                "NM_OU TEXT, " +
                "TLP_OU TEXT, " +
                "EMAIL_OU TEXT, " +
                "JALAN_OU TEXT," +
                "NO_PBB TEXT," +
                "NO_IZIN_OU TEXT," +
                "TGL_IZIN TEXT, " +
                "JNS_PEGAWAI_OU TEXT, " +
                "JAM_OUERASI_OU TEXT, " +
                "LUAS_OU TEXT, " +
                "JML_MEJA_OU TEXT, " +
                "JML_KURSI TEXT," +
                "HARGA_SEWA_OU TEXT " +
                ")");

        db.execSQL("insert into DAT_SUBJEK_PAJAK values('1212121','0','MURSIDIK','JL KEMANGGISAN RAYA','','9','4','PALMERAH','JAKARTA BARAT','','','','','-','5','317401100000000000')");

        db.execSQL("insert into DAT_SUBJEK_PAJAK values('953092511430192','0',' H YUNUS ',' JL MUAMALAH ','45','3','4',' CIPEDAK ','JAKARTA SELATAN','','','','','-','5','317103000000000000')");


    }

    

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DB_ALTER);
    }
}
