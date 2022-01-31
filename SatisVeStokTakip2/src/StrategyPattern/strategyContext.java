package StrategyPattern;

public class strategyContext {
	private strategyPattern strategy;

	public int getHesapla(strategyPattern strategy,int satisMiktari) {
	     this.strategy=strategy;
	     return this.strategy.odemeHesapla(satisMiktari);
	}

}
