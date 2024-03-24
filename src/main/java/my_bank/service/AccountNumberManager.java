package my_bank.service;

public class AccountNumberManager {
    private String prefix = "00040003";
    private String suffix = "02";
    private String initial = "00000000000";

    public String generateAccountNumber(Integer idAccount) {
        String idAccountString = idAccount.toString();
        return prefix
                + initial.substring(idAccountString.length())
                + idAccountString
                + suffix
        ;
    }

    public int extractAccountNumber(String accountNumber) {
        String idAccountString = accountNumber
                .replace(prefix, "")
                .replace(suffix, "");
        return Integer.valueOf(idAccountString);
    }
}
