/*
  Guy Sharir: 310010244
  Ido Betesh: 307833822
 */

package il.ac.shenkar.costMannager.model;
import java.util.Objects;
public class Category {
    private final  String name;

    /**
     * Category constructor creates Category object from input [no id input is needed]
     *
     * @param name (string) category name also used as a unique value in the category table in DB
     */
    public Category(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}


