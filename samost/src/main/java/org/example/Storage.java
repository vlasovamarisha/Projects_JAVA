package org.example;

public class Storage {
    private int energy = 100;

    public int plusEnergy() {
        int transfer = Math.min(energy, 50);
        energy -= transfer;
        System.out.printf("Storage plus is" + "energy. Energy is" + energy);
        return transfer;
    }
}
