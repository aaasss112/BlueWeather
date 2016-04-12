package com.blueweather.app.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import com.blueweather.app.domain.Weather;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by user on 2016/4/5.
 */
public class WeatherCacheUtil {

    private String TAG = WeatherCacheUtil.this.getClass().getSimpleName();
    //硬盘缓存大小 10MB
    private static final int DISKMAXSIZE = 10 * 1024 * 1024;
    private static DiskLruCache mDiskLruCache = null;


    public WeatherCacheUtil(Context context) {
        try {
            File cacheDir = getDiskCacheDir(context, "Weather");
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            mDiskLruCache = DiskLruCache.open(cacheDir, getAppVersion(context), 1, DISKMAXSIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从硬盘缓存中获取Weather类
     * @param cityName
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Weather getWeather(String cityName) throws IOException, ClassNotFoundException {
        String key = hashKeyForDisk(cityName);
        DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
        Weather weather = null;
        if (snapshot != null) {
            InputStream in = snapshot.getInputStream(0);
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            weather = (Weather) objectInputStream.readObject();
        }
        return weather;
    }

    /**
     * 将Weather类写入到硬盘缓存
     * @param cityName
     * @param weather
     * @throws IOException
     */
    public void putWeather(String cityName, Weather weather) throws IOException {
        String key = hashKeyForDisk(cityName);
        DiskLruCache.Editor editor = mDiskLruCache.edit(key);
        if (editor != null) {
            OutputStream outputStream = editor.newOutputStream(0);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(weather);
            editor.commit();
        }
    }

    /**
     *
     * @throws IOException
     */
    public void closeDiskCache() throws IOException{
        mDiskLruCache.close();
    }

    /**
     *
     * @param cityName
     * @throws IOException
     */
    public void removeFromDiskCache(String cityName) throws IOException{
        String key = hashKeyForDisk(cityName);
        mDiskLruCache.remove(key);
    }

    public void flushDiskCache() throws IOException {
        mDiskLruCache.flush();
    }



    /**
     * 判断是否存在SD卡，没有则选择手机内存空间
     *
     * @param context
     * @param uniqueName
     * @return
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    public int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * MD5加密函数
     * @param key
     * @return
     */
    public String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

}
