package dk.lott.VirtualPetPotatoSteffen;

public class Time {

    public long timePause = getTimeMilis();
    public long timeRes;

    private long getTimeMilis() {
        return System.currentTimeMillis();
    }

    private long getTimeDiffrence(Long lastTime) {
        return System.currentTimeMillis() - lastTime;
    }

    public void onPause() {
        timePause = getTimeMilis();
    }

    public void onResume() {
        timeRes = ((getTimeDiffrence(timePause) / 1000 / 80));
        System.out.println("Progressbarloses:" + timeRes);
    }
}

