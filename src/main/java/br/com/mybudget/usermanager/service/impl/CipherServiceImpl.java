package br.com.mybudget.usermanager.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.mybudget.usermanager.enums.CipherEnum;
import br.com.mybudget.usermanager.service.CipherService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CipherServiceImpl implements CipherService {

	@Value("${cipher.key}")
	private String cipherKey;

	@Value("${cipher.type}")
	private String typeInstance;

	private Cipher currentCipher = null;

	private String currentTypeCipher;

	private byte[] currentKey;

	@Override
	public Cipher getCipher(String typeCipher) {
		if (this.currentCipher == null) {
			log.info("No Cipher in cache. Will request a new one");
			this.currentCipher = this.retrieveNewCipher(typeCipher);
		} else if (this.currentCipher == null || this.currentTypeCipher != typeCipher) {
			log.info("Cipher not is equal previous type. Will request a new one");
			this.currentCipher = this.retrieveNewCipher(typeCipher);
		} else {
			log.info("Cipher is still valid.");
		}

		this.currentTypeCipher = typeCipher;

		return this.currentCipher;
	}

	private Cipher retrieveNewCipher(String typeCipher) {
		try {
			SecretKey aesKey = this.getKey();
			Cipher cipher = Cipher.getInstance(typeInstance);

			if (CipherEnum.DECRYPT.toString().equals(typeCipher)) {
				cipher.init(Cipher.DECRYPT_MODE, aesKey);
			} else {
				cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			}

			return cipher;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			log.error("[ERROR] Error to get cipher {} ", e.getMessage());
		} catch (InvalidKeyException e) {
			log.error("[ERROR] Error to initialize cipher: {} ", e.getMessage());
		} catch (Exception e) {
			log.error("[ERROR] Generic error, view specified error: {} ", e.getMessage() + e.getCause());
		}

		return null;
	}

	private SecretKey getKey() {
		MessageDigest sha = null;
		try {
			currentKey = cipherKey.getBytes(StandardCharsets.UTF_8);
			sha = MessageDigest.getInstance("SHA-1");
			currentKey = sha.digest(currentKey);
			currentKey = Arrays.copyOf(currentKey, 16);
			return new SecretKeySpec(currentKey, "AES");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
