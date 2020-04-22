import com.alertinnovation.vending.Exceptions.*;
import com.alertinnovation.vending.dao.Item;
import com.alertinnovation.vending.vendingStates.Ready;
import com.alertinnovation.vending.vendingStates.VendingStates;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import com.alertinnovation.vending.vendingMachine.VendingMachine;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class VendingMachineTest {
  ArrayList<Item> itemsList;
  VendingMachine vendingMachine;
  VendingStates state;
  @Before
  public void setup() {
    itemsList = new ArrayList<>();
    itemsList.add(new Item("Snickers",1000,5.0));
    itemsList.add(new Item("Pop tarts",2000,5.0));
    itemsList.add(new Item("Kurkure",3000,3.0));
    itemsList.add(new Item("Cheetos",4000,4.0));
    itemsList.add(new Item("Milky way",5000,2.0));
    itemsList.add(new Item("Trail mix",6000,5.0));
    itemsList.add(new Item("Nature valley",7000,4.0));
    itemsList.add(new Item("Keurig",8000,2.0));
    itemsList.add(new Item("Trident",9000,1.0));
    itemsList.add(new Item("Mentos",10000,1.0));
    this.vendingMachine= new VendingMachine(itemsList,100);
    this.vendingMachine.setState(new Ready(this.vendingMachine));
    this.state = this.vendingMachine.getState();
    // TODO: add if necessary.
  }

  public VendingStates getCurrentState(){
    return this.vendingMachine.getState();
  }

  public void dispenseItemWithFiveCoins(String slotName){
    getCurrentState().selectItemFromSlot(slotName);
    getCurrentState().collectMoneyForItem(1.0);
    getCurrentState().collectMoneyForItem(1.0);
    getCurrentState().collectMoneyForItem(1.0);
    getCurrentState().collectMoneyForItem(1.0);
    getCurrentState().collectMoneyForItem(1.0);
    getCurrentState().dispenseChange();
    getCurrentState().dispenseItemFromSlot();
  }

  @After
  public void teardown() {
    // TODO: add if necessary.
  }
  
  // TODO: Add your tests here. 
  
  @Test
  public void selectItemSuccessful() {
    String slot = "A1";
    dispenseItemWithFiveCoins(slot);
    int denomCount = this.vendingMachine.getCoinInventory().get(1.0);
    int itemCount = this.vendingMachine.getItemInventory().get("A1").getItemCount();
    assertEquals(14, itemCount);
    assertEquals(105, denomCount);
  }

  @Test(expected = SoldOutException.class)
  public void selectItemsSoldOut() {
    String slot = "A1";
    for(int i=0; i<16; i++){
      dispenseItemWithFiveCoins(slot);
    }
  }

  @Test(expected = InvalidSelectionException.class)
  public void invalidSelection() {
    String slot = "A19";
      dispenseItemWithFiveCoins(slot);
  }

  @Test(expected = SelectItemException.class)
  public void selectItemException() {
    String slot = "A1";
    getCurrentState().selectItemFromSlot(slot);
    getCurrentState().collectMoneyForItem(1.0);
    getCurrentState().dispenseChange();
    getCurrentState().selectItemFromSlot(slot);
  }

  @Test
  public void cancelTransactionSuccess() {
    String slot = "A1";
    getCurrentState().selectItemFromSlot(slot);
    getCurrentState().collectMoneyForItem(1.0);
    int denomCount = this.vendingMachine.getCoinInventory().get(1.0);
    assertEquals(101, denomCount);
    getCurrentState().transactionCancelled();
    denomCount = this.vendingMachine.getCoinInventory().get(1.0);
    assertEquals(100, denomCount);

  }
  @Test
  public void selectItemSuccessfulWithExtraBalance() {
    String slot = "A1";
    getCurrentState().selectItemFromSlot(slot);
    getCurrentState().collectMoneyForItem(1.0);
    getCurrentState().collectMoneyForItem(1.0);
    getCurrentState().collectMoneyForItem(1.0);
    getCurrentState().collectMoneyForItem(1.0);
    getCurrentState().collectMoneyForItem(1.0);
    getCurrentState().collectMoneyForItem(1.0);
    getCurrentState().dispenseItemFromSlot();
    int denomCount = this.vendingMachine.getCoinInventory().get(1.0);
    int itemCount = this.vendingMachine.getItemInventory().get("A1").getItemCount();
    assertEquals(14, itemCount);
    assertEquals(105, denomCount);
  }

  @Test(expected = CoinNotSupportedException.class)
  public void coinNotSupported() {
    String slot = "A1";
    getCurrentState().selectItemFromSlot(slot);
    getCurrentState().collectMoneyForItem(0.50);
  }

  @Test(expected = NotsufficientBalanceException.class)
  public void notSufficientBalance() {
    String slot = "A1";
    getCurrentState().selectItemFromSlot(slot);
    getCurrentState().collectMoneyForItem(1.0);
    getCurrentState().dispenseItemFromSlot();
  }

  @Test(expected = DispenseChangeException.class)
  public void dispenseChangeException() {
    String slot = "E1";
    getCurrentState().selectItemFromSlot(slot);
    getCurrentState().collectMoneyForItem(1.0);
    getCurrentState().dispenseItemFromSlot();
    getCurrentState().dispenseChange();
  }

}