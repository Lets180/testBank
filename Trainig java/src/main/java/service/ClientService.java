package service;
import Exception.*;

public class ClientService {
    public static void depositMoney(model.Client client, int money) throws IsRightDeposit{
        if (money<0) {
            throw new IsRightDeposit("Amount money must have above 0");
        }
        client.setBalanceAccount(client.getBalanceAccount()+money);
    }
    public static void withdrawMoney(model.Client client, int money) throws IsRightWithdraw{
        if (money>0){
            throw new IsRightWithdraw("Amount money must have below 0");
        }
        client.setBalanceAccount(client.getBalanceAccount()-money);
    }
}
