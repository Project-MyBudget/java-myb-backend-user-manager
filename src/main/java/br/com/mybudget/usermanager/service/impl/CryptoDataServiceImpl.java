package br.com.mybudget.usermanager.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mybudget.usermanager.enums.CipherEnum;
import br.com.mybudget.usermanager.service.CipherService;
import br.com.mybudget.usermanager.service.CryptoDataService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CryptoDataServiceImpl implements CryptoDataService {
	
	@Autowired
	private CipherService cipherService;
	
	@Override
	public List<String> encryptData(Object... objects) {
		Cipher cipher = cipherService.getCipher(CipherEnum.ENCRYPT.toString());
		List<String> dataEncrypteds = new ArrayList<>();

		for (Object object : objects) {
			try {
				byte[] data = String.valueOf(object).getBytes(StandardCharsets.UTF_8);
				String cipherResponse = Base64.getEncoder().encodeToString(cipher.doFinal(data));
				dataEncrypteds.add(cipherResponse);
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				log.error("[ERROR] Error to encrypt data: {} ", e.getMessage());
			}
		}

		return dataEncrypteds;
	}

	@Override
	public List<String> decryptData(Object... objects) {
		Cipher cipher = cipherService.getCipher(CipherEnum.DECRYPT.toString());
		List<String> deciphereddata = new ArrayList<>();

		for (Object object : objects) {
			try {
				String encryptedData = String.valueOf(object.toString());
				byte[] encoded = Base64.getDecoder().decode(encryptedData);
				byte[] cipherResponse = cipher.doFinal(encoded);
				String decryptedResponseToString = new String(cipherResponse);
				deciphereddata.add(decryptedResponseToString);
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				log.error("[ERROR] Error to encrypt data: {} ", e.getMessage());
			}
		}

		return deciphereddata;
	}
}
