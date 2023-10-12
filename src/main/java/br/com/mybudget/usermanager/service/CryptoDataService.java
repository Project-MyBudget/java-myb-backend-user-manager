package br.com.mybudget.usermanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CryptoDataService {
	List<String> encryptData(Object... objects);
	List<String> decryptData(Object... objects);
}
