package com.ingenico;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MyAuth {

    MyAuth(String apiKeyId, String secretApiKey) {
        this.apiKeyId = apiKeyId;
        this.secretApiKey = secretApiKey;
    }

    private static final String HMAC_ALGORITHM = "HmacSHA256";
    private static final Charset CHARSET = Charset.forName("UTF-8");

    String apiKeyId;
    String secretApiKey;

    private String toDataSign(String httpMethod, String contentType, String date, String res) {
        StringBuilder sb = new StringBuilder(100);
        sb.append(httpMethod.toUpperCase()).append('\n');
        sb.append(contentType).append('\n');
        sb.append(date).append('\n');
        sb.append(res).append('\n');

        return sb.toString();
    }

    public String createAuthSignature(String httpMethod, String contentType, String date, String res) {

        String data = toDataSign(httpMethod, contentType, date, res);

        Mac sha256Hmac;
        try {

            sha256Hmac = Mac.getInstance(HMAC_ALGORITHM);
            SecretKeySpec secretKey = new SecretKeySpec(secretApiKey.getBytes(CHARSET), HMAC_ALGORITHM);
            sha256Hmac.init(secretKey);

            byte[] unencodedResult = sha256Hmac.doFinal(data.getBytes(CHARSET));

            return Base64.encodeBase64String(unencodedResult);
        } catch (InvalidKeyException | NoSuchAlgorithmException ignored) {
            throw new RuntimeException();
        }
    }

}
