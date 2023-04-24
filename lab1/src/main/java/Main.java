import java.util.Set;

public class Main {
    public static void main(String[] args) {
        //FOR NATURAL SORT AND NO SORTING
        Mage mage0 = new Mage("a", 6, 17.2, args[0]);

        Mage mage1 = new Mage("b", 10, 15.1, args[0]);
        Mage mage2 = new Mage("c", 11, 12.7, args[0]);
        Mage mage3 = new Mage("d", 12, 22.3, args[0]);

        Mage mage4 = new Mage("e", 56, 6.0, args[0]);
        Mage mage5 = new Mage("f", 11, 18.3, args[0]);
        Mage mage6 = new Mage("g", 1, 18.6, args[0]);
        Mage mage7 = new Mage("h", 7, 4.7, args[0]);

        Mage mage8 = new Mage("i", 12, 17.1, args[0]);
        Mage mage9 = new Mage("j", 7, 25.9, args[0]);

        //FOR ALTERNATE SORITNG
//        MageComparator mageComparator = new MageComparator();
//        Mage mage0 = new Mage("a", 6, 17.2, args[0], mageComparator);
//
//        Mage mage1 = new Mage("b", 10, 15.1, args[0], mageComparator);
//        Mage mage2 = new Mage("c", 11, 12.7, args[0], mageComparator);
//        Mage mage3 = new Mage("d", 12, 22.3, args[0], mageComparator);
//
//        Mage mage4 = new Mage("e", 56, 6.0, args[0], mageComparator);
//        Mage mage5 = new Mage("f", 11, 18.3, args[0], mageComparator);
//        Mage mage6 = new Mage("g", 1, 18.6, args[0], mageComparator);
//        Mage mage7 = new Mage("h", 7, 4.7, args[0], mageComparator);
//
//        Mage mage8 = new Mage("i", 12, 17.1, args[0], mageComparator);
//        Mage mage9 = new Mage("j", 7, 25.9, args[0], mageComparator);




        //-----------------------------------------------------------------

        mage1.addApprentice(mage4);
        mage1.addApprentice(mage5);
        mage1.addApprentice(mage6);
        mage1.addApprentice(mage7);

        mage2.addApprentice(mage8);
        mage2.addApprentice(mage9);

        mage0.addApprentice(mage1);
        mage0.addApprentice(mage2);
        mage0.addApprentice(mage3);

        System.out.println(mage0.printTree(0));
    }
}
