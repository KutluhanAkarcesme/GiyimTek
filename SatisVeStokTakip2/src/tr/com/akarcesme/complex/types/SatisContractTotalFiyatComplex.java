package tr.com.akarcesme.complex.types;

public class SatisContractTotalFiyatComplex {
	private String urunAdi;
	private int adet;
	private int fiyat;
	
	
	public String getUrunAdi() {
		return urunAdi;
	}



	public void setUrunAdi(String urunAdi) {
		this.urunAdi = urunAdi;
	}



	public int getAdet() {
		return adet;
	}



	public void setAdet(int adet) {
		this.adet = adet;
	}



	public int getFiyat() {
		return fiyat;
	}



	public void setFiyat(int fiyat) {
		this.fiyat = fiyat;
	}



	@Override
	public String toString() {
		return "SatisContractTotalFiyatComplex [urunAdi=" + urunAdi + ", adet=" + adet + ", fiyat=" + fiyat
				+ ", getUrunAdi()=" + getUrunAdi() + ", getAdet()=" + getAdet() + ", getFiyat()=" + getFiyat()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}



	
	
	

}
