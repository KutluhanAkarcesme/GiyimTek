package StrategyPattern;

import static org.junit.Assert.*; 

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class strategyPatternTest {
	strategyContext context; 
	pantolon pUrun;
	ceket cUrun;
	int satisMiktari;
	int pUrunResult;
	int cUrunResult;

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
		context = new strategyContext();
		pUrun= new pantolon();
		cUrun = new ceket();
		satisMiktari = 10;
		pUrunResult = satisMiktari * 30;
		cUrunResult = satisMiktari * 40;
		System.out.println("Method Öncesi");
	}

	@After
	public void tearDown() throws Exception {
		cUrunResult=0;
		pUrunResult=0;
		System.out.println("Method Sonrasý");
	}

	@Test
	public void testOdemeHesapla() {
		int resultPantolon=context.getHesapla(pUrun, satisMiktari);
		int resultCeket = context.getHesapla(cUrun, satisMiktari);
		assertEquals(resultPantolon, pUrunResult);
		assertEquals(resultCeket, cUrunResult);
		System.out.println("Method");
	}

}
