package PrototypePattern;

import java.util.ArrayList;

import java.util.List;
import tr.com.akarcesme.dal.UrunlerDal;
import tr.com.akarcesme.types.UrunlerContract;

public class prototype implements Cloneable {

   List<Object> urunler;

	public prototype() {
		urunler = new ArrayList<Object>();
	}

	public prototype(List<Object> urunler) {
		this.urunler = urunler;
	}

	public void AddUrunler(Object x) {
		urunler.add(x);
	}

	public List<Object> getUrunlerListesi() {
		return urunler;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		List<Object> urunlerListesi = new ArrayList<Object>();
		for(Object x : this.getUrunlerListesi()) {
			urunlerListesi.add(x);
		}
		return new prototype(urunlerListesi); 
	}

}
