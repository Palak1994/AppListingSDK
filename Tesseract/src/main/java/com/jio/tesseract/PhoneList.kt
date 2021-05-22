package com.jio.tesseract

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.util.Log
import com.jio.tesseract.model.PhoneData


object PhoneList {
    fun getInstalledApps(getSysPackages: Boolean, context: Context): List<PhoneData> {
        val packs: List<PackageInfo> = context.getPackageManager().getInstalledPackages(0)
        val list: MutableList<PhoneData> = ArrayList()
        for (i in packs.indices) {
            val p = packs[i]
            if (!isSystemPackage(p)) {
                val newInfo = PhoneData(
                        p.applicationInfo.loadLabel(context.getPackageManager()).toString(),
                        p.packageName,
                        p.applicationInfo.loadIcon(
                                context.getPackageManager()
                        ),
                        p.applicationInfo.className,
                        p.versionCode,
                        p.versionName
                )
                list.add(newInfo)
            }
        }
        return list

    }

    fun isSystemPackage(pkgInfo: PackageInfo): Boolean {
        return pkgInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM !== 0
    }
}