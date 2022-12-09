package com.nighthawk.spring_portfolio.mvc.person;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.format.annotation.DateTimeFormat;

import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/*
Person is a POJO, Plain Old Java Object.
First set of annotations add functionality to POJO
--- @Setter @Getter @ToString @NoArgsConstructor @RequiredArgsConstructor
The last annotation connect to database
--- @Entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@TypeDef(name="json", typeClass = JsonType.class)
public class Person {
    
    // automatic unique identifier for Person record
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // email, password, roles are key attributes to login and authentication
    @NotEmpty
    @Size(min=5)
    @Column(unique=true)
    @Email
    private String email;

    @NotEmpty
    private String password;

    // @NonNull, etc placed in params of constructor: "@NonNull @Size(min = 2, max = 30, message = "Name (2 to 30 chars)") String name"
    @NonNull
    @Size(min = 2, max = 30, message = "Name (2 to 30 chars)")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    
    // create height and weight attributes
    @Column(unique=false)
    private int height;

    @Column(unique=false)
    private int weight;

    @Column(unique=false)
    private int goal_steps;

    /* HashMap is used to store JSON for daily "stats"
    "stats": {
        "2022-11-13": {
            "calories": 2200,
            "steps": 8000
        }
    }
    */
    @Type(type="json")
    @Column(columnDefinition = "jsonb")
    private Map<String,Map<String, Object>> stats = new HashMap<>(); 
    

    // Constructor used when building object from an API
    public Person(String email, String password, String name, Date dob, int height, int weight, int goal_steps) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.height = height;
        this.weight = weight;
        this.goal_steps = goal_steps;
    }

    // A custom getter to return age from dob attribute
    public int getAge() {
        if (this.dob != null) {
            LocalDate birthDay = this.dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return Period.between(birthDay, LocalDate.now()).getYears(); }
        return -1;
    }

    // toString method to get attributes of class
    public String toString() {
        return ("{ \"email\": " + this.email + ", " + "\"password\": " + this.password + ", " + "\"name\": " + this.name + ", " + "\"dob\": " + this.dob + " }" );
    }

    public void addStat(String date, Map<String, Object> stat) {
        this.stats.put(date, stat);
    }

    public Map<String, Object> getStat(String date) {
        return this.stats.get(date);
    }

    public int calculateBMI() {
        double heightInMeters = this.height / 100.0;
        double bmi = this.weight / (heightInMeters * heightInMeters);
        return (int) bmi;
    }

    public String getBMItoString() {
        return ("{ \"bmi\": " + this.calculateBMI() + " }");
    }

    public int calculateCalories() {
        int age = this.getAge();
        int calories = 0;
        if (age < 18) {
            calories = 2000;
        } else if (age < 30) {
            calories = 2500;
        } else if (age < 50) {
            calories = 2000;
        } else {
            calories = 1800;
        }
        return calories;
    }

    public String getCaloriesToString() {
        return ("{ \"calories\": " + this.calculateCalories() + " }");
    }

    public int calculateCaloriesBurned(int steps) {
        int calories = 0;
        if (steps < 5000) {
            calories = 1000;
        } else if (steps < 10000) {
            calories = 2000;
        } else if (steps < 15000) {
            calories = 3000;
        } else {
            calories = 4000;
        }
        return calories;
    }

    public int getPercentageofGoal(int steps) {
        int percentage = (steps * 100) / this.goal_steps;
        return percentage;
    }

    // Hack: tester method
    public static void main(String[] args) {

        Person allArgs = new Person("test@test.com", "password", "Test", new Date(), 180, 80, 10000);
        System.out.println(allArgs);

        Person noArgs = new Person();
        System.out.println(noArgs);
    }

}