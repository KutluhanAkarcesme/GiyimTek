package CompositePattern;

import java.util.ArrayList;
import java.util.List;

import tr.com.akarcesme.complex.types.SatisContractComplex;
import tr.com.akarcesme.complex.types.SatisContractTotalFiyatComplex;

public class kategoriler implements Fiyatlanabilir{

	private String adi;
	private List<SatisContractTotalFiyatComplex> urunlerList;
	
	public kategoriler() {
		this.adi=adi;
		this.urunlerList= new ArrayList<SatisContractTotalFiyatComplex>();
	}
	@Override
	public int getFiyat() {
		
		return fiyatHesaplaUtil.getToplamUrunFiyati(urunlerList);
	}
	public String getAdi() {
		return adi;
	}
	public void setAdi(String adi) {
		this.adi = adi;
	}
	public List<SatisContractTotalFiyatComplex> getUrunlerList() {
		return urunlerList;
	}
	public void setUrunlerList(List<SatisContractTotalFiyatComplex> UrunlerList) {
		this.urunlerList = UrunlerList;
	}
}
