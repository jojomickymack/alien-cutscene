package com.central.scenes

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.Actions.*
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.central.AppObj
import com.central.assets.Images.*
import com.central.assets.Sounds.*
import com.central.assets.Skins.*
import ktx.actors.*


class TheEnd : Group() {

    init {
        val background = Image(space())
        var label = Label("the end", my_skin(), "transparent")

        AppObj.stg.clear()

        background.setSize(AppObj.stg.width, AppObj.stg.height)
        background.alpha = 0f

        label.setPosition(AppObj.stg.width / 2 - label.width / 2, AppObj.stg.height / 2 - label.height / 2)
        label.alpha = 0f

        background += sequence(Actions.run { suspense().setVolume(suspense().play(), 0.5f) } +
                fadeIn(2f) + delay(10f) + fadeOut(2f) +
                Actions.run {
                    suspense().setVolume(suspense().play(), 0.5f)
                },
                delay(3f),
                Actions.run {
                    AppObj.stg.clear()
                    Gdx.app.exit()
                }
        )

        label += sequence(delay(2f) + fadeIn(2f)) + delay(2f) + fadeOut(2f)

        this += background
        this += label
    }
}