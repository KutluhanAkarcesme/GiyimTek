package tr.com.akarcesme.complex.types;

public class SatisContractTotalComplex {

	private int id;
	private String musteriAdi;
	private String personelAdi;
	private String urunAdi;
	private int adet;
	private String tarih;
	private int toplam;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMusteriAdi() {
		return musteriAdi;
	}

	public void setMusteriAdi(String musteriAdi) {
		this.musteriAdi = musteriAdi;
	}

	public String getPersonelAdi() {
		return personelAdi;
	}

	public void setPersonelAdi(String personelAdi) {
		this.personelAdi = personelAdi;
	}

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

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}
	public int getToplam() {
		return toplam;
	}

	public void setToplam(int toplam) {
		this.toplam = toplam;
	}

		public Object[] getVeriler() {
			
			Object[] veriler = {id,personelAdi,musteriAdi,urunAdi,adet,tarih,toplam};
				
			return veriler;
		}
	
	public String toString() {
		return personelAdi + " " + musteriAdi + " " + urunAdi;
	}

}
