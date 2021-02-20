public class SocialDistanceMaximizer {
    //public static void

    private static int distanceFromFirstSeat(int[] seats) {
        int distance = 0;

        for (int i = 0; i < seats.length; ++i) {
            if (seats[i] == 0) {
                distance += 1;
            } else {
                break;
            }
        }
        return distance;
    }

    private static int distanceFromLastSeat(int[] seats) {
        int distance = 0;
        for (int i = seats.length - 1; i >= 0; --i) {
            if (seats[i] == 0) {
                distance += 1;
            } else {
                break;
            }
        }
        return distance;
    }

    public static int maxDistance(int[] seats) {
        int distanceFromFirstSeat = distanceFromFirstSeat(seats);
        int distanceFromLastSeat = distanceFromLastSeat(seats);
        int maxDistanceFromEndSeat = distanceFromFirstSeat > distanceFromLastSeat ? distanceFromFirstSeat : distanceFromLastSeat;

        int currentDistance = 0;
        int maxDistanceForInnerSeat = 0;

        for (int i = 0; i < seats.length; ++i) {
            if (seats[i] == 0) {
                currentDistance += 1;
            } else {
                if (currentDistance > maxDistanceForInnerSeat) {
                    maxDistanceForInnerSeat = currentDistance;
                }
                currentDistance = 0;
            }
        }

        maxDistanceForInnerSeat = maxDistanceForInnerSeat % 2 == 0 ? maxDistanceForInnerSeat / 2 : maxDistanceForInnerSeat / 2 + 1;

        return maxDistanceForInnerSeat > maxDistanceFromEndSeat ? maxDistanceForInnerSeat : maxDistanceFromEndSeat;
    }

    public static void main(String[] args) {

        System.out.println(maxDistance(new int[]{1, 0, 0, 0, 1, 0, 1})); // expected 2

        System.out.println(maxDistance(new int[]{1, 0, 0, 0})); // expected 3

        System.out.println(maxDistance(new int[]{0, 1})); // expected 1

    }
}
