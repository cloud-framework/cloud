package cn.egame.common.cache.factory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.exceptions.JedisConnectionException;
import cn.egame.common.cache.CacheClientBase;
import cn.egame.common.cache.LocalCacheObject;
import cn.egame.common.exception.ErrorCodeBase;
import cn.egame.common.exception.ExceptionCommonBase;
import cn.egame.common.util.Utils;

/*
 * http://flychao88.iteye.com/blog/1527163
 * */
public class RedisCacheClient extends CacheClientBase {
    private JedisPool jedisPool_r = null;
    private List<JedisPool> jedisPool_ws = new ArrayList<JedisPool>();
    static Map<String, JedisPool> jedisPool_map = new HashMap<String, JedisPool>();

    List<String> serverUrls_w = new ArrayList<String>();
    List<String> serverUrls_r = new ArrayList<String>();

    public static final String REDIS_KEY_TYPE_LIST = "list";
    public static final String REDIS_KEY_TYPE_STRING = "string";
    public static final String REDIS_KEY_TYPE_NONE = "none";

    public static final int THREEYEARSECOND = 93312000;

    public RedisCacheClient() {
        super();
    }

    public static void main(String[] args) {
        try {
            Utils.initLog4j();

            RedisCacheClient s = new RedisCacheClient();
            s.setCachePool("W(192.168.10.64:6379|192.168.10.64:6380)");
            // s.set("test", 221);
            System.out.println("---------------------");
            s = new RedisCacheClient();
            s.setCachePool("W(192.168.10.64:6379|192.168.10.64:6380)R(192.168.10.64:6379|192.168.10.64:6380)");

            while (true) {
                long t = System.nanoTime();
                Object o = s.get("test");
                System.out.println("t=" + (System.nanoTime() - t));
                System.out.println(o);
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setRemote(String key, Object obj, int seconds) {
        this.setRemote(null, key, obj, seconds);
    }

    public void setRemote(JedisPool pool, String key, Object obj, int seconds) {
        Jedis jedis = getJedis(pool);
        if (jedis == null)
            return;

        try {
            if (seconds > 0)
                jedis.setex(Utils.toByte(key), seconds, Utils.objectWrite(obj));
            else
                jedis.set(Utils.toByte(key), Utils.objectWrite(obj));
        }  catch (JedisConnectionException ex) {
            logger.fatal("JedisConnectionException,jedis = " + jedis, ex);
            if (jedis != null) {
                jedis.disconnect();
            }

        }catch (UnsupportedEncodingException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        } finally {
            releaseJedis(pool, jedis);
        }
    }

    @Override
    public Object getRemote(String key) {
        return this.getRemote(null, key);
    }

    public Object getRemote(JedisPool pool, String key) {
        Object obj = null;
        Jedis jedis = null;
        try {
            jedis = getJedis(pool);
            if (jedis == null)
                return null;

            byte[] bs = jedis.get(Utils.toByte(key));
            if (bs != null) {
                obj = Utils.objectRead(bs);
            } else
                logger.info("get from redis is null,key = " + key);
        } catch (JedisConnectionException ex) {
            logger.fatal("JedisConnectionException,jedis = " + jedis, ex);
            if (jedis != null) {
                jedis.disconnect();
            }

        } catch (Exception ex) {
            logger.error("get", ex);
        } finally {
            releaseJedis(pool, jedis);
        }
        return obj;
    }

    @Override
    public void set(String key, Object obj, int seconds) {
        if (Utils.stringIsNullOrEmpty(key) || obj == null)
            return;
        LocalCacheObject l = new LocalCacheObject(key, obj, Utils.getCurrentTime(), super.localCacheGcThreadTime, seconds);
        localCached.put(key, l);

        for (int i = 0; i < jedisPool_ws.size(); i++) {
            JedisPool jedisPool = jedisPool_ws.get(i);
            if (l.isLocal())
                this.setRemote(jedisPool, key, l, seconds);
            else
                this.setRemote(jedisPool, key, obj, 0);

        }
        super.sendClearKey(key);
    }

    // @Override
    // public String getString(String key) {
    // Jedis jedis = getJedis();
    // try {
    // return jedis.get(key);
    // } catch (Exception e) {
    // logger.error(null, e);
    // } finally {
    // releaseJedis(jedis);
    // }
    //
    // return null;
    // }

    protected Jedis getJedis() {
        return getJedis(null);
    }

    protected Jedis getJedis(JedisPool pool) {
        Jedis jedis = null;
        if (pool == null)
            pool = jedisPool_r;

        if (pool != null) {
            jedis = pool.getResource();
            if (jedis == null) {
                logger.fatal("Failed to get jedis ,jedisPool==== " + pool.toString());
            }
        } else
            logger.info("getJedis pool==null");

        return jedis;
    }

    protected void releaseJedis(JedisPool pool, Jedis jedis) {
        if (jedis != null) {
            if (pool == null)
                pool = jedisPool_r;
            jedisPool_r.returnResource(jedis);
        }
    }

    protected void releaseJedis(Jedis jedis) {
        releaseJedis(null, jedis);
    }

    @Override
    public void removeRemote(String key) {
        for (JedisPool jedisPool : jedisPool_ws) {
            Jedis jedis = null;
            try {
                jedis = getJedis(jedisPool);
                jedis.del(Utils.toByte(key));
            } catch (JedisConnectionException e) {
                logger.fatal("JedisConnectionException,jedis = " + jedis, e);
                if (jedis != null) {
                    jedis.disconnect();
                }
            } catch (Exception e) {
                logger.error(e);
            } finally {
                releaseJedis(jedisPool, jedis);
            }
        }
        super.sendClearKey(key);

    }

    @Override
    public void setCachePool(String serverURL) throws ExceptionCommonBase {
        // FIXME
        if (Utils.stringIsNullOrEmpty(serverURL))
            throw new ExceptionCommonBase(ErrorCodeBase.SysConfigError, "serverURL=null");
        logger.info("setRedisPool:" + serverURL);
        JedisPoolConfig config = new JedisPoolConfig();

        config.setTestWhileIdle(true); // 默人为 true
        config.setMinEvictableIdleTimeMillis(60000); // 默认值60000
        config.setTimeBetweenEvictionRunsMillis(30000);// 默认30000
        config.setNumTestsPerEvictionRun(-1); // 默认-1，不限制

        config.setMaxActive(1000);
        config.setMaxIdle(5);
        config.setMaxWait(100);
        // config.setTestOnBorrow(true);
        // config.setTestOnReturn(true);

        Pattern regex_w = Pattern.compile("w\\(([^()]+)\\)");
        Pattern regex_r = Pattern.compile("r\\(([^()]+)\\)");

        // W(192.168.10.64:6379|192.168.10.64:6380)R(192.168.10.64:6379|192.168.10.64:6380)
        String r = serverURL.toLowerCase();
        boolean search = false;

        synchronized (SyncRoot) {
            Matcher m = regex_w.matcher(r);
            if (m.find()) {
                String v = m.group(1);
                String[] ss = v.split("\\|");
                for (String server : ss) {
                    if (!jedisPool_map.containsKey(server)) {
                        String[] s = server.split(":");
                        JedisPool pool = new JedisPool(config, s[0], Utils.toInt(s[1], 6379));
                        jedisPool_map.put(server, pool);
                    }
                    serverUrls_w.add(server);
                    jedisPool_ws.add(jedisPool_map.get(server));
                }

                search = true;
            }

            m = regex_r.matcher(r);
            if (m.find()) {
                String v = m.group(1);
                String[] ss = v.split("\\|");
                for (String server : ss) {
                    serverUrls_r.add(server);
                }

                search = true;
            }

            // client链接为空，则把随机去读服务，作为写服务器
            if (search) {
                // 随机获取一个client链接
                r = null;
                if (serverUrls_r.size() > 0) {
                    int index = Utils.getRandom(0, serverUrls_r.size() - 1);
                    index = 1;
                    r = serverUrls_r.get(index);
                }

                if (r == null) {
                    if (jedisPool_ws.size() > 0) {
                        int index = Utils.getRandom(0, jedisPool_ws.size() - 1);
                        jedisPool_r = jedisPool_ws.get(index);
                        logger.info("cache read from:" + serverUrls_w.get(index));
                        return;
                    } else
                        throw new ExceptionCommonBase(ErrorCodeBase.SysConfigError, serverURL);
                }
            }

            String server = r;
            logger.info("cache read from:" + server);
            if (!jedisPool_map.containsKey(server)) {
                serverUrls_w.add(server);
                String[] s = server.split(":");
                JedisPool pool = new JedisPool(config, s[0], Utils.toInt(s[1], 6379));
                jedisPool_map.put(server, pool);
                jedisPool_r = pool;
            } else
                jedisPool_r = jedisPool_map.get(server);

            if (jedisPool_ws.isEmpty()) {
                jedisPool_ws.add(jedisPool_r);
            }
        }
    }

    @Override
    public boolean addRemote(String key, Object obj, int mils) {
        Jedis jedis = getJedis();
        // return jedis.add(key, obj, new Date(seconds * 1000));
        return false;

    }

    public void subscribe(JedisPubSub pubsub, String... channel) {
        Jedis jedis = getJedis();
        jedis.subscribe(pubsub, channel);
    }

    public void psubscribe(JedisPubSub pubsub, String... partten) {
        Jedis jedis = getJedis();
        jedis.subscribe(pubsub, partten);
    }

    public void publishMessage(String channel, String message) {
        Jedis jedis = getJedis();
        jedis.publish(channel, message);
    }

    // /**
    // * 从List中获取一个元素
    // *
    // * @param k
    // * @param index
    // * @return
    // * @throws ExceptionCommonBase
    // * @Author qintao
    // */
    // public <T> T getTFromList(String k, int index) throws ExceptionCommonBase
    // {
    // Jedis jedis = getJedis();
    // String keyType;
    // byte[] key = null;
    // try {
    // key = Utils.toByte(k);
    // keyType = jedis.type(key);
    // if (RedisCacheClient.REDIS_KEY_TYPE_NONE.equals(keyType)
    // || !RedisCacheClient.REDIS_KEY_TYPE_LIST.equals(keyType)) {
    // throw new ExceptionCommonBase(-1, "键不存在或非List键");
    // }
    // if (!"list".equals(keyType)) {
    // return null;
    // } else {
    // return (T) Utils.objectRead(jedis.lindex(key, index));
    // }
    // } catch (ExceptionCommonBase e) {
    // e.printStackTrace();
    // throw new ExceptionCommonBase(-1, e);
    // } catch (ClassNotFoundException e) {
    // e.printStackTrace();
    // throw new ExceptionCommonBase(-1, e);
    // } catch (IOException e) {
    // e.printStackTrace();
    // throw new ExceptionCommonBase(-1, e);
    // } finally {
    // releaseJedis(jedis);
    // }
    // }
    //
    // /**
    // * List头部或者尾部取出一个元素
    // *
    // * @param k
    // * @param object
    // * @param isListHead
    // * @return
    // * @throws ExceptionCommonBase
    // * @Author qintao
    // */
    // @Override
    // public <T> T popTFromList(String k, boolean isListHead)
    // throws ExceptionCommonBase {
    // Jedis jedis = getJedis();
    // byte[] key = null;
    // T value = null;
    // try {
    // key = Utils.toByte(k);
    // String keyType = jedis.type(k);
    // if (RedisCacheClient.REDIS_KEY_TYPE_NONE.equals(keyType)
    // || !RedisCacheClient.REDIS_KEY_TYPE_LIST.equals(keyType)) {
    // throw new ExceptionCommonBase(-1, "键不存在或非List键");
    // }
    // if (isListHead) {
    // byte[] bs = jedis.lpop(key);
    // value = (T) Utils.objectRead(bs);
    // } else {
    // byte[] bs = jedis.rpop(key);
    // value = (T) Utils.objectRead(bs);
    // }
    // } catch (ExceptionCommonBase e) {
    // e.printStackTrace();
    // throw new ExceptionCommonBase(-1, e);
    // } catch (ClassNotFoundException e) {
    // e.printStackTrace();
    // throw new ExceptionCommonBase(-1, e);
    // } catch (IOException e) {
    // e.printStackTrace();
    // throw new ExceptionCommonBase(-1, e);
    // } finally {
    // releaseJedis(jedis);
    // }
    // return value;
    // }
    //
    // /**
    // * 队头或队尾插入一个元素
    // *
    // * @param k
    // * @param v
    // * @param isListHead
    // * @throws ExceptionCommonBase
    // * @Author qintao
    // */
    // @Override
    // public <T> void setT2List(String k, T v, boolean isListHead, int seconds)
    // throws ExceptionCommonBase {
    // Jedis jedis = getJedis();
    // byte[] key = null;
    // try {
    // key = Utils.toByte(k);
    // String keyType = jedis.type(key);
    // if (!RedisCacheClient.REDIS_KEY_TYPE_NONE.equals(keyType)
    // && !RedisCacheClient.REDIS_KEY_TYPE_LIST.equals(keyType)) {
    // throw new ExceptionCommonBase(-1, "键存在且非List键");
    // }
    // if (isListHead) {
    // jedis.lpush(key, Utils.objectWrite(v));
    // } else {
    // jedis.rpush(key, Utils.objectWrite(v));
    // }
    // // if (seconds <= 0 || seconds > THREEYEARSECOND) {
    // // jedis.expire(key, THREEYEARSECOND);
    // // } else {
    // // jedis.expire(key, seconds);
    // // }
    // } catch (ExceptionCommonBase e) {
    // e.printStackTrace();
    // throw new ExceptionCommonBase(-1, e);
    // } catch (IOException e) {
    // e.printStackTrace();
    // throw new ExceptionCommonBase(-1, e);
    // } finally {
    // releaseJedis(jedis);
    // }
    // }
    //
    // /**
    // * 批量插入入List
    // *
    // * @param k
    // * @param list
    // * @param isListHead
    // * @throws CacheException
    // * @Author qintao
    // */
    // @Override
    // public <T> void setBatch2List(String k, List<T> list, boolean isListHead,
    // int second) throws ExceptionCommonBase {
    // Jedis jedis = getJedis();
    // byte[] key = null;
    // try {
    // key = Utils.toByte(k);
    // String keyType = jedis.type(key);
    // Transaction transaction = jedis.multi();
    // if (!RedisCacheClient.REDIS_KEY_TYPE_NONE.equals(keyType)
    // && !RedisCacheClient.REDIS_KEY_TYPE_LIST.equals(keyType)) {
    // throw new ExceptionCommonBase(-1, "键存在且非List键");
    // } else {
    // if (isListHead) {
    // for (int i = list.size() - 1; i >= 0; i--) {
    // transaction.lpush(key, Utils.objectWrite(list.get(i)));
    // }
    // } else {
    // for (T v : list) {
    // transaction.rpush(key, Utils.objectWrite(v));
    // }
    // }
    // transaction.exec();
    // }
    // } catch (ExceptionCommonBase e) {
    // e.printStackTrace();
    // throw new ExceptionCommonBase(-1, e);
    // } catch (IOException e) {
    // e.printStackTrace();
    // throw new ExceptionCommonBase(-1, e);
    // } finally {
    // releaseJedis(jedis);
    // }
    // }
    //
    // @Override
    // public <T> void replaceList(String k, List<T> list, boolean isListHead,
    // int second) throws ExceptionCommonBase {
    // Jedis jedis = getJedis();
    // byte[] key = null;
    // try {
    // key = Utils.toByte(k);
    // String keyType = jedis.type(key);
    // Transaction transaction = jedis.multi();
    // if (!RedisCacheClient.REDIS_KEY_TYPE_NONE.equals(keyType)
    // && !RedisCacheClient.REDIS_KEY_TYPE_LIST.equals(keyType)) {
    // throw new ExceptionCommonBase(-1, "键存在且非List键");
    // } else {
    // jedis.del(key);
    // if (isListHead) {
    // for (int i = list.size() - 1; i >= 0; i--) {
    // transaction.lpush(key, Utils.objectWrite(list.get(i)));
    // }
    // } else {
    // for (T v : list) {
    // transaction.rpush(key, Utils.objectWrite(v));
    // }
    // }
    // transaction.exec();
    // }
    // } catch (ExceptionCommonBase e) {
    // e.printStackTrace();
    // throw new ExceptionCommonBase(-1, e);
    // } catch (IOException e) {
    // e.printStackTrace();
    // throw new ExceptionCommonBase(-1, e);
    // } finally {
    // releaseJedis(jedis);
    // }
    // }
    //
    // /**
    // * 从List中取一段数据
    // *
    // * @param start
    // * @param limit
    // * @param k
    // * @return
    // * @throws ExceptionCommonBase
    // * @Author qintao
    // */
    // @Override
    // public <T> List<T> getRangeTFromList(String k, int start, int limit)
    // throws ExceptionCommonBase {
    // Jedis jedis = getJedis();
    // byte[] key = null;
    // List<T> values = null;
    // try {
    // key = Utils.toByte(k);
    // String keyType = jedis.type(key);
    // if (RedisCacheClient.REDIS_KEY_TYPE_NONE.equals(keyType)) {
    // return new ArrayList<T>();
    // }
    // if (!RedisCacheClient.REDIS_KEY_TYPE_LIST.equals(keyType)) {
    // throw new ExceptionCommonBase(-1, "非List键");
    // }
    // List<byte[]> list = null;
    // if (limit >= 0) {
    // list = jedis.lrange(key, start, start + limit);
    // } else {
    // list = jedis.lrange(key, start, -1);
    // }
    // if (list != null && list.size() > 0) {
    // values = new ArrayList<T>();
    // for (byte[] b : list) {
    // values.add((T) Utils.objectRead(b));
    // }
    // }
    // } catch (UnsupportedEncodingException e) {
    // e.printStackTrace();
    // throw new ExceptionCommonBase(-1, e);
    // } catch (ExceptionCommonBase e) {
    // e.printStackTrace();
    // throw new ExceptionCommonBase(-1, e);
    // } catch (ClassNotFoundException e) {
    // e.printStackTrace();
    // throw new ExceptionCommonBase(-1, e);
    // } catch (IOException e) {
    // e.printStackTrace();
    // throw new ExceptionCommonBase(-1, e);
    // } finally {
    // releaseJedis(jedis);
    // }
    // return values;
    // }
    //
    // /**
    // * 在List中移除所有值为v的数据
    // *
    // * @param k
    // * @param v
    // * @return
    // * @throws ExceptionCommonBase
    // * @Author qintao
    // */
    // @Override
    // public <T> boolean removeFromList(String k, T v) throws
    // ExceptionCommonBase {
    // Jedis jedis = getJedis();
    // byte[] key = null;
    // try {
    // key = Utils.toByte(k);
    // String keyType = jedis.type(key);
    // if (RedisCacheClient.REDIS_KEY_TYPE_NONE.equals(keyType)
    // || !RedisCacheClient.REDIS_KEY_TYPE_LIST.equals(keyType)) {
    // throw new ExceptionCommonBase(-1, "键不存在或非List键");
    // }
    // jedis.lrem(key, 0, Utils.objectWrite(v));
    // } catch (UnsupportedEncodingException e) {
    // e.printStackTrace();
    // throw new ExceptionCommonBase(-1, e);
    // } catch (IOException e) {
    // e.printStackTrace();
    // throw new ExceptionCommonBase(-1, e);
    // } finally {
    // releaseJedis(jedis);
    // }
    // return true;
    // }
    //
    // /**
    // * 取得List长度
    // *
    // * @param k
    // * @return
    // * @throws ExceptionCommonBase
    // * @Author qintao
    // */
    // @Override
    // public long getLenthOfList(String k) throws ExceptionCommonBase {
    // Jedis jedis = getJedis();
    // String keyType;
    // byte[] key = null;
    // try {
    // key = Utils.toByte(k);
    // keyType = jedis.type(key);
    // if (RedisCacheClient.REDIS_KEY_TYPE_NONE.equals(keyType)
    // || !RedisCacheClient.REDIS_KEY_TYPE_LIST.equals(keyType)) {
    // throw new ExceptionCommonBase(-1, "键不存在或非List键");
    // }
    // return jedis.llen(key);
    // } catch (UnsupportedEncodingException e) {
    // e.printStackTrace();
    // throw new ExceptionCommonBase(-1, e);
    // } finally {
    // releaseJedis(jedis);
    // }
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
    // Jedis jedis = getJedis();
    // byte[] key = null;
    // try {
    // key = Utils.toByte(k);
    // String keyType = jedis.type(key);
    // if (!RedisCacheClient.REDIS_KEY_TYPE_NONE.equals(keyType)
    // && !RedisCacheClient.REDIS_KEY_TYPE_LIST.equals(keyType)) {
    // throw new ExceptionCommonBase(-1, "键存在且非List键");
    // }
    // jedis.linsert(key, LIST_POSITION.BEFORE, jedis.lindex(key, index),
    // Utils.objectWrite(v));
    // } catch (UnsupportedEncodingException e) {
    // e.printStackTrace();
    // } catch (IOException e) {
    // e.printStackTrace();
    // } finally {
    // releaseJedis(jedis);
    // }
    // }

}
