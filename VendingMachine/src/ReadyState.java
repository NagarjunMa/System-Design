public class ReadyState implements VendingMachineState{

    private final VendingMachine vendingMachine;

    public ReadyState(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product){
        System.out.println("Product has been selected");
    }

    @Override
    public void insertCoin(Coin coin){
        vendingMachine.addCoin(coin);
        System.out.println("Inserted coin "+coin);
        checkPaymentStatus();
    }

    @Override
    public void insertNote(Note note){
        vendingMachine.addNote(note);
        System.out.println("Inserted notes "+note);
        checkPaymentStatus();
    }

    @Override
    public void dispenseProduct(){
        System.out.println("Payment needs to be completed...");
    }

    @Override
    public void returnChange(){
        System.out.println("Payment needs to be completed..");
    }

    private void checkPaymentStatus() {
        if (vendingMachine.getTotalPayment() >= vendingMachine.getSelectedProduct().getPrice()) {
            vendingMachine.setState(vendingMachine.getDispenseState());
        }
    }



}
