package org.example.Exption;

import java.util.concurrent.Executor;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message) {
        super(message);
    }
}
// اريد من خلالها ان تظهر لي البيانات Jeson
// بدونها تظهر رساىل الغلط html