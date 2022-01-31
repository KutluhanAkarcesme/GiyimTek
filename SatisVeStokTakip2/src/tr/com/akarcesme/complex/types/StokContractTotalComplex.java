package tr.com.akarcesme.complex.types;

public class StokContractTotalComplex {

	private int id;
	private String personelAdi;
	private String urunAdi;
	private String tarih;
	private int adet;
	private int toplam;

	public int getToplam() {
		return toplam;
	}

	public void setToplam(int toplam) {
		this.toplam = toplam;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

	public int getAdet() {
		return adet;
	}

	public void setAdet(int adet) {
		this.adet = adet;
	}

	public Object[] getVeriler() {
		Object[] veriler = { id, urunAdi, personelAdi, adet, tarih, toplam };
		return veriler;
	}

	public String toString() {
		return personelAdi + " " + urunAdi;
	}
}
