package com.alertinnovation.vending.vendingStates;

import com.alertinnovation.vending.Exceptions.CoinNotSupportedException;
import com.alertinnovation.vending.Exceptions.DispenseChangeException;
import com.alertinnovation.vending.Exceptions.SelectItemException;
import com.alertinnovation.vending.vendingMachine.VendingMachine;

public class DispenseChange implements VendingStates{
private VendingMachine vendingMachine;
    public DispenseChange(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectItemFromSlot(String slotName) {
        throw new SelectItemException("Items cannot be selected while change is being dispensed");
    }

    @Override
    public void collectMoneyForItem(Double cash) {
        throw new CoinNotSupportedException("Coins cannot be entered while change is being dispensed");
    }

    @Override
    public void dispenseChange() {
        this.vendingMachine.setState(new DispenseChange(vendingMachine));
        this.vendingMachine.dispenseChange();
    }

    @Override
    public void dispenseItemFromSlot() {
        this.vendingMachine.setState(new DispenseItem(vendingMachine));
        this.vendingMachine.dispenseItem();
        this.vendingMachine.setState(new Ready(vendingMachine));
    }

    @Override
    public void transactionCancelled() {
        System.out.println("Transaction cancelled successfully");
        this.vendingMachine.cancelTransaction();
        this.vendingMachine.setState(new Ready(vendingMachine));
    }
}
