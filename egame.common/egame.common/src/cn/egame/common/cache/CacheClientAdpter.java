package cn.egame.common.cache;

import redis.clients.jedis.JedisPubSub;
import cn.egame.common.exception.ExceptionCommonBase;

public class CacheClientAdpter extends CacheClientBase {
    private CacheClientBase cacheClient;
    private boolean useCache;

    public CacheClientAdpter(CacheClientBase cacheClient) {
        if (cacheClient == null) {
            return;
        }
        this.cacheClient = cacheClient;
        this.useCache = cacheClient.cacheSwitch;
        this.cacheNotNullList = cacheClient.cacheNotNullList;
        this.localCacheSwitch = cacheClient.localCacheSwitch;
    }

    @Override
    public void setCachePool(String serverURL) throws ExceptionCommonBase {
        cacheClient.setCachePool(serverURL);
    }

    public Object get(String key) {
        if (useCache) {
            if (!this.localCacheSwitch) {
                Object obj =  cacheClient.getRemote(key);
                if (obj instanceof LocalCacheObject) {
                    LocalCacheObject l = (LocalCacheObject) obj;
                    if (l.isLocal()) {
                        //this.setLocalCache(key, obj);
                        return l.getObj();
                    }
                 }
                return obj;
            } else {
                return cacheClient.get(key);
            }
        } else {
            return null;
        }
    }

    public void set(String key, Object obj, int seconds) {
        if (useCache) {
            if (!this.localCacheSwitch) {
                setRemote(key, obj, seconds);
            } else {
                cacheClient.set(key, obj, seconds);
            }
        } else {
            return;
        }
    }

    public Object getRemote(String key) {
        if (useCache) {
            return cacheClient.getRemote(key);
        } else {
            return null;
        }
    }

    @Override
    public void removeRemote(String key) {
        if (useCache) {
            cacheClient.removeRemote(key);
        } else {
            return;
        }
    }

    @Override
    public void setRemote(String key, Object v, int seconds) {
        if (useCache) {
            cacheClient.setRemote(key, v, seconds);
        } else {
            return;
        }

    }

    @Override
    public boolean addRemote(String key, Object obj, int seconds) {
        if (useCache) {
            return cacheClient.addRemote(key, obj, seconds);
        } else {
            return false;
        }
    }

    @Override
    public void subscribe(JedisPubSub pubsub, String... channel) {
        if (useCache) {
            cacheClient.subscribe(pubsub, channel);
        }

    }

    @Override
    public void psubscribe(JedisPubSub pubsub, String... partten) {
        if (useCache) {
            cacheClient.psubscribe(pubsub, partten);
        }
    }

    @Override
    public void publishMessage(String channel, String message) {
        if (useCache) {
            cacheClient.publishMessage(channel, message);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.egame.common.cache.ICacheClient#getElementFromQueue(java.lang.String,
     * int)
     */
    // @Override
    // public <T> T getTFromList(String k, int index) throws ExceptionCommonBase
    // {
    // if (useCache) {
    // return (T) cacheClient.getTFromList(k, index);
    // } else {
    // return null;
    // }
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see cn.egame.common.cache.ICacheClient#popFromQueue(java.lang.String,
    // * java.lang.Object, boolean)
    // */
    // @Override
    // public <T> T popTFromList(String k, boolean isPopLeft)
    // throws ExceptionCommonBase {
    // if (useCache) {
    // return (T) cacheClient.popTFromList(k, isPopLeft);
    // } else {
    // return null;
    // }
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see cn.egame.common.cache.ICacheClient#pushToQueue(java.lang.String,
    // * java.lang.Object, boolean, int)
    // */
    // @Override
    // public <T> void setT2List(String k, T v, boolean isPushLeft, int seconds)
    // throws ExceptionCommonBase {
    // if (useCache) {
    // cacheClient.setT2List(k, v, isPushLeft, seconds);
    // }
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see
    // * cn.egame.common.cache.ICacheClient#pushBatchToQueue(java.lang.String,
    // * java.util.List, boolean, int)
    // */
    // @Override
    // public <T> void setBatch2List(String k, List<T> list, boolean isPushLeft,
    // int second) throws ExceptionCommonBase {
    // if (useCache) {
    // cacheClient.setBatch2List(k, list, isPushLeft, second);
    // }
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see
    // * cn.egame.common.cache.ICacheClient#getLRangeFromQueue(java.lang.String,
    // * int, int)
    // */
    // @Override
    // public <T> List<T> getRangeTFromList(String k, int start, int limit)
    // throws ExceptionCommonBase {
    // if (useCache) {
    // return cacheClient.getRangeTFromList(k, start, limit);
    // } else {
    // return null;
    // }
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see
    // cn.egame.common.cache.ICacheClient#removeFromQueue(java.lang.String,
    // * java.lang.Object)
    // */
    // @Override
    // public <T> boolean removeFromList(String k, T v) throws
    // ExceptionCommonBase {
    // if (useCache) {
    // return cacheClient.removeFromList(k, v);
    // } else {
    // return false;
    // }
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see
    // cn.egame.common.cache.ICacheClient#getLenthOfQueue(java.lang.String)
    // */
    // @Override
    // public long getLenthOfList(String k) throws ExceptionCommonBase {
    // if (useCache) {
    // return cacheClient.getLenthOfList(k);
    // }
    // return 0;
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see cn.egame.common.cache.ICacheClient#insertValue(java.lang.String,
    // * java.lang.Object, int)
    // */
    // @Override
    // public <T> void insertValue(String k, T v, int index)
    // throws ExceptionCommonBase {
    // if (useCache) {
    // cacheClient.insertValue(k, v, index);
    // }
    // }
    //
    // @Override
    // public <T> void replaceList(String k, List<T> list, boolean isListHead,
    // int second) throws ExceptionCommonBase {
    // if (useCache) {
    // cacheClient.replaceList(k, list, isListHead, second);
    // }
    //
    // }
}
