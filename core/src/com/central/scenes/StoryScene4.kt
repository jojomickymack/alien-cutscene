package com.central.scenes

import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.Actions.*
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.central.App
import com.central.assets.Images.*
import com.central.assets.Sounds.*
import ktx.actors.*


class StoryScene4(app: App): Group() {
    private val background = Image(boom())

    init {
        app.stg.clear()

        background.setSize(app.stg.width, app.stg.height)
        background.alpha = 0f

        background += sequence(Actions.run { explosion().setVolume(explosion().play(), 0.2f) } + fadeIn(2f) + delay(2f) + fadeOut(2f) +
                Actions.run {
                    app.stg.clear()
                    app.stg.addActor(TheEnd(app))
                }
        )

        this += background
    }
}