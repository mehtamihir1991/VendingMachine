package com.alertinnovation.vending.vendingStates;

/**
 * This interface provides all state transitions this are the 5 states supported
 * Ready: System is ready
 * Select item: User can select item
 * Collect Money: User can add money for the item selected
 * Dispense change: if User inputs more or cancells transactions it dispenses change
 * Dispense item: This dispenses item
 * Cancel transaction: This is responsible for cancelling transaction
 */
public interface VendingStates {
    /**
     * This method will help to select item from slot
     * @param slotName
     */
    public void selectItemFromSlot(String slotName);

    /**
     * This method accepts cash for item selected
     * @param cash
     */
    public void collectMoneyForItem(Double cash);

    /**
     * This method dispenses change if any
     */
    public void dispenseChange();

    /**
     * This method dispenses items from slot
     */
    public void dispenseItemFromSlot();

    /**
     * This method is used to cancel transaction
     */
    public void transactionCancelled();
}
