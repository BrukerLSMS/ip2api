package com.ipa.ip2.api.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * This class represents the basic "user" object in AppFuse that allows for authentication
 * and user management.  It implements Acegi Security's UserDetails interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *         Updated by Dan Kibler (dan@getrolling.com)
 *         Extended to implement Acegi UserDetails interface
 *         by David Carter david@carter.net
 */
@Entity
@Table(name="app_user")
public class User implements Serializable {
    private static final long serialVersionUID = 3832626162173359411L;

    private Long id;
    private String username;                    // required
    private String password;                    // required
    private String confirmPassword;
    private String passwordHint;
    private String firstName;                   // required
    private String lastName;                    // required
    private String email;                       // required; unique
    private String phoneNumber;
    private String website;
    private Address address = new Address();
    private Integer version;
    private boolean enabled = true;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;
    
    /**
     * Default constructor - creates a new instance with no values set.
     */
    public User() {}

    /**
     * Create a new instance and set the username.
     * @param username login name for user.
     */
    public User(final String username) {
        this.username = username;
    }

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @Column(nullable=false,length=50,unique=true)
    public String getUsername() {
        return username;
    }

    @Column(nullable=false)
    public String getPassword() {
        return password;
    }

    @Transient
    public String getConfirmPassword() {
        return confirmPassword;
    }

    @Column(name="password_hint")
    public String getPasswordHint() {
        return passwordHint;
    }

    @Column(name="first_name",nullable=false,length=50)
    public String getFirstName() {
        return firstName;
    }

    @Column(name="last_name",nullable=false,length=50)
    public String getLastName() {
        return lastName;
    }

    @Column(nullable=false,unique=true)
    public String getEmail() {
        return email;
    }

    @Column(name="phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    /**
     * Returns the full name.
     * @return firstName + ' ' + lastName
     */
    @Transient
    public String getFullName() {
        return firstName + ' ' + lastName;
    }
    
    /**
     * Returns the full name.
     * @return firstName + ' ' + lastName
     */
    @Transient
    public String getFullNameWithUsername() {
        return username + '@' + firstName + ' ' + lastName;
    }

    @Embedded
    public Address getAddress() {
        return address;
    }

    @Version
    public Integer getVersion() {
        return version;
    }
    
    @Column(name="account_enabled")
    public boolean isEnabled() {
        return enabled;
    }
    
    @Column(name="account_expired",nullable=false)
    public boolean isAccountExpired() {
        return accountExpired;
    }
    

    @Transient
    public boolean isAccountNonExpired() {
        return !isAccountExpired();
    }

    @Column(name="account_locked",nullable=false)
    public boolean isAccountLocked() {
        return accountLocked;
    }
    

    @Transient
    public boolean isAccountNonLocked() {
        return !isAccountLocked();
    }

    @Column(name="credentials_expired",nullable=false)
    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }
    

    @Transient
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setPasswordHint(String passwordHint) {
        this.passwordHint = passwordHint;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }
    
    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        final User user = (User) o;

        return !(username != null ? !username.equals(user.getUsername()) : user.getUsername() != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (username != null ? username.hashCode() : 0);
    }

}
