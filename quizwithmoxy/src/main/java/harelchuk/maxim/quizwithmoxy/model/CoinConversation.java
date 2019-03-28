package harelchuk.maxim.quizwithmoxy.model;

public class CoinConversation {
    public static long[] coins_GD_AD_CP(long money) {
        long coins_GD, coins_AD, coins_CP;
        int conversation_CP_GD = 11760;
        int conversation_CP_AD = 56;
        coins_GD = money / (conversation_CP_GD);
        money -= coins_GD * conversation_CP_GD;
        coins_AD = money / (conversation_CP_AD);
        money -= coins_AD * conversation_CP_AD;
        coins_CP = money;
        return new long[]{coins_GD, coins_AD, coins_CP};
    }
}
