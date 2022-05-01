package com.blz;

public class Vehicle {
   String name;
   Integer id;
   String color;

   public Vehicle(String name, Integer id) {
      this.name = name;
      this.id = id;
   }
   public Vehicle(String name, Integer id, String color) {
      this.name = name;
      this.id = id;
      this.color = color;
   }
   public String getColor() {
      return color;
   }
}
