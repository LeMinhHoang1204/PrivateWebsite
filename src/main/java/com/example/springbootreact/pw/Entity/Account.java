package com.example.springbootreact.pw.Entity;

import jakarta.persistence.*;
import lombok.*;

import org.springframework.data.relational.core.sql.In;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACCOUNT")
public class Account implements UserDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer Id;

        @Column(nullable = true)
        private String Password;

        @Column(nullable = true)
        private Integer Status;

        @Column(nullable = true)
        private String Role;

        @Column(nullable = true)
        private String ImagePath;

        @Column(nullable = true)
        private String Bio;

        @OneToOne
        @JoinColumn(name = "empId",  referencedColumnName = "Id")
        private Employee employee;

        public Integer getEmpId() {
                return employee.getId();
        }

        public void setEmpId(int empId) {
                Employee employee = new Employee();
                employee.setId(empId);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of(new SimpleGrantedAuthority(Role));
        }

        @Override
        public String getUsername() {
                return String.valueOf(employee.getId());
        }

        @Override
        public boolean isAccountNonExpired() {
                return true;
        }

        @Override
        public boolean isAccountNonLocked() {
                return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return true;
        }

        @Override
        public boolean isEnabled() {
                return true;
        }


}
