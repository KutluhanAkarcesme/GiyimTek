package CompositePattern;

import tr.com.akarcesme.complex.types.SatisContractComplex;
import tr.com.akarcesme.complex.types.SatisContractTotalFiyatComplex;

public class urunler implements Fiyatlanabilir{
	private String adi;
	private int adet;
	private int fiyat;
	
   public urunler(String adi,int adet,int fiyat) {
		this.adi=adi;
		this.adet=adet;
		this.fiyat=fiyat;
	}
	
	public int getFiyat() {
		return this.fiyat;
		}

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public int getAdet() {
		return adet;
	}

	public void setAdet(int adet) {
		this.adet = adet;
	}

	public void setFiyat(int fiyat) {
		this.fiyat = fiyat;
	}

	

}
