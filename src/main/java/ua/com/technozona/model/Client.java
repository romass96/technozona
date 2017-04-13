package ua.com.technozona.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.com.technozona.enums.RoleEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Entity
@Table(name = "clients")
public class Client extends Model implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "vkontakte")
    private String vkontakte;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "google")
    private String google;

    @Transient
    private final RoleEnum role = RoleEnum.CLIENT;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "client",
            cascade = CascadeType.REMOVE
    )
    private List<Order> clientOrders = new ArrayList<>();
//
//    @OneToMany(
//            fetch = FetchType.LAZY,
//            mappedBy = "manager",
//            cascade = CascadeType.REMOVE
//    )
//    private List<Order> managerOrders = new ArrayList<>();

    public Client() {
        this("", "", "");
    }

    public Client(
            final String name,
            final String email,
            final String phone
    ) {
        super();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = "";
        this.vkontakte = "";
        this.facebook = "";
        this.google = "";
    }

    @Override
    public String toString() {
        return "Name: " + this.name
                + "\nEmail: " + this.email
                + "\nPhone: " + this.phone;
    }

    /**
     * Генерирует строку для конечного сравнения пользователей
     * в методе equals() родительского класса.
     * Переопределенный метод родительского класса {@link Model}.
     *
     * @return Значение типа {@link String} -
     * имя пользователя + электронная почта + номер телефона.
     */
    @Override
    public String toEquals() {
        return getName() + getEmail() + getPhone();
    }

    /**
     * Возвращает значение типа boolean в зависемости от срока действия аккаунта.
     * Реализованый метод интерфейса {@link UserDetails}.
     *
     * @return {@code true} - если текущий аккаунт работоспособный.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    /**
     * Возвращает значение типа boolean от того,
     * заблокирован текущий аккаунт (пользователь) или нет.
     * Реализованый метод интерфейса {@link UserDetails}.
     *
     * @return {@code true} - если текущий аккаунт не заблокирован.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Возвращает значение типа boolean от того, активны ли права (полномичия)
     * данного аккаунта или нет.
     * Реализованый метод интерфейса {@link UserDetails}.
     *
     * @return {@code true} - если срок прав текущего аккаунта не истек.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Возвращает значение типа boolean от того,
     * активный ли текущий аккаунт или нет.
     * Реализованый метод интерфейса {@link UserDetails}.
     *
     * @return {@code true} - если текущий аккаунт активный.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Возвращает список всех ролей пользователя через объект-обертку
     * класса SimpleGrantedAuthority.
     * Реализованый метод интерфейса {@link UserDetails}.
     *
     * @return Объект типа {@link List} -
     * список ролей пользователя.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
        return roles;
    }

    public void initialize(
            final String name,
            final String password,
            final String email,
            final String phone,
            final String vkontakte,
            final String facebook,
            final String google
    ) {
        setName(name);
        setPassword(password);
        setEmail(email);
        setPhone(phone);
        setVkontakte(vkontakte);
        setFacebook(facebook);
        setGoogle(google);
    }

    /**
     * Возвращает имя пользователя.
     *
     * @return Значение типа {@link String} - имя пользователя.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Устанавливает имя пользователя.
     *
     * @param name Имя пользователя.
     */
    public void setName(final String name) {
        this.name = isNotBlank(name) ? name : "";
    }

    /**
     * Возвращает пароль пользователя.
     *
     * @return Значение типа {@link String} - пароль пользователя.
     */
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public void setPassword(final String password) {
        this.password = isNotBlank(password) ? password : "";
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = isNotBlank(email) ? email : "";
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(final String phone) {
        this.phone = isNotBlank(phone) ? phone : "";
    }

    public String getVkontakte() {
        return this.vkontakte;
    }

    public void setVkontakte(final String vkontakte) {
        this.vkontakte = isNotBlank(vkontakte) ? vkontakte : "";
    }

    public String getFacebook() {
        return this.facebook;
    }

    public void setFacebook(final String facebook) {
        this.facebook = isNotBlank(facebook) ? facebook : "";
    }

    public String getGoogle() {
        return this.google;
    }

    public void setGoogle(final String google) {
        this.google = isNotBlank(google) ? google : "";
    }

    /**
     * Конвертирует список заказов, которые оформил
     * текущий клиент, в список только для чтений и возвращает его.
     *
     * @return Объект типа {@link List} - список заказов только для чтения
     * или пустой список.
     */
//    public List<Order> getClientOrders() {
//        return getUnmodifiableList(this.clientOrders);
//    }
//
//    /**
//     * Устанавливает список заказов, которые оформил текущий клиент.
//     */
//    public void setClientOrders(final List<Order> clientOrders) {
//        this.clientOrders = clientOrders;
//    }
//
//    /**
//     * Конвертирует список заказов, которые обработал текущий менеджер,
//     * в список только для чтений и возвращает его.
//     */
//    public List<Order> getManagerOrders() {
//        return getUnmodifiableList(this.managerOrders);
//    }
//
//    /**
//     * Устанавливает список заказов, которые обработал текущий менеджер.
//     */
//    public void setManagerOrders(final List<Order> managerOrders) {
//        this.managerOrders = managerOrders;
//    }
}

