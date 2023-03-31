package com.check.pay.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String name;
    private String email;
    private String accountNo;
    private int totalAmount;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Transaction> transactions= new ArrayList<>();

}
