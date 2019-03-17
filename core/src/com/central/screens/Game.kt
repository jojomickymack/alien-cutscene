package com.central.screens

import com.central.App
import com.central.scenes.*
import ktx.actors.plusAssign
import ktx.app.KtxScreen
import com.central.assets.Tunes.*

class Game(val app: App) : KtxScreen {

    init {
        app.stg += TitleScene(app)
    }

    override fun show() {
        // you can quickly skip to whichever scene you're working on
        //AppObj.stg += StoryScene2()

        theme().volume = 0.5f
        theme().play()
    }

    override fun render(delta: Float) {
        app.stg.act(delta)
        app.stg.draw()
    }
}
