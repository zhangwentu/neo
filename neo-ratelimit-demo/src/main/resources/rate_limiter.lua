local key, maxPermits, expire = KEYS[1], ARGV[1], ARGV[2]

local times = redis.call('incr', key)
if tonumber(times) == 1 then
    redis.call('expire', key, tonumber(expire))
end

if tonumber(times) > tonumber(maxPermits) then
    return false
else
    return true
end