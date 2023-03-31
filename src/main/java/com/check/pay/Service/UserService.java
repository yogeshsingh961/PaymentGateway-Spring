package com.check.pay.Service;

import com.check.pay.Entity.Transaction;
import com.check.pay.Entity.User;
import com.check.pay.Repository.UserRepository;
import com.check.pay.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
  public void addUser(User user){
      userRepository.save(user);
  }
  public int getTotalSuccessfulTxn(int userId){
      User user=userRepository.findById(userId).get();
      List<Transaction> transactions=user.getTransactions();
      int count=0;
      for(Transaction transaction:transactions){
          if(transaction.getTransactionStatus()== TransactionStatus.SUCCESSFUL){
              count+=transaction.getAmount();
          }
      }
      return count;
  }
  public int getUserWithMaxRefund(){
      List<User> userlist=userRepository.findAll();

      User reqUser=null;
      int max=Integer.MIN_VALUE;

      for(User user: userlist){
          int count=0;
          List<Transaction> transactions=user.getTransactions();
          for(Transaction transaction: transactions){
              if(transaction.getRefundedAmount()!=0){
                  count+=transaction.getRefundedAmount();
                  if(count>max){
                      max=count;
                      reqUser=user;
                  }
              }
          }
      }
      return reqUser.getId();

  }
}
