package crispy_octo_moo.dto;

import crispy_octo_moo.utils.TypedMap;
import crispy_octo_moo.utils.TypedMapKey;

import java.util.List;

/**
 * Created by yangboz on 1/7/16.
 *
 * @see;http://stackoverflow.com/questions/3127189/java-classes-with-dynamic-fields/3127203#3127203
 */
public class ExtractedTypedMap extends TypedMap {
    public TypedMapKey<String> KEY1 = new TypedMapKey<String>("key1");
    public TypedMapKey<List<String>> KEY2 = new TypedMapKey<List<String>>("key2");

    @Override
    public String toString() {
        return "ExtractedTypedMap{" +
                "KEY1=" + KEY1 +
                ", KEY2=" + KEY2 +
                '}';
    }
}
