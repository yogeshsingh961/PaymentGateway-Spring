package com.check.pay.Entity;

import com.check.pay.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String transactionId;
    private int leftAmount;
    private int amount;
    @CreationTimestamp
    private Date date;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
    private int refundedAmount;
    @ManyToOne
    @JoinColumn
    User user;

}
