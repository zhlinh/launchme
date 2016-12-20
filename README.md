# launchme
==================

# USUAGE
------------------

Update: 2016-12-20

**Settings** in `/data/data/com.monet.launchme/files/launchme.conf`

Every line is a command which Android devices will start up with.

> Default Settings

At launchme.conf:
```
/data/src/main
```

> Initiate App

Install:
```
adb [-s <Device Serial Num>] push launchme-android-1.0.apk /data/local/tmp/com.monet.launchme
adb [-s <Device Serial Num>] shell pm install -r "/data/local/tmp/com.monet.launchme"
```

Launch:
```
adb [-s <Device Serial Num>] shell am start -n "com.monet.launchme/com.monet.launchme.activity.MainActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER
```
