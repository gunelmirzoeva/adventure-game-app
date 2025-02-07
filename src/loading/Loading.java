package loading;

public class Loading {

    public static void showLoadingBar(int speed, int barLength) {
        try {
            for (int i = 0; i <= 100; i++) {
                System.out.print("\rLoading: [");
                int progress = (i * barLength) / 100;

                StringBuilder bar = new StringBuilder();
                for (int j = 0; j < barLength; j++) {
                    bar.append(j < progress ? "=" : " ");
                }
                System.out.print(bar + "] " + i + "%");

                Thread.sleep(speed);
            }
            System.out.println();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
    public static void loading(int speed) {
        try{
            Thread.sleep(speed);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println();
    }

}
