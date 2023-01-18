package com.ceir.CeirCode.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.UserPayment;

public interface PaymentRepo extends JpaRepository<UserPayment,Long>{

	public UserPayment save(UserPayment payment);
	public UserPayment findByTxnId(String txnId);
}
