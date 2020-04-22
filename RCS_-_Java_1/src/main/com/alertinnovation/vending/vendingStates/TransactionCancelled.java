package com.alertinnovation.vending.vendingStates;

import com.alertinnovation.vending.Exceptions.SystemNotReadyException;
import com.alertinnovation.vending.Exceptions.TransactionCannotBeCancelledException;
import com.alertinnovation.vending.vendingMachine.VendingMachine;

public class TransactionCancelled implements VendingStates{
    private VendingMachine vendingMachine;
    public TransactionCancelled(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectItemFromSlot(String slotName){
        throw new SystemNotReadyException("Cannot select item:Transaction is being cancelled. Wait for system to get ready");
    }

    @Override
    public void collectMoneyForItem(Double cash) {
        throw new SystemNotReadyException("Cannot Collect money:Transaction is being cancelled. Wait for system to get ready");
    }

    @Override
    public void dispenseChange() {
        throw new SystemNotReadyException("Cannot dispense change:Transaction is being cancelled. Wait for system to get ready");
    }

    @Override
    public void dispenseItemFromSlot() {
        throw new SystemNotReadyException("Cannot dispense item:Transaction is being cancelled. Wait for system to get ready");
    }

    @Override
    public void transactionCancelled() {
        throw new TransactionCannotBeCancelledException("Transaction already cancelled");
    }
}
