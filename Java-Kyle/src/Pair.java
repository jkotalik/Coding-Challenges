/**
 * <b>Pair</b> is an immutable representation of a pair of strings,
 * where the first string is the 'key' and the second string is the 'value'.
 *
 * @author Kyle Steiner
 */
public class Pair<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Pair<K,V>> {

	/** Key of this pair **/
	private final K key;

	/** Value of this pair **/
    private final V value;

    // Abstraction Function:
    // For a given Pair p, "key of p" is synonymous with
    // p.key, and, likewise, "value of p" is synonymous with p.value.
    //
    // Representation Invariant:
    // key != null && value != null

    /**
     * @param key The key of the Pair to be constructed
     * @param value The value of the Pair to be constructed
     * @requires key != null && values != null
     * @effects Constructs a new Pair p,
     *          with p.key = 'key' and p.value = 'value'
     */
    public Pair(K key, V value) {
    	this.key = key;
    	this.value = value;
    }

    /**
     * Gets the key of this Pair.
     *
     * @returns the key of this Pair.
     */
    public K getKey() {
    	return key;
    }

    /**
     * Gets the value of this Pair.
     *
     * @returns the value of this Pair.
     */
    public V getValue() {
    	return value;
    }

    /**
     * @param pair2 second pair to be compared
     * @requires pair2 != null
     * @returns positive if alphabetically key is before pair2.getKey(),
     * 			otherwise compares values and returns int result
     */
	@Override
	public int compareTo(Pair<K,V> pair2) {
		if (key.equals(pair2.getKey())) {
			return value.compareTo(pair2.getValue());
		} else {
			return key.compareTo(pair2.getKey());
		}
	}

	/**
	 * Compares two Pair objects
	 *
	 * @param o pair to compare to
	 * @returns true if key and value are the same for both pairs
	 */
	@Override
	public boolean equals(/*@Nullable*/ Object o) {
		if (! (o instanceof Pair))
			return false;
		Pair<?,?> p = (Pair<?, ?>) o;
		return key.equals(p.getKey()) && value.equals(p.getValue());
	}

	/**
	 * @returns the product of the key and value hashcodes; this is used for the hashcode of a Pair
	 */
	@Override
	public int hashCode() {
		return key.hashCode() * value.hashCode();
	}
}
