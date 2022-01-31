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

public class fiyatHesaplaUtilTest {
	List<SatisContractTotalFiyatComplex> urunlerListTest;
	int resultContract=0;
	fiyatHesaplaUtil fUtil;
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
		urunlerListTest = new ArrayList<SatisContractTotalFiyatComplex>();
		fUtil = new fiyatHesaplaUtil();
		int toplamTutarContract=0;
		for (SatisContractTotalFiyatComplex urunler : new SatisDal().GetTotalFiyatSatis()) {
			urunlerListTest.add(urunler);
		    toplamTutarContract = toplamTutarContract + (urunler.getFiyat() * urunler.getAdet());
		}
	    resultContract = toplamTutarContract;
	    System.out.println("Method Öncesi");
	}

	@After
	public void tearDown() throws Exception {
		urunlerListTest=null;
		resultContract=0;
		System.out.println("Method Sonrasý");
	}

	@Test
	public void testGetToplamUrunFiyati() {
		int result=fUtil.getToplamUrunFiyati(urunlerListTest);
		assertEquals(result, resultContract);
		System.out.println("Method");
	}

}
