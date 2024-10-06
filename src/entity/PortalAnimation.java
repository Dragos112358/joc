package entity;

public class PortalAnimation {

    public int xstart, ystart, xstop, ystop;
    public int contor_portal = 0; //portalul are countdown de 5 secunde

    public PortalAnimation(int xStart, int yStart, int xStop, int yStop) {
        this.xstart = xStart;
        this.ystart = yStart;
        this.xstop = xStop;
        this.ystop = yStop;
    }
}