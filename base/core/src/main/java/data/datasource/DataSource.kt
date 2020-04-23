package data.datasource

interface DataSource {

    interface RemoteDataSource : DataSource {
        interface RequestDataSource<Req, Res : Any> : DataSource {
            suspend fun getResult(request: Req): DataHolder<Res>
        }

        interface FetchDataSource<Res : Any> : DataSource {
            suspend fun fetch(): DataHolder<Res>
        }
    }

    interface LocalSource : DataSource {
        interface ShrinkedLocalDataSource<KEY, VALUE> : DataSource {
            fun get(key: KEY): VALUE?

            fun put(key: KEY, value: VALUE): Boolean

            fun remove(key: KEY): Boolean
        }
    }


    interface CacheDataSource<K, V> : DataSource {
        fun get(key: K): V?

        fun put(key: K, value: V): Boolean

        fun drop()
    }

}