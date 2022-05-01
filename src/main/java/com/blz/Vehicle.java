package com.blz;

public class Vehicle {
   String name;
   Integer id;
   String color;
   String plateNumber;

   public Vehicle(String name, Integer id) {
      this.name = name;
      this.id = id;
   }
   public Vehicle(String name, Integer id, String color) {
      this.name = name;
      this.id = id;
      this.color = color;
   }

   public Vehicle(String name, Integer id, String color, String plateNumber) {
      this.name = name;
      this.id = id;
      this.color = color;
      this.plateNumber = plateNumber;
   }

   public String getColor() {
         return color;
   }
   public String getPlateNumber() {
      return plateNumber;
   }
   public String getName() {
      return name;
   }
}
