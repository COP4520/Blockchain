public class Staker {
    String address;
    int stake;

    public Staker(String address, int stake) {
        this.address = address;
        this.stake = stake;
    }

    public String getAddress() {
        return address;
    }

    public int getStake() {
        return stake;
    }
}
