package com.central.screens

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.central.App
import com.central.AppObj
import ktx.actors.alpha
import ktx.actors.plusAssign
import ktx.app.KtxScreen
import com.central.assets.Skins.*

class Loading(val application: App) : KtxScreen {
    private var label = Label("loading", my_skin(), "transparent")

    init {
        label.setPosition(AppObj.stg.width / 2 - label.width / 2, AppObj.stg.height / 2 - label.height / 2)
        label.alpha = 1f

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
            // this is just to make it seem like the loading takes longer - in reality it barely takes any time at all
            Thread.sleep(500)
        } else {
            println("loading complete")
            AppObj.stg.clear()
            application.addScreen(Game(application))
            application.setScreen<Game>()
        }
    }
}