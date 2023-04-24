import java.util.Comparator;

public class MageComparator implements Comparator<Mage> {


    @Override
    public int compare(Mage o1, Mage o2) {
        int ret = o1.getLevel() - o2.getLevel();;
        if(ret == 0) {
            ret = o1.getName().compareTo(o2.getName());
        }
        if(ret == 0) {
            ret = (int) (o1.getPower() - o2.getPower());
        }
        return ret;
    }
}
