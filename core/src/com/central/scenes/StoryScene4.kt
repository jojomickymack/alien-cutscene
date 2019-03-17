package com.central.scenes

import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.Actions.*
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.central.AppObj
import com.central.assets.Images.*
import com.central.assets.Sounds.*
import ktx.actors.*


class StoryScene4: Group() {
    private val background = Image(boom())

    init {
        AppObj.stg.clear()

        background.setSize(AppObj.stg.width, AppObj.stg.height)
        background.alpha = 0f

        background += sequence(Actions.run { explosion().setVolume(explosion().play(), 0.2f) } + fadeIn(2f) + delay(2f) + fadeOut(2f) +
                Actions.run {
                    AppObj.stg.clear()
                    AppObj.stg.addActor(TheEnd())
                }
        )

        this += background
    }
}