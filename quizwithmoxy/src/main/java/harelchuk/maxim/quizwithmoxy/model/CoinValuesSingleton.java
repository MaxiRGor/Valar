package harelchuk.maxim.quizwithmoxy.model;

public class CoinValuesSingleton {
    private static final CoinValuesSingleton ourInstance = new CoinValuesSingleton();

    public static CoinValuesSingleton getInstance() {
        return ourInstance;
    }

    private int l_1_cost_cp;
    private int l_2_cost_cp;
    private int l_3_cost_cp;
    private int l_4_cost_ad;
    private int l_5_cost_ad;
    private int l_6_cost_ad;
    private int l_7_cost_gd;
    private int l_8_cost_gd;
    private int l_9_cost_gd;
    private int l_10_cost_gd;

    private int l_1_reward_cp;
    private int l_2_reward_cp;
    private int l_3_reward_cp;
    private int l_4_reward_ad;
    private int l_5_reward_ad;
    private int l_6_reward_ad;
    private int l_7_reward_gd;
    private int l_8_reward_gd;
    private int l_9_reward_gd;
    private int l_10_reward_gd;

    private int l_1_lose_cp;
    private int l_2_lose_cp;
    private int l_3_lose_cp;
    private int l_4_lose_cp;
    private int l_5_lose_ad;
    private int l_6_lose_ad;
    private int l_7_lose_ad;
    private int l_8_lose_gd;
    private int l_9_lose_gd;
    private int l_10_lose_gd;

    private int conversation_CP_GD;
    private int conversation_CP_AD;

    private CoinValuesSingleton() {

        this.l_1_cost_cp = 1;
        this.l_2_cost_cp = 5;
        this.l_3_cost_cp = 15;
        this.l_4_cost_ad = 1;
        this.l_5_cost_ad = 8;
        this.l_6_cost_ad = 25;
        this.l_7_cost_gd = 1;
        this.l_8_cost_gd = 7;
        this.l_9_cost_gd = 21;
        this.l_10_cost_gd = 150;

        this.l_1_reward_cp = 11;
        this.l_2_reward_cp = 35;
        this.l_3_reward_cp = 127;
        this.l_4_reward_ad = 25;
        this.l_5_reward_ad = 83;
        this.l_6_reward_ad = 655;
        this.l_7_reward_gd = 29;
        this.l_8_reward_gd = 91;
        this.l_9_reward_gd = 321;
        this.l_10_reward_gd = 1000;

        this.l_1_lose_cp = 1;
        this.l_2_lose_cp = 2;
        this.l_3_lose_cp = 10;
        this.l_4_lose_cp = 30;
        this.l_5_lose_ad = 3;
        this.l_6_lose_ad = 24;
        this.l_7_lose_ad = 75;
        this.l_8_lose_gd = 4;
        this.l_9_lose_gd = 15;
        this.l_10_lose_gd = 63;

        this.conversation_CP_GD = 11760;
        this.conversation_CP_AD = 56;
    }

    public int[] getCostCoins() {
        int[] coins = new int[10];
        coins[0] = this.l_1_cost_cp;
        coins[1] = this.l_2_cost_cp;
        coins[2] = this.l_3_cost_cp;
        coins[3] = this.l_4_cost_ad;
        coins[4] = this.l_5_cost_ad;
        coins[5] = this.l_6_cost_ad;
        coins[6] = this.l_7_cost_gd;
        coins[7] = this.l_8_cost_gd;
        coins[8] = this.l_9_cost_gd;
        coins[9] = this.l_10_cost_gd;
        return coins;
    }

    public int[] getRewardCoins() {
        int[] coins = new int[10];
        coins[0] = this.l_1_reward_cp;
        coins[1] = this.l_2_reward_cp;
        coins[2] = this.l_3_reward_cp;
        coins[3] = this.l_4_reward_ad;
        coins[4] = this.l_5_reward_ad;
        coins[5] = this.l_6_reward_ad;
        coins[6] = this.l_7_reward_gd;
        coins[7] = this.l_8_reward_gd;
        coins[8] = this.l_9_reward_gd;
        coins[9] = this.l_10_reward_gd;
        return coins;
    }

    public int[] getLoseCoins() {
        int[] coins = new int[10];
        coins[0] = this.l_1_lose_cp;
        coins[1] = this.l_2_lose_cp;
        coins[2] = this.l_3_lose_cp;
        coins[3] = this.l_4_lose_cp;
        coins[4] = this.l_5_lose_ad;
        coins[5] = this.l_6_lose_ad;
        coins[6] = this.l_7_lose_ad;
        coins[7] = this.l_8_lose_gd;
        coins[8] = this.l_9_lose_gd;
        coins[9] = this.l_10_lose_gd;
        return coins;
    }

    public int getConversation_CP_AD() {
        return this.conversation_CP_AD;
    }

    public int getConversation_CP_GD() {
        return this.conversation_CP_GD;
    }

    public long[] convertCoinsToGAC(long money) {
        long coins_GD = money / (this.conversation_CP_GD);
        money -= coins_GD * this.conversation_CP_GD;
        long coinsAD = money / (this.conversation_CP_AD);
        money -= coinsAD * this.conversation_CP_AD;
        long coinsCP = money;
        return new long[]{coins_GD, coinsAD, coinsCP};
    }
}
