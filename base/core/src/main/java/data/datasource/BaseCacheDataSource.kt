package data.datasource


abstract class BaseCacheDataSource<KEY, VALUE> : DataSource.CacheDataSource<KEY, VALUE> {

    private val cache = LinkedHashMap<KEY, VALUE>()

    @Synchronized
    override fun get(key: KEY): VALUE? = cache[key]

    @Synchronized
    override fun put(key: KEY, value: VALUE): Boolean {
        cache.put(key, value)
        return true
    }

    @Synchronized
    override fun drop() = cache.clear()
}