


interface VendingMachineState {
    void insertMoney(VendingMachineContext machine);
    void selectProduct(VendingMachineContext machine);
    void dispenseProduct(VendingMachineContext machine); 
}

class NoMoneyState implements VendingMachineState {
    @Override
    public void insertMoney(VendingMachineContext machine) {
        if(machine.getItemCount() > 0){
            System.out.println("Money inserted. Transitioning to HasMoneyState.");
            machine.setState(new HasMoneyState());
            return;
        }else{
            System.out.println("Machine is SOLD OUT ❌");
            machine.setState(new SoldOutState());
            return;
        } 
    }

    @Override
    public void selectProduct(VendingMachineContext machine) {
        System.out.println("Please insert money first.");
    }

    @Override
    public void dispenseProduct(VendingMachineContext machine) {
        System.out.println("Please insert money first.");
    }
}

class HasMoneyState implements VendingMachineState {
    @Override
    public void insertMoney(VendingMachineContext machine) {
        System.out.println("Money already inserted. Please select a product.");
    }

    @Override
    public void selectProduct(VendingMachineContext machine) {
        System.out.println("Product selected. Transitioning to DispensingState.");
        machine.setState(new DispensingState());
    }

    @Override
    public void dispenseProduct(VendingMachineContext machine) {
        System.out.println("Please select a product first.");
    } 
}

class DispensingState implements VendingMachineState {
    @Override
    public void insertMoney(VendingMachineContext machine) {
        System.out.println("Currently dispensing. Please wait.");
    }

    @Override
    public void selectProduct(VendingMachineContext machine) {
        System.out.println("Currently dispensing. Please wait.");
    }

    @Override
    public void dispenseProduct(VendingMachineContext machine) {
        machine.releaseItem();

        if(machine.getItemCount() == 0){
            System.out.println("Machine is now SOLD OUT ❌");
            machine.setState(new SoldOutState());
            return;
        }
        machine.setState(new NoMoneyState());
    } 
}

class SoldOutState implements VendingMachineState {
    @Override
    public void insertMoney(VendingMachineContext machine) {
        System.out.println("Machine is SOLD OUT ❌");
    }

    @Override
    public void selectProduct(VendingMachineContext machine) {
        System.out.println("Machine is SOLD OUT ❌");
    }

    @Override
    public void dispenseProduct(VendingMachineContext machine) {
        System.out.println("Machine is SOLD OUT ❌");
    } 
}

class VendingMachineContext {
    private VendingMachineState state;
    private int itemCount ; 

    public VendingMachineContext(int itemCount) {
        this.itemCount = itemCount;
        this.state = new NoMoneyState();
    }

    public int getItemCount(){
        return itemCount;
    }
    public void setState(VendingMachineState state){
        this.state = state;
    }

    public void releaseItem(){
        if(itemCount > 0){
            itemCount--;
            System.out.println("Item released. Remaining items: " + itemCount);
        }
    }
    public void insertCoin(){
        state.insertMoney(this);
    }

    public void selectItem(){
        state.selectProduct(this);
    }

    public void dispenseItem(){
        state.dispenseProduct(this);
    }
}


class VendingState {
    public static void main(String[] args) {
        VendingMachineContext machine = new VendingMachineContext(1);
        machine.insertCoin();
        machine.selectItem();
        machine.dispenseItem();

        machine.insertCoin();
        machine.selectItem();
        machine.dispenseItem();



    }
}