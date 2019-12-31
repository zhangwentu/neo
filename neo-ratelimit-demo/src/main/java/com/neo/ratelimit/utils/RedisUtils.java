package com.neo.ratelimit.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 
 * @Description: redis工具类
 *               </p>
 *
 * @author neo
 * @date 2018/9/8下午11:30
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ValueOperations<String, Object> valueOperations;
    @Autowired
    private ValueOperations<String, String> valueOperationsStr;
    @Autowired
    private HashOperations<String, String, Object> hashOperations;
    @Autowired
    private HashOperations<String, String, String> hashOperationsStr;
    @Autowired
    private ListOperations<String, Object> listOperations;
    @Autowired
    private SetOperations<String, Object> setOperations;
    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    /**
     * 默认过期时长，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;
    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1;

    private final static String PATTERN_ALL = "*";

    /**
     * ============ Set 结构 ============
     */

    public void zadd(String key, Object value) {
        setOperations.add(key, toJson(value));
    }

    public long zcount(String key) {
        return setOperations.size(key);
    }

    public Set<Object> zmembers(String key) {
        return setOperations.members(key);
    }

    /**
     * ============ ZSet 结构 ============
     */
    public void zadd(String key, Object value, double score) {
        zSetOperations.add(key, toJson(value), score);
    }

    public void zSetadd(String key, Set<ZSetOperations.TypedTuple<Object>> tuples) {
        zSetOperations.add(key, tuples);
    }

    public Long zCard(String key) {
        return zSetOperations.zCard(key);
    }

    public Long removeRange(String key, long start, long end) {
        return zSetOperations.removeRange(key, start, end);
    }

    public Set<Object> reverseRange(String key, long start, long end) {
        return zSetOperations.reverseRange(key, start, end);
    }

    public <T> List<T> reverseRange(String key, Class<T> clasz, long start, long end) {
        Set<Object> results = reverseRange(key, start, end);
        return results.stream().map(e -> JSONObject.parseObject(e.toString(), clasz)).collect(Collectors.toList());
    }

    public List<Object> lrange(String key, long start, long end) {
        return listOperations.range(key, start, end);
    }

    public Set<Object> range(String key, long start, long end) {
        return zSetOperations.range(key, start, end);
    }

    public <T> List<T> range(String key, Class<T> clasz, long start, long end) {
        Set<Object> results = range(key, start, end);
        return results.stream().map(e -> JSONObject.parseObject(e.toString(), clasz)).collect(Collectors.toList());
    }

    public Set<Object> rangeByScore(String key, double min, double max) {
        return zSetOperations.rangeByScore(key, min, max);
    }

    public <T> List<T> rangeByScore(String key, Class<T> clasz, double min, double max) {
        Set<Object> results = rangeByScore(key, min, max);
        return results.stream().map(e -> JSONObject.parseObject(e.toString(), clasz)).collect(Collectors.toList());
    }

    public Long remove(String key, Object... values) {
        return zSetOperations.remove(key, values);
    }

    public Long removeRangeByScore(String key, double min, double max) {
        return zSetOperations.removeRangeByScore(key, min, max);
    }

    /**
     * 给zset value一项加分
     *
     * @param key
     * @param value
     * @param increment
     */
    public void incrZSetScore(String key, String value, double increment) {
        zSetOperations.incrementScore(key, value, increment);
    }

    /**
     * 获取带分值的zset range
     *
     * @param key
     * @return
     */
    public Set<ZSetOperations.TypedTuple<Object>> rangeByScoreWithScores(String key) {
        return rangeByScoreWithScores(key, Double.MIN_VALUE, Double.MAX_VALUE);
    }

    public Set<ZSetOperations.TypedTuple<Object>> rangeByScoreWithScores(String key, double scoreMin, double scoreMax) {
        return zSetOperations.rangeByScoreWithScores(key, scoreMin, scoreMax, 0, -1);
    }

    /**
     * ============ String 结构 ============
     */
    public Boolean setIfAbsent(String key, Object value, long timeout, TimeUnit unit) {
        return valueOperations.setIfAbsent(key, value, timeout, unit);
    }

    /**
     * 获取或创建一个原子自增的Long类型
     * <p>
     * 自增并返回自增之前的值
     * 
     * @param key
     * @param expire
     * @return
     */
    public Long incr(String key, long expire) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();

        // 初始设置过期时间
        boolean isTrue = (null == increment || increment.longValue() == 0) && expire > 0;
        if (isTrue) {
            entityIdCounter.expire(expire, TimeUnit.SECONDS);
        }
        return increment;
    }

    public void set(String key, Object value, long expire) {
        valueOperations.set(key, toJson(value));
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void pipelineSample() {
        RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
        RedisConnection redisConnection = RedisConnectionUtils.getConnection(factory);
        StringRedisConnection stringRedisConn = (StringRedisConnection)redisConnection;
        try {
            stringRedisConn.openPipeline();
            stringRedisConn.rPop("11");
            redisConnection.closePipeline();
        } finally {
            RedisConnectionUtils.releaseConnection(redisConnection, factory);
        }
    }

    public void leftPush(String key, Object value) {
        listOperations.leftPush(key, value);
    }

    public long leftPushAll(String key, Collection value) {
        return listOperations.leftPushAll(key, value);
    }

    public void rightPush(String key, Object value) {
        listOperations.rightPush(key, value);
    }

    public Object rightPop(String key) {
        return listOperations.rightPop(key);
    }

    public Object rightPop(String key, int timeout) {
        return listOperations.rightPop(key, timeout, TimeUnit.SECONDS);
    }

    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    public <T> T get(String key, Class<T> clazz, long expire) {
        Object value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : fromJson(value.toString(), clazz);
    }

    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    public String get(String key, long expire) {
        Object value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : value.toString();
    }

    public String getStr(String key, long expire) {
        String value = valueOperationsStr.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : value.toString();
    }

    public String getStr(String key) {
        return getStr(key, NOT_EXPIRE);
    }

    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    public Long increment(String key, long delta) {
        return valueOperations.increment(key, delta);
    }

    /**
     * ============ Hash 结构 ============
     */

    public void hset(String key, String field, Object value, long expire) {
        hashOperations.put(key, field, value);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void putAll(String key, Map<? extends String, ? extends Object> m) {
        putAll(key, m, NOT_EXPIRE);
    }

    public void putAll(String key, Map<? extends String, ? extends Object> m, long expire) {
        hashOperations.putAll(key, m);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public Map<String, Object> entries(String key) {
        return hashOperations.entries(key);
    }

    public Map<String, String> entriesStr(String key) {
        return hashOperationsStr.entries(key);
    }

    public List<Object> values(String key) {
        return hashOperations.values(key);
    }

    public Object hget(String key, String field) {
        return hashOperations.get(key, field);
    }

    /**
     * 获取所有的fields
     * 
     * @param key
     * @return
     */
    public Set<String> hgetKeySet(String key) {
        return hashOperations.keys(key);
    }

    public void delete(String... keys) {
        if (keys == null) {
            return;
        }
        if (keys.length == 1) {
            redisTemplate.delete(keys[0]);
        }
        redisTemplate.delete(Arrays.asList(keys));
    }

    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    public void expire(String key, long expire, TimeUnit timeUnit) {
        redisTemplate.expire(key, expire, timeUnit);
    }

    public void expire(String key, long expire) {
        expire(key, expire, TimeUnit.SECONDS);
    }

    public Long eval(String script, List<String> keys, List<String> args) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            Object nativeConnection = connection.getNativeConnection();
            // 集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
            // 集群模式
            if (nativeConnection instanceof JedisCluster) {
                return (Long)((JedisCluster)nativeConnection).eval(script, keys, args);
            }

            // 单机模式
            else if (nativeConnection instanceof Jedis) {
                return (Long)((Jedis)nativeConnection).eval(script, keys, args);
            }
            return 0L;
        });
    }

    public String scriptLoad(String script, String key) {
        return redisTemplate.execute((RedisCallback<String>) connection -> {
            Object nativeConnection = connection.getNativeConnection();
            // 集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
            // 集群模式
            if (nativeConnection instanceof JedisCluster) {
                return ((JedisCluster)nativeConnection).scriptLoad(script, key);
            }

            // 单机模式
            else if (nativeConnection instanceof Jedis) {
                return ((Jedis)nativeConnection).scriptLoad(script);
            }
            return null;
        });
    }

    public Long evalsha(String sha, List<String> keys, List<String> args) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            Object nativeConnection = connection.getNativeConnection();
            // 集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
            // 集群模式
            if (nativeConnection instanceof JedisCluster) {
                return (Long)((JedisCluster)nativeConnection).evalsha(sha, keys, args);
            }

            // 单机模式
            else if (nativeConnection instanceof Jedis) {
                return (Long)((Jedis)nativeConnection).evalsha(sha, keys, args);
            }
            return 0L;
        });
    }

    /**
     * 获取过期时间
     *
     * @param key
     * @return
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * Object转成JSON数据
     */
    private String toJson(Object object) {
        if (object instanceof Integer || object instanceof Long || object instanceof Float || object instanceof Double
            || object instanceof Boolean || object instanceof String) {
            return String.valueOf(object);
        }
        return JSONObject.toJSONString(object);
    }

    /**
     * JSON数据，转成Object
     */
    private <T> T fromJson(String json, Class<T> clazz) {
        return JSONObject.parseObject(json, clazz);
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return this.redisTemplate;
    }
}
