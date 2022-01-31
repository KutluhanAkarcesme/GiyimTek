package CompositePattern;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tr.com.akarcesme.complex.types.SatisContractTotalFiyatComplex;
import tr.com.akarcesme.dal.SatisDal;

public class SatilanUrunlerTest {
	List<Fiyatlanabilir> fiyatlanabilirListTest;
	SatilanUrunler sUrunler;
	kategoriler cUrunler;
	kategoriler pUrunler;
	int resultTest=0;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Before Class");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("After Class");
	}

	@Before
	public void setUp() throws Exception {
		fiyatlanabilirListTest = new ArrayList<Fiyatlanabilir>();
		sUrunler= new SatilanUrunler();
		cUrunler= new kategoriler();
		pUrunler = new kategoriler();
		int toplamTutar=0;
		for (SatisContractTotalFiyatComplex contract : new SatisDal().GetTotalFiyatSatis()) {
			if (contract.getUrunAdi().charAt(1) == 'C') {
				cUrunler.getUrunlerList().add(contract);
			}
			if (contract.getUrunAdi().charAt(1) == 'P') {
				pUrunler.getUrunlerList().add(contract);
			}
		}
		fiyatlanabilirListTest.add(cUrunler);
		fiyatlanabilirListTest.add(pUrunler);
		for(Fiyatlanabilir fiyatlanabilir : fiyatlanabilirListTest) {
			toplamTutar = toplamTutar + fiyatlanabilir.getFiyat();
		}
		resultTest = toplamTutar;
		System.out.println("Method Öncesi");
	}

	@After
	public void tearDown() throws Exception {
		List<Fiyatlanabilir> fiyatlanabilirListTest=null;
		SatilanUrunler sUrunler=null;
		kategoriler cUrunler=null;
		kategoriler pUrunler=null;
		int resultTest=0;
		System.out.println("Method Sonrasý");
	}
	
	@Test
	public void testToplamOdenecekTutar() {
		sUrunler.getFiyatlanabilirList().add(cUrunler);
		sUrunler.getFiyatlanabilirList().add(pUrunler);
		int result = sUrunler.toplamOdenecekTutar();
		assertEquals(result, resultTest);
		System.out.println("Method");
	}

}
