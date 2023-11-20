package br.com.gerenciamento.util;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UtilTest {

    @Test // hash MD5 = "senha123"
    void testMd5() throws NoSuchAlgorithmException {
        String senha = "senha123";
        String expectedHash = "e7d80ffeefa212b7c5c55700e4f7193e";

        String result = Util.md5(senha);

        assertEquals(expectedHash, result);
    }

    @Test // hash MD5 = vazio
    void testMd5EmptyString() throws NoSuchAlgorithmException {
        String senha = "";
        String expectedHash = "d41d8cd98f00b204e9800998ecf8427e";

        String result = Util.md5(senha);

        assertEquals(expectedHash, result);
    }

    @Test //valida entrada nula
    void testMd5NullString() {
        assertThrows(NullPointerException.class, () -> Util.md5(null));
    }
}
