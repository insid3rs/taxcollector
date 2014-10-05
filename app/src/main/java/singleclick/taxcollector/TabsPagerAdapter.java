package singleclick.taxcollector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    protected String searchKey="";
    protected String searchType="";
    protected String objekUsahaType="";
    protected String coordinates = "";

    protected void setSearchKey(String inputSearchKey){
        this.searchKey = inputSearchKey;
    }

    protected void setSearchType(String inputSearchType){ this.searchType = inputSearchType; }

    protected void setObjekUsahaType(String inputObjekUsahaType){ this.objekUsahaType = inputObjekUsahaType; }

    protected void setCoordinates(String coordinate)
    {
        this.coordinates = coordinate;
    }

    @Override
    public Fragment getItem(int index) {
        Bundle data = new Bundle();
        data.putString("searchKey", searchKey);
        data.putString("searchType", searchType);
        data.putString("objekUsahaType", objekUsahaType);
        data.putString("dataCoord",coordinates);

        //System.out.println("aa"+searchKey);
        //System.out.println(searchType);

        switch (index) {
            case 0:
                if(searchType.equals("NIK") || searchType.equals("tambahSubjekPajak")){
                    SubjekPajakFragment SubjekPajakFragment = new SubjekPajakFragment();
                    SubjekPajakFragment.setArguments(data);
                    return SubjekPajakFragment;
                }else if(searchType.equals("NOP") || searchType.equals("tambahPBB")){
                    DataPBBFragment DataPBBFragment = new DataPBBFragment();
                    DataPBBFragment.setArguments(data);
                    return DataPBBFragment;
                }else if(searchType.equals("NPWPD") && objekUsahaType.toLowerCase().equals("hotel")) {
                    DataHotelFragment DataHotelFragment = new DataHotelFragment();
                    DataHotelFragment.setArguments(data);
                    return DataHotelFragment;
                }else if(searchType.equals("NPWPD") && objekUsahaType.toLowerCase().equals("hiburan")) {
                    DataHiburanFragment DataHiburanFragment = new DataHiburanFragment();
                    DataHiburanFragment.setArguments(data);
                    return DataHiburanFragment;
                }else if(searchType.equals("NPWPD") && objekUsahaType.toLowerCase().equals("parkir")) {
                    DataParkirFragment DataParkirFragment = new DataParkirFragment();
                    DataParkirFragment.setArguments(data);
                    return DataParkirFragment;
                }else if(searchType.equals("NPWPD") && objekUsahaType.toLowerCase().equals("reklame")) {
                    DataReklameFragment DataReklameFragment = new DataReklameFragment();
                    DataReklameFragment.setArguments(data);
                    return DataReklameFragment;
                }else if(searchType.equals("NPWPD") && objekUsahaType.toLowerCase().equals("restoran")) {
                    DataRestoranFragment DataRestoranFragment = new DataRestoranFragment();
                    DataRestoranFragment.setArguments(data);
                    return DataRestoranFragment;
                }else if(searchType.equals("tambahHotel")){
                    DataHotelFragment DataHotelFragment = new DataHotelFragment();
                    DataHotelFragment.setArguments(data);
                    return DataHotelFragment;
                }else if(searchType.equals("tambahParkir")){
                    DataParkirFragment DataParkirFragment = new DataParkirFragment();
                    DataParkirFragment.setArguments(data);
                    return DataParkirFragment;
                }else if(searchType.equals("tambahHiburan")){
                    DataHiburanFragment DataHiburanFragment = new DataHiburanFragment();
                    DataHiburanFragment.setArguments(data);
                    return DataHiburanFragment;
                }else if(searchType.equals("tambahReklame")){
                    DataReklameFragment DataReklameFragment = new DataReklameFragment();
                    DataReklameFragment.setArguments(data);
                    return DataReklameFragment;
                }else if(searchType.equals("tambahRestoran")){
                    DataRestoranFragment DataRestoranFragment = new DataRestoranFragment();
                    DataRestoranFragment.setArguments(data);
                    return DataRestoranFragment;
                }


            case 1:
                if(searchType.equals("NIK") || searchType.equals("NOP")) {
                    ObjekUsahaFragment ObjekUsahaFragment = new ObjekUsahaFragment();
                    ObjekUsahaFragment.setArguments(data);
                    return ObjekUsahaFragment;
                }else if(searchType.equals("NPWPD") && objekUsahaType.toLowerCase().equals("hotel")) {
                    DataHotelFragment DataHotelFragment = new DataHotelFragment();
                    DataHotelFragment.setArguments(data);
                    return DataHotelFragment;
                }else if(searchType.equals("NPWPD") && objekUsahaType.toLowerCase().equals("hiburan")) {
                    DataHiburanFragment DataHiburanFragment = new DataHiburanFragment();
                    DataHiburanFragment.setArguments(data);
                    return DataHiburanFragment;
                }else if(searchType.equals("NPWPD") && objekUsahaType.toLowerCase().equals("parkir")) {
                    DataParkirFragment DataParkirFragment = new DataParkirFragment();
                    DataParkirFragment.setArguments(data);
                    return DataParkirFragment;
                }else if(searchType.equals("NPWPD") && objekUsahaType.toLowerCase().equals("reklame")) {
                    DataReklameFragment DataReklameFragment = new DataReklameFragment();
                    DataReklameFragment.setArguments(data);
                    return DataReklameFragment;
                }else if(searchType.equals("NPWPD") && objekUsahaType.toLowerCase().equals("restoran")) {
                    DataRestoranFragment DataRestoranFragment = new DataRestoranFragment();
                    DataRestoranFragment.setArguments(data);
                    return DataRestoranFragment;
                }else if(searchType.equals("tambahSubjekPajak")){
                    SubjekPajakFragment SubjekPajakFragment = new SubjekPajakFragment();
                    SubjekPajakFragment.setArguments(data);
                    return SubjekPajakFragment;
                }else if(searchType.equals("tambahPBB")){
                    DataPBBFragment DataPBBFragment = new DataPBBFragment();
                    DataPBBFragment.setArguments(data);
                    return DataPBBFragment;
                }else if(searchType.equals("tambahHotel")){
                    DataHotelFragment DataHotelFragment = new DataHotelFragment();
                    DataHotelFragment.setArguments(data);
                    return DataHotelFragment;
                }else if(searchType.equals("tambahParkir")){
                    DataParkirFragment DataParkirFragment = new DataParkirFragment();
                    DataParkirFragment.setArguments(data);
                    return DataParkirFragment;
                }else if(searchType.equals("tambahHiburan")){
                    DataHiburanFragment DataHiburanFragment = new DataHiburanFragment();
                    DataHiburanFragment.setArguments(data);
                    return DataHiburanFragment;
                }else if(searchType.equals("tambahReklame")){
                    DataReklameFragment DataReklameFragment = new DataReklameFragment();
                    DataReklameFragment.setArguments(data);
                    return DataReklameFragment;
                }else if(searchType.equals("tambahRestoran")){
                    DataRestoranFragment DataRestoranFragment = new DataRestoranFragment();
                    DataRestoranFragment.setArguments(data);
                    return DataRestoranFragment;
                }
        }


        return null;
    }


    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }

}
