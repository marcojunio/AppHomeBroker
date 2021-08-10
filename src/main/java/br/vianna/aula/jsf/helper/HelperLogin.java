package br.vianna.aula.jsf.helper;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HelperLogin {

    public static String Md5(String valor) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(valor.getBytes());
            byte[] c = md.digest();
            return DatatypeConverter.printHexBinary(c).toLowerCase();
        }catch(NoSuchAlgorithmException e){
            return "Ocorreu o seguinte erro: " + e.getMessage();
        }
    }
}
