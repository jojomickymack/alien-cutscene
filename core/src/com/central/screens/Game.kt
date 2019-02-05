package com.central.screens

import com.central.Application
import ktx.app.KtxScreen

class Game(val application: Application) : KtxScreen {

    init {

    }

    override fun render(delta: Float) {
        GameObj.gm.render(delta)
    }
}
