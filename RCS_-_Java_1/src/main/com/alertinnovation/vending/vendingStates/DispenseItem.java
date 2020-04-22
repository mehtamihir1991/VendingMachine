package com.alertinnovation.vending.vendingStates;

import com.alertinnovation.vending.Exceptions.CoinNotSupportedException;
import com.alertinnovation.vending.Exceptions.DispenseChangeException;
import com.alertinnovation.vending.Exceptions.SelectItemException;
import com.alertinnovation.vending.Exceptions.TransactionCannotBeCancelledException;
import com.alertinnovation.vending.vendingMachine.VendingMachine;

public class DispenseItem implements VendingStates{
    private VendingMachine vendingMachine;
    public DispenseItem(VendingMachine vendingMachine) {
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
        throw new DispenseChangeException("Change cannot be dispensed while items are dispensed");
    }

    @Override
    public void dispenseItemFromSlot() {
        this.vendingMachine.setState(new DispenseItem(vendingMachine));
        this.vendingMachine.dispenseItem();
        this.vendingMachine.setState(new Ready(vendingMachine));
    }

    @Override
    public void transactionCancelled() {
        throw new TransactionCannotBeCancelledException("Transaction cannot be cancelled while items are dispensed");
    }
}
