package org.example;

public class Electrostation {
    private int charge = 100;

        public void energy() throws Exception {
            if (charge <= 1) throw new Exception("No power");
            charge -= 10;
            System.out.printf(" Power = " + charge + ",");
        }
        public void recharge (int energy) {
            charge = Math.min (charge+energy, 100);
            System.out.printf("Charge is " + charge);
    }
}