package tn.esprit.piproject.Config;

import java.util.UUID;

public class UUIDGenerator {

    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        System.out.println("Randomly generated UUID: " + uuid);
    }
}