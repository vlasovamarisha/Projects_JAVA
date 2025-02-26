package org.example;

public class All {
    public static void main(String[] args) throws InterruptedException {
        Electrostation electrostation = new Electrostation();
        Storage storage = new Storage();
        Transport transport = new Transport();

        while (true) {
            try {
                electrostation.energy();
                //transport.recharge(storage.plusEnergy());
                //electrostation.recharge(transport. );
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }
}