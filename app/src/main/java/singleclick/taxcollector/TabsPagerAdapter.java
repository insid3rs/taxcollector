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

    protected void setSearchKey(String inputSearchKey){
        this.searchKey = inputSearchKey;
    }

    protected void setSearchType(String inputSearchType){ this.searchType = inputSearchType; }

    protected void setObjekUsahaType(String inputObjekUsahaType){ this.objekUsahaType = inputObjekUsahaType; }

	@Override
	public Fragment getItem(int index) {
        Bundle data = new Bundle();
        data.putString("searchKey", searchKey);
        data.putString("searchType", searchType);


		switch (index) {
		case 0:
            if(searchType.equals("NIK") || searchType.equals("tambahSubjekPajak")){
                SubjekPajakFragment SubjekPajakFragment = new SubjekPajakFragment();
                SubjekPajakFragment.setArguments(data);
                return SubjekPajakFragment;
            }else if(searchType.equals("NOP") || searchType.equals("NPWPD")){
                DataPBBFragment DataLokasiFragment = new DataPBBFragment();
                DataLokasiFragment.setArguments(data);
                return DataLokasiFragment;
            }else if(searchType.equals("tambahHotel")){
                DataHotelFragment DataHotelFragment = new DataHotelFragment();
                DataHotelFragment.setArguments(data);
                return DataHotelFragment;
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
                return new DataHiburanFragment();
            }else if(searchType.equals("NPWPD") && objekUsahaType.toLowerCase().equals("parkir")) {
                return new DataParkirFragment();
            }else if(searchType.equals("NPWPD") && objekUsahaType.toLowerCase().equals("reklame")) {
                return new DataReklameFragment();
            }else if(searchType.equals("NPWPD") && objekUsahaType.toLowerCase().equals("restoran")) {
                return new DataRestoranFragment();
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
