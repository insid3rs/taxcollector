package singleclick.taxcollector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

    protected String searchKey;
    protected String searchType;

    protected void setSearchKey(String inputSearchKey){
        this.searchKey = inputSearchKey;
    }

    protected void setSearchType(String inputSearchType){ this.searchType = inputSearchType; }


	@Override
	public Fragment getItem(int index) {
        Bundle data = new Bundle();
        data.putString("searchKey", searchKey);
        data.putString("searchType", searchType);


		switch (index) {
		case 0:
            if(searchType.equals("NOP")){
                DataRestoranFragment DataLokasiFragment = new DataRestoranFragment();
                DataLokasiFragment.setArguments(data);
                return DataLokasiFragment;
            }else {
                SubjekPajakFragment SubjekPajakFragment = new SubjekPajakFragment();
                SubjekPajakFragment.setArguments(data);
                return SubjekPajakFragment;
            }

		case 1:
			return new ObjekUsahaFragment();
		}


		return null;
	}


	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 2;
	}

}
