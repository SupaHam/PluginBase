package com.dumptruckman.minecraft.pluginbase.config;

class DefaultSerializer<T> implements EntrySerializer<T> {

    private Class<T> type;

    public DefaultSerializer(Class<T> type) {
        this.type = type;
    }

    private Class<T> getType() {
        return this.type;
    }

    @Override
    public T deserialize(Object obj) {
        return getType().cast(obj);
    }

    @Override
    public Object serialize(T t) {
        return t;
    }
}