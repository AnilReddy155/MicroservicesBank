package com.cg.bms;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Test {
    public static void main(String[] args) {
        String dateString = "28/May/2024:00:10:39 +0800";
        
        // Define the date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z");
        
        
        
        // Parse the date string
       // LocalDateTime parsedDate = LocalDateTime.parse(dateString, formatter);
        //System.out.println("Parsed Date: " + parsedDate);
        
        ZonedDateTime parsed =  ZonedDateTime.parse(dateString, formatter);
        System.out.println(parsed.toInstant().toEpochMilli());
        // Format the date back to the original string format
        //String formattedDate = parsedDate.format(formatter);
        
        // Print the formatted date
        //System.out.println("Formatted Date: " + formattedDate);
    }
}
