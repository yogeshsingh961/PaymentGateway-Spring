package com.check.pay.Service;

import com.check.pay.Dto.TransactionRequestDto;
import com.check.pay.Entity.Transaction;
import com.check.pay.Entity.User;
import com.check.pay.Repository.TransactionRepository;
import com.check.pay.Repository.UserRepository;
import com.check.pay.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;
    public String addTransaction(TransactionRequestDto transactionRequestDto) throws Exception {
        Transaction transaction= new Transaction();
        transaction.setTransactionId(String.valueOf(UUID.randomUUID()));

        User user=null;
        try{
            user= userRepository.findById(transactionRequestDto.getUserId()).get();
        }catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setUser(user);
            transactionRepository.save(transaction);
            throw new Exception("User not found");
        }

        if(transactionRequestDto.getAmount()>user.getTotalAmount()){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setUser(user);
            transaction.setAmount(transactionRequestDto.getAmount());
            transaction.setLeftAmount(0);
            transaction.setRefundedAmount(user.getTotalAmount());

            transactionRepository.save(transaction);

            throw  new Exception("Insufficient Balance");
        }

        transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
        transaction.setAmount(transactionRequestDto.getAmount());
        transaction.setLeftAmount(user.getTotalAmount()-transactionRequestDto.getAmount());
        transaction.setUser(user);
        transaction.setRefundedAmount(0);
        user.setTotalAmount(user.getTotalAmount()-transactionRequestDto.getAmount());
        user.getTransactions().add(transaction);

        userRepository.save(user);
        return transaction.getAmount()+" has been debited";
    }

    public void deleteAllFailedTxn(){
        List<Transaction> transactionList= transactionRepository.findAll();

        for(Transaction transaction:transactionList){
            if(transaction.getTransactionStatus()==TransactionStatus.FAILED){
                transactionRepository.deleteById(transaction.getId());
            }
        }
    }
}
