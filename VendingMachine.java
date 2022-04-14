import java.util.ArrayList;
import java.util.*;

public class VendingMachine {
  // ArrayList of Integers represents inserted coins in Coin Slot
  private ArrayList<Integer> insertedCoins;
	
  // ArrayList of Product represents inventories of products
  private ArrayList<Product> products;
  
  private int[] coinChanger = {10,5,2,1};
  
  public VendingMachine() {
    insertedCoins = new ArrayList<Integer>();
    products = new ArrayList<Product>();
  }

  public void addProduct(Product p) {
    products.add(p);
  }
	
  public void insertCoin(Integer c) {
    insertedCoins.add(c);
  }	

  public int totalCoins() {
	  int total = 0;
	  for (int coin : insertedCoins) {
		  total += coin;
	  }
	  return total;
  }

  public String rejectCoins() {
	  if (insertedCoins.size() == 0){
		  return "Rejected no coin!";
	  }
	  
	  Collections.sort(insertedCoins);
	  
	  String output = "Rejected";
	  
	  for (int coin: insertedCoins) {
		  output += (" $" + coin + ","); 
	  }
	  output = output.substring(0,output.length()-1);
	  output += (". $" +totalCoins()+ " in total.");
	  insertedCoins.clear();
	  return output;
  }
  
  public String checkProduct(String name) {
	  ArrayList<Integer> result = new ArrayList<Integer>();
	  Product drink;
	  int total = totalCoins();
	  String output = "";
	  
	  for (int i = 0; i < products.size(); i++) {
		  drink = products.get(i);
		  
		  String drinkName = drink.getName();
		  int price = drink.getPrice();
		  int quantity = drink.getQuantity();
		  if (drinkName.equalsIgnoreCase(name)) {
			  if (quantity == 0) {
				  output += drinkName+ " is out of stock!";
			  }
			  else if (price > total) {
				  output += "Not enough credit to buy " +drinkName+ "! Inserted $" +total+ " but needs $" +price+ ".";
			  }
			  else {
				  output += "Dropped " +drinkName+ ". Paid $" +total+ ". ";
				  drink.decreaseQuantity();
				  int remainder = total - price;
				  if (remainder == 0) {
					  output += "No change.";
				  }
				  else {
					  for (int x = 0; x < coinChanger.length; x++) {
						  if (remainder % coinChanger[x] != remainder) {
							  int quotient = remainder / coinChanger[x];
							  
							  remainder -= (coinChanger[x] * quotient);
							  
							  for (int y = 0; y < quotient; y++) {
								  result.add(coinChanger[x]);
							  }
						  }
					  }
					  output += "Your change: ";
					  Collections.sort(result);
					  int tempIdx = 0;
					  for (int j = 0; j < result.size()-1; j++) {
						  output += "$" +result.get(j)+ ", ";
						  tempIdx += 1;
					  }
					  output += "$" +result.get(tempIdx)+ ".";
				  }
				  insertedCoins.clear();
				  
			  }
		  }
	  }
	  return output;
	  
	  
  }
}
