package tr.com.akarcesme.types;

public class PersonelContract {
	private int id;
	private String adiSoyadi;
	private String eMail;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdiSoyadi() {
		return adiSoyadi;
	}

	public void setAdiSoyadi(String adiSoyadi) {
		this.adiSoyadi = adiSoyadi;
	}

	public String getEmail() {
		return eMail;
	}

	public void setEmail(String eMail) {
		this.eMail = eMail;
	}


	@Override
	public String toString() {
		return adiSoyadi;
	}

}
