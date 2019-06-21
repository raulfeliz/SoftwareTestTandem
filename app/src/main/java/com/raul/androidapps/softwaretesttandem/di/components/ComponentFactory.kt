package com.raul.androidapps.softwaretesttandem.di.components

import com.raul.androidapps.softwaretesttandem.TandemApplication


object ComponentFactory {

    fun component(context: TandemApplication): TandemComponent {
        return DaggerTandemComponent.builder()
                .application(context)
                .build()
    }

}