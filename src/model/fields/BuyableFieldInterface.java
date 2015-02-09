package model.fields;

/**
 * A Field which can be bought.
 * 
 * @author rohx89
 *
 */
public interface BuyableFieldInterface {

	public int getPrice();

	public String getName();

	public boolean hasMortage();

	public void setMortage(boolean mortage);

	public int getRedemptionOfMortage();

	public int getMortage();

}
