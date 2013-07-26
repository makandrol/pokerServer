import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: gleb23
 * Date: 17.06.13
 * Time: 19:29
 * To change this template use File | Settings | File Templates.
 */
public enum Combination{
    ECCI
    , ONE
    , TWO
    , THREE
    , FOUR
    , FIVE
    , SIX
    , PAIR
    , TWO_PAIRS
    , TRIPLE
    , QUAD
    , HOME
    , LINE
    , LONG_LINE
    , CHANCE;

    public static int getSum(int[] nums, Combination c) {
        switch (c) {
            case ECCI:
                int num = nums[0];
                for (int i = 1; i < nums.length; i++) {
                    if (nums[i] != num) {
                        return 0;
                    }
                }
                return 50;
            case ONE:
                return countNumberCombination(nums, c);
            case TWO:
                return countNumberCombination(nums, c);
            case THREE:
                return countNumberCombination(nums, c);
            case FOUR:
                return countNumberCombination(nums, c);
            case FIVE:
                return countNumberCombination(nums, c);
            case SIX:
                return countNumberCombination(nums, c);
            case PAIR:
                int[] qArray = getQuantityArray(nums, c);
                for (int i = qArray.length - 1; i >= 0; i--) {
                    if (qArray[i] >= 2) {
                        return i * 2;
                    }
                }
                return 0;
            case TRIPLE:
                qArray = getQuantityArray(nums, c);
                for (int i = qArray.length - 1; i >= 0; i--) {
                    if (qArray[i] >= 3) {
                        return i * 3;
                    }
                }
                return 0;
            case TWO_PAIRS: // if there is home then there is no TWO_PAIRS !!!
                qArray = getQuantityArray(nums, c);
                int sum = 0;
                int nPairs = 0;
                for (int i = qArray.length - 1; i >= 0; i--) {
                    if (qArray[i] >= 2) {
                        sum += i * 2;
                        ++nPairs;
                    }
                }
                if (nPairs == 2) {
                    return sum;
                } else {
                    return 0;
                }
            case QUAD:
                qArray = getQuantityArray(nums, c);
                for (int i = 0; i < qArray.length; i++) {
                    if (qArray[i] >= 4) {
                        return i * 4;
                    }
                }
                return 0;
            case LINE:
                final int SUM_FROM_1_TO_4 = 10;
                qArray = getQuantityArray(nums, c);
                sum = 0;
                int firstNumberInLine = 1;
                int lastNumberInLine = 4;
                if (qArray[6] >= 1 && qArray[5] >= 1) {
                    sum = 8;
                    firstNumberInLine = 3;
                    lastNumberInLine = 6;
                } else if (qArray[6] == 0 && qArray[5] >= 1) {
                    sum = 4;
                    firstNumberInLine = 2;
                    lastNumberInLine = 5;
                }
                for (int i = firstNumberInLine; i <= lastNumberInLine; i++) {
                    if (qArray[i] == 0) {
                        // smth wrong with line
                        return 0;
                    }
                }
                return sum + SUM_FROM_1_TO_4;
            case LONG_LINE:
                final int SUM_FROM_1_TO_5 = 15;
                qArray = getQuantityArray(nums, c);
                sum = 0;
                firstNumberInLine = 1;
                lastNumberInLine = 5;
                if (qArray[1] == 0) {
                    sum = 5;
                    firstNumberInLine = 2;
                    lastNumberInLine = 6;
                }
                for (int i = firstNumberInLine; i <= lastNumberInLine; i++) {
                    if (qArray[i] != 1) {
                        // smth wrong with line
                        return 0;
                    }
                }
                return sum += SUM_FROM_1_TO_5;
            case HOME:
                qArray = getQuantityArray(nums, c);
                sum = 0;
                for (int i = 1; i < qArray.length; i++) {
                    if (qArray[i] == 1 || qArray[i] == 4 || qArray[i] == 5) {
                        return 0;
                    } else if (qArray[i] == 2) {
                        sum += i * 2;
                    } else if (qArray[i] == 3) {
                        sum += i * 3;
                    }
                }
                return sum;
            case CHANCE:
                sum = 0;
                for (int i = 0; i < nums.length; i++) {
                    sum += nums[i];
                }
                return sum;
        }
        return 0;
    }

    private static int[] getQuantityArray(int[] nums, Combination c) {
        int[] qArray = new int[7];  // 1..6 are actually used
        for (int i = 0; i < nums.length; i++) {
            ++qArray[nums[i]];
        }
        return qArray;
    }

    private static int countNumberCombination(int[] nums, Combination c) {
        int number;
        int sum = 0;
        switch (c) {
            case ONE:
                number = 1;
                break;
            case TWO:
                number = 2;
                break;
            case THREE:
                number = 3;
                break;
            case FOUR:
                number = 4;
                break;
            case FIVE:
                number = 5;
                break;
            case SIX:
                number = 6;
                break;
            default:
                throw new IllegalArgumentException();
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == number) {
                sum += number;
            }
        }
        return sum;
    }
}
