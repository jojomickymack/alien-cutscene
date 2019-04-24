package com.central.scenes

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.Actions.*
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.central.App
import com.central.assets.Images.*
import com.central.assets.Sounds.*
import ktx.actors.*
import ktx.scene2d.*


class TheEnd(app: App) : Group() {
    private lateinit var label: Label

    init {
        val background = Image(space())

        table {
            label = label("the end", "transparent")
        }

        app.stg.clear()

        background.setSize(app.stg.width, app.stg.height)
        background.alpha = 0f

        label.setPosition(app.stg.width / 2 - label.width / 2, app.stg.height / 2 - label.height / 2)
        label.alpha = 0f

        background += sequence(Actions.run { suspense().setVolume(suspense().play(), 0.5f) } +
                fadeIn(2f) + delay(10f) + fadeOut(2f) +
                Actions.run {
                    suspense().setVolume(suspense().play(), 0.5f)
                },
                delay(3f),
                Actions.run {
                    app.stg.clear()
                    Gdx.app.exit()
                }
        )

        label += sequence(delay(2f) + fadeIn(2f) + delay(2f) + fadeOut(2f))

        this += background
        this += label
    }
}