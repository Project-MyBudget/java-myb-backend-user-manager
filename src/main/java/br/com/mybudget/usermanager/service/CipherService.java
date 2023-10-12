package br.com.mybudget.usermanager.service;

import javax.crypto.Cipher;

import org.springframework.stereotype.Service;

@Service
public interface CipherService {
	Cipher getCipher(String typeCipher);
}
