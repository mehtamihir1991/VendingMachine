package com.alertinnovation.vending.vendingStates;

import com.alertinnovation.vending.Exceptions.DispenseChangeException;
import com.alertinnovation.vending.Exceptions.DispenseItemException;
import com.alertinnovation.vending.Exceptions.SelectItemException;
import com.alertinnovation.vending.vendingMachine.VendingMachine;

public class SelectItem implements VendingStates{
    private VendingMachine vendingMachine;
    public SelectItem(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectItemFromSlot(String slotName) {
        this.vendingMachine.setState(new SelectItem(vendingMachine));
        this.vendingMachine.selectItemFromSlot(slotName);
    }

    @Override
    public void collectMoneyForItem(Double cash) {
        this.vendingMachine.setState(new CollectMoney(vendingMachine));
        this.vendingMachine.collectCash(cash);
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
