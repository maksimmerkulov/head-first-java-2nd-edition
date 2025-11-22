package chapter09;

/*
  Most popular object: the single Honey instance (honeyPot).

  Total references = 12:
    1 - local var honeyPot
    4 - ha[0..3]
    5 - ba[0..4].hunny
    1 - k.kh
    1 - r.rh
*/
public class Honey {
    public static void main(String [] args) {
        Honey honeyPot = new Honey();
        Honey [] ha = {honeyPot, honeyPot, honeyPot, honeyPot};
        Bees b1 = new Bees();
        b1.beeHA = ha;
        Bear [] ba = new Bear[5];
        for (int x=0; x < 5; x++) {
            ba[x] = new Bear();
            ba[x].hunny = honeyPot;
        }
        Kit k = new Kit();
        k.kh = honeyPot;
        Raccoon r = new Raccoon();

        r.rh = honeyPot;
        r.k = k;
        k = null;
    } // end of main
}
