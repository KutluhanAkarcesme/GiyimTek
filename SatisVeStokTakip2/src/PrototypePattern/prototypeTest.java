package PrototypePattern;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class prototypeTest {
	prototype Ptype;
	List<Object> testList;
	Object urun;

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
		Ptype = new prototype();
		testList = new ArrayList<Object>();
		urun = "ACN100";
		testList.add(urun);
		System.out.println("Method Öncesi");
	}

	@After
	public void tearDown() throws Exception {
		urun = null;
		testList = null;
		System.out.println("Method Sonrasý");
	}

	@Test
	public void testAddUrunler() {
	     Ptype.AddUrunler(urun);
	     List<Object> resultLÝst = Ptype.getUrunlerListesi();
	     assertEquals(resultLÝst, testList);
	     System.out.println("Method");
	}

	@Test
	public void testGetUrunlerListesi() {
		Ptype.AddUrunler(urun);
		List<Object> resultLÝst = Ptype.getUrunlerListesi();
	     assertEquals(resultLÝst, testList);
	     System.out.println("Method");
	}

	@Test
	public void testClone() throws CloneNotSupportedException {
		Ptype.AddUrunler(urun);
		prototype PtypeClone = (prototype) Ptype.clone();
		List<Object> pTypeList = Ptype.getUrunlerListesi();
		List<Object> pTypeCloneList = PtypeClone.getUrunlerListesi();
		assertEquals(pTypeList, pTypeCloneList);
		System.out.println("Method");
	}

}
