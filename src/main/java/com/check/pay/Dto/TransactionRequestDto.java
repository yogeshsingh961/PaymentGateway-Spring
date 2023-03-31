package com.check.pay.Dto;

import com.check.pay.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDto {
    private int userId;
    private int amount;


}
