package StrategyPattern;

import tr.com.akarcesme.dal.SatisDal;

public class pantolon implements strategyPattern{

	@Override
	public int odemeHesapla(int satisMiktari) {
		
		return 30*satisMiktari;
	}


}
