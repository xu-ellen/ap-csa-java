package com.nighthawk.spring_portfolio.mvc.calendar;

/** Simple POJO 
 * Used to Interface with APCalendar
 * The toString method(s) prepares object for JSON serialization
 * Note... this is NOT an entity, just an abstraction
 */

class Date {
      private int year;
      private int month;
      private int day;
      private int dayOfWeek;
      private int dayOfYear;
      private boolean isLeapYear;
   
      public Date() {
         this.year = 1;
         this.month = 1;
         this.day = 1;
         this.dayOfWeek = 0;
         this.dayOfYear = 0;
         this.isLeapYear = false;
      }
   
      public Date(int year, int month, int day) {
         this.year = year;
         this.month = month;
         this.day = day;
         this.dayOfWeek = 0;
         this.dayOfYear = 0;
         this.isLeapYear = false;
      }
   
      public void setDate(int year, int month, int day) {
         this.year = year;
         this.month = month;
         this.day = day;
         this.setIsLeapYear(year);
         this.setDayofWeek(year, month, day);
         this.setDayofYear(year, month, day);
      }

      public void setIsLeapYear(int year) {
         this.isLeapYear = APCalendar.isLeapYear(year);
      }

      public void setDayofWeek(int year, int month, int day) {
         this.dayOfWeek = APCalendar.dayOfWeek(month, day, year);
      }

      public void setDayofYear(int year, int month, int day) {
         this.dayOfYear = APCalendar.dayOfYear(month, day, year);
      }

      /* isLeapYearToString formatted to be mapped to JSON */
      public String getDateToString(){
         return ( "{ \"year\": " + this.year + ", \"month\": " + this.month + ", \"day\": " + this.day + ", \"isLeapYear\": " + this.isLeapYear + ", \"dayOfWeek\": " + this.dayOfWeek + ", \"dayOfYear\": " + this.dayOfYear + " }" );
      }

      /* standard toString placeholder until class is extended */
      public String toString() { 
         return getDateToString(); 
      }

      public static void main(String[] args) {
         Date date = new Date();
         date.setDate(2022, 1, 1);
         System.out.println(date);
      }
}