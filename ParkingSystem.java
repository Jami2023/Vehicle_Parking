/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.util.*;

class Vehicle {
    String type;
    String number;
    int slot;
    
    public Vehicle(String type, String number, int slot) {
        this.type = type;
        this.number = number;
        this.slot = slot;
    }
    
    public String toString() {
        return "Vehicle: " + type + ", Number: " + number + ", Slot: " + slot;
    }
}

public class ParkingSystem {
    static int cardId = 1000;
    static HashMap<Integer, Vehicle> parkingData = new HashMap<>();
    static Queue<Integer> bikeSlots = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
    static Queue<Integer> carSlots = new LinkedList<>(Arrays.asList(6, 7, 8, 9, 10));
    
    public static void parkVehicle(String type, String number) {
        if (type.equalsIgnoreCase("Bike") && !bikeSlots.isEmpty()) {
            int slot = bikeSlots.poll();
            parkingData.put(cardId, new Vehicle(type, number, slot));
            System.out.println("Bike parked. Card ID: " + cardId++);
        } else if (type.equalsIgnoreCase("Car") && !carSlots.isEmpty()) {
            int slot = carSlots.poll();
            parkingData.put(cardId, new Vehicle(type, number, slot));
            System.out.println("Car parked. Card ID: " + cardId++);
        } else {
            System.out.println("No available slots for " + type);
        }
    }
    
    public static void retrieveVehicle(int card) {
        if (parkingData.containsKey(card)) {
            Vehicle v = parkingData.remove(card);
            if (v.type.equalsIgnoreCase("Bike")) bikeSlots.add(v.slot);
            else carSlots.add(v.slot);
            System.out.println("Vehicle retrieved: " + v);
        } else {
            System.out.println("Invalid card ID!");
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Park Vehicle\n2. Retrieve Vehicle\n3. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.println("Enter Vehicle Type (Bike/Car): ");
                    String type = sc.nextLine();
                    System.out.println("Enter Vehicle Number: ");
                    String number = sc.nextLine();
                    parkVehicle(type, number);
                    break;
                case 2:
                    System.out.println("Enter Card ID: ");
                    int card = sc.nextInt();
                    retrieveVehicle(card);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}