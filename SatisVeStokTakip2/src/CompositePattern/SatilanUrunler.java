package CompositePattern;

import java.util.ArrayList;
import java.util.List;

import tr.com.akarcesme.complex.types.SatisContractTotalFiyatComplex;

public class SatilanUrunler {
	private String adi;
    private List<Fiyatlanabilir> fiyatlanabilirList;
	
	public SatilanUrunler () {
		this.adi=adi;
		this.fiyatlanabilirList= new ArrayList<Fiyatlanabilir>();
	}
	public int toplamOdenecekTutar() {
		int toplamTutar = 0;
		for(Fiyatlanabilir fiyatlanabilir : fiyatlanabilirList) {
			toplamTutar = toplamTutar + fiyatlanabilir.getFiyat();
		}
		return toplamTutar;
	}

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}
	public List<Fiyatlanabilir> getFiyatlanabilirList() {
		return fiyatlanabilirList;
	}
	public void setFiyatlanabilirList(List<Fiyatlanabilir> fiyatlanabilirList) {
		this.fiyatlanabilirList = fiyatlanabilirList;
	}
	
	
	

}
