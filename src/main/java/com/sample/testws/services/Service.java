/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.testws.services;

import com.sample.testws.entidad.Person;
import com.sample.testws.entidad.SalaryObj;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author nicoc
 */
@Path("service")
public class Service {
    
    private static Map<Integer,Person> persons = new HashMap<Integer,Person>();
    
    static{
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            int id = i+1;
            person.setId(id);
            person.setFullName("My wonderfull Person" + id);
            int age = new Random().nextInt(40) + 1;
            person.setAge(age);
            person.setSalary(age);
            persons.put(id, person);
        }
    }
    
    @GET
    @Path("/getPersonByIdXML/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Person getPersonByIdXML(@PathParam("id")int id){
        return persons.get(id);
    }
    @GET
    @Path("/getPersonByIdJson/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonByIdJson(@PathParam("id") int id){
        return persons.get(id);
    }
    @GET
    @Path("/getAllPersonsInXML")
    @Produces(MediaType.APPLICATION_XML)
    public List<Person> getAllPersonsInXML(){
        return new ArrayList<Person>(persons.values());
    }
    
     @GET
    @Path("/getAllPersonsInJson")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPersonsInJSON(){
        return new ArrayList<Person>(persons.values());
    }
    
    @POST
    @Path("/addPersonInJson")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Person addPersonInJson(Person person){
        System.out.println(person.getId());
        persons.put(new Integer(person.getId()), person);
        return person;
    }
    
    @GET
    @Path("/salarioP")
    @Produces(MediaType.APPLICATION_JSON)
    public SalaryObj getSalaries(){
        int total = 0;
        System.out.println(persons.size());
        for (int i = 1; i <= persons.size(); i++) {
           total+= persons.get(i).getSalary();
        }
        System.out.println(total);
        return new SalaryObj(total);
    }
    
    @GET
    @Path("/avgSalary")
    @Produces(MediaType.APPLICATION_XML)
     public String getAvgSalaries(){
        int total = 0;
        System.out.println(persons.size());
        for (int i = 1; i <= persons.size(); i++) {
           total+= persons.get(i).getSalary();
        }
        System.out.println(total);
        total = total/persons.size();
        return "<averageSalary>" + total + "</averageSalary>";
    }
    
}
