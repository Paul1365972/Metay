package io.github.paul1365972.story.datastore.filters

import io.github.paul1365972.story.datastore.DataKey
import io.github.paul1365972.story.datastore.StoryDataStore

open class TransformingDataStore<L, K>(
        val underlying: StoryDataStore<in K>,
        val transformer: (L) -> K
) : StoryDataStore<L> {
    override fun <T : Any> get(dataKey: DataKey<T>, locationKey: L): T? {
        return underlying.get(dataKey, transformer(locationKey))
    }

    override fun <T : Any> set(dataKey: DataKey<T>, locationKey: L, value: T?) {
        underlying.set(dataKey, transformer(locationKey), value)
    }

    override fun close() = underlying.close()
}