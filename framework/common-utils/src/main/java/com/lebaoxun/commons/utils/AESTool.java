package com.lebaoxun.commons.utils;

import org.apache.commons.lang.StringUtils;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.encoders.Hex;

/**
 * AES加密工具
 * <br/>
 * Created by yangyamin on 16/9/27.
 */
public class AESTool {
   // private Logger logger = LoggerFactory.getLogger(AESTool.class);

    //初始化向量
    private byte[] initVector = { 0x32, 0x37, 0x36, 0x35, 0x34, 0x33, 0x32, 0x31,
            0x38, 0x27, 0x36, 0x35, 0x33, 0x23, 0x32, 0x31 };


    /**
     * Encrypt or decrypt data.
     *
     * @param cipher
     * @param data
     * @return
     * @throws Exception
     */
    private byte[] cipherData(PaddedBufferedBlockCipher cipher, byte[] data)
            throws Exception {
        int minSize = cipher.getOutputSize(data.length);
        byte[] outBuf = new byte[minSize];
        int length1 = cipher.processBytes(data, 0, data.length, outBuf, 0);
        int length2 = cipher.doFinal(outBuf, length1);
        int actualLength = length1 + length2;
        byte[] result = new byte[actualLength];
        System.arraycopy(outBuf, 0, result, 0, result.length);
        return result;
    }

    /**
     * Encrypt data.
     *
     * @param plain
     * @param key
     * @param iv
     * @return
     * @throws Exception
     */
    private byte[] encrypt(byte[] plain, byte[] key, byte[] iv) throws Exception {
        PaddedBufferedBlockCipher aes = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESFastEngine()));
        CipherParameters ivAndKey = new ParametersWithIV(new KeyParameter(key), iv);
        aes.init(true, ivAndKey);
        return cipherData(aes, plain);
    }


    /**
     * Decrypt data.
     *
     * @param cipher
     * @param key
     * @param iv
     * @return
     * @throws Exception
     */
    private byte[] decrypt(byte[] cipher, byte[] key, byte[] iv)
            throws Exception {
        PaddedBufferedBlockCipher aes = new PaddedBufferedBlockCipher(
                new CBCBlockCipher(new AESFastEngine()));
        CipherParameters ivAndKey = new ParametersWithIV(new KeyParameter(key),
                iv);
        aes.init(false, ivAndKey);
        return cipherData(aes, cipher);
    }

    /**
     * Encrypt the content with a given key using aes algorithm.
     *
     * @param content
     * @param key
     *          must contain exactly 32 characters
     * @return
     * @throws Exception
     */
    public String encrypt(String content, String key) throws Exception {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null!");
        }
        String encrypted = null;
        byte[] keyBytes = key.getBytes();
        if (keyBytes.length != 32 && keyBytes.length != 24
                && keyBytes.length != 16) {
            throw new IllegalArgumentException(
                    "Key length must be 128/192/256 bits!");
        }
        byte[] encryptedBytes = null;
        encryptedBytes = encrypt(content.getBytes(), keyBytes, initVector);
        encrypted = new String(Hex.encode(encryptedBytes));
        return encrypted;
    }

    /**
     * Decrypt the content with a given key using aes algorithm.
     *
     * @param content
     * @param key
     *          must contain exactly 32 characters
     * @return
     * @throws Exception
     */
    public String decrypt(String content, String key) throws Exception {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null!");
        }
        String decrypted = null;
        byte[] encryptedContent = Hex.decode(content);
        byte[] keyBytes = key.getBytes();
        byte[] decryptedBytes = null;
        if (keyBytes.length != 32 && keyBytes.length != 24
                && keyBytes.length != 16) {
            throw new IllegalArgumentException(
                    "Key length must be 128/192/256 bits!");
        }
        decryptedBytes = decrypt(encryptedContent, keyBytes, initVector);
        decrypted = new String(decryptedBytes);
        return decrypted;
    }

}
