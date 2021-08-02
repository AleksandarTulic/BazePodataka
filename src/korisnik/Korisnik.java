package korisnik;

public class Korisnik {
	private String jmb;
	private String nazivKompanije;
	
	public Korisnik(String jmb, String nazivKompanije) {
		this.jmb = jmb;
		this.nazivKompanije = nazivKompanije;
	}

	public String getJmb() {
		return jmb;
	}

	public void setJmb(String jmb) {
		this.jmb = jmb;
	}

	public String getNazivKompanije() {
		return nazivKompanije;
	}

	public void setNazivKompanije(String nazivKompanije) {
		this.nazivKompanije = nazivKompanije;
	}
}
