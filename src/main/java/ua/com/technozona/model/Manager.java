package ua.com.technozona.model;


import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "managers")
@PrimaryKeyJoinColumn(name = "manager_id", referencedColumnName = "id")
public class Manager extends Employee {

}
