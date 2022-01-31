package CompositePattern;

import java.util.List;

import tr.com.akarcesme.complex.types.SatisContractComplex;
import tr.com.akarcesme.complex.types.SatisContractTotalFiyatComplex;

public class fiyatHesaplaUtil {
	
	public static int getToplamUrunFiyati(List<SatisContractTotalFiyatComplex> urunlerList) {
		int toplamTutar = 0;
		for(SatisContractTotalFiyatComplex urunler : urunlerList) {
			toplamTutar= toplamTutar + (urunler.getFiyat() * urunler.getAdet());
		}
		return toplamTutar;
	}
	public static int getToplamUrunlerFiyati(List<SatisContractTotalFiyatComplex> urunlerToplamList) { 
		int toplamTutar = 0;
		for(SatisContractTotalFiyatComplex urunler1 : urunlerToplamList) {
			toplamTutar= toplamTutar + urunler1.getFiyat();
		}
		return toplamTutar;
	}

}
