import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gleb23
 * Date: 17.06.13
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 */
public class PlayerProtocol {

    private static final int BONUS_FOR_NUMBERS = 50;
    private static final int SUM_FOR_BONUS = 63;

    private Map<Combination, Integer> values = new HashMap<Combination, Integer>();

    public PlayerProtocol() {
        // no entry means empty cell
        // 0 means 0 for this combination
    }

    public int getSum() {
        int sum = 0;
        for (Integer i : values.values()) {
            sum += i;
        }

        Integer one = values.get(Combination.ONE);
        Integer two = values.get(Combination.TWO);
        Integer three = values.get(Combination.THREE);
        Integer four = values.get(Combination.FOUR);
        Integer five = values.get(Combination.FIVE);
        Integer six = values.get(Combination.SIX);

        if (one != null &&
                two != null &&
                three != null &&
                four != null &&
                five != null &&
                six != null &&
                one + two + three + four + five + six >= SUM_FOR_BONUS) {
            sum += BONUS_FOR_NUMBERS;
        }
        return sum;
    }

    public void put(Combination c, int i) {
        values.put(c, i);
    }

}
