package tr.com.akarcesme.types;

public class MusteriContract {
	private int id;
	private String musteriAdi;
	private String telefon;
	private String adres;
	private int sehirId;

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

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public int getSehirId() {
		return sehirId;
	}

	public void setSehirId(int sehirId) {
		this.sehirId = sehirId;
	}

	@Override
	public String toString() {
		return  musteriAdi ;
	}

}
