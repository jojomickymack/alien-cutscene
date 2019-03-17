package com.central.scenes

import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.Actions.*
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.central.assets.Images.*
import com.central.assets.Sounds.*
import com.central.assets.Skins.*
import com.central.AppObj
import ktx.actors.*


class StoryScene3 : Group() {
    private val background = Image(core())
    private var label = Label("We'll destroy the ship...", my_skin(), "transparent")
    private var label2 = Label("and use the escape pod.", my_skin(), "transparent")

    init {
        AppObj.stg.clear()

        label.setPosition(AppObj.stg.width / 2 - label.width / 2, AppObj.stg.height / 2 - label.height / 2)
        label.alpha = 0f

        label2.setPosition(AppObj.stg.width / 2 - label2.width / 2, AppObj.stg.height / 2 - label.height / 2)
        label2.alpha = 0f

        background.setSize(AppObj.stg.width, AppObj.stg.height)
        background.alpha = 0f

        background += sequence(Actions.run { suspense().setVolume(suspense().play(), 0.5f) } +
                fadeIn(2f) + delay(10f) + fadeOut(2f) +
                Actions.run {
                    AppObj.stg.clear()
                    AppObj.stg.addActor(StoryScene4())
                }
        )

        label += sequence(delay(2f) + fadeIn(2f)) + delay(2f) + fadeOut(2f)
        label2 += sequence(delay(8f) + fadeIn(2f) + delay(2f) + fadeOut(2f))

        this += background
        this += label
        this += label2
    }
}