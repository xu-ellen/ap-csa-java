package com.nighthawk.spring_portfolio.mvc.calendar;

// Prototype Implementation

public class APCalendar {

    /** Returns true if year is a leap year and false otherwise.
     * isLeapYear(2019) returns False
     * isLeapYear(2016) returns True
     */          
    public static boolean isLeapYear(int year) {
        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
            return true;
        }
        return false;
    }

    /** Helper function for number of days in year based on isLeapYear */
    public static int daysInYear(int year) {
        if (isLeapYear(year)) {
            return 366;
        }
        return 365;
    }
        
    /** Returns the value representing the day of the week 
     * 0 denotes Sunday, 
     * 1 denotes Monday, ..., 
     * 6 denotes Saturday. 
     * firstDayOfYear(2019) returns 2 for Tuesday.
    */
    private static int firstDayOfYear(int year) {
        int day = 1;    // 1 AD is a Monday

        for (int i = 1; i < year; i++) {    
            if (isLeapYear(i)) {
                day = (day + 2) % 7;
            }
            else {
                day = (day + 1) % 7;
            }
        }
        return day;
    }

    /** Returns n, where month, day, and year specify the nth day of the year.
     * This method accounts for whether year is a leap year. 
     * dayOfYear(1, 1, 2019) return 1
     * dayOfYear(3, 1, 2017) returns 60, since 2017 is not a leap year
     * dayOfYear(3, 1, 2016) returns 61, since 2016 is a leap year. 
    */ 
    public static int dayOfYear(int month, int day, int year) {
        int dayofyear = 0;

        for (int i=1; i<month; i++) {
            if (i==1 || i==3 || i==5 || i==7 || i==8 || i==10 || i==12) {
                dayofyear += 31;
            }
            else if (i==2 && !isLeapYear(year)) {
                dayofyear += 29;
            }
            else {
                dayofyear += 30;
            }
        }
        return dayofyear + day;
    }

    /** Returns the number of leap years between year1 and year2, inclusive.
     * Precondition: 0 <= year1 <= year2
    */ 
    public static int numberOfLeapYears(int year1, int year2) {
        
        int count = 0;
        for (int i=year1; i<=year2; i++) {
            if (isLeapYear(i)) {
                count++;
            }
        }
        return count;
    }

    /** Returns the value representing the day of the week for the given date
     * Precondition: The date represented by month, day, year is a valid date.
    */
    public static int dayOfWeek(int month, int day, int year) { 
        return (dayOfYear(month, day, year) + firstDayOfYear(year)) % 7;
    }

    /** Return a random date **/
    public static String randomDate() {
        int year = (int) (Math.random() * 2022);
        int month = (int) (Math.random() * 12) + 1;
        int day = (int) (Math.random() * 28) + 1;
        return month + "/" + day + "/" + year;
    }

    /** Return a random time **/
    public static String randomTime() {
        int hour = (int) (Math.random() * 24);
        int minute = (int) (Math.random() * 60);
        int second = (int) (Math.random() * 60);
        return hour + ":" + minute + ":" + second;
    }

    /** Tester method */
    public static void main(String[] args) {
        // Private access modifiers
        System.out.println("firstDayOfYear: " + APCalendar.firstDayOfYear(2019));
        System.out.println("dayOfYear: " + APCalendar.dayOfYear(3, 1, 2022));

        // Public access modifiers
        System.out.println("isLeapYear: " + APCalendar.isLeapYear(2022));
        System.out.println("numberOfLeapYears: " + APCalendar.numberOfLeapYears(2000, 2022));
        System.out.println("dayOfWeek: " + APCalendar.dayOfWeek(1, 1, 2022));
        System.out.println("randomDate: " + APCalendar.randomDate());
        System.out.println("randomTime: " + APCalendar.randomTime());
    }
}