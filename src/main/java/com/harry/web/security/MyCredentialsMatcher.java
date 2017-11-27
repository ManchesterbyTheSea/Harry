package com.harry.web.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by chenhaibo on 2017/11/27.
 */
public class MyCredentialsMatcher extends HashedCredentialsMatcher {
    //集群中可能会出错
    private Cache<String, AtomicInteger> lgoinRetryCache;

    public MyCredentialsMatcher(CacheManager cacheManager) {
        lgoinRetryCache = cacheManager.getCache("lgoinRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        //retry count + 1
        AtomicInteger retryCount = lgoinRetryCache.get(username);
        if (null == retryCount) {
            retryCount = new AtomicInteger(0);
            lgoinRetryCache.put(username, retryCount);
        }
        if (retryCount.incrementAndGet() > 5) {
            throw new ExcessiveAttemptsException("username: " + username + " tried to login more than 5 times in period");
        }
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            //clear retry data
            lgoinRetryCache.remove(username);
        }
        return matches;
    }
}
