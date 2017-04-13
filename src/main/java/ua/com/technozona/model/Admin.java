package ua.com.technozona.model;


import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
@PrimaryKeyJoinColumn(name = "admin_id", referencedColumnName = "id")
public class Admin extends Employee {
}
