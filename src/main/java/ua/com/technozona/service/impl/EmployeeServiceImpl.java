package ua.com.technozona.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.technozona.exception.BadRequestException;
import ua.com.technozona.exception.WrongInformationException;
import ua.com.technozona.model.Admin;
import ua.com.technozona.model.Employee;
import ua.com.technozona.model.Manager;
import ua.com.technozona.repository.AdminRepository;
import ua.com.technozona.repository.ManagerRepository;
import ua.com.technozona.service.interfaces.EmployeeService;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.trim;

@Service
@ComponentScan(basePackages = "ua.com.technozona.repository")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    @Transactional
    public void addAdmin(Admin admin) {
        if (admin!=null&&isEmployeeExists(admin.getEmail())){
            adminRepository.save(admin);
        }
    }

    @Override
    @Transactional
    public void addManager(Manager manager) {
        if (manager!=null&&isEmployeeExists(manager.getEmail())){
            managerRepository.save(manager);
        }
    }

    @Override
    @Transactional
    public void addAdmins(List<Admin> admins) {
        if (admins != null && !admins.isEmpty()) {
            this.adminRepository.save(admins);
        }
    }

    @Override
    @Transactional
    public void addManagers(List<Manager> managers) {
        if (managers != null && !managers.isEmpty()) {
            this.managerRepository.save(managers);
        }
    }

    @Override
    @Transactional
    public void updateAdmin(Admin admin) {
        if (admin!=null){
            adminRepository.save(admin);
        }
    }

    @Override
    @Transactional
    public void updateManager(Manager manager) {
        if (manager!=null){
            managerRepository.save(manager);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Admin getAdmin(Long id) throws WrongInformationException, BadRequestException {
        if (id == null) {
            throw new WrongInformationException("No admin id!");
        }
        final Admin admin = this.adminRepository.findOne(id);
        if (admin == null) {
            throw new BadRequestException("Can't find admin by id " + id + "!");
        }
        return this.adminRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Manager getManager(Long id) throws WrongInformationException, BadRequestException  {
        if (id == null) {
            throw new WrongInformationException("No manager id!");
        }
        final Manager manager = this.managerRepository.findOne(id);
        if (manager == null) {
            throw new BadRequestException("Can't find manager by id " + id + "!");
        }
        return this.managerRepository.findOne(id);
    }


    @Override
    @Transactional(readOnly = true)
    public Admin getAdminByEmail(String email) {
        if (isBlank(email)) {
            throw new WrongInformationException("No email!");
        }
        Admin admin = this.adminRepository.findByEmail(email);
        if (admin == null) {
            throw new BadRequestException("Can't find admin by email " + email + "!");
        }
        return admin;
    }

    @Override
    @Transactional(readOnly = true)
    public Manager getManagerByEmail(String email) {
        if (isBlank(email)) {
            throw new WrongInformationException("No email!");
        }
        Manager manager = this.managerRepository.findByEmail(email);
        if (manager == null) {
            throw new BadRequestException("Can't find manager by email " + email + "!");
        }
        return manager;
    }

    @Override
    @Transactional(readOnly = true)
    public Admin getMainAdministrator() {
        return adminRepository.findOne(1L);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.addAll(getAllAdmins());
        employees.addAll(getAllManagers());
        return employees;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    @Override
    @Transactional
    public void removeAdmin(Admin admin) {
        if (admin != null) {
            this.adminRepository.delete(admin);
        }
    }

    @Override
    @Transactional
    public void removeAdmin(Long id) {
        if (id == null) {
            throw new WrongInformationException("No admin id!");
        }
        this.adminRepository.delete(id);
    }

    @Override
    @Transactional
    public void removeManager(Manager manager) {
        if (manager != null) {
            this.managerRepository.delete(manager);
        }
    }

    @Override
    @Transactional
    public void removeManager(Long id) {
        if (id == null) {
            throw new WrongInformationException("No manager id!");
        }
        this.managerRepository.delete(id);
    }

    @Override
    @Transactional
    public void removeAdminByEmail(String email) {
        if (isBlank(email)) {
            throw new WrongInformationException("No email!");
        }
        this.adminRepository.deleteByEmail(email);
    }

    @Override
    @Transactional
    public void removeManagerByEmail(String email) {
        if (isBlank(email)) {
            throw new WrongInformationException("No email!");
        }
        this.managerRepository.deleteByEmail(email);
    }

    @Override
    @Transactional
    public void removeAllEmployees() {
        adminRepository.deleteAll();
        managerRepository.deleteAll();
    }

    @Override
    @Transactional
    public void removeAllAdmins() {
        adminRepository.deleteAll();
    }

    @Override
    @Transactional
    public void removeAllManagers() {
        managerRepository.deleteAll();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try{
            return getAdminByEmail(s);
        } catch (RuntimeException runtimeException1){
            try{
                return getManagerByEmail(s);
            } catch (RuntimeException runtimeException2){
                throw runtimeException2;
            }
        }
    }

    private boolean isEmployeeExists(String email){
        try{
            loadUserByUsername(email);
            return true;
        } catch (RuntimeException e){
            return false;
        }
    }
}
