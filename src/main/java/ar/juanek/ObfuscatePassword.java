package ar.juanek;

import org.mindrot.jbcrypt.BCrypt;

public class ObfuscatePassword {
    public static void main(String[] args) {
        String password = "monday";
        System.out.println(BCrypt.hashpw(password, BCrypt.gensalt()));
    }
}
