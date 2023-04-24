import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Mage implements Comparable<Mage> {
    String name;
    int level;
    double power;
    private Set<Mage> apprentices;
    int sortingType;

    public Mage(String name, int level, double power, String sortingType) {
        this.name = name;
        this.level = level;
        this.power = power;

        if(sortingType.equals("NO SORTING")) {
            this.sortingType = 0;
            apprentices = new HashSet<>();
        }
        else if(sortingType.equals("NATURAL")) {
            this.sortingType = 1;
            apprentices = new TreeSet<>(Mage::compareTo);
        }
        else if(sortingType.equals("ALTERNATE")) {
            this.sortingType = 2;
        }
    }

    public Mage(String name, int level, double power, String sortingType, MageComparator mageComparator) {
        this(name, level, power, sortingType);
        apprentices = new TreeSet<>(mageComparator);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public Set<Mage> getApprentices() {
        return apprentices;
    }

    public void setApprentices(Set<Mage> apprentices) {
        this.apprentices = apprentices;
    }

    public void addApprentice(Mage apprentice) {
        apprentices.add(apprentice);


    }

    @Override
    public String toString() {
        String ret =  "Mage{ " +
                "name='" + name + '\'' +
                ", level=" + level +
                ", power=" + power +
                '}';

        return ret;
    }

    public String printTree(int depth) {
        String ret = "";
        for (int i = 0; i < depth; i++) {
            ret += "--";
        }
        ret += ">";

        ret = ret + this.toString();
        ret+= "\n";
        for (Mage app :
                this.apprentices) {
            ret += app.printTree(depth+1);
        }
        return ret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mage mage = (Mage) o;
        return level == mage.level && Double.compare(mage.power, power) == 0 && name.equals(mage.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = result * 31 + level;
        result = (int) Math.round(result + 37*power);
        return result;
    }

    @Override
    public int compareTo(Mage o) {
        int ret = name.compareTo(o.getName());
        if(ret == 0) {
            ret = level - o.getLevel();
        }
        if(ret == 0) {
            ret = (int) (power - o.getPower());
        }
        return ret;
    }
}
