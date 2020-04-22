package com.alertinnovation.vending.vendingStates;

import com.alertinnovation.vending.Exceptions.DispenseChangeException;
import com.alertinnovation.vending.Exceptions.DispenseItemException;
import com.alertinnovation.vending.Exceptions.SelectItemException;
import com.alertinnovation.vending.Exceptions.SoldOutException;
import com.alertinnovation.vending.vendingMachine.VendingMachine;

public class Ready implements VendingStates {
    private VendingMachine vendingMachine;

    public Ready(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        this.vendingMachine.setCurrentBalance(0.0);
        this.vendingMachine.setCollectedCash(0.0);
    }

    @Override
    public void selectItemFromSlot(String slotName) {
        this.vendingMachine.setState(new SelectItem(vendingMachine));
        this.vendingMachine.selectItemFromSlot(slotName);
    }

    @Override
    public void collectMoneyForItem(Double cash) {
        throw new SelectItemException("Select a item before entering cash");
    }

    @Override
    public void dispenseChange() {
        throw new DispenseChangeException("Change cannot be dispensed until selection is made");
    }

    @Override
    public void dispenseItemFromSlot() {
        throw new DispenseItemException("Items cannot be dispensed until selection is made");
    }

    @Override
    public void transactionCancelled() {
        System.out.println("Transaction cancelled successfully");
        this.vendingMachine.setState(new Ready(vendingMachine));
    }
}
