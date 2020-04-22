package com.alertinnovation.vending.vendingMachine;

import com.alertinnovation.vending.Exceptions.*;
import com.alertinnovation.vending.dao.Item;
import com.alertinnovation.vending.dao.Slot;
import com.alertinnovation.vending.vendingStates.VendingStates;

import java.util.ArrayList;
import java.util.HashMap;

public class VendingMachine {
    private HashMap<String, Slot> itemInventory;
    private HashMap<Double, Integer> coinInventory;
    private VendingStates state;
    private Item currentItemSelected;
    private Double collectedCash;
    private Double currentBalance;
    private String selectedSlot;

    /**
     * Constructor which takes items and number of coins
     * @param itemsList
     * @param numberOfCoins
     */
    public VendingMachine(ArrayList<Item> itemsList, int numberOfCoins) {
        this.itemInventory = new HashMap<>();
        this.coinInventory = new HashMap<>();
        initializeItemInventory(itemInventory, itemsList);
        initializeCoinInventory(coinInventory, numberOfCoins);
        this.currentBalance = 0.0;
        this.collectedCash = 0.0;
    }

    public void setCollectedCash(Double collectedCash) {
        this.collectedCash = collectedCash;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public HashMap<String, Slot> getItemInventory() {
        return itemInventory;
    }

    public HashMap<Double, Integer> getCoinInventory() {
        return coinInventory;
    }

    public VendingStates getState() {
        return state;
    }

    public void setState(VendingStates state) {
        this.state = state;
    }

    /**
     * This method initializes all the coins which are supported by vending machine
     * @param coinInventory
     * @param numberOfCoins
     */
    private void initializeCoinInventory(HashMap<Double, Integer> coinInventory, int numberOfCoins) {
        coinInventory.put(0.05, numberOfCoins);
        coinInventory.put(0.10, numberOfCoins);
        coinInventory.put(0.25, numberOfCoins);
        coinInventory.put(1.00, numberOfCoins);
        coinInventory.put(5.00, numberOfCoins);
    }

    /**
     *  This method initializes inventory with list of items for all 10 slots{A-E,1-2} with list of items supplied by
     *  Vending machine owner
     * @param itemInventory
     * @param itemsList
     */
    private void initializeItemInventory(HashMap<String, Slot> itemInventory, ArrayList<Item> itemsList) {
        int itemsListIndex = 0;
        for (char c = 'A'; c <= 'E'; c++) {
            String slotName1 = c + "1";
            String slotName2 = c + "2";
            itemInventory.put(slotName1, new Slot(itemsList.get(itemsListIndex++)));
            itemInventory.put(slotName2, new Slot(itemsList.get(itemsListIndex++)));
        }
    }

    /**
     * This method provides functionality for selecting item from slots
     * It also checks for invalid inputs and checks if items are sold out
     * @param slotName
     * @return
     */
    public Item selectItemFromSlot(String slotName) {
        this.selectedSlot = slotName;
        Slot slot = this.getItemInventory().get(slotName);
        if (slot != null) {
            if (slot.getItemCount() > 0) {
                return this.currentItemSelected = slot.getItem();
            } else {
                throw new SoldOutException("Item has been sold out please try again later");
            }
        } else {
            throw new InvalidSelectionException("Invalid selection");
        }
    }

    /**
     * This method porvides functionality for collecting cash
     * This checks for un supported coins
     * @param cash
     * @return
     */
    public boolean collectCash(Double cash) {
        Double cashForItem = this.currentItemSelected.getPrice();
        //Check if the denomination exists if not throw CoinNotSupportedException
        if (coinInventory.get(cash) != null) {
            // Update collected cash and increase coin inventory for that denomination
            collectedCash += cash;
            coinInventory.put(cash, coinInventory.get(cash) + 1);
            // User has added required cash to dispense the item
            // Then compute the current balance and return true so that it can be migrated to next state
            this.currentBalance = collectedCash - cashForItem;
        } else {
            throw new CoinNotSupportedException("Only $0.05, $0.10, $0.25, $1 coins are supported");
        }
        return false;
    }

    /**
     * This method provides functionality for dispensing change it checks for cash inventory
     * and looks for exception
     */
    public void dispenseChange() {
        if (this.currentBalance >= 0) {
            calculateChange(false);
            System.out.println("change dispensed : successfully");
        } else {
            calculateChange(true);
        }
    }

    /**
     * This method calculates change
     * @param dispenseAll : if true dispense all money user has inputted or else dispense only balance
     */
    private void calculateChange(boolean dispenseAll) {
        // When user cancels the transaction we need to dispense all the collected cash
        if (dispenseAll) {
            this.currentBalance = collectedCash;
        }
        while (this.currentBalance > 0) {
            if (this.currentBalance >= 1.0) {
                //subtract 1 from currentBalance and reduce coin inventory
                Double denomination = 1.0;
                this.currentBalance = this.currentBalance - denomination;
                // if coin inventory does not have it throw inssufficient balance exception
                if (this.coinInventory.get(denomination) > 0) {
                    this.coinInventory.put(denomination, this.coinInventory.get(denomination) - 1);
                } else {
                    throw new NotSufficientChangeException("Not sufficient change please try another product");
                }
            } else if (this.currentBalance >= 0.25) {
                //subtract 0.25 from currentBalance and reduce coin inventory
                Double denomination = 0.25;
                this.currentBalance = this.currentBalance - denomination;
                // if coin inventory does not have it throw inssufficient balance exception
                if (this.coinInventory.get(denomination) > 0) {
                    this.coinInventory.put(denomination, this.coinInventory.get(denomination) - 1);
                } else {
                    throw new NotSufficientChangeException("Not sufficient change please try another product");
                }

            } else if (this.currentBalance >= 0.10) {
                //subtract 0.10 from currentBalance and reduce coin inventory
                Double denomination = 0.10;
                this.currentBalance = this.currentBalance - denomination;
                // if coin inventory does not have it throw inssufficient balance exception
                if (this.coinInventory.get(denomination) > 0) {
                    this.coinInventory.put(denomination, this.coinInventory.get(denomination) - 1);
                } else {
                    throw new NotSufficientChangeException("Not sufficient change please try another product");
                }

            } else if (this.currentBalance >= 0.05) {
                //subtract 0.05 from currentBalance and reduce coin inventory
                Double denomination = 0.05;
                this.currentBalance = this.currentBalance - denomination;
                if (this.coinInventory.get(denomination) > 0) {
                    this.coinInventory.put(denomination, this.coinInventory.get(denomination) - 1);
                } else {
                    throw new NotSufficientChangeException("Not sufficient change please try another product");
                }

            } else {
                throw new NotSufficientChangeException("Not sufficient change please try another product");
            }
        }
    }

    /**
     *This method dispenses item if balance is fullfilled if not it throws exception
     */
    public void dispenseItem() {
        if (this.currentBalance >= 0) {
            dispenseChange();
        } else {
            throw new NotsufficientBalanceException("Price for item: " + this.currentItemSelected.getName() + " is " + this.currentItemSelected.getPrice() + "please enter sufficient balance");
        }
        //if its reached here that means items are not sold out and change has been dispense and is fully paid
        Slot slot = this.getItemInventory().get(this.selectedSlot);
        int currentItemCount = slot.getItemCount();
        slot.setItemCount(currentItemCount - 1);
        this.itemInventory.put(this.selectedSlot, slot);
        System.out.println("Item dispensed : " + this.selectedSlot + " successfully ");
    }

    /**
     * This method provides functionality to cancel transaction and dispense any balance
     */
    public void cancelTransaction() {
        calculateChange(true);
        this.currentBalance = 0.0;
        this.collectedCash = 0.0;
    }

    @Override
    public String toString() {
        return "VendingMachine{" +
                "itemInventory=" + itemInventory +
                ", coinInventory=" + coinInventory +
                '}';
    }
}