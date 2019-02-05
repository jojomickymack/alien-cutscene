package com.central.managers

import com.central.scenes.*
import com.central.screens.GameObj

class GameManager {
    var state: Scene

    init {
        state = Scene1()
        GameObj.stg.addActor(state)
    }

    fun render(delta: Float) {
        GameObj.stg.act(delta)
        GameObj.stg.draw()
    }
}