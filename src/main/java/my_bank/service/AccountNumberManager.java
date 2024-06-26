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

    public Integer extractAccountId(String accountNumber) {
        try {
            if (!(accountNumber.contains(prefix) && accountNumber.contains(suffix))) {
                return null;
            }
            String idAccountString = accountNumber
                    .replace(prefix, "")
                    .replace(suffix, "");
            return Integer.valueOf(Integer.valueOf(idAccountString));
        } catch (Exception exception) {
            System.err.println("Error occurred while extracting the account number :\n  > "
                + exception.getMessage()
            );
            return null;
        }
    }
}
