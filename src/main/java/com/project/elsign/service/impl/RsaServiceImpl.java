package com.project.elsign.service.impl;

import com.project.elsign.model.constant.RSAConstants;
import com.project.elsign.service.RsaService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;

@Service
public class RsaServiceImpl implements RsaService {

    @Override
    public byte[] encrypt(String original, Key privateKey) {
        if (original != null && privateKey != null) {
            byte[] bs = original.getBytes();
            return convert(bs, privateKey, Cipher.ENCRYPT_MODE);
        }
        return null;
    }

    @Override
    public byte[] decrypt(byte[] encrypted, Key publicKey) {
        if (encrypted != null && publicKey != null) {
            return convert(encrypted, publicKey, Cipher.DECRYPT_MODE);
        }
        return null;
    }

    @Override
    public String getMD5Hash(File file) {
        String md5 = null;

        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(file);
            md5 = DigestUtils.md5Hex(IOUtils.toByteArray(fileInputStream));
            fileInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return md5;
    }


    private byte[] convert(byte[] data, Key key, int mode) {
        try {
            Cipher cipher = Cipher.getInstance(RSAConstants.ALGORITHM);
            cipher.init(mode, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
