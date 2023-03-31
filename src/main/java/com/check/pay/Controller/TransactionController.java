package com.check.pay.Controller;

import com.check.pay.Dto.TransactionRequestDto;
import com.check.pay.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
   @Autowired
    TransactionService transactionService;
   @PostMapping("/add")
   public String addTransaction(@RequestBody TransactionRequestDto transactionRequestDto) throws Exception {
        String ans="";
      try{
          ans=transactionService.addTransaction(transactionRequestDto);
      }catch (Exception e){
          return e.getMessage();
      }
       return ans;
   }

   @DeleteMapping("/delete")
    public String deleteAllFailedTxn(){
       transactionService.deleteAllFailedTxn();
       return "Failed transactions deleted";
   }
}
