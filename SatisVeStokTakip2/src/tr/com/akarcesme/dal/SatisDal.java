package tr.com.akarcesme.dal;

import tr.com.akarcesme.complex.types.SatisContractComplex;

import tr.com.akarcesme.complex.types.SatisContractTotalComplex;
import tr.com.akarcesme.complex.types.SatisContractTotalFiyatComplex;
import tr.com.akarcesme.complex.types.StokContractComplex;
import tr.com.akarcesme.complex.types.StokContractTotalComplex;
import tr.com.akarcesme.core.ObjectHelper;
import tr.com.akarcesme.interfaces.DALInterfaces;
import tr.com.akarcesme.types.KategoriContract;
import tr.com.akarcesme.types.SatisContract;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import StrategyPattern.strategyPattern;
import tr.com.akarcesme.core.ObjectHelper;
import tr.com.akarcesme.interfaces.DALInterfaces;

public class SatisDal extends ObjectHelper implements DALInterfaces<SatisContract> {

	@Override
	public void Insert(SatisContract entity) {

		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Satis (UrunId,MusteriId,Tarih,Adet,PersonelId) VALUES ("
					+ entity.getUrunId() + "," + entity.getMusteriId() + ",'" + entity.getTarih() + "',"
					+ entity.getAdet() + "," + entity.getPersonelId() + ")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<SatisContractComplex> GetAllSatis() {
		List<SatisContractComplex> dataContract = new ArrayList<SatisContractComplex>();
		Connection connection = getConnection();
		SatisContractComplex contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT satis.Id , personel.AdiSoyadi , musteri.MusteriAdi , Adi , Adet , satis.Tarih FROM satis"
							+ " LEFT JOIN musteri ON satis.MusteriId = musteri.Id "
							+ " LEFT JOIN urunler ON satis.UrunId = urunler.Id "
							+ " LEFT JOIN personel ON satis.PersonelId = personel.ýd ORDER BY satis.Id DESC");
			while (resultSet.next()) {
				contract = new SatisContractComplex();
				contract.setId(resultSet.getInt("satis.Id"));
				contract.setMusteriAdi(resultSet.getString("musteri.MusteriAdi"));
				contract.setPersonelAdi(resultSet.getString("personel.AdiSoyadi"));
				contract.setTarih(resultSet.getString("satis.tarih"));
				contract.setUrunAdi(resultSet.getString("Adi"));
				contract.setAdet(resultSet.getInt("Adet"));
				dataContract.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataContract;
	}

	@Override
	public List<SatisContract> GetAll() {
		return null;
	}

	@Override
	public SatisContract Delete(SatisContract entity) {
		return null;
	}

	@Override
	public void update(SatisContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE satis SET UrunId=" + entity.getUrunId() + ", MusteriId="
					+ entity.getMusteriId() + ", Tarih='" + entity.getTarih() + "', Adet=" + entity.getAdet()
					+ ", PersonelId=" + entity.getPersonelId() + " WHERE Id=" + entity.getId() + "");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<SatisContractTotalComplex> GetTotalSatis() {
		List<SatisContractTotalComplex> dataContract = new ArrayList<SatisContractTotalComplex>();
		Connection connection = getConnection();
		SatisContractTotalComplex contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT SUM(Adet) as toplam,satis.Id , personel.AdiSoyadi , musteri.MusteriAdi , Adi , Adet , satis.Tarih FROM satis"
							+ " LEFT JOIN musteri ON satis.MusteriId = musteri.Id "
							+ " LEFT JOIN urunler ON satis.UrunId = urunler.Id "
							+ " LEFT JOIN personel ON satis.PersonelId = personel.ýd GROUP BY MusteriId ");
			while (resultSet.next()) {
				contract = new SatisContractTotalComplex();
				contract.setId(resultSet.getInt("satis.Id"));
				contract.setMusteriAdi(resultSet.getString("musteri.MusteriAdi"));
				contract.setPersonelAdi(resultSet.getString("personel.AdiSoyadi"));
				contract.setTarih(resultSet.getString("satis.tarih"));
				contract.setUrunAdi(resultSet.getString("Adi"));
				contract.setAdet(resultSet.getInt("Adet"));
				contract.setToplam(resultSet.getInt("toplam"));
				dataContract.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataContract;

	}

	public List<SatisContractTotalFiyatComplex> GetTotalFiyatSatis() {
		List<SatisContractTotalFiyatComplex> dataContract = new ArrayList<SatisContractTotalFiyatComplex>();
		Connection connection = getConnection();
		SatisContractTotalFiyatComplex contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT urunler.Adi , urunler.Fiyat , satis.Adet FROM satis" 
					+ " LEFT JOIN urunler ON satis.UrunId = urunler.Id ");
			while (resultSet.next()) {
				contract = new SatisContractTotalFiyatComplex();
				contract.setUrunAdi(resultSet.getString("Adi"));
				contract.setAdet(resultSet.getInt("Adet"));
				contract.setFiyat(resultSet.getInt("Fiyat"));
				dataContract.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataContract;

	}

	@Override
	public List<SatisContract> GetById(int id) {
		return null;
	}

}
