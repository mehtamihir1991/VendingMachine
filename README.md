# VendingMachine
Vending machine system comprises of different states as stated in problems statement. Six important states are

# States
Ready: System is ready <br />
Select item: User can select item
Collect Money: User can add money for the item selected <br />
Dispense change: if User inputs more or cancels transactions it dispenses change <br />
Dispense item: This dispenses item <br />
Cancel transaction: This is responsible for cancelling transaction <br />
 
# Entities
There are two entities involved <br />
Slot: This consists of various items and has maximum capacity of 15 <br />
Item: This consists of actual item which has price, name and sky associated with it and it sits in particular slot <br />
Item Inventory: This consists of map which has all slots mapped with Items <br />
Coin Inventory: This consists of map which has all coins supported and its count <br />

# Time complexity analysis
In order to look up slots and coin HashMap is used to so that system can perform with optimal time complexity i.e., O(1)

# Design Pattern
In order to comply to S.O.L.I.D design principles the best design to fit all functionality is “State design pattern” as we have identified there are 6 states which requires transitions among themselves.
