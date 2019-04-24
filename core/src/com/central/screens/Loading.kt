package com.central.screens

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.central.App
import ktx.actors.alpha
import ktx.app.KtxScreen
import ktx.actors.plusAssign
import ktx.scene2d.*

class Loading(val app: App) : KtxScreen {
    private lateinit var label: Label

    init {
        table {
            label = label("loading", "transparent")
        }

        label.setPosition(app.stg.width / 2 - label.width / 2, app.stg.height / 2 - label.height / 2)
        label.alpha = 1f

        app.stg += label
    }

    override fun render(delta: Float) {
        app.stg.act(delta)
        app.stg.draw()

        if (!app.tunesManager.update() || !app.soundsManager.update() || !app.textureManager.update()) {
            println("still loading (simulating longer load time)")
            app.textureManager.update()
            app.soundsManager.update()
            app.tunesManager.update()
            // this is just to make it seem like the loading takes longer - in reality it barely takes any time at all
            Thread.sleep(500)
        } else {
            println("loading complete")
            app.stg.clear()
            app.addScreen(Game(app))
            app.setScreen<Game>()
        }
    }
}