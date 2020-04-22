package com.alertinnovation.vending.vendingStates;

import com.alertinnovation.vending.Exceptions.SelectItemException;
import com.alertinnovation.vending.vendingMachine.VendingMachine;

public class CollectMoney implements VendingStates{
    private VendingMachine vendingMachine;
    public CollectMoney(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectItemFromSlot(String slotName) {
        throw new SelectItemException("Cancel transaction before selecting new item");
    }

    @Override
    public void collectMoneyForItem(Double cash) {
        this.vendingMachine.setState(new CollectMoney(vendingMachine));
        this.vendingMachine.collectCash(cash);
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
