package com.example.springbootreact.pw.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RESOURCES")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(nullable = true)
    private String Name;

    @Column(nullable = true)
    private String Status;

    @Column(nullable = true)
    private String Note;

    @ManyToOne
    @JoinColumn(name = "TypeId", referencedColumnName = "Id", nullable = true)
    private ResourceType resourceType;

    @OneToOne
    @JoinColumn(name = "EmpId", referencedColumnName = "Id", nullable = true)
    private Employee employee;
}
