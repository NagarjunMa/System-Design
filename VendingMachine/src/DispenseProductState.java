public class DispenseProductState implements VendingMachineState{

    private final VendingMachine vendingMachine;

    public DispenseProductState(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product){
        System.out.println("Product needs to dispensed");
    }

    @Override
    public void insertCoin(Coin coin){
        System.out.println("Product needs to dispensed");
    }

    @Override
    public void insertNote(Note note){
        System.out.println("Product needs to dispensed");
    }

    @Override
    public void dispenseProduct(){
        vendingMachine.setState(vendingMachine.getReadyState());
        Product product = vendingMachine.getSelectedProduct();
        vendingMachine.inventory.updateQuantity(product, vendingMachine.inventory.getQuantity(product) -1);
        System.out.println("Product is dispensed");
        vendingMachine.setState(vendingMachine.getReturnChangeState());
    }

    @Override
    public void returnChange(){
        System.out.println("Payment needs to be completed..");
    }
}
