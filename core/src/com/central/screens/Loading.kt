package com.central.screens

import com.badlogic.gdx.scenes.scene2d.actions.Actions.*
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.central.App
import com.central.AppObj
import ktx.actors.alpha
import ktx.actors.plus
import ktx.actors.plusAssign
import ktx.app.KtxScreen

class Loading(val application: App) : KtxScreen {
    private var label = Label("loading", AppObj.skin, "transparent")

    init {
        label.setPosition(AppObj.stg.width / 2 - label.width / 2, AppObj.stg.height / 2 - label.height / 2)
        label.alpha = 0f
        label += sequence(fadeIn(1f) + delay(1f) + fadeOut(1f))

        AppObj.stg += label
    }

    override fun render(delta: Float) {
        AppObj.stg.act(delta)
        AppObj.stg.draw()
        if (!AppObj.app.tunesManager.update() || !AppObj.app.soundsManager.update() || !AppObj.app.textureManager.update()) {
            println("still loading (simulating longer load time)")
            AppObj.app.textureManager.update()
            AppObj.app.soundsManager.update()
            AppObj.app.tunesManager.update()
            // this is just to make it seem like the loading takes longer - it does mess up the label's fadeIn/fadeOut
            Thread.sleep(500)
        } else {
            println("loading complete")
            application.addScreen(Game(application))
            application.setScreen<Game>()
        }
    }

    override fun dispose() {
        AppObj.stg.clear()
    }
}