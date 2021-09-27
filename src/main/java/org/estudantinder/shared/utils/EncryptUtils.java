package org.estudantinder.shared.utils;


import java.security.spec.InvalidKeySpecException;

import org.wildfly.security.credential.PasswordCredential;
import org.wildfly.security.evidence.PasswordGuessEvidence;
import org.wildfly.security.password.util.ModularCrypt;

import io.quarkus.elytron.security.common.BcryptUtil;

public class EncryptUtils {
    public static String encrypt(String toEncrypt) {
        return BcryptUtil.bcryptHash(toEncrypt);
    }

    public static boolean isPasswordEqual(String hashDBPassword, String loginPassword) throws InvalidKeySpecException {
        PasswordCredential producedPasswordCredential = new PasswordCredential(ModularCrypt.decode(hashDBPassword));
        PasswordGuessEvidence correctPasswordEvidence = new PasswordGuessEvidence(loginPassword.toCharArray());
        return producedPasswordCredential.verify(correctPasswordEvidence);
    }
}
