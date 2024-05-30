package com.example.springbootreact.pw.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RESOURCETYPE")
public class ResourceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(nullable = true)
    private String Name;

    @Column(nullable = true)
    private Integer Quantity;

    @Column(nullable = true)
    private Integer BorrowedQuantity;

    @Column(nullable = true)
    private String Note;
}
