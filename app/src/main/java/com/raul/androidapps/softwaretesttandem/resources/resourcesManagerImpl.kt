package com.raul.androidapps.softwaretesttandem.resources

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourcesManagerImpl @Inject constructor(val context: Context) : ResourcesManager {

    override fun getString(resId: Int): String {
        return context.getString(resId)
    }

    override fun getColor(resId: Int): Int {
        return ResourcesCompat.getColor(context.resources, resId, null)
    }

    override fun getInteger(resId: Int): Int {
        return context.resources.getInteger(resId)
    }

    override fun getResources(): Resources {
        return context.resources
    }

    override fun getDpFromSp(dimensionInDp: Int): Int {
        return context.resources.getDimensionPixelSize(dimensionInDp)
    }

    override fun getDimension(resId: Int): Int {
        return context.resources.getDimension(resId).toInt()
    }

    override fun getDrawable(resId: Int): Drawable? {
        return ContextCompat.getDrawable(context, resId)
    }

    override fun getResourceIdFromName(resourceName: String): Int {
        return context.resources.getIdentifier(resourceName, "id", context.packageName)
    }

    override fun getPlural(resId: Int, value: Int, varargs: Int?): String{
        return if(varargs == null){
            context.resources.getQuantityString(resId, value)
        }else {
            context.resources.getQuantityString(resId, value, varargs)
        }
    }

    override fun getPlural(resId: Int, value: Int) = getPlural(resId, value, null)
}