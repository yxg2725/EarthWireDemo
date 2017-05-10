package com.huadin.earthwire.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 华电 on 2017/5/10.
 */

public class SharedPreferenceUtils {
  private static SharedPreferenceUtils instance;
  private static SharedPreferences preferences;

  private SharedPreferenceUtils(Context context) {
    if(preferences == null){
      preferences = context.getSharedPreferences(Constant.PREFERENCE_CONFIG, Context.MODE_PRIVATE);
    }
  }


  public static SharedPreferenceUtils getInstance(Context context){
    if(instance == null){
      return new SharedPreferenceUtils(context);
    }
    return instance;
  }


  public  void putStringSharedPrefreence(String key,String value){
    preferences.edit().putString(key,value).apply();
  }

  public String getStringSharedPreference(String key,String defaultValue){
    String value = preferences.getString(key, defaultValue);
    return value;
  }

}
