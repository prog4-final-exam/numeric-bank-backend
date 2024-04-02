package my_bank.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReferenceGenerator {
    private int refCount = 1;
    private LocalDate lastRefGeneration = LocalDate.now();

    public String generateReference() {
        LocalDate today = LocalDate.now();
        if (ChronoUnit.DAYS.between(lastRefGeneration, today) >= 1) {
            refCount = 1;
            lastRefGeneration = today;
        }
        String ref = "VIR_"
            + today
            .toString().replace('-', '_')
            + "_" + refCount
        ;
        refCount ++;
        return ref;
    }
}
