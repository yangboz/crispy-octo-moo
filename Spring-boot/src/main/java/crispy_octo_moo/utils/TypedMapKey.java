package crispy_octo_moo.utils;

/**
 * Created by yangboz on 1/6/16.
 *
 * @see: http://blog.pdark.de/2010/05/28/type-safe-object-map/
 */
public class TypedMapKey<T> {
    private String name;

    public TypedMapKey(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }
}